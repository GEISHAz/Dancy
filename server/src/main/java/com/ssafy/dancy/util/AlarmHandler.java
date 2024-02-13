package com.ssafy.dancy.util;

import com.ssafy.dancy.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
@Slf4j
public class AlarmHandler {

    private final Map<Long, SseEmitter> userEmitters = new ConcurrentHashMap<>();

    // 이 부분을 활용하려면, text/event-stream 유형으로 produce 하여 서비스를 제공해야 한다.
    // SSE 는 양방향 통신이 필요하지 않고, 단방향으로 한번씩만 알람을 보내면 되는 서비스에 적합하다고 알려져 있다.
    // WebSocket 보다 서버 부하가 적다는 장점이 있다는 것 같아, 한번 사용해 보려고 한다.
    public SseEmitter subscribe(User user){
        Long userId = user.getUserId();
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);

        try{
            emitter.send(SseEmitter.event().name("INIT"));
        }catch(IOException e){
            log.warn("{} 닉네임의 계정을 연결하는 데 있어 IOException 발생", user.getNickname());
        }

        SseEmitter previousEmitter = userEmitters.put(userId, emitter);

        if(previousEmitter != null){
            previousEmitter.complete();
        }

        emitter.onCompletion(() -> userEmitters.remove(userId, emitter));
        emitter.onTimeout(() -> userEmitters.remove(userId, emitter));
        emitter.onError((e) -> userEmitters.remove(userId, emitter));

        return emitter;
    }

    @Async
    @Scheduled(fixedRate = 30000) // 1000 가 1초, 30000 은 30초
    // 일정한 시간 간격으로, 혹은 특정 시간대에 Code 가 실행되도록 @Scheduled 를 설정할 수 있다.
    // 이 메소드는, 매 30초마다 클라이언트로 하트비트 전송을 보내, 계속 커넥션이 살아있음을 알려주는 장치이다.
    public void sentHeartbeatToClients(){
        for(Map.Entry<Long, SseEmitter> entry : userEmitters.entrySet()){
            try {
                entry.getValue().send(SseEmitter.event().name("heartbeat").data("heartbeat send"));
            } catch (IOException e) {
                userEmitters.remove(entry.getKey());
            }
        }
    }

    @Async
    // 유저에게 이벤트를 날리는 비동기 메소드이다.
    // SSeEmitter (프론트앤드 에서는 ssePolyfill 객체) 에서, 해당
    public void sendEventToUser(Long userId, String eventName, String dataToSend){
        SseEmitter emitter = userEmitters.get(userId);
        if(emitter != null){
            try{
                emitter.send(SseEmitter.event().name(eventName).data(dataToSend));
            }catch (IOException e){
                emitter.completeWithError(e);
                userEmitters.remove(userId);
            }
        }
    }


}
