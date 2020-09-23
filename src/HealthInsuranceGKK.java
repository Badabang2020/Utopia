import java.util.HashMap;

public class HealthInsuranceGKK implements Event {

    Doctor doctor = new Doctor();

    private long insuranceCash = 1000000;
    private HashMap<String, HealthInsurancePolicies> healthInsuranceGKKMembers = new HashMap<>(); //InsuranceMembers

    @Override
        public void happens(Citizen citizen) {

            //
            int chargeOfDoctor = doctor.getChargeDoctor();
            String checkPolicy = citizen.getHealthInsurancePolicies().getPolicyNumber();
            // check if the citizen is at the doctor by checking the event
            if (citizen.getCitizenStatus().getMainStatus().getEvent() == "is in a treatment."){
               //check which policy the citizen has
                if (checkPolicy.charAt(0) == 'G') {
                    insuranceCash -= chargeOfDoctor;
                } else if (checkPolicy.charAt(0) == 'S'){
                    insuranceCash -= (chargeOfDoctor - HealthInsurancePolicySilver.getRetention());
                    }
                else {
                    insuranceCash -= (chargeOfDoctor - HealthInsurancePolicyBronze.getRetention());
                }
            }
        }

        @Override
        public void tick() {

        }

        @Override
        public Category[] getCategory() {
            Category[] category = new Category[] {Category.Money};
                    return category;
        }
    }
