package com.ssafy.dancy.handler;

import com.ssafy.dancy.exception.user.DuplicateNicknameException;
import com.ssafy.dancy.exception.user.NotHavingPermissionException;
import com.ssafy.dancy.message.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static com.ssafy.dancy.handler.ExceptionHandlerTool.makeErrorResponse;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(DuplicateNicknameException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public List<ErrorResponse> duplicateNicknameExceptionHandler(DuplicateNicknameException e){
        return makeErrorResponse(e, "nickname");
    }

    @ExceptionHandler(NotHavingPermissionException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public List<ErrorResponse> notHavingPermissionExceptionHandler(NotHavingPermissionException e){
        return makeErrorResponse(e, "email");
    }
}
