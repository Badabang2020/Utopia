public class CitizenStatus {
    private final int MINCITIZENSTATUSUP = 0;
    private final int MAXCITIZENSTATUSUP = 100;
    private final int MAXCITIZENSTATUSDOWN = 100;
    private final int MINCITIZENSTATUSDOWN = 0;
    MainStatus mainStatus;
    Emotions emotions;
    Needs needs;

    public class MainStatus {
        private int wallet = 0;
        private int healthbar = 100;
        private String event = "free";
        private int eventTime = 0;

//        MainStatus (int wallet, int healthbar){
//            this.wallet = wallet;
//            this.healthbar = healthbar;
//            this.event = "free";
//            this.eventTime = 0;
//        }

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
            this.healthbar = healthbar;
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
            return getWallet() + " " + getHealthbar() + " " + getEvent() + " " + getEventTime();
        }
    }

    public class Emotions {
        private int happiness;
        private int love;
        private int fear;
        private int sadness;
        private int anger;

        Emotions (int happiness, int love, int fear, int sadness, int anger) {
            this.happiness = happiness;
            this.love = love;
            this.fear = fear;
            this.sadness = sadness;
            this.anger = anger;
        }

        public int getHappiness() {
            return happiness;
        }

        public void setHappiness(int happiness) {
            this.happiness = happiness;
        }

        public int getLove() {
            return love;
        }

        public void setLove(int love) {
            this.love = love;
        }

        public int getFear() {
            return fear;
        }

        public void setFear(int fear) {
            this.fear = fear;
        }

        public int getSadness() {
            return sadness;
        }

        public void setSadness(int sadness) {
            this.sadness = sadness;
        }

        public int getAnger() {
            return anger;
        }

        public void setAnger(int anger) {
            this.anger = anger;
        }

        public String toString(){
            return getHappiness() + " " + getLove() + " " + getFear() + " " + getSadness() + " " + getAnger();
        }
    }

    public class Needs {
        private int sleep;
        private int thirst;
        private int hunger;
        private int toilet;

        Needs(int sleep, int thirst, int hunger, int toilet){
            this.sleep = sleep;
            this.thirst = thirst;
            this.hunger = hunger;
            this.toilet = toilet;
        }

        public int getSleep() {
            return sleep;
        }

        public void setSleep(int sleep) {
            this.sleep = sleep;
        }

        public int getThirst() {
            return thirst;
        }

        public void setThirst(int thirst) {
            this.thirst = thirst;
        }

        public int getHunger() {
            return hunger;
        }

        public void setHunger(int hunger) {
            this.hunger = hunger;
        }

        public int getToilet() {
            return toilet;
        }

        public void setToilet(int toilet) {
            this.toilet = toilet;
        }

        public String toString(){
            return getSleep() + " " + getThirst() + " " + getHunger() + " " + getToilet();
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
