package com.ssafy.dancy.comment;

import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Snippet;

import static com.ssafy.dancy.DocumentFormatProvider.required;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

public class CommentDocument {

    public static final Snippet articleIdPathField = pathParameters(
            parameterWithName("articleId").attributes(required()).description("게시물 아이디")
    );

    public static final Snippet commentIdPathField = pathParameters(
            parameterWithName("commentId").attributes(required()).description("댓글 아이디")
    );

    public static final Snippet commentWriteRequestField = requestFields(
            fieldWithPath("content").type(JsonFieldType.STRING).attributes(required()).description("댓글 내용"),
            fieldWithPath("parentId").type(JsonFieldType.NUMBER).attributes(required()).description("부모 댓글 아이디")
    );

    public static final Snippet commentModifyRequestField = requestFields(
            fieldWithPath("content").type(JsonFieldType.STRING).attributes(required()).description("댓글 내용")
    );

    public static final Snippet commentInfoResponseField = responseFields(
            fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("댓글 정보의 아이디"),
            fieldWithPath("content").type(JsonFieldType.STRING).description("댓글 내용"),
            fieldWithPath("createdDate").type(JsonFieldType.STRING).description("생성일시"),
            fieldWithPath("commentLike").type(JsonFieldType.NUMBER).description("댓글 좋아요 갯수"),
            fieldWithPath("authorNickname").type(JsonFieldType.STRING).description("작성자 닉네임"),
            fieldWithPath("articleId").type(JsonFieldType.NUMBER).description("댓글이 작성된 게시글의 아이디"),
            fieldWithPath("parentId").type(JsonFieldType.NUMBER).description("댓글의 부모 아이디(대댓글의 댓글)")
    );

    public static final Snippet commentInfoListResponseField = responseFields(
            fieldWithPath("[].commentId").type(JsonFieldType.NUMBER).description("댓글 정보의 아이디"),
            fieldWithPath("[].content").type(JsonFieldType.STRING).description("댓글 내용"),
            fieldWithPath("[].createdDate").type(JsonFieldType.STRING).description("생성일시"),
            fieldWithPath("[].commentLike").type(JsonFieldType.NUMBER).description("댓글 좋아요 갯수"),
            fieldWithPath("[].authorNickname").type(JsonFieldType.STRING).description("작성자 닉네임"),
            fieldWithPath("[].articleId").type(JsonFieldType.NUMBER).description("댓글이 작성된 게시글의 아이디"),
            fieldWithPath("[].parentId").type(JsonFieldType.NUMBER).description("댓글의 부모 아이디(대댓글의 댓글)")
    );
}
