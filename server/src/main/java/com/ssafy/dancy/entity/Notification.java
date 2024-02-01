package com.ssafy.dancy.entity;

import com.ssafy.dancy.type.NotificationContentType;
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

    @ManyToOne(optional = false)
    private User authorUser;

    @ManyToOne(optional = false)
    private User targetUser;

    @Column(nullable = false,length = 20)
    @Enumerated(EnumType.STRING)
    private NotificationContentType contentType;

    @Column(nullable = false)
    private String notificationContent;

    @ManyToOne(optional = false)
    private Article article;

    @CreatedDate
    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime createdTime;
}
