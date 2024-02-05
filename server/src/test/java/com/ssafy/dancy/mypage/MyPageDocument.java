package com.ssafy.dancy.mypage;


import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Snippet;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

public class MyPageDocument {

    public static final Snippet followResultResponseField = responseFields(
            fieldWithPath("nickname").type(JsonFieldType.STRING).description("닉네임"),
            fieldWithPath("introduceText").type(JsonFieldType.STRING).description("소개 메세지"),
            fieldWithPath("profileImageUrl").type(JsonFieldType.VARIES).description("프로필 이미지 URL"),
            fieldWithPath("following").type(JsonFieldType.NUMBER).description("팔로잉 수"),
            fieldWithPath("follower").type(JsonFieldType.NUMBER).description("팔로워 수"),
            fieldWithPath("isMine").type(JsonFieldType.BOOLEAN).description("본인의 마이페이지인지 여부"),
            fieldWithPath("followed").type(JsonFieldType.BOOLEAN).description("본인이 팔로우했는지 여부")
    );
}
