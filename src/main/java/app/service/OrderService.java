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

    // for testing made 1 minute, but in real life it should be at least 30 minutes or 1 hour
    private final int PRECONFIGURED_TIME = 60; // Default value is 60 seconds - 1 minute

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

    public boolean addSimpleOrder(SimpleOrder order) {
        ProcessOrder orderProcessor = new ProcessSimpleOrder(this, productRepo);
        orderProcessor.processOrder(order);
        if (order.getStatus() == OrderStatus.INVALID) {
            return false;
        }
        notificationService.generateNotification(NotificationSubject.ORDER_PLACEMENT, order);
        orderRepo.add(order);
        return true;
    }
    public boolean addCompoundOrder(CompoundOrder order) {
        ProcessOrder orderProcessor = new ProcessCompoundOrder(this, productRepo);
        orderProcessor.processOrder(order);
        if (order.getStatus() == OrderStatus.INVALID) {
            return false;
        }
        notificationService.generateNotification(NotificationSubject.ORDER_PLACEMENT, order);
        orderRepo.add(order);
        return true;
    }

    public boolean shipOrder(int id) {
        // create el notification subject shipOrder
        Order order = orderRepo.findByID(id);
        // check if order is placed before shipping
        if(!(order.getStatus() == OrderStatus.PLACED))
            return false;
        notificationService.generateNotification(NotificationSubject.ORDER_SHIPMENT, order);
        orderRepo.updateStatus(OrderStatus.SHIPPED, id);
        order.deductShipmentFees(OrderService.this);
        // once the order is shipped, start a timer for preconfigured time
        new Thread(new Runnable() {
            // run in new thread so the request thread doesn't wait for preconfigured time and stops
            @Override
            public void run() {
                try {
                    Thread.sleep(PRECONFIGURED_TIME * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // condition to check if the order wasn't cancelled in another thread and still shipped
                if(order.getStatus() == OrderStatus.SHIPPED) {
                    order.setPreconfiguredTimeFinished(true);
                }
            }
        }).start();
        return true;
    }
    public boolean cancelPlacement(int id) {
        Order order = orderRepo.findByID(id);
        // check if order is placed before cancelling
        if(!(order.getStatus() == OrderStatus.PLACED))
            return false;
        notificationService.generateNotification(NotificationSubject.PLACEMENT_CANCELLATION, order);

        order.refund(this);

        orderRepo.delete(id);
        return true;
    }
    public boolean cancelShipment(int id) {
        Order order = orderRepo.findByID(id);
        // check if order is shipped before cancelling
        if(!(order.getStatus() == OrderStatus.SHIPPED))
            return false;
        // cancel only if preconfigured time isn't finished yet
       if(!order.isPreconfiguredTimeFinished()) {
           notificationService.generateNotification(NotificationSubject.SHIPMENT_CANCELLATION, order);
           orderRepo.updateStatus(OrderStatus.PLACED, id);
           return true;
       }
       return false;
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
        return customerRepo.findByUsername(customerUsername);
    }
    public ProductRepo getProductRepo() {
        return productRepo;
    }

}
