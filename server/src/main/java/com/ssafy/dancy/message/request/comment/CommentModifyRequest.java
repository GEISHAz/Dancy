package com.ssafy.dancy.message.request.comment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

public record CommentModifyRequest(
        @NotNull(message = "댓글를 입력해 주세요.")
        @Size(min = 1, max = 255, message = "댓글은 1자 이상 255자 이하여야 합니다.")
        String content
) {
    @Builder
    public CommentModifyRequest{

    }
}
