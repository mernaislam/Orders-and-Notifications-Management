package app.models.Orders;

import app.models.Product.Product;
import app.repos.ProductRepo;
import app.service.OrderService;

import java.util.ArrayList;
import java.util.Random;

public class ProcessSimpleOrder extends ProcessOrder {


    public ProcessSimpleOrder(OrderService orderService, ProductRepo productRepo) {
        super(orderService, productRepo);
    }

    @Override
    public void assignOrderID(Order order) {
        order.setOrderID(orderService.generateID());
    }

    @Override
    public void calculateProductFees(Order order) {
        SimpleOrder simpleOrder = (SimpleOrder) order;
        double productsFees = 0;
        for (Product product : simpleOrder.getProducts()) {
            productsFees += product.getPrice() * product.getQuantity();
        }
        order.setProductsFees(productsFees);
    }

    @Override
    public void calculateShippingFees(Order order) {
        // should be calculated based on customer's city (random for now)
        double shippingFees = new Random().nextDouble() * (100.0 - 50.0) + 50.0;
        order.setShippingFees(shippingFees);

    }

    @Override
    public void validateOrder(Order ord) {
        SimpleOrder order = (SimpleOrder) ord;
//         law el order rg3ly status invalid m3mlosh add
        ord.setStatus(OrderStatus.INVALID);
        if (orderService.orderExists(order.getOrderID())
            || !orderService.userExists(order.getCustomer().getCustomerID())
            || !orderService.hasMoney(order.getCustomer(), order.getTotalPrice())){
            return;
        }
        for (Product p : order.getProducts()) {
            Product repoProduct = productRepo.findByID(p.getProductID());
            //                                      quantity el m3aya  - quantity el hashtreha
            if (repoProduct == null || repoProduct.getQuantity() - p.getQuantity() < 0) {
                return;
            }
        }
        // for loop to decrement product quantity
        for (Product p : order.getProducts()) {
            Product repoProduct = productRepo.findByID(p.getProductID());
            repoProduct.setQuantity(repoProduct.getQuantity() - p.getQuantity());
            if (repoProduct.getQuantity()== 0) {
                productRepo.delete(repoProduct.getProductID());
            }
        }
        ord.customer.setBalance(order.customer.getBalance() - order.getTotalPrice());
        ord.setStatus(OrderStatus.PLACED);
    }
}
