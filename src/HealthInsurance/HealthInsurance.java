package HealthInsurance;

import Citizen.Citizen;

public interface HealthInsurance {

    public void demandPremiumMonthly (Citizen citizen);

    public void payForMember(Citizen citizen);
}
