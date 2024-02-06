package com.ssafy.dancy.repository.article;

import com.querydsl.core.annotations.QueryProjection;
import com.ssafy.dancy.entity.Article;
import com.ssafy.dancy.entity.ArticleLike;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@Getter
public class ArticleDetailDTO {
    private Article article;
    private ArticleLike articleLike;

    @QueryProjection
    public ArticleDetailDTO(Article article, ArticleLike articleLike){
        this.article = article;
        this.articleLike = articleLike;
    }
}
