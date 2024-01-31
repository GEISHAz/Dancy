package com.ssafy.dancy.message.response.auth;

import com.ssafy.dancy.type.AuthType;
import lombok.Builder;

public record JwtTokenResponse(String accessToken, String tokenType, AuthType authType) {
    @Builder
    public JwtTokenResponse{

    }
}
