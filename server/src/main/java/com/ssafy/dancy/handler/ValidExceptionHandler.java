package com.ssafy.dancy.handler;

import com.ssafy.dancy.message.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ValidExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorResponse> processValidationError(BindException e) {

        return e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> ErrorResponse.builder()
                        .message(fieldError.getDefaultMessage())
                        .errorType(fieldError.getCode())
                        .fieldName(fieldError.getField())
                        .build())
                .toList();
    }
}
