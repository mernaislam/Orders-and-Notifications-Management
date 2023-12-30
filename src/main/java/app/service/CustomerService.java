package app.service;

import app.models.Customer.Customer;
import app.repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

@Service
public class CustomerService {
    private final CustomerRepo customerRepo;

    @Autowired
    public CustomerService() {
        this.customerRepo = new CustomerRepo();
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

    public Customer validLoggedInCustomer(String username, String password){
        ArrayList<Customer> allCustomers = getCustomers();
        for (int i = 0; i < allCustomers.size(); i++) {
            if(Objects.equals(username, allCustomers.get(i).getUsername())){
                if(Objects.equals(password, allCustomers.get(i).getPassword())){
                    return allCustomers.get(i);
                }
            }
        }
        return null;
    }

    public boolean validRegisteredCustomer(Customer customer){
        if(!validUsername(customer.getUsername())){
            return false;
        }
        if(!validPassword(customer.getPassword())){
            return false;
        }
        if(!validEmail(customer.getEmail())){
            return false;
        }
        if(!validMobileNumber(customer.getPhoneNumber())){
            return false;
        }
        return true;
    }

    boolean validUsername(String username){
        String usernameRegex = "^[a-zA-Z0-9]*$";
        if (!username.matches(usernameRegex)){
            return false;
        }
        ArrayList<Customer> allCustomers = getCustomers();
        for (int i = 0; i < allCustomers.size(); i++) {
            if(Objects.equals(username, allCustomers.get(i).getUsername())){
               return false;
            }
        }
        return true;
    }

    boolean validMobileNumber(String mobile){
        String mobRegex = "^01[0-2,5]{1}[0-9]{8}$";
        if (!mobile.matches(mobRegex)){
            return false;
        }
        ArrayList<Customer> allCustomers = getCustomers();
        for (int i = 0; i < allCustomers.size(); i++) {
            if(Objects.equals(mobile, allCustomers.get(i).getPhoneNumber())){
                return false;
            }
        }
        return true;
    }

    boolean validEmail(String email){
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        if (!email.matches(emailRegex)){
            return false;
        }
        ArrayList<Customer> allCustomers = getCustomers();
        for (int i = 0; i < allCustomers.size(); i++) {
            if(Objects.equals(email, allCustomers.get(i).getEmail())){
                return false;
            }
        }
        return true;
    }

    boolean validPassword(String password){
        String passRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        if (!password.matches(passRegex)){
            return false;
        }
        return true;
    }

    boolean uniqueId(int id){
        ArrayList<Customer> allCustomers = getCustomers();
        if(allCustomers != null){
            for (int i = 0; i < allCustomers.size(); i++) {
                if(Objects.equals(id, allCustomers.get(i).getCustomerID())){
                    return false;
                }
            }
        }
        return true;
    }


}
