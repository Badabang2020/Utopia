import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class RecordSection {
    HashMap <String, Citizen> citizenRegister;

    RecordSection() {
        citizenRegister = new HashMap<String, Citizen>();
    }

    boolean registerCitizen(Citizen citizen) {
        String socialSecurityNumber = citizen.getSocialSecurityNumber();
        if (citizenRegister.containsKey(socialSecurityNumber)) {
            return false;
        } else {
            citizenRegister.put(socialSecurityNumber, citizen);
            return true;
        }
    }
    public Citizen getCitizen(String socialSecurityNumber) {
        if (citizenRegister.containsKey(socialSecurityNumber)) {
            return citizenRegister.get(socialSecurityNumber);
        } else {
            return null;
        }
    }
}
