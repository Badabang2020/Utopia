package UtopiaCore;

public class Address {
    private int district;
    private String street;
    private int streetNo;
    private int doorNo;


    public Address(int district, String street, int streetNo, int doorNo) {
        this.district = district;
        this.street = street;
        this.streetNo = streetNo;
        this.doorNo = doorNo;
    }

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(int streetNo) {
        this.streetNo = streetNo;
    }

    public int getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(int doorNo) {
        this.doorNo = doorNo;
    }

    public String toString() {
        return getDistrict() + ". District | " + getStreet() + " " + getStreetNo();
    }


}
