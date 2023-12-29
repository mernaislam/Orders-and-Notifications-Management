package app.controllers;

import app.models.Customer.Customer;
import app.repos.CustomerRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping(path="/app")
public class CustomerController {
//    private ArrayList<Customer> allCustomers = new ArrayList<>();

    @PostConstruct
    void loadCustomers(){
//        CustomerRepo repo = new CustomerRepo();
//        allCustomers = repo.getCustomers();
    }

    @PostMapping("/register")
    void register(@RequestBody Customer customer){

    }

    @GetMapping("/login/{username}/{password}")
    Customer login(@PathVariable String username, @PathVariable String password){
        Customer customer = checkCustomer(username, password);
        if(customer!= null){
            return customer;
        } else {
            return null;
        }
    }


}
