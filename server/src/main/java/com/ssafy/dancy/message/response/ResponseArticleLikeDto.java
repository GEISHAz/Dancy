package com.ssafy.dancy.message.response;

import lombok.Builder;

public record ResponseArticleLikeDto(
        int articleLike,
        boolean isArticleLiked
) {
    @Builder
    public ResponseArticleLikeDto {
    }
}
