package app.models.Notification;

import app.models.Customer.Customer;
import app.models.Orders.Order;
import app.service.OrderService;

public class OrderPlacementNotification extends NotificationTemplate {
    private Order order;

    public OrderPlacementNotification(Language language, Order order, OrderService orderService) {
        super(language, orderService);
        this.order = order;
    }

    @Override
    public void generateText() {
        this.text = "Hello {customerName}! Your order number #{orderID} has been successfully placed. " +
                    "Thanks for using our store :)";
    }

    @Override
    public void addPlaceholders() {
        Customer customer = orderService.getCustomer(order.getCustomerUsername());
        // add the placeholders that was written in the translated generated text
        this.placeholders.put("{customerName}", customer.getName());
        this.placeholders.put("{orderID}", String.valueOf(order.getOrderID()));
    }
}
