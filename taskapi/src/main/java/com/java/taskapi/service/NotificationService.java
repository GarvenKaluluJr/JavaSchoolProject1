package com.java.taskapi.service;

import com.java.taskapi.model.Notification;
import java.util.List;
import java.util.UUID;

public interface NotificationService {
    List<Notification> getAllNotifications(UUID userId);
    List<Notification> getPendingNotifications(UUID userId);
    Notification addNotification(Notification notification);
}