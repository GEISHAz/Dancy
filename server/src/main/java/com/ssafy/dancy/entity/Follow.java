package com.ssafy.dancy.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long followId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User fromUser;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User toUser;
    // FetchType.LAZY 를 걸었을 경우, n + 1 문제가 발생하는 케이스가 존재.

    @PrePersist
    private void preMakingFollow() {
        this.fromUser.setFollowingCount(this.fromUser.getFollowingCount() + 1);
        this.toUser.setFollowerCount(this.toUser.getFollowerCount() + 1);
    }

    @PreRemove
    private void preRemovingFollow() {
        this.fromUser.setFollowingCount(this.fromUser.getFollowingCount() - 1);
        this.toUser.setFollowerCount(this.toUser.getFollowerCount() - 1);
    }

}
