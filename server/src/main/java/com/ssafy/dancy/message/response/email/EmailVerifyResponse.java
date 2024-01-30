package com.ssafy.dancy.message.response.email;

import lombok.Builder;

public record EmailVerifyResponse(
        String targetEmail,
        boolean verified
) {

    @Builder
    public EmailVerifyResponse{

    }
}
