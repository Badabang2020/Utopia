import java.util.ArrayList;

public class HealthInsurancePolicyGold extends HealthInsurancePolicies{

    private static int counterGold = 0;

    HealthInsurancePolicyGold(){
        super(15,0);
        ArrayList<String> goldBenefits = new ArrayList<String>();
        goldBenefits.add("doctor");
        goldBenefits.add("ambulance");
        setBenefits(goldBenefits);
        counterGold++;
        setPolicyNumber(String.format("G" + "%07d", counterGold));
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
