package com.ssafy.dancy.type;

import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.exception.user.SocialAccountException;

public enum AuthType {
    KAKAO, NAVER, GOOGLE, DANCY;

    public static void checkSocialAccount(User user){
        if(!user.getAuthType().equals(DANCY)){
            throw new SocialAccountException("소셜 로그인 계정입니다.");
        }
    }
}
