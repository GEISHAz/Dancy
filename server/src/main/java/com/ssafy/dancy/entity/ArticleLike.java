package com.ssafy.dancy.entity;

import com.ssafy.dancy.entity.PK.ArticleLikePK;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@IdClass(ArticleLikePK.class)
@NoArgsConstructor
@AllArgsConstructor
public class ArticleLike {

    @Id
    @ManyToOne
    private User user;

    @Id
    @ManyToOne
    private Article article;

}
