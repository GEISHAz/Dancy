package com.ssafy.dancy.message.response;

import lombok.Builder;

public record ResponseCommentLikeDto(
        int commentLike,
        boolean isCommentLiked
) {
    @Builder
    public ResponseCommentLikeDto {
    }
}
