package app.repos;

import app.models.Customer.Customer;
import app.models.Orders.Order;
import app.models.Orders.OrderStatus;
import app.models.Orders.SimpleOrder;
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
        orders.add(ord);
    }

    @Override
    public void delete(int ordID) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderID() == ordID) {
                orders.remove(i);
                break;
            }
        }
    }
    @Override
    public void update(Order ord, int ordID) {
        return;
    }
    public void updateStatus(OrderStatus status,int ordID) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderID() == ordID) {
                Order order = orders.get(i);
                order.setStatus(status);
                orders.set(i, order);
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
        ArrayList<Product> orderItems = new ArrayList<>();

        orderItems.add(new Product());
        Order simpleOrder1 = new SimpleOrder("",  orderItems);
        orders.add(simpleOrder1);
        // order2
        orderItems = new ArrayList<>();
        orderItems.add(new Product());
        orderItems.add(new Product());
        Order simpleOrder2 = new SimpleOrder("",  orderItems);
        orders.add(simpleOrder2);
        // order3
        orderItems = new ArrayList<>();
        orderItems.add(new Product());
        orderItems.add(new Product());
        Order simpleOrder3 = new SimpleOrder("",  orderItems);
        orders.add(simpleOrder3);

    }
}
