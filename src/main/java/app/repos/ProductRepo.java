package app.repos;

import app.models.Product.Category;
import app.models.Product.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class ProductRepo implements Repository<Product> {
    private HashMap<Category, Integer> categoryCount = new HashMap<>();
    private ArrayList<Product> products = new ArrayList<>();

    public ProductRepo() {
        loadData();
    }

    @Override
    public void add(Product p) {
        // add
        // update categoryCount
    }

    @Override
    public void delete(int pID) {
        // delete
        // update categoryCount
    }

    @Override
    public void delete(Product p) {

    }

    @Override
    public void update(Product p, int pID) {

    }

    @Override
    public void save(Product p) {

    }

    @Override
    public Product findByID(int pID) {
        return null;
    }

    @Override
    public ArrayList<Product> getAll() {
        return products;
    }

    @Override
    public void loadData() {
        products.add(new Product("Apple", "Vendor", Category.CATEGORY1, 10.0));
        products.add(new Product("Banana", "Vendor", Category.CATEGORY1, 5.0));
        products.add(new Product("Milk", "Vendor", Category.CATEGORY2, 20.0));
        products.add(new Product("Cheese", "Vendor", Category.CATEGORY3, 15.0));
    }


    public int getCategoryCount(Category category) {
        return categoryCount.get(category);
    }
}
