package com.ssafy.dancy.repository.article;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.dancy.entity.Article;
import com.ssafy.dancy.entity.Follow;
import com.ssafy.dancy.entity.User;
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

@Repository
@RequiredArgsConstructor
public class ArticleCustomRepositoryImpl implements ArticleCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<ArticleSimpleResponse> getStagePageInfo(int findCount, Long previousLastArticleId) {
        JPAQuery<Article> queryProcess = jpaQueryFactory.selectFrom(article);

        if(previousLastArticleId != null){
            queryProcess = queryProcess.where(article.articleId.lt(previousLastArticleId));
        }

        List<Article> resultArticle = queryProcess
                .orderBy(article.articleId.desc())
                .limit(findCount)
                .fetch();

        if(resultArticle.isEmpty()){
            throw new LastArticleException("마지막 게시글입니다.");
        }
        return makeArticlesToSimpleList(resultArticle);
    }

    @Override
    public Optional<ArticleDetailResponse> getArticleDetailInfo(User me, long articleId) {
        ArticleDetailDTO dto = jpaQueryFactory.select(
                Projections.bean(
                        ArticleDetailDTO.class,
                        article.as("article"),
                        articleLike.as("articleLike")
                        )
                )
                .from(article)
                .leftJoin(articleLike).on(article.articleId.eq(articleLike.article.articleId)
                        .and(articleLike.user.userId.eq(me.getUserId())))
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

    private ArticleDetailResponse makeResponse(User user, ArticleDetailDTO dto, Follow followInfo) {

        User author = dto.getArticle().getUser();
        boolean isArticleLiked = dto.getArticleLike() != null && dto.getArticleLike().getUser().equals(user);

        return ArticleDetailResponse.builder()
                .articleId(dto.getArticle().getArticleId())
                .articleTitle(dto.getArticle().getArticleTitle())
                .articleContent(dto.getArticle().getArticleContent())
                .thumbnailImageUrl(dto.getArticle().getThumbnailImageUrl())
                .thumbnailVideoUrl(dto.getArticle().getThumbnailVideoUrl())
                .view(dto.getArticle().getView())
                .articleLike(dto.getArticle().getArticleLike())
                .createdDate(dto.getArticle().getCreatedDate())
                .isArticleLiked(isArticleLiked)
                .isAuthorFollowed(followInfo != null)
                .score(-1) // TODO : 나중에 score 받을 수 있으면 집어넣기
                .video(null) // TODO : video 처리하고 난 뒤에 하기
                .follower(author.getFollowerCount())
                .authorId(author.getUserId())
                .nickname(author.getNickname())
                .profileImageUrl(author.getProfileImageUrl())
                .build();
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
                    .build());
        }
        return result;
    }
}
