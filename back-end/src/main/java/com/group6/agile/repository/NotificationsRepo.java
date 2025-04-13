package com.group6.agile.repository;

import com.group6.agile.entity.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationsRepo extends JpaRepository<Notifications, String> {
}
