package com.java.taskapi.service.impl;

import com.java.taskapi.model.Notification;
import com.java.taskapi.messaging.TaskMessage;
import com.java.taskapi.repository.NotificationRepository;
import com.java.taskapi.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@Profile("rabbit")
public class NotificationServiceRabbitImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceRabbitImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    // This will ONLY get called by RabbitMQ Listener, not directly
    @RabbitListener(queues = "task.queue")
    public void processTaskMessage(TaskMessage message) {
        Notification notification = new Notification();
        notification.setId(UUID.randomUUID());
        notification.setUserId(message.getUserId());
        notification.setMessage("Task created: " + message.getDescription());
        notification.setRead(false);
        notificationRepository.save(notification);
    }

    // Implement the interface but do nothing for these methods
    @Override public java.util.List<Notification> getAllNotifications(UUID userId) { return notificationRepository.findByUserId(userId); }
    @Override public java.util.List<Notification> getPendingNotifications(UUID userId) { return notificationRepository.findByUserIdAndReadFalse(userId); }
    @Override public Notification addNotification(Notification notification) { return notificationRepository.save(notification); }
}
