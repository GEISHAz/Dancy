package com.ssafy.dancy.entity;

import com.ssafy.dancy.entity.PK.FollowPK;
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

    @ManyToOne
    private User fromUser;

    @ManyToOne
    private User toUser;

}
