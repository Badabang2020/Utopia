package UtopiaCore;

import Bank.Bank;
import Citizen.*;
import Health.*;
import Facilities.*;

import Health.*;
import Citizen.*;
import HealthInsurance.*;
import Work.*;

import java.util.Random;

public class Tester {

    static Integer runTheTestOnEveryThisNumberOfTicks = 1; // Change this value to set tick sequence that will run the following method

    Event doctor = new Doctor();
    Event bank = new Bank();
    Event themepark = new Themepark();
    Event lottery = new Lottery();
    Event home = new Home();
    Event ambulance = new Ambulance();
    Event cinema = new Cinema();
    Event hospital = new Hospital();
    Event publictoilet = new PublicToilet();
    Event death = new Death();
    Event graveyard = new Graveyard();


    Random rand = new Random();

    public void runDeveloperTest(){ // this method will run on each tick.
        System.out.println("---------------------  Hello from tester. -----------------------");

        //delete what you want from this method, this code is an example.
        // Referencing the controller like         Main.myController  will not work!

        System.out.println("Utopia time is "+UtopiaMain.myController.getUtopiaTime());

        Random rdm = new Random();

        if (GlobalStacker.registredCitizens.size() == 0 && GlobalStacker.registeredActivities.size() == 0) {
            UtopiaMain.myController.registerActivity(bank);
            UtopiaMain.myController.registerActivity(doctor);
            UtopiaMain.myController.registerActivity(home);
            UtopiaMain.myController.registerActivity(lottery);
            UtopiaMain.myController.registerActivity(ambulance);
            UtopiaMain.myController.registerActivity(cinema);
            UtopiaMain.myController.registerActivity(hospital);
            UtopiaMain.myController.registerActivity(publictoilet);
            UtopiaMain.myController.registerActivity(graveyard);
            UtopiaMain.myController.registerActivity(death);

            for (int i = 0; i < 20; i++) {
                UtopiaMain.myController.registerCitizen(new Citizen("" + i, ""+i+"!", "" + rdm.nextInt(1000000000), 'm', rdm.nextInt(100), new Address(), new GKK(), false, new CitizenStatus()));
            }        GlobalStacker.registredCitizens.get(0).getCitizenStatus().getMainStatus().setHealthbar(-5);
            GlobalStacker.registredCitizens.get(1).getCitizenStatus().getMainStatus().setHealthbar(-5);
        }

        for (int i = 0 ; i < GlobalStacker.registredCitizens.size(); i++) {
            Citizen citizen = GlobalStacker.registredCitizens.get(i);
            if(citizen.getCitizenStatus().getMainStatus().getEventTime()==0){
                citizen.doEvent(bank);
                citizen.doEvent(publictoilet);
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
