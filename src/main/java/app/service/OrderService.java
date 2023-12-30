package app.service;

import app.models.Customer.Customer;
import app.models.Notification.NotificationSubject;
import app.models.Orders.*;
import app.repos.CustomerRepo;
import app.repos.OrderRepo;
import app.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
public class OrderService {
    private OrderRepo orderRepo;
    private final NotificationTemplateService notificationService;
    private final CustomerRepo customerRepo;
    private final ProductRepo productRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo, NotificationTemplateService notificationService, CustomerRepo customerRepo, ProductRepo productRepo) {
        this.orderRepo = orderRepo;
        this.notificationService = notificationService;
        this.customerRepo = customerRepo;
        this.productRepo = productRepo;
    }

    public Order findOrderById(int id) {
        return orderRepo.findByID(id);
    }

    public ArrayList<Order> getOrders() {
        return orderRepo.getAll();
    }

    public void addSimpleOrder(SimpleOrder order) {
        ProcessOrder orderProcessor = new ProcessSimpleOrder(this, productRepo);
        orderProcessor.processOrder(order);
        if (order.getStatus() == OrderStatus.INVALID) {
            return;
        }
        notificationService.generateNotification(NotificationSubject.ORDER_PLACEMENT, order);
        orderRepo.add(order);
        orderRepo.updateStatus(OrderStatus.PLACED, order.getOrderID());
    }
    public void addCompoundOrder(CompoundOrder order) {
        ProcessOrder orderProcessor = new ProcessCompoundOrder(this, productRepo);
        orderProcessor.processOrder(order);
        if (order.getStatus() == OrderStatus.INVALID) {
            return;
        }
        notificationService.generateNotification(NotificationSubject.ORDER_PLACEMENT, order);
        orderRepo.add(order);
        orderRepo.updateStatus(OrderStatus.PLACED, order.getOrderID());
    }

    public void shipOrder(int id) {
        // create el notification subject shipOrder
        Order order = orderRepo.findByID(id);
        if (order == null)
            return;
        notificationService.generateNotification(NotificationSubject.ORDER_SHIPMENT, order);
        orderRepo.updateStatus(OrderStatus.SHIPPED, id);
    }
    public void cancelPlacement(int id) {
        Order order = orderRepo.findByID(id);
        if (order == null) {
            return;
        }
        notificationService.generateNotification(NotificationSubject.PLACEMENT_CANCELLATION, order);
        orderRepo.delete(id);
    }
    public void cancelShipment(int id) {
        // create el notification subject cancelShipment law hn3ml

        //condition 2n fe configuered time m3dash men sa3t ma el order et3mlo ship
        orderRepo.updateStatus(OrderStatus.PLACED, id);
    }
    public boolean orderExists(int id) {
        return orderRepo.findByID(id) != null;
    }
    public boolean userExists(int id) {
        // check if user exists
        return customerRepo.findByID(id) != null;
    }
    public boolean hasMoney(Customer customer, double amount) {
        return customer.getBalance() >= amount;
    }
    public int generateID(){
        Random random = new Random();
        int value = random.nextInt(100000 - 1);
        while(orderExists(value)){
            value = random.nextInt(100000 - 1);
        }
        return value;
    }
    public Customer getCustomer(String customerUsername){
        orderRepo = new OrderRepo();
        return customerRepo.findByUsername(customerUsername);
    }

}
