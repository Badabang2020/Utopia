import java.util.ArrayList;
import java.util.Random;

public class Themepark implements Event {
    private ArrayList<Citizen> guests = new ArrayList<Citizen>();
    private Rollercoaster rollercoaster = new Rollercoaster();
    private Icecream icecream = new Icecream();
    private String msg = "|at themepark| ";
    private int ticksSinceLastCrash = 100;
    private Category[] category = {Category.Fun};

    @Override
    public void happens(Citizen citizen) {
        citizen.getCitizenStatus().getMainStatus().setEventTime(-1);
        citizen.getCitizenStatus().getMainStatus().setEvent("goes to Themepark");
        guests.add(citizen);
    }

    @Override
    public void tick() {
        rollercoaster.tick();
        Citizen current;
        for (int i = 0; i < guests.size(); ) {
            current = guests.get(i);
            if (current.getCitizenStatus().getMainStatus().getWallet() <= 10 || current.getCitizenStatus().getMainStatus().getEventTime() < -30) {
                current.getCitizenStatus().getMainStatus().setEventTime(1);
                current.getCitizenStatus().getMainStatus().setEvent("leaves the Themepark");
                guests.remove(i);

            } else if (current.getCitizenStatus().getEmotions().getFear() > 80) {
                current.doEvent(icecream);
                i++;
            } else {
                current.doEvent(rollercoaster);
                guests.remove(i);
            }
        }
    }

    @Override
    public Category[] getCategory() {
        return category;
    }

    private class Rollercoaster implements Event {
        ArrayList queue = new ArrayList<Citizen>();
        ArrayList onRide = new ArrayList<Citizen>();
        ArrayList scared = new ArrayList<Citizen>();

        @Override
        public Category[] getCategory() {
            return null;
        }

        @Override
        public void happens(Citizen citizen) {
            queue.add(citizen);
            citizen.getCitizenStatus().getMainStatus().setEvent(msg + "goes into queue for Rollercoaster");
        }

        @Override
        public void tick() {
            Random rand = new Random();
            if (ticksSinceLastCrash > 700) {
                category = new Category[]{Category.Fun};
            }
            if (rand.nextInt(1000) == 666) {
                category = new Category[]{};
                while (onRide.size() > 0) {
                    guests.add((Citizen) onRide.get(0));
                    ((Citizen) onRide.get(0)).getCitizenStatus().getMainStatus().setHealthbar(((Citizen) onRide.get(0)).getCitizenStatus().getMainStatus().getHealthbar() - 60);
                    onRide.remove(0);
                }
                while (scared.size() > 0) {
                    guests.add((Citizen) scared.get(0));
                    scared.remove(0);
                }

                while (queue.size() > 0) {
                    guests.add((Citizen) queue.get(0));
                    queue.remove(0);
                }
                while (guests.size() > 0) {
                    Citizen guest = (Citizen) guests.get(0);
                    guest.getCitizenStatus().getMainStatus().setEvent(msg + "leaves the Themepark in Terror!");
                    guest.getCitizenStatus().getEmotions().setFear(95);
                    guest.getCitizenStatus().getMainStatus().setEventTime(1);
                    guests.remove(0);
                }
            } else {

            }
            for (int i = 0; i < queue.size(); i++) {
                Citizen guest = (Citizen) queue.get(i);
                guest.getCitizenStatus().getMainStatus().setEvent(msg + "waits for Rollercoaster");
                guest.getCitizenStatus().getEmotions().setFear(guest.getCitizenStatus().emotions.getFear() + 1);

            }

            while (onRide.size() > 0) {
                guests.add((Citizen) onRide.get(0));
                onRide.remove(0);
            }
            while (scared.size() > 0) {
                guests.add((Citizen) scared.get(0));
                scared.remove(0);
            }

            while (onRide.size() != 5 && queue.size() > 0) {


                Citizen guest = (Citizen) queue.get(0);
                queue.remove(0);

                if (guest.getCitizenStatus().getEmotions().getFear() < 90) {

                    guest.getCitizenStatus().getMainStatus().setWallet(guest.getCitizenStatus().getMainStatus().getWallet() - 10);//guest.money -= 10;
                    guest.getCitizenStatus().getEmotions().setFear(guest.getCitizenStatus().getEmotions().getFear() + 10);
                    guest.getCitizenStatus().getEmotions().setHappiness(guest.getCitizenStatus().getEmotions().getHappiness() + 10);
                    if (guest.getCitizenStatus().getEmotions().getHappiness() > 100) {
                        guest.getCitizenStatus().getEmotions().setHappiness(100);
                    }
                    guest.getCitizenStatus().getMainStatus().setEvent(msg + "takes a Ride in Rollercoaster");
                    onRide.add(guest);
                } else {

                    guest.getCitizenStatus().getMainStatus().setEvent(msg + "leaves the queue in fear");
                    if (guest.getCitizenStatus().getEmotions().getFear() < 100) {
                        guest.getCitizenStatus().getEmotions().setFear(100);
                    }
                    scared.add(guest);
                }


            }
        }
    }

    private class Icecream implements Event {
        @Override
        public Category[] getCategory() {
            return null;
        }

        ;

        @Override
        public void happens(Citizen citizen) {
            citizen.getCitizenStatus().getMainStatus().setEvent(msg + "Eats Icecream");
            citizen.getCitizenStatus().emotions.setFear(citizen.getCitizenStatus().emotions.getFear() - 10);
            citizen.getCitizenStatus().mainStatus.setWallet(citizen.getCitizenStatus().getMainStatus().getWallet() - 2);
        }

        @Override
        public void tick() {
        }
    }
}
