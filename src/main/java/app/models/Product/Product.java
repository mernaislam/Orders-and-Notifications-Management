package app.models.Product;

import java.util.Random;

public class Product {

    private int productID;
    private String serialNumber;
    private String name;
    private String vendor;
    private Category category;
    private double price;

    public Product(){
        // generate random product
        // id should be generated automatically
        this.serialNumber = "Serial Number";
        this.name = "Name";
        this.vendor = "Vendor";
        this.category = Category.getRandom();
        this.price = new Random().nextDouble() * (100.0 - 5.0) + 5.0;
    }

    public Product(String name, String vendor, Category category, double price) {
        // id should be generated automatically
        this.serialNumber = "Serial Number";
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
