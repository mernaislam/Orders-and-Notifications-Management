package app.service;

import app.repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;
    @Autowired
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

}
