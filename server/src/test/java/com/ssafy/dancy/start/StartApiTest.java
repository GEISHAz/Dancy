package com.ssafy.dancy.start;

import com.ssafy.dancy.ApiTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class StartApiTest extends ApiTest {

    @Test
    void start_테스트(){
        given()
                .when()
                .get("/start/hello")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();
    }
}
