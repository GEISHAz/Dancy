package com.ssafy.dancy.like;

import com.ssafy.dancy.ApiTest;
import com.ssafy.dancy.auth.AuthSteps;
import com.ssafy.dancy.entity.Article;
import com.ssafy.dancy.entity.Comment;
import com.ssafy.dancy.message.request.user.SignUpRequest;
import com.ssafy.dancy.repository.ArticleRepository;
import com.ssafy.dancy.repository.CommentRepository;
import com.ssafy.dancy.repository.UserRepository;
import com.ssafy.dancy.service.user.UserService;
import com.ssafy.dancy.type.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.util.Set;

import static io.restassured.RestAssured.given;

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

    private SignUpRequest signUpRequest;
    private SignUpRequest signUpOpponentRequest;

    @BeforeEach
    void settings(){
        signUpRequest = authSteps.회원가입정보_생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
        signUpOpponentRequest = authSteps.상대방회원가입정보_생성();
        userService.signup(signUpOpponentRequest, Set.of(Role.USER));
    }

    @Test
    void 게시글_좋아요(){

        Article savedArticle = articleRepository.save(Article.builder()
                .articleTitle("fuck")
                .articleContent("asedf")
                .build());

        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .body(savedArticle.getArticleId())
                .when()
                // URL 쏘는 자리. get, post, put, patch, delete
                .post("/like/article-like")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();
    }

    @Test
    void 댓글_좋아요(){

        Article savedArticle = articleRepository.save(Article.builder()
                .user(userRepository.findByEmail("allmin9702@naver.com").get())
                .articleTitle("fuck")
                .articleContent("asedf")
                .build()
        );

        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        Comment comment = Comment
                .builder()
                .article(savedArticle)
                .commentLike(0)
                .commentContent("힘드네요")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .parentId(-1L)
                .user(savedArticle.getUser())
                .build();
        Comment save = commentRepository.save(comment);

        given(this.spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .body(save.getCommentId())
                .when()
                // URL 쏘는 자리. get, post, put, patch, delete
                .post("/like/comment-like")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();
    }

    @Test
    void 게시글_좋아요_리스트(){

        Article savedArticle = articleRepository.save(Article.builder()
                .articleTitle("fuck")
                .articleContent("asedf")
                .build());

        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .body(savedArticle.getArticleId())
                .when()
                // URL 쏘는 자리. get, post, put, patch, delete
                .get("/like/who-like")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();
    }

}
