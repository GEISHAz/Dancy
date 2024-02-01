package com.ssafy.dancy.message.response.user;

import lombok.Builder;

public record ChangeIntroduceResponse(
        String email,
        String introduceText
) {

    @Builder
    public ChangeIntroduceResponse{

    }
}
