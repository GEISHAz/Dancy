package com.ssafy.dancy.service.like;

import com.ssafy.dancy.entity.*;
import com.ssafy.dancy.exception.article.ArticleNotFoundException;
import com.ssafy.dancy.exception.comment.CommentNotFoundException;
import com.ssafy.dancy.message.response.LikeResponse;
import com.ssafy.dancy.message.response.ArticleLikeResponse;
import com.ssafy.dancy.message.response.CommentLikeResponse;
import com.ssafy.dancy.repository.ArticleRepository;
import com.ssafy.dancy.repository.CommentLikeRepository;
import com.ssafy.dancy.repository.CommentRepository;
import com.ssafy.dancy.repository.ArticleLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final ArticleLikeRepository articleLikeRepository;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;

    public List<LikeResponse> getLikeUserList(Long articleId) {
        Article article = articleRepository.findByArticleId(articleId).orElseThrow(
                () -> new ArticleNotFoundException("게시글을 찾을 수 없습니다."));

        List<ArticleLike> list = articleLikeRepository.findAllByArticle(article);

        List<LikeResponse> response = new ArrayList<>();

        for (ArticleLike ar : list) {
            response.add(LikeResponse.builder()
                    .profileImageUrl(ar.getUser().getProfileImageUrl())
                    .nickname(ar.getUser().getNickname())
                    .build());
        }

        return response;
    }

    public ArticleLikeResponse likeOrUnLikeArticle(User user, Long articleId) {
        Article article = articleRepository.findByArticleId(articleId).orElseThrow(()
                -> new ArticleNotFoundException("게시글을 찾을 수 없습니다."));

        Optional<ArticleLike> articleLike =
                articleLikeRepository.findByUserAndArticle(user, article);
        articleLike.ifPresentOrElse(articleLikeRepository::delete,
                () -> articleLikeRepository.save(ArticleLike
                        .builder()
                        .article(article)
                        .user(user)
                        .build())
        );

        boolean isLiked = articleLike.isEmpty();
        return ArticleLikeResponse.builder()
                .articleLike(article.getArticleLike())
                .isArticleLiked(isLiked)
                .build();
    }

    public CommentLikeResponse likeOrUnLikeComment(User user, Long commentId) {
        Comment comment = commentRepository.findByCommentId(commentId).orElseThrow(
                () -> new CommentNotFoundException("댓글을 찾을 수 없습니다."));

        Optional<CommentLike> commentLike = commentLikeRepository.findByUserAndComment(user, comment);

        commentLike.ifPresentOrElse(commentLikeRepository::delete,
                () -> commentLikeRepository.save(CommentLike
                        .builder()
                        .comment(comment)
                        .user(user)
                        .build()));

        boolean isLiked = commentLike.isEmpty();
        return CommentLikeResponse.builder()
                .commentLike(comment.getCommentLike())
                .isCommentLiked(isLiked)
                .build();
    }
}
