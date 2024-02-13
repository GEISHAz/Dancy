package com.ssafy.dancy.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Video video;

    Integer startTime;

    Integer endTime;

    Double accuracy;
}
