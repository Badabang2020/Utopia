import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UtopiaMain {
    static RecordSection utopiaCitizenRecords;
    static GKK employeeCare;
    static SVA companyCare;
    public static void main(String[] args) throws ParseException {
        utopiaCitizenRecords = new RecordSection();
        employeeCare = new GKK();
        companyCare = new SVA();

        System.out.println(utopiaCitizenRecords.registerCitizen(new Citizen("Max", "Mustermann", "08/15", 'm', new SimpleDateFormat("dd.MM.yyyy").parse("10.05.2000"), new Address(), employeeCare, false, new HealthStatus())));
        System.out.println(utopiaCitizenRecords.registerCitizen(new Citizen("Mira", "Musterfrau", "47/11", 'w', new SimpleDateFormat("dd.MM.yyyy").parse("05.04.2002"), new Address(), companyCare, false, new HealthStatus())));
        System.out.println(utopiaCitizenRecords.registerCitizen(new Citizen("Moritz", "Mustermann", "08/15", 'm', new SimpleDateFormat("dd.MM.yyyy").parse("10.05.2000"), new Address(), employeeCare, false, new HealthStatus())));
        System.out.println(utopiaCitizenRecords.getCitizen("08/15"));
    }
}
