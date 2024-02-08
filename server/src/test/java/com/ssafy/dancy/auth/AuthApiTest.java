package com.ssafy.dancy.auth;

import com.ssafy.dancy.ApiTest;
import com.ssafy.dancy.CommonDocument;
import com.ssafy.dancy.email.EmailDocument;
import com.ssafy.dancy.email.EmailSteps;
import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.message.request.auth.LoginUserRequest;
import com.ssafy.dancy.message.request.user.SignUpRequest;
import com.ssafy.dancy.repository.UserRepository;
import com.ssafy.dancy.service.user.UserService;
import com.ssafy.dancy.type.Role;
import com.ssafy.dancy.user.UserDocument;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;

public class AuthApiTest extends ApiTest {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthSteps authSteps;
    @Autowired
    private EmailSteps emailSteps;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

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
    void 로그인_정보불일치_404(){

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,
                        AuthDocument.LoginUserRequestField, CommonDocument.ErrorResponseFields))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(AuthSteps.로그인요청_잘못된정보생성())
                .when()
                .post("/auth/login")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .log().all().extract();
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

        Mockito.verify(redisTemplate, times(1)).delete(anyString());
        Mockito.verify(mockValueOp, times(2)).set(anyString(), anyString(), anyLong(), any());
        // 로그인 할 때 한번 저장, 로그아웃할 때 블랙리스트 한번 저장
    }

    @Test
    void 로그아웃_토큰없음_401(){
        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH))
                .when()
                .post("/auth/logout")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .log().all().extract();
    }


    @Test
    void 비밀번호변경_성공_200(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, "비밀번호를 변경하는 API 입니다." +
                                "<br>비밀번호 변경에 성공한다면, 200 OK 와 함께 로그아웃 처리됩니다." +
                                "<br>프론트앤드 영역에서 Access Token 을 직접 삭제시켜 주어야 합니다." +
                                "<br>바꾸고자 하는 비밀번호가 정규표현식을 만족하지 못하는 경우, 400 Bad Request 와 함께 에러 메세지가 반환됩니다." +
                                "<br>AUTH-TOKEN 이 유효하지 않거나, 값이 없을 경우 401 Unauthorized 가 반환됩니다." +
                                "<br>A기존 패스워드와 일치하지 않을 경우, 403 Forbidden 과 함께 패스워드가 일치하지 않는다는 메세지가 반환됩니다.",
                        "비밀번호 변경",
                        CommonDocument.AccessTokenHeader, UserDocument.changePasswordRequestField))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .body(authSteps.비밀번호_변경_요청생성())
                .when()
                .put("/auth/change")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .cookie("refreshToken", RestAssuredMatchers.detailedCookie().maxAge(0))
                .log().all().extract();

        User user = userRepository.findByEmail(AuthSteps.email).get();
        assertThat(passwordEncoder.matches(AuthSteps.newPassword, user.getPassword())).isTrue();
    }

    @Test
    void 비밀번호변경_정규표현식_불만족_400(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,
                        UserDocument.changePasswordRequestField, CommonDocument.ErrorResponseFields))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .body(authSteps.비밀번호_변경_조건불만족())
                .when()
                .put("/auth/change")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .log().all().extract();
    }

    @Test
    void 비밀번호변경_토큰없음_401(){
        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(authSteps.비밀번호_변경_요청생성())
                .when()
                .put("/auth/change")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .log().all().extract();
    }

    @Test
    void 비밀번호변경_비밀번호불일치_403(){

        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,
                        UserDocument.changePasswordRequestField, CommonDocument.ErrorResponseFields))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .body(authSteps.비밀번호_변경_비밀번호틀림())
                .when()
                .put("/auth/change")
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .log().all().extract();
    }

    @Test
    void 회원탈퇴_성공_200(){

        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, "회원 탈퇴 API 입니다." +
                                "<br>올바른 비밀번호를 입력했을 때, 200 OK 와 함께 회원 탈퇴 처리되며, 로그아웃됩니다." +
                                "<br>프론트앤드 영역에서 Access Token 을 직접 삭제시켜 주어야 합니다." +
                                "<br>AUTH-TOKEN 이 유효하지 않거나, 값이 없을 경우 401 Unauthorized 가 반환됩니다." +
                                "<br>기존 패스워드와 일치하지 않을 경우, 403 Forbidden 과 함께 패스워드가 일치하지 않는다는 메세지가 반환됩니다.",
                        "회원 탈퇴",
                        CommonDocument.AccessTokenHeader, UserDocument.userDeleteRequestField))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .body(authSteps.회원탈퇴_유저_비밀번호_입력())
                .when()
                .put("/auth/delete")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .cookie("refreshToken", RestAssuredMatchers.detailedCookie().maxAge(0))
                .log().all().extract();

        assertThat(userRepository.findByEmail(AuthSteps.email)).isEmpty();
        Mockito.verify(redisTemplate, times(1)).delete(anyString());
        Mockito.verify(mockValueOp, times(2)).set(anyString(), anyString(), anyLong(), any());
        // 로그인 할때 set 한번, 회원탈퇴 시 로그아웃 때 한번
    }

    @Test
    void 회원탈퇴_토큰없음_401(){
        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(authSteps.회원탈퇴_유저_비밀번호_입력())
                .when()
                .delete("/auth")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .log().all().extract();
    }

    @Test
    void 회원탈퇴_비밀번호불일치_403(){

        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,
                        UserDocument.userDeleteRequestField,
                        CommonDocument.ErrorResponseFields))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .body(authSteps.회원탈퇴_유저_잘못된_비밀번호())
                .when()
                .put("/auth/delete")
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .log().all().extract();
    }

    @Test
    void 비밀번호_찾기_인증번호_검증_성공_200(){

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, "비밀번호 찾기에서의 이메일 인증번호 검증 API 입니다.." +
                                "<br>지정된 코드와 일치하는 코드를 입력하는 경우, 200 OK 와 함께 AUTH-TOKEN 을 반환합니다." +
                                "<br>로그인 처리되는 것은 아니지만, 해당 JWT 코드를 저장해 이후 비밀번호 찾기를 진행할 수 있습니다." +
                                "<br>서버 내에서는, “비밀번호 찾기 기능이 인가되었다” 라는 정보가 저장됩니다. " +
                                "<br>해당 정보는 1시간 동안 유효하며, 1시간 이내로 비밀번호 찾기 기능에서의 변경을 수행하지 않으면 해당 프로세스를 재실행해야 합니다." +
                                "<br>이메일에 동봉된 인증코드와 입력한 인증코드가 일치하지 않을 경우, " +
                                "<br>403 Forbidden 과 함께 인증 코드가 일치하지 않는다는 메세지가 반환됩니다." +
                                "<br>저장된 인증코드가 존재하지 않을 경우, 404 Not Found 가 반환됩니다." +
                                "<br>인증번호 입력을 5번 틀린 사용자가, 해당 시스템을 이용하려고 할 때 406 Not Acceptable 과 함께 아래 메세지를 입력받습니다." +
                                "<br>소셜 로그인 계정일 경우, 409 Conflict 가 반환됩니다." +
                                "<br>현재, 404, 406, 409 Code 가 나가는 케이스의 경우, 테스트하기 어려운 케이스이기 때문에" +
                                "<br>노션에 직접 해당 테스트 검증 결과를 추후 남겨 두겠습니다.",
                        "비밀번호 찾기 인증번호 검증",
                        EmailDocument.codeVerifyRequestField, AuthDocument.JwtTokenResponseField))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(emailSteps.인증번호_정보_생성("123456"))
                .when()
                .post("/auth/password/check")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();
    }

    @Test
    void 비밀번호_찾기_인증번호_불일치_403(){
        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, EmailDocument.codeVerifyRequestField
                ,CommonDocument.ErrorResponseFields))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(emailSteps.인증번호_정보_생성("654321"))
                .when()
                .post("/auth/password/check")
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .log().all().extract();
    }

    String 비밀번호_찾기_토큰가져오기(){
        ExtractableResponse<Response> response = given(this.spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(emailSteps.인증번호_정보_생성("123456"))
                .when()
                .post("/auth/password/check")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        JsonPath jsonPath = response.jsonPath();
        return jsonPath.getString("accessToken");
    }


    @Test
    void 비밀번호_찾기_비밀번호_변경_성공_200(){

        String token = 비밀번호_찾기_토큰가져오기();

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, "비밀번호 찾기에서의 최종 비밀번호 변경을 진행하는 API 입니다." +
                                "<br>비밀번호 찾기에서의 이메일 프로세스를 모두 통과하고, 받아온 토큰과 함께 비밀번호를 최종 변경할 경우," +
                                "<br>200 OK 와 함께 최종적으로 비밀번호가 변경되게 됩니다." +
                                "<br>최종적으로 다시 로그인되는 것은 아니며, 이전에 받았던 Token 을 삭제하는 작업이 클라이언트 단에서 필요합니다." +
                                "<br>이전의 프로세스 진행 후 1시간 동안 비밀번호를 바꾸지 않았다면, 다시 해당 프로세스 전체를 진행해야 합니다." +
                                "<br>비밀번호는 8자리 이상, 영문, 숫자, 특수문자 조합이어야 하며, 이를 위반하는 경우 400 Bad Request 가 반환됩니다." +
                                "<br>AUTH-TOKEN 이 유효하지 않거나 값이 없을 경우, 401 Unauthorized 가 반환됩니다." +
                                "<br>비밀번호 찾기 인증 시스템에서 정상적으로 해당 기능에 대한 허가를 받지 않은 사용자가 해당 API 를 이용할 경우, 403 Forbidden 이 반환됩니다." +
                                "<br>소셜 로그인 계정일 경우, 409 Conflict 가 반환됩니다." +
                                "<br>현재, 403, 409 Code 가 나가는 케이스의 경우, 테스트하기 어려운 케이스이기 때문에" +
                                "<br>테스트를 수동으로 진행했으며, 409 의 경우 소셜 로그인을 구현 후 진행합니다.",
                        "비밀번호 찾기에서의 비밀번호 변경",
                        CommonDocument.AccessTokenHeader,
                        AuthDocument.passwordFindChangeRequestField))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .body(authSteps.비밀번호_찾기_새비밀번호_생성())
                .when()
                .post("/auth/password/find")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        User user = userRepository.findByEmail(AuthSteps.email).get();
        passwordEncoder.matches(AuthSteps.newPassword, user.getPassword());
    }

    @Test
    void 비밀번호_찾기_비밀번호_변경_패스워드타입아님_400(){
        String token = 비밀번호_찾기_토큰가져오기();

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, CommonDocument.AccessTokenHeader,
                        AuthDocument.passwordFindChangeRequestField, CommonDocument.ErrorResponseFields))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .body(authSteps.비밀번호_찾기_비밀번호형식_미준수())
                .when()
                .post("/auth/password/find")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .log().all().extract();
    }

    @Test
    void 비밀번호_찾기_비밀번호_변경_토큰없음_401(){
        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(authSteps.비밀번호_찾기_새비밀번호_생성())
                .when()
                .post("/auth/password/find")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .log().all().extract();
    }
}
