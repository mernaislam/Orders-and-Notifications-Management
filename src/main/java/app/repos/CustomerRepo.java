package app.repos;

import app.models.Customer.Customer;

import java.util.ArrayList;

public class CustomerRepo implements Repository<Customer> {
    private ArrayList<Customer> customers = new ArrayList<>();

    public CustomerRepo() {
        loadData();
    }

    @Override
    public void add(Customer cust) {

    }

    @Override
    public void delete(int custID) {

    }

    @Override
    public void delete(Customer cust) {

    }

    @Override
    public void update(Customer cust, int custID) {

    }

    @Override
    public void save(Customer cust) {

    }

    @Override
    public Customer findByID(int custID) {
        return null;
    }

    @Override
    public ArrayList<Customer> getAll() {
        return null;
    }

    @Override
    public void loadData() {
        customers.add(new Customer(
                "Nada Mohamed", "nadaamohhamed",
                "123456789", "nada@email.com",
                "6,Street 9", "Cairo",
                "01234256781", 500.0));
        customers.add(new Customer(
                "Merna Islam", "mernaislam",
                "merna12", "merna@email.com",
                "2,Street 50", "Giza",
                "0123356782", 250.0));
    }
}
