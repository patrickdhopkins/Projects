package Model;

import ui.SimpleGUI;

import javax.swing.*;
import java.awt.*;

public class Notification implements Runnable {
    private String name;
    private int dose;
    private int hour;
    private int minute;
    private String amOrPm;

    private JFrame frame;


    public Notification(String name, int dose, int hour, int minute, String amOrPm) {
        this.name = name;
        this.dose = dose;
        this.hour = hour;
        this.minute = minute;
        this.amOrPm = amOrPm;
        this.frame = SimpleGUI.getInstance().getFrame();

    }

    @Override
    public void run() {
        String min;

        if (minute < 10) {
            min = "0" + minute;
        } else {min = "" + minute;}

        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(frame, name + " " + dose + " pill(s) " + hour + ":" + min + " " + amOrPm);




    }
}
