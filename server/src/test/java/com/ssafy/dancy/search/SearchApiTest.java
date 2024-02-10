package com.ssafy.dancy.search;

import com.ssafy.dancy.ApiTest;
import com.ssafy.dancy.CommonDocument;
import com.ssafy.dancy.article.ArticleDocument;
import com.ssafy.dancy.article.ArticleSteps;
import com.ssafy.dancy.auth.AuthSteps;
import com.ssafy.dancy.message.request.user.SignUpRequest;
import com.ssafy.dancy.repository.article.ArticleRepository;
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

public class SearchApiTest extends ApiTest {

    @Autowired
    private AuthSteps authSteps;
    @Autowired
    private ArticleSteps articleSteps;
    @Autowired
    private UserService userService;
    private SignUpRequest signUpRequest;
    private SignUpRequest otherSignUpRequest;
    private Long testLastId;
    private Long whatFirstId;

    @BeforeEach
    void settings(){
        signUpRequest = authSteps.회원가입정보_생성();
        otherSignUpRequest = authSteps.상대방회원가입정보_생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
        userService.signup(otherSignUpRequest, Set.of(Role.USER));

        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        게시물_작성(token, "include test");
        testLastId = 게시물_작성(token, "test title");

        String oppositeToken = authSteps.로그인액세스토큰정보(AuthSteps.상대방로그인_생성());
        whatFirstId = 게시물_작성(oppositeToken, "what title");
        게시물_작성(oppositeToken, "what is include?");
    }


    @Test
    void 제목_검색결과_불러오기_limit_이전게시글아이디_포함_200(){

        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        ExtractableResponse<Response> response = given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,
                        "제목으로 검색결과를 불러오는 API 입니다." +
                                "<br>현재 무한 스크롤을 지원하고 있으며, previousArticleId 를 query string 으로 입력했을 때" +
                                "<br>해당 article Id 를 기준으로 그보다 이전에 만들어진 검색 article 정보가 나옵니다." +
                                "<br>limit 정보 역시 query string 으로 입력해 주어야 하며, 몇 개의 게시글을 받아올지 지정하는 부분입니다." +
                                "<br>query string 에서 limit 정보는 필수값이며, previousArticleId 는 선택값입니다." +
                                "<br>성공적으로 검색 결과의 게시물 리스트가 반환되었을 때, 200 OK 를 확인할 수 있습니다." +
                                "<br>게시글 리스트가 마지막이었다면, 204 No Content 가 반환됩니다. " +
                                "<br>이때, 그냥 article 자체가 존재하지 않는 상황이더라도, 204 No Content 가 반환됩니다.",
                        "검색 결과(제목)",
                        CommonDocument.AccessTokenHeader,
                        ArticleDocument.keywordPathField,
                        ArticleDocument.stageRequestField, ArticleDocument.simpleArticleListResponseField))
                .header("AUTH-TOKEN", token)
                .param("limit", 10)
                .param("previousArticleId", testLastId)
                .pathParams("keyword", "test")
                .when()
                .get("/search/title/{keyword}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getList("").size()).isEqualTo(1);
    }

    @Test
    void 제목_검색결과_불러오기_마지막아이디_204(){

        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, CommonDocument.AccessTokenHeader,
                        ArticleDocument.keywordPathField, ArticleDocument.stageRequestField))
                .header("AUTH-TOKEN", token)
                .param("limit", 10)
                .param("previousArticleId", whatFirstId)
                .pathParams("keyword", "what")
                .when()
                .get("/search/title/{keyword}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NO_CONTENT.value())
                .log().all().extract();
    }



    @Test
    void 닉네임_검색결과_불러오기_성공_200(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        ExtractableResponse<Response> response = given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,
                        "작성자 닉네임으로 검색결과를 불러오는 API 입니다." +
                                "<br>현재 무한 스크롤을 지원하고 있으며, previousArticleId 를 query string 으로 입력했을 때" +
                                "<br>해당 article Id 를 기준으로 그보다 이전에 만들어진 검색 article 정보가 나옵니다." +
                                "<br>limit 정보 역시 query string 으로 입력해 주어야 하며, 몇 개의 게시글을 받아올지 지정하는 부분입니다." +
                                "<br>query string 에서 limit 정보는 필수값이며, previousArticleId 는 선택값입니다." +
                                "<br>성공적으로 검색 결과의 게시물 리스트가 반환되었을 때, 200 OK 를 확인할 수 있습니다." +
                                "<br>게시글 리스트가 마지막이었다면, 204 No Content 가 반환됩니다. " +
                                "<br>이때, 그냥 article 자체가 존재하지 않는 상황이더라도, 204 No Content 가 반환됩니다.",
                        "검색 결과(닉네임)",
                        CommonDocument.AccessTokenHeader,
                        ArticleDocument.keywordPathField,
                        ArticleDocument.stageRequestField, ArticleDocument.simpleArticleListResponseField))
                .header("AUTH-TOKEN", token)
                .param("limit", 10)
                .param("previousArticleId", testLastId)
                .pathParams("keyword", "do")
                .when()
                .get("/search/nickname/{keyword}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getList("").size()).isEqualTo(1);
    }

    @Test
    void 닉네임_검색결과_마지막_204(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, CommonDocument.AccessTokenHeader,
                        ArticleDocument.keywordPathField, ArticleDocument.stageRequestField))
                .header("AUTH-TOKEN", token)
                .param("limit", 10)
                .param("previousArticleId", whatFirstId)
                .pathParams("keyword", AuthSteps.opponentnickname)
                .when()
                .get("/search/nickname/{keyword}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NO_CONTENT.value())
                .log().all().extract();
    }

    Long 게시물_작성(String token, String title){
        ExtractableResponse<Response> response = given(this.spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .body(articleSteps.게시물_생성(title))
                .when()
                .post("/stage")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        JsonPath jsonPath = response.jsonPath();
        return jsonPath.getLong("articleId");
    }
}
