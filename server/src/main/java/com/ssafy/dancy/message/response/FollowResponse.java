package com.ssafy.dancy.message.response;

import lombok.Builder;

public record FollowResponse(

    String profileImageUrl,
    String nickname
){
    @Builder
    public FollowResponse {
    }
}
