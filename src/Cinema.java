import java.util.ArrayList;

public class Cinema implements Event {
    ArrayList<Movie> movieList;
    ArrayList<Snack> snackList;

    Cinema() {
        movieList = new ArrayList<>();
        movieList.add((new Movie("Star Wars", 10, 20, 0, 0,0, 0, 2))); // gives happiness
        movieList.add((new Movie("Titanic", 10, 0, 20, 0,-20, 0, 2))); // gives love
        movieList.add(new Movie("Winni Pooh",10,5,0,20,0,10,2));       // gives fear
        movieList.add(new Movie("Forest Gump",10,15,0,0,20,0,2));      // gives sadness
        movieList.add(new Movie("Rambo",10,10,0,0,0,20,2));            // gives anger

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
        public int duration; // in hours

        Movie(String name, int cost, int happiness, int love, int fear, int sadness, int anger, int duration) {
            this.name = name;
            this.cost = cost;
            this.happiness = happiness;
            this.love = love;
            this.fear = fear;
            this.sadness = sadness;
            this.anger = anger;
            this.duration = duration;
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
    public String generateEventText(Movie movie, Citizen citizen) {
        ArrayList<String> gains = new ArrayList<>();     // when emotion is positive
        ArrayList<String> decreases = new ArrayList<>(); // when emotion is negative

        if (movie.happiness > 0) gains.add(movie.happiness + " happiness"); else if (movie.happiness < 0) decreases.add(movie.happiness + " happiness");
        if (movie.love > 0) gains.add(movie.love + " love"); else if (movie.love < 0) decreases.add(movie.love + " love");
        if (movie.fear > 0) gains.add(movie.fear + " fear"); else if (movie.fear < 0) decreases.add(movie.fear + " fear");
        if (movie.sadness > 0) gains.add(movie.sadness + " sadness"); else if (movie.sadness < 0) decreases.add(movie.sadness + " sadness");
        if (movie.anger > 0) gains.add(movie.anger + " anger"); else if (movie.anger < 0) decreases.add(movie.anger + " anger");

        String text = citizen.getFirstName() + " watched " + movie.name;

        // adds the gains to the text
        for (int i = 0; i < gains.size(); i++) {
            if (i != 0) {
                text += ", " + gains.get(i);
            }
            // adds gains after watched movie and the text only by the first emotion
            else {
                text += " and gains " + gains.get(i);
            }
        }

        // adds the decreases to the text
        for (int i = 0; i < decreases.size(); i++) {
            if (i != 0) {
                text += ", " + decreases.get(i);
            }
            // adds decreases after watched movie/gains and the text only by the first emotion
            else {
                text +=" but decreases " + decreases.get(i);
            }
        }

        return text + ".";
    }


    @Override
    public void happens(Citizen citizen) {
        Movie bestMovie = getBestMovie(citizen);
        if (bestMovie != null) {
            changeStatusOfCitizen(citizen, bestMovie);
        }
    }

    private Movie getBestMovie(Citizen citizen) {
        int[] emotionsCitizen = new int[5]; // [happiness, love, fear, sadness, anger]
        int money = citizen.getCitizenStatus().getMainStatus().getWallet();             // money
        emotionsCitizen[0] = citizen.getCitizenStatus().getEmotions().getHappiness();   // happiness
        emotionsCitizen[1] = citizen.getCitizenStatus().getEmotions().getLove();        // love
        emotionsCitizen[2] = citizen.getCitizenStatus().getEmotions().getFear();        // fear
        emotionsCitizen[3] = citizen.getCitizenStatus().getEmotions().getSadness();     // sadness
        emotionsCitizen[4] = citizen.getCitizenStatus().getEmotions().getAnger();       // anger

        // find the lowest emotion value = highest priority
        int index = 0, lowestValue = 101;
        for (int i = 0; i < emotionsCitizen.length; i++) {
            // found lowest number and what emotion
            if (lowestValue > emotionsCitizen[i]) {
                lowestValue = emotionsCitizen[i];
                index = i;
            }
        }

        // clone movieList
        ArrayList<Movie> clonedMovieList = new ArrayList<>();
//        for (Movie movie : movieList) { clonedMovieList.add(movie); }
        clonedMovieList.addAll(movieList);

        // sort movies descending from the emotion what has priority
        ArrayList<Movie> sortedMovies = new ArrayList<>();
        int[] emotionsMovie = new int[5]; // [happiness, love, fear, sadness, anger]
        // iterate over clonedMovieList until there is no more movies in it
        while (clonedMovieList.size() > 0) {
            int indexMovie = 0;
            int highestNumber = -1;
            // find highest number
            for (int i = 0; i < clonedMovieList.size(); i++) {
                // initialise movie
                Movie movie = clonedMovieList.get(i);
                // sets emotion
                emotionsMovie[0] = movie.happiness;
                emotionsMovie[1] = movie.love;
                emotionsMovie[2] = movie.fear;
                emotionsMovie[3] = movie.sadness;
                emotionsMovie[4] = movie.anger;

                // found highest emotion number and there index
                if (highestNumber < emotionsMovie[index]) {
                    highestNumber = emotionsMovie[index];
                    indexMovie = i;
                }
            }

            // puts the movie into sortedMovies and deletes it from cloneMovieList
            sortedMovies.add(clonedMovieList.get(indexMovie));
            clonedMovieList.remove(indexMovie);
        }

        // find the best movie for the specific emotion value
        for (int i = 0; i < sortedMovies.size(); i++) {
            // initialise movie
            Movie movie = sortedMovies.get(i);
            // sets emotion
            emotionsMovie[0] = movie.happiness;
            emotionsMovie[1] = movie.love;
            emotionsMovie[2] = movie.fear;
            emotionsMovie[3] = movie.sadness;
            emotionsMovie[4] = movie.anger;

            // check if the values don't go over 100
            if (emotionsMovie[index] + emotionsCitizen[index] <= 100 && money >= movie.cost) {
                // check if no other values go to 0 or lower
                if (emotionsCitizen[0] + movie.happiness > 0 && emotionsCitizen[0] + movie.happiness <= 100 &&
                        emotionsCitizen[1] + movie.love > 0 && emotionsCitizen[0] + movie.love <= 100 &&
                        emotionsCitizen[2] + movie.fear > 0 && emotionsCitizen[0] + movie.fear <= 100 &&
                        emotionsCitizen[3] + movie.sadness > 0 && emotionsCitizen[0] + movie.sadness <= 100 &&
                        emotionsCitizen[4] + movie.anger > 0 && emotionsCitizen[0] + movie.anger <= 100) {
                    return movie;
                }
            }
        }

    return null;
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
        text = generateEventText(movie, citizen) + pickSnack(citizen);

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

                // he or she
                String gender = (citizen.getGender() == 'm') ? "he" : "she";

                return " In addition " + gender + " ate " + snack.name + " thus gaining " + snack.hunger + " hunger.";
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
