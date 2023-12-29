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
    protected ArrayList<Product> products;

    public Order(Customer customer, int orderID, ArrayList<Product> products){
        this.customer = customer;
        this.orderID = orderID;
        this.products = products;
        this.orderDate = LocalDate.now();
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

    public ArrayList<Product> getProducts() {
        return products;
    }
}
