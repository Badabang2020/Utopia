<<<<<<< HEAD
package Health;

import Citizen.Citizen;
import UtopiaCore.Category;
import UtopiaCore.Event;

=======
<<<<<<< HEAD:src/Hospital.java
>>>>>>> a72cceeadf393038fae1a58c06d6dbe73cf8d68c
public class Hospital implements Event {
    Citizen patient;

    @Override
    public void happens(Citizen citizen) {
        citizen.getCitizenStatus().getMainStatus().setEventTime(-1);
        this.patient = citizen;
        System.out.println("!test");
    }

    @Override
    public void tick() {
        if (patient != null) {
        int health = patient.getCitizenStatus().getMainStatus().getHealthbar();
        health += 5;
        patient.getCitizenStatus().getMainStatus().setHealthbar(health);
        if (patient.getCitizenStatus().getMainStatus().getHealthbar() == 100) {
            patient.getCitizenStatus().getMainStatus().setEventTime(0);
            patient = null;
        }
        }
    }

    @Override
    public Category[] getCategory() {
        return new Category[] {Category.Health};
    }
}
<<<<<<< HEAD
=======
=======
package Health;

public class Hospital {
    String name;
    int test;
}
>>>>>>> 1d5401338bc8f9ca851e1c8f21c63264ac811f7f:src/Health/Hospital.java
>>>>>>> a72cceeadf393038fae1a58c06d6dbe73cf8d68c
