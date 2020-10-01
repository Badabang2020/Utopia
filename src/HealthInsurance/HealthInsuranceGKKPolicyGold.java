package HealthInsurance;

import java.util.ArrayList;
import Citizen.Citizen;

public class HealthInsuranceGKKPolicyGold extends HealthInsurancePolicies{

    private static int counterGold = 0;

    HealthInsuranceGKKPolicyGold(Citizen citizen){
        super(25,0);
        ArrayList<String> goldBenefits = new ArrayList<String>();
        goldBenefits.add("doctor");
        goldBenefits.add("ambulance");
        setBenefits(goldBenefits);
        counterGold++;
        policyNumber = String.format("G- " + citizen.getSocialSecurityNumber(), counterGold);
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
