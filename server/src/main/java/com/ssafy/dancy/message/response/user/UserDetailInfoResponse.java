package com.ssafy.dancy.message.response.user;

import lombok.Builder;

import java.util.Date;

public record UserDetailInfoResponse(

        String email,
        String nickname,
        String birthDate,
        String introduceText,
        String profileImageUrl
) {
    @Builder
    public UserDetailInfoResponse{

    }
}
