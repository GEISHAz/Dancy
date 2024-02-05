package com.ssafy.dancy.repository;

import com.ssafy.dancy.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleLikeRepository extends JpaRepository<ArticleLike,CommentLike> {

    List<ArticleLike> findAllByArticle(Article articleId);

    Optional<ArticleLike> findByUserAndArticle(User user,Article article);

}
