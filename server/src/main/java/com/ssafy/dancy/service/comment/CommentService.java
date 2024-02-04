package com.ssafy.dancy.service.comment;

import com.ssafy.dancy.entity.Article;
import com.ssafy.dancy.entity.Comment;
import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.exception.article.ArticleNotFoundException;
import com.ssafy.dancy.message.request.CommentRequestDto;
import com.ssafy.dancy.repository.ArticleRepository;
import com.ssafy.dancy.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void updateComment(long commentId, String content) {
        Comment comment = commentRepository.findByCommentId(commentId);
        comment.setCommentContent(content);
    }


    public void deleteComment(long commentId) {
        // 지울 수 있는 권한이 있는지 확인할 것.
        commentRepository.deleteByCommentId(commentId);
    }
}
