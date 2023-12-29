package app.models.Customer;

public class Customer {
    private int customerID;
    private String name;
    private String username;
    private String password;
    private String email;
    private String shippingAddress;
    private String city;
    private String phoneNumber;
    private Double balance;

    public Customer(String name, String username, String password, String email, String shippingAddress,
                    String city, String phoneNumber, Double balance) {
        // id should be generated automatically
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.shippingAddress = shippingAddress;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
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

    public String getShippingAddress() {
        return shippingAddress;
    }

    public String getCity() {
        return city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Double getBalance() {
        return balance;
    }
}
