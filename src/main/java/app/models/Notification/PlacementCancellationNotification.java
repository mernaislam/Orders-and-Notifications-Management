package app.models.Notification;

import app.models.Orders.Order;
import app.service.OrderService;

public class PlacementCancellationNotification extends NotificationTemplate{
    private Order order;

    public PlacementCancellationNotification(Language language, Order order, OrderService orderService) {
        super(language, orderService);
        this.order = order;
    }

    @Override
    public void generateText() {
        this.text = "Hello {customerName}! Your order placement number #{orderID} has been successfully cancelled. " +
                    "A full refund will be provided for your cancelled order :)";
    }

    @Override
    public void addPlaceholders() {
//        Customer customer = orderService.getCustomer();
        // add the placeholders that was written in the translated generated text
//        this.placeholders.put("{customerName}", customer.getName());
        this.placeholders.put("{orderID}", String.valueOf(order.getOrderID()));
    }
}
