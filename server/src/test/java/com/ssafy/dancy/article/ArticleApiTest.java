package com.ssafy.dancy.article;

import com.ssafy.dancy.ApiTest;
import com.ssafy.dancy.CommonDocument;
import com.ssafy.dancy.auth.AuthSteps;
import com.ssafy.dancy.entity.Article;
import com.ssafy.dancy.message.request.user.SignUpRequest;
import com.ssafy.dancy.repository.ArticleRepository;
import com.ssafy.dancy.service.user.UserService;
import com.ssafy.dancy.type.Role;
import com.ssafy.dancy.user.UserDocument;
import groovy.util.logging.Slf4j;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
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
    private UserService userService;
    @Autowired
    private ArticleRepository articleRepository;
    private SignUpRequest signUpRequest;
    private SignUpRequest otherSignUpRequest;



    @Test
    void 전체_게시물_조회(){

        signUpRequest = authSteps.회원가입정보_생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
        final String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
//                .filter(document(DEFAULT_RESTDOC_PATH,
//                        "<br>stage 페이지에서 쓰는 전체 게시물을 조회하는 API 입니다." +
//                        "<br>성공적으로 게시물 리스트가 반환되었을 때, 200 OK가 반환됩니다." +
//                        "<br>게시물 관련 API는 모두 헤더에 AUTH-TOKEN을 넣어줘야 합니다." +
//                        "<br>그렇지 않으면 401 Unauthorized 가 반환됩니다.", "전체 게시물 조회",
//
//
//
//
//                        CommonDocument.AccessTokenHeader, CommonDocument.ErrorResponseFields))
//                .filter(document(DEFAULT_RESTDOC_PATH, UserDocument.nicknamePathField, CommonDocument.ErrorResponseFields))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .when()
                .get("/stage")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

    }


    @Test
    void 게시물_작성_성공_200(){

        signUpRequest = authSteps.회원가입정보_생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
        final String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        ExtractableResponse<Response> response = given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,
                        "게시물을 작성하는 API입니다." +
                        "<br>성공적으로 작성되면 200 OK와 함께 게시글 ID, 제목, 내용, 썸네일 등이 반환됩니다." +
                        "<br>게시물 제목을 40자 이하로 작성하지 않거나, 공백으로 둘 경우, 그리고 게시물 내용이 공백일 경우" +
                        "<br>400 Bad Request 가 생성됩니다." +
                        "<br>헤더에 로그인 토큰을 입력하지 않을 경우, 401 Unauthorized 가 반환됩니다." +
                        "<br>해시대크 부분은 추가 구현 예정입니다. 참고해 주세요.",
                        "게시물작성",
                        CommonDocument.AccessTokenHeader,
                        ArticleDocument.ArticleWriteRequestField, ArticleDocument.ArticleWriteResponseField))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .body(articleSteps.게시물_생성())
                .when()
                .post("/stage")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        Long articleId = response.jsonPath().getLong("articleId");
        assertThat(articleRepository.findByArticleId(articleId)).isPresent();
    }

    @Test
    void 게시물_작성_검증위반_400(){

        signUpRequest = authSteps.회원가입정보_생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
        final String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, CommonDocument.AccessTokenHeader,
                        ArticleDocument.ArticleWriteRequestField, CommonDocument.ErrorResponseFields))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .body(articleSteps.게시물_생성_타이틀공백())
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
                .body(articleSteps.게시물_생성_타이틀공백())
                .when()
                .post("/stage")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .log().all().extract();
    }

    @Test
    void 게시물_수정(){

        signUpRequest = authSteps.회원가입정보_생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
        final String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        Long articleId = 게시물_생성(token);

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
                        ArticleDocument.ArticleWriteRequestField, ArticleDocument.ArticleWriteResponseField))
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
        signUpRequest = authSteps.회원가입정보_생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
        final String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        Long articleId = 게시물_생성(token);

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,
                        CommonDocument.AccessTokenHeader,
                        ArticleDocument.ArticleWriteRequestField, CommonDocument.ErrorResponseFields))
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
        signUpRequest = authSteps.회원가입정보_생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
        final String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        Long articleId = 게시물_생성(token);

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
        signUpRequest = authSteps.회원가입정보_생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
        final String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        Long articleId = 게시물_생성(token);

        otherSignUpRequest = authSteps.상대방회원가입정보_생성();
        userService.signup(otherSignUpRequest, Set.of(Role.USER));
        final String otherToken = authSteps.로그인액세스토큰정보(AuthSteps.상대방로그인_생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,
                        CommonDocument.AccessTokenHeader,
                        ArticleDocument.ArticleWriteRequestField, CommonDocument.ErrorResponseFields))
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
        signUpRequest = authSteps.회원가입정보_생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
        final String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,
                        CommonDocument.AccessTokenHeader,
                        ArticleDocument.ArticleWriteRequestField, CommonDocument.ErrorResponseFields))
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

        signUpRequest = authSteps.회원가입정보_생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
        final String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        Long articleId = 게시물_생성(token);

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

        signUpRequest = authSteps.회원가입정보_생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
        final String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        Long articleId = 게시물_생성(token);

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
        signUpRequest = authSteps.회원가입정보_생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
        final String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        Long articleId = 게시물_생성(token);

        otherSignUpRequest = authSteps.상대방회원가입정보_생성();
        userService.signup(otherSignUpRequest, Set.of(Role.USER));
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
        signUpRequest = authSteps.회원가입정보_생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
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

    Long 게시물_생성(String token){
        ExtractableResponse<Response> response = given(this.spec)

                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .body(articleSteps.게시물_생성())
                .when()
                .post("/stage")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        return response.jsonPath().getLong("articleId");
    }

}
