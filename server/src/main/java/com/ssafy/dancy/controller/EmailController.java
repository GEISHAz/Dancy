package com.ssafy.dancy.controller;

import com.ssafy.dancy.message.request.SendEmailRequest;
import com.ssafy.dancy.message.request.VerifyEmailRequest;
import com.ssafy.dancy.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

//    @PostMapping("/verify")
//    public void verifyEmail(@Valid @RequestBody VerifyEmailRequest request){
//        emailService.sendEmail(request.targetEmail(),"테스트 이메일 제목");
//    }
//
    @PostMapping("/verify/send")
    public void sendJoinEmail(@Valid @RequestBody SendEmailRequest request){
        emailService.sendVerifyCode(request.targetEmail());
    }

}
