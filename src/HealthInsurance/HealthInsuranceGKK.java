package HealthInsurance;
import UtopiaCore.*;
import Citizen.Citizen;
import Health.Doctor;

import java.util.HashMap;

public class HealthInsuranceGKK implements Event, HealthInsurance{



    private long insuranceCash = 1000000;
    static int time = 0;
    private HashMap<String, HealthInsurancePolicies> healthInsuranceGKKMembers = new HashMap<String, HealthInsurancePolicies>(); //InsuranceMembers registration
    private int bronzePolicyLimit = 1000;
    private int silverPolicyLimit = 2000;
    private int goldPolicyLimit = silverPolicyLimit + 1;


    public boolean registerHealthInscuranceGKKMember(Citizen citizen, int income) {                 //register a HealthInsuranceGKKMember
        if (!healthInsuranceGKKMembers.containsKey(citizen.getSocialSecurityNumber())) {
            HealthInsurancePolicies citizenPolicy;
            if (income <= bronzePolicyLimit) {
                citizenPolicy = new HealthInsuranceGKKPolicyBronze(citizen);                               // create a bronze Policy
                healthInsuranceGKKMembers.put(citizen.getSocialSecurityNumber(), citizenPolicy);    // create new Member with policyNumber
                citizen.setHealthInsurancePolicies(citizenPolicy);                                  // set created policyNumber into citizens HealthInsurancePolicies
                citizen.getCitizenStatus().getMainStatus().setEventTime(0);
            } else if (income < silverPolicyLimit) {
                citizenPolicy = new HealthInsuranceGKKPolicySilver(citizen);                               // create a silver Policy
                healthInsuranceGKKMembers.put(citizen.getSocialSecurityNumber(), citizenPolicy);    // create new Member with policyNumber
                citizen.setHealthInsurancePolicies(citizenPolicy);                                  // set created policyNumber into citizens HealthInsurancePolicies
                citizen.getCitizenStatus().getMainStatus().setEventTime(0);
            } else if (income > goldPolicyLimit){
                citizenPolicy = new HealthInsuranceGKKPolicyGold(citizen);                                 // create a gold Policy for the citizen
                healthInsuranceGKKMembers.put(citizen.getSocialSecurityNumber(), citizenPolicy);    // create new Member with policyNumber
                citizen.setHealthInsurancePolicies(citizenPolicy);                                  // set created policyNumber into citizens HealthInsurancePolicies
                citizen.getCitizenStatus().getMainStatus().setEventTime(0);
            }
            return true;
        }
        return false;
    }

    public void demandPremiumMonthly(Citizen citizen) {
        //initialize the wallet of citizen with getter
        int walletOfCitizen = citizen.getCitizenStatus().getMainStatus().getWallet();
        //initialize retention of citizen's policy
        int retentionOfCitizen = healthInsuranceGKKMembers.get(citizen.getSocialSecurityNumber()).getRetention();
        // initialize the checkPolicy with getter
        char checkPolicyType = healthInsuranceGKKMembers.get(citizen.getSocialSecurityNumber()).getPolicyType();
        // check if Policy starts with G-S-B and demand the premium form citizens wallet
        if (checkPolicyType == 'G'){
            walletOfCitizen -= retentionOfCitizen;
            insuranceCash += retentionOfCitizen;

        } else if (checkPolicyType == 'S'){
            walletOfCitizen -= retentionOfCitizen;
            insuranceCash += retentionOfCitizen;

        } else if (checkPolicyType == 'B'){
            walletOfCitizen -= retentionOfCitizen;
            insuranceCash += retentionOfCitizen;
        }
    }


    public void payForMember(Citizen citizen) {
        Doctor doctor = (Doctor) GlobalStacker.registeredActivities.get(1);  //////// ATTENTION: HARDCODING INDEX 1 ////////////////////
        // getting the charge of the doctor and safe it into chargeOfDoctor
        int chargeOfDoctor = doctor.getChargeDoctor();
        // getting citizens policyNumber and safe it into checkPolicy
        char checkPolicyType = healthInsuranceGKKMembers.get(citizen.getSocialSecurityNumber()).getPolicyType();
        // amount to pay for HealthInsurance
        int toPay = (chargeOfDoctor - citizen.getHealthInsurancePolicies().getRetention());
            //check which policy the citizen has
            if (checkPolicyType == 'G') {
                insuranceCash -= chargeOfDoctor;
                citizen.getCitizenStatus().getMainStatus().setEventTime(0);

            } else if (checkPolicyType == 'S'){
                insuranceCash -= toPay;
                citizen.getCitizenStatus().getMainStatus().setEventTime(0);

            }
            else if (checkPolicyType == 'B'){
                insuranceCash -= toPay;
                citizen.getCitizenStatus().getMainStatus().setEventTime(0);

            }
        }

    public long getInsuranceCash() {
        return insuranceCash;
    }

    public void setInsuranceCash(long insuranceCash) {
        this.insuranceCash = insuranceCash;
    }

    @Override
        public void happens(Citizen citizen) {
        boolean newGKKMember = registerHealthInscuranceGKKMember(citizen, citizen.getCitizenStatus().getMainStatus().getIncome());                              // checks if the citizen is Member of HealthInsuranceGKK
        String msg = (newGKKMember ? "Register new HealthInsuranceGKKMember with policy number: " : "Citizen is already a Member of HealthInsuranceGKK with policy: ");
        citizen.getCitizenStatus().getMainStatus().setEvent(msg + citizen.getHealthInsurancePolicies().getPolicyType() + "-" + healthInsuranceGKKMembers.get(citizen.getSocialSecurityNumber()).getPolicyNumber());          //String for creating new Member

    }

        @Override
        public void tick() {
//            time++;
//            if (time  == 80000) {
//                time = 0;
//                Set<String> keys = healthInsuranceGKKMembers.keySet();
//                for (String key : keys) {
//                    HealthInsurance.HealthInsurancePolicies healthInsurancePolicies = healthInsuranceGKKMembers.get(key);
//                    demandPremiumMonthly(citizen);
//                }
//            }
        }

        @Override
        public Category[] getCategory() {
            Category[] category = new Category[] {Category.Money};
                    return category;
        }



}
