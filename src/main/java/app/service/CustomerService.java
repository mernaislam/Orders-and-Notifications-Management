package app.service;

import app.models.Customer.Customer;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;
    
    Customer checkCustomer(String username, String password){
//        for (int i = 0; i < allCustomers.size(); i++) {
//            if(Objects.equals(username, allCustomers.get(i).getUsername())){
//                if(Objects.equals(password, allCustomers.get(i).getPassword())){
//                    return allCustomers.get(i);
//                }
//            }
//        }
        return null;
    }

    boolean checkUsername(String username){
        return true;
    }

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
