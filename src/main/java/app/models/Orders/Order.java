package app.models.Orders;

import app.models.Customer.Customer;
import app.models.Product.Product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

@Entity
public abstract class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int orderID;
    protected Customer customer;
    protected Double productsFees; // to be calculated when the order is placed
    protected Double shippingFees; // to be calculated when the order is shipped
    protected Double totalPrice; // to be calculated when the order is placed and shipped
    protected LocalDate orderDate;
    protected ArrayList<Product> products = new ArrayList<>();

    public Order(){
        // generate random order
        this.customer = new Customer();
        this.orderDate = LocalDate.now();
        int numOfProducts = new Random().nextInt() * (15 - 1) + 1;
        for(int i = 0; i < numOfProducts; i++){
            this.products.add(new Product());
        }
    }
    public Order(Customer customer, ArrayList<Product> products){
        this.customer = customer;
        this.products = products;
        this.orderDate = LocalDate.now();
    }
    public Customer getCustomer() {
        return customer;
    }

    public int getOrderID() {
        return orderID;
    }

    public Double getProductsFees() {
        return productsFees;
    }

    public Double getShippingFees() {
        return shippingFees;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProductsFees(Double productsFees) {
        this.productsFees = productsFees;
    }

    public void setShippingFees(Double shippingFees) {
        this.shippingFees = shippingFees;
    }

    public void setTotalPrice() {
        // to be called after setting productsFees and shippingFees
        this.totalPrice = this.productsFees + this.shippingFees;
    }
}
