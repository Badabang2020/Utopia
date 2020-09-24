import java.util.ArrayList;
import java.util.Random;

public class Fightclub implements Event{
    ArrayList fighters = new ArrayList<Citizen>();
    Random rand = new Random();
    @Override
    //citizen goes into an ArrayList of Citizens who want to fight
    public void happens(Citizen citizen) {
        fighters.add(citizen);
        citizen.getCitizenStatus().getMainStatus().setEventTime(-1);
        citizen.getCitizenStatus().getMainStatus().setEvent("at Fightclub");
    }

    @Override
    //every tick there is one fight between 2 citizens from the ArrayList "fighters"
    public void tick() {
        if(fighters.size()>=2){
            Citizen fighter1 = (Citizen)fighters.get(0);
            int health1 = fighter1.getCitizenStatus().getMainStatus().getHealthbar();
            int anger1 = fighter1.getCitizenStatus().getEmotions().getAnger();
            fighter1.getCitizenStatus().getMainStatus().setEvent("in a fight");
            Citizen fighter2 = (Citizen)fighters.get(1);
            int health2 = fighter2.getCitizenStatus().getMainStatus().getHealthbar();
            int anger2 = fighter2.getCitizenStatus().getEmotions().getAnger();
            fighter2.getCitizenStatus().getMainStatus().setEvent("in a fight");

            //fight goes on as long as both fighters have more than 15 health;
            while(health1 > 15 && health2 > 15){
                if (rand.nextInt(2)==1){
                    health1--;
                }
                else{
                    health2--;
                }
            }
            anger1-=50;
            anger2-=50;

            if(anger1 < 0){
                anger1 = 0;
            }
            if(anger2 < 0){
                anger2 = 0;
            }
            if (health1==health2){
                fighter1.getCitizenStatus().getMainStatus().setEvent("Draw in Fightclub");
                fighter2.getCitizenStatus().getMainStatus().setEvent("Draw in Fightclub");
            }
            if(health1>health2){
                fighter1.getCitizenStatus().getMainStatus().setEvent("won a Fight.");
                fighter2.getCitizenStatus().getMainStatus().setEvent("lost a Fight.");
            }
            else{
                fighter2.getCitizenStatus().getMainStatus().setEvent("won a Fight.");
                fighter1.getCitizenStatus().getMainStatus().setEvent("lost a Fight.");
            }
            fighter1.getCitizenStatus().getMainStatus().setHealthbar(health1);
            fighter2.getCitizenStatus().getMainStatus().setHealthbar(health2);
            fighter1.getCitizenStatus().getMainStatus().setEventTime(1);
            fighter2.getCitizenStatus().getMainStatus().setEventTime(1);
            fighter1.getCitizenStatus().getEmotions().setAnger(anger1);
            fighter2.getCitizenStatus().getEmotions().setAnger(anger2);
            fighters.remove(0);//removes fighter1 from fighters
            fighters.remove(0);//removes fighter2 from fighters

        }
    }

    @Override
    public Category[] getCategory() {
        return new Category[] {Category.Fun};
    }
}
