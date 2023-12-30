package app.models.Notification;

import app.models.Orders.Order;
import app.service.OrderService;

public class ShipmentCancellationNotification extends NotificationTemplate {
    private Order order;

    public ShipmentCancellationNotification(Language language, Order order, OrderService orderService) {
        super(language, orderService);
        this.order = order;
    }

    @Override
    public void generateText() {
        this.text = "Hello {customerName}! Your order shipment number #{orderID} has been successfully cancelled. " +
                     "We hope to see you again soon :(";
    }

    @Override
    public void addPlaceholders() {
//        Customer customer = orderService.getCustomer();
        // add the placeholders that was written in the translated generated text
//        this.placeholders.put("{customerName}", customer.getName());
        this.placeholders.put("{orderID}", String.valueOf(order.getOrderID()));
    }
}
