package HealthInsurance;

import java.util.ArrayList;

public class HealthInsuranceGKKPolicyBronze extends HealthInsurancePolicies {

    // counting all new HealthInsurancePolicyBronze
    private static int counterBronze = 0;

    // initializing parameters
    HealthInsuranceGKKPolicyBronze(){
        super(5,5);
        ArrayList<String> bronzeBenefits = new ArrayList<String>();
        bronzeBenefits.add("doctor");
        setBenefits(bronzeBenefits);
        counterBronze++;
        policyNumber = String.format("B- " + "%07d", counterBronze);
    }

    @Override
    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    @Override
    public String getPolicyNumber() {
        return super.getPolicyNumber();
    }

    @Override
    public int getPremium() {
        return super.getPremium();
    }

    @Override
    public int getRetention() {
        return super.getRetention();
    }

}
