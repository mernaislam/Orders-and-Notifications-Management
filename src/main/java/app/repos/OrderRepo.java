package app.repos;

import app.models.Customer.Customer;
import app.models.Orders.CompoundOrder;
import app.models.Orders.Order;
import app.models.Orders.SimpleOrder;
import app.models.Product.Category;
import app.models.Product.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class OrderRepo implements Repository<Order> {
    private ArrayList<Order> orders = new ArrayList<>();

    public OrderRepo() {
        loadData();
    }

    @Override
    public void add(Order ord) {

    }

    @Override
    public void delete(int ordID) {

    }

    @Override
    public void delete(Order ord) {

    }

    @Override
    public void update(Order ord, int ordID) {

    }

    @Override
    public void save(Order ord) {

    }

    @Override
    public Order findByID(int ordID) {

        return null;
    }

    @Override
    public ArrayList<Order> getAll() {
        return null;
    }

    @Override
    public void loadData() {
        // load orders
    }
}
