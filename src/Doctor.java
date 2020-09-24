public class Doctor implements Event{

    protected String praxisName = "Doctor Best";
    private int chargeDoctor = 20; /////////////////// new from Rapha

    private void healthCheck(Citizen citizen) {
        citizen.getCitizenStatus().getMainStatus().setEvent("has a health check at" + praxisName + "'s Praxis...");
        if (citizen.getCitizenStatus().getMainStatus().getHealthbar() < 30) {
            treatment(citizen);
        }
    }

    private void treatment (Citizen citizen) {
        int wallet = citizen.getCitizenStatus().getMainStatus().getWallet();
        String checkPolicy = citizen.getHealthInsurancePolicies().getPolicyNumber();
        int retentionOfCitizen = citizen.getHealthInsurancePolicies().getRetention();

        /////////////////// new from Rapha
        if (checkPolicy.charAt(0) == 'G') {
            citizen.getCitizenStatus().getMainStatus().setEventTime(2);
            citizen.getCitizenStatus().getMainStatus().setHealthbar(100);
            citizen.getCitizenStatus().getMainStatus().setEvent("is in a treatment.");
        } else if (wallet < retentionOfCitizen) {
            citizen.getCitizenStatus().getMainStatus().setEvent("don't have enough money.");
        } else if (wallet > retentionOfCitizen && checkPolicy.charAt(0) != 'G'){
            wallet -= retentionOfCitizen;
            citizen.getCitizenStatus().getMainStatus().setEventTime(2);
            citizen.getCitizenStatus().getMainStatus().setWallet(wallet);
            citizen.getCitizenStatus().getMainStatus().setHealthbar(100);
            citizen.getCitizenStatus().getMainStatus().setEvent("is in a treatment.");
        } else {
            wallet -= chargeDoctor;
            citizen.getCitizenStatus().getMainStatus().setEventTime(2);
            citizen.getCitizenStatus().getMainStatus().setWallet(wallet);
            citizen.getCitizenStatus().getMainStatus().setHealthbar(100);
            citizen.getCitizenStatus().getMainStatus().setEvent("is in a treatment.");
        }
        ///////////////////
    }

    public int getChargeDoctor() {
        return chargeDoctor;
    }

    public void setChargeDoctor(int chargeDoctor) {
        this.chargeDoctor = chargeDoctor;
    }

    @Override
    public void happens(Citizen citizen) {
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
