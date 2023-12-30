package app.models.Orders;

import app.models.Customer.Customer;
import app.models.Product.Product;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Order {
    protected int orderID;
    protected Customer customer;
    protected Double productsFees; // to be calculated when the order is placed
    protected Double shippingFees; // to be calculated when the order is shipped
    protected Double totalPrice; // to be calculated when the order is placed and shipped
    protected LocalDate orderDate;
    protected OrderStatus status;

    public Order(Customer customer){
        // id generated randomly
        this.customer = customer;
        this.productsFees = 0.0;
        this.shippingFees = 0.0;
        this.totalPrice = 0.0;
        this.orderDate = LocalDate.now();
        this.status = OrderStatus.PENDING;
    }
    public Customer getCustomer() {
        return customer;
    }

    public int getOrderID() {
        return orderID;
    }

    public Double getProductsFees() {
        return productsFees;
    }

    public Double getShippingFees() {
        return shippingFees;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }


    public void setProductsFees(Double productsFees) {
        this.productsFees = productsFees;
    }

    public void setShippingFees(Double shippingFees) {
        this.shippingFees = shippingFees;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

}
