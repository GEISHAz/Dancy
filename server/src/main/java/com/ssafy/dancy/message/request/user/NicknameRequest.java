package com.ssafy.dancy.message.request.user;

import com.ssafy.dancy.message.annotation.user.Nickname;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public record NicknameRequest(
        @NotNull(message = "닉네임을 입력해 주세요.")
        @Nickname
        String nickname
) {

    @Builder
    public NicknameRequest{

    }
}
