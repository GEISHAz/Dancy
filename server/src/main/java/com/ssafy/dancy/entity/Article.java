package com.ssafy.dancy.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    @Column(nullable = false,length = 40)
    private String articleTitle;

    @Column(nullable = false)
    private String articleContent;

    @Column(nullable = false)
    private String thumbnailImageUrl;

    @Column(nullable = false)
    private String thumbnailVideoUrl;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Long view;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int articleLike;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @ManyToOne
    @Column(nullable = false)
    private User user;

    @OneToOne
    @Column(nullable = false)
    private Video video;
}
