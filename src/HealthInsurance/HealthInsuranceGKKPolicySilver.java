package HealthInsurance;

import java.util.ArrayList;
import Citizen.Citizen;

public class HealthInsuranceGKKPolicySilver extends HealthInsurancePolicies {

    private static int counterSilver = 0;

    public HealthInsuranceGKKPolicySilver(Citizen citizen){
        super(10,10);
        ArrayList<String> silverBenefits = new ArrayList<String>();
        silverBenefits.add("doctor");
        silverBenefits.add("ambulance");
        setBenefits(silverBenefits);
        counterSilver++;
        policyNumber = String.format(citizen.getSocialSecurityNumber());
        policyType = 'S';
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
