package com.ssafy.dancy.message.request.comment;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;


public record CommentRequest(
        @NotNull(message = "댓글를 입력해 주세요.")
        @Size(min = 1, max = 255, message = "댓글은 1자 이상 255자 이하여야 합니다.")
        String content,

        @NotBlank(message = "부모 아이디를 입력해 주세요. parentId는 부모댓글일때 -1 자식댓글일때 부모댓글의id를 가집니다.")
        @Min(message = "0 이상으로 입력해 주세요", value = 0L)
        Long parentId
) {


    @Builder
    public CommentRequest {

    }
}
