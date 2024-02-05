package com.ssafy.dancy.message.response.comment;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Date;

public record CommentResponse(
        Long commentId,
        String content,
        LocalDateTime createdDate,
        Integer commentLike,
        String authorNickname,
        Long articleId,
        Long parentId
) {
    @Builder
    public CommentResponse {
    }
}
