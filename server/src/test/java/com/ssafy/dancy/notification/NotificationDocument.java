package com.ssafy.dancy.notification;

import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Snippet;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;

public class NotificationDocument {

    public static final Snippet alarmLimitRequestField = queryParameters(
            parameterWithName("limit").description("몇 개 받을지 정하는 수").optional()
    );

    public static final Snippet notificationResultResponseField =  responseFields(
            fieldWithPath("[].notificationId").type(JsonFieldType.NUMBER).description("알람 고유 ID"),
            fieldWithPath("[].content").type(JsonFieldType.STRING).description("알람 내용"),
            fieldWithPath("[].makerUserId").type(JsonFieldType.NUMBER).description("알람 보낸 사람 고유 ID"),
            fieldWithPath("[].makerUserProfileImageUrl").type(JsonFieldType.VARIES).description("프로필 이미지 URL"),
            fieldWithPath("[].makerUserNickname").type(JsonFieldType.STRING).description("알람 보낸 사람 닉네임"),
            fieldWithPath("[].createdTime").type(JsonFieldType.ARRAY).description("알람 생성일시"),
            fieldWithPath("[].articleId").type(JsonFieldType.VARIES).description("관련 게시글 고유 ID").optional()
    );
}
