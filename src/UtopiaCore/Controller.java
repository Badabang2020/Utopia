package UtopiaCore;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import Citizen.*;


public class Controller {
    private  Date startedAT = new Date(); // used to store the date-time when utopia was started.

    public Tester tester=new Tester(); // here can the developer seinen code testen

    public Date getStartedAT() { // getter for startedAT
        return startedAT;
    }

    private Date utopiaTime; // used to store the date-time on Utopia. The world there starts at 01.01.2100 00:00

    public Date getUtopiaTime() {
        return utopiaTime;
    }

    private long lastUpdateAtSecond = 0; // only to see if a second is passed or not since last tick. Only on second change will be a tick on utopia generated
    Calendar calendar = Calendar.getInstance(); // will also be used to store the time when utopia was started. Search for other posibilities

    Controller() { // CONSTRUCTOR
        this.startedAT = new Date();
        calendar.set(2100, Calendar.JANUARY, 1, 0, 0, 0);
        this.utopiaTime = calendar.getTime();
    }

    Controller(Date startDateTime) { // CONSTRUCTOR

    } // end of constructor

    // ----------------------------------|  T h e   T I C K |---------------------------------------\\
    public void tick() {
<<<<<<< HEAD
        if (this.lastUpdateAtSecond==10) GlobalStacker.stopUtopia();
=======
        if (this.lastUpdateAtSecond==25) GlobalStacker.stopUtopia();
>>>>>>> develop

        // let's tick every citizen - must be implementet in citizen
//        for (Citizen.Citizen citizen: UtopiaCore.GlobalStacker.registredCitizens) {
//            citizen.ontick();
//        }

        if (this.lastUpdateAtSecond % Tester.runTheTestOnEveryThisNumberOfTicks == 0) tester.runDeveloperTest(); // here can we put some test code.


    } //  end of tick()




    public boolean cycle() { // this can be run as many times as wished. If a complete second is passed since last tick(), then a new tick() is called
        // on each tick we must update utopiaTime !
        // one step has 900 utopia seconds = 15min.
//        final int ONEEARTHSECONDIS = 900;
        long diffInMillies = Math.abs(new Date().getTime() - startedAT.getTime());
        long diff = TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS); // represents the seconds between start of the program and now.
        if (diff != this.lastUpdateAtSecond) {
            this.lastUpdateAtSecond = diff;

            this.utopiaTime = GlobalStacker.addSecondsToJavaUtilDate(calendar.getTime(), (int) (diff * GlobalStacker.oneSecondOnEarthEqualsThisManySecondsOnUtopia));
            System.out.println("\r\nCalling a new tick! Utopia is running since:" + diff + " earth seconds. Time on Utopia is now:" + this.utopiaTime.toString());
            this.tick(); // here happens everything.
        }
        return GlobalStacker.utopiaIsRunning;

    } // end of cycle()

    //every activity that is created must be registered here to be able to make an offer to the citizen.
    public Integer registerActivity(Event activity) { // the activity will be stored in UtopiaCore.GlobalStacker \\
        GlobalStacker.registeredActivities.add( activity);
        return GlobalStacker.numberOfRegisteredActivities;
    }

    // call this method to register a citizen (add it to ArrayList UtopiaCore.GlobalStacker.registredCitizens )
    public void registerCitizen(Citizen citizen) {
        GlobalStacker.registredCitizens.add(citizen);
        System.out.println("The Citizen.Citizen " + citizen.toString() + " was registered.");
    }

   public void doActivity(Citizen citizen, Event activity){
        if (citizen==null || activity == null){
            System.out.println("!!!!!!!!!!!!!!!! doActivity wurde mit null parameter aufgerufen. Wird nicht ausgeführt");
            return;
        }
       citizen.doEvent(activity);
   }


    public Citizen getRandomCitizen(){
        if (GlobalStacker.registredCitizens.size()>0)
            return GlobalStacker.registredCitizens.get(GlobalStacker.generateRandomInteger(0,GlobalStacker.registredCitizens.size()-1));
        return null;
    }

    public Event getRandomActivity(){
        if (GlobalStacker.registeredActivities.size()>0) {
            Integer randmomIndex = GlobalStacker.generateRandomInteger(0, GlobalStacker.registeredActivities.size() - 1);
            return GlobalStacker.registeredActivities.get(GlobalStacker.generateRandomInteger(0,GlobalStacker.registeredActivities.size()-1));
        }
        return null; // if the size of ArrayList activities is zero

    }



//    private Date addSecondsToJavaUtilDate(Date date, int seconds) { // used to add seconds to a date. => utopiaTime
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.add(Calendar.SECOND, seconds);
//        return calendar.getTime();
//    }


}
