package com.ssafy.dancy.start;

import com.ssafy.dancy.ApiTest;
import com.ssafy.dancy.auth.AuthSteps;
import com.ssafy.dancy.message.request.TestSaveRequest;
import com.ssafy.dancy.message.request.user.SignUpRequest;
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

import java.util.Set;

import static io.restassured.RestAssured.given;
import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static org.assertj.core.api.Assertions.assertThat;

public class StartApiTest extends ApiTest {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthSteps authSteps;

    private SignUpRequest signUpRequest;

    @BeforeEach
    void settings(){
        signUpRequest = authSteps.회원가입정보_생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
    }


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

    @Test
    void 로그인_정보_기본_가져오기세팅_테스트(){

        final String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        given(this.spec)
                .header("AUTH-TOKEN", token)
                .when()
                .get("/start/test/userinfo")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();
    }

    @Test
    void 로그인_정보_기본_토큰없음_401(){
        given(this.spec)
                .when()
                .get("/start/test/userinfo")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
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
