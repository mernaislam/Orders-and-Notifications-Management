package app.controllers;

import app.models.Product.Category;
import app.models.Product.Product;
import app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping(path="/api")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService =  productService;
    }
    // return list of products [no authorization]
    @GetMapping("/products")
    ArrayList<Product> displayProducts(){
        return productService.getProducts();
    }
    // return the number of products in each category [no authorization]
    @GetMapping("/categoryCount")
    Map<Category, Integer> getCategories(){
        return productService.getCategoryCount();
    }
}
