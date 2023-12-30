package app.service;

import app.models.Notification.NotificationSubject;
import app.models.Orders.*;
import app.repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderService {
    private final OrderRepo orderRepo;
    private final NotificationTemplateService notificationService;

    @Autowired
    public OrderService(OrderRepo orderRepo, NotificationTemplateService notificationService) {
        this.orderRepo = orderRepo;
        this.notificationService = notificationService;
    }

    public Order findOrderById(int id) {
        return orderRepo.findByID(id);
    }

    public ArrayList<Order> getOrders() {
        return orderRepo.getAll();
    }

    public void addSimpleOrder(SimpleOrder order) {
        // validations el simple order hena
        ProcessOrder orderProcessor = new ProcessSimpleOrder();
        orderProcessor.processOrder(order);
        notificationService.generateNotification(NotificationSubject.ORDER_PLACEMENT, order);
        orderRepo.add(order);
    }
    public void addCompoundOrder(CompoundOrder order) {
        //validations el compound order hena
        ProcessOrder orderProcessor = new ProcessCompoundOrder();
        orderProcessor.processOrder(order);
        notificationService.generateNotification(NotificationSubject.ORDER_PLACEMENT, order);
        orderRepo.add(order);
    }

    public void deleteOrder(int id) {
        // check if order exists
        Order order = orderRepo.findByID(id);
        if (order == null)
            return;
        notificationService.generateNotification(NotificationSubject.ORDER_CANCELLATION, order);
        orderRepo.delete(id);
    }

    public void shipOrder(int id) {
        // create el notification subject shipOrder
        Order order = orderRepo.findByID(id);
        if (order == null)
            return;
        notificationService.generateNotification(NotificationSubject.ORDER_SHIPMENT, order);
        orderRepo.updateStatus(OrderStatus.SHIPPED, id);
    }
}