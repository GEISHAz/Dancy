package com.ssafy.dancy.repository.video;

import com.ssafy.dancy.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video, Long>, VideoCustomRepository {

    Optional<Video> findByVideoId(Long videoId);

}
