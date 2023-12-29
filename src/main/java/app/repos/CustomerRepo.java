package app.repos;

import app.models.Customer.Customer;

import java.util.ArrayList;

public class CustomerRepo implements Repository{
    private ArrayList<Customer> customers = new ArrayList<>();

    public CustomerRepo() {
        loadData();
    }

    @Override
    public void add(Object object) {

    }

    @Override
    public void delete(Object object) {

    }

    @Override
    public void update(Object object) {

    }

    @Override
    public void save(Object object) {

    }

    @Override
    public Object findByID(int id) {
        return null;
    }

    @Override
    public void loadData() {
        customers.add(new Customer(
                "Nada Mohamed", "nadaamohhamed",
                "123456789", "nada@email.com",
                "6,Street 9", "Cairo",
                "01234256781",500.0));
        customers.add(new Customer(
                "Merna Islam", "mernaislam",
                "merna12", "merna@email.com",
                "2,Street 50", "Giza",
                "0123356782",250.0));
    }
}
