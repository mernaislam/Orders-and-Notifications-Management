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

    CustomerController(){
         customerService = new CustomerService() ;
    }

    @PostMapping(value = "/authenticate",
        consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<ResponseEntityStructure> register(@RequestBody Customer customer){
        GlobalException exception;
        if(customerService.validRegisteredCustomer(customer)){
            customerService.addCustomer(customer);
            exception = new GlobalException("Registration completed successfully!", HttpStatus.OK);
        } else {
            exception = new GlobalException("Invalid data, please try again!", HttpStatus.BAD_REQUEST);
        }
        return exceptionController.GlobalException(exception);
    }

    @GetMapping(value = "/authenticate/{username}/{password}")
    ResponseEntity<ResponseEntityStructure> login(@PathVariable String username, @PathVariable String password){
        GlobalException exception;
        Customer customer = customerService.validLoggedInCustomer(username, password);
        if(customer!= null){
            final String token = jwtTokenUtil.generateToken(customer);
            exception = new GlobalException(token, HttpStatus.OK);
        } else {
            exception = new GlobalException("Invalid credentials, please try again!", HttpStatus.BAD_REQUEST);
        }
        return exceptionController.GlobalException(exception);
    }

//    @GetMapping("/testing")
//    String verifyToken(@RequestHeader("Authorization") String token){
//        return jwtTokenUtil.getUsernameFromToken(token.substring(7));
//    }
}
