package com.ssafy.dancy.entity.PK;

import com.ssafy.dancy.entity.Article;
import com.ssafy.dancy.entity.User;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.BigIntJdbcType;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SavedArticlePK implements Serializable {

    @Id
    @JdbcType(BigIntJdbcType.class)
    private Article article;

    @Id
    @JdbcType(BigIntJdbcType.class)
    private User user;

}
