package app.controllers;

import app.models.Customer.Customer;
import app.security.GlobalException;
import app.security.JwtTokenUtil;
import app.security.ResponseEntityStructure;
import app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="/api")
public class CustomerController {
    private CustomerService customerService;
    private GlobalExceptionController exceptionController = new GlobalExceptionController();
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    CustomerController(CustomerService customerService){
         this.customerService = customerService;
    }

    // register a new user
    @PostMapping(value = "/authenticate",
        consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<ResponseEntityStructure> register(@RequestBody Customer customer){
        GlobalException exception;
        if(customer.getBalance() != null && customer.getName() != null && customer.getShippingAddress() != null && customer.getShippingAddress().getCity() != null){
            if(customer.getBalance() >= 0){
                if(customerService.validRegisteredCustomer(customer)){
                    customerService.addCustomer(customer);
                    exception = new GlobalException("Registration completed successfully!", HttpStatus.OK);
                } else {
                    exception = new GlobalException("Invalid data [ex: username/email/mobile number already exists], please try again!", HttpStatus.BAD_REQUEST);
                }
            } else {
                exception = new GlobalException("Balance cannot be negative, please try again!", HttpStatus.BAD_REQUEST);
            }
        } else {
            exception = new GlobalException("Data is missing check all fields, please try again!", HttpStatus.BAD_REQUEST);
        }
        return exceptionController.GlobalException(exception);
    }

    // login an existing user and returns a token that will be used for authentication
    @GetMapping(value = "/authenticate")
    ResponseEntity<ResponseEntityStructure> login(@RequestBody Customer customer){
        GlobalException exception;
        Customer customer2 = customerService.validLoggedInCustomer(customer.getUsername(), customer.getPassword());
        if(customer2 != null){
            final String token = jwtTokenUtil.generateToken(customer2);
            exception = new GlobalException(token, HttpStatus.OK);
        } else {
            exception = new GlobalException("Invalid credentials, please try again!", HttpStatus.BAD_REQUEST);
        }
        return exceptionController.GlobalException(exception);
    }

    // get customer details by an id
    @GetMapping("/customer/{id}")
    Object getCustomer(@PathVariable int id, @RequestHeader("Authorization") String token) {
        GlobalException exception;
        Customer customer = customerService.findCustomerById(id);
        if (customer != null) {
            if (jwtTokenUtil.getUsernameFromToken(token.substring(7)).equals(customer.getUsername())) {
                return customer;
            } else {
                exception = new GlobalException("Customer id doesn't match customer username, try again", HttpStatus.BAD_REQUEST);
            }
        } else {
            exception = new GlobalException("Invalid customer id, try again", HttpStatus.BAD_REQUEST);
        }
        return exceptionController.GlobalException(exception);
    }

    @PutMapping("/customer/{id}")
    Object updateBalance(@PathVariable int id, @RequestHeader("Authorization") String token, @RequestBody Customer customer){
        GlobalException exception;
        Customer customer2 = customerService.findCustomerById(id);
        if (customer != null) {
            if (jwtTokenUtil.getUsernameFromToken(token.substring(7)).equals(customer2.getUsername())) {
                if(customer.getBalance() >= 0){
                    customer2.setBalance(customer.getBalance());
                    exception = new GlobalException("Customer balance is updated Successfully!", HttpStatus.OK);
                } else {
                    exception = new GlobalException("Invalid balance value [it cannot be a negative number], try again", HttpStatus.BAD_REQUEST);
                }
            } else {
                exception = new GlobalException("Customer id doesn't match customer username, try again", HttpStatus.BAD_REQUEST);
            }
        } else {
            exception = new GlobalException("Invalid customer id, try again", HttpStatus.BAD_REQUEST);
        }
        return exceptionController.GlobalException(exception);
    }
}
