package com.ssafy.dancy.service.article;

import com.ssafy.dancy.entity.Article;
import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.exception.article.ArticleNotFoundException;
import com.ssafy.dancy.exception.article.ArticleNotOwnerException;
import com.ssafy.dancy.message.request.article.ArticleModifyRequest;
import com.ssafy.dancy.message.request.article.ArticleUpdateRequest;
import com.ssafy.dancy.message.response.article.ArticleDetailResponse;
import com.ssafy.dancy.message.response.article.ArticleSimpleResponse;
import com.ssafy.dancy.repository.article.ArticleRepository;
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

    public List<ArticleSimpleResponse> getStagePage(int limit, Long previousLastArticleId){
        return articleRepository.getStagePageInfo(limit, previousLastArticleId);
    }

    public ArticleDetailResponse getArticle(User user, long articleId) {
        return articleRepository.getArticleDetailInfo(user, articleId).orElseThrow(() ->
                new ArticleNotFoundException("게시물을 찾을 수 없습니다."));
    }

    public ArticleDetailResponse insertArticle(User user, ArticleUpdateRequest dto) {

        Article article = Article.builder()
                .articleTitle(dto.articleTitle())
                .articleContent(dto.articleContent())
                .thumbnailImageUrl(dto.thumbnailImageUrl())
                .thumbnailVideoUrl(dto.video())
                .user(user)
                .build();

        articleRepository.save(article);

        return makeArticleDetailResponse(article, user);

    }

    @Transactional
    public ArticleDetailResponse modifyArticle(User user, long articleId, ArticleModifyRequest dto) {

        Article article = articleRepository.findByArticleId(articleId).orElseThrow(() ->
                new ArticleNotFoundException("게시물을 찾을 수 없습니다."));

        if(!user.equals(article.getUser())){
            throw new ArticleNotOwnerException("게시물을 수정/삭제할 수 있는 권한이 없습니다.");
        }

        article.setArticleTitle(dto.articleTitle());
        article.setArticleContent(dto.articleContent());

        return makeArticleDetailResponse(article, user);
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

    public ArticleDetailResponse makeArticleDetailResponse(Article article, User user){
        return ArticleDetailResponse.builder()
                .articleId(article.getArticleId())
                .articleTitle(article.getArticleTitle())
                .articleContent(article.getArticleContent())
                .articleLike(article.getArticleLike())
                .thumbnailImageUrl(article.getThumbnailImageUrl())
                .thumbnailVideoUrl(article.getThumbnailVideoUrl())
                .view(article.getView())
                .createdDate(article.getCreatedDate())
                .authorId(user.getUserId())
                .nickname(user.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
//                .isArticleLiked()
//                .isAuthorFollowed()
//                .follower()
                .video(null) // TODO : 추후, 비디오 부분도 집어넣기(비디오 부분 개발 완료 후)
                .score(-1) // TODO : score 나오면 할 것.
                .build();
    }


}
