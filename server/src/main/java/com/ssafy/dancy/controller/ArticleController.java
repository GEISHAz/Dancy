package com.ssafy.dancy.controller;

import com.ssafy.dancy.entity.Article;
import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.message.request.ArticleRequestDto;
import com.ssafy.dancy.message.response.ArticleResponseDto;
import com.ssafy.dancy.service.article.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/stage")
public class ArticleController {

    private final ArticleService articleService;

//    @GetMapping("/{articleId}")
//    public ArticleResponseDto getArticle(@AuthenticationPrincipal User user, @PathVariable long articleId){
//        return articleService.getArticle(user,articleId);
//    }

    @GetMapping("")
    public List<Article> findAllArticle(){
        log.info("전체 게시물 조회 : {}", articleService.findAllArticle());
        return articleService.findAllArticle();
    }

    @PostMapping
    public Long insertArticle(@AuthenticationPrincipal User user, @RequestBody ArticleRequestDto dto){
        return articleService.insertArticle(user,dto);
    }
}
