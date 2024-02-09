package com.ssafy.dancy.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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

    @Column(nullable = false)
    private String videoTitle;

    @Column(nullable = false)
    private String fullVideoUrl;

    @ColumnDefault("0")
    @Column(nullable = false)
    @Builder.Default
    private int score =  0;
}
