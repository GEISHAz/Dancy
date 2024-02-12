package com.ssafy.dancy.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNotification is a Querydsl query type for Notification
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNotification extends EntityPathBase<Notification> {

    private static final long serialVersionUID = -707740344L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNotification notification = new QNotification("notification");

    public final QArticle article;

    public final QUser authorUser;

    public final EnumPath<com.ssafy.dancy.type.NotificationContentType> contentType = createEnum("contentType", com.ssafy.dancy.type.NotificationContentType.class);

    public final DateTimePath<java.time.LocalDateTime> createdTime = createDateTime("createdTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> notificationId = createNumber("notificationId", Long.class);

    public final QUser targetUser;

    public QNotification(String variable) {
        this(Notification.class, forVariable(variable), INITS);
    }

    public QNotification(Path<? extends Notification> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNotification(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNotification(PathMetadata metadata, PathInits inits) {
        this(Notification.class, metadata, inits);
    }

    public QNotification(Class<? extends Notification> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.article = inits.isInitialized("article") ? new QArticle(forProperty("article"), inits.get("article")) : null;
        this.authorUser = inits.isInitialized("authorUser") ? new QUser(forProperty("authorUser")) : null;
        this.targetUser = inits.isInitialized("targetUser") ? new QUser(forProperty("targetUser")) : null;
    }

}

