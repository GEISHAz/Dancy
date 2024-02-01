package com.ssafy.dancy.exception.user;

public class UserPasswordNotMatchException extends RuntimeException{
    public UserPasswordNotMatchException(String msg){
        super(msg);
    }
}
