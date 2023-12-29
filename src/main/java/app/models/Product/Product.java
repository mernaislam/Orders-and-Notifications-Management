package app.models.Product;

import app.service.ProductService;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Random;

@Entity
public class Product {
    private int productID;
    private String serialNumber;
    private String name;
    private String vendor;
    private Category category;
    private double price;

    public Product(){
        // generate random product
        this.serialNumber = "Serial Number";
        this.name = "Name";
        this.vendor = "Vendor";
        this.category = Category.getRandom();
        this.price = new Random().nextDouble() * (100.0 - 5.0) + 5.0;
    }

    public Product(int id, String name, String vendor, Category category, double price) {
        this.name = name;
        this.vendor = vendor;
        this.category = category;
        this.price = price;
        this.productID = id;
    }
    public Product(String name, String vendor, Category category, double price) {
        this.name = name;
        this.vendor = vendor;
        this.category = category;
        this.price = price;
        ProductService productService = new ProductService();
        this.productID = productService.generateProductId();
    }

    public int getProductID() {
        return productID;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getName() {
        return name;
    }

    public String getVendor() {
        return vendor;
    }

    public Category getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

}
