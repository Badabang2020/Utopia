public class Ambulance implements Event{

    public void happens(Citizen citizen){
        int health = citizen.getCitizenStatus().getMainStatus().getHealthbar();

        if (health < 10) {
            citizen.doEvent(GlobalStacker.registeredActivities.getregisteredActivities());
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