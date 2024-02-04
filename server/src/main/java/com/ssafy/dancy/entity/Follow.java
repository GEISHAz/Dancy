package com.ssafy.dancy.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;

    @ManyToOne(fetch = FetchType.LAZY)
    private User fromUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private User toUser;

    @PrePersist
    private void preMakingFollow() {
        this.fromUser.setFollowerCount(this.fromUser.getFollowerCount() + 1);
        this.toUser.setFollowerCount(this.toUser.getFollowerCount() + 1);
    }

    @PreRemove
    private void preRemovingFollow() {
        this.fromUser.setFollowerCount(this.fromUser.getFollowerCount() - 1);
        this.toUser.setFollowerCount(this.toUser.getFollowerCount() - 1);
    }

}
