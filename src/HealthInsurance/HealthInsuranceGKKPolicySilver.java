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
        policyNumber = String.format("S- " + citizen.getSocialSecurityNumber(), counterSilver);
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
