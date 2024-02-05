package com.ssafy.dancy.repository.follow;

import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.message.response.MyPageResponse;

import java.util.Optional;

public interface FollowCustomRepository {
    Optional<MyPageResponse> findUserMyPageInfo(String nickname, User me);
}
