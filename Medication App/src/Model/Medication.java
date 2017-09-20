package Model;

import java.util.ArrayList;
import java.util.Iterator;


public class Medication {
    private String name;
    private int dose;
    private ArrayList<MedicationTime> medTimes;
    private MedManager meds;

    public Medication(String name, int dose, int numberOfPills, ArrayList<Integer> hours, ArrayList<Integer> minutes, ArrayList<String> amOrPms) {
        this.name = name;
        this.dose = dose;
        this.medTimes = new ArrayList<>();

        Iterator<Integer> it1 = hours.iterator();
        Iterator<Integer> it2 = minutes.iterator();
        Iterator<String> it3 = amOrPms.iterator();
        while (it1.hasNext() && it2.hasNext() && it3.hasNext()) {
            int hour = it1.next();
            int minute = it2.next();
            String amOrPm = it3.next();

            medTimes.add(new MedicationTime(name, dose, numberOfPills, hour, minute, amOrPm));

        }
        this.meds = MedManager.getInstance();
    }


    public String getName() {
        return this.name;
    }

    public int getPills() {
        return dose;
    }

    public ArrayList<MedicationTime> getMedTimes() {
        return this.medTimes;
    }




}
