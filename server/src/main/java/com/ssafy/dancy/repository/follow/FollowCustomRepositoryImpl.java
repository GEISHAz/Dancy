package com.ssafy.dancy.repository.follow;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.message.response.MyPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.ssafy.dancy.entity.QFollow.follow;
import static com.ssafy.dancy.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class FollowCustomRepositoryImpl implements FollowCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<MyPageResponse> findUserMyPageInfo(String nickname, User me) {

        MyPageResponseDTO myPageResponseDTO = jpaQueryFactory.select(
                        Projections.bean(
                                MyPageResponseDTO.class,
                                user.nickname.as("nickname"),
                                user.introduceText.as("introduceText"),
                                user.profileImageUrl.as("profileImageUrl"),
                                user.followingCount.as("following"),
                                user.followerCount.as("follower"),
                                follow.fromUser.as("fromUser")
                        )
                )
                .from(user)
                .leftJoin(follow).on(user.userId.eq(follow.toUser.userId)
                        .and(follow.fromUser.eq(me)))
                .where(user.nickname.eq(nickname))
                .fetchOne();


        return convertToResponse(myPageResponseDTO, me);
    }

    private Optional<MyPageResponse> convertToResponse(MyPageResponseDTO dto, User user){
        if(dto == null){
            return Optional.empty();
        }

        MyPageResponse response = MyPageResponse.builder()
                .nickname(dto.getNickname())
                .introduceText(dto.getIntroduceText())
                .profileImageUrl(dto.getProfileImageUrl())
                .following(dto.getFollowing())
                .follower(dto.getFollower())
                .isMine(dto.getNickname().equals(user.getNickname()))
                .followed(dto.getFromUser() != null)
                .build();

        return Optional.of(response);
    }
}
