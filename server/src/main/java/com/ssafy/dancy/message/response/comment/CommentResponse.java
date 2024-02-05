package com.ssafy.dancy.message.response.comment;

import lombok.Builder;

public record CommentResponse(
        Long commentId,
        String content
) {
    @Builder
    public CommentResponse {
    }
}
