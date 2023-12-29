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
        orders.add(ord);
    }

    @Override
    public void delete(int ordID) {
        System.out.println("delete order");
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderID() == ordID) {
                orders.remove(i);
                break;
            }
        }
    }

    @Override
    public void delete(Order ord) {

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
    public void save(Order ord) {

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
        Customer c1 = new Customer(1,
                "Nada Mohamed", "nadaamohhamed",
                "123456789", "nada@email.com",
                "6,Street 9", "Cairo",
                "01234256781", 500.0);
        Customer c2 = new Customer(2,
                "Merna Islam", "mernaislam",
                "merna12", "merna@email.com",
                "2,Street 50", "Giza",
                "0123356782", 250.0);
        Customer c3 = new Customer(3,
                "Waleed", "snowkaai",
                "s123", "waleed@email.com",
                "3,Street 70", "Cairo",
                "0123123123", 1000.0);
        // order1
        ArrayList<Product> orderItems = new ArrayList<>();
        orderItems.add(new Product(1,"Apple", "Vendor", Category.CATEGORY1, 10.0));
        Order simpleOrder1 = new SimpleOrder(c1, orders.size() + 1, orderItems);
        orders.add(simpleOrder1);
        // order2
        orderItems = new ArrayList<>();
        orderItems.add(new Product(2,"Banana", "Vendor", Category.CATEGORY1, 5.0));
        orderItems.add(new Product(3,"Milk", "Vendor", Category.CATEGORY2, 20.0));
        Order simpleOrder2 = new SimpleOrder(c2, orders.size() + 1, orderItems);
        orders.add(simpleOrder2);
        // order3
        orderItems = new ArrayList<>();
        orderItems.add(new Product(4,"Cheese", "Vendor", Category.CATEGORY3, 15.0));
        Order simpleOrder3 = new SimpleOrder(c3, orders.size() + 1, orderItems);
        orders.add(simpleOrder3);
    }
}
