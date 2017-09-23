package Model;

import java.util.ArrayList;
import java.util.Iterator;


public class Medication {
    private String name;
    private int dose;
    private ArrayList<MedicationTime> medTimes;
    private int numberOfPills;

    // Creates a new Medication, adds it to Instance of MedManager

    public Medication(String name, int dose, int numberOfPills, ArrayList<Integer> hours, ArrayList<Integer> minutes, ArrayList<String> amOrPms) {
        this.name = name;
        this.dose = dose;
        this.medTimes = new ArrayList<>();
        this.numberOfPills = numberOfPills;

        Iterator<Integer> it1 = hours.iterator();
        Iterator<Integer> it2 = minutes.iterator();
        Iterator<String> it3 = amOrPms.iterator();
        while (it1.hasNext() && it2.hasNext() && it3.hasNext()) {
            int hour = it1.next();
            int minute = it2.next();
            String amOrPm = it3.next();

            medTimes.add(new MedicationTime(this, hour, minute, amOrPm));

        }
        MedManager.getInstance().addMed(this);

    }

    public void takeMedication() {
        this.numberOfPills = numberOfPills - dose;
        if(numberOfPills <= 0) {
            MedManager.getInstance().removeMed(this);
        }
    }


    // Returns name of Medication
    public String getName() {
        return this.name;
    }

    // Returns dose of Medications
    public int getPills() {
        return dose;
    }

    // Returns the total number of pills left
    public int getNumberOfPills() {
        return numberOfPills;
    }

    // Returns times Medication should be taken
    public ArrayList<MedicationTime> getMedTimes() {
        return this.medTimes;
    }




}
