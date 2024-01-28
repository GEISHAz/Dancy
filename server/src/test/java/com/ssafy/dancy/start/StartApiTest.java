package com.ssafy.dancy.start;

import com.ssafy.dancy.ApiTest;
import com.ssafy.dancy.message.request.TestSaveRequest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    void jpa_테스트(){
        String daegu = "Daegu";

        test_entity_저장("dongwoo", "Daegu Dalseo Bolli");
        test_entity_저장("whalesbob", "Daegu Bukgu Sangyeok");

        ExtractableResponse<Response> result = given(this.spec)
                .pathParams("address", daegu)
                .when()
                .get("/start/test/jpa/{address}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        JsonPath jsonPath = result.jsonPath();
        assertThat(jsonPath.getList("")).size().isEqualTo(2);
    }


    @Test
    void querydsl_테스트(){

        String dongwooAddr = "Daegu Dalseo Bolli";
        test_entity_저장("dongwoo", dongwooAddr);
        test_entity_저장("whalesbob", "Daegu Bukgu Sangyeok");

        ExtractableResponse<Response> result = given(this.spec)
                .pathParams("name", "dongwoo")
                .when()
                .get("/start/test/querydsl/{name}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        JsonPath jsonPath = result.jsonPath();
        assertThat(jsonPath.getList("")).size().isEqualTo(1);
        assertThat(jsonPath.getString("address")).contains(dongwooAddr);
    }

    @Test
    void spring_validation_검증_성공_200(){
        TestSaveRequest request = request_생성("dongwoo", "daegu");

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/start/test/valid")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();
    }

    @Test
    void spring_validation_주소_누락_400(){
        TestSaveRequest request = request_생성("dongwoo", null);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/start/test/valid")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .log().all().extract();
    }

    void test_entity_저장(String name, String address){
        TestSaveRequest request = request_생성(name, address);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/start/test/save")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all();
    }

    TestSaveRequest request_생성(String name, String address){
        return TestSaveRequest.builder()
                .name(name)
                .address(address)
                .build();
    }
}
