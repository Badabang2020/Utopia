package Citizen;

import java.util.Random;

public class CitizenStatus {
    Random rdm = new Random();
    private final int MINCITIZENSTATUS = 0;
    private final int MAXCITIZENSTATUS = 100;

    // creating obj of our classes Mainstatus, Emotions and Needs
    MainStatus mainStatus = new MainStatus();
    Emotions emotions = new Emotions();
    Needs needs = new Needs();

    public class MainStatus {
        private int wallet = 0;
        private int healthbar = 100;
        private String event = "free";
        private int eventTime = 0;

        public int getWallet() {
            return wallet;
        }

        public void setWallet(int wallet) {
            this.wallet = wallet;
        }

        public int getHealthbar() {
            return healthbar;
        }

        public void setHealthbar(int healthbar) {
            this.healthbar = checkMinMax(healthbar);
        }

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }

        public int getEventTime() {
            return eventTime;
        }

        public void setEventTime(int eventTime) {
            this.eventTime = eventTime;
        }

        public String toString(){
            return "Wallet: " + getWallet() + " Health: " + getHealthbar();
        }
    }

    //checks if the int is <0 or >100, returns a legal number between 0 and 100 for changing the citizens stat.
    private static int checkMinMax(int stat){
        if(stat>100){
            stat = 100;
        }

        else if(stat<0){
            stat = 0;
        }
        return stat;
    }

    public class Emotions {
        // setting random numbers for Emotions when creating new citizen
        private int happiness = rdm.nextInt(100) + 1;
        private int love = rdm.nextInt(100) + 1;
        private int fear = rdm.nextInt(100) + 1;
        private int sadness = rdm.nextInt(100) + 1;
        private int anger = rdm.nextInt(100) + 1;

        public int getHappiness() {
            return happiness;
        }

        public void setHappiness(int happiness) {
            this.happiness = checkMinMax(happiness);
        }

        public int getLove() {
            return love;
        }

        public void setLove(int love) {
            this.love = checkMinMax(love);
        }

        public int getFear() {
            return fear;
        }

        public void setFear(int fear) {
            this.fear = checkMinMax(fear);
        }

        public int getSadness() {
            return sadness;
        }

        public void setSadness(int sadness) {
            this.sadness = checkMinMax(sadness);
        }

        public int getAnger() {
            return anger;
        }

        public void setAnger(int anger) {
            this.anger = checkMinMax(anger);
        }

        public String toString(){
            return "Happiness: " + getHappiness() + " Love: " + getLove() + " Fear: " + getFear() + " Sadness: " + getSadness() + " Anger: " + getAnger();
        }
    }

    public class Needs {
        // setting random numbers for Needs when creating new citizen
        private int sleep = rdm.nextInt(70) + 31;
        private int thirst = rdm.nextInt(70) + 31;
        private int hunger = rdm.nextInt(70) + 31;
        private int toilet = rdm.nextInt(70) + 31;

        public int getSleep() {
            return sleep;
        }

        public void setSleep(int sleep) {

            this.sleep = checkMinMax(sleep);
        }

        public int getThirst() {
            return thirst;
        }

        public void setThirst(int thirst) {

            this.thirst = checkMinMax(thirst);
        }

        public int getHunger() {
            return hunger;
        }

        public void setHunger(int hunger) {

            this.hunger = checkMinMax(hunger);
        }

        public int getToilet() {
            return toilet;
        }

        public void setToilet(int toilet) {

            this.toilet = checkMinMax(toilet);
        }

        public String toString(){
            return "Sleep: " + getSleep() + " Thirst: " + getThirst() + " Hunger: " + getHunger() + " Toilet: " + getToilet();
        }
    }

    public MainStatus getMainStatus() {
        return mainStatus;
    }

    public void setMainStatus(MainStatus mainStatus) {
        this.mainStatus = mainStatus;
    }

    public Emotions getEmotions() {
        return emotions;
    }

    public void setEmotions(Emotions emotions) {
        this.emotions = emotions;
    }

    public Needs getNeeds() {
        return needs;
    }

    public void setNeeds(Needs needs) {
        this.needs = needs;
    }
}
