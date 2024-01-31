package com.ssafy.dancy.entity.PK;

import com.ssafy.dancy.entity.Article;
import com.ssafy.dancy.entity.Hashtag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleHashtagPK implements Serializable {

    private Article article;

    private Hashtag hashtag;

}
