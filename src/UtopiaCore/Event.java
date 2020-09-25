package UtopiaCore;

import Citizen.Citizen;

public interface Event {
    public void happens(Citizen citizen);
    public void tick();
    public Category[] getCategory();
    //     public UtopiaCore.Category[] getCategory() {
    //        UtopiaCore.Category[] category = new UtopiaCore.Category[] {UtopiaCore.Category.1, UtopiaCore.Category.2, ...};
    //        return category;
    //     }
}
