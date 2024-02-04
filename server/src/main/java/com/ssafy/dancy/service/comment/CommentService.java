package com.ssafy.dancy.service.comment;

import com.ssafy.dancy.entity.Article;
import com.ssafy.dancy.entity.Comment;
import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.exception.user.NotHavingPermissionException;
import com.ssafy.dancy.exception.article.ArticleNotFoundException;
import com.ssafy.dancy.exception.comment.CommentNotFoundException;
import com.ssafy.dancy.message.request.CommentRequest;
import com.ssafy.dancy.message.response.comment.CommentResponse;
import com.ssafy.dancy.repository.ArticleRepository;
import com.ssafy.dancy.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public List<Comment> searchComment(long articleId) {
        return commentRepository.findCommentByArticle_ArticleId(articleId);
    }

    public CommentResponse insertComment(User user, long articleId, CommentRequest dto) {

        Article article = articleRepository.findByArticleId(articleId)
                        .orElseThrow(() -> new ArticleNotFoundException("해당 글을 찾을 수 없습니다."));

        Comment comment = Comment
                .builder()
                .commentContent(dto.content())
                .user(user)
                .article(article)
                .parentId(dto.parentId())
                .build();

        commentRepository.save(comment);

        return CommentResponse
                .builder()
                .commentId(comment.getCommentId())
                .content(comment.getCommentContent())
                .build();
    }

    @Transactional
    public CommentResponse updateComment(User user, Long commentId, String content) {
        Comment comment = commentRepository.findByCommentId(commentId).orElseThrow(
                () -> new CommentNotFoundException("존재하지 않는 댓글입니다."));

        if(!comment.getUser().equals(user)){
            throw new NotHavingPermissionException("작성자만이 수정가능합니다.");
        }else {
            comment.setCommentContent(content);
            return CommentResponse
                    .builder()
                    .commentId(commentId)
                    .content(comment.getCommentContent())
                    .build();
        }
    }


    public String deleteComment(User user,long commentId) {
        Comment comment = commentRepository.findByCommentId(commentId).orElseThrow(
                () -> new CommentNotFoundException("존재하지 않는 댓글입니다."));

        if(!comment.getUser().equals(user)){
            throw new NotHavingPermissionException("작성자만이 수정가능합니다.");
        }else {
            commentRepository.delete(comment);
            return "삭제완료";
        }
    }
}
