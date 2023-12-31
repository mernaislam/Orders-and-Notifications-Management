package app.models.Orders;

import app.models.Customer.Customer;
import app.models.Product.Product;
import app.service.OrderService;

import java.time.LocalDate;
import java.util.ArrayList;

public class CompoundOrder extends Order{
    private ArrayList<SimpleOrder> orders;

    public CompoundOrder(String customerUsername, ArrayList<SimpleOrder> orders) {
        super(customerUsername);
        this.orders = orders;
    }

    public ArrayList<SimpleOrder> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<SimpleOrder> orders) {
        this.orders = orders;
    }
    @Override
    public void setStatus(OrderStatus status) {
        super.setStatus(status);
        for(SimpleOrder order : orders){
            order.setStatus(status);
        }
    }

    @Override
    public void refund(OrderService orderService) {
        for(SimpleOrder o : orders){
            o.refund(orderService);
        }
    }

    @Override
    public void deductShipmentFees(OrderService orderService) {
        for(SimpleOrder o : orders){
            o.deductShipmentFees(orderService);
        }
    }

    @Override
    public void deductProductsFees(OrderService orderService) {
        for(SimpleOrder o : orders){
            o.deductProductsFees(orderService);
        }
    }

    @Override
    public void setPreConfiguredTime(boolean preconfiguredTimeFinished) {
        for(SimpleOrder o : orders){
            o.setPreConfiguredTime(preconfiguredTimeFinished);
        }
    }
}
