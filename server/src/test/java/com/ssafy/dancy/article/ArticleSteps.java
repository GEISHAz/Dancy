package com.ssafy.dancy.article;

import com.ssafy.dancy.message.request.article.ArticleModifyRequest;
import com.ssafy.dancy.message.request.article.ArticleWriteRequest;
import org.springframework.stereotype.Component;

@Component
public class ArticleSteps {

    public static final String modifiedTestTitle = "test_title_modified";
    public static final String modifiedTestContent = "test_content_modified";
    public static final String testTitle = "test_title";
    public static final String testContent = "test_content";
    public static final String testVideo = "test_video";
    public static final String testImage = "test_image";

    public ArticleWriteRequest 게시물_생성(String title, Long videoId){
        return ArticleWriteRequest.builder()
                .articleTitle(title)
                .articleContent(testContent)
                .videoId(videoId)
                .build();
    }

    public ArticleWriteRequest 게시물_생성(Long videoId){

        return ArticleWriteRequest.builder()
                .articleTitle(testTitle)
                .articleContent(testContent)
                .videoId(videoId)
                .build();
    }

    public ArticleWriteRequest 게시물_생성_타이틀공백(Long videoId){
        return ArticleWriteRequest.builder()
                .articleTitle("")
                .articleContent(testContent)
                .videoId(videoId)
                .build();
    }

    public ArticleModifyRequest 게시물_수정(){

        return ArticleModifyRequest.builder()
                .articleTitle(modifiedTestTitle)
                .articleContent(modifiedTestContent)
                .build();
    }

    public ArticleModifyRequest 게시물_수정_타이틀공백(){
        return ArticleModifyRequest.builder()
                .articleTitle("")
                .articleContent(modifiedTestContent)
                .build();
    }
}
