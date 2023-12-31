package app.repos;

import app.models.Product.Category;
import app.models.Product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class ProductRepo implements Repository<Product> {
    private HashMap<Category, Integer> categoryCount = new HashMap<>();
    private ArrayList<Product> products = new ArrayList<>();

    @Autowired
    public ProductRepo() {
        loadData();
    }

    @Override
    public void add(Product p) {
        // add
        products.add(p);
        // update categoryCount
        if (categoryCount.containsKey(p.getCategory())) {
            categoryCount.put(p.getCategory(), categoryCount.get(p.getCategory()) + 1);
        } else {
            categoryCount.put(p.getCategory(), 1);
        }
    }

    @Override
    public void delete(int pID) {
        Category c = findByID(pID).getCategory();
        // delete
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductID() == pID) {
                products.remove(i);
                break;
            }
        }
        // update categoryCount
        for (Category category : categoryCount.keySet()) {
            if (category.equals(c)){
                categoryCount.put(category, categoryCount.get(category) - 1);
                break;
            }
        }
    }

    @Override
    public void update(Product p, int pID) {
        // update
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductID() == pID) {
                products.set(i, p);
                break;
            }
        }
    }

    @Override
    public Product findByID(int pID) {
        for (Product product : products) {
            if (product.getProductID() == pID)
                return product;
        }
        return null;
    }

    @Override
    public ArrayList<Product> getAll() {
        return products;
    }

    @Override
    public void loadData() {
        products.add(new Product(1,"Apple", "Vendor1", Category.FRUITS, 10.0, 10));
        products.add(new Product(2,"Banana", "Vendor2", Category.FRUITS, 5.0, 10));
        products.add(new Product(3,"Orange", "Vendor3", Category.FRUITS, 20.0, 10));
        products.add(new Product(4,"Watermelon", "Vendor4", Category.FRUITS, 15.0, 10));

        products.add(new Product(5,"KitKat", "Vendor1", Category.SNACKS, 10.0, 10));
        products.add(new Product(6,"Oreo", "Vendor2", Category.SNACKS, 5.0, 10));
        products.add(new Product(7,"Doritos", "Vendor3", Category.SNACKS, 20.0, 10));
        products.add(new Product(8,"Cheetos", "Vendor4", Category.SNACKS, 15.0, 10));

        products.add(new Product(9,"Pepsi", "Vendor1", Category.DRINKS, 10.0, 10));
        products.add(new Product(10,"Coca-Cola", "Vendor2", Category.DRINKS, 5.0, 10));
        products.add(new Product(11,"7UP", "Vendor3", Category.DRINKS, 20.0, 10));
        products.add(new Product(12,"Green Tea", "Vendor4", Category.DRINKS, 15.0, 10));

        categoryCount.put(Category.FRUITS, 4);
        categoryCount.put(Category.SNACKS, 4);
        categoryCount.put(Category.DRINKS, 4);
    }


    public int getCategoryCount(Category category) {
        return categoryCount.get(category);
    }

    public HashMap<Category, Integer> getCategoryCount() {
        return categoryCount;
    }
}
