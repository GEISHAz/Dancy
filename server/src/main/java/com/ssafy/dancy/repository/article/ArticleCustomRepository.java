package com.ssafy.dancy.repository.article;

import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.message.response.ArticleDetailResponse;

import java.util.Optional;

public interface ArticleCustomRepository {

    Optional<ArticleDetailResponse> getArticleDetailInfo(User user, long articleID);
}
