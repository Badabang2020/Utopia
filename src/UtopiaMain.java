import java.text.ParseException;

public class UtopiaMain {
    static RecordSection utopiaCitizenRecords;
    static GKK employeeCare;
    static SVA companyCare;

    public static void main(String[] args) throws ParseException {
        utopiaCitizenRecords = new RecordSection();
        employeeCare = new GKK();
        companyCare = new SVA();
        Bank bank = new Bank();
        Restaurant restaurant = new Restaurant();


        Citizen citizen = new Citizen("Moritz", "Mustermann", "08/15", 'm', 65, new Address(), employeeCare, false, new CitizenStatus());

        System.out.println(citizen);

        citizen.doEvent(bank);

        System.out.println(citizen);

        citizen.doEvent(restaurant);

        System.out.println(citizen);
    }
}
