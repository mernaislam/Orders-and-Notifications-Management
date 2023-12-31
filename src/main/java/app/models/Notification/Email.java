package app.models.Notification;

public class Email implements NotificationChannel {
    @Override
    public void sendNotification(NotificationTemplate notification, int userID) {
        // Simulate
        // Sending email to customer with given userID and given notification text...
    }
}
