import java.util.ArrayList;
import java.util.Random;

public class Tester {

    static Integer runTheTestOnEveryThisNumberOfTicks = 1; // Change this value to set tick sequence that will run the following method
    Event restaurant = new Restaurant();
    Event doctor = new Doctor();
    Event bank = new Bank();
    Event themepark = new Themepark();
    Event lottery = new Lottery();
    Event home = new Home();
    Event ambulance = new Ambulance();
    Event cinema = new Cinema();


    Random rand = new Random();


    public void runDeveloperTest(){ // this method will run on each tick.
        System.out.println("---------------------  Hello from tester. -----------------------");

        //delete what you want from this method, this code is an example.
        // Referencing the controller like         Main.myController  will not work!

        System.out.println("Utopia time is "+UtopiaMain.myController.getUtopiaTime());

        Random rdm = new Random();

        if (GlobalStacker.registredCitizens.size() == 0 && GlobalStacker.registeredActivities.size() == 0) {
            UtopiaMain.myController.registerActivity(bank);
            UtopiaMain.myController.registerActivity(themepark);
            UtopiaMain.myController.registerActivity(restaurant);
            UtopiaMain.myController.registerActivity(doctor);
            UtopiaMain.myController.registerActivity(home);
            UtopiaMain.myController.registerActivity(lottery);
            UtopiaMain.myController.registerActivity(ambulance);
            UtopiaMain.myController.registerActivity(cinema);
            for (int i = 0; i < 100; i++) {
                UtopiaMain.myController.registerCitizen(new Citizen("" + i, ""+i+"!", "" + rdm.nextInt(1000000000), 'm', rdm.nextInt(100), new Address(), new GKK(), false, new CitizenStatus()));
            }
        }

        for (int i = 0; i < GlobalStacker.registeredActivities.size(); i++) {
            GlobalStacker.registeredActivities.get(i).tick();
        }

        for (int i = 0 ; i < GlobalStacker.registredCitizens.size(); i++) {
            Citizen citizen = GlobalStacker.registredCitizens.get(i);
            if(citizen.getCitizenStatus().getMainStatus().getEventTime()==0){
                if(citizen.getCitizenStatus().getMainStatus().getWallet()<30){
                    citizen.doEvent(bank);
                }
                else{
                    citizen.doEvent(GlobalStacker.registeredActivities.get(rand.nextInt(GlobalStacker.registeredActivities.size())));
                }
            }
            else{
                citizen.getCitizenStatus().getMainStatus().setEventTime(citizen.getCitizenStatus().getMainStatus().getEventTime()-1);
            }
            System.out.println(citizen);
            System.out.println("");


        }








        System.out.println("------------------------------------------------------------------");
    }
}
