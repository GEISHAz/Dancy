package com.ssafy.dancy.auth;

import com.ssafy.dancy.ApiTest;
import com.ssafy.dancy.message.request.auth.LoginUserRequest;
import com.ssafy.dancy.message.request.user.SignUpRequest;
import com.ssafy.dancy.service.user.UserService;
import com.ssafy.dancy.type.Role;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Set;

import static io.restassured.RestAssured.given;

public class AuthApiTest extends ApiTest {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthSteps authSteps;

    private SignUpRequest signUpRequest;

    @BeforeEach
    void settings(){
        signUpRequest = authSteps.회원가입정보_생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
    }

    @Test
    void 로그인성공_200(){
        LoginUserRequest request = AuthSteps.로그인요청생성();

        ExtractableResponse<Response> response = given(this.spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/auth/login")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();
    }
}
