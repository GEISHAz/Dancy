package com.ssafy.dancy.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVideo is a Querydsl query type for Video
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVideo extends EntityPathBase<Video> {

    private static final long serialVersionUID = 169959102L;

    public static final QVideo video = new QVideo("video");

    public final StringPath fullVideoUrl = createString("fullVideoUrl");

    public final StringPath thumbnailImageUrl = createString("thumbnailImageUrl");

    public final StringPath thumbnailVideoUrl = createString("thumbnailVideoUrl");

    public final NumberPath<Long> videoId = createNumber("videoId", Long.class);

    public final StringPath videoTitle = createString("videoTitle");

    public QVideo(String variable) {
        super(Video.class, forVariable(variable));
    }

    public QVideo(Path<? extends Video> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVideo(PathMetadata metadata) {
        super(Video.class, metadata);
    }

}

