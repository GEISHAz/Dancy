package com.ssafy.dancy.repository;

import com.ssafy.dancy.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article,String> {


    List<Article> findAll();

    Optional<Article> findByArticleId(long articleId);


}
