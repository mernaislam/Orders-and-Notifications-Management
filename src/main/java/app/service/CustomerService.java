package app.service;

import app.models.Customer.Customer;
import app.repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;
    @Autowired
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public Customer findCustomerById(int id) {
        return customerRepo.findByID(id);
    }

    public ArrayList<Customer> getCustomers() {
        return customerRepo.getAll();
    }

    public void addCustomer(Customer Customer) {
        customerRepo.add(Customer);
    }

    public void updateCustomer(Customer Customer, int id) {
        customerRepo.update(Customer, id);
    }

    public void deleteCustomer(int id) {
        customerRepo.delete(id);
    }
}
