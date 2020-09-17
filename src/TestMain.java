import java.util.ArrayList;

public class TestMain {

    public static void main(String[] args) {

        //get all Toilet Events

        Event restaurant = new Restaurant();
        Event doctor = new Doctor();
        Event bank = new Bank();
        Event home = new Home();

        Event[] events = new Event[] {restaurant, doctor, bank, home};

        ArrayList<Event> toiletEvents = new ArrayList<>();

        for (Event e : events) {
            Category[] cats = e.getCategory();
            for (Category c : cats) {
                if (c == Category.Money) {
                    toiletEvents.add(e);
                }
            }
        }

        for (Event e : toiletEvents) {
            System.out.println(e);
        }
    }
}
