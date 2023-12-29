package app.service;


import app.models.Notification.*;
import app.models.Orders.Order;
import app.repos.NotificationTemplateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Queue;

@Service
public class NotificationTemplateService {
    // SMS, Email channel depending on the customer, should be set
    private NotificationChannel channel = new SMS();
    private final int CONFIGURED_TIME = 5; // Default value is 5 seconds

    private final NotificationTemplateRepo notificationRepo;

//    @Autowired
//    public NotificationTemplateService(NotificationTemplateRepo notificationRepo, NotificationChannel channel) {
//        this.notificationRepo = notificationRepo;
//        this.channel = channel; // error
//    }

    @Autowired
    public NotificationTemplateService(NotificationTemplateRepo notificationRepo) {
        this.notificationRepo = notificationRepo;
    }


    public Queue<NotificationTemplate> getNotificationTemplates() {
        return notificationRepo.getAllNotifications();
    }

    public void generateNotification(NotificationSubject subject, Order order){
        int customerID = order.getCustomer().getCustomerID();
        Language language = new EnglishLanguage(); // should get the language from the customer
        NotificationTemplate notification = null;
        switch (subject) {
            case ORDER_PLACEMENT ->  notification = new OrderPlacementNotification(language, order);
            case ORDER_SHIPMENT -> notification = new OrderShipmentNotification(language, order);
        };
        notification.createTemplate();
        notificationRepo.addNotification(notification);
        sendNotification(notification, customerID);
        notificationRepo.deleteLastNotification();
        // update most notified email address/sms number
    }

    public void sendNotification(NotificationTemplate notificationToSend, int customerID){
        // after configured time, send the notification
        try {
            Thread.sleep(CONFIGURED_TIME * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        channel.sendNotification(notificationToSend, customerID);
    }
    public String getTemplateStatistics(){
        int mx = -999999;
        String mostNotifiedTemplate = "";
        NotificationTemplate mostNotified = null;
        HashMap<NotificationTemplate, Integer> notificationCount = notificationRepo.getAllNotificationCount();
        for (NotificationTemplate key: notificationCount.keySet()) {
            if (notificationCount.get(key) > mx) {
                mx = notificationCount.get(key);
                mostNotified = key;
            }
        }
        if (mostNotified != null) {
            mostNotifiedTemplate += "Most Notified: " + mostNotified.getText();
            mostNotifiedTemplate += "\nNumber of times notified: " + mx;
        }
        return mostNotifiedTemplate;
    }


//    public void getChannelStatistics(){
//    }

}
