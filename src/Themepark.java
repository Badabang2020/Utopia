import java.util.ArrayList;


public class Themepark implements Event {
    private ArrayList<Citizen> guests = new ArrayList<Citizen>();
    private Rollercoaster rollercoaster = new Rollercoaster();

    private String msg = "|at themepark|";

    public class Rollercoaster implements Event {

        ArrayList queue = new ArrayList();

        @Override
        public void happens(Citizen citizen) {
            queue.add(citizen);
            citizen.getCitizenStatus().getMainStatus().setEventTime(-1);
            citizen.getCitizenStatus().getMainStatus().setEvent(msg + "goes into queue for Rollercoaster");
        }
        @Override
        public void tick(){
            for (int i = 0; i < queue.size(); i++) {
                Citizen guest = (Citizen) queue.get(i);
                guest.getCitizenStatus().getMainStatus().setEvent(msg + "waits for Rollercoaster");
            }
            for (int i = 0; i < 1; i++) {
                if(queue.size()>0){

                    Citizen guest = (Citizen) queue.get(0);
                    queue.remove(0);
                    guest.getCitizenStatus().getMainStatus().setEventTime(1);
                    guest.getCitizenStatus().getMainStatus().setWallet(guest.getCitizenStatus().getMainStatus().getWallet()-10);//guest.money -= 10;

                    guest.getCitizenStatus().getMainStatus().setEvent(msg + "takes a Ride in Rollercoaster");

                    guests.add(guest);
                }

            }
        }
    }






    @Override
    public void happens(Citizen citizen) {
        citizen.getCitizenStatus().getMainStatus().setEventTime(-1);
        citizen.getCitizenStatus().getMainStatus().setEvent("goes to Themepark");
        guests.add(citizen);
    }



    @Override
    public void tick() {
        Citizen current;
        for (int i = 0; i < guests.size();i = 0) {
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

}
