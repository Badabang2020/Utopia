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

    public void goToToilet(Citizen citizen){
        int checkToilet = citizen.getCitizenStatus().getNeeds().getToilet();

        if(checkToilet < 15) {
            citizen.getCitizenStatus().getNeeds().setToilet(100);
            citizen.getCitizenStatus().getMainStatus().setEvent("Is on a big shit");
            citizen.getCitizenStatus().getMainStatus().setEventTime(1);
        }
        else if(checkToilet < 50){
            checkToilet += 15;
            citizen.getCitizenStatus().getNeeds().setToilet(checkToilet);
            citizen.getCitizenStatus().getMainStatus().setEvent("Is on a small business");
            citizen.getCitizenStatus().getMainStatus().setEventTime(1);
        }
    }
}
