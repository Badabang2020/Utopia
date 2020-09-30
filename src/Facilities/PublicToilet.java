package Facilities;

import Citizen.Citizen;
import UtopiaCore.Category;
import UtopiaCore.Event;

public class PublicToilet implements Event {

    @Override
    public void happens(Citizen citizen) {

        int toilet;
        int happy;

        toilet = citizen.getCitizenStatus().getNeeds().getToilet();
        happy = citizen.getCitizenStatus().getEmotions().getHappiness();

        toilet = citizen.getCitizenStatus().getNeeds().getToilet();

        if (toilet > 95) {
            citizen.getCitizenStatus().getMainStatus().setEventTime(2);
            toilet = 0;
            citizen.getCitizenStatus().getMainStatus().setEvent("│PublicToilet│"+"Was poop!");
        } else if (toilet > 50){
            citizen.getCitizenStatus().getMainStatus().setEventTime(1);
            toilet = 0;
            citizen.getCitizenStatus().getMainStatus().setEvent("│PublicToilet│"+"Was pee!");
        }

        citizen.getCitizenStatus().getNeeds().setToilet(toilet);

    }

    @Override
    public void tick() {

    }

    @Override
    public Category[] getCategory() {
        return new Category[0];
    }
}
