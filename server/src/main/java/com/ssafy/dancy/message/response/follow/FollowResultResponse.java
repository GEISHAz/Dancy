package com.ssafy.dancy.message.response.follow;

import lombok.Builder;

public record FollowResultResponse(
        Long followInfoId,
        String followerNickname,
        String followedNickname
) {
    @Builder
    public FollowResultResponse{

    }
}
