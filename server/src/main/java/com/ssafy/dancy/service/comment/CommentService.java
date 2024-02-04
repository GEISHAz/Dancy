package com.ssafy.dancy.service.comment;

import com.ssafy.dancy.entity.Article;
import com.ssafy.dancy.entity.Comment;
import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.exception.NoPermissionException;
import com.ssafy.dancy.exception.article.ArticleNotFoundException;
import com.ssafy.dancy.exception.comment.CommentNotFoundException;
import com.ssafy.dancy.message.request.CommentRequestDto;
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

    public void insertComment(User user, long articleId, CommentRequestDto dto) {

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
    }

    @Transactional
    public void updateComment(User user, long commentId, String content) {
        Comment comment = commentRepository.findByCommentId(commentId).orElseThrow(() -> new CommentNotFoundException("존재하지 않는 댓글입니다."));
        if(comment.getUser().equals(user)){
            comment.setCommentContent(content);
        }else{
            throw new NoPermissionException("작성자만이 수정가능합니다.");
        }
    }


    public void deleteComment(User user,long commentId) {
        Comment comment = commentRepository.findByCommentId(commentId).orElseThrow(() -> new CommentNotFoundException("존재하지 않는 댓글입니다."));
        if(comment.getUser().equals(user)){
            commentRepository.deleteByCommentId(commentId);

        }else{
            throw new NoPermissionException("작성자만이 수정가능합니다.");
        }
    }
}
