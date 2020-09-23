import java.lang.reflect.Array;
import java.util.ArrayList;

public class HealthInsurancePolicyBronze extends HealthInsurancePolicies {

    // counting all new HealthInsurancePolicyBronze
    private static int counterBronze = 0;

    // generate policyNumber
    private String policyNumber;

    // initializing parameters
    HealthInsurancePolicyBronze(){
        super(5,15);
        ArrayList<String> bronzeBenefits = new ArrayList<String>();
        bronzeBenefits.add("doctor");
        setBenefits(bronzeBenefits);
        counterBronze++;
        setPolicyNumber(String.format("B" + "%07d", counterBronze));
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
