package app.service;

import app.models.Customer.Customer;
import app.models.Product.Category;
import app.models.Product.Product;
import app.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    private final ProductRepo productRepo;
    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }
    public Product findProductById(int id) {
        return productRepo.findByID(id);
    }

    public ArrayList<Product> getProducts() {
        return productRepo.getAll();
    }

    public void addProduct(Product product) {
        productRepo.add(product);
    }

    public void updateProduct(Product product, int id) {
        productRepo.update(product, id);
    }

    public void deleteProduct(int id) {
        productRepo.delete(id);
    }
    public Map<Category, Integer> getCategoryCount(){
        return productRepo.getCategoryCount();
    }
}
