package com.ssafy.dancy.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentLikeId;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
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
