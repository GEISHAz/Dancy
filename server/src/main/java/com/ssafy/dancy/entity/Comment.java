package com.ssafy.dancy.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Article article;

    @ColumnDefault("0")
    @Column(nullable = false)
    @Builder.Default
    private Long parentId = 0L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(commentId, comment.commentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId);
    }
}
