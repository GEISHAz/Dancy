package com.ssafy.dancy.service.follow;

import com.ssafy.dancy.entity.Follow;
import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.exception.follow.CanNotFollowException;
import com.ssafy.dancy.exception.user.UserInfoNotMatchException;
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
        User fromUser = userRepository.findByNickname(nickname).orElseThrow(() -> new UserInfoNotMatchException("해당 유저가 팔로잉한 유저를 찾을 수 없습니다."));
        List<Follow> list = followRepository.findAllByFromUser(fromUser);
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
        User toUser = userRepository.findByNickname(nickname).orElseThrow(() -> new UserInfoNotMatchException("해당 유저를 팔로우한 유저를 찾을 수 없습니다."));
        List<Follow> list = followRepository.findAllByToUser(toUser);
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


    public String doFollow(User user, String toNickname) {
        User toUser = userRepository.findByNickname(toNickname).orElseThrow(() -> new UserInfoNotMatchException("팔로우할 유저를 찾을 수 없습니다."));

        try {
            followRepository.save(Follow
                    .builder()
                    .fromUser(user)
                    .toUser(toUser)
                    .build());
        } catch (CanNotFollowException e) {
            throw new CanNotFollowException("팔로우 과정중 문제가 생겼습니다.");
        }

        return "팔로우 성공";
    }

    public String doUnFollow(User user, String toNickname) {
        User toUser = userRepository.findByNickname(toNickname).orElseThrow(() -> new UserInfoNotMatchException("언팔로우할 유저를 찾을 수 없습니다."));
        try {
            followRepository.delete(Follow
                    .builder()
                    .fromUser(user)
                    .toUser(toUser)
                    .build());
        } catch (CanNotFollowException e) {
            throw new CanNotFollowException("언팔로우 과정중 문제가 생겼습니다.");
        }
        return "언팔로우 실패";
    }
}
