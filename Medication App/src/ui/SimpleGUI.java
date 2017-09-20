package ui;

import Model.MedManager;
import Model.Medication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SimpleGUI {

    private final int FRAME_HEIGHT = 350;
    private final int FRAME_WIDTH = 375;

    private static SimpleGUI instance;
    private JFrame frame;

    private MedicationsGUI medicationsGui;

    public JTabbedPane tabbedPane;

    private JButton storeButton;
    private JButton addTimesButton;
    private JButton removeTimesButton;

    private JLabel nameLabel;
    private JLabel doseLabel;
    private JLabel numberOfPillsLabel;
    private ArrayList<JLabel> timeLabels;

    private JTextField nameField;
    private JTextField doseField;
    private JTextField numberOfPillsField;

    private ArrayList<JList<String>> hourLists;
    private ArrayList<JList<String>> minuteLists;
    private ArrayList<JList<String>> amOrPmLists;

    private JPanel panel;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panelExtraTimes;
    private JPanel panel5;

    private JPanel panelTimes;
    private JScrollPane scroller;
    private JScrollPane scroller1;
    private JScrollPane scroller2;
    private JScrollPane scroller3;


    private SimpleGUI(){};

    public static SimpleGUI getInstance(){
        if(instance == null) {
            instance = new SimpleGUI();}
        return instance;
    }

    //Runs medApp GUI
    public static void main(String[] args) {
        SimpleGUI gui = new SimpleGUI();
        instance = gui;
        gui.buildFrame();
        gui.go();}

        public void buildFrame() {
        this.frame = new JFrame();
        }

        //Sets up Main GUI
        public void go(){
        tabbedPane = new JTabbedPane();
        storeButton = new JButton("Save Medication");
        addTimesButton = new JButton("More Times");
        removeTimesButton = new JButton("Remove Times");

        nameLabel = new JLabel("Name: ");
        doseLabel = new JLabel("Dose: ");
        numberOfPillsLabel = new JLabel("Total Number of Pills: ");

        nameField = new JTextField();
        doseField = new JTextField();
        numberOfPillsField = new JTextField();

        hourLists = new ArrayList<>();
        minuteLists = new ArrayList<>();
        amOrPmLists = new ArrayList<>();

        panel = new JPanel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panelExtraTimes = new JPanel();
        panel5 = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
        panelExtraTimes.setLayout(new BoxLayout(panelExtraTimes, BoxLayout.Y_AXIS));
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));

        panel1.add(nameLabel);
        panel1.add(nameField);

        panel2.add(doseLabel);
        panel2.add(doseField);

        panel3.add(numberOfPillsLabel);
        panel3.add(numberOfPillsField);

        JLabel timeLabel1 = new JLabel("Time: ");

        timeLabels = new ArrayList<>();

       timeLabels.add(timeLabel1);
       panel4.add(timeLabels.get(0));
       panel4.add(panelExtraTimes);
       addTimes();

       panel5.add(storeButton);
       panel5.add(addTimesButton);
       panel5.add(removeTimesButton);

       panel.add(panel1);
       panel.add(panel2);
       panel.add(panel3);
       panel.add(panel4);
       panel.add(panel5);

       frame.getContentPane().add(BorderLayout.CENTER, tabbedPane);
       tabbedPane.addTab("Add Medication",panel);
       medicationsGui = MedicationsGUI.getInstance();

       storeButton.addActionListener(new StoreListener());
       addTimesButton.addActionListener(new MoreTimeListener());
       removeTimesButton.addActionListener(new RemoveTimeListener());

       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setVisible(true);
       frame.pack();
    }

    //Refreshes GUI
    public void reCast(){
        panelExtraTimes.removeAll();
        panelExtraTimes.repaint();
        panelExtraTimes.revalidate();
        hourLists.clear();
        minuteLists.clear();
        amOrPmLists.clear();
        addTimes();
        nameField.setText("");
        doseField.setText("");
        numberOfPillsField.setText("");
        frame.pack();
    }
    //Removes extra time and refreshes GUI
    public void removeTimes(){
        scroller.remove(hourLists.size() - 1);
        scroller1.remove(minuteLists.size() - 1);
        scroller2.remove(amOrPmLists.size() - 1);
        reCast();

    }

    //Adds time capacity to GUI
    public void addTimes() {
        String[] hourEntries = new String[13];

        for (int i = 1; i <= 12; i++) {
            hourEntries[i] = "" + i;
        }

        JList<String> hours = new JList<>(hourEntries);
        hours.setVisibleRowCount(10);
        hours.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scroller = new JScrollPane(hours);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        hourLists.add(hours);

        String[] minuteEntries = new String[60];

        for (int j = 0; j <= 59; j++) {
            minuteEntries[j] = "" + j;
        }

        JList<String> minutes = new JList<>(minuteEntries);
        minutes.setVisibleRowCount(10);
        minutes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scroller1 = new JScrollPane(minutes);
        scroller1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        minuteLists.add(minutes);

        String[] amOrPmEntries = new String[2];
        amOrPmEntries[0] = "AM";
        amOrPmEntries[1] = "PM";

        JList<String> amOrPm = new JList<>(amOrPmEntries);
        amOrPm.setVisibleRowCount(2);
        amOrPm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scroller2 = new JScrollPane(amOrPm);
        scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        amOrPmLists.add(amOrPm);

        panelTimes = new JPanel();
        panelTimes.setLayout(new BoxLayout(panelTimes, BoxLayout.X_AXIS));

        panelTimes.add(scroller);
        panelTimes.add(scroller1);
        panelTimes.add(scroller2);

        panelExtraTimes.add(panelTimes);
        frame.pack();
    }
    //Returns JFrame
    public JFrame getFrame(){
            return this.frame;
    }

    class StoreListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();

            int numberOfPills = Integer.parseInt(numberOfPillsField.getText());
            int dose = Integer.parseInt(doseField.getText());

            ArrayList<Integer> hours = new ArrayList<>();

            for (JList<String> hourSelection : hourLists) {
                hours.add(Integer.parseInt(hourSelection.getSelectedValue()));
            }

            ArrayList<Integer> minutes = new ArrayList<>();

            for (JList<String> minuteSelection : minuteLists) {
                minutes.add(Integer.parseInt(minuteSelection.getSelectedValue()));
            }

            ArrayList<String> amOrPms = new ArrayList<>();

            for (JList<String> amOrPmSelection : amOrPmLists) {
                amOrPms.add(amOrPmSelection.getSelectedValue());
            }

            Medication medication = new Medication(name, dose, numberOfPills, hours, minutes, amOrPms);

            for(int hour : hours){
                System.out.println("Hours: " + hour);}

            System.out.println("Size: " + hourLists.size());

            MedManager.getInstance().addMed(medication);
            medicationsGui.loadMeds();
            reCast();
        }
    }

    class MoreTimeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            addTimes();
            frame.pack();
        }
    }

    class RemoveTimeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.setSize(new Dimension(frame.getWidth(), frame.getHeight() - 150));
            removeTimes();
            frame.pack();


        }
    }

}
