package com.ssafy.dancy.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ArticleHashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long articleHashtagId;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Article article;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Hashtag hashtag;
}
