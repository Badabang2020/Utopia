public class Ambulance implements Event{

    public void happens(Citizen citizen){
        int health = citizen.getCitizenStatus().getMainStatus().getHealthbar();
        Hospital hospital = GlobalStacker.registeredActivities.
        if (health < 10) {
            citizen.doEvent(hospital);
        }
    }
    public void tick(){

    }

    @Override
    public Category[] getCategory() {
        return new Category[] {Category.Health};
    }

    ;
}