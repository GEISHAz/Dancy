package com.ssafy.dancy.message.request.email;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public record SendEmailRequest(
        @NotBlank(message = "이메일을 입력해 주세요.")
        @Email(message = "이메일 형식이 아닙니다.")
        String targetEmail
) {
    @Builder
    public SendEmailRequest {

    }
}
