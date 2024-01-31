package com.ssafy.dancy.entity;

import com.ssafy.dancy.entity.PK.ArticleHashtagPK;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(ArticleHashtagPK.class)
public class ArticleHashtag {

    @Id
    @ManyToOne
    private Article article;

    @Id
    @ManyToOne
    private Hashtag hashtag;
}
