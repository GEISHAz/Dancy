package com.ssafy.dancy.message.request.article;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

public record ArticleWriteRequest(
        @NotEmpty(message = "게시물 제목을 입력해 주세요")
        @Size(max = 40, message = "게시물 제목은 40자 아래로 입력해 주세요")
        String articleTitle,
        @NotEmpty(message = "게시물 내용을 입력해 주세요")
        String articleContent,
        @NotNull(message = "videoId 는 필수값입니다.")
        @Min(value = 1L, message = "videoId 는 1 이상의 수로 입력해 주세요")
        Long videoId
) {
    @Builder
    public ArticleWriteRequest {

    }
}
