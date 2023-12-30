package app.controllers;

import app.models.Orders.CompoundOrder;
import app.models.Orders.Order;
import app.models.Orders.SimpleOrder;
import app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path="/api/order")
public class OrderController {
    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    // Add a new simpleOrder to OrderRepo
    @PostMapping (path="/placeOrder/simpleOrder")
    public void addSimpleOrder(@RequestBody SimpleOrder order) { // works
        orderService.addSimpleOrder(order);
    }
    // Add a new CompoundOrder to OrderRepo
    @PostMapping (path="/placeOrder/compoundOrder")
    public void addCompoundOrder(@RequestBody CompoundOrder order) { // works
        orderService.addCompoundOrder(order);
    }
    // Delete an existing order
    @DeleteMapping (path="/deleteOrder/{id}")
    public void deleteOrder(@PathVariable(name = "id") int id) { // works
        orderService.cancelOrder(id);
    }

    // Returns order by id
    @GetMapping (path="/findOrder/{id}")
    public Order getOrderById(@PathVariable(name = "id") int id) { // works
        //m3rfsh hena 7war <optional> da bayn
        return orderService.findOrderById(id);
    }
    // Returns list of orders
    @GetMapping (path="/getOrders")
    public ArrayList<Order> getOrders() { // works
        return orderService.getOrders();
    }
    // Ships Order
    @GetMapping (path="/shipOrder/{id}")
    public void shipOrder(@PathVariable(name = "id") int id) { // works
         orderService.shipOrder(id);
    }
}