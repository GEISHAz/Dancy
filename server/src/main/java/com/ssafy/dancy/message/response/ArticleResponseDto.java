package com.ssafy.dancy.message.response;

import com.ssafy.dancy.entity.Article;
import lombok.Builder;

public record ArticleResponseDto (
    Article article,
    boolean isArticleLiked,
    boolean isAuthorFollowing,
    int score,
    int follower)
{
    @Builder
    public ArticleResponseDto {
    }
}
