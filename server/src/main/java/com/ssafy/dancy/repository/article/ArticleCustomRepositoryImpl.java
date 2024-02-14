package com.ssafy.dancy.repository.article;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.dancy.entity.*;
import com.ssafy.dancy.exception.article.LastArticleException;
import com.ssafy.dancy.message.response.article.ArticleDetailResponse;
import com.ssafy.dancy.message.response.article.ArticleSimpleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ssafy.dancy.entity.QArticle.article;
import static com.ssafy.dancy.entity.QArticleLike.articleLike;
import static com.ssafy.dancy.entity.QFollow.follow;
import static com.ssafy.dancy.entity.QSavedArticle.savedArticle;

@Repository
@RequiredArgsConstructor
public class ArticleCustomRepositoryImpl implements ArticleCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<ArticleDetailResponse> getArticleDetailInfo(User me, long articleId) {
        ArticleDetailDTO dto = jpaQueryFactory.selectDistinct(
                Projections.bean(
                        ArticleDetailDTO.class,
                        article.as("article"),
                        articleLike.as("articleLike"),
                        savedArticle.as("savedArticle")
                        )
                )
                .from(article)
                .leftJoin(articleLike).on(article.articleId.eq(articleLike.article.articleId)
                        .and(articleLike.user.userId.eq(me.getUserId())))
                .leftJoin(savedArticle).on(savedArticle.article.eq(article)
                        .and(savedArticle.user.userId.eq(me.getUserId())))
                .where(article.articleId.eq(articleId))
                .fetchOne();

        if(dto == null){
            return Optional.empty();
        }

        Follow followInfo = jpaQueryFactory.selectFrom(follow)
                .where(follow.fromUser.eq(me)
                        .and(follow.toUser.eq(dto.getArticle().getUser())))
                .fetchOne();

        return Optional.of(makeResponse(me, dto, followInfo));
    }

    @Override
    public List<ArticleSimpleResponse> getStagePageInfo(int findCount, Long previousLastArticleId) {
        return getResultStagePage(findCount, previousLastArticleId, article.isNotNull());
    }

    @Override
    public List<ArticleSimpleResponse> getArticleSearchedByTitle(String title, int findCount, Long previousLastArticleId) {
        return getResultStagePage(findCount, previousLastArticleId, article.articleTitle.contains(title));
    }

    @Override
    public List<ArticleSimpleResponse> getArticleSearchByNickname(String nickname, int findCount, Long previousLastArticleId) {
        return getResultStagePage(findCount, previousLastArticleId, article.user.nickname.contains(nickname));
    }

    @Override
    public List<ArticleSimpleResponse> getArticleSavedByPerson(String nickname, int findCount, Long previousLastArticleId) {
        JPAQuery<Article> queryProcess = jpaQueryFactory.select(article)
                .from(article)
                .leftJoin(savedArticle).on(savedArticle.article.eq(article));

        BooleanExpression expression = savedArticle.user.nickname.eq(nickname);

        if(previousLastArticleId != null){
            expression = expression.and(article.articleId.lt(previousLastArticleId));
        }

        List<Article> resultArticle = queryProcess
                .where(expression)
                .orderBy(article.articleId.desc())
                .limit(findCount)
                .fetch();

        if(resultArticle.isEmpty()){
            throw new LastArticleException("마지막 게시글입니다.");
        }
        return makeArticlesToSimpleList(resultArticle);
    }

    public List<ArticleSimpleResponse> getResultStagePage(int findCount, Long previousLastArticleId,
                                                          BooleanExpression searcher){
        JPAQuery<Article> queryProcess = jpaQueryFactory.selectFrom(article);

        if(previousLastArticleId != null){
            searcher = searcher.and(article.articleId.lt(previousLastArticleId));
        }

        List<Article> resultArticle = queryProcess
                .where(searcher)
                .orderBy(article.articleId.desc())
                .limit(findCount)
                .fetch();

        if(resultArticle.isEmpty()){
            throw new LastArticleException("마지막 게시글입니다.");
        }
        return makeArticlesToSimpleList(resultArticle);
    }

    private ArticleDetailResponse makeResponse(User user, ArticleDetailDTO dto, Follow followInfo) {

        User author = dto.getArticle().getUser();
        boolean isArticleLiked = dto.getArticleLike() != null && dto.getArticleLike().getUser().equals(user);

        ArticleDetailResponse.ArticleDetailResponseBuilder builder = ArticleDetailResponse.builder()
                .articleId(dto.getArticle().getArticleId())
                .articleTitle(dto.getArticle().getArticleTitle())
                .articleContent(dto.getArticle().getArticleContent())
                .view(dto.getArticle().getView())
                .articleLike(dto.getArticle().getArticleLike())
                .createdDate(dto.getArticle().getCreatedDate())
                .isArticleLiked(isArticleLiked)
                .isAuthorFollowed(followInfo != null)
                .isArticleSaved(dto.getSavedArticle() != null)
                .follower(author.getFollowerCount())
                .authorId(author.getUserId())
                .nickname(author.getNickname())
                .profileImageUrl(author.getProfileImageUrl());

        if(dto.getArticle().getVideo() != null){
            Video video = dto.getArticle().getVideo();
            builder = builder
                    .thumbnailImageUrl(video.getThumbnailImageUrl())
                    .score(video.getScore())
                    .videoUrl(video.getFullVideoUrl());
        }

        return builder.build();
    }

    private List<ArticleSimpleResponse> makeArticlesToSimpleList(List<Article> articles){
        List<ArticleSimpleResponse> result = new ArrayList<>();

        for(Article article : articles){
            User author = article.getUser();

            result.add(ArticleSimpleResponse.builder()
                    .articleId(article.getArticleId())
                    .articleTitle(article.getArticleTitle())
                    .articleThumbnail(article.getThumbnailImageUrl())
                    .authorId(author.getUserId())
                    .authorName(author.getNickname())
                    .authorProfileImage(author.getProfileImageUrl())
                    .articleView(article.getView())
                    .createdDate(article.getCreatedDate())
                    .build());
        }
        return result;
    }
}
