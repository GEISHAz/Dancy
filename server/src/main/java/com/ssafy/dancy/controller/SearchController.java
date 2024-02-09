package com.ssafy.dancy.controller;

import com.ssafy.dancy.message.response.article.ArticleSimpleResponse;
import com.ssafy.dancy.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/title/{keyword}")
    public List<ArticleSimpleResponse> searchByTitle(@PathVariable String keyword, @RequestParam int limit,
                                                     @RequestParam(required = false) Long previousArticleId){
        return searchService.searchByTitle(keyword, limit, previousArticleId);
    }

    @GetMapping("/nickname/{keyword}")
    public List<ArticleSimpleResponse> searchByNickname(@PathVariable String keyword, @RequestParam int limit,
                                                        @RequestParam(required = false) Long previousArticleId){
        return searchService.searchByNickname(keyword, limit, previousArticleId);
    }
}
