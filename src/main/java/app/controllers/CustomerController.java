package app.controllers;

import app.models.Customer.Customer;
import app.repos.CustomerRepo;
import app.security.JwtTokenUtil;
import app.service.CustomerService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping(path="/api")
public class CustomerController {
    private CustomerService customerService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    CustomerController(){
         customerService = new CustomerService() ;
    }

    @PostMapping(value = "/authenticate",
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> register(@RequestBody Customer customer){
        if(customerService.validRegisteredCustomer(customer)){
            final String token = jwtTokenUtil.generateToken(customer);
            customerService.addCustomer(customer);
            System.out.println(token);
            return ResponseEntity.ok(token);
        }
        return null;
    }

    @GetMapping("/authenticate")
    Customer login(){
//        Customer customer = customerService.validLoggedInCustomer(username, password);
//        if(customer!= null){
//            return customer;
//        } else {
//            return null;
//        }
        return null;
    }

    @GetMapping("/testing")
    String verifyToken(@RequestHeader("Authorization") String token){
        return jwtTokenUtil.getUsernameFromToken(token.substring(7));
    }
}
