package com.ssafy.dancy.message.response.article;

import lombok.Builder;

@Builder
public record ArticleSimpleResponse(
        Long articleId,
        String articleTitle,
        String articleThumbnail,
        Long authorId,
        String authorProfileImage,
        String authorName,
        Long articleView
) {
}
