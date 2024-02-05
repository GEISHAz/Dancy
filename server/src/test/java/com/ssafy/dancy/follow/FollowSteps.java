package com.ssafy.dancy.follow;

import com.ssafy.dancy.message.request.follow.FollowRequest;
import org.springframework.stereotype.Component;

@Component
public class FollowSteps {

    public FollowRequest 팔로우_정보_생성(String nickname){
        return FollowRequest.builder()
                .nickname(nickname)
                .build();
    }

}
