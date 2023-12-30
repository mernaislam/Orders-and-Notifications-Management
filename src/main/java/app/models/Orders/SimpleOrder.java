package app.models.Orders;

import app.models.Customer.Customer;
import app.models.Product.Product;

import java.util.ArrayList;

public class SimpleOrder extends Order{
    private ArrayList<Product> products = new ArrayList<>();

    public SimpleOrder(Customer customer, ArrayList<Product> products) {
        super(customer);
        this.products = products;
    }
    public ArrayList<Product> getProducts() {
        return products;
    }
}
