package com.ssafy.dancy.exception.verify;

public class VerifyCodeNotMatchException extends RuntimeException{
    public VerifyCodeNotMatchException(String msg){
        super(msg);
    }
}
