package com.ssafy.dancy.exception.user;

public class NotHavingPermissionException extends RuntimeException{
    public NotHavingPermissionException(String msg){
        super(msg);
    }
}