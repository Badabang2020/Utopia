import java.util.ArrayList;

public class Jobs implements Event {
    ArrayList<JobsCategory> joblist;

    Jobs(){
        joblist = new ArrayList<>();
        joblist.add((new JobsCategory("Zeitungszusteller" ,75,2, -5,-5,"work as a Zeitungszusteller and gain 75 Utopia Dollars.")));
        joblist.add((new JobsCategory("Aushilfskraft",150,4, -10,-10,"work as a Aushilfskraft and gain 150 Utopia Dollars.")));
        joblist.add((new JobsCategory("Vollzeit", 300,8, -20,-20,"work as a Vollzeit and gain 150 Utopia Dollars.")));

    }

    private class JobsCategory{
        public String jobname;
        public int wallet;
        public int duration;
        int happyness;
        int sleep;
        public String text;

    JobsCategory(String jobname, int wallet, int duration, int happyness, int sleep, String text ){
        this.jobname = jobname;
        this.wallet = wallet;
        this.duration = duration;
        this.happyness= happyness;
        this.sleep = sleep;
        this.text = text;
    }
    }
    

    @Override
    public void happens(Citizen citizen){
        int citizenHappyness = citizen.getCitizenStatus().getEmotions().getHappiness();
        int citizenSpleep = citizen.getCitizenStatus().getNeeds().getSleep();
        int citizenWallet = citizen.getCitizenStatus().getMainStatus().getWallet();

        citizen.getCitizenStatus().getEmotions().setHappiness(citizenHappyness);
        citizen.getCitizenStatus().getNeeds().setSleep(citizenSpleep);
        citizen.getCitizenStatus().getMainStatus().setWallet(citizenWallet);

    }

    @Override
    public void tick() {

    }


    @Override
    public Category[] getCategory() {
        return new Category[] {Category.Money};
    }

}
