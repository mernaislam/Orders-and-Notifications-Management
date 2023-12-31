package app.models.Notification;

public class SMS implements NotificationChannel {
    @Override
    public void sendNotification(NotificationTemplate notification, int userID) {
        // Simulate
        // Sending SMS to customer with given userID and given notification text...
    }
}
