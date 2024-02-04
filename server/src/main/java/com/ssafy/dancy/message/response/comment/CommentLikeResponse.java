package com.ssafy.dancy.message.response.comment;

import lombok.Builder;

public record CommentLikeResponse(
        int commentLike,
        boolean isCommentLiked
) {
    @Builder
    public CommentLikeResponse {
    }
}
