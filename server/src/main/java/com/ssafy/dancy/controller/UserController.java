package com.ssafy.dancy.controller;

import com.ssafy.dancy.message.request.user.SignUpRequest;
import com.ssafy.dancy.message.response.user.SignUpResultResponse;
import com.ssafy.dancy.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public SignUpResultResponse signup(@Valid @ModelAttribute SignUpRequest request){
       return userService.signup(request);
    }
}
