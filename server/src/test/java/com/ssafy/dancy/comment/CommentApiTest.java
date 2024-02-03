package com.ssafy.dancy.comment;

import com.ssafy.dancy.ApiTest;
import com.ssafy.dancy.auth.AuthSteps;
import com.ssafy.dancy.entity.Article;
import com.ssafy.dancy.message.request.user.SignUpRequest;
import com.ssafy.dancy.repository.ArticleRepository;
import com.ssafy.dancy.service.user.UserService;
import com.ssafy.dancy.type.Role;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class CommentApiTest extends ApiTest {

    @Autowired
    private AuthSteps authSteps;
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleRepository articleRepository;

    private SignUpRequest signUpRequest;

    @BeforeEach
    void settings(){
        signUpRequest = authSteps.회원가입정보_생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
    }


    @Test
    void 댓글_작성(){

        Article article = Article.builder()
                        .articleTitle("fuck")
                        .articleContent("asedf")
                        .build();

        Article savedArticle = articleRepository.save(article);


        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
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
    void 댓글_조회(){

        댓글_작성();

        List<Article> list =  articleRepository.findAll();

        System.out.println(list.toString());

        given(this.spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .pathParam("articleId",1)
                .when()
                .get("/comment/{articleId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

    }

    @Test
    void 댓글_수정(){

        댓글_작성();

        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParam("commentId", 1)
                .body("수정된 댓글입니다.")
                .when()
                // URL 쏘는 자리. get, post, put, patch, delete
                .put("/comment/{commentId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();
    }

    @Test
    void 댓글_삭제(){

        댓글_작성();

        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .pathParam("commentId", 1)
                .when()
                // URL 쏘는 자리. get, post, put, patch, delete
                .delete("/comment/{commentId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();
    }

}
