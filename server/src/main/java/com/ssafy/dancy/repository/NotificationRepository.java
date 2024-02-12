package com.ssafy.dancy.repository;

import com.ssafy.dancy.entity.Notification;
import com.ssafy.dancy.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByTargetUserOrderByNotificationIdDesc(User user, Pageable pageable);
}
