package com.ssafy.dancy.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentLikeId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Comment comment;



    @PrePersist
    private void preMakingCommentLike() {
        this.comment.setCommentLike(this.comment.getCommentLike() + 1);
    }

    @PreRemove
    private void preRemovingCommentLike() {
        this.comment.setCommentLike(this.comment.getCommentLike() - 1);
    }
}
