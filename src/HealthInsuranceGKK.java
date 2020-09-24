import java.util.HashMap;
import java.util.Set;

public class HealthInsuranceGKK implements Event, HealthInsurance{

    Doctor doctor = new Doctor();

    private long insuranceCash = 1000000;
    static int time = 0;
    private HashMap<String, HealthInsurancePolicies> healthInsuranceGKKMembers = new HashMap<String, HealthInsurancePolicies>(); //InsuranceMembers registration
    private int bronzePolicyLimit = 1000;
    private int silverPolicyLimit = 2000;
    private int goldPolicyLimit = silverPolicyLimit + 1;


    public boolean registerHealthInscuranceGKKMember(Citizen citizen, int income) { //register a HealthInsuranceGKKMember
        if (!healthInsuranceGKKMembers.containsKey(citizen.getSocialSecurityNumber())) {
            HealthInsurancePolicies citizenPolicy;
            if (income <= bronzePolicyLimit) {
                citizenPolicy = new HealthInsuranceGKKPolicyBronze();
                healthInsuranceGKKMembers.put(citizen.getSocialSecurityNumber(), citizenPolicy); // create a bronze Policy for the citizen
                citizen.setHealthInsurancePolicies(citizenPolicy);
            } else if (income < silverPolicyLimit) {
                citizenPolicy = new HealthInsuranceGKKPolicySilver();
                healthInsuranceGKKMembers.put(citizen.getSocialSecurityNumber(), citizenPolicy); // create a silver Policy for the citizen
                citizen.setHealthInsurancePolicies(citizenPolicy);
            } else if (income > goldPolicyLimit){
                citizenPolicy = new HealthInsuranceGKKPolicyGold();
                healthInsuranceGKKMembers.put(citizen.getSocialSecurityNumber(), citizenPolicy); // create a gold Policy for the citizen
                citizen.setHealthInsurancePolicies(citizenPolicy);
            }
            return true;
        }
        return false;
    }

    public int demandPremiumMonthly(Citizen citizen) {
        //initialize the wallet of citizen with getter
        int walletOfCitizen = citizen.getCitizenStatus().getMainStatus().getWallet();
        // initialize the checkPolicy with getter
        String checkPolicy = healthInsuranceGKKMembers.get(citizen.getSocialSecurityNumber()).getPolicyNumber();
        // check if Policy starts with G-S-B and demand the premium form citizens wallet
        if (checkPolicy.charAt(0) == 'G'){
            walletOfCitizen -= healthInsuranceGKKMembers.get(citizen.getSocialSecurityNumber()).getRetention();
            return walletOfCitizen;
        } else if (checkPolicy.charAt(0) == 'S'){
            walletOfCitizen -= healthInsuranceGKKMembers.get(citizen.getSocialSecurityNumber()).getRetention();
            return walletOfCitizen;
        } else if (checkPolicy.charAt(0) == 'B'){
            walletOfCitizen -= healthInsuranceGKKMembers.get(citizen.getSocialSecurityNumber()).getRetention();
            return walletOfCitizen;
        } else {
            return walletOfCitizen;
        }
    }


    public long payForMember(Citizen citizen) {
        // getting the charge of the doctor and safe it into chargeOfDoctor
        int chargeOfDoctor = doctor.getChargeDoctor();
        String checkPolicy = healthInsuranceGKKMembers.get(citizen.getSocialSecurityNumber()).getPolicyNumber();
        // check if the citizen is at the doctor by checking the event
        if (citizen.getCitizenStatus().getMainStatus().getEvent().equals("is in a treatment.")){
            //check which policy the citizen has
            if (checkPolicy.charAt(0) == 'G') {
                insuranceCash -= chargeOfDoctor;
                citizen.getCitizenStatus().getMainStatus().setEvent("Health Insurance is paying everything, depending on your GoldPolicy.");
                citizen.getCitizenStatus().getMainStatus().setEventTime(1);
            } else if (checkPolicy.charAt(0) == 'S'){
                insuranceCash -= (chargeOfDoctor - citizen.getHealthInsurancePolicies().getRetention());
                citizen.getCitizenStatus().getMainStatus().setEvent("Health Insurance is paying the rest of figure, depending on your SilverPolicy");
                citizen.getCitizenStatus().getMainStatus().setEventTime(1);
            }
            else if (checkPolicy.charAt(0) == 'B'){
                insuranceCash -= (chargeOfDoctor - citizen.getHealthInsurancePolicies().getRetention());
                citizen.getCitizenStatus().getMainStatus().setEvent("Health Insurance is paying the rest of figure, depending on your BronzePolicy");
                citizen.getCitizenStatus().getMainStatus().setEventTime(1);
            }
        }
        return insuranceCash;
    }

    @Override
        public void happens(Citizen citizen) {
        boolean newGKKMember = registerHealthInscuranceGKKMember(citizen, citizen.getCitizenStatus().getMainStatus().getIncome()); // checks if the citizen is Member of HealthInsuranceGKK
        String msg = (newGKKMember ? "Register new HealthInscuranceGKKMember with policy number " : "Citizen is already a Member of HealthInsuranceGKK"); //+ citizen.getHealthInsurancePolicies().getPolicyNumber()
        citizen.getCitizenStatus().getMainStatus().setEvent("Register new HealthInscuranceGKKMember with policy number " + healthInsuranceGKKMembers.get(citizen.getSocialSecurityNumber()).getPolicyNumber());//String for creating new Member
        payForMember(citizen);

    }

        @Override
        public void tick() {
//            time++;
//            if (time  == 80000) {
//                time = 0;
//                Set<String> keys = healthInsuranceGKKMembers.keySet();
//                for (String key : keys) {
//                    HealthInsurancePolicies healthInsurancePolicies = healthInsuranceGKKMembers.get(key);
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
