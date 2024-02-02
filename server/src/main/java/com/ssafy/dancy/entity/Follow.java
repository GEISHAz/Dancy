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
@IdClass(FollowPK.class)
public class Follow {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private User fromUser;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private User toUser;

}
