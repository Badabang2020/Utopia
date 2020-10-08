package UtopiaCore;

import java.util.*;
import java.util.concurrent.TimeUnit;

import Citizen.*;


public class Controller {

    enum TickType { // is used only in this class.
        UNDEFINED, FIRSTTICK, NORMALTICK, LASTTICK;
    }


    private  Date startedAT; // used to store the date-time when program  was started ( Date format ). Init in constructor ! :-))

    private Integer tickCountSinceStartup = -1; // will start with -1 and will increment it before the first tick....

    private TickType currentTickType = TickType.UNDEFINED; // will start ticking undefined and change it as the ticks progresses...

    public Tester tester; // here can the developer test the code. Will be initialized in UtopiaMain

    private Date utopiaTime; // used to store the date-time on Utopia. The world there starts at 01.01.2100 00:00

    public Date getUtopiaTime() {
        return utopiaTime;
    } // here you can obtain Utopia time...
    public Date getStartedAT() { // getter for startedAT
        return startedAT;
    } // not really used


    Calendar calendar = Calendar.getInstance(); // will also be used to store the time when utopia was started. Search for other posibilities

    public void  start(){
        this.tester= new Tester(); // create an object for the Tester.
    }
    public Controller() { // CONSTRUCTOR
        this.startedAT = new Date();
        calendar.set(2100, Calendar.JANUARY, 1, 0, 0, 0);
        this.utopiaTime = calendar.getTime();
    }

    // ----------------------------------|  T h e   T I C K |---------------------------------------\\
    public void tick() {


        // we check if ever ticked, if that's the case, run the initialisations.
        if (currentTickType == TickType.UNDEFINED){
            currentTickType = TickType.FIRSTTICK;
            tester.firstTick(); // run the init procedure in Tester.
            currentTickType = TickType.NORMALTICK;
            this.tickAllActivities();
            tester.mainTick();
        }
        else if (currentTickType == TickType.NORMALTICK){ // after the init method, we run the normal tick
            this.tickAllActivities();
            tester.mainTick(); // run the middleTick method in tester
        }
        else if (currentTickType == TickType.LASTTICK){ // after the init method, we run the normal tick
            this.tickAllActivities();
            tester.mainTick(); // run the middleTick method in tester
            tester.lastTick();
            UtopiaMain.myGlobalStacker.utopiaIsRunning = false; // this will cause the cycle method of this class to return false to main() and end the program
        }

    } //  end of tick()




    public boolean cycle() throws InterruptedException {

        // increment tick count
        this.tickCountSinceStartup++;

        // calculate the utopia Time with the current tick time.
        calendar.set(2100, Calendar.JANUARY, 1, 0, 0, 0);
        calendar.add(Calendar.SECOND, this.tickCountSinceStartup * UtopiaMain.myGlobalStacker.oneTickIsSoManySecondsOnUtopia);
        this.utopiaTime = calendar.getTime();

        System.out.println("\r\n * * * Calling Tick Nr:" + this.tickCountSinceStartup + ". Time on Utopia is now:" + this.utopiaTime.toString());
        // here happens everything.
        this.tick();

        // check if we should stop utopia
        if (UtopiaMain.myGlobalStacker.stopUtopiaAfterSoManyTicks == (this.tickCountSinceStartup+1)) this.stopUtopia(); // after stop will run 1 supplementary tick

        // wait between ticks ....
        Thread.sleep(UtopiaMain.myGlobalStacker.waitSoManyMillisecondsBetweenTicks);

        // return false if utopia should stop
        return UtopiaMain.myGlobalStacker.utopiaIsRunning;
    } // end of cycle()

    public void stopUtopia(){
        System.out.println("* * * Controller: STOP UTOPIA WAS CALLED. Utopia will end shortly. * * *");
        this.currentTickType= TickType.LASTTICK;
    } // end of stopUtopia



    //every activity that is created must be registered here to be able to make an offer to the citizen.
    public Integer registerActivity(Event activity) { // the activity will be stored in GlobalStacker \\
        UtopiaMain.myGlobalStacker.registeredActivities.add( activity);
        return UtopiaMain.myGlobalStacker.numberOfRegisteredActivities;
    }

    // call this method to register a citizen (add it to ArrayList UtopiaMain.myGlobalStacker.registredCitizens )
    public void registerCitizen(Citizen citizen) {
        UtopiaMain.myGlobalStacker.registredCitizens.add(citizen);
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
        if (UtopiaMain.myGlobalStacker.registredCitizens.size()>0)
            return UtopiaMain.myGlobalStacker.registredCitizens.get(UtopiaMain.myGlobalStacker.generateRandomInteger(0, UtopiaMain.myGlobalStacker.registredCitizens.size()-1));
        return null;
    }

    public Event getRandomActivity(){
        if (UtopiaMain.myGlobalStacker.registeredActivities.size()>0) {
            Integer randmomIndex = UtopiaMain.myGlobalStacker.generateRandomInteger(0, UtopiaMain.myGlobalStacker.registeredActivities.size() - 1);
            return UtopiaMain.myGlobalStacker.registeredActivities.get(UtopiaMain.myGlobalStacker.generateRandomInteger(0, UtopiaMain.myGlobalStacker.registeredActivities.size()-1));
        }

        return null; // if the size of ArrayList activities is zero
    }

    public Event getMyEvent(String filter){
        Event myReturnEvent = null ;
        for(Event s: UtopiaMain.myGlobalStacker.registeredActivities){
            if (s.getClass().getSimpleName().toLowerCase().startsWith(filter))
                myReturnEvent =  s;
        }
        if (myReturnEvent==null) System.out.println("Error in Controller : public Event getMyEvent(String filter). Filter ist nicht ok.");
        return myReturnEvent;
    }


    // it can be used to tick all Events. It is used in controller tick.
    public void tickAllActivities(){
        for (Event activity: UtopiaMain.myGlobalStacker.registeredActivities) activity.tick();
    }

    // use this method if you want to find out the number of seconds
    public Integer getTickCountSinceStartup() {
        return this.tickCountSinceStartup;
    }


    public Event getBestOfferForCitizen(Citizen citizen){

        // this will be the list of available Categories .... Sleep,Food,Money,Toilet,Health,Fun
        Category[] myCategListe = Category.values();

        // here we gonna store the table : Category.value , CitizenStatus.value, Event[], percent for one citizen.

        class MyTable{
            Category category;
            Integer citizenHasSoMuchFromThisCategory;
            Integer citizenMaximumPossibleFromThisCategory;
            ArrayList<Event> eventList;
            Integer percentUtilityValueForCitizen;

            public MyTable(Category category, Integer citizenHasSoMuchFromThisCategory, Integer citizenMaximumPossibleFromThisCategory, ArrayList<Event> eventList, Integer procentUtilityValueForCitizen) {
                this.category = category;
                this.citizenHasSoMuchFromThisCategory = citizenHasSoMuchFromThisCategory;
                this.citizenMaximumPossibleFromThisCategory = citizenMaximumPossibleFromThisCategory;
                this.eventList = eventList;
                this.percentUtilityValueForCitizen = procentUtilityValueForCitizen;
            }
        }
        // this is the variable where we store the "table"
        ArrayList<MyTable> myChoosenEvents;



        // We make an Hashmap where we store one element from Category ( ex FOOD ... ) and an ArrayList with the corresponding Events that offer this Category
        //myChoosenEvents = new HashMap<Category,ArrayList<Event>>();

        Integer citizenHasSoMuchFromThisCategory;
        Integer citizenMaximumPossibleFromThisCategory;
        // we create now the "table" as described in class MyTable
        for (Category myEnumElement :myCategListe ) { // cycle throw all the available categories ....
            citizenHasSoMuchFromThisCategory = 0;
            citizenMaximumPossibleFromThisCategory = 0;
            switch (myEnumElement){
                case Food:
                    citizenHasSoMuchFromThisCategory = citizen.getCitizenStatus().getNeeds().getHunger();
                    citizenMaximumPossibleFromThisCategory = 100;
                    break;
                case Sleep:
                    citizenHasSoMuchFromThisCategory = citizen.getCitizenStatus().getNeeds().getSleep();
                    citizenMaximumPossibleFromThisCategory = 100;
                    break;
                case Money:
                    citizenHasSoMuchFromThisCategory = citizen.getCitizenStatus().getNeeds().getSleep();
                    citizenMaximumPossibleFromThisCategory = 100; // nicht unbedingt €100 wäre maximum ... aber habe jetzt keine bessere Idee
                    break;
                case Toilet:
                    citizenHasSoMuchFromThisCategory = citizen.getCitizenStatus().getNeeds().getToilet();
                    citizenMaximumPossibleFromThisCategory = 100;
                    break;
                case Health:
                    citizenHasSoMuchFromThisCategory = citizen.getCitizenStatus().getMainStatus().getHealthbar();
                    citizenMaximumPossibleFromThisCategory = 100;
                    break;
                case Fun:
                    citizenHasSoMuchFromThisCategory = citizen.getCitizenStatus().getEmotions().getHappiness();
                    citizenMaximumPossibleFromThisCategory = 100;
                    break;
                default:
                    break;
            }
            // after the switch we filled the values for:  citizenHasSoMuchFromThisCategory citizenMaximumPossibleFromThisCategory






            

        }

//        for ( Map.Entry<Category, ArrayList<Event>> myEvents:myChoosenEvents.entrySet()) {
//            System.out.println("KEY:"+myEvents.getKey());
//            System.out.println("Values:"+myEvents.getValue().toString());
//        }








        class MyClass {
            Category category;
            Double citizenhas;
            Double citizenHasMax;
            Double citizenneeds;
        }

        MyClass myclass = new MyClass();


        return  null;
    }


    public ArrayList<Event> getEventArrayList(){ // will be used in getEventsListForCategory() - next method ...
        return (ArrayList<Event>) UtopiaMain.myGlobalStacker.registeredActivities.clone();
    }


    // you send the category and the method returns a list of Events that offers that category.
    public ArrayList<Event> getEventsListForCategory(Category category){
        ArrayList<Event> returnArrayList = new ArrayList<Event>();
            for (Event currentEvent : UtopiaMain.myController.getEventArrayList())
                for (Category currentEventCategories : currentEvent.getCategory())
                    if (currentEventCategories == category) {
                        returnArrayList.add(currentEvent);
                        System.out.println("Running getEventsListForCategory(Category category) . Found event : " + currentEvent.getClass().getCanonicalName() + " that will ofer you " + currentEventCategories);
                    }
        return returnArrayList;
    } // end getEventsListForCategory()


} //end class Controller
