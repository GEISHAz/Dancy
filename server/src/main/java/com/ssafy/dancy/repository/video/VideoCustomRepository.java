package com.ssafy.dancy.repository.video;

import com.ssafy.dancy.message.response.video.VideoReferenceResponse;

import java.util.List;

public interface VideoCustomRepository {
    List<VideoReferenceResponse> findVideoReferenceList(int limit, Long previousVideoId);
}
