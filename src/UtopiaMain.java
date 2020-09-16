import java.text.ParseException;

public class UtopiaMain {
    static RecordSection utopiaCitizenRecords;
    static GKK employeeCare;
    static SVA companyCare;
    static Controller myController =new Controller();

    public static void main(String[] args) throws ParseException {
        utopiaCitizenRecords = new RecordSection();
        employeeCare = new GKK();
        companyCare = new SVA();



        System.out.println(utopiaCitizenRecords.registerCitizen(new Citizen("Max", "Mustermann", "08/15", 'm', 24, new Address(), employeeCare, false, new CitizenStatus())));
        System.out.println(utopiaCitizenRecords.registerCitizen(new Citizen("Mira", "Musterfrau", "47/11", 'w', 14, new Address(), companyCare, false, new CitizenStatus())));
        System.out.println(utopiaCitizenRecords.registerCitizen(new Citizen("Moritz", "Mustermann", "08/15", 'm', 65, new Address(), employeeCare, false, new CitizenStatus())));
        System.out.println(utopiaCitizenRecords.getCitizen("08/15"));
    }
}
