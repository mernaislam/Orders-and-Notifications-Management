package app.models.Notification;

import app.models.Orders.Order;

public class OrderCancellationNotification extends NotificationTemplate{
    private Order order;

    public OrderCancellationNotification(Language language, Order order) {
        super(language);
        this.order = order;
    }

    @Override
    public void generateText() {
        this.text = "Hello {customerName}! Your order number #{orderID} has been successfully cancelled. " +
                "We hope to see you again soon :(";
    }

    @Override
    public void addPlaceholders() {
        // add the placeholders that was written in the translated generated text
        this.placeholders.put("{customerName}", order.getCustomer().getName());
        this.placeholders.put("{orderID}", String.valueOf(order.getOrderID()));
    }
}
