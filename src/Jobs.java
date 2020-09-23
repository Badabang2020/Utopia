import java.util.ArrayList;

public class Jobs implements Event {
    ArrayList<JobsCategory> joblist;

    Jobs(){
        joblist = new ArrayList<>();
        joblist.add((new JobsCategory("Zeitungszusteller" ,75,2, "work as a Zeitungszusteller and gain 75 Utopia Dollars.")));
        joblist.add((new JobsCategory("Aushilfskraft",150,4, "work as a Aushilfskraft and gain 150 Utopia Dollars.")));
        joblist.add((new JobsCategory("Vollzeit", 300,8, "work as a Vollzeit and gain 150 Utopia Dollars.")));

    }

    private class JobsCategory{
        public String jobname;
        public int wallet;
        public int duration;
        public String text;

    JobsCategory(String jobname, int wallet, int duration, String text ){
        this.jobname = jobname;
        this.wallet = wallet;
        this.duration = duration;
        this.text = text;
    }
    }

    @Override
    public void happens(Citizen citizen){

    }

    @Override
    public void tick() {

    }


    @Override
    public Category[] getCategory() {
        return new Category[] {Category.Money};
    }

}
