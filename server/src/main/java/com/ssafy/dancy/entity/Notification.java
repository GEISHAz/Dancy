package com.ssafy.dancy.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @ManyToOne
    @Column(nullable = false,unique = true)
    private User authorUserId;

    @ManyToOne
    @Column(nullable = false,unique = true)
    private User targetUserId;

    @Column(nullable = false,length = 10)
    @Enumerated(EnumType.STRING)
    private String contentType;

    @Column(nullable = false)
    private String notificationContent;

    @ManyToOne
    @Column(nullable = false)
    private Article article;

    @CreatedDate
    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime createdTime;
}
