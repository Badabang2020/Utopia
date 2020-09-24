import java.util.ArrayList;

public class HealthInsuranceGKKPolicyGold extends HealthInsurancePolicies{

    private static int counterGold = 0;

    HealthInsuranceGKKPolicyGold(){
        super(25,0);
        ArrayList<String> goldBenefits = new ArrayList<String>();
        goldBenefits.add("doctor");
        goldBenefits.add("ambulance");
        setBenefits(goldBenefits);
        counterGold++;
        policyNumber = String.format("G- " + "%07d", counterGold);
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
