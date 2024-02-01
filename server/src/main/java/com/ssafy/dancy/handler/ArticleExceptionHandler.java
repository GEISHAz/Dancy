package com.ssafy.dancy.handler;

import com.ssafy.dancy.entity.Article;
import com.ssafy.dancy.exception.article.ArticleNotFoundException;
import com.ssafy.dancy.message.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static com.ssafy.dancy.handler.ExceptionHandlerTool.makeErrorResponse;

@RestControllerAdvice
public class ArticleExceptionHandler {


    @ExceptionHandler(ArticleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public List<ErrorResponse> articleNotFoundExceptionHandler(ArticleNotFoundException e){
        return makeErrorResponse(e, "article");
    }
}
