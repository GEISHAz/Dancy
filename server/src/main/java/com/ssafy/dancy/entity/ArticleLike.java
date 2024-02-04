package com.ssafy.dancy.entity;

import com.ssafy.dancy.entity.PK.ArticleLikePK;
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

}
