package com.ssafy.dancy.message.response;

import lombok.Builder;

public record LikeResponsDto(
    String profileImageUrl,
    String nickname
) {
    @Builder
    public LikeResponsDto {
    }
}
