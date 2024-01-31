package com.ssafy.dancy.entity;

import com.ssafy.dancy.entity.PK.FollowPK;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
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
    @ManyToOne
    private User fromUser;

    @Id
    @ManyToOne
    private User toUser;

}
