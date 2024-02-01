package com.ssafy.dancy.repository;

import com.ssafy.dancy.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface CommentRepository extends JpaRepository<Comment,String> {

    List<Comment> findCommentByArticle_ArticleId(Long articleId);

    Comment findByCommentId(Long commentId);


}
