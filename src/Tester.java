import java.util.ArrayList;

public class Tester {

    static Integer runTheTestOnEveryThisNumberOfTicks = 1; // Change this value to set tick sequence that will run the following method

    public void runDeveloperTest(){ // this method will run on each tick.
        System.out.println("---------------------  Hello from tester. -----------------------");

        //delete what you want from this method, this code is an example.
        // Referencing the controller like         Main.myController  will not work!



        System.out.println("Utopia time is "+UtopiaMain.myController.getUtopiaTime());

        Event restaurant = new Restaurant();
        Event doctor = new Doctor();
        Event bank = new Bank();
        Event home = new Home();

        UtopiaMain.myController.registerActivity(restaurant);
        UtopiaMain.myController.registerActivity(doctor);
        UtopiaMain.myController.registerActivity(bank);
        UtopiaMain.myController.registerActivity(home);

        Citizen citizen = new Citizen("First name","Last name","223",'f', 25, new Address(), new GKK(), false, new CitizenStatus());
        UtopiaMain.myController.registerCitizen(citizen);


        System.out.println(UtopiaMain.myController.getRandomActivity());
        System.out.println(UtopiaMain.myController.getRandomCitizen());




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

        for (Event e : toiletEvents) {
            System.out.println(e);
        }


        //Main.myController.generateCitizens(25);
        for (int i = 0 ; i < 10; i++) {
            UtopiaMain.myController.doActivity( UtopiaMain.myController.getRandomCitizen(), UtopiaMain.myController.getRandomActivity());
        }





        System.out.println("------------------------------------------------------------------");
    }
}
