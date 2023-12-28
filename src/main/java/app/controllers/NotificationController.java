package app.controllers;


import app.models.Notification.*;
import app.models.Orders.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/app")
public class NotificationController {
    private NotificationChannel channel;

    public NotificationController(NotificationChannel channel) {
        this.channel = channel;
    }
    
    public void sendNotification(NotificationSubject subject, Order order){
        int userID = order.getCustomerID();
        Language language = new EnglishLanguage();
        NotificationTemplate notification = null;
        switch (subject) {
            case ORDER_PLACEMENT ->  notification = new OrderPlacementNotification(language, order);
            case ORDER_SHIPMENT -> notification = new OrderShipmentNotification(language, order);
        };
        channel.sendNotification(notification, userID);
    }

}
