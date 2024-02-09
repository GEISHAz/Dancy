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
    private Video referenceVideo;

    @OneToOne
    @JoinColumn(name = "videoId")
    private Video practiceVideo;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ConvertStatus status = ConvertStatus.REQUESTING;

}
