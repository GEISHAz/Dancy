package com.ssafy.dancy.message.response;

import lombok.Builder;

public record ErrorResponse(String message, String errorType, String fieldName) {
    @Builder
    public ErrorResponse {
    }
}