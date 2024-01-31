package com.ssafy.dancy.entity.PK;

import com.ssafy.dancy.entity.Comment;
import com.ssafy.dancy.entity.User;
import lombok.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentLikePK implements Serializable {

    private User user;

    private Comment commentId;
}
