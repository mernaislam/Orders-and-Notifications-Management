package app.models.Channel;

import app.models.Notification.NotificationTemplate;

// component interface
public interface Channel {
    void sendNotification(NotificationTemplate notification, int userID);
}
