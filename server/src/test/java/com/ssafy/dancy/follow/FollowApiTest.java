package com.ssafy.dancy.follow;

import com.ssafy.dancy.ApiTest;
import com.ssafy.dancy.CommonDocument;
import com.ssafy.dancy.auth.AuthSteps;
import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.message.request.user.SignUpRequest;
import com.ssafy.dancy.repository.follow.FollowRepository;
import com.ssafy.dancy.repository.UserRepository;
import com.ssafy.dancy.service.user.UserService;
import com.ssafy.dancy.type.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Set;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


public class FollowApiTest extends ApiTest {

    @Autowired
    private FollowSteps followSteps;
    @Autowired
    private AuthSteps authSteps;
    @Autowired
    private UserService userService;
    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private UserRepository userRepository;

    private SignUpRequest signUpRequest;
    private SignUpRequest opponentSignUpRequest;
    private SignUpRequest otherSignupRequest;

    @BeforeEach
    void settings(){
        signUpRequest = authSteps.회원가입정보_생성();
        opponentSignUpRequest = authSteps.상대방회원가입정보_생성();
//        otherSignupRequest = authSteps.삼자회원가입정보_생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
        userService.signup(opponentSignUpRequest, Set.of(Role.USER));
//        userService.signup(otherSignupRequest, Set.of(Role.USER));
    }

    @Test
    void 팔로잉_조회_성공_200(){

        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        팔로우_진행(token, opponentSignUpRequest.nickname());
//        팔로우_진행(token, otherSignupRequest.nickname());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, "특정 계정의 팔로잉 정보를 조회하는 API 입니다." +
                        "<br>존재하는 닉네임을 입력했을 경우, 200 OK 와 함께 그 계정의 팔로잉한 유저 정보를 조회할 수 있습니다." +
                                "<br>존재하지 않는 계정 닉네임을 입력했을 경우 ,404 Not Found 와 함께 에러 메세지를 반환받습니다.",
                        "팔로잉 정보 조회",
                        FollowDocument.nicknamePathField, FollowDocument.followInfoListResponseField))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParam("nickname", signUpRequest.nickname())
                .when()
                .get("/follow/get-followings/{nickname}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();
    }

    @Test
    void 팔로잉_조회_닉네임없음_404(){
        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,
                        FollowDocument.nicknamePathField, CommonDocument.ErrorResponseFields))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("nickname", "no_where")
                .when()
                .get("/follow/get-followings/{nickname}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .log().all().extract();
    }

    @Test
    void 팔로우_조회(){

        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        팔로우_진행(token, opponentSignUpRequest.nickname());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, "특정 계정의 팔로워 정보를 조회하는 API 입니다." +
                                "<br>존재하는 닉네임을 입력했을 경우, 200 OK 와 함께 그 계정을 팔로우한 유저 정보를 조회할 수 있습니다." +
                                "<br>존재하지 않는 계정 닉네임을 입력했을 경우 ,404 Not Found 와 함께 에러 메세지를 반환받습니다.",
                        "팔로우 정보 조회",
                        FollowDocument.nicknamePathField, FollowDocument.followInfoListResponseField))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParam("nickname", opponentSignUpRequest.nickname())
                .when()
                .get("/follow/get-followers/{nickname}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();
    }

    @Test
    void 팔로우_조회_닉네임없음_404(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        팔로우_진행(token, opponentSignUpRequest.nickname());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,
                        FollowDocument.nicknamePathField, CommonDocument.ErrorResponseFields))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParam("nickname", "no_where")
                .when()
                .get("/follow/get-followers/{nickname}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .log().all().extract();
    }

    @Test
    void 팔로우_성공_200(){

        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, "팔로우를 진행하는 API입니다." +
                                "<br>성공적으로 수정되면 200 OK와 함께 수정 후 게시글 ID, 제목, 내용, 썸네일 등이 반환됩니다." +
                                "<br>게시물 제목을 40자 이하로 작성하지 않거나, 공백으로 둘 경우, 그리고 게시물 내용이 공백일 경우" +
                                "<br>400 Bad Request 가 생성됩니다." +
                                "<br>로그인 토큰을 입력하지 않으면, 401 Unauthorized 가 반환됩니다." +
                                "<br>로그인되어 있는 사람이 작성하지 않은 게시물을 수정하고자 하는 경우, 403 Forbidden 이 반환됩니다." +
                                "<br>해당 글을 찾을 수 없을 경우, 404 Not Found 가 반환됩니다.",
                        "팔로우 진행",
                        CommonDocument.AccessTokenHeader,
                        FollowDocument.followRequestField, FollowDocument.followResultResponseField))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .body(followSteps.팔로우_정보_생성(opponentSignUpRequest.nickname()))
                .when()
                .post("/follow/request-follow")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        User opponentUser = userRepository.findByNickname(opponentSignUpRequest.nickname()).get();
        User fromUser = userRepository.findByEmail(signUpRequest.email()).get();

        assertThat(opponentUser.getFollowerCount()).isEqualTo(1);
        assertThat(fromUser.getFollowingCount()).isEqualTo(1);
    }

    @Test
    void 팔로우_토큰없음_401(){
        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(followSteps.팔로우_정보_생성(opponentSignUpRequest.nickname()))
                .when()
                .post("/follow/request-follow")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .log().all().extract();
    }

    @Test
    void 팔로우_상대방_닉네임없음_404(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,
                        CommonDocument.AccessTokenHeader,
                        FollowDocument.followRequestField, CommonDocument.ErrorResponseFields))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .body(followSteps.팔로우_정보_생성("notregistered"))
                .when()
                .post("/follow/request-follow")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .log().all().extract();
    }

    @Test
    void 언팔로우(){

        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        팔로우_진행(token, opponentSignUpRequest.nickname());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, "언팔로우를 진행하는 API 입니다." +
                        "<br>언팔로우를 성공적으로 진행한다면, 200 OK 와 함께 언팔로우한 정보를 받습니다." +
                        "<br>로그인 토큰을 입력하지 않는다면 401 Unauthorized 가 반환됩니다." +
                        "<br>기존에 팔로우한 정보가 없다면 404 Not Found 가 반환됩니다.",
                        "언팔로우 진행",
                        CommonDocument.AccessTokenHeader, FollowDocument.followRequestField,
                        FollowDocument.followResultResponseField))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .body(followSteps.팔로우_정보_생성(opponentSignUpRequest.nickname()))
                .when()
                .delete("/follow/request-unfollow")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        User opponentUser = userRepository.findByNickname(opponentSignUpRequest.nickname()).get();
        User fromUser = userRepository.findByEmail(signUpRequest.email()).get();

        assertThat(opponentUser.getFollowerCount()).isEqualTo(0);
        assertThat(fromUser.getFollowingCount()).isEqualTo(0);
    }

    @Test
    void 언팔로우_토큰없음_401(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        팔로우_진행(token, opponentSignUpRequest.nickname());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(followSteps.팔로우_정보_생성(opponentSignUpRequest.nickname()))
                .when()
                .delete("/follow/request-unfollow")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .log().all().extract();
    }

    @Test
    void 언팔로우_팔로우정보없음_404(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,
                        CommonDocument.AccessTokenHeader,
                        FollowDocument.followRequestField,
                        CommonDocument.ErrorResponseFields))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .body(followSteps.팔로우_정보_생성(opponentSignUpRequest.nickname()))
                .when()
                .delete("/follow/request-unfollow")
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
