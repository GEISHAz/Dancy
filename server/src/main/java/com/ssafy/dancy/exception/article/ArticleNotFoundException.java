package com.ssafy.dancy.exception.article;

import com.ssafy.dancy.entity.Article;

public class ArticleNotFoundException extends RuntimeException{
    public ArticleNotFoundException(String msg){
        super(msg);
    }
}
