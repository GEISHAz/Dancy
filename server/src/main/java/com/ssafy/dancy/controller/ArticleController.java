package com.ssafy.dancy.controller;

import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.message.request.ArticleRequestDto;
import com.ssafy.dancy.message.response.ArticleResponseDto;
import com.ssafy.dancy.service.article.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public Long writeArticle(@AuthenticationPrincipal User user, @RequestBody ArticleRequestDto dto){
        return articleService.insertArticle(user,dto);
    }
}
