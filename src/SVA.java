import java.util.ArrayList;
import java.util.HashMap;

public class SVA implements HealthInsurance {
    Double premium; //ToDo - calculate stuff
    ArrayList<String> benefits; // ToDo s.a.
    HashMap<String, Policies> members;

    String name;

    SVA() {
        name = "SVA";
        members = new HashMap<String, Policies>();
        benefits = new ArrayList<String>();
        premium = 150.0;
    }

    @Override
    public Double getInsurancePremium(String socialSecurityNumber) {
        return members.get(socialSecurityNumber).getPremiumPerMonth();
    }
    @Override
    public ArrayList<String> getBenefits(String socialSecurityNumber) {
        return benefits;
    }
    @Override
    public String getInsuranceName() {
        return name;
    }
}