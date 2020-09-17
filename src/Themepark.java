import java.util.ArrayList;

hallo Matthias
public class Themepark implements Event {
    private ArrayList<Citizen> guests = new ArrayList<Citizen>();
    private Rollercoaster rollercoaster = new Rollercoaster();
    private String msg = "|at themepark| ";

    @Override
    public void happens(Citizen citizen) {
        citizen.getCitizenStatus().getMainStatus().setEventTime(-1);
        citizen.getCitizenStatus().getMainStatus().setEvent("goes to Themepark");
        guests.add(citizen);
    }

    @Override
    public void tick() {
        Citizen current;
        for (int i = 0; i < guests.size(); i = 0) {
            current = guests.get(0);
            if (current.getCitizenStatus().getMainStatus().getWallet() <= 10) {
                current.getCitizenStatus().getMainStatus().setEventTime(0);
                current.getCitizenStatus().getMainStatus().setEvent("leaves the Themepark");
                guests.remove(0);

            } else {
                current.doEvent(rollercoaster);
                guests.remove(0);
            }
        }

        rollercoaster.tick();
    }
    @Override
    public Category[] getCategory(){
        return Category.Fun;
    };

    private class Rollercoaster implements Event {
        ArrayList queue = new ArrayList();
        @Override
        public Category[] getCategory(){
            return Category.Fun;
        };

        @Override
        public void happens(Citizen citizen) {
            queue.add(citizen);
            citizen.getCitizenStatus().getMainStatus().setEventTime(-1);
            citizen.getCitizenStatus().getMainStatus().setEvent(msg + "goes into queue for Rollercoaster");
        }

        @Override
        public void tick() {
            for (int i = 0; i < queue.size(); i++) {
                Citizen guest = (Citizen) queue.get(i);
                guest.getCitizenStatus().getMainStatus().setEvent(msg + "waits for Rollercoaster");
                guest.getCitizenStatus().getEmotions().setFear(guest.getCitizenStatus().emotions.getFear()+1);
            }
            for (int i = 0; i < 3; i++) {
                if (queue.size() > 0) {

                    Citizen guest = (Citizen) queue.get(0);
                    queue.remove(0);

                    if(guest.getCitizenStatus().getEmotions().getFear()<90){
                        guest.getCitizenStatus().getMainStatus().setEventTime(1);
                        guest.getCitizenStatus().getMainStatus().setWallet(guest.getCitizenStatus().getMainStatus().getWallet() - 10);//guest.money -= 10;

                        guest.getCitizenStatus().getMainStatus().setEvent(msg + "takes a Ride in Rollercoaster");
                    }

                    else{
                        guest.getCitizenStatus().getMainStatus().setEvent(msg + "leaves the queue in fear");
                        if(guest.getCitizenStatus().getEmotions().getFear()<100){
                            guest.getCitizenStatus().getEmotions().setFear(100);
                        }
                    }

                    
                    guests.add(guest);
                }

            }
        }
    }

    private class Icecream implements Event{
        @Override
        public Category getCategory(){
            return Category.Fun;
        };
        @Override
        public void happens(Citizen citizen) {
            citizen.getCitizenStatus().getMainStatus().setEvent(msg + "Eats Icecream");
            citizen.getCitizenStatus().emotions.setFear(citizen.getCitizenStatus().emotions.getFear()-10);
        }

        @Override
        public void tick() {

        }

        @Override
        public Category getCategory() {
            return Category.Food;
        }
    }
}
