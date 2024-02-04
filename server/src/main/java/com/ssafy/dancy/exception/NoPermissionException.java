package com.ssafy.dancy.exception;

public class NoPermissionException extends RuntimeException{
    public NoPermissionException(String msg){
        super(msg);
    }
}