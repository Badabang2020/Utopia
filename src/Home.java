public class Home implements Event{
    @Override
    public void happens(Citizen citizen) {
        goToToilet(citizen);
        goToSleep(citizen);
    }

    @Override
    public void tick() {

    }

    @Override
         public Category[] getCategory() {
            Category[] category = new Category[] {Category.Sleep, Category.Toilet};
            return category;
         }

    public void goToSleep(Citizen citizen){
        // creating variable to store sleep value from citizen
        int checkSleep = citizen.getCitizenStatus().getNeeds().getSleep();
        // if sleep is under 15, citizen should sleep 7 hours - gaining full recovery
        if(checkSleep < 15){
            citizen.getCitizenStatus().getNeeds().setSleep(100);
            citizen.getCitizenStatus().getMainStatus().setEvent("Is sleeping");
            citizen.getCitizenStatus().getMainStatus().setEventTime(7);
        }
        // if sleep is under 50, citizen should sleep 1 hours (power nap) - gaining 15 recovery
        else if(checkSleep < 50){
            while (checkSleep < 50) {
                checkSleep += 15;
                citizen.getCitizenStatus().getNeeds().setSleep(checkSleep);
                citizen.getCitizenStatus().getMainStatus().setEvent("Is powernapping");
                citizen.getCitizenStatus().getMainStatus().setEventTime(1);
            }
        }
    }

    public void goToToilet(Citizen citizen){
        // creating variable to store toilet value from citizen
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
