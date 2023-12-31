package app.service;


import app.models.Customer.Customer;
import app.models.Notification.*;
import app.models.Orders.Order;
import app.repos.NotificationTemplateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Queue;

@Service
public class NotificationTemplateService {
    private NotificationChannel channel;
    private OrderService orderService;
    private final int CONFIGURED_TIME = 30; // Default value is 30 seconds
    private final NotificationTemplateRepo notificationRepo;
    private final StatisticsService statisticsService;

    @Autowired
    @Lazy
    public NotificationTemplateService(OrderService orderService, NotificationTemplateRepo notificationRepo, StatisticsService statisticsService) {
        this.orderService = orderService;
        this.notificationRepo = notificationRepo;
        this.statisticsService = statisticsService;
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
                statisticsService.updateNotificationCount(sendNotification);
                statisticsService.updateChannelCount(channel);
                statisticsService.updateCustomerNotificationCount(customer);
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


}
