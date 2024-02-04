package com.ssafy.dancy.controller;

import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.message.response.MyPageResponse;
import com.ssafy.dancy.service.mypage.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping("/{nickname}")
    public MyPageResponse getUserInfo(@AuthenticationPrincipal User user, @PathVariable String nickname){
        return myPageService.getUserInfo(user,nickname);
    }

}
