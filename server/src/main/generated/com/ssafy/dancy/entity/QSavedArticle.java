package com.ssafy.dancy.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSavedArticle is a Querydsl query type for SavedArticle
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSavedArticle extends EntityPathBase<SavedArticle> {

    private static final long serialVersionUID = 1982600396L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSavedArticle savedArticle = new QSavedArticle("savedArticle");

    public final QArticle article;

    public final QUser user;

    public QSavedArticle(String variable) {
        this(SavedArticle.class, forVariable(variable), INITS);
    }

    public QSavedArticle(Path<? extends SavedArticle> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSavedArticle(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSavedArticle(PathMetadata metadata, PathInits inits) {
        this(SavedArticle.class, metadata, inits);
    }

    public QSavedArticle(Class<? extends SavedArticle> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.article = inits.isInitialized("article") ? new QArticle(forProperty("article"), inits.get("article")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

