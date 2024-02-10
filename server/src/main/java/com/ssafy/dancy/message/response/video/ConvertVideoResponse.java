package com.ssafy.dancy.message.response.video;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ConvertVideoResponse(
        String referenceVideoUrl,
        String practiceVideoUrl,
        LocalDateTime requestTime
) {
}
