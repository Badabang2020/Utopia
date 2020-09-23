import java.util.ArrayList;

public class Cinema implements Event {
    ArrayList<Movie> movieList;
    ArrayList<Snack> snackList;

    // To-Do -> generate Text automatically
    Cinema() {
        movieList = new ArrayList<>();
        movieList.add((new Movie("Titanic", 10, 0, 30, 0,-30, 0, 2, "watched Titanic and gained 30 love, but sadness was decreased by 30.")));
        movieList.add((new Movie("Star Wars", 10, 20, 0, 0,0, 0, 2, "watched Star Wars and gained 20 happiness.")));

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
        public int duration; // in hour
        public String eventText;

        Movie(String name, int cost, int happiness, int love, int fear, int sadness, int anger, int duration, String eventText) {
            this.name = name;
            this.cost = cost;
            this.happiness = happiness;
            this.love = love;
            this.fear = fear;
            this.sadness = sadness;
            this.anger = anger;
            this.duration = duration;
            this.eventText = eventText;
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

    // "watched Titanic and gained 30 love, but sadness was decreased by 30." | help Text
    public String generateEventText(Movie movie) {
        ArrayList<String> gains = new ArrayList<>();     // when emotion is positive
        ArrayList<String> decreases = new ArrayList<>(); // when emotion is negative

        if ((movie.happiness > 0)) gains.add(Integer.toString(movie.happiness)); else if (movie.happiness < 0) decreases.add(Integer.toString(movie.happiness));
        if ((movie.love > 0)) gains.add(Integer.toString(movie.love)); else if (movie.love < 0) decreases.add(Integer.toString(movie.love));
        if ((movie.fear > 0)) gains.add(Integer.toString(movie.fear)); else if (movie.fear < 0) decreases.add(Integer.toString(movie.fear));
        if ((movie.sadness > 0)) gains.add(Integer.toString(movie.sadness)); else if (movie.sadness < 0) decreases.add(Integer.toString(movie.sadness));
        if ((movie.anger > 0)) gains.add(Integer.toString(movie.anger)); else if (movie.anger < 0) decreases.add(Integer.toString(movie.anger));

        String text = "watched" + movie.name;

        // use for loop


        return "";
    }


    @Override
    public void happens(Citizen citizen) {
        // initialize variables of citizen status
        int citizenHunger = citizen.getCitizenStatus().getNeeds().getHunger();
        int citizenMoney = citizen.getCitizenStatus().getMainStatus().getWallet();
        int citizenHappiness = citizen.getCitizenStatus().getEmotions().getHappiness();
        int citizenLove = citizen.getCitizenStatus().getEmotions().getLove();
        int citizenFear = citizen.getCitizenStatus().getEmotions().getFear();
        int citizenSadness = citizen.getCitizenStatus().getEmotions().getSadness();
        int citizenAnger = citizen.getCitizenStatus().getEmotions().getAnger();

        // picks Movie | currently bias towards Star Wars
        for (Movie movie : movieList) {
            // picks Star Wars
            if (citizenHappiness <= 100 - movie.happiness && movie.happiness != 0 && citizenMoney > movie.cost) {
                changeStatusOfCitizen(citizen, movie);
                break;
            }
            // picks Titanic
            else if (citizenLove < 100 - movie.love && citizenSadness > 40 && movie.love != 0 && citizenMoney > movie.cost) {
                changeStatusOfCitizen(citizen, movie);
                break;
            }
        }
    }

    private void changeStatusOfCitizen(Citizen citizen, Movie movie) {
        // initialize variables of citizen status
        int citizenMoney = citizen.getCitizenStatus().getMainStatus().getWallet();
        int citizenHappiness = citizen.getCitizenStatus().getEmotions().getHappiness();
        int citizenLove = citizen.getCitizenStatus().getEmotions().getLove();
        int citizenFear = citizen.getCitizenStatus().getEmotions().getFear();
        int citizenSadness = citizen.getCitizenStatus().getEmotions().getSadness();
        int citizenAnger = citizen.getCitizenStatus().getEmotions().getAnger();
        String text = "";

        // buys the ticket
        citizen.getCitizenStatus().getMainStatus().setWallet(citizenMoney - movie.cost);

        // generate text + chooses a snack
        text = movie.eventText + pickSnack(citizen);

        // set event text + duration
        citizen.getCitizenStatus().getMainStatus().setEvent(text);
        citizen.getCitizenStatus().getMainStatus().setEventTime(movie.duration);

        // change status of citizen
        citizen.getCitizenStatus().getEmotions().setHappiness(citizenHappiness + movie.happiness);
        citizen.getCitizenStatus().getEmotions().setLove(citizenLove + movie.love);
        citizen.getCitizenStatus().getEmotions().setFear(citizenFear + movie.fear);
        citizen.getCitizenStatus().getEmotions().setSadness(citizenSadness + movie.sadness);
        citizen.getCitizenStatus().getEmotions().setAnger(citizenAnger + movie.anger);
    }

    private String pickSnack(Citizen citizen) {
        int citizenMoney = citizen.getCitizenStatus().getMainStatus().getWallet(); // updates money
        int citizenHunger = citizen.getCitizenStatus().getNeeds().getHunger();

        // he buys Snack
        for (Snack snack : snackList) {
            if (100 - citizenHunger >= snack.hunger && citizenMoney >= snack.cost) {
                // change status of citizen
                citizen.getCitizenStatus().getMainStatus().setHealthbar(citizen.getCitizenStatus().getMainStatus().getHealthbar() + snack.health);
                citizen.getCitizenStatus().getNeeds().setHunger(citizenHunger + snack.hunger);
                citizen.getCitizenStatus().getMainStatus().setWallet(citizenMoney - snack.cost);
                citizen.getCitizenStatus().getNeeds().setToilet(citizen.getCitizenStatus().getNeeds().getToilet() - snack.toilet);

                return " And ate " + snack.name + " thus gaining " + snack.hunger + " hunger.";
            }
        }
        // nothing was bought
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
