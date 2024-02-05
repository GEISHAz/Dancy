package com.ssafy.dancy.follow;

import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Snippet;

import static com.ssafy.dancy.DocumentFormatProvider.required;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

public class FollowDocument {

    public static final Snippet followRequestField = requestFields(
            fieldWithPath("nickname").type(JsonFieldType.STRING).attributes(required()).description("팔로우 요청할 닉네임")
    );

    public static final Snippet nicknamePathField = pathParameters(
            parameterWithName("nickname").attributes(required()).description("닉네임")
    );

    public static final Snippet followResultResponseField = responseFields(
            fieldWithPath("followInfoId").type(JsonFieldType.NUMBER).description("팔로우 정보 ID"),
            fieldWithPath("followerNickname").type(JsonFieldType.STRING).description("팔로우한 사람의 닉네임"),
            fieldWithPath("followedNickname").type(JsonFieldType.STRING).description("팔로우받은 사람의 닉네임")
    );

    public static final Snippet followInfoListResponseField = responseFields(
            fieldWithPath("[].nickname").type(JsonFieldType.STRING).description("팔로우 정보 ID"),
            fieldWithPath("[].profileImageUrl").type(JsonFieldType.VARIES).description("팔로우한 사람의 닉네임")
    );
}
