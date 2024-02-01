package com.ssafy.dancy.controller;


import com.ssafy.dancy.entity.Comment;
import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.message.request.CommentRequestDto;
import com.ssafy.dancy.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentControlloer {

    private final CommentService commentService;

    @GetMapping("/{articleId}")
    public List<Comment> searchComment(@PathVariable Long articleId){
        return commentService.searchComment(articleId);
    }

    @PostMapping("/{articleId}")
    public void insertComment(@AuthenticationPrincipal User user, @PathVariable Long articleId,
                             @RequestBody CommentRequestDto dto){
        commentService.insertComment(user,articleId,dto);
    }

    @PutMapping("/{commentId}")
    public void modifyComment(@AuthenticationPrincipal User user,  @PathVariable Long commentId,
                              @RequestBody String content) {
        commentService.updateComment(commentId,content);
    }

    @DeleteMapping("/{commentId}")
    public void removeComment(@AuthenticationPrincipal User user,  @PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}
