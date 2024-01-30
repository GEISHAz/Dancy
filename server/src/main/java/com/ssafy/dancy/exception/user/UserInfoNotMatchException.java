package com.ssafy.dancy.exception.user;

public class UserInfoNotMatchException extends RuntimeException{
    public UserInfoNotMatchException(String msg){
        super(msg);
    }
}
