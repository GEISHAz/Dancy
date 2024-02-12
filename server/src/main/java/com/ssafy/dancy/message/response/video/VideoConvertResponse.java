package com.ssafy.dancy.message.response.video;

import lombok.Builder;

import java.util.List;

@Builder
public record VideoConvertResponse(
        List<VideoWrongSection> list,
        String totalUrl,
        String thumbnailImageUrl,
        Double total_accuracy
) {
}
