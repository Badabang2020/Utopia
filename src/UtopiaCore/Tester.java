package UtopiaCore;

import Bank.Bank;
import Facilities.*;

import Health.*;
import Citizen.*;
import HealthInsurance.*;

import java.util.Random;

public class Tester {

    // constructor. put your initialisation code here
    public Tester() {

        System.out.println(" Starting beforeFirstTick() method in Tester : -------------------------------");

        // Here you can speed up and slow down Utopia. See adjustments and variable names for more info.
        // If you delete this , the default values in GlobalStacker will be used.
        UtopiaMain.myGlobalStacker.oneTickIsSoManySecondsOnUtopia = 900; // One tick = 900 seconds ( 15 min ) on Utopia.
        UtopiaMain.myGlobalStacker.waitSoManyMillisecondsBetweenTicks = 0; // will wait so many seconds between the ticks.
        UtopiaMain.myGlobalStacker.stopUtopiaAfterSoManyTicks = 10; // this are 5 seconds.
        System.out.printf("| Utopia time Schema: 1tick = %s Utopia seconds. | Wait between ticks: every %s milliseconds | Utopia will run for %s ticks.\r\n",  UtopiaMain.myGlobalStacker.oneTickIsSoManySecondsOnUtopia, UtopiaMain.myGlobalStacker.waitSoManyMillisecondsBetweenTicks, UtopiaMain.myGlobalStacker.stopUtopiaAfterSoManyTicks);
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
        //UtopiaMain.myController.registerActivity(ambulance); // here is an error in ambulance. can't use it.
        UtopiaMain.myController.registerActivity(cinema);

        Random rdm = new Random();
        int amountOfCitizens = 5;

        for (int i = 0; i < amountOfCitizens ; i++) {
            System.out.println("Registering a new citizen : ");
            UtopiaMain.myController.registerCitizen(new Citizen("" + i, ""+i+"!", "" + rdm.nextInt(1000000000), 'm', rdm.nextInt(100), new Address(), new GKK(), false, new CitizenStatus()));
        }



    }

    public void runDeveloperTest(){ // this method is not used anymore.
        // please move your code in mainTick().
    }


    //-------------------------------------------- F I R S T   T I C K  -----
    // this will be executed before the first tick
    public void firstTick(){
        System.out.println("TESTER - > FIRST TICK ------------------------------------------");
        // if you need to run some code on the first tick, use this method. For initialisations, use "beforeFirstTick()"

        System.out.println("getEventsListForCategory(Category.Fun) returns : ");
        UtopiaMain.myController.getEventsListForCategory(Category.Fun);


    } // end of first Tick




    // will be executed repeatedly on each tick. ---- M I D D L E   T I C K  -----
    // ! it will be executed also on the first tick, right after   "firstTick()" and before "lastTick()"
    public void mainTick(){
        System.out.println("TESTER - > MAIN TICK  -----------------------------------------");
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
        for (int i = 0; i < UtopiaMain.myGlobalStacker.registredCitizens.size(); i++) {
            Citizen citizen = UtopiaMain.myGlobalStacker.registredCitizens.get(i);
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




