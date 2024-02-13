package com.ssafy.dancy.util;

import com.ssafy.dancy.entity.Article;
import com.ssafy.dancy.repository.article.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class ViewCountAspect {

    private final ArticleRepository articleRepository;

    @AfterReturning("execution(public * com.ssafy.dancy.service.article.ArticleService.getArticle(..))")
    public void afterGettingArticle(JoinPoint joinPoint){
        Long articleId = (Long)joinPoint.getArgs()[1];
        updateViewCount(articleId);
    }

    @Async
    protected void updateViewCount(Long articleId){
        Article article = articleRepository.findByArticleId(articleId).get();
        Long view = article.getView();
        article.setView(view + 1);
        articleRepository.save(article);
    }
}
