package com.ssafy.dancy.exception.article;

public class ArticleNotOwnerException extends RuntimeException{
    public ArticleNotOwnerException(String msg){
        super(msg);
    }
}
