package Health;

import UtopiaCore.Category;
import UtopiaCore.Event;
import UtopiaCore.GlobalStacker;
import Citizen.*;

public class Ambulance implements Event {

    public void happens(Citizen citizen) {
        int health = citizen.getCitizenStatus().getMainStatus().getHealthbar();
        Event hospital = GlobalStacker.registeredActivities.get(8);
        if (health < 10) {
            citizen.doEvent(hospital);
        }
    }

    public void tick() {

    }

    @Override
    public Category[] getCategory() {
        return new Category[]{Category.Health};
    }
}