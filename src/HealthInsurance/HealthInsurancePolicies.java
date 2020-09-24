package HealthInsurance;

import java.util.ArrayList;

public abstract class HealthInsurancePolicies {

    // creating template with variables we need fo each policiy (bronze-silver-gold)
    protected String policyNumber;  // e.g. B-00000001 - B for Bronze and serial number
    public int premium;
    public int retention;
    public ArrayList<String> benefits;

    HealthInsurancePolicies(int premium, int retention){
        this.premium = premium;
        this.retention = retention;
        benefits = new ArrayList<String>(); // Empty Benefits for Abstract Base Class
    }

    ArrayList<String> getBenefits() {
        return benefits;
    }

    void setBenefits(ArrayList<String> benefits) {
        this.benefits = benefits;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public int getPremium() {
        return premium;
    }

    public void setPremium(int premium) {
        this.premium = premium;
    }

    public int getRetention() {
        return retention;
    }

    public void setRetention(int retention) {
        this.retention = retention;
    }


}
