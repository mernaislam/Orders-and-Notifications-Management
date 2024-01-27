package app.service;


import app.models.Channel.Channel;
import app.models.Channel.Email;
import app.models.Channel.SMS;
import app.models.Customer.Customer;
import app.models.Notification.NotificationSubject;
import app.models.Notification.NotificationTemplate;
import app.models.Notification.NotificationTemplateFactory;
import app.models.Orders.Order;
import app.repos.NotificationTemplateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Queue;

@Service
public class NotificationTemplateService {
    private Channel channel;
    private NotificationTemplateFactory factory;
    private final OrderService orderService;
    private final int CONFIGURED_TIME = 30; // Default value is 30 seconds
    private final NotificationTemplateRepo notificationRepo;
    private final StatisticsService statisticsService;

    @Autowired
    @Lazy
    public NotificationTemplateService(OrderService orderService, NotificationTemplateRepo notificationRepo, StatisticsService statisticsService) {
        this.orderService = orderService;
        this.notificationRepo = notificationRepo;
        this.statisticsService = statisticsService;
        factory = new NotificationTemplateFactory();
    }

    public Queue<NotificationTemplate> getNotificationTemplates() {
        return notificationRepo.getAllNotifications();
    }

    public void generateNotification(NotificationSubject subject, Order order){
        Customer customer = orderService.getCustomer(order.getCustomerUsername());
        int customerID = customer.getCustomerID();

        // create notification template
        NotificationTemplate notification = factory.createNotificationTemplate(subject, order, orderService);

        // should get the preferred channel/s from the customer and modify in the customer management, then create the channels accordingly
        // for now, assume Email & SMS channel is the preferred choice
        channel = new SMS(new Email());

        if(notification != null) {
            new Thread(new Runnable() {
            // run in new thread so the request thread doesn't wait for the notification to be sent and stops
            @Override
            public void run() {
                // add to queue, send notification, remove from queue
                notificationRepo.addNotification(notification);
                sendNotification(notification, customerID);
                notificationRepo.deleteNotification(notification);

                // after it was sent successfully, update live statistics
                statisticsService.updateStatistics(notification, customer);
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
