package com.ssafy.dancy.handler;

import com.ssafy.dancy.exception.user.*;
import com.ssafy.dancy.message.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static com.ssafy.dancy.handler.ExceptionHandlerTool.makeErrorResponse;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public List<ErrorResponse> userAlreadyExistExceptionHandler(UserAlreadyExistException e){
        return makeErrorResponse(e, "email");
    }

    @ExceptionHandler(UserInfoNotMatchException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public List<ErrorResponse> userInfoNotMatchExceptionHandler(UserInfoNotMatchException e){
        return makeErrorResponse(e, "email");
    }

    @ExceptionHandler(UserPasswordNotMatchException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public List<ErrorResponse> userPasswordNotMatchException(UserPasswordNotMatchException e){
        return makeErrorResponse(e, "currentPassword");
    }

    @ExceptionHandler(SocialAccountException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public List<ErrorResponse> socialAccountExceptionException(SocialAccountException e){
        return makeErrorResponse(e, "email");
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public List<ErrorResponse> userNotFoundExceptionHandler(UserNotFoundException e){
        return makeErrorResponse(e, "user");
    }
}
