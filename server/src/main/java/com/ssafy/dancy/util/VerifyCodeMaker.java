package com.ssafy.dancy.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class VerifyCodeMaker {

    private final String charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final StringBuilder codeBuilder = new StringBuilder();
    private final Random random = new Random();

    public String makeVerifyCode(){
        codeBuilder.delete(0, codeBuilder.length());

        for(int i = 0; i < 6; i++){
            int randomIndex = random.nextInt(0, charset.length());
            char randomChar = charset.charAt(randomIndex);
            codeBuilder.append(randomChar);
        }

        return codeBuilder.toString();
    }
}
