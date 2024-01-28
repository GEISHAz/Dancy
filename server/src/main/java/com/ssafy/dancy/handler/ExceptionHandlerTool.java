package com.ssafy.dancy.handler;

import com.ssafy.dancy.message.response.ErrorResponse;

import java.util.List;

public class ExceptionHandlerTool {
    public static List<ErrorResponse> makeErrorResponse(Exception e, String fieldName) {
        return List.of(ErrorResponse.builder()
                .message(e.getMessage())
                .errorType(e.getClass().getSimpleName())
                .fieldName(fieldName)
                .build());
    }
}
