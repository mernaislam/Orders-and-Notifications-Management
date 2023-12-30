package app.models.Product;

import java.util.Random;

public class Product {

    private int productID;
    private String serialNumber;
    private String name;
    private String vendor;
    private Category category;
    private double price;
    private int quantity;

    public Product(){
        // generate random product
        // id should be generated automatically
        this.serialNumber = "Serial Number";
        this.name = "Name";
        this.vendor = "Vendor";
        this.category = Category.getRandom();
        this.price = new Random().nextDouble() * (100.0 - 5.0) + 5.0;
        this.quantity = (int) (new Random().nextDouble() * (100.0 - 5.0) + 5.0);
    }

    public Product(int id, String name, String vendor, Category category, double price, int quantity) {
        // id should be generated automatically
        this.productID = id;
        this.serialNumber = "Serial Number";
        this.name = name;
        this.vendor = vendor;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }
    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
