package com.ssafy.dancy.video;

import com.ssafy.dancy.ApiTest;
import com.ssafy.dancy.CommonDocument;
import com.ssafy.dancy.auth.AuthSteps;
import com.ssafy.dancy.message.request.user.SignUpRequest;
import com.ssafy.dancy.message.request.video.ConvertVideoRequest;
import com.ssafy.dancy.repository.video.VideoRepository;
import com.ssafy.dancy.service.user.UserService;
import com.ssafy.dancy.type.Role;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Set;

import static com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper.document;
import static io.restassured.RestAssured.given;

public class VideoApiTest extends ApiTest {

    @Autowired
    private AuthSteps authSteps;
    @Autowired
    private UserService userService;
    @Autowired
    private VideoSteps videoSteps;

    private SignUpRequest signUpRequest;
    @BeforeEach
    void settings(){
        signUpRequest = authSteps.회원가입정보_생성();
        userService.signup(signUpRequest, Set.of(Role.USER));
    }

//    @Test
//    void 레퍼런스_비디오_있는것_조회(){
//        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
//
//        레퍼런스_비디오_업로드(token, "first.mp4");
//        레퍼런스_비디오_업로드(token, "second.mp4");
//
//        given(this.spec)
//                .filter(document(DEFAULT_RESTDOC_PATH, "레퍼런스 비디오 정보들을 가져오는 API 입니다." +
//                        "<br>성공적으로 레퍼런스 비디오 리스트를 불러오면, 200 OK 와 함께 비디오와 썸네일 링크를 반환받습니다." +
//                        "<br>무한 스크롤을 지원하는 API 입니다." +
//                        "<br>previousId 를 query String 으로 입력했을 때, 해당 Video Id 를 기준으로 그보다 이전에 만든 " +
//                        "<br>Reference Video 정보가 나옵니다." +
//                        "<br>받고자 하는 비디오의 갯수를 limit 라는 이름으로 query string 에 넣어 직접 지정할 수 있으며, limit 는 필수값입니다." +
//                        "<br>마지막 비디오일때, 204 No Content 를 반환받습니다.", "레퍼런스 비디오 조회",
//                        VideoDocument.referenceVideoListRequestField,
//                        VideoDocument.referenceVideoListResponseField))
//                .param("limit", 10)
//                .when()
//                .get("/video")
//                .then()
//                .assertThat()
//                .statusCode(HttpStatus.OK.value())
//                .log().all().extract();
//    }
//
//    @Test
//    void 레퍼런스_비디오_업로드_테스트(){
//
//        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
//
//        given(this.spec)
//                .filter(document(DEFAULT_RESTDOC_PATH, "레퍼런스 비디오를 업로드하는 API 입니다." +
//                                "<br>성공적으로 레퍼런스 비디오가 올라간 경우, 200 OK 와 함께 video 고유 ID 와 videoUrl, 썸네일 이미지 URL 이 반환됩니다." +
//                                "<br>반드시 multipart/form-data 형식으로 올려야 하며, 비디오 이름에 '-'(bar), '_'(underbar) 가 포함된 이름이어서는 안됩니다." +
//                                "<br>multipart/form-data 의 지정 이름은 'videoFile' 입니다." +
//                                "<br>위의 규칙을 위배하는 경우, 400 Bad Request 가 반환됩니다." +
//                                "<br>AUTH-TOKEN 이 유효하지 않거나, 값이 없을 경우 401 Unauthorized 가 반환됩니다.",
//                        "레퍼런스비디오 업로드",
//                        CommonDocument.AccessTokenHeader,
//                        VideoDocument.uploadReferenceVideoRequestField,
//                        VideoDocument.uploadVideoResponseField))
//                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
//                .header("AUTH-TOKEN", token)
//                .multiPart(VideoSteps.비디오_생성("emptyVideoOne.mp4"))
//                .when()
//                .post("/video/upload/reference")
//                .then()
//                .assertThat()
//                .statusCode(HttpStatus.OK.value())
//                .log().all().extract();
//    }
//
//    @Test
//    void 연습_비디오_업로드_테스트(){
//        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());
//
//        Long referenceVideoId = 레퍼런스_비디오_업로드(token, "videoExample.mp4");
//
//        given(this.spec)
//                .filter(document(DEFAULT_RESTDOC_PATH, "연습 비디오를 업로드하는 API 입니다." +
//                                "<br>성공적으로 연습 비디오가 올라간 경우, 200 OK 와 함께 video 고유 ID 와 videoUrl, 썸네일 이미지 URL 이 반환됩니다." +
//                                "<br>반드시 multipart/form-data 형식으로 올려야 하며, 비디오 이름에 '-'(bar), '_'(underbar) 가 포함된 이름이어서는 안됩니다." +
//                                "<br>multipart/form-data 의 비디오 지정 이름은 'videoFile' 입니다." +
//                                "<br>multipart/form-data 의 레퍼런스 비디오 지정이름은 'referenceVideoId' 입니다." +
//                                "<br>위의 규칙을 위배하는 경우, 400 Bad Request 가 반환됩니다." +
//                                "<br>AUTH-TOKEN 이 유효하지 않거나, 값이 없을 경우 401 Unauthorized 가 반환됩니다.",
//                        "연습비디오 업로드",
//                        CommonDocument.AccessTokenHeader,
//                        VideoDocument.uploadPracticeVideoRequestField,
//                        VideoDocument.uploadVideoResponseField))
//                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
//                .header("AUTH-TOKEN", token)
//                .multiPart("referenceVideoId", referenceVideoId)
//                .multiPart(VideoSteps.비디오_생성("videoPractice.mp4"))
//                .when()
//                .post("/video/upload/practice")
//                .then()
//                .assertThat()
//                .statusCode(HttpStatus.OK.value())
//                .log().all().extract();
//    }

    @Test
    void 비디오_변환_테스트(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        ConvertVideoRequest request = ConvertVideoRequest.builder()
                .practiceVideoUrl("https://gumid210bucket.s3.ap-northeast-2.amazonaws.com/video/prac/asap_prac_cnh2_uuid.mp4")
                .referenceVideoUrl("https://gumid210bucket.s3.ap-northeast-2.amazonaws.com/video/gt/asap_gt_cnh2.mp4")
                .build();

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, "입력받은 두 S3 URL 을 바탕으로, 실제로 비교작업에 들어가는 API 입니다." +
                                "<br>성공적으로 신청이 들어갔다면, 200 OK 와 함께 신청 현황을 입력받습니다." +
                                "<br>두 입력이 URL 형태가 아닐 경우, 400 Bad Request 를 입력받습니다." +
                                "<br>만약 S3에 존재하지 않는 객체를 입력했을 경우, 404 Not Found 를 입력받습니다." +
                                "<br>파이썬 서버 이상으로 요청이 성공적으로 들어가지 않은 경우, 409 Conflict 를 반환받습니다." +
                                "<br>해당 API 에서 409 를 발견하신다면, 주저하지 않고 문의 바랍니다.",
                        "비디오 분석 요청",
                        CommonDocument.AccessTokenHeader,
                        VideoDocument.convertTwoVideoRequestField,
                        VideoDocument.convertVideoResponseField))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("AUTH-TOKEN", token)
                .body(request)
                .when()
                .post("/video/analyze")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();
    }

    @Test
    void 결과_보여주기_테스트(){
        String token = authSteps.로그인액세스토큰정보(AuthSteps.로그인요청생성());

        Long videoId = videoSteps.결과확인_사전작업(AuthSteps.email);

        given(this.spec)
                .filter(document(DEFAULT_RESTDOC_PATH, "입력받은 결과 videoId 를 바탕으로, 분석 결과를 보여주는 API 입니다." +
                        "<br>성공적으로 정보를 받았다면, 200 OK 와 함께 관련 정보를 입력받습니다. " +
                        "<br>토큰을 입력하지 않고 정보 반환 신청을 하면 401 Unauthorized 가 반환됩니다." +
                        "<br>영상이 존재하지만 해당 유저의 영상이 아닐 경우, 403 Forbidden 이 반환됩니다." +
                        "<br>해당 영상 아이디로 영상을 찾지 못할 경우, 404 Not Found 가 반환됩니다.", "분석결과확인",
                        CommonDocument.AccessTokenHeader,
                        VideoDocument.videoIdPathField,
                        VideoDocument.analysisResultResponseField))
                .headers("AUTH-TOKEN", token)
                .pathParams("videoId", videoId)
                .when()
                .get("/video/after/{videoId}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

    }

    Long 레퍼런스_비디오_업로드(String token, String videoFilename){
        ExtractableResponse<Response> response = given(this.spec)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .header("AUTH-TOKEN", token)
                .multiPart(VideoSteps.비디오_생성(videoFilename))
                .when()
                .post("/video/upload/reference")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .log().all().extract();

        return response.jsonPath().getLong("videoId");
    }
}
