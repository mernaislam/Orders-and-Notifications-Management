package app.managers;

import app.models.Notification.*;
import app.models.Orders.Order;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class NotificationManager {
    private NotificationChannel channel;
    private Queue<NotificationTemplate> notificationsQueue = new LinkedList<>();
    private final int CONFIGURED_TIME = 5; // Default value is 5 seconds

//    private HashMap<NotificationChannel, Integer> channelCount = new HashMap<>(); // for statistics
    private HashMap<NotificationTemplate, Integer> notificationTemplateCount = new HashMap<>(); // for statistics

    public NotificationManager(NotificationChannel channel) {
        this.channel = channel;
    }
    public void generateNotification(NotificationSubject subject, Order order){
        int customerID = order.getCustomer().getCustomerID();
        Language language = new EnglishLanguage(); // should get the language from the customer
        NotificationTemplate notification = null;
        switch (subject) {
            case ORDER_PLACEMENT ->  notification = new OrderPlacementNotification(language, order);
            case ORDER_SHIPMENT -> notification = new OrderShipmentNotification(language, order);
        };
        notificationsQueue.add(notification);
        sendNotification(notification, customerID);
        updateNotificationCount(notification);
        // update most notified email address/sms number
    }

    public void updateNotificationCount(NotificationTemplate notification){
        if (notificationTemplateCount.containsKey(notification)){
            notificationTemplateCount.put(notification, notificationTemplateCount.get(notification) + 1);
        }
        else {
            notificationTemplateCount.put(notification, 1);
        }
    }

    public void sendNotification(NotificationTemplate notificationToSend, int customerID){
        // after configured time, send the notification
        try {
            Thread.sleep(CONFIGURED_TIME * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        channel.sendNotification(notificationToSend, customerID);
        notificationsQueue.remove(notificationToSend);
    }

    public void listCurrentNotifications(){
        int count = 1;
        for (NotificationTemplate notification : notificationsQueue) {
            System.out.println("Notification " + count++ + ": ");
            System.out.println("    " + notification.getText());
        }
    }
}
