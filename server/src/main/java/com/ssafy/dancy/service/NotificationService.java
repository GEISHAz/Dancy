package com.ssafy.dancy.service;

import com.ssafy.dancy.entity.Notification;
import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.message.response.NotificationResponse;
import com.ssafy.dancy.repository.NotificationRepository;
import com.ssafy.dancy.type.NotificationContentType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    public List<NotificationResponse> getNotification(User user, Integer limit) {
        PageRequest pageRequest = PageRequest.of(0, limit);
        List<Notification> notifications =
                notificationRepository.findAllByTargetUserOrderByNotificationIdDesc(user, pageRequest);

        return convertToResponse(notifications);
    }

    private List<NotificationResponse> convertToResponse(List<Notification> notiList){
        List<NotificationResponse> resultList = new ArrayList<>();

        for(Notification notify : notiList){

            User maker = notify.getAuthorUser();
            Long articleId = null;
            if(notify.getArticle() != null){
                articleId = notify.getArticle().getArticleId();
            }

            NotificationResponse response = NotificationResponse.builder()
                    .notificationId(notify.getNotificationId())
                    .makerUserId(maker.getUserId())
                    .makerUserProfileImageUrl(maker.getProfileImageUrl())
                    .makerUserNickname(maker.getNickname())
                    .content(NotificationContentType.makeContent(maker, notify.getContentType()))
                    .createdTime(notify.getCreatedTime())
                    .articleId(articleId)
                    .build();

            resultList.add(response);
        }

        return resultList;
    }
}
