package com.ssafy.dancy.exception.video;

public class VideoNotFoundException extends RuntimeException{
    public VideoNotFoundException(String msg){
        super(msg);
    }
}
