package com.ssafy.dancy.email;

import com.ssafy.dancy.message.request.email.SendEmailRequest;
import org.springframework.stereotype.Component;

@Component
public class EmailSteps {

    public static final String targetEmail = "ndw8200@naver.com";
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
}
