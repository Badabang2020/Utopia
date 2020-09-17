public class Ambulance implements Event{

    public void happens(Citizen citizen){
        int health = citizen.getCitizenStatus().getMainStatus().getHealthbar();

        if (health < 10) {
            health = health + 20;
            citizen.getCitizenStatus().getMainStatus().setHealthbar(health);
            citizen.getCitizenStatus().getMainStatus().setEvent("20 HP added");
        }
    };
    public void tick(){

    }

    @Override
    public Category[] getCategory() {
        return new Category[] {Category.Health};
    }

    ;
}