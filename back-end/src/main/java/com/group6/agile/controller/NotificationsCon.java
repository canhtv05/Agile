package com.group6.agile.controller;

import com.group6.agile.entity.Notifications;
import com.group6.agile.service.impl.NotificationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationsCon {
    private final NotificationsService notificationsService;
    @PostMapping("/create")
    public Notifications createNotification(@RequestBody Notifications notification){
        return notificationsService.createNotification(notification);
    }
}
