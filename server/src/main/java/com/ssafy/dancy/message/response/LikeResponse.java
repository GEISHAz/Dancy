package com.ssafy.dancy.message.response;

import lombok.Builder;

public record LikeResponse(
    String profileImageUrl,
    String nickname
) {
    @Builder
    public LikeResponse {
    }
}
