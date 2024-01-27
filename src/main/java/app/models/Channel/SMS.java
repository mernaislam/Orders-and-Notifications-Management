package app.models.Channel;

import app.models.Notification.NotificationTemplate;

public class SMS extends ChannelDecorator {

    public SMS(Channel channel) {
        super(channel);
    }
    @Override
    public void sendNotification(NotificationTemplate notification, int userID) {
        super.sendNotification(notification, userID);
        // Simulate sending SMS to customer with given userID and given notification text...
    }
}
