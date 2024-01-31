package com.ssafy.dancy.auth;

import com.ssafy.dancy.message.request.auth.LoginUserRequest;
import com.ssafy.dancy.message.request.user.SignUpRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    public static ExtractableResponse<Response> 로그인요청(LoginUserRequest request){
        return RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/auth/login")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();
    }

    public String 로그인액세스토큰정보(LoginUserRequest request){
        ExtractableResponse<Response> loginResponse = 로그인요청(request);
        return loginResponse.body().jsonPath().getString("accessToken");
    }

}
