package com.ssafy.dancy.service.follow;

import com.ssafy.dancy.entity.Follow;
import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.message.response.FollowResponseDto;
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

    public List<FollowResponseDto> listFollowings(String nickname) {
        Long userId = userRepository.findByNickname(nickname).get().getUserId();

        List<Follow> list = followRepository.findAllByToUser(userRepository.findByUserId(userId).get());
        List<FollowResponseDto> responseList = new ArrayList<>();
        for (Follow u : list) {
            responseList.add(FollowResponseDto
                    .builder()
                    .nickName(u.getToUser().getNickname())
                    .profileImageUrl(u.getToUser().getProfileImageUrl())
                    .build());
        }
        return responseList;
    }

    public List<FollowResponseDto> listFollowers(String nickname) {
        Long userId = userRepository.findByNickname(nickname).get().getUserId();

        List<Follow> list =  followRepository.findAllByFromUser(userRepository.findByUserId(userId).get());
        List<FollowResponseDto> responseList = new ArrayList<>();
        for (Follow u : list) {
            responseList.add(FollowResponseDto
                    .builder()
                    .nickName(u.getFromUser().getNickname())
                    .profileImageUrl(u.getFromUser().getProfileImageUrl())
                    .build());
        }
        return responseList;
    }

    public String reqFollow(User user, String nickName) {
        User toUser = userRepository.findByNickname(nickName).get();
        Follow follow = Follow
                .builder()
                .fromUser(user)
                .toUser(toUser)
                .build();
        followRepository.save(follow);
        return "Follow 성공.";
    }

    public String reqUnFollow(User user, String nickName) {
        User toUser = userRepository.findByNickname(nickName).get();
        Follow follow = Follow
                .builder()
                .fromUser(user)
                .toUser(toUser)
                .build();
        followRepository.delete(follow);
        return "UnFollow 성공.";
    }
}
