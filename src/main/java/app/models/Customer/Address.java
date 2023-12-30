package app.models.Customer;

public class Address {
    private String city;
    private int streetNumber;
    private int buildingNumber;

    public Address(String city, int streetNumber, int buildingNumber) {
        this.city = city;
        this.streetNumber = streetNumber;
        this.buildingNumber = buildingNumber;
    }
}
