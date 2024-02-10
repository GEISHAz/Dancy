package com.ssafy.dancy.message.response.article;

import lombok.Builder;

@Builder
public record ArticleSaveResponse(
        boolean isSaved,
        Long articleId,
        String saveUserNickname,
        String articleTitle,
        String articleAuthorNickname
) {
}
