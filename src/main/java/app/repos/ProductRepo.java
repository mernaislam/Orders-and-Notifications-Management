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
        products.add(new Product("Apple", "Vendor", Category.CATEGORY1, 10.0));
        products.add(new Product("Banana", "Vendor", Category.CATEGORY1, 5.0));
        products.add(new Product("Milk", "Vendor", Category.CATEGORY2, 20.0));
        products.add(new Product("Cheese", "Vendor", Category.CATEGORY3, 15.0));
        products.add(new Product());
    }


    public int getCategoryCount(Category category) {
        return categoryCount.get(category);
    }
}
