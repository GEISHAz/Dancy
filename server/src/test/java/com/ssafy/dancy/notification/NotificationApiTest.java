package com.ssafy.dancy.notification;

import com.ssafy.dancy.ApiTest;
import com.ssafy.dancy.CommonDocument;
import com.ssafy.dancy.article.ArticleSteps;
import com.ssafy.dancy.auth.AuthSteps;
import com.ssafy.dancy.comment.CommentSteps;
import com.ssafy.dancy.follow.FollowSteps;
import com.ssafy.dancy.message.request.user.SignUpRequest;
import com.ssafy.dancy.service.user.UserService;
import com.ssafy.dancy.type.Role;
import com.ssafy.dancy.video.VideoSteps;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Set;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static io.restassured.RestAssured.given;

public class NotificationApiTest extends ApiTest {

    @Autowired
    private AuthSteps authSteps;
    @Autowired
    private ArticleSteps articleSteps;
    @Autowired
    private UserService userService;
    @Autowired
    private VideoSteps videoSteps;
    @Autowired
    private FollowSteps followSteps;
    private SignUpRequest signUpRequest;
    private SignUpRequest otherSignUpRequest;

    private Long videoIdOne;

    @BeforeEach
    void settings(){
        signUpRequest = authSteps.회원가입정보_생성();
        otherSignUpRequest = authSteps.상대방회원가입정보_생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
        userService.signup(otherSignUpRequest, Set.of(Role.USER));

        videoIdOne = videoSteps.결과확인_사전작업(AuthSteps.email);
    }

    @Test
    void 알람_가져오기_200(){

        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        Long articleId = 게시물_작성(token, "게시물", videoIdOne);

        String otherToken = authSteps.로그인액세스토큰정보(AuthSteps.상대방로그인_생성());
        댓글_작성_진행(otherToken, articleId);
        게시글_좋아요_진행(otherToken, articleId);
        팔로우_진행(otherToken, AuthSteps.nickname);

        ExtractableResponse<Response> response = given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, "자신에게 온 알람을 확인할 수 있는 API 입니다." +
                                "<br>성공적으로 알람을 가져오면, (디폴트) 최신 10개의 알람을 받아볼 수 있습니다." +
                                "<br>query string 으로 limit 값을 넣어줄 수 있으며, 이것은 선택 사항입니다." +
                                "<br>로그인 토큰을 넣지 않으면, 401 Unauthorized 가 반환됩니다.",
                        "알람 가져오기",
                        CommonDocument.AccessTokenHeader,
                        NotificationDocument.alarmLimitRequestField,
                        NotificationDocument.notificationResultResponseField))
                .header("AUTH-TOKEN", token)
                .when()
                .get("/notification")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        JsonPath jsonPath = response.jsonPath();
        Assertions.assertThat(jsonPath.getList("").size()).isEqualTo(3);
    }

    @Test
    void 알람_가져오기_토큰없음_401(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        Long articleId = 게시물_작성(token, "게시물", videoIdOne);

        String otherToken = authSteps.로그인액세스토큰정보(AuthSteps.상대방로그인_생성());
        댓글_작성_진행(otherToken, articleId);
        게시글_좋아요_진행(otherToken, articleId);
        팔로우_진행(otherToken, AuthSteps.nickname);

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH))
                .when()
                .get("/notification")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .log().all().extract();
    }


    Long 게시물_작성(String token, String title, Long videoId){
        ExtractableResponse<Response> response = given(this.spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .body(articleSteps.게시물_생성(title, videoId))
                .when()
                .post("/stage")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        JsonPath jsonPath = response.jsonPath();
        return jsonPath.getLong("articleId");
    }

    Integer 댓글_작성_진행(String token, Long articleId){
        ExtractableResponse<Response> response = given(this.spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParam("articleId", articleId)
                .body(CommentSteps.댓글_정보_생성())
                .when()
                .post("/comment/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        JsonPath jsonPath = response.jsonPath();
        return jsonPath.getInt("commentId");
    }

    void 게시글_좋아요_진행(String token, Long articleId){
        given(this.spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParams("articleId", articleId)
                .when()
                .post("/like/article-like/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
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
