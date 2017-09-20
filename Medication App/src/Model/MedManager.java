package Model;

import java.util.ArrayList;

public class MedManager {
    private static MedManager instance;
    ArrayList<Medication> meds;

    private MedManager(){
        this.meds = new ArrayList<Medication>();
    }

    public static MedManager getInstance() {
        if(instance == null) {
            instance = new MedManager();}
        return instance;
    }

    public void addMed(Medication med) { this.meds.add(med);
    }

    public void removeMed(Medication med) {
        this.meds.remove(med);
    }

    public ArrayList<Medication> getMeds() {
        return this.meds;
    }

}
