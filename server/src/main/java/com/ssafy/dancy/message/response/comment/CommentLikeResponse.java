package com.ssafy.dancy.message.response.comment;

import lombok.Builder;

public record CommentLikeResponse(
        int commentLikeCount,
        boolean isCommentLiked
) {
    @Builder
    public CommentLikeResponse {
    }
}
