package app.models.Notification;

public class SMS implements NotificationChannel {
    @Override
    public void sendNotification(NotificationTemplate notification, int userID) {
        // "Sending SMS to customer " + userID + " with message: \n" + notification.getText());
    }
}
