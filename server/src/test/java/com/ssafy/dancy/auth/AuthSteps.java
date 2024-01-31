package com.ssafy.dancy.auth;

import com.ssafy.dancy.message.request.auth.LoginUserRequest;
import com.ssafy.dancy.message.request.user.SignUpRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthSteps {

    public static final String email = "ndw8200@naver.com";
    public static final String nickname = "dongw";
    public static final String password = "Test1122!";
    public static final String birthDate = "2000-01-01";
    static final String gender = "MALE";
    static final String authType = "DANCY";




    public SignUpRequest 회원가입정보_생성(){

        return SignUpRequest.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .gender(gender)
                .birthDate(birthDate)
                .authType(authType)
                .build();
    }

    public static LoginUserRequest 로그인요청생성(){
        return LoginUserRequest.builder()
                .email(email)
                .password(password)
                .build();
    }
}
