package com.ssafy.dancy.message.response.video;

import lombok.Builder;

import java.util.List;

@Builder
public record VideoConvertResponse(
        List<VideoWrongSection> wrongSections,
        String totalUrl,
        String thumbnailImageUrl
) {
}
