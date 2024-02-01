package com.ssafy.dancy.message.request.user;

import lombok.Builder;

public record UserDeleteRequest(String password){

    @Builder
    public UserDeleteRequest{

    }
}
