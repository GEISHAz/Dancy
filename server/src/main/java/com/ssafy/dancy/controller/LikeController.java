package com.ssafy.dancy.controller;


import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.message.response.LikeResponse;
import com.ssafy.dancy.message.response.article.ArticleLikeResponse;
import com.ssafy.dancy.message.response.comment.CommentLikeResponse;
import com.ssafy.dancy.service.like.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;

    @GetMapping("/who-like/{articleId}")
    public List<LikeResponse> getLikeUserList(@PathVariable Long articleId){ //user token 검사 건너뜀
        return likeService.getLikeUserList(articleId);
    }

    @PostMapping("/article-like/{articleId}")
    public ArticleLikeResponse likeOrUnLikeArticle(@AuthenticationPrincipal User user, @PathVariable Long articleId){
        return likeService.likeOrUnLikeArticle(user,articleId);
    }

    @PostMapping("/comment-like/{commentId}")
    public CommentLikeResponse likeOrUnLikeComment(@AuthenticationPrincipal User user, @PathVariable Long commentId){
        return likeService.likeOrUnLikeComment(user,commentId);
    }
}
