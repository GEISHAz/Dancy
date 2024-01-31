package com.ssafy.dancy.entity;

import com.ssafy.dancy.entity.PK.SavedArticlePK;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@IdClass(SavedArticlePK.class)
@Getter
@Setter
@Builder
public class SavedArticle {

    @Id
    @ManyToOne
    private Article article;

    @Id
    @ManyToOne
    private User user;

}
