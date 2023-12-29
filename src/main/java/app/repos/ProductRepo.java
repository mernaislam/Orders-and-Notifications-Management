package app.repos;

import app.models.Product.Category;
import app.models.Product.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductRepo implements Repository{
    private HashMap<Category, Integer> categoryCount = new HashMap<>();
    private ArrayList<Product> products = new ArrayList<>();

    public ProductRepo() {
        loadData();
    }

    @Override
    public void add(Object object) {
        // add
        // update categoryCount
    }

    @Override
    public void delete(Object object) {
        // delete
        // update categoryCount
    }

    @Override
    public void update(Object object) {

    }

    @Override
    public void save(Object object) {

    }

    @Override
    public Object findByID(int id) {
        return null;
    }

    @Override
    public void loadData() {
        products.add(new Product("Apple", "Vendor", Category.CATEGORY1, 10.0));
        products.add(new Product("Banana", "Vendor", Category.CATEGORY1, 5.0));
        products.add(new Product("Milk", "Vendor", Category.CATEGORY2, 20.0));
        products.add(new Product("Cheese", "Vendor", Category.CATEGORY3, 15.0));
    }


    public int getCategoryCount(Category category){
        return categoryCount.get(category);
    }
}
