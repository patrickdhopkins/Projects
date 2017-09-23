package Model;

import ui.MedicationsGUI;
import ui.SimpleGUI;

import javax.swing.*;
import java.awt.*;

public class Notification implements Runnable {
    private int hour;
    private int minute;
    private String amOrPm;
    private JFrame frame;
    private Medication medication;


    // Creates new Notification

    public Notification(Medication medication, int hour, int minute, String amOrPm) {
        this.medication = medication;
        this.hour = hour;
        this.minute = minute;
        this.amOrPm = amOrPm;
        this.frame = SimpleGUI.getInstance().getFrame();
    }

    // Launches Notification in JOptionPane with beep sound, reduces the number of pills left of associated Medication by dose

    @Override
    public void run() {
        String min;

        if (minute < 10) {
            min = "0" + minute;
        } else {min = "" + minute;}

        this.medication.takeMedication();

        MedicationsGUI.getInstance().loadMeds();

        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(frame, this.medication.getName() + ", " + this.medication.getPills() + " pill(s), " + hour + ":" + min + " " + amOrPm + ", " + this.medication.getNumberOfPills() + " Pills Left");

    }
}
