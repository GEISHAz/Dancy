package com.ssafy.dancy.service.article;

import com.ssafy.dancy.entity.Article;
import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.exception.article.ArticleNotFoundException;
import com.ssafy.dancy.exception.article.ArticleNotOwnerException;
import com.ssafy.dancy.message.request.ArticleModifyRequest;
import com.ssafy.dancy.message.request.ArticleUpdateRequest;
import com.ssafy.dancy.message.response.ArticleResponseDto;
import com.ssafy.dancy.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> getAllArticle(){
        return articleRepository.findAll();
    }

    public ArticleResponseDto getArticle(User user, long articleId) {

        Article article = articleRepository.findByArticleId(articleId).orElseThrow(() ->
                new ArticleNotFoundException("게시물을 찾을 수 없습니다."));

        return makeArticleResponseDto(article, user);
    }

    public ArticleResponseDto insertArticle(User user, ArticleUpdateRequest dto) {

        Article article = Article.builder()
                .articleTitle(dto.articleTitle())
                .articleContent(dto.articleContent())
                .thumbnailImageUrl(dto.thumbnailImageUrl())
                .thumbnailVideoUrl(dto.video())
                .user(user)
                .build();

        articleRepository.save(article);

        return makeArticleResponseDto(article, user);

    }

    @Transactional
    public ArticleResponseDto modifyArticle(User user, long articleId, ArticleModifyRequest dto) {

        Article article = articleRepository.findByArticleId(articleId).orElseThrow(() ->
                new ArticleNotFoundException("게시물을 찾을 수 없습니다."));

        if(!user.equals(article.getUser())){
            throw new ArticleNotOwnerException("게시물을 수정/삭제할 수 있는 권한이 없습니다.");
        }

        article.setArticleTitle(dto.articleTitle());
        article.setArticleContent(dto.articleContent());

        return makeArticleResponseDto(article, user);
    }

    public Long deleteArticle(User user, long articleId) {

        Article article = articleRepository.findByArticleId(articleId).orElseThrow(() ->
                new ArticleNotFoundException("게시물을 찾을 수 없습니다."));

        if(!user.equals(article.getUser())){
            throw new ArticleNotOwnerException("게시물을 수정/삭제할 수 있는 권한이 없습니다.");
        }

        articleRepository.delete(article);

        return article.getArticleId();
    }

    public ArticleResponseDto makeArticleResponseDto(Article article, User user){
        return ArticleResponseDto.builder()
                .articleId(article.getArticleId())
                .articleTitle(article.getArticleTitle())
                .articleContent(article.getArticleContent())
                .articleLike(article.getArticleLike())
                .thumbnailImageUrl(article.getThumbnailImageUrl())
                .thumbnailVideoUrl(article.getThumbnailVideoUrl())
                .view(article.getView())
                .createdDate(article.getCreatedDate())
                //.isArticleLiked()
                //.isAuthorFollowing()
                //.follower()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
                .build();
    }


}
