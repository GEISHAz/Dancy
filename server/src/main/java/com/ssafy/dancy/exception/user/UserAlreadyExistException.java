package com.ssafy.dancy.exception.user;

public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException(String msg){
        super(msg);
    }
}
