package com.ssafy.dancy.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(nullable = false,length = 40)
    private String videoTitle;

    @Column(nullable = false)
    private String fullVideoUrl;

    @Column(nullable = false)
    private String thumbnailVideoUrl;

    @Column(nullable = false)
    private String thumbnailImageUrl;
}
