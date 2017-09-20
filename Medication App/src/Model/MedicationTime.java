package Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MedicationTime  {

    private int medHour;
    private int militaryTime;
    private int medMinute;
    private String medAmOrPm;
    private int dose;
    private int numberOfPills;
    private Notification notification;
    private ArrayList<ScheduledExecutorService> schedulers;



    public MedicationTime(String name, int dose, int numberOfPills, int hour, int minute, String amOrPm){
        this.medHour = hour;
        this.militaryTime = hour + afternoonDecide(amOrPm, hour);
        this.medMinute = minute;
        this.medAmOrPm = amOrPm;
        this.notification = new Notification(name, dose, hour, minute, amOrPm);
        this.dose = dose;
        this.numberOfPills = numberOfPills;
        this.schedulers = new ArrayList<>();
        scheduleMedication();
    }


    public static int afternoonDecide(String amOrPm, int hour) {
        if (amOrPm.equals("AM") && hour == 12) {
            return -12;
        } if (amOrPm.equals("AM") || hour == 12) {
            return 0;
        } return 12;
    }

    public void scheduleMedication() {
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
        int delayHour = militaryTime - currentHour;
        int delayMinute = medMinute - currentMinute;
        int initialDelay = (delayHour * 60) + delayMinute;
        int minutesPerDay = 24*60;

        if(initialDelay < 0){
            initialDelay = minutesPerDay + initialDelay;
        }

        while (numberOfPills > 0) {
            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.schedule(notification, initialDelay, TimeUnit.MINUTES);
            schedulers.add(scheduler);
            System.out.println("Schedule Hour: " + militaryTime + " Schedule Minute: " + medMinute);
            initialDelay = initialDelay + minutesPerDay;
            numberOfPills = numberOfPills - dose;
        }
    }

    public int getMedHour() {
        return this.medHour;
    }



    public int getMedMinute() {
        return this.medMinute;
    }

    public String getMedAmOrPm() {
        return this.medAmOrPm;
    }






}
