package com.ssafy.dancy.message.response.video;

import lombok.Builder;

@Builder
public record VideoWrongSection (
        Integer start,
        Integer end,
        double accuracy
){
}
