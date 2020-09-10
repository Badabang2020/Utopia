public class CitizenStatus {
    private final int MINCITIZENSTATUSUP = 0;
    private final int MAXCITIZENSTATUSUP = 100;
    private final int MAXCITIZENSTATUSDOWN = 100;
    private final int MINCITIZENSTATUSDOWN = 0;

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
    }

    public class Needs {
        private int sleep;
        private int hunger;
        private int thirst;
        private int toilet;

        Needs(int sleep, int hunger, int thirst, int toilet){
            this.sleep = sleep;
            this.hunger = hunger;
            this.thirst = thirst;
            this.toilet = toilet;
        }
    }
}
