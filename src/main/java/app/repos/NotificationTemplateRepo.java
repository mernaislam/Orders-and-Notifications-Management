package app.repos;

import app.models.Notification.NotificationChannel;
import app.models.Notification.NotificationTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

@Component
public class NotificationTemplateRepo{
    private Queue<NotificationTemplate> notificationsQueue = new LinkedList<>();

    //private HashMap<NotificationChannel, >channelCount = new HashMap<>(); // for statistics
    private HashMap<NotificationTemplate, Integer> notificationTemplateCount = new HashMap<>(); // for statistics

    public NotificationTemplateRepo() {
//        loadData();
    }

    public void addNotification(NotificationTemplate notificationTemplate) {
        notificationsQueue.add(notificationTemplate);
        updateNotificationCount(notificationTemplate);
    }

    public void deleteLastNotification() {
        notificationsQueue.poll();
    }

    public Queue<NotificationTemplate> getAllNotifications() {
        return notificationsQueue;
    }
    public HashMap<NotificationTemplate, Integer> getAllNotificationCount() {
        return notificationTemplateCount;
    }

    public void updateNotificationCount(NotificationTemplate notification){
        if (notificationTemplateCount.containsKey(notification)){
            notificationTemplateCount.put(notification, notificationTemplateCount.get(notification) + 1);
        }
        else {
            notificationTemplateCount.put(notification, 1);
        }
    }

    public void updateChannelCount(NotificationChannel channel){

    }

}
