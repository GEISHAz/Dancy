package com.ssafy.dancy.message.response.follow;

import lombok.Builder;

public record FollowResponse(

    String profileImageUrl,
    String nickname
){
    @Builder
    public FollowResponse {
    }
}
