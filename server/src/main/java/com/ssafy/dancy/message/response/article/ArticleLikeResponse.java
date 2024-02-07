package com.ssafy.dancy.message.response.article;

import lombok.Builder;

public record ArticleLikeResponse(
        int articleLikeCount,
        boolean isArticleLiked
) {
    @Builder
    public ArticleLikeResponse {
    }
}
