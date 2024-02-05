package com.ssafy.dancy.message.request.email;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public record VerifyEmailRequest(

        @NotNull(message = "targetEmail 을 입력해야 합니다.")
        @Email(message = "이메일 형식이 아닙니다.")

        String targetEmail,

        String verifyCode
) {

    @Builder
    public VerifyEmailRequest{

    }
}
