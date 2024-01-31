package com.ssafy.dancy.message.response.user;

import lombok.Builder;

public record SignUpResultResponse(
        String email,
        String nickname
) {

    @Builder
    public SignUpResultResponse{

    }
}
