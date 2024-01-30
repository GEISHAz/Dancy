package com.ssafy.dancy;

import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Snippet;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

public class CommonDocument {
    public static final Snippet ErrorResponseFields = responseFields(
            fieldWithPath("[].message").type(JsonFieldType.STRING).description("에러메세지"),
            fieldWithPath("[].errorType").type(JsonFieldType.STRING).description("에러타입"),
            fieldWithPath("[].fieldName").type(JsonFieldType.STRING).description("에러난 필드이름")
    );

}
