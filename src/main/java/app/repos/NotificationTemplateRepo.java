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
    private HashMap<String, Integer> notificationTemplateCount = new HashMap<>(); // for statistics
    private HashMap<String, Integer> channelCount = new HashMap<>(); // for statistics

    public void addNotification(NotificationTemplate notificationTemplate) {
        notificationsQueue.add(notificationTemplate);
    }

    public void deleteLastNotification() {
        notificationsQueue.poll();
    }

    public Queue<NotificationTemplate> getAllNotifications() {
        return notificationsQueue;
    }
    public HashMap<String, Integer> getAllTemplateCount() {
        return notificationTemplateCount;
    }
    public HashMap<String, Integer> getAllChannelCount() {
        return channelCount;
    }

    public void updateNotificationCount(NotificationTemplate notification){
        String notificationTemplateName = notification.getClass().getSimpleName();
        if (notificationTemplateCount.containsKey(notificationTemplateName)){
            notificationTemplateCount.put(notificationTemplateName, notificationTemplateCount.get(notificationTemplateName) + 1);
        }
        else {
            notificationTemplateCount.put(notificationTemplateName, 1);
        }
    }
    public void updateChannelCount(NotificationChannel channel){
        String channelName = channel.getClass().getSimpleName();
        if (channelCount.containsKey(channelName)){
            channelCount.put(channelName, channelCount.get(channelName) + 1);
        }
        else {
            channelCount.put(channelName, 1);
        }
    }
}
