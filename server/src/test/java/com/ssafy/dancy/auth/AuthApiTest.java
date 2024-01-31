package com.ssafy.dancy.auth;

import com.ssafy.dancy.ApiTest;
import com.ssafy.dancy.CommonDocument;
import com.ssafy.dancy.message.request.auth.LoginUserRequest;
import com.ssafy.dancy.message.request.user.SignUpRequest;
import com.ssafy.dancy.service.user.UserService;
import com.ssafy.dancy.type.Role;
import io.restassured.matcher.RestAssuredMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Set;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;

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

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, "로그인을 진행하는 API 입니다." +
                                "<br>로그인에 성공한다면, 200 OK 와 함께 Access Token 과 Refresh Token, 인증타입, 토큰타입 정보가 반환됩니다." +
                                "<br>아이디나 비밀번호가 일치하지 않는다면, 404 Not Found 가 반환됩니다.",
                        "로그인",
                        AuthDocument.LoginUserRequestField, AuthDocument.JwtTokenResponseField))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/auth/login")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .cookie("refreshToken", notNullValue())
                .cookie("refreshToken", RestAssuredMatchers.detailedCookie().httpOnly(true))
                .body("accessToken", notNullValue())
                .log().all().extract();

        Mockito.verify(mockValueOp, times(1)).set(anyString(), anyString(), anyLong(), any());
    }

    @Test
    void 로그아웃_refreshToken_cookie_제거_200(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, "로그아웃을 진행하는 API 입니다." +
                                "<br>로그아웃에 성공한다면, 200 OK 와 함께 Refresh Token 을 가지고 있는 Cookie 가 삭제됩니다." +
                                "<br>프론트앤드 영역에서 Access Token 을 직접 삭제시켜 주어야 합니다." +
                                "<br>AUTH-TOKEN 을 입력하지 않았을 때, 401 Unauthorized 가 반환됩니다.",
                        "로그아웃",
                        CommonDocument.AccessTokenHeader))
                .header("AUTH-TOKEN", token)
                .when()
                .post("/auth/logout")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .cookie("refreshToken", RestAssuredMatchers.detailedCookie().maxAge(0))
                .log().all().extract();

        Mockito.verify(redisTemplate, times(1)).delete(any());
        Mockito.verify(mockValueOp, times(1)).set(anyString(), anyString(), anyLong(), any());
    }
}
