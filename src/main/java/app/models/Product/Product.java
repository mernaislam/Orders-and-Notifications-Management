package app.models.Product;

import app.service.ProductService;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Random;

public class Product {

    private int productID;
    private String name;
    private String vendor;
    private Category category;
    private double price;
    private int quantity;

    public Product(){
        // generate random product
        // id should be generated automatically
        this.name = "Name";
        this.vendor = "Vendor";
        this.category = Category.getRandom();
        this.price = new Random().nextDouble() * (100.0 - 5.0) + 5.0;
        this.quantity = (int) (new Random().nextDouble() * (100.0 - 5.0) + 5.0);
    }

    public Product(int id, String name, String vendor, Category category, double price, int quantity) {
        this.name = name;
        this.vendor = vendor;
        this.category = category;
        this.price = price;
        this.productID = id;
        this.quantity = quantity;
    }
    public int getProductID() {
        return productID;
    }
    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public void copy(Product p){
        this.name = p.name;
        this.vendor = p.vendor;
        this.category = p.category;
        this.price = p.price;
        this.productID = p.productID;
        this.quantity = p.quantity;
    }
}
