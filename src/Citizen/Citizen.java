package Citizen;

import HealthInsurance.HealthInsurancePolicies;
import UtopiaCore.Event;

public class Citizen {
    private String firstName;
    private String lastName;
    private String socialSecurityNumber;
    private char gender;
    private int age;
    private Address address;
    private HealthInsurancePolicies healthInsurancePolicies;
    private Boolean isMarried;
    private CitizenStatus citizenStatus;

    public Citizen(String firstName, String lastName, String socialSecurityNumber,
                   char gender, int age, Address address, HealthInsurancePolicies healthInsurancePolicies,
                   Boolean isMarried, CitizenStatus citizenStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.healthInsurancePolicies = healthInsurancePolicies;
        this.isMarried = isMarried;
        this.citizenStatus = citizenStatus;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public HealthInsurancePolicies getHealthInsurancePolicies() {
        return healthInsurancePolicies;
    }

    public void setHealthInsurancePolicies (HealthInsurancePolicies healthInsurancePolicies) {
        this.healthInsurancePolicies = healthInsurancePolicies;
    }

    public Boolean getMarried() {
        return isMarried;
    }

    public void setMarried(Boolean married) {
        isMarried = married;
    }

    public CitizenStatus getCitizenStatus() {
        return citizenStatus;
    }

    public void setCitizenStatus(CitizenStatus citizenStatus) {
        this.citizenStatus = citizenStatus;
    }

    public String toString() {
        return getFirstName() + " " + getLastName() + getAge() + "\n" +
                citizenStatus.getMainStatus().toString() + "\n" + 
                citizenStatus.getNeeds().toString() + "\n" +
                citizenStatus.getEmotions().toString() + "\n" +
                " Event: " + citizenStatus.getMainStatus().getEvent() + " Eventtime: " + citizenStatus.getMainStatus().getEventTime();
    }
    public void doEvent(Event event){
        event.happens(this);
    }
}
