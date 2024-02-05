package com.ssafy.dancy.service.follow;

import com.ssafy.dancy.entity.Follow;
import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.exception.follow.FollowInfoNotFoundException;
import com.ssafy.dancy.exception.user.UserInfoNotMatchException;
import com.ssafy.dancy.exception.user.UserNotFoundException;
import com.ssafy.dancy.message.response.FollowResponse;
import com.ssafy.dancy.message.response.follow.FollowResultResponse;
import com.ssafy.dancy.repository.FollowRepository;
import com.ssafy.dancy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
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


    public FollowResultResponse follow(User user, String toNickname) {
        User toUser = userRepository.findByNickname(toNickname).orElseThrow(()
                -> new UserNotFoundException("팔로우할 유저를 찾을 수 없습니다."));

        Follow savedFollow = followRepository.save(Follow
                .builder()
                .fromUser(user)
                .toUser(toUser)
                .build());

        return FollowResultResponse.builder()
                .followedNickname(savedFollow.getToUser().getNickname())
                .followerNickname(savedFollow.getFromUser().getNickname())
                .followInfoId(savedFollow.getFollowId())
                .build();
    }

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
