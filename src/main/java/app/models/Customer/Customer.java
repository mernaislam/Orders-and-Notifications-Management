package app.models.Customer;

import app.repos.CustomerRepo;
import app.service.CustomerService;

public class Customer {
    private int customerID;
    private String name;
    private String username;
    private String password;
    private String email;
    private Address shippingAddress;
    private String phoneNumber;
    private Double balance;
    public Customer(){

    }

    public Customer(String name, String username, String password, String email, Address shippingAddress,
                    String phoneNumber, Double balance) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.shippingAddress = shippingAddress;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        CustomerService customerService = new CustomerService(new CustomerRepo());
        this.customerID = customerService.generateCustomerId();
    }

    public Customer(int id, String name, String username, String password, String email, Address shippingAddress,
                    String phoneNumber, Double balance) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.shippingAddress = shippingAddress;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.customerID = id;
    }
    public int getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }

}
