package com.ssafy.dancy.handler;

import com.ssafy.dancy.exception.video.LastVideoException;
import com.ssafy.dancy.exception.video.VideoNotConvertedException;
import com.ssafy.dancy.exception.video.VideoNotFoundException;
import com.ssafy.dancy.message.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static com.ssafy.dancy.handler.ExceptionHandlerTool.makeErrorResponse;

@RestControllerAdvice
public class VideoExceptionHandler {

    @ExceptionHandler(VideoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public List<ErrorResponse> videoNotFoundExceptionHandler(VideoNotFoundException e){
        return makeErrorResponse(e, "videoId");
    }

    @ExceptionHandler(LastVideoException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public List<ErrorResponse> lastVideoExceptionHandler(LastVideoException e){
        return makeErrorResponse(e, "previousVideoId");
    }

    @ExceptionHandler(VideoNotConvertedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public List<ErrorResponse> videoNotConvertedExceptionHandler(VideoNotConvertedException e){
        return makeErrorResponse(e, "video");
    }
}
