package com.ssafy.dancy.repository;

import com.ssafy.dancy.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article,String> {

    Optional<Article> findByArticleId(long articleId);

    List<Article> findAll();


}
