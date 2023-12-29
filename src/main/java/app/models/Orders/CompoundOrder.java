package app.models.Orders;

import app.models.Customer.Customer;
import app.models.Product.Product;

import java.util.ArrayList;
import java.util.Random;

public class CompoundOrder extends Order{
    private ArrayList<Order> orders = new ArrayList<>();

    public CompoundOrder(){
        // generate random compound order
        super();
        int numOfProducts = new Random().nextInt() * (15 - 1) + 1;
        for(int i = 0; i < numOfProducts; i++){
            this.orders.add(new SimpleOrder());
        }
    }

    public CompoundOrder(Customer customer, ArrayList<Product> products, ArrayList<Order> orders) {
        super(customer, products);
        this.orders = orders;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
}
