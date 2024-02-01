package com.ssafy.dancy.controller;

import com.ssafy.dancy.message.request.user.SignUpRequest;
import com.ssafy.dancy.message.response.user.SignUpResultResponse;
import com.ssafy.dancy.service.user.UserService;
import com.ssafy.dancy.type.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public SignUpResultResponse signup(@Valid @ModelAttribute SignUpRequest request){
       return userService.signup(request, Set.of(Role.USER));
    }

    @GetMapping("/exists/{nickname}")
    public void checkDuplicateNickname(@Size(min = 1, max = 15, message = "닉네임은 1자 이상 15자 미만으로 입력해 주세요.")
                                       @PathVariable String nickname){

        userService.checkDuplicateNickname(nickname);
    }
}
