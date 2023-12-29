package app.models.Product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Random;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Product(String name, String vendor, Category category, double price) {
        // serialNumber should be generated automatically
        this.name = name;
        this.vendor = vendor;
        this.category = category;
        this.price = price;
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
