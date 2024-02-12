package com.ssafy.dancy.message.request.video;

import lombok.Builder;

@Builder
public record ConvertToPythonRequest(
        String gtUrl,
        String pracUrl
) {
}
