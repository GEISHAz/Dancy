package com.ssafy.dancy.service.comment;

import com.ssafy.dancy.entity.Article;
import com.ssafy.dancy.entity.Comment;
import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.exception.user.NotHavingPermissionException;
import com.ssafy.dancy.exception.article.ArticleNotFoundException;
import com.ssafy.dancy.exception.comment.CommentNotFoundException;
import com.ssafy.dancy.message.request.comment.CommentWriteRequest;
import com.ssafy.dancy.message.response.comment.CommentResponse;
import com.ssafy.dancy.repository.article.ArticleRepository;
import com.ssafy.dancy.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public List<CommentResponse> searchComment(long articleId, long parentId) {

        List<Comment> searchedComment = commentRepository.findCommentByArticle_ArticleIdAndParentId(articleId, parentId);

        List<CommentResponse> response = new ArrayList<>();
        for(Comment comment : searchedComment){
            response.add(makeCommentResponse(comment));
        }

        return response;
    }

    public CommentResponse insertComment(User user, long articleId, CommentWriteRequest dto) {

        Article article = articleRepository.findByArticleId(articleId)
                        .orElseThrow(() -> new ArticleNotFoundException("해당 글을 찾을 수 없습니다."));

        Comment comment = Comment
                .builder()
                .commentContent(dto.content())
                .user(user)
                .article(article)
                .parentId(dto.parentId())
                .build();

        Comment savedComment = commentRepository.save(comment);

        return makeCommentResponse(savedComment);
    }

    @Transactional
    public CommentResponse updateComment(User user, Long commentId, String content) {
        Comment comment = commentRepository.findByCommentId(commentId).orElseThrow(
                () -> new CommentNotFoundException("존재하지 않는 댓글입니다."));

        if(!comment.getUser().equals(user)){
            throw new NotHavingPermissionException("작성자만이 수정가능합니다.");
        }

        comment.setCommentContent(content);
        return makeCommentResponse(comment);
    }


    public CommentResponse deleteComment(User user,long commentId) {
        Comment comment = commentRepository.findByCommentId(commentId).orElseThrow(
                () -> new CommentNotFoundException("존재하지 않는 댓글입니다."));

        if(!comment.getUser().equals(user)){
            throw new NotHavingPermissionException("작성자만이 수정가능합니다.");
        }
        commentRepository.delete(comment);

        comment.setCommentId(-1L);
        return makeCommentResponse(comment);

    }

    public CommentResponse makeCommentResponse(Comment comment){
        return CommentResponse
                .builder()
                .commentId(comment.getCommentId())
                .content(comment.getCommentContent())
                .createdDate(comment.getCreatedDate())
                .commentLike(comment.getCommentLike())
                .authorNickname(comment.getUser().getNickname())
                .authorProfileImageUrl(comment.getUser().getProfileImageUrl())
                .articleId(comment.getArticle().getArticleId())
                .parentId(comment.getParentId())
                .build();
    }
}
