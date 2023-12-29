package app.controllers;

import app.models.Orders.Order;
import app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path="/app/order")
public class OrderController {
    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    // Add a new order to OrderRepo
    @PostMapping (path="/addOrder")
    public void addOrder(@RequestBody Order order) { // doesn't work
        // el json fel request body byrg3 Order object 3la tol bs mby3rfsh 3shan da abstract fa bygeb error
        orderService.addOrder(order);
    }
    // Delete an existing order
    @DeleteMapping (path="/deleteOrder{id}")
    public void deleteOrder(@PathVariable(name = "id") int id) { // works
        orderService.deleteOrder(id);
    }
    // Update an existing order
    @PutMapping (path="/updateOrder{id}") // doesn't work
    public void updateOrder(@RequestBody Order order, @PathVariable(name = "id") int id) {
        // el json fel request body byrg3 Order object 3la tol bs mby3rfsh 3shan da abstract fa bygeb error
        orderService.updateOrder(order, id);
    }
    // Returns order by id
    @GetMapping (path="/findOrder{id}")
    public Order getOrderById(@PathVariable(name = "id") int id) { // works
        //m3rfsh hena 7war <optional> da bayn
        return orderService.findOrderById(id);
    }
    // Returns list of orders
    @GetMapping (path="/getOrders")
    public ArrayList<Order> getOrders() { // works
        return orderService.getOrders();
    }
}