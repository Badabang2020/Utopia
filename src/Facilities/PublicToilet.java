package Facilities;

import Citizen.Citizen;
import UtopiaCore.Category;
import UtopiaCore.Event;

import java.util.Random;

public class PublicToilet implements Event {

    int safe = 0;
    int dirty = 0;
    boolean cleaning = false;
    int cticks = 0;

    @Override
    public void happens(Citizen citizen) {

        int toilet;
        int happy;
        int health;
        int money;

        Random rnm = new Random();

        toilet = citizen.getCitizenStatus().getNeeds().getToilet();
        happy = citizen.getCitizenStatus().getEmotions().getHappiness();
        health = citizen.getCitizenStatus().getMainStatus().getHealthbar();
        money = citizen.getCitizenStatus().getMainStatus().getWallet();

        if (cleaning) {
            citizen.getCitizenStatus().getMainStatus().setEvent("<|PublicToilet|> " + "Toilet is cleaning! SORRY!");
        } else {
            if (money >= 1 && toilet >= 50) {
                if (toilet >= 90) {
                    citizen.getCitizenStatus().getMainStatus().setEventTime(2);
                    toilet = 0;
                    citizen.getCitizenStatus().getMainStatus().setEvent("<│PublicToilet│> " + "Was poop!");
                    dirty += rnm.nextInt(10) +5;;
                } else {
                    citizen.getCitizenStatus().getMainStatus().setEventTime(1);
                    toilet = 0;
                    citizen.getCitizenStatus().getMainStatus().setEvent("<│PublicToilet│> " + "Was pee!");
                    dirty += rnm.nextInt(5) +1;;
                }
;
                if (dirty >= 90) {
                    health = health - 15;
                    happy = happy - 50;
                } else if (dirty >= 75 && dirty < 90) {
                    health = health - 10;
                    happy = happy - 35;
                } else if (dirty >= 50 && dirty < 75) {
                    health = health - 5;
                    happy = happy - 25;
                } else if (dirty >= 40 && dirty < 50) {
                    happy = happy - 20;
                } else if (dirty >= 30 && dirty < 40) {
                    happy = happy - 10;
                } else if (dirty >= 20 && dirty < 30) {
                    happy = happy -5;
                } else if (dirty >=10 && dirty < 20) {
                    happy = happy + 5;
                } else if (dirty < 10) {
                    happy = happy + 15;
                }

                money = money - 2;
                safe = safe + 2;
                citizen.getCitizenStatus().getNeeds().setToilet(toilet);
                citizen.getCitizenStatus().getMainStatus().setWallet(money);
                citizen.getCitizenStatus().getEmotions().setHappiness(happy);
                citizen.getCitizenStatus().getMainStatus().setHealthbar(health);

            } else if (money <= 0){
                citizen.getCitizenStatus().getMainStatus().setEvent("<│PublicToilet│> " + "You need at least 1 UT$");
            }
        }
    }

    @Override
    public void tick() {
        cticks++;
        if (cticks > 4) {
            cleaning = false;
        }
        if (dirty >= 20 && safe >= 50) {
            cleaning = true;

            safe = safe - 50;
            dirty = 0;
            cticks = 0;
        }

    }

    @Override
    public Category[] getCategory() {
        return new Category[] {Category.Toilet};
    }
}
