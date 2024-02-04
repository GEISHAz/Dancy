package com.ssafy.dancy.follow;

import com.ssafy.dancy.ApiTest;
import com.ssafy.dancy.auth.AuthSteps;
import com.ssafy.dancy.message.request.user.SignUpRequest;
import com.ssafy.dancy.service.user.UserService;
import com.ssafy.dancy.type.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Set;

import static io.restassured.RestAssured.given;

public class FollowApiTest extends ApiTest {

    @Autowired
    private AuthSteps authSteps;
    @Autowired
    private UserService userService;
    private SignUpRequest signUpRequest;
    private SignUpRequest signUpRequest2;

    @BeforeEach
    void settings(){
        signUpRequest = authSteps.회원가입정보_생성();
        signUpRequest2 = authSteps.회원가입정보_상대방정보생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
        userService.signup(signUpRequest2, Set.of(Role.USER));
    }

    @Test
    void 팔로잉_조회(){

        팔로우();

        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParam("nickname", signUpRequest.nickname())
        .when()
                // URL 쏘는 자리. get, post, put, patch, delete
                .get("/follow/get-followings")
        .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();
    }

    @Test
    void 팔로우_조회(){

        팔로우();

        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParam("nickname", signUpRequest.nickname())
                .when()
                // URL 쏘는 자리. get, post, put, patch, delete
                .get("/follow/get-followers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();
    }

    @Test
    void 팔로우(){

        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .body(signUpRequest2.nickname())
                .when()
                // URL 쏘는 자리. get, post, put, patch, delete
                .post("/follow/request-follow")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();
    }


    @Test
    void 언팔로우(){

        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .body(signUpRequest2.nickname())
                .when()
                // URL 쏘는 자리. get, post, put, patch, delete
                .delete("/follow/request-unfollow")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();
    }


}
