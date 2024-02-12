package com.ssafy.dancy.message.response.video;

import lombok.Builder;

import java.util.List;

@Builder
public record ConvertResultResponse(
        List<VideoWrongSection> wrongSections,
        String videoUrl,
        String thumbnailImageUrl,
        String nickname,
        String videoTitle,
        Double score
) {
}
