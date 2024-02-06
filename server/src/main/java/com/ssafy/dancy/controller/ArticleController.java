package com.ssafy.dancy.controller;

import com.ssafy.dancy.entity.Article;
import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.message.request.article.ArticleModifyRequest;
import com.ssafy.dancy.message.request.article.ArticleUpdateRequest;
import com.ssafy.dancy.message.response.ArticleDetailResponse;
import com.ssafy.dancy.service.article.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/stage")
@Validated
public class ArticleController {

    private final ArticleService articleService;


    @GetMapping("")
    public List<Article> getAllArticle(){
        return articleService.getAllArticle();
    }

    @GetMapping("/{articleId}")
    public ArticleDetailResponse getArticle(@AuthenticationPrincipal User user, @PathVariable long articleId){
        return articleService.getArticle(user,articleId);
    }


    @PostMapping("")
    public ArticleDetailResponse insertArticle(@AuthenticationPrincipal User user, @Valid @RequestBody ArticleUpdateRequest dto){

        return articleService.insertArticle(user,dto);
    }

    @PutMapping("/{articleId}")
    public ArticleDetailResponse modifyArticle(@AuthenticationPrincipal User user,
                                               @Valid @RequestBody ArticleModifyRequest dto,
                                               @PathVariable long articleId){

        return articleService.modifyArticle(user,articleId, dto);
    }

    @DeleteMapping("/{articleId}")
    public Long deleteArticle(@AuthenticationPrincipal User user, @PathVariable long articleId){

        return articleService.deleteArticle(user,articleId);
    }


}
