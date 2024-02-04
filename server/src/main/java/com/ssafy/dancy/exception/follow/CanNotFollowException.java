package com.ssafy.dancy.exception.follow;

public class CanNotFollowException extends RuntimeException{
    public CanNotFollowException(String msg){
        super(msg);
    }
}
