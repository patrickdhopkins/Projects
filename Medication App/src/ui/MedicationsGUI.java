package ui;

import Model.MedManager;
import Model.Medication;
import Model.MedicationTime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class MedicationsGUI {

    private static MedicationsGUI instance;
    private ArrayList<Medication> meds;

    public JPanel panel;

    private HashMap<JButton, Medication> buttonMedicationHashmap;

    private MedicationsGUI() {
        this.meds = MedManager.getInstance().getMeds();
        loadMeds();
    }

    public static MedicationsGUI getInstance() {
        if(instance == null) {
            instance = new MedicationsGUI();}
        return instance;
    }

    public void loadMeds() {
        this.panel = new JPanel();

        buttonMedicationHashmap = new HashMap<>();


        for(Medication med : this.meds) {

            String name = med.getName();
            JLabel nameLabel = new JLabel(name);

            int dose = med.getPills();
            JLabel doseLabel = new JLabel(dose + " pills");

            ArrayList<MedicationTime> medTimes = med.getMedTimes();

            JPanel medPanel = new JPanel();
            medPanel.setLayout(new BoxLayout(medPanel, BoxLayout.Y_AXIS));

            medPanel.add(nameLabel);
            medPanel.add(doseLabel);

            JButton removeMedButton = new JButton("Remove " + med.getName());
            removeMedButton.addActionListener(new RemoveMedicationListener());

            buttonMedicationHashmap.put(removeMedButton, med);

            for(MedicationTime medTime : medTimes) {

                int hour = medTime.getMedHour();
                int minute = medTime.getMedMinute();

                String amOrPm = medTime.getMedAmOrPm();
                String labelText;

                if (minute < 10) {
                    labelText = hour + ":" + "0" + minute + " " + amOrPm;
                } else  {labelText = hour + ":" + minute + " " + amOrPm;}

                JLabel timeLabel = new JLabel(labelText);
                medPanel.add(timeLabel);
            }

            medPanel.add(removeMedButton);
            JPanel wrapperPanel = new JPanel(new GridBagLayout());
            wrapperPanel.add(medPanel);
            panel.add(wrapperPanel);

        }

        if (SimpleGUI.getInstance().tabbedPane.getTabCount() > 1) {
            SimpleGUI.getInstance().tabbedPane.removeTabAt(1);
        }
        SimpleGUI.getInstance().tabbedPane.addTab("Current Medications", this.panel);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        SimpleGUI.getInstance().getFrame().pack();
    }

    public class RemoveMedicationListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton buttonPressed = (JButton)e.getSource();
            Medication medRemoved = buttonMedicationHashmap.get(buttonPressed);

            buttonMedicationHashmap.remove(buttonPressed);
            meds.remove(medRemoved);
            loadMeds();
        }
    }





}
