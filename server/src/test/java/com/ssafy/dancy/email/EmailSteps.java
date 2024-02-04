package com.ssafy.dancy.email;

import com.ssafy.dancy.message.request.email.SendEmailRequest;
import com.ssafy.dancy.message.request.email.VerifyEmailRequest;
import org.springframework.stereotype.Component;

@Component
public class EmailSteps {

    public static final String targetEmail = "ndw8200@naver.com";
    public static final String notEmail = "ndw8200";
    public static final String invalidEmail = "asdf";

    public SendEmailRequest 이메일_정보_생성(){
        return SendEmailRequest.builder()
                .targetEmail(targetEmail)
                .build();
    }

    public SendEmailRequest 이메일_헝식_아닌정보_생성(){
        return SendEmailRequest.builder()
                .targetEmail(invalidEmail)
                .build();
    }

    public VerifyEmailRequest 인증번호_정보_생성(String code){
        return VerifyEmailRequest.builder()
                .targetEmail(targetEmail)
                .verifyCode(code)
                .build();
    }

    public VerifyEmailRequest 인증번호_정보_이메일_아님(String code){
        return VerifyEmailRequest.builder()
                .targetEmail(notEmail)
                .verifyCode(code)
                .build();
    }
}
