package com.ssafy.dancy.repository.video;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.dancy.exception.video.LastVideoException;
import com.ssafy.dancy.message.response.video.VideoReferenceResponse;
import com.ssafy.dancy.type.VideoType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.dancy.entity.QVideo.video;

@Repository
@RequiredArgsConstructor
public class VideoCustomRepositoryImpl implements VideoCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<VideoReferenceResponse> findVideoReferenceList(int limit, Long previousVideoId) {
        JPAQuery<VideoReferenceResponse> query = jpaQueryFactory.select(
                        Projections.bean(
                                VideoReferenceResponse.class,
                                video.videoId.as("videoId"),
                                video.fullVideoUrl.as("videoUrl"),
                                video.thumbnailImageUrl.as("thumbnailImageUrl")
                        )
                )
                .from(video);

        BooleanExpression expression = video.videoType.eq(VideoType.REFERENCE);

        if(previousVideoId != null){
            expression = expression.and(video.videoId.lt(previousVideoId));
        }

        List<VideoReferenceResponse> resultVideoList = query.where(expression)
                .orderBy(video.videoId.desc())
                .limit(limit)
                .fetch();

        if(resultVideoList.isEmpty()){
            throw new LastVideoException("마지막 비디오입니다.");
        }

        return resultVideoList;
    }
}
