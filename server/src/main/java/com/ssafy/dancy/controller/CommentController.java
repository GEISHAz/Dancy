package com.ssafy.dancy.controller;


import com.ssafy.dancy.entity.Comment;
import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.message.request.CommentRequest;
import com.ssafy.dancy.message.response.comment.CommentResponse;
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
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{articleId}")
    public List<Comment> searchComment(@PathVariable Long articleId){
        return commentService.searchComment(articleId);
    }

    @PostMapping("/{articleId}")
    public CommentResponse insertComment(@AuthenticationPrincipal User user, @PathVariable Long articleId,
                                         @RequestBody CommentRequest dto){
        return commentService.insertComment(user,articleId,dto);
    }

    @PutMapping("/{commentId}")
    public CommentResponse modifyComment(@AuthenticationPrincipal User user,  @PathVariable Long commentId,
                              @RequestBody String content)  {
        return commentService.updateComment(user,commentId,content);
    }

    @DeleteMapping("/{commentId}")
    public String removeComment(@AuthenticationPrincipal User user,  @PathVariable Long commentId) {
        return commentService.deleteComment(user,commentId);
    }
}
