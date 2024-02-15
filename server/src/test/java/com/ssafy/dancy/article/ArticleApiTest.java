package com.ssafy.dancy.article;

import com.ssafy.dancy.ApiTest;
import com.ssafy.dancy.CommonDocument;
import com.ssafy.dancy.auth.AuthSteps;
import com.ssafy.dancy.entity.Article;
import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.follow.FollowSteps;
import com.ssafy.dancy.message.request.user.SignUpRequest;
import com.ssafy.dancy.repository.ArticleSaveRepository;
import com.ssafy.dancy.repository.UserRepository;
import com.ssafy.dancy.repository.article.ArticleRepository;
import com.ssafy.dancy.service.user.UserService;
import com.ssafy.dancy.type.Role;
import com.ssafy.dancy.video.VideoSteps;
import groovy.util.logging.Slf4j;
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

@Slf4j
public class ArticleApiTest extends ApiTest {


    @Autowired
    private AuthSteps authSteps;
    @Autowired
    private ArticleSteps articleSteps;
    @Autowired
    private FollowSteps followSteps;
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleSaveRepository articleSaveRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VideoSteps videoSteps;
    private SignUpRequest signUpRequest;
    private SignUpRequest otherSignUpRequest;

    private Long videoIdOne;
    private Long videoIdTwo;

    @BeforeEach
    void settings(){
        signUpRequest = authSteps.회원가입정보_생성();
        otherSignUpRequest = authSteps.상대방회원가입정보_생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
        userService.signup(otherSignUpRequest, Set.of(Role.USER));

        videoIdOne = videoSteps.결과확인_사전작업(AuthSteps.email);
        videoIdTwo = videoSteps.결과확인_사전작업(AuthSteps.email);
    }

    @Test
    void 전체_게시물_조회_이전게시글아이디_존재_200(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        게시물_작성(token, videoIdOne);
        Long lastId = 게시물_작성(token, videoIdTwo);

        ExtractableResponse<Response> response = given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,
                        "stage 페이지에서 게시물을 조회하는 API 입니다." +
                                "<br>현재 무한 스크롤을 지원하고 있으며, previousArticleId 를 query string 으로 입력했을 때" +
                                "<br>해당 article Id 를 기준으로 그보다 이전에 만들어진 article 정보가 나옵니다." +
                                "<br>limit 정보 역시 query string 으로 입력해 주어야 하며, 몇 개의 게시글을 받아올지 지정하는 부분입니다." +
                                "<br>query string 에서 limit 정보는 필수값이며, previousArticleId 는 선택값입니다." +
                                "<br>성공적으로 게시물 리스트가 반환되었을 때, 200 OK가 반환됩니다." +
                                "<br>게시글 리스트가 마지막이었다면, 204 No Content 가 반환됩니다. " +
                                "<br>이때, 그냥 article 자체가 존재하지 않는 상황이더라도, 204 No Content 가 반환됩니다.",
                        "전체 게시물 조회",
                        ArticleDocument.stageRequestField, ArticleDocument.simpleArticleListResponseField))
                .header("AUTH-TOKEN", token)
                .param("limit", 10)
                .param("previousArticleId", lastId)
                .when()
                .get("/stage")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getList("").size()).isEqualTo(1);
    }


    @Test
    void 전체_게시물_조회_이전게시글아이디_없음_200(){

        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        게시물_작성(token, videoIdOne);
        게시물_작성(token, videoIdTwo);

        ExtractableResponse<Response> response = given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, ArticleDocument.simpleArticleListResponseField))
                .header("AUTH-TOKEN", token)
                .param("limit", 10)
                .when()
                .get("/stage")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getList("").size()).isEqualTo(2);
    }

    @Test
    void 전체_게시물_조회_리미트처리_성공_200(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        게시물_작성(token, videoIdOne);
        게시물_작성(token, videoIdTwo);

        ExtractableResponse<Response> response = given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, ArticleDocument.simpleArticleListResponseField))
                .header("AUTH-TOKEN", token)
                .param("limit", 1)
                .when()
                .get("/stage")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getList("").size()).isEqualTo(1);
    }

    @Test
    void 전체_게시물_조회_마지막게시글_처리_204(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        Long realLastId = 게시물_작성(token, videoIdOne);
        게시물_작성(token, videoIdTwo);

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH))
                .header("AUTH-TOKEN", token)
                .param("limit", 10)
                .param("previousArticleId", realLastId)
                .when()
                .get("/stage")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NO_CONTENT.value())
                .log().all().extract();
    }

    @Test
    void 게시글_상세_조회_성공_좋아요클릭(){

        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        Long articleId = 게시물_작성(token, videoIdOne);
        게시물_좋아요(token, articleId);

        ExtractableResponse<Response> response = given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, "게시글 상세 조회를 수행하는 API 입니다." +
                        "<br>존재하는 게시글을 조회 시도하는 경우, 200 OK 와 함께 해당 게시글의 상세정보를 반환받습니다. " +
                        "<br>이때, 글을 쓴 사람을 팔로우하는지, 혹은 그 글을 좋아요 클릭했는지 정보도 같이 반환됩니다." +
                        "<br>로그인 토큰을 입력하지 않고 조회했다면, 401 Unauthorized 가 반환됩니다." +
                        "<br>존재하지 않는 글을 조회하면, 404 Not Found 가 반환됩니다.","게시글상세조회",
                        CommonDocument.AccessTokenHeader,
                        ArticleDocument.articleIdPathField,
                        ArticleDocument.articleDetailResponseField
                ))
                .header("AUTH-TOKEN", token)
                .pathParams("articleId", articleId)
                .when()
                .get("/stage/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getBoolean("isArticleLiked")).isTrue();
        assertThat(jsonPath.getBoolean("isAuthorFollowed")).isFalse();

        Article article = articleRepository.findByArticleId(articleId).get();
        assertThat(article.getView()).isEqualTo(1);
    }

    @Test
    void 게시글_상세_조회_해당유저팔로우(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        Long articleId = 게시물_작성(token, videoIdOne);

        String opponentToken = authSteps.로그인액세스토큰정보(AuthSteps.상대방로그인_생성());
        팔로우_진행(opponentToken, AuthSteps.nickname);

        ExtractableResponse<Response> response = given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, CommonDocument.AccessTokenHeader,
                        ArticleDocument.articleIdPathField, ArticleDocument.articleDetailResponseField))
                .header("AUTH-TOKEN", opponentToken)
                .pathParams("articleId", articleId)
                .when()
                .get("/stage/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getBoolean("isArticleLiked")).isFalse();
        assertThat(jsonPath.getBoolean("isAuthorFollowed")).isTrue();
    }

    @Test
    void 게시글_상세_조회_둘다해당(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        Long articleId = 게시물_작성(token, videoIdOne);

        String opponentToken = authSteps.로그인액세스토큰정보(AuthSteps.상대방로그인_생성());

        게시물_좋아요(opponentToken, articleId);
        팔로우_진행(opponentToken, AuthSteps.nickname);
        게시글_저장_진행(opponentToken, articleId);

        ExtractableResponse<Response> response = given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, CommonDocument.AccessTokenHeader,
                        ArticleDocument.articleIdPathField, ArticleDocument.articleDetailResponseField))
                .header("AUTH-TOKEN", opponentToken)
                .pathParams("articleId", articleId)
                .when()
                .get("/stage/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getBoolean("isArticleLiked")).isTrue();
        assertThat(jsonPath.getBoolean("isAuthorFollowed")).isTrue();
    }

    @Test
    void 게시글_상세_조회_좋아요_팔로우_아님(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        Long articleId = 게시물_작성(token, videoIdOne);

        ExtractableResponse<Response> response = given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, CommonDocument.AccessTokenHeader,
                        ArticleDocument.articleIdPathField, ArticleDocument.articleDetailResponseField))
                .header("AUTH-TOKEN", token)
                .pathParams("articleId", articleId)
                .when()
                .get("/stage/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getBoolean("isArticleLiked")).isFalse();
        assertThat(jsonPath.getBoolean("isAuthorFollowed")).isFalse();
    }

    @Test
    void 게시글_상세_조회_토큰없음_401(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        Long articleId = 게시물_작성(token, videoIdOne);

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH))
                .pathParams("articleId", articleId)
                .when()
                .get("/stage/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .log().all().extract();
    }

    @Test
    void 게시글_상세_조회_게시글없음_404(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, CommonDocument.AccessTokenHeader,
                        ArticleDocument.articleIdPathField, CommonDocument.ErrorResponseFields))
                .header("AUTH-TOKEN", token)
                .pathParams("articleId", 12345)
                .when()
                .get("/stage/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .log().all().extract();
    }

    @Test
    void 게시물_작성_성공_200(){

        final String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        Long videoId = videoSteps.결과확인_사전작업(AuthSteps.email);


        ExtractableResponse<Response> response = given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,
                        "게시물을 작성하는 API입니다." +
                        "<br>성공적으로 작성되면 200 OK와 함께 게시글 ID, 제목, 내용, 썸네일 등이 반환됩니다." +
                        "<br>게시물 제목을 40자 이하로 작성하지 않거나, 공백으로 둘 경우, 그리고 게시물 내용이 공백일 경우" +
                        "<br>또는 videoId 가 없을 경우 400 Bad Request 가 생성됩니다." +
                        "<br>헤더에 로그인 토큰을 입력하지 않을 경우, 401 Unauthorized 가 반환됩니다." +
                        "<br>해시대크 부분은 추가 구현 예정입니다. 참고해 주세요.",
                        "게시물작성",
                        CommonDocument.AccessTokenHeader,
                        ArticleDocument.ArticleWriteRequestField, ArticleDocument.ArticleWriteResponseField))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .body(articleSteps.게시물_생성(videoId))
                .when()
                .post("/stage")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        Long articleId = response.jsonPath().getLong("articleId");
        assertThat(articleRepository.findByArticleId(articleId)).isPresent();
    }

    Long 게시물_작성(String token, Long videoId){

        ExtractableResponse<Response> response = given(this.spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .body(articleSteps.게시물_생성(videoId))
                .when()
                .post("/stage")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        JsonPath jsonPath = response.jsonPath();
        return jsonPath.getLong("articleId");
    }

    void 게시물_좋아요(String token, Long articleId){
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

    @Test
    void 게시물_작성_검증위반_400(){

        final String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, CommonDocument.AccessTokenHeader,
                        ArticleDocument.ArticleWriteRequestField, CommonDocument.ErrorResponseFields))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .body(articleSteps.게시물_생성_타이틀공백(videoIdOne))
                .when()
                .post("/stage")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .log().all().extract();
    }

    @Test
    void 게시물_작성_토큰없음_401(){
        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(articleSteps.게시물_생성_타이틀공백(videoIdOne))
                .when()
                .post("/stage")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .log().all().extract();
    }

    @Test
    void 게시물_수정(){

        final String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        Long articleId = 게시물_생성(token, videoIdOne);

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,
                        "게시물을 수정하는 API입니다." +
                        "<br>성공적으로 수정되면 200 OK와 함께 수정 후 게시글 ID, 제목, 내용, 썸네일 등이 반환됩니다." +
                        "<br>게시물 제목을 40자 이하로 작성하지 않거나, 공백으로 둘 경우, 그리고 게시물 내용이 공백일 경우" +
                        "<br>400 Bad Request 가 생성됩니다." +
                        "<br>로그인 토큰을 입력하지 않으면, 401 Unauthorized 가 반환됩니다." +
                        "<br>로그인되어 있는 사람이 작성하지 않은 게시물을 수정하고자 하는 경우, 403 Forbidden 이 반환됩니다." +
                        "<br>해당 글을 찾을 수 없을 경우, 404 Not Found 가 반환됩니다.",
                        "게시물 수정",
                        CommonDocument.AccessTokenHeader,
                        ArticleDocument.ArticleModifyRequestField, ArticleDocument.ArticleWriteResponseField))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParam("articleId", articleId)
                .body(articleSteps.게시물_수정())
                .when()
                .put("/stage/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();


        Article article = articleRepository.findByArticleId(articleId).get();
        assertThat(article.getArticleTitle()).isEqualTo(ArticleSteps.modifiedTestTitle);
        assertThat(article.getArticleContent()).isEqualTo(ArticleSteps.modifiedTestContent);
    }

    @Test
    void 게시물_수정_검증위반_400(){

        final String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        Long articleId = 게시물_생성(token, videoIdOne);

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,
                        CommonDocument.AccessTokenHeader,
                        ArticleDocument.ArticleModifyRequestField, CommonDocument.ErrorResponseFields))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParam("articleId", articleId)
                .body(articleSteps.게시물_수정_타이틀공백())
                .when()
                .put("/stage/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .log().all().extract();
    }

    @Test
    void 게시물_수정_토큰없음_401(){

        final String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        Long articleId = 게시물_생성(token, videoIdOne);

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("articleId", articleId)
                .body(articleSteps.게시물_수정())
                .when()
                .put("/stage/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .log().all().extract();
    }

    @Test
    void 게시물_수정_권한없음_403(){

        final String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        Long articleId = 게시물_생성(token , videoIdOne);

        final String otherToken = authSteps.로그인액세스토큰정보(AuthSteps.상대방로그인_생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,
                        CommonDocument.AccessTokenHeader,
                        ArticleDocument.ArticleModifyRequestField, CommonDocument.ErrorResponseFields))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", otherToken)
                .pathParam("articleId", articleId)
                .body(articleSteps.게시물_수정())
                .when()
                .put("/stage/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .log().all().extract();

    }

    @Test
    void 게시물_수정_게시물없음_404(){

        final String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,
                        CommonDocument.AccessTokenHeader,
                        ArticleDocument.ArticleModifyRequestField, CommonDocument.ErrorResponseFields))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParam("articleId", 123)
                .body(articleSteps.게시물_수정())
                .when()
                .put("/stage/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .log().all().extract();
    }

    @Test
    void 게시물_삭제(){

        final String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        Long articleId = 게시물_생성(token, videoIdOne);

        given(this.spec)
                 .filter(document(DEFAULT_RESTDOC_PATH,
                        "게시물을 삭제하는 API입니다." +
                        "<br>성공적으로 삭제되면 200 OK와 함께 삭제된 게시글 ID가 반환됩니다." +
                        "<br>로그인 토큰을 입력하지 않는다면 401 Unauthorized 가 반환됩니다." +
                        "<br>로그인한 유저가 그 글을 삭제할 권한을 가지고 있지 않다면, 403 Forbidden 이 반환됩니다." +
                        "<br>삭제하고자 하는 글을 찾을 수 없다면, 404 Not Found 가 반환됩니다.",
                        "게시물 삭제",
                        CommonDocument.AccessTokenHeader,
                        ArticleDocument.articleIdPathField))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParam("articleId", articleId)
                .when()
                // URL 쏘는 자리. get, post, put, patch, delete
                .delete("/stage/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        assertThat(articleRepository.findByArticleId(articleId)).isNotPresent();
    }

    @Test
    void 게시물_삭제_토큰없음_401(){

        final String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        Long articleId = 게시물_생성(token, videoIdOne);

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("articleId", articleId)
                .when()
                // URL 쏘는 자리. get, post, put, patch, delete
                .delete("/stage/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .log().all().extract();
    }

    @Test
    void 게시물_삭제_권한없음_403(){

        final String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        Long articleId = 게시물_생성(token, videoIdOne);
        final String otherToken = authSteps.로그인액세스토큰정보(AuthSteps.상대방로그인_생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", otherToken)
                .pathParam("articleId", articleId)
                .when()
                .delete("/stage/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .log().all().extract();

    }

    @Test
    void 게시물_삭제_게시물없음_404(){

        final String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParam("articleId", 123)
                .when()
                .delete("/stage/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .log().all().extract();
    }

    @Test
    void 게시물_저장_성공_200(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        Long articleId = 게시물_생성(token, videoIdOne);

        String otherToken = authSteps.로그인액세스토큰정보(AuthSteps.상대방로그인_생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, "게시물을 저장하는 API 입니다. " +
                        "<br>성공적으로 게시물을 저장했을 때, 200 OK 와 함께 저장 내역을 반환받습니다. " +
                        "<br>로그인 토큰을 입력받지 않고 저장 시도를 하면, 401 Unauthorized 를 반환받습니다. " +
                        "<br>존재하지 않는 articleId 를 저장 시도하면, 404 Not Found 가 반환됩니다.",
                        "게시물저장",
                        CommonDocument.AccessTokenHeader,
                        ArticleDocument.articleIdPathField,
                        ArticleDocument.articleSaveResponseField
                        ))
                .header("AUTH-TOKEN", otherToken)
                .pathParams("articleId", articleId)
                .when()
                .post("/stage/save/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        User user = userRepository.findByEmail(AuthSteps.opponentemail).get();
        assertThat(articleSaveRepository.findByArticle_ArticleIdAndUser(articleId, user)).isPresent();
    }

    @Test
    void 게시물_저장_취소_성공_200(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        Long articleId = 게시물_생성(token, videoIdOne);

        String otherToken = authSteps.로그인액세스토큰정보(AuthSteps.상대방로그인_생성());
        게시글_저장_진행(otherToken, articleId);

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, CommonDocument.AccessTokenHeader,
                        ArticleDocument.articleIdPathField,
                        ArticleDocument.articleSaveResponseField
                ))
                .header("AUTH-TOKEN", otherToken)
                .pathParams("articleId", articleId)
                .when()
                .post("/stage/save/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        User user = userRepository.findByEmail(AuthSteps.opponentemail).get();
        assertThat(articleSaveRepository.findByArticle_ArticleIdAndUser(articleId, user)).isEmpty();
    }

    @Test
    void 게시물_저장_토큰없음_401(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        Long articleId = 게시물_생성(token, videoIdOne);

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH))
                .pathParams("articleId", articleId)
                .when()
                .post("/stage/save/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .log().all().extract();
    }

    @Test
    void 게시물_저장_해당게시물없음_404(){
        String otherToken = authSteps.로그인액세스토큰정보(AuthSteps.상대방로그인_생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,
                        CommonDocument.AccessTokenHeader,
                        ArticleDocument.articleIdPathField,
                        CommonDocument.ErrorResponseFields
                ))
                .header("AUTH-TOKEN", otherToken)
                .pathParams("articleId", 12345)
                .when()
                .post("/stage/save/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .log().all().extract();
    }

    Long 게시물_생성(String token, Long videoId){
        ExtractableResponse<Response> response = given(this.spec)

                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .body(articleSteps.게시물_생성(videoId))
                .when()
                .post("/stage")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        return response.jsonPath().getLong("articleId");
    }

    void 팔로우_진행(String token, String nickname){
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

    void 게시글_저장_진행(String token, Long articleId){
        given(this.spec)
                .header("AUTH-TOKEN", token)
                .pathParams("articleId", articleId)
                .when()
                .post("/stage/save/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();
    }
}
