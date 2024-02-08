package com.ssafy.dancy.article;

import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.restdocs.snippet.Snippet;

import static com.ssafy.dancy.DocumentFormatProvider.required;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;

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

    public static final Snippet stageRequestField = queryParameters(
            parameterWithName("limit").attributes(required()).description("받을 게시글 최대 갯수"),
            parameterWithName("previousArticleId").description("무한 스크롤에서 마지막에 받은 게시글 아이디")
    );

    public static final Snippet keywordPathField = pathParameters(
            parameterWithName("keyword").attributes(required()).description("검색 키워드")
    );

    public static final Snippet ArticleWriteResponseField = responseFields(
            fieldWithPath("articleId").type(JsonFieldType.NUMBER).description("게시물 ID"),
            fieldWithPath("articleTitle").type(JsonFieldType.STRING).description("게시물 제목"),
            fieldWithPath("articleContent").type(JsonFieldType.STRING).description("게시물 내용"),
            fieldWithPath("thumbnailImageUrl").type(JsonFieldType.STRING).description("썸네일 이미지 URL"),
            fieldWithPath("thumbnailVideoUrl").type(JsonFieldType.STRING).description("썸네일 비디오 URL"),
            fieldWithPath("view").type(JsonFieldType.NUMBER).description("게시물 조회수"),
            fieldWithPath("articleLike").type(JsonFieldType.NUMBER).description("게시물 좋아요"),
            fieldWithPath("createdDate").type(JsonFieldType.ARRAY).description("생성일시"),
            fieldWithPath("isArticleLiked").type(JsonFieldType.BOOLEAN).description("내가 좋아요했는지 여부"),
            fieldWithPath("isAuthorFollowed").type(JsonFieldType.BOOLEAN).description("해당 사람 팔로우 여부"),
            fieldWithPath("score").type(JsonFieldType.NUMBER).description("점수"),
            fieldWithPath("follower").type(JsonFieldType.NUMBER).description("팔로워 수"),
            fieldWithPath("authorId").type(JsonFieldType.NUMBER).description("유저 PK 아이디"),
            fieldWithPath("nickname").type(JsonFieldType.STRING).description("유저 닉네임"),
            fieldWithPath("profileImageUrl").type(JsonFieldType.VARIES).description("프로필 이미지 URL"),
            fieldWithPath("video").type(JsonFieldType.VARIES).description("비디오")
    );

    public static final Snippet articleDetailResponseField = responseFields(
            fieldWithPath("articleId").type(JsonFieldType.NUMBER).description("게시글 고유 아이디"),
            fieldWithPath("articleTitle").type(JsonFieldType.STRING).description("게시글 제목"),
            fieldWithPath("articleContent").type(JsonFieldType.STRING).description("게시글 내용"),
            fieldWithPath("thumbnailImageUrl").type(JsonFieldType.STRING).description("썸네일 이미지 URL"),
            fieldWithPath("thumbnailVideoUrl").type(JsonFieldType.STRING).description("썸네일 비디오 URL"),
            fieldWithPath("view").type(JsonFieldType.NUMBER).description("조회수"),
            fieldWithPath("articleLike").type(JsonFieldType.NUMBER).description("게시글 좋아요 갯수"),
            fieldWithPath("createdDate").type(JsonFieldType.ARRAY).description("생성일시"),
            fieldWithPath("isArticleLiked").type(JsonFieldType.BOOLEAN).description("내가 좋아요했는지 여부"),
            fieldWithPath("isAuthorFollowed").type(JsonFieldType.BOOLEAN).description("내가 글쓴이를 팔로우했는지 여부"),
            fieldWithPath("score").type(JsonFieldType.NUMBER).description("비디오 정확도"),
            fieldWithPath("follower").type(JsonFieldType.NUMBER).description("글쓴이 팔로워 수"),
            fieldWithPath("authorId").type(JsonFieldType.NUMBER).description("글쓴이 고유 아이디"),
            fieldWithPath("nickname").type(JsonFieldType.STRING).description("글쓴이 닉네임"),
            fieldWithPath("profileImageUrl").type(JsonFieldType.VARIES).description("글쓴이 프로필 URL"),
            fieldWithPath("video").type(JsonFieldType.VARIES).description("비디오 URL")
    );

    public static final Snippet simpleArticleListResponseField = responseFields(
            fieldWithPath("[].articleId").type(JsonFieldType.NUMBER).description("게시글 고유 아이디"),
            fieldWithPath("[].articleTitle").type(JsonFieldType.STRING).description("게시글 제목"),
            fieldWithPath("[].articleThumbnail").type(JsonFieldType.STRING).description("썸네일 이미지 URL"),
            fieldWithPath("[].authorId").type(JsonFieldType.NUMBER).description("글쓴이 고유 아이디"),
            fieldWithPath("[].authorProfileImage").type(JsonFieldType.VARIES).description("글쓴이 프로필 URL"),
            fieldWithPath("[].authorName").type(JsonFieldType.STRING).description("글쓴이 닉네임"),
            fieldWithPath("[].articleView").type(JsonFieldType.NUMBER).description("조회수"),
            fieldWithPath("[].createdDate").type(JsonFieldType.ARRAY).description("생성일시")
    );
}
