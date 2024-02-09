package com.ssafy.dancy.message.response.video;

import lombok.Builder;

@Builder
public record ConvertVideoResponse(
        Long videoStatusId,
        String referenceVideoUrl,
        String practiceVideoUrl,
        String status
) {
}
