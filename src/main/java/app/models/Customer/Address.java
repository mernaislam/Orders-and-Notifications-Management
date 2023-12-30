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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }
}
