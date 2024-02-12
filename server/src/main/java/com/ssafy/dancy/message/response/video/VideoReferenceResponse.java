package com.ssafy.dancy.message.response.video;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class VideoReferenceResponse{
    private Long videoId;
    private String videoUrl;
    private String thumbnailImageUrl;

    @QueryProjection
    public VideoReferenceResponse(Long videoId, String videoUrl, String thumbnailImageUrl){
        this.videoId = videoId;
        this.videoUrl = videoUrl;
        this.thumbnailImageUrl = thumbnailImageUrl;
    }
}
