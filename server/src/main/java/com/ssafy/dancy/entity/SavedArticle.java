package com.ssafy.dancy.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Article article;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

}
