package com.ssafy.dancy.service.article;

import com.ssafy.dancy.entity.Article;
import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.message.request.ArticleRequestDto;
import com.ssafy.dancy.message.response.ArticleResponseDto;
import com.ssafy.dancy.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

//    public ArticleResponseDto getArticle(User user, long articleId) {
//        Article article = articleRepository.findByArticleId(articleId);
//        return ArticleResponseDto
//                .builder()
//
//                .build();
//    }


    public List<Article> findAllArticle(){
        return articleRepository.findAll();
    }

    public Long insertArticle(User user, ArticleRequestDto dto) {
        Article article = Article.builder()
                .articleTitle(dto.getArticleTitle())
                .articleContent(dto.getArticleContent())
                .thumbnailImageUrl(dto.getThumbnailImageUrl())
                .thumbnailVideoUrl(dto.getVideo())
                .user(user)
                .build();
        articleRepository.save(article);
        return article.getArticleId();

    }


}
