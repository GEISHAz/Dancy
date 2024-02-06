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
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/video")
@Validated
public class VideoController {

    private final VideoService videoService;
    WebClient webClient = WebClient.create();

    @PostMapping("")
    public String requestAccuracyData(@RequestBody String accuracyData){


        try{
            String apiUrl = "http://i10d210.p.ssafy.io:5000/sendData";

            String responseBody = webClient.post()
                    .uri(apiUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(new String("Spring에서 저장한 url"))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            return responseBody;

        }
        catch(Exception e){
            return "ERROR";
        }

    }

}
