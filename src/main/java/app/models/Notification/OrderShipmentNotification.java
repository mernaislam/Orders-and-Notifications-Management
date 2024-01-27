package app.models.Notification;

import app.models.Customer.Customer;
import app.models.Language.Language;
import app.models.Orders.Order;
import app.service.OrderService;

public class OrderShipmentNotification extends NotificationTemplate {
    private Order order;

    public OrderShipmentNotification(Language language, Order order, OrderService orderService) {
        super(language, orderService);
        this.order = order;
    }

    @Override
    public void generateText() {
        this.text = "Hello {customerName}! Your order number #{orderID} has been successfully shipped." +
                                                " You should receive your order in 3-4 business days :)";
    }

    @Override
    public void addPlaceholders() {
        Customer customer = orderService.getCustomer(order.getCustomerUsername());
        // add the placeholders that was written in the translated generated text
        this.placeholders.put("{customerName}", customer.getName());
        this.placeholders.put("{orderID}", String.valueOf(order.getOrderID()));
    }
}
