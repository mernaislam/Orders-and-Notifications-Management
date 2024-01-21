package app.models.Orders;

import app.models.Customer.Customer;
import app.models.Product.Product;
import app.service.OrderService;

import java.util.ArrayList;

public class SimpleOrder extends Order{
    private ArrayList<Product> products = new ArrayList<>();

    public SimpleOrder(String customerUsername, ArrayList<Product> products) {
        super(customerUsername);
        this.products = products;
    }
    public ArrayList<Product> getProducts() {
        return products;
    }

    @Override
    public void refund(OrderService orderService) {
        Customer customer = orderService.getCustomer(getCustomerUsername());
        customer.setBalance(customer.getBalance() + getProductsFees());
    }

    @Override
    public void deductShipmentFees(OrderService orderService){
        Customer customer = orderService.getCustomer(getCustomerUsername());
        customer.setBalance(customer.getBalance() - getShippingFees());
    }

    @Override
    public void deductProductsFees(OrderService orderService) {
        Customer customer = orderService.getCustomer(getCustomerUsername());
        customer.setBalance(customer.getBalance() - getProductsFees());
    }

    @Override
    public void setPreConfiguredTime(boolean preconfiguredTimeFinished) {
        this.preconfiguredTimeFinished = preconfiguredTimeFinished;
    }


}
