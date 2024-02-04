package com.ssafy.dancy.entity;

import com.ssafy.dancy.entity.PK.SavedArticlePK;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SavedArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long savedArticleId;

    @ManyToOne
    private Article article;

    @ManyToOne
    private User user;

}
