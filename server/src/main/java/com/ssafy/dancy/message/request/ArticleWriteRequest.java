package com.ssafy.dancy.message.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import org.hibernate.validator.constraints.URL;

public record ArticleWriteRequest(
        @NotEmpty
        String articleTitle,
        @NotEmpty
        String articleContent,
//        @URL
        String video,
//        @URL
        String thumbnailImageUrl
) {
    @Builder
    public ArticleWriteRequest{

    }
}
