package Health;

import Citizen.Citizen;
import HealthInsurance.HealthInsurance;
import HealthInsurance.HealthInsuranceGKK;
import UtopiaCore.Event;
import UtopiaCore.Category;
import UtopiaCore.GlobalStacker;

public class Doctor implements Event{

    protected String praxisName = "Health.Doctor Best";
    private int chargeDoctor = 20; /////////////////// new from Rapha
    private boolean inTreatment = false;
    private HealthInsurance healthInsurance;

    private void healthCheck(Citizen citizen) {
        citizen.getCitizenStatus().getMainStatus().setEvent("has a health check at" + praxisName + "'s Praxis...");
        if (citizen.getCitizenStatus().getMainStatus().getHealthbar() < 30) {
            treatment(citizen);
        }
    }

    private void treatment (Citizen citizen) {
        int wallet = citizen.getCitizenStatus().getMainStatus().getWallet();
        char checkPolicyType = citizen.getHealthInsurancePolicies().getPolicyType();
        int retentionOfCitizen = citizen.getHealthInsurancePolicies().getRetention();

        /////////////////// new from Rapha
        if (checkPolicyType == 'G') {
            inTreatment = true;
            citizen.getCitizenStatus().getMainStatus().setHealthbar(100);
            citizen.getCitizenStatus().getMainStatus().setEvent("is in a treatment. Health Insurance is paying everything, depending on your Gold Policy.");
            citizen.getCitizenStatus().getMainStatus().setEventTime(2);
            healthInsurance.payForMember(citizen);

        } else if (wallet < retentionOfCitizen) {
            citizen.getCitizenStatus().getMainStatus().setEvent("don't have enough money.");
        } else if (wallet > retentionOfCitizen && (checkPolicyType == 'S' || checkPolicyType == 'B')){
            inTreatment = true;
            wallet -= retentionOfCitizen;
            citizen.getCitizenStatus().getMainStatus().setWallet(wallet);
            citizen.getCitizenStatus().getMainStatus().setHealthbar(100);
            citizen.getCitizenStatus().getMainStatus().setEvent("is in a treatment. Health Insurance is paying the rest of figure, depending on your " + ((citizen.getHealthInsurancePolicies().getPolicyType() == 'S') ? "Silver" : "Bronze") + " Policy.");
            citizen.getCitizenStatus().getMainStatus().setEventTime(2);
            healthInsurance.payForMember(citizen);
        } else {
            wallet -= chargeDoctor;
            citizen.getCitizenStatus().getMainStatus().setWallet(wallet);
            citizen.getCitizenStatus().getMainStatus().setHealthbar(100);
            citizen.getCitizenStatus().getMainStatus().setEvent("is in a treatment. Citizen paid by himself because "  +
                    ((citizen.getGender() == 'm') ? "his" : "her") + " has no Insurance");
            citizen.getCitizenStatus().getMainStatus().setEventTime(2);
        }
        ///////////////////
    }

    public int getChargeDoctor() {
        return chargeDoctor;
    }

    public void setChargeDoctor(int chargeDoctor) {
        this.chargeDoctor = chargeDoctor;
    }

    public boolean isInTreatment() {
        return inTreatment;
    }

    public void setInTreatment(boolean inTreatment) {
        this.inTreatment = inTreatment;
    }

    @Override
    public void happens(Citizen citizen) {
        healthInsurance = (HealthInsuranceGKK)GlobalStacker.registeredActivities.get(2); //////// ATTENTION: HARDCODING INDEX 2 ////////////////////
        healthCheck(citizen);

    }

    @Override
    public void tick() {
    }

    @Override
    public Category[] getCategory() {
        Category[] category = new Category[] {Category.Health};
        return category;
    }}
