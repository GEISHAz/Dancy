package com.ssafy.dancy.message.request.video;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public record ConvertVideoRequest(

        @NotNull(message = "레퍼런스 비디오 URL 을 입력해주세요")
        @URL(message = "URL 형식으로 입력해야 합니다.")
        String referenceVideoUrl,

        @NotNull(message = "연습 비디오 URL 을 입력해주세요")
        @URL(message = "URL 형식으로 입력해야 합니다.")
        String practiceVideoUrl
) {
}
