package com.ssafy.dancy.entity;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long savedArticleId;

    @ManyToOne
    private Article article;

    @ManyToOne
    private User user;

}
