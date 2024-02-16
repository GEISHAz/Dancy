package com.ssafy.dancy.message.response.video;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.ssafy.dancy.message.response.video.QVideoReferenceResponse is a Querydsl Projection type for VideoReferenceResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QVideoReferenceResponse extends ConstructorExpression<VideoReferenceResponse> {

    private static final long serialVersionUID = -1916016676L;

    public QVideoReferenceResponse(com.querydsl.core.types.Expression<Long> videoId, com.querydsl.core.types.Expression<String> videoUrl, com.querydsl.core.types.Expression<String> thumbnailImageUrl) {
        super(VideoReferenceResponse.class, new Class<?>[]{long.class, String.class, String.class}, videoId, videoUrl, thumbnailImageUrl);
    }

}

