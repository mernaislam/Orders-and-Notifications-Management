package app.service;

import app.models.Orders.*;
import app.repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderService {
    private final OrderRepo orderRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
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
        // create el notification subject placeOrder
        orderRepo.add(order);
    }
    public void addCompoundOrder(CompoundOrder order) {
        //validations el compound order hena
        ProcessOrder orderProcessor = new ProcessCompoundOrder();
        orderProcessor.processOrder(order);
        // create el notification subject placeOrder
        orderRepo.add(order);
    }

    public void deleteOrder(int id) {
        // check if order exists
        orderRepo.delete(id);
        // order cancelled notification
    }

    public void shipOrder(int id) {
        // create el notification subject shipOrder
        orderRepo.updateStatus(OrderStatus.SHIPPED, id);
    }
}