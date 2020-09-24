package Facilities;

import Citizen.Citizen;
import UtopiaCore.Category;
import UtopiaCore.Event;

import java.util.Random;

public class Lottery implements Event {

    public void happens(Citizen citizen) {
        int chance = 75; // 0 = 0% Win || 100 = 100% Win \\
        int multiplier = 10; // Multiplication = x10 \\
        int costs;  // Random Cousts \\
        int happywin;  // Gain Happyness with costs * 5 \\
        int angry;  // Gain Anger \\

        int money = citizen.getCitizenStatus().getMainStatus().getWallet();
        int happy = citizen.getCitizenStatus().getEmotions().getHappiness();
        int anger = citizen.getCitizenStatus().getEmotions().getAnger();

        citizen.getCitizenStatus().getMainStatus().setEventTime(1);

        Random rnm = new Random();
        costs = rnm.nextInt(10) +1;
        int random = rnm.nextInt(100) +1;

        if (money > 0) {
            while (costs > money) {
                costs = rnm.nextInt(10) +1;
            }
            money = money - costs;
            citizen.getCitizenStatus().getMainStatus().setEvent(costs + " UT$ paid for the Facilities.Lottery! Current win chance: " +chance+ " %");

            // Check if Won or Lost \\
            if (random < chance) {
                money = money + (costs * multiplier);
                citizen.getCitizenStatus().getMainStatus().setEvent(costs * multiplier + " UT$ Won!");
                happywin = costs * 5;
                angry = costs * 2;
                // Add Happiness if Win \\
                if (happy + happywin <= 100) {
                    happy = happy + happywin;
                    citizen.getCitizenStatus().getEmotions().setHappiness(happy);
                } else {
                    happy = 100;
                    citizen.getCitizenStatus().getEmotions().setHappiness(happy);
                }
                // Reduce Anger if Win \\
                if (anger - angry >= 0) {
                    anger = anger - angry;
                    citizen.getCitizenStatus().getEmotions().setAnger(anger);
                } else {
                    anger = 0;
                    citizen.getCitizenStatus().getEmotions().setAnger(anger);
                }
            // Lost \\
            } else {
                citizen.getCitizenStatus().getMainStatus().setEvent("Lost!");
                happywin = costs * 2;
                angry = costs * 5;
                // Reduce Happiness if Lose \\
                if (happy - happywin >= 0) {
                    happy = happy - happywin;
                    citizen.getCitizenStatus().getEmotions().setHappiness(happy);
                } else {
                    happy = 0;
                    citizen.getCitizenStatus().getEmotions().setHappiness(happy);
                }
                // Add Anger if Lose \\
                if (anger + angry <= 100) {
                    anger = anger + angry;
                    citizen.getCitizenStatus().getEmotions().setAnger(anger);
                } else {
                    anger = 100;
                    citizen.getCitizenStatus().getEmotions().setAnger(anger);
                }
            }
            citizen.getCitizenStatus().getMainStatus().setWallet(money);
        } else {
            citizen.getCitizenStatus().getMainStatus().setEvent("Not enough money!");
        }


    }

    public void tick() {

    }

    @Override
    public Category[] getCategory() {
        return new Category[] {Category.Money, Category.Fun};
    }
}
