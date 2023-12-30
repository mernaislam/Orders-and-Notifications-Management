package app.models.Orders;

import app.models.Customer.Customer;
import app.models.Product.Product;

import java.util.ArrayList;

public class SimpleOrder extends Order{
    private ArrayList<Product> products = new ArrayList<>();

    public SimpleOrder(String customerUsername, ArrayList<Product> products) {
        super(customerUsername);
        this.products = products;
    }
    public ArrayList<Product> getProducts() {
        return products;
    }
}
