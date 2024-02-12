package com.ssafy.dancy.util;

import com.ssafy.dancy.entity.Article;
import com.ssafy.dancy.entity.ArticleLike;
import com.ssafy.dancy.entity.Notification;
import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.message.request.comment.CommentWriteRequest;
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
        User targetUser = article.getUser();

        NotificationContentType type = COMMENT_ON_ARTICLE;

        if(request.parentId() != 0){
            type = COMMENT_ON_COMMENT;
        }

        afterProcess(authorUser, targetUser, article, type, "comment");
    }


    private void afterProcess(User authorUser, User targetUser, Article article, NotificationContentType type, String sendData){

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
