package app.models.Orders;

import app.models.Customer.Customer;
import app.models.Product.Product;

import java.util.ArrayList;

public class CompoundOrder extends Order{
    private ArrayList<SimpleOrder> orders = new ArrayList<>();

    public CompoundOrder(Customer customer, ArrayList<Product> products, ArrayList<SimpleOrder> orders) {
        super(customer, products);
        this.orders = orders;
    }

    public ArrayList<SimpleOrder> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<SimpleOrder> orders) {
        this.orders = orders;
    }
    @Override
    public void setStatus(OrderStatus status) {
        super.setStatus(status);
        for(SimpleOrder order : orders){
            order.setStatus(status);
        }
    }
}
