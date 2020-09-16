public class Doctor implements Event{

    protected String praxisName;

    private void healthCheck(Citizen citizen) {
        citizen.getCitizenStatus().getMainStatus().setEvent("has a health check...");
        if (citizen.getCitizenStatus().getMainStatus().getHealthbar() < 30) {
            treatment(citizen);
        }
    }

    private void treatment (Citizen citizen) {
        int wallet = citizen.getCitizenStatus().getMainStatus().getWallet();
        if (wallet < 20) {
            citizen.getCitizenStatus().getMainStatus().setEvent("don't have enough money.");
        } else {
            wallet -= 20;
            citizen.getCitizenStatus().getMainStatus().setEventTime(2);
            citizen.getCitizenStatus().getMainStatus().setWallet(wallet);
            citizen.getCitizenStatus().getMainStatus().setHealthbar(100);
            citizen.getCitizenStatus().getMainStatus().setEvent("is in a treatment.");
        }
    }

    @Override
    public void happens(Citizen citizen) {
        healthCheck(citizen);
    }

    @Override
    public void tick() {
    }
}
