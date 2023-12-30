package app.models.Orders;

import app.models.Product.Product;
import app.repos.OrderRepo;
import app.repos.ProductRepo;
import app.service.OrderService;
import app.service.ProductService;

public abstract class ProcessOrder {
    protected OrderService orderService;
    protected ProductRepo productRepo;
    public ProcessOrder(OrderService orderService, ProductRepo productRepo) {
        this.orderService = orderService;
        this.productRepo = productRepo;
    }
    public final void processOrder(Order order) {
        // Template method - Skeleton
        assignOrderID(order);
        calculateProductFees(order);
        calculateShippingFees(order);
        calculateTotalFees(order);
        validateOrder(order);
    }

    public abstract void assignOrderID(Order order);

    public abstract void calculateProductFees(Order order);
    public abstract void calculateShippingFees(Order order);
    public abstract void validateOrder(Order order);
    public final void calculateTotalFees(Order order) {
        order.setTotalPrice(order.productsFees + order.shippingFees);
    }
}
