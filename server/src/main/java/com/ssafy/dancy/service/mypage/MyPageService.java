package com.ssafy.dancy.service.mypage;

import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.exception.user.UserNotFoundException;
import com.ssafy.dancy.message.response.MyPageResponse;
import com.ssafy.dancy.repository.FollowRepository;
import com.ssafy.dancy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyPageService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    public MyPageResponse getUserInfo(User user, String nickname) {
        User who = userRepository.findByNickname(nickname).orElseThrow(
                () -> new UserNotFoundException("해당하는 유저가 존재하지 않습니다."));
        return MyPageResponse
                .builder()
                .introduce_text(who.getIntroduceText())
                .profileImageUrl(who.getProfileImageUrl())
                .nickname(who.getNickname())
                .following(who.getFollowingCount())
                .follower(who.getFollowerCount())
                .isMine(user.equals(who))
                .followed(followRepository
                        .findAllByFromUserAndToUser(user,who)
                        .isPresent())
                .build();
    }
}
