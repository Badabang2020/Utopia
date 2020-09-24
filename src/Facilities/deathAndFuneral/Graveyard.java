package Facilities.deathAndFuneral;


import Citizen.Citizen;
import Facilities.Facilities;
import UtopiaCore.Category;
import UtopiaCore.Event;
import UtopiaCore.GlobalStacker;

import java.util.ArrayList;
import java.util.Random;

public class Graveyard implements Event {

    ArrayList mourners = new ArrayList<Citizen>();//An Arraylist of mourning Citizens
    ArrayList buried = new ArrayList<Citizen>();//An Arraylist of dead Citizens buried in this graveyard
    int ticksSinceLastBurial = 1000;
    String msg = "at the graveyard";//default event-msg for alive citizens who are at the graveyard

    @Override
    public void happens(Citizen citizen) {
        mourners.add(citizen);
        citizen.getCitizenStatus().getMainStatus().setEvent("goes to graveyard");
        citizen.getCitizenStatus().getEmotions().setHappiness((citizen.getCitizenStatus().getEmotions().getHappiness() - 5));
        citizen.getCitizenStatus().getMainStatus().setEventTime(4);
    }

    @Override
    public void tick() {
        ticksSinceLastBurial++;
        if(ticksSinceLastBurial == 1){
            Citizen current = (Citizen) buried.get(buried.size() - 1); // newest buried citizens gets an Eventmessage
            current.getCitizenStatus().getMainStatus().setEvent("dead");
        }


        //funerals can occur if there are dead citizens who haven't had a funeral and enough time has passed since the last funeral.
        //funerals affect all citizens who are currently at the graveyard
        if (Death.notBuried.size() > 0 && ticksSinceLastBurial > 7) {
            ticksSinceLastBurial = 0;
            Citizen corpse = (Citizen) Death.notBuried.get(0);//takes the next Citizen from Death.notBuried
            corpse.getCitizenStatus().getMainStatus().setEvent("getting buried at the Graveyard");
            Death.notBuried.remove(0);//removes the buried Citizen from Death.notBuried list
            this.buried.add(corpse);
            while (mourners.size() > 0) {
                Citizen current = (Citizen) mourners.get(0);
                current.getCitizenStatus().getMainStatus().setEvent(msg + "|| mourning the death of" + corpse.getFirstName() + " " + corpse.getLastName());
                mourners.remove(0);
                current.getCitizenStatus().getMainStatus().setEventTime(4);
                current.getCitizenStatus().getEmotions().setSadness(current.getCitizenStatus().getEmotions().getSadness() - 30);
                current.getCitizenStatus().getEmotions().setHappiness((current.getCitizenStatus().getEmotions().getHappiness() - 30));
            }
        } else {
            for (int i = 0; i < mourners.size(); i++) {
                Citizen current = (Citizen) mourners.get(i);
                current.getCitizenStatus().getMainStatus().setEvent(msg);
                if (current.getCitizenStatus().getMainStatus().getEventTime() == 0) {
                    mourners.remove(i);
                    i--;
                }
            }

        }

    }

    @Override
    public Category[] getCategory() {
        return new Category[]{};
    }
}
