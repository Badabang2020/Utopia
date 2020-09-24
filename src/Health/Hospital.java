public class Hospital implements Event {
    Citizen patient;

    @Override
    public void happens(Citizen citizen) {
        citizen.getCitizenStatus().getMainStatus().setEventTime(-1);
        this.patient = citizen;
        System.out.println("!test");
    }

    @Override
    public void tick() {
        if (patient != null) {
        int health = patient.getCitizenStatus().getMainStatus().getHealthbar();
        health += 5;
        patient.getCitizenStatus().getMainStatus().setHealthbar(health);
        if (patient.getCitizenStatus().getMainStatus().getHealthbar() == 100) {
            patient.getCitizenStatus().getMainStatus().setEventTime(0);
            patient = null;
        }
        }
    }

    @Override
    public Category[] getCategory() {
        return new Category[] {Category.Health};
    }
}
