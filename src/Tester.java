import java.util.ArrayList;
import java.util.Random;

public class Tester {

    static Integer runTheTestOnEveryThisNumberOfTicks = 1; // Change this value to set tick sequence that will run the following method
    Event restaurant = new Restaurant();
    Event doctor = new Doctor();
    Event bank = new Bank();
    Event home = new Home();


    public void runDeveloperTest(){ // this method will run on each tick.
        System.out.println("---------------------  Hello from tester. -----------------------");

        //delete what you want from this method, this code is an example.
        // Referencing the controller like         Main.myController  will not work!

        System.out.println("Utopia time is "+UtopiaMain.myController.getUtopiaTime());

        Random rdm = new Random();

        if (GlobalStacker.registredCitizens.size() == 0 && GlobalStacker.registeredActivities.size() == 0) {
            UtopiaMain.myController.registerActivity(restaurant);
            UtopiaMain.myController.registerActivity(doctor);
            UtopiaMain.myController.registerActivity(bank);
            UtopiaMain.myController.registerActivity(home);
            for (int i = 0; i < 3; i++) {
                UtopiaMain.myController.registerCitizen(new Citizen("!!!!!!! " + i + " !!!!!!", "XXXXXX", "" + rdm.nextInt(100), 'm', 30, new Address(), new GKK(), false, new CitizenStatus()));
            }
        }




        Event[] events = new Event[] {restaurant, doctor, bank, home};

        ArrayList<Event> toiletEvents = new ArrayList<>();

        for (Event e : events) {
            Category[] cats = e.getCategory();
            for (Category c : cats) {
                if (c == Category.Money) {
                    toiletEvents.add(e);
                }
            }
        }


        //Main.myController.generateCitizens(25);
        for (int i = 0 ; i < GlobalStacker.registredCitizens.size(); i++) {
            Citizen citizen = GlobalStacker.registredCitizens.get(i);
            citizen.doEvent(bank);
            System.out.println(citizen);
        }





        System.out.println("------------------------------------------------------------------");
    }
}
