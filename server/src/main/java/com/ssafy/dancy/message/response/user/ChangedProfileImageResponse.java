package com.ssafy.dancy.message.response.user;

import lombok.Builder;

public record ChangedProfileImageResponse (
        String email,
        String profileImageUrl
){
    @Builder
    public ChangedProfileImageResponse{

    }
}
