package com.ssafy.dancy.controller;

import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.message.request.video.ConvertVideoRequest;
import com.ssafy.dancy.message.request.video.PracticeVideoUploadRequest;
import com.ssafy.dancy.message.request.video.ReferenceVideoUploadRequest;
import com.ssafy.dancy.message.response.video.ConvertResultResponse;
import com.ssafy.dancy.message.response.video.ConvertVideoResponse;
import com.ssafy.dancy.message.response.video.VideoReferenceResponse;
import com.ssafy.dancy.message.response.video.UploadVideoResponse;
import com.ssafy.dancy.service.video.CreateVideoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/video")
public class VideoController {

    private final CreateVideoService videoService;

    @GetMapping("")
    public List<VideoReferenceResponse> getReferenceVideoList(@RequestParam int limit,
                                                              @RequestParam(required = false) Long previousVideoId){
        return videoService.getReferenceVideoList(limit, previousVideoId);
    }

    @PostMapping(value = "/upload/reference", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UploadVideoResponse uploadReferenceVideo(@AuthenticationPrincipal User user,
                                                    @Valid @ModelAttribute ReferenceVideoUploadRequest request){

        return videoService.uploadReferenceVideo(user, request.videoFile());
    }

    @PostMapping(value = "/upload/practice", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UploadVideoResponse uploadDanceVideo(@AuthenticationPrincipal User user,
                                                @Valid @ModelAttribute PracticeVideoUploadRequest request){

        return videoService.uploadPracticeVideo(user, request.videoFile(), request.referenceVideoId());
    }

    @PostMapping("/analyze")
    public ConvertVideoResponse convertTwoVideo(@AuthenticationPrincipal User user,
                                                @Valid @RequestBody ConvertVideoRequest request){

        return videoService.requestConvertToFlask(user, request);
    }

    @GetMapping("/after/{videoId}")
    public ConvertResultResponse convertResult(@AuthenticationPrincipal User user, @PathVariable Long videoId){
        return videoService.getResultVideoInfo(user, videoId);
    }
}
