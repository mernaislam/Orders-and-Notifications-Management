package app.models.Orders;

import app.models.Customer.Customer;
import app.models.Product.Product;
import app.repos.ProductRepo;
import app.service.OrderService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class ProcessCompoundOrder extends ProcessOrder {


    public ProcessCompoundOrder(OrderService orderService, ProductRepo productRepo) {
        super(orderService, productRepo);
    }

    @Override
    public void assignOrderID(Order order) {
        int id = orderService.generateID();
        order.setOrderID(id);
    }

    @Override
    public void calculateProductFees(Order order) {
        CompoundOrder compoundOrder = (CompoundOrder) order;
        double totalProductsFees = 0;
        for (SimpleOrder o : compoundOrder.getOrders()) {
            totalProductsFees += o.getProductsFees();
        }
        order.setProductsFees(totalProductsFees);
    }

    @Override
    public void calculateShippingFees(Order order) {
        CompoundOrder compoundOrder = (CompoundOrder) order;
        double minShippingFees = 100;
        for (SimpleOrder o : compoundOrder.getOrders()) {
            minShippingFees = Math.min(minShippingFees, o.getShippingFees());
        }

        // should be calculated based on customer's city (random for now)
        int numberOfOrder = compoundOrder.getOrders().size();
        double shippingFees = minShippingFees;
        shippingFees /= numberOfOrder;

        for (SimpleOrder o : compoundOrder.getOrders()) {
            o.setShippingFees(shippingFees);
            o.setTotalPrice(o.getProductsFees() + shippingFees);
        }

        order.setShippingFees(minShippingFees);
    }

    @Override
    public void validateOrder(Order ord) {
        CompoundOrder order = (CompoundOrder) ord;

        // validate city
        order.setStatus(OrderStatus.INVALID);
        String city = null;
        for (SimpleOrder o : order.getOrders()) {
            Customer customer = orderService.getCustomer(o.getCustomerUsername());
            if (city == null && customer != null)
                city = customer.getShippingAddress().getCity();
            if (customer == null || !Objects.equals(city, customer.getShippingAddress().getCity())
                || !orderService.hasMoney(customer, o.getShippingFees())) {
                return;
            }

        }
        order.setStatus(OrderStatus.PLACED);
    }
}
