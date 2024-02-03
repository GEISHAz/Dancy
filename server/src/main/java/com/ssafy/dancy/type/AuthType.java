package com.ssafy.dancy.type;

import com.ssafy.dancy.entity.User;

public enum AuthType {
    KAKAO, NAVER, GOOGLE, DANCY;

    public static boolean isSocialAccount(User user){
        return !user.getAuthType().equals(DANCY);
    }
}
