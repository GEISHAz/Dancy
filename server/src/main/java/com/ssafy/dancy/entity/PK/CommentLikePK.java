package com.ssafy.dancy.entity.PK;

import com.ssafy.dancy.entity.Comment;
import com.ssafy.dancy.entity.User;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.BigIntJdbcType;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentLikePK implements Serializable {

    @Id
    @JdbcType(BigIntJdbcType.class)
    private User user;

    @Id
    @JdbcType(BigIntJdbcType.class)
    private Comment comment;
}
