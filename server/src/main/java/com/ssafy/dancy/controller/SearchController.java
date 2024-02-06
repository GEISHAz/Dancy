package com.ssafy.dancy.controller;

import com.ssafy.dancy.message.response.article.ArticleSimpleResponse;
import com.ssafy.dancy.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/title/{keyword}")
    public List<ArticleSimpleResponse> searchByTitle(@PathVariable String keyword){
        return searchService.searchByTitle(keyword);
    }

    @GetMapping("/nickname/{keyword}")
    public List<ArticleSimpleResponse> searchByNickname(@PathVariable String keyword){
        return searchService.searchByNickname(keyword);
    }


}
