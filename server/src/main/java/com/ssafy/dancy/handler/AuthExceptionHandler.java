package com.ssafy.dancy.handler;

import com.ssafy.dancy.exception.user.UserAlreadyExistException;
import com.ssafy.dancy.exception.user.UserInfoNotMatchException;
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
}
