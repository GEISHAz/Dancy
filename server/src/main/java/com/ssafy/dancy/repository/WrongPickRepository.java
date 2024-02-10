package com.ssafy.dancy.repository;

import com.ssafy.dancy.entity.WrongPick;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WrongPickRepository extends JpaRepository<WrongPick, Long> {

    List<WrongPick> findAllByVideo_VideoId(Long videoId);
}
