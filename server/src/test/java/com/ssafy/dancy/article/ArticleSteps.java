package com.ssafy.dancy.article;

import com.ssafy.dancy.message.request.ArticleUpdateRequest;
import org.springframework.stereotype.Component;

@Component
public class ArticleSteps {

    public static final String modifiedTestTitle = "test_title_modified";
    public static final String modifiedTestContent = "test_content_modified";



    public ArticleUpdateRequest 게시물_생성(){

        return ArticleUpdateRequest.builder()
                .articleTitle("test_title")
                .articleContent("test_content")
                .video("test_video")
                .thumbnailImageUrl("test_image")
                .build();
    }

    public ArticleUpdateRequest 게시물_생성_타이틀공백(){
        return ArticleUpdateRequest.builder()
                .articleTitle("")
                .articleContent("test_content")
                .video("test_video")
                .thumbnailImageUrl("test_image")
                .build();
    }

    public ArticleUpdateRequest 게시물_수정(){

        return ArticleUpdateRequest.builder()
                .articleTitle(modifiedTestTitle)
                .articleContent(modifiedTestContent)
                .build();
    }

    public ArticleUpdateRequest 게시물_수정_타이틀공백(){
        return ArticleUpdateRequest.builder()
                .articleTitle("")
                .articleContent(modifiedTestContent)
                .build();
    }
}
