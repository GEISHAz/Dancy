package com.ssafy.dancy.service.article;

import com.ssafy.dancy.entity.Article;
import com.ssafy.dancy.entity.SavedArticle;
import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.exception.article.ArticleNotFoundException;
import com.ssafy.dancy.exception.article.ArticleNotOwnerException;
import com.ssafy.dancy.message.request.article.ArticleModifyRequest;
import com.ssafy.dancy.message.request.article.ArticleUpdateRequest;
import com.ssafy.dancy.message.response.article.ArticleDetailResponse;
import com.ssafy.dancy.message.response.article.ArticleSaveResponse;
import com.ssafy.dancy.message.response.article.ArticleSimpleResponse;
import com.ssafy.dancy.repository.ArticleSaveRepository;
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
    private final ArticleSaveRepository articleSaveRepository;

    public List<ArticleSimpleResponse> getStagePage(int limit, Long previousLastArticleId){
        return articleRepository.getStagePageInfo(limit, previousLastArticleId);
    }

    public ArticleDetailResponse getArticle(User user, long articleId) {
        return articleRepository.getArticleDetailInfo(user, articleId).orElseThrow(() ->
                new ArticleNotFoundException("게시물을 찾을 수 없습니다."));
    }

    public List<ArticleSimpleResponse> getArticleOfPerson(String nickname, int findCount, Long previousArticleId) {
        return articleRepository.getArticleSearchByNickname(nickname, findCount, previousArticleId);
    }

    public ArticleDetailResponse insertArticle(User user, ArticleUpdateRequest dto) {

        Article article = Article.builder()
                .articleTitle(dto.articleTitle())
                .articleContent(dto.articleContent())
                .thumbnailImageUrl(dto.thumbnailImageUrl())
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

    public ArticleSaveResponse saveArticleForUser(User user, Long articleId) {
        Article article = articleRepository.findByArticleId(articleId).orElseThrow(() ->
                new ArticleNotFoundException("요청한 게시글을 찾을 수 없습니다."));

        SavedArticle savedArticle = articleSaveRepository.save(SavedArticle.builder()
                .article(article)
                .user(user)
                .build());

        return ArticleSaveResponse.builder()
                .saveId(savedArticle.getSavedArticleId())
                .articleId(articleId)
                .saveUserNickname(user.getNickname())
                .articleTitle(article.getArticleTitle())
                .articleAuthorNickname(article.getUser().getNickname())
                .build();
    }

    public List<ArticleSimpleResponse> getSavedArticleOfUser(String nickname, Integer findCount, Long previousArticleId) {
        return articleRepository.getArticleSavedByPerson(nickname, findCount, previousArticleId);
    }

    public ArticleDetailResponse makeArticleDetailResponse(Article article, User user){
        // 본인이 입력하고, 본인이 수정하는 데 사용되는 메소드이다.
        return ArticleDetailResponse.builder()
                .articleId(article.getArticleId())
                .articleTitle(article.getArticleTitle())
                .articleContent(article.getArticleContent())
                .articleLike(article.getArticleLike())
                .view(article.getView())
                .createdDate(article.getCreatedDate())
                .authorId(user.getUserId())
                .nickname(user.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
                .isArticleLiked(false) // 일단, 자기가 좋아요했는데 수정하는 부분을 배제
                .isAuthorFollowed(false)
                .follower(user.getFollowerCount())
                .thumbnailImageUrl(article.getThumbnailImageUrl())
                .video(null) // TODO : 추후, 비디오 부분도 집어넣기(비디오 부분 개발 완료 후)
                .score(-1D) // TODO : score 나오면 할 것.
                .build();
    }


}
