package app.controllers;

import app.models.Orders.CompoundOrder;
import app.models.Orders.Order;
import app.models.Orders.OrderStatus;
import app.models.Orders.SimpleOrder;
import app.security.GlobalException;
import app.security.JwtTokenUtil;
import app.security.ResponseEntityStructure;
import app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path="/api")
public class OrderController {
    private final OrderService orderService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    private GlobalExceptionController exceptionController = new GlobalExceptionController();

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Add a new simpleOrder to OrderRepo
    @PostMapping (path="/placeOrder/simpleOrder")
    public ResponseEntity<ResponseEntityStructure> addSimpleOrder(@RequestBody SimpleOrder order, @RequestHeader("Authorization") String token) throws GlobalException { // works
        GlobalException globalException;
        if(jwtTokenUtil.getUsernameFromToken(token.substring(7)).equals(order.getCustomerUsername())){
            orderService.addSimpleOrder(order);
            if(order.getStatus() == OrderStatus.INVALID){
                return null;
            }
        }
        return null;
    }

    // Add a new CompoundOrder to OrderRepo
    @PostMapping (path="/placeOrder/compoundOrder")
    public void addCompoundOrder(@RequestBody CompoundOrder order) { // works
        orderService.addCompoundOrder(order);
    }

    // Delete an existing order
    @DeleteMapping (path="/order/{id}")
    public void deleteOrder(@PathVariable(name = "id") int id) { // works
        orderService.cancelPlacement(id);
    }

    // Returns order by id
    @GetMapping (path="/order/{id}")
    public Order getOrderById(@PathVariable(name = "id") int id) { // works
        return orderService.findOrderById(id);
    }

    // Returns list of orders
    @GetMapping (path="/order")
    public ArrayList<Order> getOrders() { // works
        return orderService.getOrders();
    }

    // Ships Order
    @PutMapping (path="/shipOrder/{id}")
    public void shipOrder(@PathVariable(name = "id") int id) { // works
         orderService.shipOrder(id);
    }

    // Cancels Shipment
    @PutMapping (path="/cancelShipment/{id}")
    public void cancelShipment(@PathVariable(name = "id") int id) {
        orderService.cancelShipment(id);
    }

}
