package app.repos;

import app.models.Notification.NotificationTemplate;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class NotificationTemplateRepo{
    private Queue<NotificationTemplate> notificationsQueue = new LinkedList<>();

    public void addNotification(NotificationTemplate notificationTemplate) {
        notificationsQueue.add(notificationTemplate);
    }

    public void deleteNotification(NotificationTemplate notificationTemplate) {
        notificationsQueue.remove(notificationTemplate);
    }

    public Queue<NotificationTemplate> getAllNotifications() {
        return notificationsQueue;
    }
}
