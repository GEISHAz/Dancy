package com.ssafy.dancy.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 975293736L;

    public static final QUser user = new QUser("user");

    public final EnumPath<com.ssafy.dancy.type.AuthType> authType = createEnum("authType", com.ssafy.dancy.type.AuthType.class);

    public final DateTimePath<java.util.Date> birthDate = createDateTime("birthDate", java.util.Date.class);

    public final StringPath email = createString("email");

    public final EnumPath<com.ssafy.dancy.type.Gender> gender = createEnum("gender", com.ssafy.dancy.type.Gender.class);

    public final StringPath introduceText = createString("introduceText");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath profileImageUrl = createString("profileImageUrl");

    public final SetPath<com.ssafy.dancy.type.Role, EnumPath<com.ssafy.dancy.type.Role>> roles = this.<com.ssafy.dancy.type.Role, EnumPath<com.ssafy.dancy.type.Role>>createSet("roles", com.ssafy.dancy.type.Role.class, EnumPath.class, PathInits.DIRECT2);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

