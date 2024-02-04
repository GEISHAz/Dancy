package com.ssafy.dancy.repository;

import com.ssafy.dancy.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike,String> {

    Optional<CommentLike> findByUserAndComment(User user, Comment comment);

}
