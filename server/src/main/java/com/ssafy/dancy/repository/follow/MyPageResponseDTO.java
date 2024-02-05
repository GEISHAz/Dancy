package com.ssafy.dancy.repository.follow;

import com.querydsl.core.annotations.QueryProjection;
import com.ssafy.dancy.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MyPageResponseDTO {
    private String nickname;
    private String introduceText;
    private String profileImageUrl;
    private int following;
    private int follower;
    private User fromUser;

    @QueryProjection
    public MyPageResponseDTO(String nickname, String introduceText, String profileImageUrl,
                             Integer following, Integer follower, User fromUser){
        this.nickname = nickname;
        this.introduceText = introduceText;
        this.profileImageUrl = profileImageUrl;
        this.following = following;
        this.follower = follower;
        this.fromUser = fromUser;
    }
}
