package Policies;

import java.util.ArrayList;

public class GKK implements HealthInsurance {
    Double premium; //ToDo - calculate stuff
    ArrayList<String> benefits; // ToDo s.a.
    String name;

    public GKK() {
        name = "Policies.GKK";
        benefits = new ArrayList<String>();
        premium = 100.0;
    }

    @Override
    public Double getInsurancePremium(String socialSecurityNumber) {
        return premium;
    }
    @Override
    public ArrayList<String> getBenefits(String socialSecurityNumber) {
        return benefits;
    }
    @Override
    public String getInsuranceName() {
        return name;
    }
}
