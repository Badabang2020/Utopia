import java.util.ArrayList;

public class HealthInsurancePolicySilver extends HealthInsurancePolicies {

    private static int counterSilver = 0;

    public HealthInsurancePolicySilver(){
        super(10,8);
        ArrayList<String> silverBenefits = new ArrayList<String>();
        silverBenefits.add("doctor");
        silverBenefits.add("ambulance");
        setBenefits(silverBenefits);
        counterSilver++;
        setPolicyNumber(String.format("S" + "%07d", counterSilver));
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
