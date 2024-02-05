package com.ssafy.dancy.message.response;

import lombok.Builder;

public record ArticleLikeResponse(
        int articleLikeCount,
        boolean isArticleLiked
) {
    @Builder
    public ArticleLikeResponse {
    }
}
