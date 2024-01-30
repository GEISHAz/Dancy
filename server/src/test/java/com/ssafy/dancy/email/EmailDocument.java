package com.ssafy.dancy.email;

import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Snippet;

import static com.ssafy.dancy.DocumentFormatProvider.required;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;

public class EmailDocument {

    public static final Snippet targetEmailRequestField = requestFields(
            fieldWithPath("targetEmail").type(JsonFieldType.STRING)
                    .attributes(required()).description("인증번호를 보내려는 이메일")
    );
}
