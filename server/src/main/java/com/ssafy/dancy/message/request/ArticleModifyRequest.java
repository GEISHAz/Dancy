package com.ssafy.dancy.message.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

public record ArticleModifyRequest(

    @NotEmpty
    String articleTitle,
    @NotEmpty
    String articleContent
) {


    @Builder
    public ArticleModifyRequest {
    }
}
