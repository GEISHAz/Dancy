package com.ssafy.dancy.controller;

import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.message.request.article.ArticleModifyRequest;
import com.ssafy.dancy.message.request.article.ArticleUpdateRequest;
import com.ssafy.dancy.message.response.article.ArticleDetailResponse;
import com.ssafy.dancy.message.response.article.ArticleSaveResponse;
import com.ssafy.dancy.message.response.article.ArticleSimpleResponse;
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
    public List<ArticleSimpleResponse> getAllArticle(@RequestParam int limit,
                                                     @RequestParam(required = false) Long previousArticleId){
        return articleService.getStagePage(limit, previousArticleId);
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

    @PostMapping("/save/{articleId}")
    public ArticleSaveResponse articleSave(@AuthenticationPrincipal User user, @PathVariable Long articleId){
        return articleService.saveArticleForUser(user, articleId);
    }

}
