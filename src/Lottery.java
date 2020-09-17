import java.util.Random;

public class Lottery implements Event{

    public void happens(Citizen citizen) {
        int chance = 75; // 0 = 0% Win || 100 = 100% Win \\
        int multiplier = 10; // Multiplication = x10 \\
        int costs;

        int money = citizen.getCitizenStatus().getMainStatus().getWallet();

        Random rnm = new Random();
        costs = rnm.nextInt(10) +1;

        Random rn = new Random();
        int random = rn.nextInt(100) +1;

        if (money > 0) {
            while (costs > money) {
                costs = rnm.nextInt(10) +1;
            }
            money = money - costs;
            citizen.getCitizenStatus().getMainStatus().setEvent(costs + " UT$ paid for the Lottery! Current win chance: " +chance+ " %");

            if (random > chance) {
                money = money + (costs * multiplier);
                citizen.getCitizenStatus().getMainStatus().setEvent(costs * multiplier + " UT$ Won!");
            } else {
                citizen.getCitizenStatus().getMainStatus().setEvent("Lost!!");
            }
            citizen.getCitizenStatus().getMainStatus().setWallet(money);
        } else {
            citizen.getCitizenStatus().getMainStatus().setEvent("Not enough money!");
        }


    }

    public void tick() {

    }

    @Override
    public Category[] getCategory() {
        return new Category[] {Category.Money, Category.Fun};
    }
}
