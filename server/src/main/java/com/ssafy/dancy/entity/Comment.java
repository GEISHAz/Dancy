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
    private int commentLike;

    @CreatedDate
    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private Date createdDate;

    @LastModifiedDate
    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private Date updatedDate;

    @ManyToOne
    @Column(nullable = false)
    private User user;

    @ManyToOne
    @Column(nullable = false)
    private Article article;

    @Column(nullable = false)
    private int parentId;

}
