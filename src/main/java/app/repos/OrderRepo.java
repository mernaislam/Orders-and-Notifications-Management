package app.repos;

import app.models.Orders.CompoundOrder;
import app.models.Orders.Order;
import app.models.Orders.SimpleOrder;
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
        orders.add(ord);
    }

    @Override
    public void delete(int ordID) {
//        System.out.println("delete order");
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderID() == ordID) {
                orders.remove(i);
                break;
            }
        }
    }

    @Override
    public void update(Order ord, int ordID) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderID() == ordID) {
                orders.set(i, ord);
                break;
            }
        }
    }

    @Override
    public Order findByID(int ordID) {
        for (Order order : orders) {
            if (order.getOrderID() == ordID)
                return order;
        }
        return null;
    }

    @Override
    public ArrayList<Order> getAll() {
        return orders;
    }

    @Override
    public void loadData() {
        // load orders
        orders.add(new SimpleOrder());
        orders.add(new CompoundOrder());
        orders.add(new SimpleOrder());
        orders.add(new CompoundOrder());
        orders.add(new SimpleOrder());
    }
}
