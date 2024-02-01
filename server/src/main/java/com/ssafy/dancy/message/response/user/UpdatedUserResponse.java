package com.ssafy.dancy.message.response.user;

import lombok.Builder;

public record UpdatedUserResponse(
        String email,
        String nickname
) {

    @Builder
    public UpdatedUserResponse {

    }
}
