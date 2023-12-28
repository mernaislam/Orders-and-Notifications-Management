package app.models.Orders;

import app.models.Product.Product;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Order {
    protected int orderId;
    protected int customerID;
    protected Double productsFees;
    protected Double shippingFees;
    protected Double totalCost;
    protected LocalDate orderDate;
    protected ArrayList<Product> products;

    public int getCustomerID() {
        return customerID;
    }
}
