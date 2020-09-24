package HealthInsurance;

public class Policies {
    String socialSecurityNumber;
    String contractType;
    Double premiumPerMonth; // Payment per month

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public Double getPremiumPerMonth() {
        return premiumPerMonth;
    }

    public void setPremiumPerMonth(Double premiumPerMonth) {
        this.premiumPerMonth = premiumPerMonth;
    }
}
