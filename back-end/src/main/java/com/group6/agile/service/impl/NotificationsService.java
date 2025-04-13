package com.group6.agile.service.impl;

import com.group6.agile.entity.Notifications;
import com.group6.agile.repository.NotificationsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationsService {
    private final NotificationsRepo notificationsRepo;
    public Notifications createNotification(Notifications notification) {

        Notifications noti = new Notifications();
        noti.setTitle(notification.getTitle());
        noti.setContent(notification.getContent());
        noti.setIsGlobal(true);
        return notificationsRepo.save(noti);
    }
}
