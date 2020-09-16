import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class Controller {
    private  Date startedAT = new Date(); // used to store the date-time when utopia was started.


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
        if (this.lastUpdateAtSecond == 10) GlobalStacker.stopUtopia();
        if (this.lastUpdateAtSecond == 3) {
//            this.doActivity(GlobalStacker.registeredActivities.get(1), Main.myCitizen);
        }

        // let's tick every citizen
//        for (Citizen citizen : GlobalStacker.registredCitizens) {
//            citizen.ontick();
//        }


    } //  end of tick()




    public boolean cycle() { // this can be run as many times as wished. If a complete second is passed since last tick(), then a new tick() is called
        // on each tick we must update utopiaTime !
        // one step has 900 utopia seconds = 15min.
        final int ONEEARTHSECONDIS = 900;
        long diffInMillies = Math.abs(new Date().getTime() - startedAT.getTime());
        long diff = TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS); // represents the seconds between start of the program and now.
        if (diff != this.lastUpdateAtSecond) {
            this.lastUpdateAtSecond = diff;

            this.utopiaTime = GlobalStacker.addSecondsToJavaUtilDate(calendar.getTime(), (int) (diff * GlobalStacker.oneSecondOnEarthEqualsThisManySecondsOnUtopia));
            this.utopiaTime = addSecondsToJavaUtilDate(calendar.getTime(), (int) (diff * ONEEARTHSECONDIS));
            System.out.println("Calling a new tick! Utopia is running since:" + diff + " earth seconds. Time on Utopia is now:" + this.utopiaTime.toString());
            System.out.println("\r\nCalling a new tick! Utopia is running since:" + diff + " earth seconds. Time on Utopia is now:" + this.utopiaTime.toString());
            this.tick(); // here happens everything.
        }
        return GlobalStacker.utopiaIsRunning;

    } // end of cycle()

    //every activity that is created must be registered here to be able to make an offer to the citizen.
    public Integer registerActivity(Event activity) { // the activity will be stored in GlobalStacker \\
       // GlobalStacker.registeredActivities.put(++GlobalStacker.numberOfRegisteredActivities, activity);
        return GlobalStacker.numberOfRegisteredActivities;
    }

    // call this method to register a citizen (add it to ArrayList GlobalStacker.registredCitizens )
    public void registerCitizen(Citizen citizen) {
        GlobalStacker.registredCitizens.add(citizen);
        System.out.println("The Citizen " + citizen.toString() + " was registered.");
    }

   public void doActivity(Citizen citizen, Event activity){
       citizen.doEvent(activity);
   }




    private Date addSecondsToJavaUtilDate(Date date, int seconds) { // used to add seconds to a date. => utopiaTime
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
    }


}
