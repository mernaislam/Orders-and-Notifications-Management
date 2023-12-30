package app.models.Orders;

import app.models.Customer.Customer;
import app.models.Product.Product;

import java.time.LocalDate;
import java.util.ArrayList;

public class CompoundOrder extends Order{
    private ArrayList<SimpleOrder> orders = new ArrayList<>();

    public CompoundOrder(String customerUsername, ArrayList<SimpleOrder> orders) {
        super(customerUsername);
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
