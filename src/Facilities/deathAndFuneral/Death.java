package Facilities.deathAndFuneral;

import Citizen.Citizen;
import Facilities.Facilities;

import UtopiaCore.Category;
import UtopiaCore.Event;
import UtopiaCore.UtopiaMain;

import java.util.ArrayList;

public class Death implements Event{



    static ArrayList notBuried = new ArrayList(); //an Arraylist of all not yet buried dead citizens

    @Override
    public void happens(Citizen citizen) {
        citizen.getCitizenStatus().getMainStatus().setEvent("died");
        citizen.getCitizenStatus().getMainStatus().setEventTime(-1);
        notBuried.add(citizen);
    }

    @Override
    public void tick() {
        for (int i = 0; i < notBuried.size(); i++) {
            Citizen current = (Citizen)notBuried.get(i);
            current.getCitizenStatus().getMainStatus().setEvent("preparing for Funeral");
        }
    }

    @Override
    public Category[] getCategory() {
        return new Category[]{};
    }

    public void bury(){
        notBuried.remove(0);
    }
}
