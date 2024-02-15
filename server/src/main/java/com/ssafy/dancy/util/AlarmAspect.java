package com.ssafy.dancy.util;

import com.ssafy.dancy.entity.*;
import com.ssafy.dancy.message.request.comment.CommentWriteRequest;
import com.ssafy.dancy.repository.CommentRepository;
import com.ssafy.dancy.repository.NotificationRepository;
import com.ssafy.dancy.repository.UserRepository;
import com.ssafy.dancy.repository.article.ArticleRepository;
import com.ssafy.dancy.type.NotificationContentType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import static com.ssafy.dancy.type.NotificationContentType.*;


@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class AlarmAspect {

    private final AlarmHandler alarmHandler;
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    @AfterReturning("execution(public * com.ssafy.dancy.service.follow.FollowService.follow(..))")
    public void afterFollow(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        User authorUser = (User)args[0];

        String targetNickname = (String)args[1];
        User targetUser = userRepository.findByNickname(targetNickname).get();

        afterProcess(authorUser, targetUser, null, FOLLOW, "follow");
    }

    @AfterReturning("execution(* com.ssafy.dancy.repository.ArticleLikeRepository.save(..))")
    public void afterArticleLike(JoinPoint joinPoint){
        ArticleLike like = (ArticleLike)joinPoint.getArgs()[0];

        User authorUser = like.getUser();
        User targetUser = like.getArticle().getUser();

        afterProcess(authorUser, targetUser, like.getArticle(), LIKE, "article_like");
    }

    @AfterReturning("execution(* com.ssafy.dancy.service.comment.CommentService.insertComment(..))")
    public void afterInsertComment(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        User authorUser = (User)args[0];
        Long articleId = (Long)args[1];
        CommentWriteRequest request = (CommentWriteRequest)args[2];

        Article article = articleRepository.findByArticleId(articleId).get();

        if(request.parentId() == 0){
            insertCommentOnArticle(article, authorUser);
        }else{
            insertCommentOnComment(article, authorUser, request.parentId());
        }
    }

    void insertCommentOnArticle(Article article, User authorUser){
        User targetUser = article.getUser();
        afterProcess(authorUser, targetUser, article, COMMENT_ON_ARTICLE, "comment");
    }

    void insertCommentOnComment(Article article, User authorUser, Long parentCommentId){
        Comment comment = commentRepository.findByCommentId(parentCommentId).get();
        User targetUser = comment.getUser();
        afterProcess(authorUser, targetUser, article, COMMENT_ON_COMMENT, "comment");
    }


    private void afterProcess(User authorUser, User targetUser, Article article, NotificationContentType type, String sendData){

        if(authorUser.equals(targetUser)){
            return;
        }

        Notification notification = Notification.builder()
                .authorUser(authorUser)
                .targetUser(targetUser)
                .contentType(type)
                .article(article)
                .build();

        notificationRepository.save(notification);
        alarmHandler.sendEventToUser(notification.getTargetUser().getUserId(), "notification", sendData);
    }


}
