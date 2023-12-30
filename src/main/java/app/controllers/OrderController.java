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
import org.springframework.http.HttpStatus;
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
        GlobalException exception;
        if(jwtTokenUtil.getUsernameFromToken(token.substring(7)).equals(order.getCustomerUsername())){
            orderService.addSimpleOrder(order);
            if(order.getStatus() == OrderStatus.PLACED){
                exception = new GlobalException("Order placed Successfully", HttpStatus.OK);
            } else {
                exception = new GlobalException("Order cannot be placed, try again [check balance or product quantities]", HttpStatus.BAD_REQUEST);
            }
        } else {
            exception = new GlobalException("No such username exists, try again", HttpStatus.BAD_REQUEST);
        }
        return exceptionController.GlobalException(exception);
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
