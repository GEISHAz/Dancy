package com.ssafy.dancy.video;

import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Snippet;

import static com.ssafy.dancy.DocumentFormatProvider.required;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;

public class VideoDocument {

    public static final Snippet uploadReferenceVideoRequestField = requestParts(
            partWithName("videoFile").attributes(required())
                    .description("입력하려는 비디오 파일")
    );

    public static final Snippet uploadPracticeVideoRequestField = requestParts(
            partWithName("referenceVideoId").attributes(required()).description("레퍼런스 비디오의 고유 ID"),
            partWithName("videoFile").attributes(required()).description("입력하려는 비디오 파일")
    );

    public static final Snippet convertTwoVideoRequestField = requestFields(
            fieldWithPath("practiceVideoUrl").type(JsonFieldType.STRING).attributes(required())
                    .description("연습 비디오 URL"),
            fieldWithPath("referenceVideoUrl").type(JsonFieldType.STRING).attributes(required())
                    .description("레퍼런스 비디오 URL")
    );

    public static final Snippet videoIdPathField = pathParameters(
            parameterWithName("videoId").attributes(required()).description("비디오 고유 ID")
    );

    public static final Snippet referenceVideoListRequestField = queryParameters(
            parameterWithName("limit").attributes(required()).description("받을 비디오 최대 갯수"),
            parameterWithName("previousVideoId").description("무한 스크롤에서 마지막에 받은 비디오 아이디").optional()
    );

    public static final Snippet uploadVideoResponseField = responseFields(
            fieldWithPath("videoId").type(JsonFieldType.NUMBER).description("비디오 고유 ID"),
            fieldWithPath("resultVideoUrl").type(JsonFieldType.STRING).description("업로드된 비디오의 S3 URL"),
            fieldWithPath("thumbnailImageUrl").type(JsonFieldType.STRING).description("업로드된 비디오 썸네일의 S3 URL")
    );

    public static final Snippet convertVideoResponseField = responseFields(
            fieldWithPath("referenceVideoUrl").type(JsonFieldType.STRING).description("레퍼런스 비디오 URL"),
            fieldWithPath("practiceVideoUrl").type(JsonFieldType.STRING).description("연습 비디오 URL"),
            fieldWithPath("requestTime").type(JsonFieldType.ARRAY).description("신청시간")
    );

    public static final Snippet analysisResultResponseField = responseFields(
            fieldWithPath("wrongSections").type(JsonFieldType.ARRAY).description("틀린 구간 리스트"),
            fieldWithPath("wrongSections[].start").type(JsonFieldType.NUMBER).description("틀린 부분 시작"),
            fieldWithPath("wrongSections[].end").type(JsonFieldType.NUMBER).description("틀린 부분 끝"),
            fieldWithPath("wrongSections[].accuracy").type(JsonFieldType.NUMBER).description("틀린 구간 순간정확도"),
            fieldWithPath("videoUrl").type(JsonFieldType.STRING).description("결과 비디오 URL"),
            fieldWithPath("thumbnailImageUrl").type(JsonFieldType.STRING).description("영상 썸네일 이미지 URL"),
            fieldWithPath("nickname").type(JsonFieldType.STRING).description("유저 닉네임"),
            fieldWithPath("videoTitle").type(JsonFieldType.STRING).description("비디오 제목"),
            fieldWithPath("score").type(JsonFieldType.NUMBER).description("평균점수")
    );

    public static final Snippet referenceVideoListResponseField = responseFields(
            fieldWithPath("[].videoId").type(JsonFieldType.NUMBER).description("비디오 고유 ID"),
            fieldWithPath("[].videoUrl").type(JsonFieldType.STRING).description("레퍼런스 비디오 URL"),
            fieldWithPath("[].thumbnailImageUrl").type(JsonFieldType.STRING).description("썸네일 이미지 URL")
    );
}
