package com.ssafy.dancy.type;

import com.ssafy.dancy.entity.User;

public enum NotificationContentType {
    DANCE_AFTER(" 님이 회원님의 영상을 따라추기 했습니다."),
    FOLLOW(" 님이 회원님을 팔로우했습니다."),
    LIKE(" 님이 회원님의 게시글을 좋아합니다."),
    COMMENT_ON_ARTICLE(" 님이 회원님의 게시글에 댓글을 달았습니다."),
    COMMENT_ON_COMMENT(" 님이 회원님의 댓글에 댓글을 달았습니다.");

    private String contentSuffix;

    NotificationContentType(String contentSuffix){
        this.contentSuffix = contentSuffix;
    }

    public static String makeContent(User maker, NotificationContentType type){
        return maker.getNickname() + type.contentSuffix;
    }
}
