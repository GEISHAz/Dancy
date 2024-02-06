package com.ssafy.dancy.controller;

import com.ssafy.dancy.entity.Article;
import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.message.request.article.ArticleModifyRequest;
import com.ssafy.dancy.message.request.article.ArticleUpdateRequest;
import com.ssafy.dancy.message.response.ArticleDetailResponse;
import com.ssafy.dancy.service.article.ArticleService;
import com.ssafy.dancy.service.video.VideoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/video")
@Validated
public class VideoController {

    private final VideoService videoService;

    @PostMapping("")
    public String sendAccuracyData(@RequestBody String accuracyData){

        return accuracyData;
    }

}
