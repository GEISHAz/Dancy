package com.ssafy.dancy.service.video;

import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.entity.Video;
import com.ssafy.dancy.entity.WrongPick;
import com.ssafy.dancy.exception.user.NotHavingPermissionException;
import com.ssafy.dancy.exception.video.VideoNotFoundException;
import com.ssafy.dancy.message.request.video.ConvertVideoRequest;
import com.ssafy.dancy.message.response.video.*;
import com.ssafy.dancy.repository.VideoRepository;
import com.ssafy.dancy.repository.WrongPickRepository;
import com.ssafy.dancy.type.VideoType;
import com.ssafy.dancy.util.AlarmHandler;
import com.ssafy.dancy.util.AwsS3Util;
import com.ssafy.dancy.util.FileStoreUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateVideoService {

    private final FileStoreUtil fileStoreUtil;
    private final VideoRepository videoRepository;
    private final WrongPickRepository wrongPickRepository;
    private final AwsS3Util awsS3Util;
    private final WebClient webClient;
    private final AlarmHandler alarmHandler;

    private static final String PRACTICE_VIDEO_TARGET = "video/prac";
    private static final String REFERENCE_VIDEO_TARGET = "video/gt";
    private static final String CONVERT_COMPLETE_NAME = "CONVERT_COMPLETE";

    public UploadVideoResponse uploadReferenceVideo(User user, MultipartFile file){

        String originalFilename = file.getOriginalFilename();
        String ext = fileStoreUtil.extractExt(originalFilename);
        String storeName = String.format("%s_%s_%s", getOriginalName(originalFilename, ext), "gt", user.getNickname());

        return uploadVideo(storeName, user, ext, file, REFERENCE_VIDEO_TARGET, VideoType.REFERENCE);
    }

    public UploadVideoResponse uploadPracticeVideo(User user, MultipartFile file, Long referenceVideoId){

        Video video = videoRepository.findByVideoId(referenceVideoId).orElseThrow(() ->
                new VideoNotFoundException("해당 레퍼런스 비디오를 찾을 수 없습니다."));

        String ext = fileStoreUtil.extractExt(file.getOriginalFilename());
        String uuid = UUID.randomUUID().toString();
        String[] videoNames = video.getVideoTitle().split("_");
        String storeName = String.format("%s_%s_%s_%s", videoNames[0], "prac", user.getNickname(), uuid);

        return uploadVideo(storeName, user, ext, file, PRACTICE_VIDEO_TARGET, VideoType.PRACTICE);
    }

    public ConvertVideoResponse requestConvertToFlask(User user, ConvertVideoRequest request) {

        String reference = extractNameFromUrl(request.referenceVideoUrl());
        String practice = extractNameFromUrl(request.practiceVideoUrl());

        if(!awsS3Util.hasObjectInS3(reference) || !awsS3Util.hasObjectInS3(practice)){
            throw new VideoNotFoundException("레퍼런스나 연습 비디오가 존재하지 않습니다.");
        }


//        String apiUrl = "http://i10d210.p.ssafy.io:5000/uploadVideo";
        String apiUrl = "http://localhost:5000/sendData";

        webClient.post()
                .uri(apiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(makeSimpleRequest(request))
                .retrieve()
                .bodyToMono(VideoConvertResponse.class)
                .subscribe((result) -> afterCompleteConvert(user, result));

        return ConvertVideoResponse.builder()
                .practiceVideoUrl(request.practiceVideoUrl())
                .referenceVideoUrl(request.referenceVideoUrl())
                .requestTime(LocalDateTime.now())
                .build();
    }

    public ConvertResultResponse getResultVideoInfo(User user, Long videoId){
        Video video = videoRepository.findByVideoId(videoId).orElseThrow(() ->
                new VideoNotFoundException("해당 영상을 찾을 수 없습니다."));

        if(!video.getUser().equals(user)){
            throw new NotHavingPermissionException("해당 유저의 비디오가 아닙니다.");
        }

        List<WrongPick> wrongPickList = wrongPickRepository.findAllByVideo_VideoId(videoId);

        return ConvertResultResponse.builder()
                .videoUrl(video.getFullVideoUrl())
                .score(video.getScore())
                .wrongSections(convertToWrongSection(wrongPickList))
                .build();
    }

    protected void afterCompleteConvert(User user, VideoConvertResponse response){
        log.info("변환된 영상 : {}", response.totalUrl());

        // 해당 영상은 이미 S3 에 저장되어 있음
        // 스프링부트에서 해야 할 역할은, 이를 DB 에 저장하고
        // 사용자에게 실시간으로 전달하는 일이 남았음.

        // 일단, 확실한 것은 다 끝나고 받으면 이게 실행될 수 있다는 것이다.

        Video savedVideo = videoRepository.save(Video.builder()
                .user(user)
                .score(0.755) // TODO : score 정보 넘어오면 매핑해 줄 것
                .thumbnailImageUrl(response.thumbnailImageUrl())
                .videoType(VideoType.TOTAL)
                .fullVideoUrl(response.totalUrl())
                .build());

        List<WrongPick> pickList = new ArrayList<>();

        for (VideoWrongSection section : response.wrongSections()) {
            pickList.add(WrongPick.builder()
                    .startTime(section.start())
                    .endTime(section.end())
                    .accuracy(section.accuracy())
                    .build());
        }

        wrongPickRepository.saveAll(pickList);
        alarmHandler.sendEventToUser(user.getUserId(), CONVERT_COMPLETE_NAME, savedVideo.getVideoId().toString());
    }

    private UploadVideoResponse uploadVideo(String storeName, User user, String ext, MultipartFile file,
                                            String target, VideoType videoType){

        String storeFilename = storeName + "." + ext;
        String referenceVideoUrl = fileStoreUtil.uploadVideoFileToS3(file, target, storeFilename);

        Video savedVideo = videoRepository.save(Video.builder()
                .user(user)
                .videoTitle(storeName)
                .fullVideoUrl(referenceVideoUrl)
                .videoType(videoType)
                .build());

        return wrapUpVideoUrl(savedVideo.getVideoId(), referenceVideoUrl);
    }

    private UploadVideoResponse wrapUpVideoUrl(Long videoId, String videoUrl){
        return UploadVideoResponse.builder()
                .videoId(videoId)
                .resultVideoUrl(videoUrl)
                .build();
    }

    private String getOriginalName(String originalFilename, String ext){
        return originalFilename.substring(0, originalFilename.length() - ext.length() - 1);
    }

    private ConvertVideoRequest makeSimpleRequest(ConvertVideoRequest request){
        return ConvertVideoRequest.builder()
                .practiceVideoUrl(extractSimpleUrlFromFull(request.practiceVideoUrl()))
                .referenceVideoUrl(extractSimpleUrlFromFull(request.referenceVideoUrl()))
                .build();
    }
    private String extractSimpleUrlFromFull(String fullUrl){
        int startIndex = fullUrl.indexOf("video/");
        return fullUrl.substring(startIndex);
    }

    private String extractNameFromUrl(String url){
        String[] split = url.split("/");
        return split[split.length - 1];
    }

    private List<VideoWrongSection> convertToWrongSection(List<WrongPick> list){
        List<VideoWrongSection> resultList = new ArrayList<>();

        for(WrongPick pick : list){
            resultList.add(VideoWrongSection.builder()
                            .start(pick.getStartTime())
                            .end(pick.getEndTime())
                            .accuracy(pick.getAccuracy())
                    .build());
        }
        return resultList;
    }
}
