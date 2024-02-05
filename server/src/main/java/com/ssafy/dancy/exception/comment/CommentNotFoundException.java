package com.ssafy.dancy.exception.comment;

public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException(String msg){
        super(msg);
    }
}
