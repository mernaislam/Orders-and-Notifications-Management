package app.models.Orders;

public abstract class ProcessOrder {
    public final void processOrder(Order order) {
        // Template method
        calculateProductFees(order);
        calculateShipmentFees(order);
        calculateTotalFees(order);
    }
    public abstract void calculateProductFees(Order order);
    public abstract void calculateShipmentFees(Order order);
    public final void calculateTotalFees(Order order) {
        order.setTotalPrice();
    }
}
