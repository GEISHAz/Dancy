package com.ssafy.dancy.email;

import com.ssafy.dancy.ApiTest;
import com.ssafy.dancy.CommonDocument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class EmailApiTest extends ApiTest {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private EmailSteps emailSteps;

    @Test
    void 이메일_가입_인증번호_전송_테스트_성공_200(){

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, "이메일 가입 인증번호 코드를 전송하는 API 입니다." +
                                "<br>이미 생성된 계정이 아닐 때, 200 OK 와 함께 이메일이 해당 계정으로 전송됩니다." +
                                "<br>이메일이 전송될 때, 서버에 인증번호 정보가 저장되어 있습니다. 인증번호의 유효기간은 30분 입니다. " +
                                "<br>가입하려는 이메일 계정이, 이메일 형식을 하고 있지 않을 경우 400 Bad Request 가 반환됩니다." +
                                "<br>이미 가입된 이메일의 경우, 409 Conflict 가 반환됩니다.",
                        "이메일 가입 인증번호 전송",

                        EmailDocument.targetEmailRequestField))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(emailSteps.이메일_정보_생성())
                .when()
                .post("/email/verify/send")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        String expectedKey = String.format("VERIFY:%s", EmailSteps.targetEmail);
        assertThat(redisTemplate.hasKey(expectedKey)).isTrue();
    }

    @Test
    void 이메일_가입_인증번호_전송_이메일_형식_아님_400(){
        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, CommonDocument.ErrorResponseFields))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(emailSteps.이메일_헝식_아닌정보_생성())
                .when()
                .post("/email/verify/send")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .log().all().extract();
    }
}
