package com.ssafy.dancy.email;

import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Snippet;

import static com.ssafy.dancy.DocumentFormatProvider.required;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

public class EmailDocument {

    public static final Snippet targetEmailRequestField = requestFields(
            fieldWithPath("targetEmail").type(JsonFieldType.STRING)
                    .attributes(required()).description("인증번호를 보내려는 이메일")
    );

    public static final Snippet codeVerifyRequestField = requestFields(
            fieldWithPath("targetEmail").type(JsonFieldType.STRING)
                    .attributes(required()).description("인증번호를 보낸 이메일"),
            fieldWithPath("verifyCode").type(JsonFieldType.STRING)
                    .attributes(required()).description("들어온 인증번호")
    );

    public static final Snippet emailVerifyResponse = responseFields(
            fieldWithPath("targetEmail").type(JsonFieldType.STRING).description("인증 번호를 보낸 이메일"),
            fieldWithPath("verified").type(JsonFieldType.BOOLEAN).description("인증 확인")
    );
}
