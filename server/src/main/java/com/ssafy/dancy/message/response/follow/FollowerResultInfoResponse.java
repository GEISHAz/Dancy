package com.ssafy.dancy.message.response.follow;

import lombok.Builder;

public record FollowerResultInfoResponse(
        String nickname,

        boolean followed,
        Integer following,
        Integer follower
) {
    @Builder
    public FollowerResultInfoResponse {

    }
}
