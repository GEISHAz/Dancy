package com.ssafy.dancy.video;

import com.ssafy.dancy.ApiTest;
import com.ssafy.dancy.auth.AuthSteps;
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

public class VideoApiTest extends ApiTest {

    @Autowired
    private AuthSteps authSteps;
    @Autowired
    private UserService userService;

    private SignUpRequest signUpRequest;
    @BeforeEach
    void settings(){
        signUpRequest = authSteps.회원가입정보_생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
    }

    @Test
    void 비디오_테스트(){

        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .header("AUTH-TOKEN", token)
                .multiPart(VideoSteps.레퍼런스_비디오_생성())
                .when()
                .post("/video/upload/reference")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();
    }

    @Test
    void 연습_비디오_테스트(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        Long referenceVideoId = 레퍼런스_비디오_업로드(token);

        given(this.spec)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .header("AUTH-TOKEN", token)
                .multiPart("referenceVideoId", referenceVideoId)
                .multiPart(VideoSteps.연습_비디오_생성())
                .when()
                .post("/video/upload/practice")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();
    }

    Long 레퍼런스_비디오_업로드(String token){
        ExtractableResponse<Response> response = given(this.spec)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .header("AUTH-TOKEN", token)
                .multiPart(VideoSteps.레퍼런스_비디오_생성())
                .when()
                .post("/video/upload/reference")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        return response.jsonPath().getLong("videoId");
    }
}
