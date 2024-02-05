package com.ssafy.dancy.article;

import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Snippet;

import static com.ssafy.dancy.DocumentFormatProvider.required;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

public class ArticleDocument {

    // 차후 이 부분은 멀티파트로 변경될 수 있음.
    public static final Snippet ArticleWriteRequestField = requestFields(
            fieldWithPath("articleTitle").type(JsonFieldType.STRING).attributes(required()).description("게시물 제목"),
            fieldWithPath("articleContent").type(JsonFieldType.STRING).attributes(required()).description("게시물 내용"),
            fieldWithPath("video").type(JsonFieldType.VARIES).attributes(required()).description("비디오"),
            fieldWithPath("thumbnailImageUrl").type(JsonFieldType.VARIES).attributes(required()).description("썸네일 이미지 URL")
    );

    public static final Snippet articleIdPathField = pathParameters(
            parameterWithName("articleId").attributes(required()).description("게시물 아이디")
    );

    public static final Snippet ArticleWriteResponseField = responseFields(
            fieldWithPath("articleId").type(JsonFieldType.NUMBER).description("게시물 ID"),
            fieldWithPath("articleTitle").type(JsonFieldType.STRING).description("게시물 제목"),
            fieldWithPath("articleContent").type(JsonFieldType.STRING).description("게시물 내용"),
            fieldWithPath("thumbnailImageUrl").type(JsonFieldType.STRING).description("썸네일 이미지 URL"),
            fieldWithPath("thumbnailVideoUrl").type(JsonFieldType.STRING).description("썸네일 비디오 URL"),
            fieldWithPath("view").type(JsonFieldType.NUMBER).description("게시물 조회수"),
            fieldWithPath("articleLike").type(JsonFieldType.NUMBER).description("게시물 좋아요"),
            fieldWithPath("createdDate").type(JsonFieldType.STRING).description("생성일시"),
            fieldWithPath("isArticleLiked").type(JsonFieldType.BOOLEAN).description("내가 좋아요했는지 여부"),
            fieldWithPath("isAuthorFollowing").type(JsonFieldType.BOOLEAN).description("해당 사람 팔로우 여부"),
            fieldWithPath("score").type(JsonFieldType.NUMBER).description("점수"),
            fieldWithPath("follower").type(JsonFieldType.NUMBER).description("팔로워 수"),
            fieldWithPath("userId").type(JsonFieldType.NUMBER).description("유저 PK 아이디"),
            fieldWithPath("nickname").type(JsonFieldType.STRING).description("유저 닉네임"),
            fieldWithPath("profileImageUrl").type(JsonFieldType.VARIES).description("프로필 이미지 URL"),
            fieldWithPath("video").type(JsonFieldType.VARIES).description("비디오")
    );
}
