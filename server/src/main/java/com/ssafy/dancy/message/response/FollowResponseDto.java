package com.ssafy.dancy.message.response;

import lombok.Builder;

public record FollowResponseDto(

    String profileImageUrl,
    String nickName
){
    @Builder
    public FollowResponseDto {
    }
}
