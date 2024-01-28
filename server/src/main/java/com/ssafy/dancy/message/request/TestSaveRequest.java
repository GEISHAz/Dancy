package com.ssafy.dancy.message.request;

import lombok.Builder;

public record TestSaveRequest(
        String name,
        String address) {

    @Builder
    public TestSaveRequest{

    }
}
