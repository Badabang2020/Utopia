package UtopiaCore;

import Bank.Bank;
import Facilities.*;
import Facilities.deathAndFuneral.Death;
import Facilities.deathAndFuneral.Graveyard;
import Health.*;
import Citizen.*;
import HealthInsurance.*;

import java.util.Random;

public class Tester {

    static Integer runTheTestOnEveryThisNumberOfTicks = 1; // Change this value to set tick sequence that will run the following method
    Event restaurant = new Restaurant();
    Event doctor = new Doctor();
    Event bank = new Bank();
    Event themepark = new Themepark();
    Event lottery = new Lottery();
    Event home = new Home();
    Event ambulance = new Ambulance();
    Event cinema = new Cinema();
    Event hospital = new Hospital();
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
            UtopiaMain.myController.registerActivity(themepark);
            UtopiaMain.myController.registerActivity(restaurant);
            UtopiaMain.myController.registerActivity(doctor);
            UtopiaMain.myController.registerActivity(home);
            UtopiaMain.myController.registerActivity(lottery);
            UtopiaMain.myController.registerActivity(ambulance);
            UtopiaMain.myController.registerActivity(cinema);
            UtopiaMain.myController.registerActivity(hospital);
            UtopiaMain.myController.registerActivity(graveyard);
            UtopiaMain.myController.registerActivity(death);

            for (int i = 0; i < 5; i++) {
                UtopiaMain.myController.registerCitizen(new Citizen("" + i, ""+i+"!", "" + rdm.nextInt(1000000000), 'm', rdm.nextInt(100), new Address(), new GKK(), false, new CitizenStatus()));
            }        GlobalStacker.registredCitizens.get(0).getCitizenStatus().getMainStatus().setHealthbar(-5);
            GlobalStacker.registredCitizens.get(1).getCitizenStatus().getMainStatus().setHealthbar(-5);

        }



        for (int i = 0 ; i < GlobalStacker.registredCitizens.size(); i++) {
            Citizen citizen = GlobalStacker.registredCitizens.get(i);
            if(citizen.getCitizenStatus().getMainStatus().getEventTime()==0){
                if (citizen.getCitizenStatus().getMainStatus().getHealthbar()<0){
                    citizen.doEvent(death);
                }else{
                    citizen.doEvent(graveyard);
                }

            }
            else{
                citizen.getCitizenStatus().getMainStatus().setEventTime(citizen.getCitizenStatus().getMainStatus().getEventTime()-1);
            }
            System.out.println(citizen);
            System.out.println("");

        }
        for (int i = 0; i < GlobalStacker.registeredActivities.size(); i++) {
            GlobalStacker.registeredActivities.get(i).tick();
        }


        System.out.println("------------------------------------------------------------------");
    }
}
