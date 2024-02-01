package com.ssafy.dancy.message.response;

import com.ssafy.dancy.entity.Article;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ArticleResponseDto {

    Article article;
    boolean isArticleLiked;
    boolean isAuthorFollowing;
    int score;
    int follower;

    @Builder
    public ArticleResponseDto() {
    }
}
