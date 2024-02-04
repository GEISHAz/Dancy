package com.ssafy.dancy.controller;


import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.message.response.LikeResponsDto;
import com.ssafy.dancy.message.response.ResponseArticleLikeDto;
import com.ssafy.dancy.message.response.ResponseCommentLikeDto;
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

    @GetMapping("/who-like")
    public List<LikeResponsDto> getLikeUserList(@RequestBody Long articleId){ //user token 검사 건너뜀
        return likeService.getLikeUserList(articleId);
    }

    @PostMapping("/article-like")
    public ResponseArticleLikeDto iLikeOrUnLikeArticle(@AuthenticationPrincipal User user,@RequestBody Long articleId){
        return likeService.iLikeOrUnLikeArticle(user,articleId);
    }

    @PostMapping("/comment-like")
    public ResponseCommentLikeDto iLikeOrUnLikeComment(@AuthenticationPrincipal User user, @RequestBody Long commentId){
        return likeService.iLikeOrUnLikeComment(user,commentId);
    }
}
