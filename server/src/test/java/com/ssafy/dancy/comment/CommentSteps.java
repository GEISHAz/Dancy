package com.ssafy.dancy.comment;

import com.ssafy.dancy.message.request.comment.CommentModifyRequest;
import com.ssafy.dancy.message.request.comment.CommentWriteRequest;
import org.springframework.stereotype.Component;

@Component
public class CommentSteps {

    public static final String content = "fuck";
    public static final String modifiedContent = "modified Content";
    public static final Long parentId = 0L;

    public static CommentWriteRequest 댓글_정보_생성(){
        return CommentWriteRequest.builder()
                .content(content)
                .parentId(parentId)
                .build();
    }

    public static CommentWriteRequest 댓글_정보_부모아이디_음수(){
        return CommentWriteRequest.builder()
                .content(content)
                .parentId(-1L)
                .build();
    }

    public static CommentModifyRequest 댓글_수정_생성(){
        return CommentModifyRequest.builder()
                .content(modifiedContent)
                .build();
    }

    public static CommentModifyRequest 댓글_수정_공란_생성(){
        return CommentModifyRequest.builder()
                .content("")
                .build();
    }
}
