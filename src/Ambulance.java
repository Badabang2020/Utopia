public class Ambulance implements Event{

    public void happens(Citizen citizen){
<<<<<<< HEAD
        int health = citizen.getCitizenStatus().getMainStatus().getHealthbar();

        if (health < 10) {
            health = health + 25;
            citizen.getCitizenStatus().getMainStatus().setHealthbar(health);
=======
        int health = 0;

        health = citizen.getCitizenStatus().getMainStatus().getHealthbar();
        if (health < 50) {
            health = health + 50;
            citizen.getCitizenStatus().getMainStatus().setHealthbar() = health;
>>>>>>> Ambulance
        }
    };
    public void tick(){

    };
}
