package com.ssafy.dancy.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private Article article;

    @ManyToOne
    private Hashtag hashtag;
}
