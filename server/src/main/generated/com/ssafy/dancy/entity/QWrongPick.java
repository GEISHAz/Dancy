package com.ssafy.dancy.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWrongPick is a Querydsl query type for WrongPick
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWrongPick extends EntityPathBase<WrongPick> {

    private static final long serialVersionUID = -656657359L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWrongPick wrongPick = new QWrongPick("wrongPick");

    public final NumberPath<Double> accuracy = createNumber("accuracy", Double.class);

    public final NumberPath<Integer> endTime = createNumber("endTime", Integer.class);

    public final NumberPath<Integer> startTime = createNumber("startTime", Integer.class);

    public final QVideo video;

    public final NumberPath<Long> wrongPickId = createNumber("wrongPickId", Long.class);

    public QWrongPick(String variable) {
        this(WrongPick.class, forVariable(variable), INITS);
    }

    public QWrongPick(Path<? extends WrongPick> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWrongPick(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWrongPick(PathMetadata metadata, PathInits inits) {
        this(WrongPick.class, metadata, inits);
    }

    public QWrongPick(Class<? extends WrongPick> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.video = inits.isInitialized("video") ? new QVideo(forProperty("video"), inits.get("video")) : null;
    }

}

