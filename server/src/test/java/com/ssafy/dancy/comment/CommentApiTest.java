package com.ssafy.dancy.comment;

import com.ssafy.dancy.ApiTest;
import com.ssafy.dancy.CommonDocument;
import com.ssafy.dancy.auth.AuthSteps;
import com.ssafy.dancy.entity.Article;
import com.ssafy.dancy.entity.Comment;
import com.ssafy.dancy.message.request.user.SignUpRequest;
import com.ssafy.dancy.message.response.comment.CommentResponse;
import com.ssafy.dancy.repository.article.ArticleRepository;
import com.ssafy.dancy.repository.CommentRepository;
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

import java.util.List;
import java.util.Set;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class CommentApiTest extends ApiTest {

    @Autowired
    private AuthSteps authSteps;
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentRepository commentRepository;

    private SignUpRequest signUpRequest;
    private SignUpRequest otherSignupRequest;
    private Article savedArticle;

    @BeforeEach
    void settings(){
        signUpRequest = authSteps.회원가입정보_생성();
        otherSignupRequest = authSteps.상대방회원가입정보_생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
        userService.signup(otherSignupRequest, Set.of(Role.USER));


        Article article = Article.builder()
                .articleTitle("fuck")
                .articleContent("asedf")
                .build();

        savedArticle = articleRepository.save(article);
    }


    @Test
    void 댓글_작성_성공_200(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, "댓글을 작성하는 API 입니다." +
                        "<br>댓글 작성이 성공하면 200 OK 와 함께 작성된 댓글의 정보를 입력받습니다." +
                        "<br>댓글은 200 자 까지 작성 가능합니다. 댓글에 공란을 입력하거나, 200 자를 초과하는 경우, 400 Bad Request 가 반환됩니다." +
                        "<br>부모 댓글 아이디는 필수값입니다. 부모 댓글이 존재하지 않을 경우, 0을 입력해야 하며 " +
                        "<br>로그인 토큰을 헤더에 입력하지 않고 작성 시도를 하는 경우, 401 Unauthorized 가 반환됩니다." +
                        "<br>댓글을 작성하려는 글이 존재하지 않을 경우, 404 Not Found 가 반환됩니다.", "댓글 작성",
                        CommonDocument.AccessTokenHeader,
                        CommentDocument.commentWriteRequestField,
                        CommentDocument.commentInfoResponseField))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParam("articleId", savedArticle.getArticleId())
                .body(CommentSteps.댓글_정보_생성())
                .when()
                // URL 쏘는 자리. get, post, put, patch, delete
                .post("/comment/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();
    }

    @Test
    void 댓글_작성_조건위반_400(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, CommonDocument.AccessTokenHeader,
                        CommentDocument.commentWriteRequestField, CommonDocument.ErrorResponseFields))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParam("articleId", savedArticle.getArticleId())
                .body(CommentSteps.댓글_정보_부모아이디_음수())
                .when()
                // URL 쏘는 자리. get, post, put, patch, delete
                .post("/comment/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .log().all().extract();

    }

    @Test
    void 댓글_작성_토큰없음_401(){
        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("articleId", savedArticle.getArticleId())
                .body(CommentSteps.댓글_정보_생성())
                .when()
                // URL 쏘는 자리. get, post, put, patch, delete
                .post("/comment/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .log().all().extract();
    }

    @Test
    void 댓글_작성_해당게시물없음_404(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, CommonDocument.AccessTokenHeader,
                        CommentDocument.commentWriteRequestField, CommonDocument.ErrorResponseFields))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParam("articleId", 1234567)
                .body(CommentSteps.댓글_정보_생성())
                .when()
                // URL 쏘는 자리. get, post, put, patch, delete
                .post("/comment/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .log().all().extract();
    }

    @Test
    void 댓글_조회(){

        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        댓글_작성_진행(token);

        ExtractableResponse<Response> response = given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, "댓글을 조회하는 API 입니다." +
                        "<br>articleId 를 path variable 로 입력하면, 200 OK 와 함께 댓글 목록이 반환됩니다." +
                        "<br>해당 게시글의 본 댓글을 보고자 한다면, parentId 를 query string 형태로 입력해 줄 필요가 없습니다." +
                        "<br>이때, 대댓글이 아닌 본 댓글만 반환됩니다." +
                        "<br>특정 댓글의 대댓글을 확인하고자 한다면, query string 으로 parentId 정보를 입력해 주어야 합니다." +
                        "<br>존재하지 않는 articleId 이더라도, 댓글 란이 빈 칸으로 존재합니다.", "댓글조회",
                        CommentDocument.articleIdPathField,
                        CommentDocument.parentIdQueryField,
                        CommentDocument.commentInfoListResponseField))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("articleId", savedArticle.getArticleId())
                .param("parentId", 0)
                .when()
                .get("/comment/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        List<Comment> commentList = commentRepository.findCommentByArticle_ArticleIdAndParentId(
                savedArticle.getArticleId(), 0L);
        JsonPath jsonPath = response.jsonPath();

        List<CommentResponse> responseList = jsonPath.getList("", CommentResponse.class);
        assertThat(responseList.size()).isEqualTo(commentList.size());
    }

    @Test
    void 댓글_수정_성공_200(){

        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        Integer commentId = 댓글_작성_진행(token);

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, "댓글 수정을 진행하는 API 입니다." +
                        "<br>성공적으로 댓글 수정이 진행되면, 200 OK 와 함께 수정된 댓글 정보를 입력받습니다." +
                        "<br>수정 댓글을 공란이나 200 자가 넘게 된다면, 400 Bad Request 가 반환됩니다." +
                        "<br>로그인 토큰을 입력하지 않고 댓글 수정을 진행하면, 401 Unauthorized 가 반환됩니다." +
                        "<br>로그인한 유저가 작성하지 않은 댓글을 수정하고자 한다면, 403 Forbidden 이 반환됩니다." +
                        "<br>존재하지 않는 댓글을 수정하고자 시도한다면, 404 Not Found 가 반환됩니다.", "댓글수정",
                        CommonDocument.AccessTokenHeader,
                        CommentDocument.commentModifyRequestField, CommentDocument.commentInfoResponseField))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParam("commentId", commentId)
                .body(CommentSteps.댓글_수정_생성())
                .when()
                .put("/comment/{commentId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();
    }

    @Test
    void 댓글_수정_검증위반_400(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        Integer commentId = 댓글_작성_진행(token);

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, CommonDocument.AccessTokenHeader,
                        CommentDocument.commentModifyRequestField, CommonDocument.ErrorResponseFields))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParam("commentId", commentId)
                .body(CommentSteps.댓글_수정_공란_생성())
                .when()
                .put("/comment/{commentId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .log().all().extract();
    }

    @Test
    void 댓글_수정_토큰없음_401(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        Integer commentId = 댓글_작성_진행(token);

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("commentId", commentId)
                .body(CommentSteps.댓글_수정_공란_생성())
                .when()
                .put("/comment/{commentId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .log().all().extract();
    }

    @Test
    void 댓글_수정_권한없음_403(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        Integer commentId = 댓글_작성_진행(token);

        String otherToken = authSteps.로그인액세스토큰정보(AuthSteps.상대방로그인_생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, CommonDocument.AccessTokenHeader,
                        CommentDocument.commentModifyRequestField, CommonDocument.ErrorResponseFields))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", otherToken)
                .pathParam("commentId", commentId)
                .body(CommentSteps.댓글_수정_생성())
                .when()
                .put("/comment/{commentId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .log().all().extract();
    }

    @Test
    void 댓글_수정_해당댓글없음_404(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, CommonDocument.AccessTokenHeader,
                        CommentDocument.commentModifyRequestField, CommonDocument.ErrorResponseFields))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParam("commentId", 1234567)
                .body(CommentSteps.댓글_수정_생성())
                .when()
                .put("/comment/{commentId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .log().all().extract();
    }

    @Test
    void 댓글_삭제_성공_200(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        Integer commentId = 댓글_작성_진행(token);

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, "댓글 삭제를 진행하는 API 입니다." +
                        "<br>정상적으로 삭제가 진행되었다면, 200 OK 와 함께 삭제된 댓글 정보가 반환됩니다." +
                        "<br>commentId 를 -1 처리하여, 삭제되었다고 표시하고 있습니다. " +
                        "<br>로그인 토큰을 입력하지 않고 댓글 삭제를 시도할 경우, 401 Unauthorized 가 반환됩니다." +
                        "<br>로그인한 유저가 작성한 댓글이 아닌 댓글을 삭제 시도할 경우, 403 Forbidden 이 반환됩니다." +
                        "<br>존재하지 않는 댓글을 삭제 시도할 경우, 404 Not Found 가 반환됩니다.", "댓글 삭제",
                        CommonDocument.AccessTokenHeader, CommentDocument.commentIdPathField,
                        CommentDocument.commentInfoResponseField))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParam("commentId", commentId)
                .when()
                .delete("/comment/{commentId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();
    }

    @Test
    void 댓글_삭제_토큰없음_401(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        Integer commentId = 댓글_작성_진행(token);

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("commentId", commentId)
                .when()
                .delete("/comment/{commentId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .log().all().extract();
    }

    @Test
    void 댓글_삭제_권한없음_403(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        Integer commentId = 댓글_작성_진행(token);

        String otherToken = authSteps.로그인액세스토큰정보(AuthSteps.상대방로그인_생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, CommonDocument.AccessTokenHeader,
                        CommentDocument.commentIdPathField, CommonDocument.ErrorResponseFields))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", otherToken)
                .pathParam("commentId", commentId)
                .when()
                .delete("/comment/{commentId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .log().all().extract();
    }

    @Test
    void 댓글_삭제_댓글없음_404(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, CommonDocument.AccessTokenHeader,
                        CommentDocument.commentIdPathField, CommonDocument.ErrorResponseFields))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParam("commentId", 123456)
                .when()
                .delete("/comment/{commentId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .log().all().extract();
    }

    Integer 댓글_작성_진행(String token){
        ExtractableResponse<Response> response = given(this.spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParam("articleId", savedArticle.getArticleId())
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
}
