package com.ssafy.dancy.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class ArticleLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleLikeId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Article article;

    @PrePersist
    private void preMakingArticleLike() {
        this.article.setArticleLike(this.article.getArticleLike() + 1);
    }

    @PreRemove
    private void preRemovingArticleLike() {
        this.article.setArticleLike(this.article.getArticleLike() - 1);
    }
}
