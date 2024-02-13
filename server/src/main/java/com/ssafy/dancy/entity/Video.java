package com.ssafy.dancy.entity;

import com.ssafy.dancy.type.VideoType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoId;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(nullable = false)
    private String videoTitle;

    @Column(nullable = false)
    private String fullVideoUrl;

    private String thumbnailImageUrl;

    @Column
    @Enumerated(value = EnumType.STRING)
    private VideoType videoType;

    @ColumnDefault("0")
    @Column(nullable = false)
    @Builder.Default
    private double score =  0;

    @OneToOne
    @JoinColumn(name = "articleId")
    private Article article;
}
