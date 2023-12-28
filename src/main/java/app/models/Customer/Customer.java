package app.models.Customer;

public class Customer {
    private int customerID;
    private String name;
    private String username;
    private String password;
    private String email;
    private String shippingAddress;
    private String phone;
    private Double balance;

    public String getName() {
        return name;
    }

    public Double getBalance() {
        return balance;
    }

    public int getCustomerID() {
        return customerID;
    }

}
