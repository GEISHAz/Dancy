package com.ssafy.dancy.article;

import com.ssafy.dancy.message.request.ArticleWriteRequest;
import org.springframework.stereotype.Component;

@Component
public class ArticleSteps {

    public static final String modifiedTestTitle = "test_title_modified";
    public static final String modifiedTestContent = "test_content_modified";



    public ArticleWriteRequest 게시물_생성(){

        return ArticleWriteRequest.builder()
                .articleTitle("test_title")
                .articleContent("test_content")
                .video("test_video")
                .thumbnailImageUrl("test_image")
                .build();
    }

    public ArticleWriteRequest 게시물_수정(){

        return ArticleWriteRequest.builder()
                .articleTitle(modifiedTestTitle)
                .articleContent(modifiedTestContent)
                .build();
    }
}
