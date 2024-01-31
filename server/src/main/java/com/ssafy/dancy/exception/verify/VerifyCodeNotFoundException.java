package com.ssafy.dancy.exception.verify;

public class VerifyCodeNotFoundException extends RuntimeException{
    public VerifyCodeNotFoundException(String msg){
        super(msg);
    }
}
