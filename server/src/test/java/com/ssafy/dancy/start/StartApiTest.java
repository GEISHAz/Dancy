package com.ssafy.dancy.start;

import com.ssafy.dancy.ApiTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static io.restassured.RestAssured.given;

public class StartApiTest extends ApiTest {

    @Test
    void start_테스트(){
        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, "시작 테스트 입니다.",
                        "시작 API", StartDocument.startResponseField))
                .when()
                .get("/start/hello")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();
    }
}
