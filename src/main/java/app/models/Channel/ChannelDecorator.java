package app.models.Channel;

import app.models.Notification.NotificationTemplate;

// Base decorator class
public abstract class ChannelDecorator implements Channel {
    private final Channel channel;

    public ChannelDecorator(Channel channel) {
        this.channel = channel;
    }
    @Override
    public void sendNotification(NotificationTemplate notification, int userID) {
        channel.sendNotification(notification, userID);
    }
}
