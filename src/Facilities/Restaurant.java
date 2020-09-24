import java.util.ArrayList;

public class Restaurant implements Event {
    ArrayList<MenuItem> menuItems;

    //Adds the menues to an arraylist, sorted from big to small
    Restaurant() {
        menuItems = new ArrayList<MenuItem>();
        menuItems.add(new MenuItem("Large Menu", 10, 50, 30, 10));
        menuItems.add(new MenuItem("Medium Menu", 5, 30 , 15, 5));
        menuItems.add(new MenuItem("Small Menu", 3, 10, 10, 3));
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
    }

    //check if he is hungry and got money -> gives him the first possible menu
    @Override
    public void happens(Citizen citizen) {
        int citizenHunger = citizen.getCitizenStatus().getNeeds().getHunger();
        int citizenMoney = citizen.getCitizenStatus().getMainStatus().getWallet();
        for (MenuItem m: menuItems) {

            if (100 - citizenHunger > m.hunger && citizenMoney > m.cost) {
                citizen.getCitizenStatus().getMainStatus().setEvent("ate " + m.name + "and gained " + m.hunger + "hunger.");

                citizenHunger = citizenHunger + m.hunger;
                citizenMoney = citizenMoney - m.cost;
                citizen.getCitizenStatus().getNeeds().setHunger(citizenHunger);
                citizen.getCitizenStatus().getMainStatus().setWallet(citizenMoney);
            }
        }
    }

    @Override
    public void tick() {

    }

    @Override
    public Category[] getCategory() {
        Category[] category = new Category[] {Category.Food, Category.Toilet};
        return category;
    }
}
