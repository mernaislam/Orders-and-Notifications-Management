package app.repos;

import app.models.Customer.Address;
import app.models.Customer.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomerRepo implements Repository<Customer> {
    private ArrayList<Customer> customers = new ArrayList<>();

    public CustomerRepo() {
        loadData();
    }
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public void add(Customer customer) {
        customers.add(customer);
    }

    @Override
    public void delete(int customerID) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerID() == customerID) {
                customers.remove(i);
                break;
            }
        }
    }
    @Override
    public void update(Customer customer, int customerID) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerID() == customerID) {
                customers.set(i, customer);
                break;
            }
        }
    }

    @Override
    public Customer findByID(int customerID) {
        for (Customer customer : customers) {
            if (customer.getCustomerID() == customerID)
                return customer;
        }
        return null;
    }

    public Customer findByUsername(String username){
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username))
                return customer;
        }
        return null;
    }

    @Override
    public ArrayList<Customer> getAll() {
        return customers;
    }

    @Override
    public void loadData() {
        customers.add(new Customer(0,
                "Nada Mohamed", "nadaamohhamed",
                "123456789", "nada@email.com",
                new Address("Cairo", 11, 5),
                "01234256781", 500.0));

        customers.add(new Customer(1,
                "Merna Islam", "mernaislam",
                "merna12", "merna@email.com",
                new Address("Giza", 15, 2),
                "0123356782", 250.0));

        customers.add(new Customer(2,
                "Waleed", "snowkaai",
                "s123", "waleed@email.com",
                new Address("Cairo", 9, 300),
                "0123123123", 1000.0));
    }
}
