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
    public ResponseEntity<ResponseEntityStructure> addSimpleOrder(@RequestBody SimpleOrder order, @RequestHeader("Authorization") String token) {
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
    public ResponseEntity<ResponseEntityStructure> addCompoundOrder(@RequestBody CompoundOrder order, @RequestHeader("Authorization") String token) {
        GlobalException exception;
        if(jwtTokenUtil.getUsernameFromToken(token.substring(7)).equals(order.getCustomerUsername())){
            orderService.addCompoundOrder(order);
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

    // Delete an existing order
    @DeleteMapping (path="/order/{id}")
    public void deleteOrder(@PathVariable(name = "id") int id) { // works
        orderService.cancelPlacement(id);
    }

    // Returns order by id
    @GetMapping (path="/order/{id}")
    public Object getOrderById(@PathVariable(name = "id") int id) { // works
        Order order = orderService.findOrderById(id);
        if(order == null){
            GlobalException exception = new GlobalException("Invalid order id", HttpStatus.BAD_REQUEST);
            return exceptionController.GlobalException(exception);
        } else {
            return order;
        }
    }

    // Returns list of orders
    @GetMapping (path="/order")
    public Object getOrders() { // works
        ArrayList<Order> orders = orderService.getOrders();
        if(orders == null){
            GlobalException exception = new GlobalException("No Orders are placed yet", HttpStatus.BAD_REQUEST);
            return exceptionController.GlobalException(exception);
        } else {
            return orders;
        }
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
