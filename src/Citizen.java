import java.util.Date;

public class Citizen {
    private String firstName;
    private String lastName;
    private String socialSecurityNumber;
    private char gender;
    private int age;
    private Address address;
    private HealthInsurance healthInsurance;
    private Boolean isMarried;
    private CitizenStatus citizenStatus;

    Citizen(String firstName, String lastName, String socialSecurityNumber,
            char gender, int age, Address address, HealthInsurance healthInsurance,
            Boolean isMarried, CitizenStatus citizenStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.healthInsurance = healthInsurance;
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

    public HealthInsurance getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(GKK healthInsurance) {
        this.healthInsurance = healthInsurance;
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
        return getLastName() + " "+ getFirstName() + "\nHealth Insurance: " + healthInsurance.getInsuranceName();
    }
}
