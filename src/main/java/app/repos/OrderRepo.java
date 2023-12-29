package app.repos;

import app.models.Orders.Order;

import java.util.ArrayList;

public class OrderRepo implements Repository{
    private ArrayList<Order> orders = new ArrayList<>();

    public OrderRepo() {
        loadData();
    }

    @Override
    public void add(Object object) {

    }

    @Override
    public void delete(Object object) {

    }

    @Override
    public void update(Object object) {

    }

    @Override
    public void save(Object object) {

    }

    @Override
    public Object findByID(int id) {
        return null;
    }

    @Override
    public void loadData() {
        // load orders

    }
}
