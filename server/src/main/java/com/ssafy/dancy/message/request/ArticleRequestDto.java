package com.ssafy.dancy.message.request;

import com.ssafy.dancy.entity.Video;
import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
public class ArticleRequestDto {

    String articleTitle;
    String articleContent;
    String video;
    String thumbnailImageUrl;

    @Builder
    public ArticleRequestDto() {
    }
}
