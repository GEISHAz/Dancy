package com.ssafy.dancy.message.response;

import lombok.Builder;

public record MyPageResponse(

    String nickname,
    String introduceText,
    String profileImageUrl,
    int following,
    int follower,
    boolean isMine,
    boolean followed
){
    @Builder
    public MyPageResponse {

    }
}
