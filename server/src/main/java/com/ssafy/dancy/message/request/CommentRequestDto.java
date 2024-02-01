package com.ssafy.dancy.message.request;

import com.ssafy.dancy.entity.Comment;
import com.ssafy.dancy.entity.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Component;


public record CommentRequestDto(
        String content,
        Long parentId
) {


    @Builder
    public CommentRequestDto {

    }
}
