package com.ssafy.dancy.message.response.video;

import lombok.Builder;

@Builder
public record UploadVideoResponse(
        Long videoId,
        String resultVideoUrl
) {
}
