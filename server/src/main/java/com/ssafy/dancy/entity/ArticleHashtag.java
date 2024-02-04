package com.ssafy.dancy.entity;

import com.ssafy.dancy.entity.PK.ArticleHashtagPK;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleHashtagId;

    @ManyToOne
    private Article article;

    @ManyToOne
    private Hashtag hashtag;
}
