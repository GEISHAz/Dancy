package com.ssafy.dancy.controller;


import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.message.request.comment.CommentModifyRequest;
import com.ssafy.dancy.message.request.comment.CommentWriteRequest;
import com.ssafy.dancy.message.response.comment.CommentResponse;
import com.ssafy.dancy.service.comment.CommentService;
import jakarta.validation.Valid;
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
    public List<CommentResponse> searchComment(@PathVariable Long articleId){
        return commentService.searchComment(articleId);
    }

    @PostMapping("/{articleId}")
    public CommentResponse insertComment(@AuthenticationPrincipal User user, @PathVariable Long articleId,
                                         @Valid @RequestBody CommentWriteRequest dto){
        return commentService.insertComment(user,articleId,dto);
    }

    @PutMapping("/{commentId}")
    public CommentResponse modifyComment(@AuthenticationPrincipal User user, @PathVariable Long commentId,
                                         @Valid @RequestBody CommentModifyRequest request)  {
        return commentService.updateComment(user,commentId, request.content());
    }

    @DeleteMapping("/{commentId}")
    public CommentResponse removeComment(@AuthenticationPrincipal User user,  @PathVariable Long commentId) {
        return commentService.deleteComment(user,commentId);
    }
}
