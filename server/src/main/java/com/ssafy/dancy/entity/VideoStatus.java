package com.ssafy.dancy.entity;

import com.ssafy.dancy.type.ConvertStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VideoStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long videoStatusId;

    @ManyToOne
    @Column(name = "gt_video_id")
    private Video referenceVideo;

    @OneToOne
    @Column(name = "prac_video_id")
    private Video practiceVideo;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ConvertStatus status = ConvertStatus.REQUESTING;

}
