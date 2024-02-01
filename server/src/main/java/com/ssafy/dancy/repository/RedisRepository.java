package com.ssafy.dancy.repository;

import com.ssafy.dancy.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class RedisRepository {

    private final RedisTemplate<Object, Object> redisTemplate;

    private static final String EMAIL_VERIFY_PREFIX = "VERIFY";
    private static final String EMAIL_VERIFY_SUCCESS_PREFIX = "SUCCESS";
    private static final String REFRESH_TOKEN_PREFIX = "RT";
    private static final String BLACKLIST_TOKEN_PREFIX = "BLACK";

    public String saveEmailVerifyCode(String targetEmail, String code, int timeLimit){
        String key = String.format("%s:%s", EMAIL_VERIFY_PREFIX, targetEmail);
        return saveKeyValue(key, code, timeLimit, TimeUnit.MINUTES);
    }

    public String getEmailVerifyCode(String targetEmail){
        String key = String.format("%s:%s", EMAIL_VERIFY_PREFIX, targetEmail);
        return (String)redisTemplate.opsForValue().get(key);
    }

    public void saveVerifySuccess(String targetEmail, int timeLimit){
        String key = getVerifySuccessKey(targetEmail);
        saveKeyValue(key, "1", timeLimit, TimeUnit.MINUTES);
    }
    public Boolean checkVerifiedEmail(String targetEmail){
        return redisTemplate.hasKey(getVerifySuccessKey(targetEmail));
    }

    public void saveRefreshToken(String email, String token, int timeLimit){
        String key = getRefreshTokenKey(email);
        saveKeyValue(key, token, timeLimit, TimeUnit.DAYS);
    }

    public void logoutProcess(User user, int timeLimit) {
        String key = getRefreshTokenKey(user.getEmail());
        String token = (String)redisTemplate.opsForValue().get(key);

        redisTemplate.delete(key);
        saveKeyValue(getTokenBlacklistKey(token), user.getEmail(), timeLimit, TimeUnit.DAYS);
    }

    private static String getVerifySuccessKey(String targetEmail) {
        return String.format("%s:%s", EMAIL_VERIFY_SUCCESS_PREFIX, targetEmail);
    }

    private static String getRefreshTokenKey(String email){
        return String.format("%s:%s", REFRESH_TOKEN_PREFIX, email);
    }

    private static String getTokenBlacklistKey(String token){
        return String.format("%s:%s", BLACKLIST_TOKEN_PREFIX, token);
    }

    private String saveKeyValue(String key, String value, int limitMinute, TimeUnit timeUnit){
        log.info("레디스 템플릿 : redisTemplate : {}", redisTemplate.toString());
        ValueOperations<Object, Object> operation = redisTemplate.opsForValue();
        try{ // 미봉책. 나중에 더 상세히 파 볼 것.
            operation.set(key, value, limitMinute, timeUnit);
            log.info("key: {}, value: {} 로 {} 간 redis 저장", key, value, limitMinute);
        }catch(NullPointerException ignored){}
        return String.format("%s -> %s", key, value);
    }
}
