package com.ssafy.dancy.message.response.article;

import com.ssafy.dancy.entity.Video;
import lombok.Builder;

import java.time.LocalDateTime;


@Builder
public record ArticleDetailResponse(
    Long articleId,
    String articleTitle,
    String articleContent,
    String thumbnailImageUrl,
    Long view,
    int articleLike,
    LocalDateTime createdDate,
    boolean isArticleLiked,
    boolean isAuthorFollowed,
    boolean isArticleSaved,
    Double score,
    int follower,
    long authorId,
    String nickname,
    String profileImageUrl,
    String videoUrl

){



}
