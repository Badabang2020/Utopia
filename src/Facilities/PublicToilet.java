package Facilities;

import Citizen.Citizen;
import UtopiaCore.Category;
import UtopiaCore.Event;

public class PublicToilet implements Event {

    @Override
    public void happens(Citizen citizen) {

        int toilet;
        int happy;
        int money;

        toilet = citizen.getCitizenStatus().getNeeds().getToilet();
        happy = citizen.getCitizenStatus().getEmotions().getHappiness();
        money = citizen.getCitizenStatus().getMainStatus().getWallet();



        toilet = citizen.getCitizenStatus().getNeeds().getToilet();
        if (money >= 1) {
            if (toilet > 95) {
                citizen.getCitizenStatus().getMainStatus().setEventTime(2);
                toilet = 0;
                citizen.getCitizenStatus().getMainStatus().setEvent("<│PublicToilet│> " + "Was poop!");
            } else if (toilet > 50) {
                citizen.getCitizenStatus().getMainStatus().setEventTime(1);
                toilet = 0;
                citizen.getCitizenStatus().getMainStatus().setEvent("<│PublicToilet│> " + "Was pee!");
            }
            money--;
            citizen.getCitizenStatus().getNeeds().setToilet(toilet);
            citizen.getCitizenStatus().getMainStatus().setWallet(money);
        }
        citizen.getCitizenStatus().getMainStatus().setEvent("<│PublicToilet│> " + "You need at least 1 UT$");
    }

    @Override
    public void tick() {

    }

    @Override
    public Category[] getCategory() {
        return new Category[0];
    }
}
