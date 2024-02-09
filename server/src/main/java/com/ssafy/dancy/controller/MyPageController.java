package com.ssafy.dancy.controller;

import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.message.response.MyPageResponse;
import com.ssafy.dancy.message.response.article.ArticleSimpleResponse;
import com.ssafy.dancy.service.article.ArticleService;
import com.ssafy.dancy.service.mypage.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;
    private final ArticleService articleService;

    @GetMapping("/{nickname}")
    public MyPageResponse getUserInfo(@AuthenticationPrincipal User user, @PathVariable String nickname){
        return myPageService.getUserInfo(user,nickname);
    }

    @GetMapping("/article/{nickname}")
    public List<ArticleSimpleResponse> getArticleOfTarget(@PathVariable String nickname,
                                                          @RequestParam Integer limit,
                                                          @RequestParam(required = false) Long previousArticleId){

        return articleService.getArticleOfPerson(nickname, limit, previousArticleId);
    }

    @GetMapping("/keep/{nickname}")
    public List<ArticleSimpleResponse> getSavedArticles(@PathVariable String nickname,
                                                        @RequestParam Integer limit,
                                                        @RequestParam(required = false) Long previousArticleId){

        return articleService.getSavedArticleOfUser(nickname, limit, previousArticleId);
    }


}
