package com.ssafy.dancy.message.response;

import com.ssafy.dancy.entity.Article;
import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.entity.Video;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Builder
public record ArticleResponseDto (
    Long articleId,
    String articleTitle,
    String articleContent,
    String thumbnailImageUrl,
    String thumbnailVideoUrl,
    Long view,
    int articleLike,
    LocalDateTime createdDate,
    boolean isArticleLiked,
    boolean isAuthorFollowing,
    int score,
    int follower,
    long userId,
    String nickname,
    String profileImageUrl,
    Video video

){



}
