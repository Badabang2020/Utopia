package HealthInsurance;

import java.util.ArrayList;
import Citizen.Citizen;

public class HealthInsuranceGKKPolicyBronze extends HealthInsurancePolicies {

    // counting all new HealthInsurancePolicyBronze
    private static int counterBronze = 0;

    // initializing parameters
    HealthInsuranceGKKPolicyBronze(Citizen citizen){
        super(5,5);
        ArrayList<String> bronzeBenefits = new ArrayList<String>();
        bronzeBenefits.add("doctor");
        setBenefits(bronzeBenefits);
        counterBronze++;
        policyNumber = String.format(citizen.getSocialSecurityNumber());
        policyType = 'B';
    }

    @Override
    public String getPolicyNumber() {
        return super.getPolicyNumber();
    }

    @Override
    public char getPolicyType() {
        return super.getPolicyType();
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
