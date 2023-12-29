package app.models.Notification;

import app.models.Orders.Order;

public class OrderShipmentNotification extends NotificationTemplate {
    private Order order;

    public OrderShipmentNotification(Language language, Order order) {
        super(language);
        this.order = order;
    }

    @Override
    public void generateText() {
        this.text = "Hello {customerName}! Your order has been successfully shipped." +
                                                " You should receive your order in 3-4 business days :)";
    }

    @Override
    public void addPlaceholders() {
        // add the placeholders that was written in the translated generated text
        this.placeholders.put("{customerName}", order.getCustomer().getName());
    }
}
