package com.ssafy.dancy.exception.verify;

public class VerifySystemNotAuthorizedException extends RuntimeException{
    public VerifySystemNotAuthorizedException(String msg){
        super(msg);
    }
}
