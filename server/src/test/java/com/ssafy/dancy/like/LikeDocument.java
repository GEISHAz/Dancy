package com.ssafy.dancy.like;

import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.snippet.Snippet;

import static com.ssafy.dancy.DocumentFormatProvider.required;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

public class LikeDocument {

    public static final Snippet articlePathField = pathParameters(
            parameterWithName("articleId").attributes(required()).description("게시물 아이디")
    );

    public static final Snippet commentPathField = pathParameters(
            parameterWithName("commentId").attributes(required()).description("댓글 아이디")
    );

    public static final Snippet articleLikeResponseField = responseFields(
            fieldWithPath("articleLikeCount").type(JsonFieldType.NUMBER).description("해당 게시글의 좋아요 갯수"),
            fieldWithPath("isArticleLiked").type(JsonFieldType.BOOLEAN).description("해당 사람이 좋아요했는지 현재 여부")
    );

    public static final Snippet commentLikeResponseField = responseFields(
            fieldWithPath("commentLikeCount").type(JsonFieldType.NUMBER).description("해당 댓글의 좋아요 갯수"),
            fieldWithPath("isCommentLiked").type(JsonFieldType.BOOLEAN).description("해당 사람이 좋아요했는지 현재 여부")
    );

    public static final Snippet articleLikeUserListResponseField = responseFields(
            fieldWithPath("[].profileImageUrl").type(JsonFieldType.VARIES).description("프로필 이미지 URL"),
            fieldWithPath("[].nickname").type(JsonFieldType.STRING).description("좋아요한 사람의 닉네임")
    );

}
