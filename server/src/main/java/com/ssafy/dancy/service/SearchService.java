package com.ssafy.dancy.service;

import com.ssafy.dancy.message.response.article.ArticleSimpleResponse;
import com.ssafy.dancy.repository.article.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final ArticleRepository articleRepository;

    public List<ArticleSimpleResponse> searchByTitle(String keyword, int limit, Long previousArticleId) {
        return articleRepository.getArticleSearchedByTitle(keyword, limit, previousArticleId);
    }

    public List<ArticleSimpleResponse> searchByNickname(String keyword, int limit, Long previousArticleId) {
        return articleRepository.getArticleSearchByNickname(keyword, limit,previousArticleId);
    }
}
