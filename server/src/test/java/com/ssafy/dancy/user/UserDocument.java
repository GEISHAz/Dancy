package com.ssafy.dancy.user;

import org.mockito.exceptions.verification.SmartNullPointerException;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Snippet;

import static com.ssafy.dancy.DocumentFormatProvider.required;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public class UserDocument {
    public static final Snippet signUpRequestField = requestParts(
            partWithName("email").attributes(required())
                    .description("가입하려는 이메일입니다. 이메일 형식이어야 합니다."),
            partWithName("nickname").attributes(required())
                    .description("가입하려는 닉네임입니다. 1자 이상 15자 미만이어야 합니다."),
            partWithName("password").attributes(required())
                    .description("비밀번호 입력입니다. 비밀번호는 8자리 이상, 영문, 숫자, 특수문자 조합이어야 합니다."),
            partWithName("birthDate").attributes(required())
                    .description("생년월일입니다. 입력 날짜는 현재 날짜보다 이전 날짜여야 하며 yyyy-MM-dd 형식이어야 합니다."),
            partWithName("gender").attributes(required())
                    .description("성별입니다. MALE/FEMALE 중 하나로만 입력해야 합니다."),
            partWithName("authType").attributes(required())
                    .description("인증 타입입니다. KAKAO, NAVER, GOOGLE, DANCY 를 제외한 인증 타입은 존재하지 않습니다."),
            partWithName("profileImage").description("프로필에 등록하는 이미지 파일들입니다. 한 장 등록할 수 있습니다.")
    );

    public static final Snippet nicknamePathField = pathParameters(
            parameterWithName("nickname").attributes(required()).description("닉네임")
    );

    public static final Snippet nicknameBodyField = requestFields(
            fieldWithPath("nickname").type(JsonFieldType.STRING)
                    .attributes(required()).description("변경하고자 하는 닉네임")
    );

    public static final Snippet introduceTextField = requestFields(
            fieldWithPath("introduceText").type(JsonFieldType.STRING)
                    .attributes(required()).description("바꾸고자 하는 소개 메세지")
    );

    public static final Snippet changePasswordRequestField = requestFields(
            fieldWithPath("currentPassword").type(JsonFieldType.STRING)
                    .attributes(required()).description("현재 패스워드"),
            fieldWithPath("newPassword").type(JsonFieldType.STRING)
                    .attributes(required()).description("바꾸고자 하는 패스워드")
    );

    public static final Snippet userDeleteRequestField = requestFields(
            fieldWithPath("password").type(JsonFieldType.STRING)
                    .attributes(required()).description("현재 비밀번호")
    );

    public static final Snippet changeProfileImageRequestField = requestParts(
            partWithName("profileImage").description("프로필에 등록하는 이미지 파일들입니다. 한 장 등록할 수 있습니다.")
    );

    public static final Snippet updateUserResponseField = responseFields(
            fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
            fieldWithPath("nickname").type(JsonFieldType.STRING).description("닉네임")
    );

    public static final Snippet updateIntroduceResponseField = responseFields(
            fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
            fieldWithPath("introduceText").type(JsonFieldType.STRING).description("닉네임")
    );

    public static final Snippet userDetailInfoResponseField = responseFields(
            fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
            fieldWithPath("nickname").type(JsonFieldType.STRING).description("닉네임"),
            fieldWithPath("birthDate").type(JsonFieldType.STRING).description("생년월일"),
            fieldWithPath("introduceText").type(JsonFieldType.STRING).description("소개메세지"),
            fieldWithPath("profileImageUrl").type(JsonFieldType.VARIES).description("프로필이미지URL")
    );

    public static final Snippet changeProfileImageResponseField = responseFields(
            fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
            fieldWithPath("profileImageUrl").type(JsonFieldType.STRING).description("프로필이미지URL")
    );

}

