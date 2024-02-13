package com.ssafy.dancy.entity;

import com.ssafy.dancy.type.NotificationContentType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private User authorUser;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private User targetUser;

    @Column(nullable = false,length = 20)
    @Enumerated(EnumType.STRING)
    private NotificationContentType contentType;

    @ManyToOne
    private Article article;

    @CreatedDate
    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime createdTime;
}
