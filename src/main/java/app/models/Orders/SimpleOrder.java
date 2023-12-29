package app.models.Orders;

import app.models.Customer.Customer;
import app.models.Product.Product;

import java.util.ArrayList;

public class SimpleOrder extends Order{
    public SimpleOrder(){
        super();
    }
    public SimpleOrder(Customer customer, ArrayList<Product> products) {
        super(customer, products);
    }
}
