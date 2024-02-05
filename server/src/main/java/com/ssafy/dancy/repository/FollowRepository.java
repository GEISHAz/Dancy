package com.ssafy.dancy.repository;

import com.ssafy.dancy.entity.Follow;
import com.ssafy.dancy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow,String> {
    List<Follow> findAllByFromUser(User fromUser);
    List<Follow> findAllByToUser(User toUser);
}
