package com.ssafy.dancy.controller;

import com.ssafy.dancy.message.request.email.SendEmailRequest;
import com.ssafy.dancy.message.request.email.VerifyEmailRequest;
import com.ssafy.dancy.message.response.email.EmailVerifyResponse;
import com.ssafy.dancy.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/verify/send")
    public void sendJoinEmail(@Valid @RequestBody SendEmailRequest request){
        emailService.sendVerifyCode(request.targetEmail());
    }

    @PostMapping("/verify/check")
    public EmailVerifyResponse checkJoinVerifyCode(@Valid @RequestBody VerifyEmailRequest request){
        return emailService.checkJoinVerifyCode(request.targetEmail(), request.verifyCode());
    }

}
