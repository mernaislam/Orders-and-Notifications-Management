package app.models.Orders;

public abstract class ProcessOrder {
    public final void processOrder(Order order) {
        // Template method
        calculateProductFees(order);
        calculateShipmentFees(order);
    }
    public abstract void calculateProductFees(Order order);
    public abstract void calculateShipmentFees(Order order);
}
