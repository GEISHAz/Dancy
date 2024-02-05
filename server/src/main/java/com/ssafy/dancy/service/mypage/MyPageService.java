package com.ssafy.dancy.service.mypage;

import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.exception.user.UserNotFoundException;
import com.ssafy.dancy.message.response.MyPageResponse;
import com.ssafy.dancy.repository.follow.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyPageService {

    private final FollowRepository followRepository;

    public MyPageResponse getUserInfo(User user, String nickname) {
        return followRepository.findUserMyPageInfo(nickname, user).orElseThrow(
                () -> new UserNotFoundException("해당하는 유저가 존재하지 않습니다."));
    }
}
