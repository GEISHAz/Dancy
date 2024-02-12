package com.ssafy.dancy.message.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record NotificationResponse(
        Long notificationId,
        String content,
        Long makerUserId,
        String makerUserProfileImageUrl,
        String makerUserNickname,
        LocalDateTime createdTime,
        Long articleId
) {
}
