package app.models.Channel;

import app.models.Notification.NotificationTemplate;

// concrete component - assume that the default notification channel is the Email channel
public class Email implements Channel {

    @Override
    public void sendNotification(NotificationTemplate notification, int userID) {
        // Simulate sending Email to customer with given userID and given notification text...
    }
}
