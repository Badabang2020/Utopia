import java.text.ParseException;



public class UtopiaMain {
    static RecordSection utopiaCitizenRecords;
    static GKK employeeCare;
    static SVA companyCare;

    public static String eventtrack(Citizen citizen){
        return "" + citizen.getFirstName() + "   " + citizen.getCitizenStatus().getMainStatus().getEvent();
    }



    public static void main(String[] args) throws ParseException {
        utopiaCitizenRecords = new RecordSection();
        employeeCare = new GKK();
        companyCare = new SVA();
        Bank bank = new Bank();
        Restaurant restaurant = new Restaurant();
        Themepark themepark = new Themepark();


        Citizen citizen = new Citizen("Moritz", "Mustermann", "08/12", 'm', 65, new Address(), employeeCare, false, new CitizenStatus());
        Citizen citizen1 = new Citizen("fabian", "Mustermann", "08/333", 'm', 65, new Address(), employeeCare, false, new CitizenStatus());
        Citizen citizen2 = new Citizen("ben", "Mustermann", "08/123235", 'm', 65, new Address(), employeeCare, false, new CitizenStatus());
        Citizen citizen3 = new Citizen("mathias", "Mustermann", "084254/15", 'm', 65, new Address(), employeeCare, false, new CitizenStatus());
        System.out.println("");
        System.out.println("------------------------------------");
        System.out.println("");

        System.out.println(eventtrack(citizen));
        System.out.println(eventtrack(citizen1));
        System.out.println(eventtrack(citizen2));
        System.out.println(eventtrack(citizen3));

        citizen.doEvent(bank);
        citizen1.doEvent(bank);
        citizen2.doEvent(bank);
        citizen3.doEvent(bank);

        System.out.println("");
        System.out.println("------------------------------------");
        System.out.println("");

        System.out.println(eventtrack(citizen));
        System.out.println(eventtrack(citizen1));
        System.out.println(eventtrack(citizen2));
        System.out.println(eventtrack(citizen3));

        citizen.doEvent(themepark);
        citizen1.doEvent(themepark);
        citizen2.doEvent(themepark);
        citizen3.doEvent(themepark);

        System.out.println("");
        System.out.println("------------------------------------");
        System.out.println("");


        for (int i = 0; i < 99; i++) {
            System.out.println((citizen));
            System.out.println("");
            System.out.println((citizen1));
            System.out.println("");
            System.out.println((citizen2));
            System.out.println("");
            System.out.println((citizen3));

            themepark.tick();

            System.out.println("");
            System.out.println("------------------------------------");
            System.out.println("");
        }





    }
}
