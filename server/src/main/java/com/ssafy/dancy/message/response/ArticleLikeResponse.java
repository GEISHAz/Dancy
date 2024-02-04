package com.ssafy.dancy.message.response;

import lombok.Builder;

public record ArticleLikeResponse(
        int articleLike,
        boolean isArticleLiked
) {
    @Builder
    public ArticleLikeResponse {
    }
}
