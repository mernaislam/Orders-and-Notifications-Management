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
                exception = new GlobalException("Order placed Successfully!", HttpStatus.OK);
            } else {
                exception = new GlobalException("Order cannot be placed, try again! [check balance or product quantities]", HttpStatus.BAD_REQUEST);
            }
        } else {
            exception = new GlobalException("Username doesn't match the Jwt token, try again!", HttpStatus.BAD_REQUEST);
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
                exception = new GlobalException("Order placed Successfully!", HttpStatus.OK);
            } else {
                exception = new GlobalException("Order cannot be placed, try again! [check balance or product quantities]", HttpStatus.BAD_REQUEST);
            }
        } else {
            exception = new GlobalException("No such username exists, try again!", HttpStatus.BAD_REQUEST);
        }
        return exceptionController.GlobalException(exception);
    }

    // Cancels Placement and then deletes order
    @DeleteMapping (path="/cancelPlacement/{id}")
    public ResponseEntity<ResponseEntityStructure> deleteOrder(@PathVariable(name = "id") int id, @RequestHeader("Authorization") String token) {
        GlobalException exception;
        Order order = orderService.findOrderById(id);
        if(order != null){
            if(jwtTokenUtil.getUsernameFromToken(token.substring(7)).equals(order.getCustomerUsername())){
                if(orderService.cancelPlacement(id)){
                    exception = new GlobalException("Order Placement is cancelled Successfully!", HttpStatus.OK);
                } else {
                    exception = new GlobalException("Order Placement cannot be cancelled, try again!", HttpStatus.BAD_REQUEST);
                }
            } else {
                exception = new GlobalException("Order id doesn't match order username, try again!", HttpStatus.BAD_REQUEST);
            }
        } else {
            exception = new GlobalException("Invalid order id, try again!", HttpStatus.BAD_REQUEST);
        }
        return exceptionController.GlobalException(exception);
    }

    // Returns order by id
    @GetMapping (path="/order/{id}")
    public Object getOrderById(@PathVariable(name = "id") int id, @RequestHeader("Authorization") String token) {
        GlobalException exception;
        Order order = orderService.findOrderById(id);
        if(order != null){
            if(jwtTokenUtil.getUsernameFromToken(token.substring(7)).equals(order.getCustomerUsername())){
                return order;
            } else {
                exception = new GlobalException("Order id doesn't match order username, try again!", HttpStatus.BAD_REQUEST);
            }
        } else {
            exception = new GlobalException("Invalid order id, try again!", HttpStatus.BAD_REQUEST);
        }
        return exceptionController.GlobalException(exception);
    }

    // Returns list of all orders in the system [no authorization]
    @GetMapping (path="/order")
    public Object getOrders() { // works
        ArrayList<Order> orders = orderService.getOrders();
        if(orders.isEmpty()){
            GlobalException exception = new GlobalException("No Orders are placed yet!", HttpStatus.OK);
            return exceptionController.GlobalException(exception);
        } else {
            return orders;
        }
    }

    // Ships Order
    @PutMapping (path="/shipOrder/{id}")
    public ResponseEntity<ResponseEntityStructure> shipOrder(@PathVariable(name = "id") int id, @RequestHeader("Authorization") String token) {
        GlobalException exception;
        Order order = orderService.findOrderById(id);
        if(order != null){
            if(jwtTokenUtil.getUsernameFromToken(token.substring(7)).equals(order.getCustomerUsername())){
                if(orderService.shipOrder(id)){
                    exception = new GlobalException("Order is shipped Successfully!", HttpStatus.OK);
                } else {
                    exception = new GlobalException("Order cannot be shipped, try again!", HttpStatus.BAD_REQUEST);
                }
            } else {
                exception = new GlobalException("Order id doesn't match order username, try again!", HttpStatus.BAD_REQUEST);
            }
        } else {
            exception = new GlobalException("Invalid order id, try again!", HttpStatus.BAD_REQUEST);
        }
        return exceptionController.GlobalException(exception);
    }

    // Cancels Shipment
    @PutMapping (path="/cancelShipment/{id}")
    public ResponseEntity<ResponseEntityStructure> cancelShipment(@PathVariable(name = "id") int id, @RequestHeader("Authorization") String token) {
        GlobalException exception;
        Order order = orderService.findOrderById(id);
        if(order != null){
            if(jwtTokenUtil.getUsernameFromToken(token.substring(7)).equals(order.getCustomerUsername())){
                if(orderService.cancelShipment(id)){
                    exception = new GlobalException("Order shipment is cancelled Successfully!", HttpStatus.OK);
                } else {
                    exception = new GlobalException("Order shipment cannot be cancelled!", HttpStatus.BAD_REQUEST);
                }
            } else {
                exception = new GlobalException("Order id doesn't match order username, try again!", HttpStatus.BAD_REQUEST);
            }
        } else {
            exception = new GlobalException("Invalid order id, try again!", HttpStatus.BAD_REQUEST);
        }
        return exceptionController.GlobalException(exception);
    }

}
