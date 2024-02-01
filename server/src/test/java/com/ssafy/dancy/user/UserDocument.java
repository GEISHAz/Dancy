package com.ssafy.dancy.user;

import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Snippet;

import static com.ssafy.dancy.DocumentFormatProvider.required;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
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

    public static final Snippet signUpResponseField = responseFields(
            fieldWithPath("email").type(JsonFieldType.STRING).description("가입한 이메일"),
            fieldWithPath("nickname").type(JsonFieldType.STRING).description("가입한 닉네임")
    );

    public static final Snippet nicknameField = pathParameters(
            parameterWithName("nickname").attributes(required()).description("닉네임")
    );
}
