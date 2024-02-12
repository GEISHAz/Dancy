package com.ssafy.dancy.repository.article;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.ssafy.dancy.repository.article.QArticleDetailDTO is a Querydsl Projection type for ArticleDetailDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QArticleDetailDTO extends ConstructorExpression<ArticleDetailDTO> {

    private static final long serialVersionUID = -440770618L;

    public QArticleDetailDTO(com.querydsl.core.types.Expression<? extends com.ssafy.dancy.entity.Article> article, com.querydsl.core.types.Expression<? extends com.ssafy.dancy.entity.ArticleLike> articleLike, com.querydsl.core.types.Expression<? extends com.ssafy.dancy.entity.SavedArticle> savedArticle) {
        super(ArticleDetailDTO.class, new Class<?>[]{com.ssafy.dancy.entity.Article.class, com.ssafy.dancy.entity.ArticleLike.class, com.ssafy.dancy.entity.SavedArticle.class}, article, articleLike, savedArticle);
    }

}

