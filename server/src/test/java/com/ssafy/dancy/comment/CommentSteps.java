package com.ssafy.dancy.comment;

import com.ssafy.dancy.message.request.comment.CommentRequest;
import org.springframework.stereotype.Component;

@Component
public class CommentSteps {

    public static final String content = "fuck";
    public static final String modifiedContent = "fuck2";
    public static final Long parentId = 1L;

    public static CommentRequest 댓글_정보_생성(){
        return CommentRequest.builder()
                .content(content)
                .parentId(parentId)
                .build();
    }


}
