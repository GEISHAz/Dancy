package com.ssafy.dancy.message.request.follow;

import lombok.Builder;

public record FollowRequest(String nickname) {

    @Builder
    public FollowRequest{

    }
}
