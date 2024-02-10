package com.ssafy.dancy.message.response.video;

import lombok.Builder;

import java.util.List;

@Builder
public record ConvertResultResponse(
        List<VideoWrongSection> wrongSections,
        String videoUrl,
        Double score
) {
}
