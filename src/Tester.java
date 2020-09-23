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

        // Here you can speed up and slow down Utopia. See adjustments and variable names for more info.
        // If you delete this , the default values in GlobalStacker will be used.
        GlobalStacker.oneSecondOnEarthEqualsThisManySecondsOnUtopia = 3600; // One second on earth = 3600 seconds ( 1 Hour ) on Utopia.
        GlobalStacker.doATickEverySoManyMilliseconds = 250; // If you want 5 ticks in one second put here 200.
        GlobalStacker.stopUtopiaAfterSoManyMilliseconds = 10000; // this are 5 seconds.
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


    // use for init of Events and Citizens ---- F I R S T   T I C K  -----
    public void firstTick(){
        System.out.println("TESTER - > firstTick()");
        // put your initialisation code here






    } // end of first Tick



    // will be executed repeatedly on each tick. ---- M I D D L E   T I C K  -----
    // ! it will be executed also on the first tick, right after   this.firstTick()
    public void middleTick(){
        System.out.println("TESTER - > MIDDLE TICK  -----------------------------------------");
        // put your "tick" code here
        System.out.println("Utopia time is "+UtopiaMain.myController.getUtopiaTime());

        Random rand = new Random();

        System.out.println( "One random activity-> "+GlobalStacker.registeredActivities.get(rand.nextInt(GlobalStacker.registeredActivities.size())).getClass().getSimpleName());

        System.out.println("Bank is "+ UtopiaMain.myController.getMyEvent("bank"));


        //delete what you want from this method, this code is an example.
        // Referencing the controller like         Main.myController  will not work!

        System.out.println("Utopia time is "+UtopiaMain.myController.getUtopiaTime());



        for (int i = 0 ; i < GlobalStacker.registredCitizens.size(); i++) {
            Citizen citizen = GlobalStacker.registredCitizens.get(i);
            if(citizen.getCitizenStatus().getMainStatus().getEventTime()==0){
                if(citizen.getCitizenStatus().getMainStatus().getWallet()<30){
                    citizen.doEvent( UtopiaMain.myController.getMyEvent("bank") );
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





    } // end of middleTick



    // can be useful to put some cleanup code  ---- L A S T   T I C K  -----
    // ! on the last tick , the middleTick() is called and after that will be executed lastTick()
    public void lastTick(){
        System.out.println("TESTER - > LAST TICK");
    }


} // end of lastTick()




