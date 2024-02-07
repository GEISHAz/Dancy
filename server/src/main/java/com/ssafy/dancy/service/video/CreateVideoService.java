package com.ssafy.dancy.service.video;

import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.entity.Video;
import com.ssafy.dancy.exception.video.VideoNotFoundException;
import com.ssafy.dancy.message.response.video.UploadVideoResponse;
import com.ssafy.dancy.repository.VideoRepository;
import com.ssafy.dancy.util.FileStoreUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateVideoService {

    private final FileStoreUtil fileStoreUtil;
    private final VideoRepository videoRepository;

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
}
