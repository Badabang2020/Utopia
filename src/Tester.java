import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class Tester {

    public void runDeveloperTest(){ // this method is not used anymore.
        // please move your code in middleTick().
    }


    // this will be executed before first tick
    public void beforeFirstTick(){
        System.out.println(" Starting beforeFirstTick() method in Tester : -------------------------------");

        // Here you can speed up and slow down Utopia. See adjustments and variable names for more info.
        // If you delete this , the default values in GlobalStacker will be used.
        GlobalStacker.oneSecondOnEarthEqualsThisManySecondsOnUtopia = 3600; // One second on earth = 3600 seconds ( 1 Hour ) on Utopia.
        GlobalStacker.doATickEverySoManyMilliseconds = 1000; // If you want 5 ticks in one second put here 200.
        GlobalStacker.stopUtopiaAfterSoManyMilliseconds = 5000; // this are 5 seconds.
        System.out.printf("| Tester is setting a custom Utopia time: 1sec = %s Utopia seconds. | Frequency of ticks: every %s milliseconds | Utopia will run for %s milliseconds.\r\n",  GlobalStacker.oneSecondOnEarthEqualsThisManySecondsOnUtopia,GlobalStacker.doATickEverySoManyMilliseconds, GlobalStacker.stopUtopiaAfterSoManyMilliseconds  );
        // ---------------------------------------------------------------------------------------------


        Event restaurant = new Restaurant();
        Event doctor = new Doctor();
        Event bank = new Bank();
        Event themepark = new Themepark();
        Event lottery = new Lottery();
        Event home = new Home();
        Event ambulance = new Ambulance();
        Event cinema = new Cinema();


        // please register every Event object here.
        // you can retriev your object later like this : "UtopiaMain.myController.getMyEvent("bank")".
        UtopiaMain.myController.registerActivity(bank);
        UtopiaMain.myController.registerActivity(themepark);
        UtopiaMain.myController.registerActivity(restaurant);
        UtopiaMain.myController.registerActivity(doctor);
        UtopiaMain.myController.registerActivity(home);
        UtopiaMain.myController.registerActivity(lottery);
        UtopiaMain.myController.registerActivity(ambulance);
        UtopiaMain.myController.registerActivity(cinema);

        Random rdm = new Random();

        for (int i = 0; i < 5; i++) {
            UtopiaMain.myController.registerCitizen(new Citizen("" + i, ""+i+"!", "" + rdm.nextInt(1000000000), 'm', rdm.nextInt(100), new Address(), new GKK(), false, new CitizenStatus()));
        }


    }


    //-------------------------------------------- F I R S T   T I C K  -----
    // this will be executed before the first tick
    public void firstTick(){
        System.out.println("TESTER - > FIRST TICK ------------------------------------------");
        // if you need to run some code on the first tick, use this method. For initialisations, use "beforeFirstTick()"

        UtopiaMain.myController.getEventsListForCategory(Category.Fun);

        UtopiaMain.myController.getBestOfferForCitizen(UtopiaMain.myController.getRandomCitizen()); // call for test the best offer 4 citizen method.

    } // end of first Tick




    // will be executed repeatedly on each tick. ---- M I D D L E   T I C K  -----
    // ! it will be executed also on the first tick, right after   "firstTick()" and before "lastTick()"
    public void middleTick(){
        System.out.println("TESTER - > MIDDLE TICK  -----------------------------------------");
        // put your "tick" code here

        // I make variable myController with a reference ( not a new object ) to the existing UtopiaMain.myController object, for easier code reading ....
        Controller myController = UtopiaMain.myController;
        // but you can use also the original reference : "UtopiaMain.myController" like this:
        System.out.println("Utopia time is "+UtopiaMain.myController.getUtopiaTime());


        System.out.println( "One random activity-> "+myController.getRandomActivity().getClass().getSimpleName()); // myController.getRandomActivity() will return an Event object , and .getClass().getSimpleName() return the class name of the object.
        System.out.println("One random citizen -> "+ myController.getRandomCitizen().toString());

        // use filter parameter like in the following statements, to retrieve your  Event type object:
        System.out.println("Bank Event object is :"+ myController.getMyEvent("bank"));
        System.out.println("themepark Event object is :"+ myController.getMyEvent("themepark"));
        System.out.println("restaurant Event object is :"+ myController.getMyEvent("restaurant"));
        System.out.println("doctor Event object is :"+ myController.getMyEvent("doctor"));
        System.out.println("home Event object is :"+ myController.getMyEvent("home"));
        System.out.println("lottery Event object is :"+ myController.getMyEvent("lottery"));

        //an other example to retrieve and use your event:
        System.out.println(" // Testing random citizen with chosen Event \\");
        Event myRestaurant = myController.getMyEvent("restaurant");
        Citizen myRandomCitizen = myController.getRandomCitizen();
        System.out.println("My random citizen before cinema "+myRandomCitizen.getSocialSecurityNumber()+myRandomCitizen.toString());
        myController.doActivity(myRandomCitizen, myRestaurant);
        System.out.println("My random citizen after cinema "+myRandomCitizen.getSocialSecurityNumber()+myRandomCitizen.toString());
        System.out.println("\\   ------------------------------------    //");


        // some sample code from you ... , but it's old ....
        for (int i = 0 ; i < GlobalStacker.registredCitizens.size(); i++) {
            Citizen citizen = GlobalStacker.registredCitizens.get(i);
            if(citizen.getCitizenStatus().getMainStatus().getEventTime()==0){
                if(citizen.getCitizenStatus().getMainStatus().getWallet()<30){
                    citizen.doEvent( myController.getMyEvent("bank") );
                }
                else{
                    citizen.doEvent(myController.getRandomActivity());
                }
            }
            else{
                citizen.getCitizenStatus().getMainStatus().setEventTime(citizen.getCitizenStatus().getMainStatus().getEventTime()-1);
            }
            System.out.println(citizen);
            System.out.println("");
        }
    } // end of middleTick



    // can be useful to put some cleanup code  ---- L A S T   T I C K  -----
    // ! on the last tick , the middleTick() is called and after that will be executed lastTick()
    public void lastTick(){
       System.out.println("TESTER - > LAST TICK -----------------------------------------------------");
    }


} // end of lastTick()




