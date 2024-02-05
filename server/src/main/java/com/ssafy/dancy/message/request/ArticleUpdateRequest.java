package com.ssafy.dancy.message.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;

public record ArticleUpdateRequest(
        @NotEmpty(message = "게시물 제목을 입력해 주세요")
        @Size(max = 40, message = "게시물 제목은 40자 아래로 입력해 주세요")
        String articleTitle,
        @NotEmpty(message = "게시물 내용을 입력해 주세요")
        String articleContent,
//        @URL
        String video,
//        @URL
        String thumbnailImageUrl
) {
    @Builder
    public ArticleUpdateRequest {

    }
}
