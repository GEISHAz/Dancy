package com.ssafy.dancy.repository;

import com.ssafy.dancy.entity.SavedArticle;
import com.ssafy.dancy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleSaveRepository extends JpaRepository<SavedArticle, Long> {
    List<SavedArticle> findAllByUser_Nickname(String nickname);
    Optional<SavedArticle> findByArticle_ArticleIdAndUser(Long articleId, User user);

}
