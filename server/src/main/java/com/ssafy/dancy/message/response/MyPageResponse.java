package com.ssafy.dancy.message.response;

import lombok.Builder;

public record MyPageResponse(

    String nickname,
    String introduce_text,
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
