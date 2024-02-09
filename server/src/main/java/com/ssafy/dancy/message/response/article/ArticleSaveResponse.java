package com.ssafy.dancy.message.response.article;

import lombok.Builder;

@Builder
public record ArticleSaveResponse(
        Long saveId,
        Long articleId,
        String saveUserNickname,
        String articleTitle,
        String articleAuthorNickname
) {
}
