package app.models.Orders;

import app.models.Customer.Customer;
import app.models.Product.Product;

import java.util.ArrayList;

public class SimpleOrder extends Order{
    public SimpleOrder(Customer customer, int orderID, ArrayList<Product> products) {
        super(customer, orderID,products);
    }
}
