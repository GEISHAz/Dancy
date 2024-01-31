package com.ssafy.dancy.auth;

import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Snippet;

import static com.ssafy.dancy.DocumentFormatProvider.required;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

public class AuthDocument {

    public static final Snippet LoginUserRequestField = requestFields(
            fieldWithPath("email").type(JsonFieldType.STRING).attributes(required()).description("아이디"),
            fieldWithPath("password").type(JsonFieldType.STRING).attributes(required()).description("비밀번호")
    );

    public static final Snippet JwtTokenResponseField = responseFields(
            fieldWithPath("accessToken").type(JsonFieldType.STRING).description("access token"),
            fieldWithPath("tokenType").type(JsonFieldType.STRING).description("토큰 타입"),
            fieldWithPath("authType").type(JsonFieldType.STRING).description("인증 타입")
    );
}
