package app.controllers;

import app.models.Product.Product;
import app.repos.ProductRepo;
import app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(path="/api")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService =  productService;
    }
    @GetMapping("/products")
    ArrayList<Product> displayProducts(){
        return productService.getProducts();
    }
}
