package app.models.Product;

public class Product {
    private int serialNumber;
    private String name;
    private String vendor;
    private Category category;
    private double price;

    public Product(String name, String vendor, Category category, double price) {
        // serialNumber should be generated automatically
        this.name = name;
        this.vendor = vendor;
        this.category = category;
        this.price = price;
    }

    public int getSerialNumber() {
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
