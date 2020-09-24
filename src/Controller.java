import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class Controller {

    enum TickType { // is used only in this class.
        UNDEFINED, FIRSTTICK, NORMALTICK, LASTTICK;
    }


    private  Date startedAT; // used to store the date-time when program  was started ( Date format ). Init in constructor ! :-))

    private TickType currentTickType = TickType.UNDEFINED; // will start ticking undefined and change it as the ticks progresses...

    public Tester tester; // here can the developer test the code. Init in constructor ....

    private Date utopiaTime; // used to store the date-time on Utopia. The world there starts at 01.01.2100 00:00

    public Date getUtopiaTime() {
        return utopiaTime;
    } // here you can obtain Utopia time...
    public Date getStartedAT() { // getter for startedAT
        return startedAT;
    } // not really used


    private long lastUpdateAtMilliSecond = 0; // only to see if a second is passed or not since last tick. Only on second change will be a tick on utopia generated
    Calendar calendar = Calendar.getInstance(); // will also be used to store the time when utopia was started. Search for other posibilities

    Controller() { // CONSTRUCTOR
        this.startedAT = new Date();
        calendar.set(2100, Calendar.JANUARY, 1, 0, 0, 0);
        this.utopiaTime = calendar.getTime();

        tester=new Tester(); // create an object for the Tester.
    }

    Controller(Date startDateTime) { // CONSTRUCTOR

    } // end of constructor

    // ----------------------------------|  T h e   T I C K |---------------------------------------\\
    public void tick() {

        // we check if ever ticked, if that's the case, run the initialisations.
        if (currentTickType == TickType.UNDEFINED){
            currentTickType = TickType.FIRSTTICK;
            tester.firstTick(); // run the init procedure in Tester.
            currentTickType = TickType.NORMALTICK;
            this.tickAllActivities();
            tester.middleTick();
        }
        else if (currentTickType == TickType.NORMALTICK){ // after the init method, we run the normal tick
            this.tickAllActivities();
            tester.middleTick(); // run the middleTick method in tester
            if (this.lastUpdateAtMilliSecond >= GlobalStacker.stopUtopiaAfterSoManyMilliseconds) this.stopUtopia(); // utopia will end on next tick()
        }
        else if (currentTickType == TickType.LASTTICK){ // after the init method, we run the normal tick
            this.tickAllActivities();
            tester.middleTick(); // run the middleTick method in tester
            tester.lastTick();
            GlobalStacker.utopiaIsRunning = false; // this will cause the cycle method of this class to return false to main() and end the program
        }

    } //  end of tick()




    public boolean cycle() {

        // here we call the before first tick method in Tester.
        if (this.lastUpdateAtMilliSecond == 0)  tester.beforeFirstTick();


        // we read the system clock here. If it passed more then this.utopiaTime since the last tick, we gonna do a new tick.

        long diffInMillies = Math.abs(new Date().getTime() - startedAT.getTime()); // new Date().getTime() will return current date&time.
        long diff = TimeUnit.MILLISECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS); // represents the milliseconds between start of the program and now.

        if (  (diff > this.lastUpdateAtMilliSecond + GlobalStacker.doATickEverySoManyMilliseconds) || this.lastUpdateAtMilliSecond==0  ){ // will be executed if this.utopiaTime passed since the last tick()
            this.lastUpdateAtMilliSecond = diff;
            int diffInMillisecondsSinceUtopiaRunsInEarthMillisecondsIs = (int)((new Date()).getTime()- this.startedAT.getTime());
            calendar.set(2100, Calendar.JANUARY, 1, 0, 0, 0);
            calendar.add(Calendar.MILLISECOND, diffInMillisecondsSinceUtopiaRunsInEarthMillisecondsIs * GlobalStacker.oneSecondOnEarthEqualsThisManySecondsOnUtopia);
            this.utopiaTime = calendar.getTime();
            System.out.println("\r\nCalling a new tick! Utopia is running since:" + diff + " earth milliseconds. Time on Utopia is now:" + this.utopiaTime.toString());
            this.tick(); // here happens everything.
        }
        return GlobalStacker.utopiaIsRunning;

    } // end of cycle()

    public void stopUtopia(){
        System.out.println("* * * Controller: STOP UTOPIA WAS CALLED. Utopia will end shortly. * * *");
        this.currentTickType= TickType.LASTTICK;
    } // end of stopUtopia



    //every activity that is created must be registered here to be able to make an offer to the citizen.
    public Integer registerActivity(Event activity) { // the activity will be stored in GlobalStacker \\
        GlobalStacker.registeredActivities.add( activity);
        return GlobalStacker.numberOfRegisteredActivities;
    }

    // call this method to register a citizen (add it to ArrayList GlobalStacker.registredCitizens )
    public void registerCitizen(Citizen citizen) {
        GlobalStacker.registredCitizens.add(citizen);
        System.out.println("The Citizen " + citizen.toString() + " was registered.");
    }

   public void doActivity(Citizen citizen, Event activity){
        if (citizen==null || activity == null){
            System.out.println("!!!!!!!!!!!!!!!! Controller.doActivity(Citizen citizen, Event activity) ERROR: citizen or event object is null ");
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

    public Event getMyEvent(String filter){
        Event myReturnEvent = null ;
        for(Event s: GlobalStacker.registeredActivities){
            if (s.getClass().getSimpleName().toLowerCase().startsWith(filter))
                myReturnEvent =  s;
        }
        if (myReturnEvent==null) System.out.println("Error in Controller : public Event getMyEvent(String filter). Filter ist nicht ok.");
        return myReturnEvent;
    }


    // it can be used to tick all Events. It is used in controller tick.
    public void tickAllActivities(){
        for (Event activity: GlobalStacker.registeredActivities) activity.tick();
    }




    public Event getBestOfferForCitizen(Citizen citizen){

        Category[] myCategListe = Category.values();
        for (Category myEnumElement :myCategListe ) {
            System.out.println(myEnumElement.toString());
        }
        System.out.println("EnumListe:"+ Arrays.toString(myCategListe));

        // we build now a "table" of Arraylist<MyClass>

        class MyClass {
          Category category;
          Double citizenhas;
          Double citizenHasMax;
          Double citizenneeds;
        }

        MyClass myclass = new MyClass();


        return  null;
    }





}
