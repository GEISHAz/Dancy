package com.ssafy.dancy.message.response;

import com.ssafy.dancy.entity.Video;
import lombok.Builder;

import java.time.LocalDateTime;


@Builder
public record ArticleDetailResponse(
    Long articleId,
    String articleTitle,
    String articleContent,
    String thumbnailImageUrl,
    String thumbnailVideoUrl,
    Long view,
    int articleLike,
    LocalDateTime createdDate,
    boolean isArticleLiked,
    boolean isAuthorFollowed,
    int score,
    int follower,
    long authorId,
    String nickname,
    String profileImageUrl,
    Video video

){



}
