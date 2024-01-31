package com.ssafy.dancy.exception.verify;

public class EmailNotVerifiedException extends RuntimeException{
    public EmailNotVerifiedException(String msg){
        super(msg);
    }
}
