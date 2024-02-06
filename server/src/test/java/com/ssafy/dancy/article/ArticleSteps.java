package com.ssafy.dancy.article;

import com.ssafy.dancy.message.request.article.ArticleUpdateRequest;
import org.springframework.stereotype.Component;

@Component
public class ArticleSteps {

    public static final String modifiedTestTitle = "test_title_modified";
    public static final String modifiedTestContent = "test_content_modified";
    public static final String testTitle = "test_title";
    public static final String testContent = "test_content";
    public static final String testVideo = "test_video";
    public static final String testImage = "test_image";

    public ArticleUpdateRequest 게시물_생성(String title){
        return ArticleUpdateRequest.builder()
                .articleTitle(title)
                .articleContent(testContent)
                .video(testVideo)
                .thumbnailImageUrl(testImage)
                .build();
    }

    public ArticleUpdateRequest 게시물_생성(){

        return ArticleUpdateRequest.builder()
                .articleTitle(testTitle)
                .articleContent(testContent)
                .video(testVideo)
                .thumbnailImageUrl(testImage)
                .build();
    }

    public ArticleUpdateRequest 게시물_생성_타이틀공백(){
        return ArticleUpdateRequest.builder()
                .articleTitle("")
                .articleContent(testContent)
                .video(testVideo)
                .thumbnailImageUrl(testImage)
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
