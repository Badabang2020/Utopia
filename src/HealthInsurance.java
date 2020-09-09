import java.util.ArrayList;

public interface HealthInsurance {
    public Double getInsurancePremium(String socialSecurityNumber); // Monthly premium for health insurance
    public ArrayList<String> getBenefits(String socialSecurityNumber);
    public String getInsuranceName();
}
