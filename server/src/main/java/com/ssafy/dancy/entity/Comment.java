package com.ssafy.dancy.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String commentContent;

    @Column(nullable = false)
    @ColumnDefault("0")
    @Builder.Default
    private Integer commentLike = 0;

    @CreatedDate
    @Column(columnDefinition = "TIMESTAMP") //, nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(columnDefinition = "TIMESTAMP") //, nullable = false)
    private LocalDateTime updatedDate;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Article article;

    @ColumnDefault("-1")
    @Column(nullable = false)
    @Builder.Default
    private Long parentId = -1L;

}
