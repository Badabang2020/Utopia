import java.util.ArrayList;

public class Restaurant extends Facilities implements Event {
    ArrayList<MenuItem> menuItems;

    Restaurant() {
        menuItems = new ArrayList<MenuItem>();
        menuItems.add(new MenuItem("Small Menu", 3, -3, -10, 3));
        menuItems.add(new MenuItem("Large Beer", 15, -4, -20, 10));
        menuItems.add(new MenuItem("Medium Menu", 5, -5, -15, 5));
        menuItems.add(new MenuItem("Super Large Menu", 10, -10, -30, 10));
    }

    private class MenuItem {
        public String name;
        public int health;
        public int hunger;
        public int cost;
        public int toilet;
        MenuItem(String name, int health, int hunger, int cost, int toilet) {
            this.name = name;
            this.cost = cost;
            this.health = health;
            this.toilet = toilet;
            this.hunger = hunger;
        }
        Double getRate(Citizen citizen, ArrayList<String> attributes) {
            int citizenHunger = citizen.getCitizenStatus().getNeeds().getHunger();
            int tmpHunger = citizenHunger - hunger;
            return 10.0 - tmpHunger; // 10 = best rating
        }

    }
    // 3 Menüs: klein mittel groß
    public void happens(Citizen citizen) {

    }

    @Override
    void getBestService(Citizen citizen, ArrayList<String> attributes ) {
        MenuItem bestItem = null;
        Double rating = -100.0;
        for (MenuItem m: menuItems) {
            if (m.getRate(citizen,attributes) > rating) {
                rating = m.getRate(citizen,attributes);
                bestItem = m;
            }
        }
        return citizen.doEvent();
    }
}
