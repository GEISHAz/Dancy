package com.ssafy.dancy.message.request.auth;

import com.ssafy.dancy.message.annotation.user.Password;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public record ChangePasswordRequest(
        String currentPassword,
        @NotNull(message = "새로운 비밀번호를 입력해 주세요.")
        @Password
        String newPassword
) {

    @Builder
    public ChangePasswordRequest{

    }
}
