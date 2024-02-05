package com.ssafy.dancy.mypage;

import com.ssafy.dancy.ApiTest;
import com.ssafy.dancy.CommonDocument;
import com.ssafy.dancy.auth.AuthSteps;
import com.ssafy.dancy.follow.FollowDocument;
import com.ssafy.dancy.follow.FollowSteps;
import com.ssafy.dancy.message.request.user.SignUpRequest;
import com.ssafy.dancy.service.user.UserService;
import com.ssafy.dancy.type.Role;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Set;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class MyPageApiTest extends ApiTest {

    @Autowired
    private AuthSteps authSteps;
    @Autowired
    private FollowSteps followSteps;
    @Autowired
    private UserService userService;
    private SignUpRequest signUpRequest;
    private SignUpRequest opponentSignUpRequest;

    @BeforeEach
    void settings(){
        signUpRequest = authSteps.회원가입정보_생성();
        opponentSignUpRequest = authSteps.상대방회원가입정보_생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
        userService.signup(opponentSignUpRequest, Set.of(Role.USER));
    }

    @Test
    void 마이페이지_팔로우시_유저정보조회(){

        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        팔로우_진행(token, opponentSignUpRequest.nickname());

        ExtractableResponse<Response> response = given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, "마이페이지 정보를 조회하는 API 입니다." +
                                "<br>여기서 말하는 마이페이지란, 본인 스스로의 상세정보 조회가 아닌 SNS 기능으로의 마이페이지 조회입니다." +
                                "<br>존재하는 사람의 path variable 로 입력 시, 200 OK 와 함께 SNS 상에서의 상대방 정보와" +
                                "<br>나와의 관계 (ex: 내가 팔로우했는지, 내 개인 계정인지) 여부가 반환됩니다." +
                                "<br>로그인 토큰 미입력 시, 401 Unauthorized 가 반환됩니다." +
                                "<br>존재하지 않는 사용자 닉네임을 입력했을 때, 404 Not Found 가 반환됩니다.",
                        "SNS 마이페이지",
                        CommonDocument.AccessTokenHeader,
                        FollowDocument.nicknamePathField, MyPageDocument.followResultResponseField))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParam("nickname", opponentSignUpRequest.nickname())
                .when()
                .get("/mypage/{nickname}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getBoolean("followed")).isTrue();
    }

    @Test
    void 마이페이지_팔로우아닐시_유저정보조회(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        ExtractableResponse<Response> response = given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, CommonDocument.AccessTokenHeader,
                        FollowDocument.nicknamePathField, MyPageDocument.followResultResponseField))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParam("nickname", opponentSignUpRequest.nickname())
                .when()
                .get("/mypage/{nickname}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getBoolean("followed")).isFalse();
    }

    @Test
    void 마이페이지_나자신일때_유저정보조회(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        ExtractableResponse<Response> response = given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, CommonDocument.AccessTokenHeader,
                        FollowDocument.nicknamePathField, MyPageDocument.followResultResponseField))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParam("nickname", signUpRequest.nickname())
                .when()
                .get("/mypage/{nickname}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getBoolean("isMine")).isTrue();
    }

    @Test
    void 마이페이지_토큰없음_401(){
        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("nickname", signUpRequest.nickname())
                .when()
                .get("/mypage/{nickname}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .log().all().extract();
    }

    @Test
    void 마이페이지_없는사람_조회_404(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParam("nickname", "no_where")
                .when()
                .get("/mypage/{nickname}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .log().all().extract();
    }

    void 팔로우_진행(String token, String nickname) {

        given(this.spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .body(followSteps.팔로우_정보_생성(nickname))
                .when()
                .post("/follow/request-follow")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();
    }
}
