package app.service;


import app.models.Customer.Customer;
import app.models.Notification.*;
import app.models.Orders.Order;
import app.repos.NotificationTemplateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Queue;

@Service
public class NotificationTemplateService {
    private NotificationChannel channel;
    private OrderService orderService;
    private final int CONFIGURED_TIME = 5; // Default value is 5 seconds

    private final NotificationTemplateRepo notificationRepo;

    @Autowired
    public NotificationTemplateService(NotificationTemplateRepo notificationRepo) {
        this.notificationRepo = notificationRepo;
    }

    public Queue<NotificationTemplate> getNotificationTemplates() {
        return notificationRepo.getAllNotifications();
    }

    public void generateNotification(NotificationSubject subject, Order order){
        Customer customer = orderService.getCustomer(order.getCustomerUsername());
        int customerID = customer.getCustomerID();
        Language language = new EnglishLanguage(); // assume english language is chosen
        NotificationTemplate notification = null;
        switch (subject) {
            case ORDER_PLACEMENT ->  {
                notification = new OrderPlacementNotification(language, order, orderService);
                channel = new Email(); // assume email channel is chosen
            }
            case ORDER_SHIPMENT -> {
                notification = new OrderShipmentNotification(language, order, orderService);
                channel = new SMS(); // assume sms channel is chosen
            }
            case PLACEMENT_CANCELLATION -> {
                notification = new PlacementCancellationNotification(language, order, orderService);
                channel = new Email(); // assume email channel is chosen
            }
            case SHIPMENT_CANCELLATION -> {
                notification = new ShipmentCancellationNotification(language, order, orderService);
                channel = new SMS(); // assume SMS channel is chosen
            }
        };
        NotificationTemplate sendNotification = notification;
        if(notification != null) {
            new Thread(new Runnable() {
            // run in new thread so the request thread doesn't wait for the notification to be sent and stops
            @Override
            public void run() {
                // add to queue, send notification, remove from queue
                sendNotification.createTemplate();
                notificationRepo.addNotification(sendNotification);
                sendNotification(sendNotification, customerID);
                notificationRepo.deleteNotification(sendNotification);

                // after it was sent successfully, update live statistics
                notificationRepo.updateNotificationCount(sendNotification);
                notificationRepo.updateChannelCount(channel);
            }
            }).start();
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
    }
    public String getTemplateStatistics(){
        int mx = 0;
        String mostNotifiedTemplate = "";
        String mostNotified = null;
        HashMap<String, Integer> notificationCount = notificationRepo.getAllTemplateCount();
        for (String key: notificationCount.keySet()) {
            if (notificationCount.get(key) > mx) {
                mx = notificationCount.get(key);
                mostNotified = key;
            }
        }
        if (mostNotified != null) {
            mostNotifiedTemplate += "Most Notified Notification Template: " + mostNotified;
            mostNotifiedTemplate += "\nNumber of times notified: " + mx;
        }
        return mostNotifiedTemplate;
    }

    public String getChannelStatistics(){
        int mx = 0;
        String mostNotifiedChannel = "";
        String mostNotified = null;
        HashMap<String, Integer> channelCount = notificationRepo.getAllChannelCount();
        for (String key: channelCount.keySet()) {
            if (channelCount.get(key) > mx) {
                mx = channelCount.get(key);
                mostNotified = key;
            }
        }
        if (mostNotified != null) {
            mostNotifiedChannel += "Most Notified Notification Channel: " + mostNotified;
            mostNotifiedChannel += "\nNumber of times notified: " + mx;
        }
        return mostNotifiedChannel;
    }

}
