package com.ssafy.dancy.repository;

import com.ssafy.dancy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {


}
