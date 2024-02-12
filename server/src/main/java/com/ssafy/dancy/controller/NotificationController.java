package com.ssafy.dancy.controller;

import com.ssafy.dancy.entity.User;
import com.ssafy.dancy.message.response.NotificationResponse;
import com.ssafy.dancy.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("")
    public List<NotificationResponse> getUserNotification(@AuthenticationPrincipal User user,
                                                          @RequestParam(required = false, defaultValue = "10") Integer limit){
        return notificationService.getNotification(user, limit);
    }
}
