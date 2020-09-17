import java.util.ArrayList;

public class Cinema implements Event {
    ArrayList<Movie> movieList;
    ArrayList<Snack> snackList;

    Cinema() {
        movieList = new ArrayList<>();
        movieList.add((new Movie("Titanic", 10, 0, 30, 0,-30, 0)));
        movieList.add((new Movie("Star Wars", 10, 20, 0, 0,0, 0)));

        snackList = new ArrayList<>();
        snackList.add(new Snack("Large Popcorn", 15, 40, 30, 10));
        snackList.add(new Snack("Medium Popcorn", 10, 20, 15, 6));
        snackList.add(new Snack("Small Popcorn", 5, 7, 10, 3));
    }

    private class Movie {
        public String name;
        public int cost;
        public int happiness;
        public int love;
        public int fear;
        public int sadness;
        public int anger;

        Movie(String name, int cost, int happiness, int love, int fear, int sadness, int anger) {
            this.name = name;
            this.cost = cost;
            this.happiness = happiness;
            this.love = love;
            this.fear = fear;
            this.sadness = sadness;
            this.anger = anger;
        }
    }

    private class Snack {
        public String name;
        public int health;
        public int hunger;
        public int cost;
        public int toilet;
        Snack (String name, int health, int hunger, int cost, int toilet) {
            this.name = name;
            this.cost = cost;
            this.health = health;
            this.toilet = toilet;
            this.hunger = hunger;
        }
    }




    @Override
    public void happens(Citizen citizen) {
        int citizenHunger = citizen.getCitizenStatus().getNeeds().getHunger();
        int citizenMoney = citizen.getCitizenStatus().getMainStatus().getWallet();
        int citizenHappiness = citizen.getCitizenStatus().getEmotions().getHappiness();
        int citizenLove = citizen.getCitizenStatus().getEmotions().getLove();
        int citizenFear = citizen.getCitizenStatus().getEmotions().getFear();
        int citizenSadness = citizen.getCitizenStatus().getEmotions().getSadness();
        int citizenAnger = citizen.getCitizenStatus().getEmotions().getAnger();
        String text = "";

        boolean ticket = false;
        // picks Moive
        for (Movie movie : movieList) {
            if (citizenMoney > movie.cost) {
                // picks Star Wars
                if (citizenHappiness <= 100 - movie.happiness && movie.happiness != 0) {
                    // generate text
                    text = "watched " + movie.name + " and gained " + movie.happiness + " happiness.";
                    String text2 = pickSnack(citizen);

                    // set event text
                    citizen.getCitizenStatus().getMainStatus().setEvent(text + text2);

                    // change status of citizen
                    citizen.getCitizenStatus().getMainStatus().setWallet(citizenMoney - movie.cost);
                    citizen.getCitizenStatus().getEmotions().setHappiness(citizenHappiness + movie.happiness);
                    break;
                }
                // picks Titanic
                else if (citizenLove < 100 - movie.love && citizenSadness > 40 && movie.love != 0) {
                    // generate text
                    text = "watched " + movie.name + " and gained " + movie.love + " love, but sadness was decreased by " + movie.sadness + ".";
                    String text2 = pickSnack(citizen);

                    // set event text
                    citizen.getCitizenStatus().getMainStatus().setEvent(text + text2);

                    // change status of citizen
                    citizen.getCitizenStatus().getMainStatus().setWallet(citizenMoney - movie.cost);
                    citizen.getCitizenStatus().getEmotions().setLove(citizenLove + movie.love);
                    citizen.getCitizenStatus().getEmotions().setSadness(citizenSadness + movie.sadness);
                    break;
                }
            }
        }
    }

    private String pickSnack(Citizen citizen) {
        int citizenMoney = citizen.getCitizenStatus().getMainStatus().getWallet(); // update money
        int citizenHunger = citizen.getCitizenStatus().getNeeds().getHunger();

        // he buys Snack
        for (Snack snack : snackList) {
            if (100 - citizenHunger >= snack.hunger && citizenMoney >= snack.cost) {
                citizen.getCitizenStatus().getMainStatus().setHealthbar(citizen.getCitizenStatus().getMainStatus().getHealthbar() + snack.health);
                citizen.getCitizenStatus().getNeeds().setHunger(citizenHunger + snack.hunger);
                citizen.getCitizenStatus().getMainStatus().setWallet(citizenMoney - snack.cost);
                citizen.getCitizenStatus().getNeeds().setToilet(citizen.getCitizenStatus().getNeeds().getToilet() - snack.toilet);

                return " And ate " + snack.name + " thus gaining " + snack.hunger + " hunger.";
            }
        }

        return "";
    }

    @Override
    public void tick() {

    }

    @Override
    public Category[] getCategory() {
        Category[] category = new Category[] {Category.Fun};
        return category;
    }
}
