package com.ssafy.dancy.service.follow;

import com.ssafy.dancy.entity.Follow;
import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.exception.follow.FollowInfoNotFoundException;
import com.ssafy.dancy.exception.user.UserNotFoundException;
import com.ssafy.dancy.message.response.FollowResponse;
import com.ssafy.dancy.message.response.follow.FollowResultResponse;
import com.ssafy.dancy.repository.follow.FollowRepository;
import com.ssafy.dancy.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public List<FollowResponse> listFollowings(String nickname) {
        User fromUser = userRepository.findByNickname(nickname).orElseThrow(
                () -> new UserNotFoundException("해당 유저를 찾을 수 없습니다."));

        List<Follow> list = followRepository.findAllByFromUser(fromUser);

        List<FollowResponse> responseList = new ArrayList<>();

        for (Follow u : list) {
            responseList.add(FollowResponse
                    .builder()
                    .nickname(u.getToUser().getNickname())
                    .profileImageUrl(u.getToUser().getProfileImageUrl())
                    .build());
        }

        return responseList;
    }

    public List<FollowResponse> listFollowers(String nickname) {
        User toUser = userRepository.findByNickname(nickname).orElseThrow(
                () -> new UserNotFoundException("해당 유저를 찾을 수 없습니다."));

        List<Follow> list = followRepository.findAllByToUser(toUser);

        List<FollowResponse> responseList = new ArrayList<>();
        for (Follow u : list) {
            responseList.add(FollowResponse
                    .builder()
                    .nickname(u.getToUser().getNickname())
                    .profileImageUrl(u.getToUser().getProfileImageUrl())
                    .build());
        }

        return responseList;
    }


    @Transactional
    public FollowResultResponse follow(User user, String toNickname) {
        User toUser = userRepository.findByNickname(toNickname).orElseThrow(()
                -> new UserNotFoundException("팔로우할 유저를 찾을 수 없습니다."));

        log.info("보내는 유저 : {}", user);
        log.info("보내는 유저 닉네임 : {}", user.getNickname());
        log.info("팔로우받는 유저 닉네임 : {}",toNickname);
        Follow savedFollow = followRepository.save(Follow
                .builder()
                .fromUser(user)
                .toUser(toUser)
                .build());

        userRepository.save(user);
        return FollowResultResponse.builder()
                .followedNickname(toNickname)
                .followerNickname(user.getNickname())
                .followInfoId(savedFollow.getFollowId())
                .build();
    }

    @Transactional
    public FollowResultResponse unFollow(User user, String toNickname) {

        Follow followInfo = followRepository.findByFromUserAndToUser_Nickname(user, toNickname).orElseThrow(
                () -> new FollowInfoNotFoundException("팔로우한 정보를 찾을 수 없습니다."));

        followRepository.delete(followInfo);

        return FollowResultResponse.builder()
                .followInfoId(0L)
                .followerNickname(followInfo.getFromUser().getNickname())
                .followedNickname(followInfo.getToUser().getNickname())
                .build();
    }
}
