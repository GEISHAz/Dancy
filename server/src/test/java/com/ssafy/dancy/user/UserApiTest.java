package com.ssafy.dancy.user;

import com.amazonaws.services.s3.AmazonS3;
import com.ssafy.dancy.ApiTest;
import com.ssafy.dancy.auth.AuthSteps;
import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.repository.UserRepository;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.awt.*;
import java.util.Optional;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

public class UserApiTest extends ApiTest {

    @Autowired
    private AuthSteps authSteps;
    @Autowired
    private UserRepository userRepository;

    @MockBean
    private AmazonS3 amazonS3;


    @Test
    void 회원가입_성공_200(){
        Mockito.doReturn(null).when(amazonS3).putObject(Mockito.any());

        ExtractableResponse<Response> response = given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, "회원 가입을 처리하는 API 입니다." +
                                "<br>성공적으로 회원가입이 이루어진 경우, 200 OK 와 함께 이메일과 닉네임 정보가 반환됩니다." +
                                "<br>검증 로직에 맞지 않는 입력값을 넣었을 때, 400 Bad Request 가 반환됩니다. " +
                                "<br>이메일 검증이 이루어지지 않은 사용자가 가입을 시도할 경우, 403 Forbidden 이 반환됩니다." +
                                "<br>이때, 이메일 인증 이후 30분 이상 가입이 진행되지 않았을 경우, 인증이 취소된 것으로 간주되며" +
                                "<br>새로 인증을 진행해야 합니다." +
                                "<br>이미 가입된 이메일의 경우, 409 Conflict 가 반환됩니다.",
                        "회원가입",
                        UserDocument.signUpRequestField, UserDocument.signUpResponseField))
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .multiPart("email", AuthSteps.email)
                .multiPart("nickname", AuthSteps.nickname)
                .multiPart("password", AuthSteps.password)
                .multiPart("birthDate", AuthSteps.birthDate)
                .multiPart("gender", AuthSteps.gender)
                .multiPart("authType", AuthSteps.authType)
                .multiPart(UserSteps.프로필_이미지_간단생성())
                .when()
                .post("/user/signup")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        JsonPath jsonPath = response.jsonPath();
        Optional<User> foundUser = userRepository.findByEmail(AuthSteps.email);
        assertThat(foundUser.isPresent()).isTrue();
        assertThat(foundUser.get().getNickname()).isEqualTo(jsonPath.getString("nickname"));

        Mockito.verify(amazonS3, times(1)).putObject(Mockito.any());
        Mockito.verify(redisTemplate, times(1)).hasKey(Mockito.any());
    }
}
