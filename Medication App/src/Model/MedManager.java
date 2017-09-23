package Model;

import java.util.ArrayList;

public class MedManager {
    private static MedManager instance;
    ArrayList<Medication> meds;

    private MedManager(){
        this.meds = new ArrayList<Medication>();
    }

    // Calls and Instance of MedManager
    public static MedManager getInstance() {
        if(instance == null) {
            instance = new MedManager();}
        return instance;
    }
    // Adds a Medication to the MedManager
    public void addMed(Medication med) { this.meds.add(med);
    }
    // Removes a Medications from the MedManager
    public void removeMed(Medication med) {
        this.meds.remove(med);
    }

    // Gives list of Medications stored in the MedManager
    public ArrayList<Medication> getMeds() {
        return this.meds;
    }

}
