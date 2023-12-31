package app.models.Orders;

import app.models.Product.Product;

import java.util.ArrayList;

public class OrderHelper {
    private String customerUsername;
    private ArrayList<Product> products;
    private ArrayList<SimpleOrder> orders;

    public OrderHelper(String customerUsername, ArrayList<Product> products, ArrayList<SimpleOrder> orders) {
        this.customerUsername = customerUsername;
        this.products = products;
        this.orders = orders;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<SimpleOrder> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<SimpleOrder> orders) {
        this.orders = orders;
    }

    public void addOrder(SimpleOrder order){
        orders.add(order);
    }
}
