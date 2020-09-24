package Health;

import Citizen.Citizen;
import UtopiaCore.Category;
import UtopiaCore.Event;

public class Ambulance implements Event {

    public void happens(Citizen citizen){
        int health = citizen.getCitizenStatus().getMainStatus().getHealthbar();

        if (health < 10) {
            health = health + 25;
            citizen.getCitizenStatus().getMainStatus().setHealthbar(health);
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