import java.util.Date;

public class Citizen {
    private String firstName;
    private String lastName;
    private String socialSecurityNumber;
    private char gender;
    private Date birthDate;
    private Address address;
    private HealthInsurance healthInsurance;
    private Boolean isMarried;
    private HealthStatus healthStatus;

    Citizen(String firstName, String lastName, String socialSecurityNumber,
            char gender, Date birthDate, Address address, HealthInsurance healthInsurance,
            Boolean isMarried, HealthStatus healthStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
        this.gender = gender;
        this.birthDate = birthDate;
        this.address = address;
        this.healthInsurance = healthInsurance;
        this.isMarried = isMarried;
        this.healthStatus = healthStatus;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(HealthStatus healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String toString() {
        return getLastName() + " "+ getFirstName() + "\nHealth Insurance: " + healthInsurance.getInsuranceName();
    }
}
