package app.service;

import app.models.Orders.Order;
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

    public void addOrder(Order order) {
        orderRepo.add(order);
    }

    public void updateOrder(Order order, int id) {
        orderRepo.update(order, id);
    }

    public void deleteOrder(int id) {
        orderRepo.delete(id);
    }
}
