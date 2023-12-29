package app.managers;

import app.models.Notification.*;
import app.models.Orders.Order;

import java.util.LinkedList;
import java.util.Queue;

public class NotificationManager {
    private NotificationChannel channel;
    private Queue<NotificationTemplate> notificationsQueue = new LinkedList<>();

    public NotificationManager(NotificationChannel channel) {
        this.channel = channel;
    }

    public void sendNotification(NotificationSubject subject, Order order){
        int customerID = order.getCustomer().getCustomerID();
        Language language = new EnglishLanguage(); // should get the language from the customer
        NotificationTemplate notification = null;
        switch (subject) {
            case ORDER_PLACEMENT ->  notification = new OrderPlacementNotification(language, order);
            case ORDER_SHIPMENT -> notification = new OrderShipmentNotification(language, order);
        };
        channel.sendNotification(notification, customerID);
    }
}
