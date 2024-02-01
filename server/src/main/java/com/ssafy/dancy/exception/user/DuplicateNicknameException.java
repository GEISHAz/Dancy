package com.ssafy.dancy.exception.user;

public class DuplicateNicknameException extends RuntimeException{
    public DuplicateNicknameException(String msg){
        super(msg);
    }
}
