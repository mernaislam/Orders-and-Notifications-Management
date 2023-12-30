package app.service;

import app.models.Customer.Customer;
import app.models.Product.Product;
import app.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

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

    boolean uniqueId(int id){
        ArrayList<Product> allProducts = getProducts();
        if(allProducts != null){
            for (int i = 0; i < allProducts.size(); i++) {
                if(Objects.equals(id, allProducts.get(i).getProductID())){
                    return false;
                }
            }
        }
        return true;
    }

    public int generateProductId(){
        Random random = new Random();
        int value = random.nextInt(100000 - 1);
        while(!uniqueId(value)){
            value = random.nextInt(100000 - 1);
        }
        return value;
    }
}
