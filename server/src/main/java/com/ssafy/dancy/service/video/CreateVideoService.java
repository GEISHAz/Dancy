package com.ssafy.dancy.service.video;

import com.amazonaws.services.s3.AmazonS3;
import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.entity.Video;
import com.ssafy.dancy.exception.video.VideoNotFoundException;
import com.ssafy.dancy.message.request.video.ConvertVideoRequest;
import com.ssafy.dancy.message.response.video.ConvertVideoResponse;
import com.ssafy.dancy.message.response.video.UploadVideoResponse;
import com.ssafy.dancy.repository.VideoRepository;
import com.ssafy.dancy.type.ConvertStatus;
import com.ssafy.dancy.util.AwsS3Util;
import com.ssafy.dancy.util.FileStoreUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateVideoService {

    private final FileStoreUtil fileStoreUtil;
    private final VideoRepository videoRepository;
    private final AwsS3Util awsS3Util;
    private final WebClient webClient;

    private static final String PRACTICE_VIDEO_TARGET = "video/prac";
    private static final String REFERENCE_VIDEO_TARGET = "video/gt";

    public UploadVideoResponse uploadReferenceVideo(User user, MultipartFile file){

        String originalFilename = file.getOriginalFilename();
        String ext = fileStoreUtil.extractExt(originalFilename);
        String storeName = String.format("%s_%s_%s", getOriginalName(originalFilename, ext), "gt", user.getNickname());

        return uploadVideo(storeName, ext, file, REFERENCE_VIDEO_TARGET);
    }

    public UploadVideoResponse uploadPracticeVideo(User user, MultipartFile file, Long referenceVideoId){

        Video video = videoRepository.findByVideoId(referenceVideoId).orElseThrow(() ->
                new VideoNotFoundException("해당 레퍼런스 비디오를 찾을 수 없습니다."));

        String ext = fileStoreUtil.extractExt(file.getOriginalFilename());
        String uuid = UUID.randomUUID().toString();
        String[] videoNames = video.getVideoTitle().split("_");
        String storeName = String.format("%s_%s_%s_%s", videoNames[0], "prac", user.getNickname(), uuid);

        return uploadVideo(storeName, ext, file, PRACTICE_VIDEO_TARGET);
    }

    private UploadVideoResponse uploadVideo(String storeName, String ext, MultipartFile file, String target){

        String storeFilename = storeName + "." + ext;
        String referenceVideoUrl = fileStoreUtil.uploadVideoFileToS3(file, target, storeFilename);

        Video savedVideo = videoRepository.save(Video.builder()
                .videoTitle(storeName)
                .fullVideoUrl(referenceVideoUrl)
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

    public ConvertVideoResponse requestConvertToFlask(ConvertVideoRequest request) {

        String reference = extractNameFromUrl(request.referenceVideoUrl());
        String practice = extractNameFromUrl(request.practiceVideoUrl());

        if(!awsS3Util.hasObjectInS3(reference) || !awsS3Util.hasObjectInS3(practice)){
            throw new VideoNotFoundException("레퍼런스나 연습 비디오가 존재하지 않습니다.");
        }


//        String apiUrl = "http://i10d210.p.ssafy.io:5000/sendData";
        String apiUrl = "http://localhost:5000/sendData";

        webClient.post()
                .uri(apiUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(s -> log.info("받아온 데이터 : {}", s));


        return ConvertVideoResponse.builder()
                .videoStatusId(2L)
                .practiceVideoUrl(request.practiceVideoUrl())
                .referenceVideoUrl(request.referenceVideoUrl())
                .status(ConvertStatus.REQUESTING.toString())
                .build();
    }

    private String extractNameFromUrl(String url){
        String[] split = url.split("/");
        return split[split.length - 1];
    }
}
