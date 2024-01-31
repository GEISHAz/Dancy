package com.ssafy.dancy.entity;

import com.ssafy.dancy.entity.PK.CommentLikePK;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CommentLikePK.class)
@Getter
@Setter
public class CommentLike {

    @Id
    @ManyToOne
    private Comment comment;

    @Id
    @ManyToOne
    private User user;

}
