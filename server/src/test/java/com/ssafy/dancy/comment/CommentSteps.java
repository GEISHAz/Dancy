package com.ssafy.dancy.comment;

import com.ssafy.dancy.message.request.CommentRequestDto;
import org.springframework.stereotype.Component;

@Component
public class CommentSteps {

    public static final String content = "fuck";
    public static final String modifiedContent = "fuck2";
    public static final Long parentId = 1L;

    public static CommentRequestDto 댓글_정보_생성(){
        return CommentRequestDto.builder()
                .content(content)
                .parentId(parentId)
                .build();
    }


}
