package app.service;

import app.models.Customer.Customer;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomerService {
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
}
