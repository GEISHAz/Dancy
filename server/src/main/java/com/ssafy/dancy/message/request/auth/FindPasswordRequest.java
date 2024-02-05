package com.ssafy.dancy.message.request.auth;

import com.ssafy.dancy.message.annotation.user.Password;
import lombok.Builder;

public record FindPasswordRequest(
        @Password
        String newPassword
) {
        @Builder
        public FindPasswordRequest{

        }
}
