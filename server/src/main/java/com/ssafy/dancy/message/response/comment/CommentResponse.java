package com.ssafy.dancy.message.response.comment;

import lombok.Builder;

import java.util.Date;

public record CommentResponse(
        Long commentId,
        String content,
        Date createdDate
) {
    @Builder
    public CommentResponse {
    }
}
