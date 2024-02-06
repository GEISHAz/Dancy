package com.ssafy.dancy.repository.article;

import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.message.response.article.ArticleDetailResponse;
import com.ssafy.dancy.message.response.article.ArticleSimpleResponse;

import java.util.List;
import java.util.Optional;

public interface ArticleCustomRepository {

    List<ArticleSimpleResponse> getStagePageInfo(int findCount, Long previousLastArticleId);

    Optional<ArticleDetailResponse> getArticleDetailInfo(User user, long articleID);

}
