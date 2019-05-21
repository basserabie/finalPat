/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patstake1;

import com.toedter.calendar.JCalendar;
import java.awt.Color;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TreeSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import static net.ucanaccess.converters.Functions.date;

/**
 *
 * @author YishaiBasserabie
 */
public class CalendarHandler {
    
    public static boolean DAY_HAS_LESSON = true;
    private static int COLOUR = 0;
    
    

    public void JCalendarActionPerformed(JCalendar cal) {
        cal.getDayChooser().addPropertyChangeListener("day", new PropertyChangeListener() {
        @Override
        public void propertyChange(PropertyChangeEvent e) {
            CalendarHandler ch = new CalendarHandler();
            String date = ch.getFormattedDateFromJCalendar(cal.getDate().toString());
            
            ch.passToDailyPlan(date);
        }  
        });
    }
    
    public String getFormattedDateFromJCalendar(String UDate) {
        lessonDataArray la = new lessonDataArray();
        String formattedDate = "";
        
        String UYear = UDate.substring(25);
        String UMonth = UDate.substring(4, 7);
        String UDay = UDate.substring(8, 10);
        
        //checks and formats month
        String finalMonth = "";
        switch (UMonth) {
            case "Jan": 
                finalMonth = "01";
                break;
            case "Feb":
                finalMonth = "02";
                break; 
            case "Mar":
                finalMonth = "03";
                break; 
            case "Apr":
                finalMonth = "04";
                break; 
            case "May":
                finalMonth = "05";
                break; 
            case "Jun":
                finalMonth = "06";
                break; 
            case "Jul":
                finalMonth = "07";
                break; 
            case "Aug":
                finalMonth = "08";
                break; 
            case "Sep":
                finalMonth = "09";
                break; 
            case "Oct":
                finalMonth = "10";
                break; 
            case "Nov":
                finalMonth = "11";
                break; 
            case "Dec":
                finalMonth = "12";
                break; 
        }
        formattedDate = UYear + "/" + UDay + "/" + finalMonth;
        return formattedDate;
    }
    
    public String getFirstStartTime(String date) {
        String startTime = this.getStartTimesOnDate(date)[0];
        return startTime;
    }
    
    public int getStartTimeIndexfromStartTime(String date, String startTime) {
        int index = 0;
        for (int i = 0; i < this.getStartTimesOnDate(date).length; i++) {
            if (this.getStartTimesOnDate(date)[i].equals(startTime)) {
                index = i;
            }
        }
        return index;
    }
    
    public String floorStartTime(String attemptedTime, String date) {
        lessonDataArray la = new lessonDataArray();
        TreeSet<Date> times = new TreeSet<> ();
        String time = "";
        
        //create a date fromatter
            DateFormat sdf = new SimpleDateFormat("yyy/dd/MM HH:mm");
            DateFormat sdf2 = new SimpleDateFormat("HH:mm");
            
            Calendar timeSeg = Calendar.getInstance(); // adds instance to cal
            try {
                timeSeg.setTime(sdf.parse(date + " " + attemptedTime));
            } catch (ParseException ex) {
                Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        for (int i = 0; i < this.getStartTimesOnDate(date).length; i++) {
            //create a date fromatter
            Calendar startTime = Calendar.getInstance(); // adds instance to timeIn
            try {
                startTime.setTime(sdf.parse(date + " " + this.getStartTimesOnDate(date)[i])); 
                times.add(startTime.getTime());
            } catch (ParseException ex) {
                Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //gets first starttime of day
        Calendar firstStartTime = Calendar.getInstance();
        try {
            firstStartTime.setTime(sdf.parse(date + " " + this.getFirstStartTime(date)));
        } catch (ParseException ex) {
            Logger.getLogger(CalendarHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Date floor = null;
        boolean overTime = true;
        if (timeSeg.getTime().toString() != null && !(timeSeg.getTime().before(firstStartTime.getTime()))) {
                if (times.floor(timeSeg.getTime()) != null) {
                    floor = times.floor(timeSeg.getTime());
                }
        Calendar endTime = Calendar.getInstance();
        Calendar checkTime = Calendar.getInstance();
        try {
            if (floor != null) {
                endTime.setTime(sdf2.parse(this.getEndTimesOnDate(date)[this.getStartTimeIndexfromStartTime(date, sdf2.format(floor.getTime()))]));
                checkTime.setTime(sdf2.parse(sdf2.format(timeSeg.getTime())));
                if (checkTime.before(endTime)) {
                    overTime = false;
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(CalendarHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
          if (floor != null && overTime == false)  {
            time = sdf2.format(times.floor(floor));
          } 
        }
        return time;
    }
    
    public String [] getStartTimesOnDate(String date) {
        lessonDataArray la = new lessonDataArray();
        keysArray ka = new keysArray();
        ArrayList<String> startTimes = new ArrayList<>();
        
        for (int i = 0; i < this.keysOnDay(date).length; i++) {
            String startAttemptedTime = ka.getStartTimeFromKey(this.keysOnDay(date)[i]);
            startTimes.add(startAttemptedTime);
        }
        String times [] = startTimes.toArray(new String[startTimes.size()]);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
        Calendar t1 = Calendar.getInstance();
        Calendar t2 = Calendar.getInstance();
        for (int i = 0; i < times.length-1; i++) {
            for (int k = i+1; k < times.length; k++) {
                try {
                    t1.setTime(sdf.parse(date + " " + times[i]));
                    t2.setTime(sdf.parse(date + " " + times[k]));
                } catch (ParseException ex) {
                    Logger.getLogger(CalendarHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (t2.before(t1)) {
                    String temp = sdf2.format(t1.getTime());
                    times[i] = times[k];
                    times[k] = temp;
                }
            }
        }
        return times;
    }
    
    public String [] getEndTimesOnDate(String date) {
        lessonDataArray la = new lessonDataArray();
        keysArray ka = new keysArray();
        ArrayList<String> endTimes = new ArrayList<>();
        
        for (int i = 0; i < this.keysOnDay(date).length; i++) {
            String endAttemptedTime = ka.getEndTimeFromKey(this.keysOnDay(date)[i]);
            endTimes.add(endAttemptedTime);
        }
        String times [] = endTimes.toArray(new String[endTimes.size()]);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
        Calendar t1 = Calendar.getInstance();
        Calendar t2 = Calendar.getInstance();
        for (int i = 0; i < times.length-1; i++) {
            for (int k = i+1; k < times.length; k++) {
                try {
                    t1.setTime(sdf.parse(date + " " + times[i]));
                    t2.setTime(sdf.parse(date + " " + times[k]));
                } catch (ParseException ex) {
                    Logger.getLogger(CalendarHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (t2.before(t1)) {
                    String temp = sdf2.format(t1.getTime());
                    times[i] = times[k];
                    times[k] = temp;
                }
            }
        }
        return times;
    }
    
    public int [] getLessonIDsOnDate(String date) {
        lessonDataArray la = new lessonDataArray();
        keysArray ka = new keysArray();
        ArrayList<Integer> lessonIDs = new ArrayList<>();
        
        for (int i = 0; i < this.keysOnDay(date).length; i++) {
            lessonIDs.add(ka.getFirstLessonIDFromKey(this.keysOnDay(date)[i]));
        }
        int ids [] = lessonIDs.stream().mapToInt(i -> i).toArray();
        return ids;
    }
    
    
    public String getLessonDataOnThatDateAndTime(String date, String timeInputted) {
        lessonDataArray la = new lessonDataArray();
        keysArray ka = new keysArray();
        String lessonsOnDayData = "Youre lesson at this time is:\n\n";
        String lessonIntro = "";
        String timeRef = "";
        
         //checks if there is a startTime to the selected time and assignes that startTime to timeRef
        if (this.TimeHasLesson(date, timeInputted)) {
            timeRef = this.floorStartTime(timeInputted, date);
        }
        
        if (!timeRef.equals("")) {
            String time = "";
            String venue = "";
            String students = "";
            //populates lessonsOnDayData with the data for all of the lessons on the selected date
                String key = ka.getKeyFromDateAndTime(date, timeRef);
                switch (la.getFrequencyFromKey(key)) {
                    case "once-off":
                        lessonIntro = "This is a once-off lesson:\n";
                        break;
                    case "weekly":
                        lessonIntro = "This is a weekly lesson:\n";
                        break;
                    case "monthly":
                        lessonIntro = "This is a monthly lesson:\n";
                        break;
                }
            time = "Time: " + this.timeFromLessonKey(key) + "\n";
            venue = "Venue: " + this.venueFromLessonKey(key) + "\n";
            students = "Student(s): " + this.studentsStringFromArray(this.studentsFromLessonDateAndTime(date, this.StartTimeFromLessonKey(key)));
            lessonsOnDayData += lessonIntro + time + venue + students + "\n";
        } else {
            lessonsOnDayData = "You ave no lesson at this time! :) :)";
        }
        
       return  lessonsOnDayData;
    }
    
    public String studentsStringFromArray(String [] studentsArray) {
        String students = "";
        for (int i = 0; i < studentsArray.length; i++) {
            students += studentsArray[i] + "\n                  ";
        }
        return students;
    }
    
    public String studentsStringFromArrayForParent(String [] studentsArray) {
        String students = "";
        for (int i = 0; i < studentsArray.length; i++) {
            students += studentsArray[i] + ", ";
        }
        return students;
    }
    
    public String getKeyFromPositionInDayAndDate(int index, String date) {
        String key = "";
        for (int i = 0; i < this.keysOnDay(date).length; i++) {
            if (i == index) {
                key = this.keysOnDay(date)[i];
            }
        }
        return key;
    }
    
    public String [] keysOnDay(String date) {
        lessonDataArray la = new lessonDataArray();
        keysArray ka = new keysArray();
        ka.sortArray();
        la.sortArray();
        boolean keyAlreadyIn = false;
        ArrayList<String> keys = new ArrayList<>();
        
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {
            if (la.getLessonDataArray().get(i).getLessonDate().equals(date)) {
                if (!keys.contains(ka.getKeyFromLessonID(la.getLessonDataArray().get(i).getLessonID()))) {
                    keys.add(ka.getKeyFromLessonID(la.getLessonDataArray().get(i).getLessonID()));
                }
            }
        }
        
        Calendar p1 = Calendar.getInstance();
        Calendar p2 = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM HH:mm");
        for (int i = 0; i < keys.size(); i++) {
            try {
                if (i < keys.size()-1) {
                    p1.setTime(sdf.parse(ka.getDateFromKey(keys.get(i)) + " " + ka.getStartTimeFromKey(keys.get(i))));
                    p2.setTime(sdf.parse(ka.getDateFromKey(keys.get(i+1)) + " " + ka.getStartTimeFromKey(keys.get(i+1))));
                }
                String temp;
                if (p2.after(p1)) {
                    temp = keys.get(i+1);
                    keys.set(i+1, keys.get(i));
                    keys.set(i, temp);
                }
            } catch (ParseException ex) {
                Logger.getLogger(keysArray.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String keysArray [] = keys.toArray(new String[keys.size()]);
        return keysArray;
    }
    
    public String StartTimeFromLessonKey(String key) {
        lessonDataArray la = new lessonDataArray();
        keysArray ka = new keysArray();
        String time = "";
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {
            if (ka.getKeyFromLessonID(la.getLessonDataArray().get(i).getLessonID()).equals(key)) {
                time = la.getLessonStartTimeFromLessonID(la.getLessonDataArray().get(i).getLessonID());  
            }
        }
        return time;
    }
    
    public String timeFromLessonKey(String key) {
        lessonDataArray la = new lessonDataArray();
        keysArray ka = new keysArray();
        String time = "";
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {
            
            if (ka.getKeyFromLessonID(la.getLessonDataArray().get(i).getLessonID()).equals(key)) {
                time = la.getLessonDataArray().get(i).getLessonTime() + " - " +
                        la.getEndTime(la.getLessonDataArray().get(i).getLessonTime(), la.getLessonDataArray().get(i).getLessonDuration());
            }
        }
        return time;
    }
    
    public String venueFromLessonKey(String key) {
        keysArray ka = new keysArray();
        venueArray va = new venueArray();
        lessonDataArray la = new lessonDataArray();
        String venue = "";
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {
            if (ka.getKeyFromLessonID(la.getLessonDataArray().get(i).getLessonID()).equals(key)) {
                int lessonID = la.getLessonDataArray().get(i).getLessonID();
                int index = la.getIndexFromID(lessonID);
                venue = va.venueNameFromID(la.getLessonDataArray().get(index).getVenueID());
            }
        }
        return venue;
    }
    
    public int NumberOfLessonsOnDay(String date) {
        lessonDataArray la = new lessonDataArray();
        keysArray ka = new keysArray();
        ArrayList<String> keys = new ArrayList<>();
        boolean keyAlreadyIn = false;
        
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {
            if (la.getLessonDataArray().get(i).getLessonDate().equals(date)) {
                //checks if lesson already added to key list by checking it against the lessonKey
                for (int k = 0; k < keys.size(); k++) {
                    if (keys.get(k).equals(ka.getKeyFromLessonID(la.getLessonDataArray().get(i).getLessonID()))) {
                        keyAlreadyIn = true;
                    }
                }
                if (keyAlreadyIn == false) {
                    keys.add(ka.getKeyFromLessonID(la.getLessonDataArray().get(i).getLessonID()));
                }
                keyAlreadyIn = false;
            }
        }
        return keys.size();
    }
    
    public String [] studentsFromLessonDateAndTime(String date, String time) {
        lessonDataArray la = new lessonDataArray();
        keysArray ka = new keysArray();
        studentsArray sa = new studentsArray();
        boolean studentAlreadyIn = false;
        ArrayList<String> students = new ArrayList<>();
        
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {
            if (la.getLessonDataArray().get(i).getLessonDate().equals(date) && la.getLessonDataArray().get(i).getLessonTime().equals(time)) {
                students.add(sa.studentNameFromID(la.getLessonDataArray().get(i).getStudentID()));
            }
        }
        String studentsArray [] = students.toArray(new String[students.size()]);
        return studentsArray;
    }
    
    public String getEndSegTime(String segStartTime, String date) {
        //create a date fromatter
        DateFormat sdf = new SimpleDateFormat("yyy/dd/MM HH:mm");
        Calendar time = Calendar.getInstance(); // adds instance to cal
        try {
            time.setTime(sdf.parse(date + " " + segStartTime));
        } catch (ParseException ex) {
            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
        }
        time.add(Calendar.MINUTE, 15);
        String StringTime = sdf.format(time.getTime());
        return StringTime;
    }
    
    public boolean TimeHasLesson(String date, String time) {
        lessonDataArray la = new lessonDataArray();
        keysArray ka = new keysArray();
        boolean segHasLessonBooked = false;
        ArrayList<String> keys = new ArrayList<>();
        ArrayList<String> startsBooked = new ArrayList<>();
        ArrayList<String> endsBooked = new ArrayList<>();
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {
            if (la.getLessonDataArray().get(i).getLessonDate().equals(date)) {
                if (!keys.contains(ka.getKeyFromLessonID(la.getLessonDataArray().get(i).getLessonID()))) {
                    keys.add(ka.getKeyFromLessonID(la.getLessonDataArray().get(i).getLessonID()));
                }
            }
        }
        for (int i = 0; i < keys.size(); i++) {
            String startTime = ka.getStartTimeFromKey(keys.get(i));
            String endTime = ka.getEndTimeFromKey(keys.get(i));
            startsBooked.add(startTime);
            endsBooked.add(endTime);
        }
        
        //create a date fromatter
        DateFormat sdf = new SimpleDateFormat("yyyy/dd/MM HH:mm");
        Calendar attemptedStartTime = Calendar.getInstance(); // adds instance to cal
        Calendar attemptedEndTime = Calendar.getInstance();
        try {
            attemptedStartTime.setTime(sdf.parse(date + " " + time)); 
            attemptedEndTime.setTime(sdf.parse(this.getEndSegTime(time, date)));
        } catch (ParseException ex) {
            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < keys.size(); i++) {
            
            Calendar refStartTime = Calendar.getInstance();
            Calendar refEndTime = Calendar.getInstance();
            try {
                refStartTime.setTime(sdf.parse(date + " " + this.getStartTimesOnDate(date)[i]));
                if (refStartTime.getTime().toString() != null) {
                    refEndTime.setTime(sdf.parse(date + " " + this.getEndTimesOnDate(date)[i]));
                } 
            } catch (ParseException ex) {
                Logger.getLogger(CalendarHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (attemptedStartTime.equals(refStartTime) ||
                    attemptedEndTime.equals(refEndTime) ||
                    attemptedStartTime.after(refStartTime) && attemptedEndTime.before(refEndTime)) {
                segHasLessonBooked = true;
            }
        }
        return segHasLessonBooked;
    }
    
    public String formatEventAtHour(String date, String time, String startTime) {
        lessonDataArray la = new lessonDataArray();
        String lessonDataEventFiller = "";
        keysArray ka = new keysArray();
        ka.sortArray();
        DateFormat sdf = new SimpleDateFormat("yyy/dd/MM HH:mm");
        DateFormat sdf2 = new SimpleDateFormat("HH:mm");
        String colours [] = {"red", "blue", "green", "pink", "purple", "yellow", "orange"};
        String colour = colours[COLOUR];
        boolean after = false;
        
        if (this.TimeHasLesson(date, time)) {
            String endTime = "";
            for (int i = 0; i < la.getLessonDataArray().size(); i++) {
                if (la.getLessonDataArray().get(i).getLessonDate().equals(date) && la.getLessonDataArray().get(i).getLessonTime().equals(startTime)) {
                    endTime = la.getEndTime(la.getLessonDataArray().get(i).getLessonTime(), la.getLessonDataArray().get(i).getLessonDuration());
                }
            }
            
            Calendar timeDate = Calendar.getInstance();
            Calendar endTimeDate = Calendar.getInstance();
            Calendar endTimeDateRef = Calendar.getInstance();
            
            try {
                timeDate.setTime(sdf.parse(date + " " + time));
                endTimeDate.setTime(sdf.parse(this.getEndSegTime(sdf2.format(timeDate.getTime()), date)));
                endTimeDateRef.setTime(sdf.parse(date + " " + endTime));
            } catch (ParseException ex) {
                Logger.getLogger(CalendarHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            lessonDataEventFiller = "<html><font size = 200 color=\"" + colour + "\">■■■■</font></html>";
            
            if (endTimeDate.equals(endTimeDateRef)) {
                if (COLOUR < colours.length-1) {
                    COLOUR++;
                } else {
                    COLOUR = 0;
                }
            }
        }
        
        return lessonDataEventFiller;
    }
     public void LessonsOnDay(String date) {
         if (this.getStartTimesOnDate(date).length == 0) {
             DAY_HAS_LESSON = false;
         }
     }
    
    public DefaultTableModel selectedDateModel(String date) {
        DefaultTableModel model = null;
        lessonDataArray la = new lessonDataArray();
        
            Object columnNames[] = {"06:00", "06:15", "06:30", "06:45", "07:00", "07:15", "07:30", "07:45", "08:00",
                "08:15", "08:30", "08:45", "09:00", "09:15", "09:30", "09:45", "10:00", "10:15", "10:30", "10:45",
                    "11:00", "11:15", "11:30", "11:45", "12:00", "12:15", "12:30", "12:45", "13:00", "13:15", "13:30",
                    "13:45", "14:00", "14:15", "14:30", "14:45", "15:00", "15:15", "15:30", "15:45", "16:00", "16:15",
                    "16:30", "16:45", "17:00", "17:15", "17:30", "17:45", "18:00", "18:15", "18:30", "18:45", "19:00",
                    "19:15", "19:30", "19:45", "20:00", "20:15", "20:30", "20:45", "21:00", "21:15", "21:30", "21:45",
                    "22:00", "22:15", "22:30", "22:45", "23:00", "23:15", "23:30", "23:45"};
            model = new DefaultTableModel(columnNames, 0);

            System.out.println("1");
                String sixAM = this.formatEventAtHour(date, "06:00", this.floorStartTime("06:00", date));
                String six15AM = this.formatEventAtHour(date, "06:15", this.floorStartTime("06:15", date));
                String six30AM = this.formatEventAtHour(date, "06:30", this.floorStartTime("06:30", date));
                String six45AM = this.formatEventAtHour(date, "06:45", this.floorStartTime("06:45", date));
                String sevenAM = this.formatEventAtHour(date, "07:00", this.floorStartTime("07:00", date));
                String seven15AM = this.formatEventAtHour(date, "07:15", this.floorStartTime("07:15", date));
                String seven30AM = this.formatEventAtHour(date, "07:30", this.floorStartTime("07:30", date));
                String seven45AM = this.formatEventAtHour(date, "07:45", this.floorStartTime("07:45", date));
                String eightAM = this.formatEventAtHour(date, "08:00", this.floorStartTime("08:00", date));
                String eight15AM = this.formatEventAtHour(date, "08:15", this.floorStartTime("08:15", date));
                String eight30AM = this.formatEventAtHour(date, "08:30", this.floorStartTime("08:30", date));
                String eight45AM = this.formatEventAtHour(date, "08:45", this.floorStartTime("08:45", date));
                String nineAM = this.formatEventAtHour(date, "09:00", this.floorStartTime("09:00", date));
                String nine15AM = this.formatEventAtHour(date, "09:15", this.floorStartTime("09:15", date));
                String nine30AM = this.formatEventAtHour(date, "09:30", this.floorStartTime("09:30", date));
                String nine45AM = this.formatEventAtHour(date, "09:45", this.floorStartTime("09:45", date));
                String tenAM = this.formatEventAtHour(date, "10:00", this.floorStartTime("10:00", date));
                String ten15AM = this.formatEventAtHour(date, "10:15", this.floorStartTime("10:15", date));
                String ten30AM = this.formatEventAtHour(date, "10:30", this.floorStartTime("10:30", date));
                String ten45AM = this.formatEventAtHour(date, "10:45", this.floorStartTime("10:45", date));
                String elevenAM = this.formatEventAtHour(date, "11:00", this.floorStartTime("11:00", date));
                String eleven15AM = this.formatEventAtHour(date, "11:15", this.floorStartTime("11:15", date));
                System.out.println("2");
                String eleven30AM = this.formatEventAtHour(date, "11:30", this.floorStartTime("11:30", date));
                String eleven45AM = this.formatEventAtHour(date, "11:45", this.floorStartTime("11:45", date));
                String twelvePM = this.formatEventAtHour(date, "12:00", this.floorStartTime("12:00", date));
                String twelve15PM = this.formatEventAtHour(date, "12:15", this.floorStartTime("12:15", date));
                String twelve30PM = this.formatEventAtHour(date, "12:30", this.floorStartTime("12:30", date));
                String twelve45PM = this.formatEventAtHour(date, "12:45", this.floorStartTime("12:45", date));
                String onePM = this.formatEventAtHour(date, "13:00", this.floorStartTime("13:00", date));
                String one15PM = this.formatEventAtHour(date, "13:15", this.floorStartTime("13:15", date));
                String one30PM = this.formatEventAtHour(date, "13:30", this.floorStartTime("13:30", date));
                String one45PM = this.formatEventAtHour(date, "13:45", this.floorStartTime("13:45", date));
                String twoPM = this.formatEventAtHour(date, "14:00", this.floorStartTime("14:00", date));
                String two15PM = this.formatEventAtHour(date, "14:15", this.floorStartTime("14:15", date));
                String two30PM = this.formatEventAtHour(date, "14:30", this.floorStartTime("14:30", date));
                String two45PM = this.formatEventAtHour(date, "14:45", this.floorStartTime("14:45", date));
                String threePM = this.formatEventAtHour(date, "15:00", this.floorStartTime("15:00", date));
                String three15PM = this.formatEventAtHour(date, "15:15", this.floorStartTime("15:15", date));
                String three30PM = this.formatEventAtHour(date, "15:30", this.floorStartTime("15:30", date));
                String three45PM = this.formatEventAtHour(date, "15:45", this.floorStartTime("15:45", date));
                String fourPM = this.formatEventAtHour(date, "16:00", this.floorStartTime("16:00", date));
                System.out.println("3");
                String four15PM = this.formatEventAtHour(date, "16:15", this.floorStartTime("16:15", date));
                String four30PM = this.formatEventAtHour(date, "16:30", this.floorStartTime("16:30", date));
                String four45PM = this.formatEventAtHour(date, "16:45", this.floorStartTime("16:45", date));
                String fivePM = this.formatEventAtHour(date, "17:00", this.floorStartTime("17:00", date));
                String five15PM = this.formatEventAtHour(date, "17:15", this.floorStartTime("17:15", date));
                String five30PM = this.formatEventAtHour(date, "17:30", this.floorStartTime("17:30", date));
                String five45PM = this.formatEventAtHour(date, "17:45", this.floorStartTime("17:45", date));
                String sixPM = this.formatEventAtHour(date, "18:00", this.floorStartTime("18:00", date));
                String six15PM = this.formatEventAtHour(date, "18:15", this.floorStartTime("18:15", date));
                String six30PM = this.formatEventAtHour(date, "18:30", this.floorStartTime("18:30", date));
                String six45PM = this.formatEventAtHour(date, "18:45", this.floorStartTime("18:45", date));
                String sevenPM = this.formatEventAtHour(date, "19:00", this.floorStartTime("19:00", date));
                String seven15PM = this.formatEventAtHour(date, "19:15", this.floorStartTime("19:15", date));
                String seven30PM = this.formatEventAtHour(date, "19:30", this.floorStartTime("19:30", date));
                String seven45PM = this.formatEventAtHour(date, "19:45", this.floorStartTime("19:45", date));
                String eightPM = this.formatEventAtHour(date, "20:00", this.floorStartTime("20:00", date));
                String eight15PM = this.formatEventAtHour(date, "20:15", this.floorStartTime("20:15", date));
                String eight30PM = this.formatEventAtHour(date, "20:30", this.floorStartTime("20:30", date));
                String eight45PM = this.formatEventAtHour(date, "20:45", this.floorStartTime("20:45", date));
                System.out.println("4");
                String ninePM = this.formatEventAtHour(date, "21:00", this.floorStartTime("21:00", date));
                String nine15PM = this.formatEventAtHour(date, "21:15", this.floorStartTime("21:15", date));
                String nine30PM = this.formatEventAtHour(date, "21:30", this.floorStartTime("21:30", date));
                String nine45PM = this.formatEventAtHour(date, "21:45", this.floorStartTime("21:45", date));
                String tenPM = this.formatEventAtHour(date, "22:00", this.floorStartTime("22:00", date));
                String ten15PM = this.formatEventAtHour(date, "22:15", this.floorStartTime("22:15", date));
                String ten30PM = this.formatEventAtHour(date, "22:30", this.floorStartTime("22:30", date));
                String ten45PM = this.formatEventAtHour(date, "22:45", this.floorStartTime("22:45", date));
                String elevenPM = this.formatEventAtHour(date, "23:00", this.floorStartTime("23:00", date));
                String eleven15PM = this.formatEventAtHour(date, "23:15", this.floorStartTime("23:15", date));
                String eleven30PM = this.formatEventAtHour(date, "23:30", this.floorStartTime("23:30", date));
                String eleven45PM = this.formatEventAtHour(date, "23:45", this.floorStartTime("32:45", date));

                System.out.println("5");
                model.addRow(new Object[] {sixAM, six15AM, six30AM, six45AM, sevenAM, seven15AM, seven30AM,
                    seven45AM,eightAM, eight15AM, eight30AM, eight45AM, nineAM, nine15AM, nine30AM, nine45AM
                        , tenAM, ten15AM, ten30AM, ten45AM, elevenAM, eleven15AM, eleven30AM, eleven45AM, twelvePM
                        , twelve15PM, twelve30PM, twelve45PM, onePM, one15PM, one30PM, one45PM, twoPM, two15PM, two30PM
                        , two45PM, threePM, three15PM, three30PM, three45PM, fourPM, four15PM, four30PM, four45PM,
                        fivePM, five15PM, five30PM, five45PM, sixPM, six15PM, six30PM, six45PM, sevenPM, seven15PM
                        , seven30PM, seven45PM, eightPM, eight15PM, eight30PM, eight45PM, ninePM, nine15PM, nine30PM
                        , nine45PM, tenPM, ten15PM, ten30PM, ten45PM, elevenPM, eleven15PM, eleven30PM, eleven45PM});

        return model;
    }
    
    public DefaultTableModel noLessonModel() {
        DefaultTableModel model = null;
        String n = "<html><font size = 20 color=\" black\">N/A</font></html>";
        Object columnNames[] = {n, n, n, n, n, n, n, n, n, n, n, n, n};
        model = new DefaultTableModel(columnNames, 0);
        model.addRow(new Object[] {n, n, n, n, n, n, n, n, n, n, n, n, n});
        return model;
    }
    
    public DefaultTableModel DefModel() {
        DefaultTableModel model = null;
        String l = "<html><font size = 200 color=\" black\">↻</font></html>";
        Object columnNames[] = {l, l, l, l, l, l, l, l, l, l, l, l, l, l};
        model = new DefaultTableModel(columnNames, 0);
        model.addRow(new Object[] {l, l, l, l, l, l, l, l, l, l, l, l, l, l});
        return model;
    }

    public void passToDailyPlan(String date) {
        dailyPlanForm dp = new dailyPlanForm();
        dp.setDateLabel(date);
        dp.setVisible(true);
    }
   
}