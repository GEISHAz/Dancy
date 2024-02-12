package com.ssafy.dancy.like;

import com.ssafy.dancy.ApiTest;
import com.ssafy.dancy.CommonDocument;
import com.ssafy.dancy.auth.AuthSteps;
import com.ssafy.dancy.entity.Article;
import com.ssafy.dancy.entity.Comment;
import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.entity.Video;
import com.ssafy.dancy.message.request.user.SignUpRequest;
import com.ssafy.dancy.repository.*;
import com.ssafy.dancy.repository.article.ArticleRepository;
import com.ssafy.dancy.repository.video.VideoRepository;
import com.ssafy.dancy.service.user.UserService;
import com.ssafy.dancy.type.Role;
import com.ssafy.dancy.video.VideoSteps;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.util.Set;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class LikeApiTest extends ApiTest {

    @Autowired
    private AuthSteps authSteps;
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArticleLikeRepository articleLikeRepository;
    @Autowired
    private CommentLikeRepository commentLikeRepository;
    @Autowired
    private VideoSteps videoSteps;
    @Autowired
    private VideoRepository videoRepository;

    private SignUpRequest signUpRequest;
    private SignUpRequest signUpOpponentRequest;
    private Article savedArticle;
    private Long videoId;

    @BeforeEach
    void settings(){
        signUpRequest = authSteps.회원가입정보_생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
        signUpOpponentRequest = authSteps.상대방회원가입정보_생성();
        userService.signup(signUpOpponentRequest, Set.of(Role.USER));

        User opponentUser = userRepository.findByEmail(AuthSteps.opponentemail).get();
        videoId = videoSteps.결과확인_사전작업(opponentUser.getEmail());

        Video video = videoRepository.findByVideoId(videoId).get();

        savedArticle = articleRepository.save(Article.builder()
                .user(opponentUser)
                .articleTitle("titleasd")
                .articleContent("asedf")
                .video(video)
                .thumbnailImageUrl(video.getThumbnailImageUrl())
                .build());
    }

    @Test
    void 게시글_좋아요_프로세스_성공_200(){

        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        ExtractableResponse<Response> response = given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, "게시글 좋아요를 등록하고 취소하는 API 입니다." +
                                "<br>게시글 좋아요 등록이 성공하면, 200 OK 와 함께 해당 게시글의 좋아요 갯수, 자신이 좋아요하는지 여부 정보를 입력받습니다." +
                                "<br>게시글이 좋아요 되어 있는 상태에서 다시 해당 API 를 호출하면, 200 OK 가 반환되며 좋아요가 취소됩니다." +
                                "<br>로그인 토큰을 헤더에 입력하지 않고 해당 API 를 호출하면, 401 Unauthorized 가 반환됩니다." +
                                "<br>존재하지 않는 게시글을 좋아요 요청하면, 404 Not Found 가 반환됩니다.",
                        "게시글 좋아요 프로세스",
                        CommonDocument.AccessTokenHeader,
                        LikeDocument.articlePathField,
                        LikeDocument.articleLikeResponseField))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParams("articleId", savedArticle.getArticleId())
                .when()
                .post("/like/article-like/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();


        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getInt("articleLikeCount")).isEqualTo(1);

        User user = userRepository.findByEmail(AuthSteps.email).get();
        assertThat(articleLikeRepository.findByUserAndArticle(user, savedArticle)).isPresent();

        Article targetArticle = articleRepository.findByArticleId(savedArticle.getArticleId()).get();
        assertThat(targetArticle.getArticleLike()).isEqualTo(1);
    }

    @Test
    void 게시글_좋아요_좋아요이후_취소_200(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        게시글_좋아요_진행(token);

        ExtractableResponse<Response> response = given(this.spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParams("articleId", savedArticle.getArticleId())
                .when()
                .post("/like/article-like/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getInt("articleLikeCount")).isEqualTo(0);

        User user = userRepository.findByEmail(AuthSteps.email).get();
        assertThat(articleLikeRepository.findByUserAndArticle(user, savedArticle)).isEmpty();

        Article targetArticle = articleRepository.findByArticleId(savedArticle.getArticleId()).get();
        assertThat(targetArticle.getArticleLike()).isEqualTo(0);
    }

    @Test
    void 게시글_좋아요_토큰없음_401(){
        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParams("articleId", savedArticle.getArticleId())
                .when()
                .post("/like/article-like/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .log().all().extract();
    }

    @Test
    void 게시글_좋아요_게시글없음_404(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, CommonDocument.AccessTokenHeader,
                        LikeDocument.articlePathField, CommonDocument.ErrorResponseFields))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParams("articleId", 123456)
                .when()
                .post("/like/article-like/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .log().all().extract();
    }

    void 게시글_좋아요_진행(String token){
        given(this.spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParams("articleId", savedArticle.getArticleId())
                .when()
                .post("/like/article-like/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();
    }

    @Test
    void 댓글_좋아요_성공_200(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        Comment comment = 댓글_작성_진행();

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, "댓글 좋아요를 등록하고 취소하는 API 입니다." +
                                "<br>댓글 좋아요 등록이 성공하면, 200 OK 와 함께 해당 댓글의 좋아요 갯수, 자신이 좋아요하는지 여부 정보를 입력받습니다." +
                                "<br>댓글이 좋아요 되어 있는 상태에서 다시 해당 API 를 호출하면, 200 OK 가 반환되며 좋아요가 취소됩니다." +
                                "<br>로그인 토큰을 헤더에 입력하지 않고 해당 API 를 호출하면, 401 Unauthorized 가 반환됩니다." +
                                "<br>존재하지 않는 댓글을 좋아요 요청하면, 404 Not Found 가 반환됩니다.",
                        "댓글 좋아요 프로세스",
                        CommonDocument.AccessTokenHeader,
                        LikeDocument.commentPathField,
                        LikeDocument.commentLikeResponseField))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParams("commentId", comment.getCommentId())
                .when()
                .post("/like/comment-like/{commentId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        User user = userRepository.findByEmail(AuthSteps.email).get();

        assertThat(commentLikeRepository.findByUserAndComment(user, comment)).isPresent();
        Comment foundComment = commentRepository.findByCommentId(comment.getCommentId()).get();
        assertThat(foundComment.getCommentLike()).isEqualTo(1);
    }

    @Test
    void 댓글_좋아요_취소_성공_200(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        Comment comment = 댓글_작성_진행();
        댓글_좋아요_진행(comment, token);

        given(this.spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParams("commentId", comment.getCommentId())
                .when()
                .post("/like/comment-like/{commentId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        User user = userRepository.findByEmail(AuthSteps.email).get();

        assertThat(commentLikeRepository.findByUserAndComment(user, comment)).isEmpty();
        Comment foundComment = commentRepository.findByCommentId(comment.getCommentId()).get();
        assertThat(foundComment.getCommentLike()).isEqualTo(0);
    }

    @Test
    void 댓글_좋아요_토큰없음_401(){
        Comment comment = 댓글_작성_진행();

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParams("commentId", comment.getCommentId())
                .when()
                .post("/like/comment-like/{commentId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .log().all().extract();
    }

    @Test
    void 댓글_좋아요_댓글없음_404(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, CommonDocument.AccessTokenHeader,
                        LikeDocument.commentPathField, CommonDocument.ErrorResponseFields))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParams("commentId", 123456)
                .when()
                .post("/like/comment-like/{commentId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .log().all().extract();
    }


    Comment 댓글_작성_진행(){
        Comment comment = Comment
                .builder()
                .article(savedArticle)
                .commentLike(0)
                .commentContent("힘드네요")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .parentId(0L)
                .user(savedArticle.getUser())
                .build();

        return commentRepository.save(comment);
    }

    void 댓글_좋아요_진행(Comment comment, String token){
        given(this.spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParams("commentId", comment.getCommentId())
                .when()
                .post("/like/comment-like/{commentId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();
    }

    @Test
    void 게시글_좋아요_리스트_200(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
        게시글_좋아요_진행(token);

        ExtractableResponse<Response> response = given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, "게시글 좋아요를 한 사람의 리스트를 불러오는 API 입니다. " +
                        "<br>존재하는 게시글의 아이디를 path variable 로 전달했을 시, 200 OK 와 함께 좋아요한 사람의 간단한 정보 리스트가 전달됩니다." +
                        "<br>존재하지 않는 게시글 아이디를 전달했을 시, 404 Not Found 가 전달됩니다.",
                        "게시글 좋아요 리스트 조회",
                        LikeDocument.articlePathField, LikeDocument.articleLikeUserListResponseField))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParams("articleId", savedArticle.getArticleId())
                .when()
                .get("/like/who-like/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        Article foundArticle = articleRepository.findByArticleId(savedArticle.getArticleId()).get();
        JsonPath jsonPath = response.jsonPath();
        assertThat(jsonPath.getList("").size()).isEqualTo(foundArticle.getArticleLike());
    }

    @Test
    void 게시글_좋아요_없는게시글_404(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH,
                        LikeDocument.articlePathField, CommonDocument.ErrorResponseFields))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParams("articleId", 12345)
                .when()
                .get("/like/who-like/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .log().all().extract();
    }
}
