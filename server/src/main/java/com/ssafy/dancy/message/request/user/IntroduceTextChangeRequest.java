package com.ssafy.dancy.message.request.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

public record IntroduceTextChangeRequest(
        @NotNull(message = "소개 메세지를 입력해 주세요.")
        @Size(min = 1, max = 50, message = "소개 메세지는 1자 이상 50자 이하여야 합니다.")
        String introduceText
) {

    @Builder
    public IntroduceTextChangeRequest{

    }
}
