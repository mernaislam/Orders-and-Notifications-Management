package app.models.Notification;

public class Email implements NotificationChannel {
    @Override
    public void sendNotification(NotificationTemplate notification, int userID) {
        // "Sending email to customer " + userID + " with message: \n" + notification.getText());
    }
}
