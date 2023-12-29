package app.controllers;

import app.models.Product.Product;
import app.repos.ProductRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(path="/api")
public class ProductController {

    @GetMapping("/products")
    ArrayList<Product> displayProducts(){
        ProductRepo productRepo = new ProductRepo();
        return productRepo.getAll();
    }
}
