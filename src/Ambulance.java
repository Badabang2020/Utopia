public class Ambulance implements Event{

    public void happens(Citizen citizen){
        int health = 0;

        health = citizen.getCitizenStatus().getMainStatus().getHealthbar();
        if (health < 50) {
            health = health + 50;
            citizen.getCitizenStatus().getMainStatus().setHealthbar() = health;
        }
    };
    public void tick(){

    };
}
