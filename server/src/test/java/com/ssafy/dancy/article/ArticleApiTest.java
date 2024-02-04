package com.ssafy.dancy.article;

import com.ssafy.dancy.ApiTest;
import com.ssafy.dancy.auth.AuthSteps;
import com.ssafy.dancy.entity.Article;
import com.ssafy.dancy.message.request.user.SignUpRequest;
import com.ssafy.dancy.repository.ArticleRepository;
import com.ssafy.dancy.service.user.UserService;
import com.ssafy.dancy.type.Role;
import groovy.util.logging.Slf4j;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Set;

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



    @Test
    void 전체_게시물_조회(){

        signUpRequest = authSteps.회원가입정보_생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
        final String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
//                .filter(document(DEFAULT_RESTDOC_PATH, UserDocument.nicknamePathField, CommonDocument.ErrorResponseFields))
                /**
                 * stage페이지에서 쓰는 전체 게시물을 조회하는 API입니다.
                 * 성공적으로 게시물 리스트가 반환되었을 때, 200 OK가 반환됩니다.
                 * 게시물 관련 API는 모두 헤더에 AUTH-TOKEN을 넣어줘야 합니다.
                 * 그렇지 않으면 401 Unauthorized 가 반환됩니다.
                 */
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
    void 게시물_작성(){

        signUpRequest = authSteps.회원가입정보_생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
        final String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        ExtractableResponse<Response> response = given(this.spec)
                /**
                 * 게시물을 작성하는 API입니다.
                 * 성공적으로 작성되면 200 OK와 함께 게시글 ID, 제목, 내용, 썸네일 등이 반환됩니다.
                 * 작성에 실패했다면 400이 반환됩니다.
                 */
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
    void 게시물_수정(){

        signUpRequest = authSteps.회원가입정보_생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
        final String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        ExtractableResponse<Response> response = given(this.spec)
                /**
                 * 게시물을 수정하는 API입니다.
                 * 성공적으로 수정되면 200 OK와 함께 수정 후 게시글 ID, 제목, 내용, 썸네일 등이 반환됩니다.
                 * 게시물을 찾을 수 없거나, 수정권한이 없는 등 수정에 실패했다면 400이 반환됩니다.
                 */
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .body(articleSteps.게시물_생성())
                .when()
                // URL 쏘는 자리. get, post, put, patch, delete
                .post("/stage")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        Long articleId = response.jsonPath().getLong("articleId");
        given(this.spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParam("articleId", articleId)
                .body(articleSteps.게시물_수정())
                .when()
                // URL 쏘는 자리. get, post, put, patch, delete
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
    void 게시물_삭제(){

        signUpRequest = authSteps.회원가입정보_생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
        final String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        ExtractableResponse<Response> response = given(this.spec)
                /**
                 * 게시물을 삭제하는 API입니다.
                 * 성공적으로 삭제되면 200 OK와 함께 삭제된 게시글 ID가 반환됩니다.
                 * 게시물을 찾을 수 없거나, 삭제권한이 없는 등 수정에 실패했다면 400이 반환됩니다.
                 */
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .body(articleSteps.게시물_생성())
                .when()
                // URL 쏘는 자리. get, post, put, patch, delete
                .post("/stage")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        Long articleId = response.jsonPath().getLong("articleId");
        given(this.spec)
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


}
