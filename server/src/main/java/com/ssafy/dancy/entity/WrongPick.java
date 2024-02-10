package com.ssafy.dancy.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WrongPick {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wrongPickId;

    @ManyToOne
    private Video video;

    Integer startTime;

    Integer endTime;

    Double accuracy;
}
