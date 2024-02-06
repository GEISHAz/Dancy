package com.ssafy.dancy.repository.follow;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.ssafy.dancy.repository.follow.QMyPageResponseDTO is a Querydsl Projection type for MyPageResponseDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMyPageResponseDTO extends ConstructorExpression<MyPageResponseDTO> {

    private static final long serialVersionUID = -1781176932L;

    public QMyPageResponseDTO(com.querydsl.core.types.Expression<String> nickname, com.querydsl.core.types.Expression<String> introduceText, com.querydsl.core.types.Expression<String> profileImageUrl, com.querydsl.core.types.Expression<Integer> following, com.querydsl.core.types.Expression<Integer> follower, com.querydsl.core.types.Expression<? extends com.ssafy.dancy.entity.User> fromUser) {
        super(MyPageResponseDTO.class, new Class<?>[]{String.class, String.class, String.class, int.class, int.class, com.ssafy.dancy.entity.User.class}, nickname, introduceText, profileImageUrl, following, follower, fromUser);
    }

}

