package com.ssafy.dancy.handler;

import com.ssafy.dancy.exception.verify.*;
import com.ssafy.dancy.message.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static com.ssafy.dancy.handler.ExceptionHandlerTool.makeErrorResponse;

@RestControllerAdvice
public class VerifyExceptionHandler {

    @ExceptionHandler(VerifyCodeNotFoundException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public List<ErrorResponse> verifyCodeNotFoundExceptionHandler(VerifyCodeNotFoundException e){
        return makeErrorResponse(e, "verifyCode");
    }

    @ExceptionHandler(VerifyCodeNotMatchException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public List<ErrorResponse> verifyCodeNotMatchExceptionHandler(VerifyCodeNotMatchException e){
        return makeErrorResponse(e, "verifyCode");
    }

    @ExceptionHandler(EmailNotVerifiedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public List<ErrorResponse> emailNotVerifiedExceptionHandler(EmailNotVerifiedException e){
        return makeErrorResponse(e, "email");
    }

    @ExceptionHandler(VerifySystemBlockException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public List<ErrorResponse> verifySystemBlockExceptionHandler(VerifySystemBlockException e){
        return makeErrorResponse(e, "email");
    }

    @ExceptionHandler(VerifySystemNotAuthorizedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public List<ErrorResponse> verifySystemNotAuthorizedExceptionHandler(VerifySystemNotAuthorizedException e){
        return makeErrorResponse(e, "email");
    }
}
