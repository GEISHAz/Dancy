package com.ssafy.dancy.message.request.email;

import jakarta.validation.constraints.Email;
import lombok.Builder;

public record VerifyEmailRequest(

        @Email
        String targetEmail,

        String verifyCode
) {

    @Builder
    public VerifyEmailRequest{

    }
}
