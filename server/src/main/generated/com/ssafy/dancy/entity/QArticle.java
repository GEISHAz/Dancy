package com.ssafy.dancy.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QArticle is a Querydsl query type for Article
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QArticle extends EntityPathBase<Article> {

    private static final long serialVersionUID = -1063218567L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QArticle article = new QArticle("article");

    public final StringPath articleContent = createString("articleContent");

    public final NumberPath<Long> articleId = createNumber("articleId", Long.class);

    public final NumberPath<Integer> articleLike = createNumber("articleLike", Integer.class);

    public final StringPath articleTitle = createString("articleTitle");

    public final DateTimePath<java.time.LocalDateTime> createdDate = createDateTime("createdDate", java.time.LocalDateTime.class);

    public final StringPath thumbnailImageUrl = createString("thumbnailImageUrl");

    public final StringPath thumbnailVideoUrl = createString("thumbnailVideoUrl");

    public final QUser user;

    public final QVideo video;

    public final NumberPath<Long> view = createNumber("view", Long.class);

    public QArticle(String variable) {
        this(Article.class, forVariable(variable), INITS);
    }

    public QArticle(Path<? extends Article> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QArticle(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QArticle(PathMetadata metadata, PathInits inits) {
        this(Article.class, metadata, inits);
    }

    public QArticle(Class<? extends Article> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
        this.video = inits.isInitialized("video") ? new QVideo(forProperty("video")) : null;
    }

}

