package Database;

public interface UtopiaDAO {

    //methods for Citizens
    public void getAllCitizens();
    public void getCitizen();
    public void updateCitizen();
    public void deleteCitizen();
    public void addCitizen();

    //methods for Facilities
    public void getAllFacilities();
    public void updateFacilities();

    //method to synchronize
    public void synchronize();
    
}
