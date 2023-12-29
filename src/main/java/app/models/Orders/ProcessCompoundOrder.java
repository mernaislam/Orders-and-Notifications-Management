package app.models.Orders;

import app.models.Product.Product;

import java.util.Random;

public class ProcessCompoundOrder extends ProcessOrder{
    @Override
    public void calculateProductFees(Order order) {
        CompoundOrder compoundOrder = (CompoundOrder) order;
        double totalProductsFees = 0;
        for(Order o : compoundOrder.getOrders()){
            double productsFees = 0;
            for(Product product : o.getProducts()){
                productsFees += product.getPrice();
            }
            o.setProductsFees(productsFees);
            totalProductsFees += productsFees;
        }
        order.setProductsFees(totalProductsFees);
    }

    @Override
    public void calculateShippingFees(Order order) {
        // should be calculated based on customer's city (random for now)
        double totalShippingFees = 0;
        CompoundOrder compoundOrder = (CompoundOrder) order;
        for(Order o : compoundOrder.getOrders()){
            double shippingFees = new Random().nextDouble() * (100.0 - 50.0) + 50.0;
            o.setShippingFees(shippingFees);
            totalShippingFees += shippingFees;
        }
        order.setShippingFees(totalShippingFees);
    }
}
