//package app.models.Orders;
//
//import app.models.Product.Product;
//
//import java.util.Random;
//
//public class ProcessSimpleOrder extends ProcessOrder{
//    @Override
//    public void calculateProductFees(Order order) {
//        double productsFees = 0;
//        for(Product product : order.getProducts()){
//            productsFees += product.getPrice();
//        }
//        order.setProductsFees(productsFees);
//    }
//
//    @Override
//    public void calculateShipmentFees(Order order) {
//        // should be calculated based on customer's city (random for now)
//        double shippingFees = new Random().nextDouble() * (100.0 - 50.0) + 50.0;
//        order.setShippingFees(shippingFees);
//
//    }
//}
