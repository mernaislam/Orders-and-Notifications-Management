package app.repos;

import app.models.Notification.NotificationTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

@Component
public class NotificationTemplateRepo{
    private Queue<NotificationTemplate> notificationsQueue = new LinkedList<>();

    private HashMap<String, Integer> SMSChannelCount = new HashMap<>(); // for statistics
    private HashMap<String, Integer>emailChannelCount = new HashMap<>(); // for statistics

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
    public HashMap<NotificationTemplate, Integer> getAllTemplateCount() {
        return notificationTemplateCount;
    }

    public HashMap<String, Integer> getSMSChannelCount() {
        return SMSChannelCount;
    }

    public HashMap<String, Integer> getEmailChannelCount() {
        return emailChannelCount;
    }

    public void updateNotificationCount(NotificationTemplate notification){
        if (notificationTemplateCount.containsKey(notification)){
            notificationTemplateCount.put(notification, notificationTemplateCount.get(notification) + 1);
        }
        else {
            notificationTemplateCount.put(notification, 1);
        }
    }
    public void updateEmailChannelCount(String Email){
        if (emailChannelCount.containsKey(Email)){
            emailChannelCount.put(Email, emailChannelCount.get(Email) + 1);
        }
        else {
            emailChannelCount.put(Email, 1);
        }
    }
    public void updateSMSChannelCount(String phoneNumber){
        if (SMSChannelCount.containsKey(phoneNumber)){
            SMSChannelCount.put(phoneNumber, SMSChannelCount.get(phoneNumber) + 1);
        }
        else {
            SMSChannelCount.put(phoneNumber, 1);
        }
    }

}
