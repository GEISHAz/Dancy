package com.ssafy.dancy.repository;

import com.ssafy.dancy.entity.SavedArticle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleSaveRepository extends JpaRepository<SavedArticle, Long> {
    List<SavedArticle> findAllByUser_Nickname(String nickname);

}
