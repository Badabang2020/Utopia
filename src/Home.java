public class Home {

    public void goToSleep(Citizen citizen){
        int checkSleep = citizen.getCitizenStatus().getNeeds().getSleep();

        if(checkSleep < 15){
            citizen.getCitizenStatus().getNeeds().setSleep(100);
            citizen.getCitizenStatus().getMainStatus().setEvent("Is sleeping");
            citizen.getCitizenStatus().getMainStatus().setEventTime(7);
        }

        else if(checkSleep < 50){
            checkSleep += 15;
            citizen.getCitizenStatus().getNeeds().setSleep(checkSleep);
            citizen.getCitizenStatus().getMainStatus().setEvent("Is powernapping");
            citizen.getCitizenStatus().getMainStatus().setEventTime(1);
        }
    }
}
