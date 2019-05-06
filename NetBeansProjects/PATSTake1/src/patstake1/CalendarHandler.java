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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import static net.ucanaccess.converters.Functions.date;

/**
 *
 * @author YishaiBasserabie
 */
public class CalendarHandler {
    
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
    
    public int getIndexOfEndTimeFromTartTime(String date, String startTime) {
        int index = 0;
        for (int i = 0; i < this.getStartTimesOnDate(date).length; i++) {
            DateFormat sdf = new SimpleDateFormat("yyy/dd/MM HH:mm");
            Calendar start = Calendar.getInstance(); // adds instance to cal
            Calendar nextStart = Calendar.getInstance();
            Calendar end = Calendar.getInstance();
            if (i < this.getStartTimesOnDate(date).length-1) {
                try {
                    start.setTime(sdf.parse(startTime)); 
                    end.setTime(sdf.parse(date + " " + this.getEndTimesOnDate(date)[i]));
                    nextStart.setTime(sdf.parse(date + " " + this.getStartTimesOnDate(date)[i+1]));
                } catch (ParseException ex) {
                    Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (end.after(start) && end.before(nextStart)) {
                    index = i;
                    break;
                }
            } else {
                index = i;
            }
        }
        System.out.println("endTime of floor lesson: " + this.getEndTimesOnDate(date)[index]);
        return index;
    }
    
    public String CielingStartTime(String attemptedTime, String date) {
        lessonDataArray la = new lessonDataArray();
        TreeSet<Date> times = new TreeSet<> ();
        String time = "";
        
        //create a date fromatter
            DateFormat sdf = new SimpleDateFormat("yyy/dd/MM HH:mm");
            DateFormat sdf2 = new SimpleDateFormat("HH:mm");
            Calendar timeObject = Calendar.getInstance(); // adds instance to cal
            try {
                timeObject.setTime(sdf.parse(date + " " + attemptedTime));
                
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
        
        boolean overTime = true;    
        Date floor = times.floor(timeObject.getTime());
        
        Calendar endTime = Calendar.getInstance();
        Calendar checkTime = Calendar.getInstance();
        try {
            endTime.setTime(sdf2.parse(this.getEndTimesOnDate(date)[this.getIndexOfEndTimeFromTartTime(date, sdf.format(floor.getTime()))]));
            checkTime.setTime(sdf2.parse(sdf2.format(timeObject.getTime())));
            if (checkTime.before(endTime) && floor != null) {
                overTime = false;
            }
        } catch (ParseException ex) {
            Logger.getLogger(CalendarHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (floor != null && overTime == false)  {
           time = sdf2.format(floor);
        }
        System.out.println("startTime of selected: " + time);
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
        String lessonsOnDayData = "Youre lessons on this day are:\n\n";
        String lessonIntro = "";
        
        String timeRef = this.CielingStartTime(timeInputted, date);
        
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
        boolean keyAlreadyIn = false;
        ArrayList<String> keys = new ArrayList<>();
        
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
                refStartTime.setTime(sdf.parse(date + " " + startsBooked.get(i)));
                refEndTime.setTime(sdf.parse(date + " " + endsBooked.get(i)));
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
    
    public String formatEventAtHour(String date, String time) {
        String lessonDataEventFiller = "";
        if (this.TimeHasLesson(date, time)) {
            lessonDataEventFiller = "---------";
        }
        return lessonDataEventFiller;
    }
    
    public DefaultTableModel selectedDateModel(String date) {
        DefaultTableModel model = null;
         
         Object columnNames[] = {"06:00", "06:15", "06:30", "06:45", "07:00", "07:15", "07:30", "07:45", "08:00",
             "08:15", "08:30", "08:45", "09:00", "09:15", "09:30", "09:45", "10:00", "10:15", "10:30", "10:45",
                 "11:00", "11:15", "11:30", "11:45", "12:00", "12:15", "12:30", "12:45", "13:00", "13:15", "13:30",
                 "13:45", "14:00", "14:15", "14:30", "14:45", "15:00", "15:15", "15:30", "15:45", "16:00", "16:15",
                 "16:30", "16:45", "17:00", "17:15", "17:30", "17:45", "18:00", "18:15", "18:30", "18:45", "19:00",
                 "19:15", "19:30", "19:45", "20:00", "20:15", "20:30", "20:45", "21:00", "21:15", "21:30", "21:45",
                 "22:00", "22:15", "22:30", "22:45", "23:00", "23:15", "23:30", "23:45", "24:00", "24:15", "24:30", "24:45"};
         model = new DefaultTableModel(columnNames, 0);
         
             String sixAM = this.formatEventAtHour(date, "06:00");
             String six15AM = this.formatEventAtHour(date, "06:15");
             String six30AM = this.formatEventAtHour(date, "06:30");
             String six45AM = this.formatEventAtHour(date, "06:45");
             String sevenAM = this.formatEventAtHour(date, "07:00");
             String seven15AM = this.formatEventAtHour(date, "07:15");
             String seven30AM = this.formatEventAtHour(date, "07:30");
             String seven45AM = this.formatEventAtHour(date, "07:45");
             String eightAM = this.formatEventAtHour(date, "08:00");
             String eight15AM = this.formatEventAtHour(date, "08:15");
             String eight30AM = this.formatEventAtHour(date, "08:30");
             String eight45AM = this.formatEventAtHour(date, "08:45");
             String nineAM = this.formatEventAtHour(date, "09:00");
             String nine15AM = this.formatEventAtHour(date, "09:15");
             String nine30AM = this.formatEventAtHour(date, "09:30");
             String nine45AM = this.formatEventAtHour(date, "09:45");
             String tenAM = this.formatEventAtHour(date, "10:00");
             String ten15AM = this.formatEventAtHour(date, "10:15");
             String ten30AM = this.formatEventAtHour(date, "10:30");
             String ten45AM = this.formatEventAtHour(date, "10:45");
             String elevenAM = this.formatEventAtHour(date, "11:00");
             String eleven15AM = this.formatEventAtHour(date, "11:15");
             String eleven30AM = this.formatEventAtHour(date, "11:30");
             String eleven45AM = this.formatEventAtHour(date, "11:45");
             String twelvePM = this.formatEventAtHour(date, "12:00");
             String twelve15PM = this.formatEventAtHour(date, "12:15");
             String twelve30PM = this.formatEventAtHour(date, "12:30");
             String twelve45PM = this.formatEventAtHour(date, "12:45");
             String onePM = this.formatEventAtHour(date, "13:00");
             String one15PM = this.formatEventAtHour(date, "13:15");
             String one30PM = this.formatEventAtHour(date, "13:30");
             String one45PM = this.formatEventAtHour(date, "13:45");
             String twoPM = this.formatEventAtHour(date, "14:00");
             String two15PM = this.formatEventAtHour(date, "14:15");
             String two30PM = this.formatEventAtHour(date, "14:30");
             String two45PM = this.formatEventAtHour(date, "14:45");
             String threePM = this.formatEventAtHour(date, "15:00");
             String three15PM = this.formatEventAtHour(date, "15:15");
             String three30PM = this.formatEventAtHour(date, "15:30");
             String three45PM = this.formatEventAtHour(date, "15:45");
             String fourPM = this.formatEventAtHour(date, "16:00");
             String four15PM = this.formatEventAtHour(date, "16:15");
             String four30PM = this.formatEventAtHour(date, "16:30");
             String four45PM = this.formatEventAtHour(date, "16:45");
             String fivePM = this.formatEventAtHour(date, "17:00");
             String five15PM = this.formatEventAtHour(date, "17:15");
             String five30PM = this.formatEventAtHour(date, "17:30");
             String five45PM = this.formatEventAtHour(date, "17:45");
             String sixPM = this.formatEventAtHour(date, "18:00");
             String six15PM = this.formatEventAtHour(date, "18:15");
             String six30PM = this.formatEventAtHour(date, "18:30");
             String six45PM = this.formatEventAtHour(date, "18:45");
             String sevenPM = this.formatEventAtHour(date, "19:00");
             String seven15PM = this.formatEventAtHour(date, "19:15");
             String seven30PM = this.formatEventAtHour(date, "19:30");
             String seven45PM = this.formatEventAtHour(date, "19:45");
             String eightPM = this.formatEventAtHour(date, "20:00");
             String eight15PM = this.formatEventAtHour(date, "20:15");
             String eight30PM = this.formatEventAtHour(date, "20:30");
             String eight45PM = this.formatEventAtHour(date, "20:45");
             String ninePM = this.formatEventAtHour(date, "21:00");
             String nine15PM = this.formatEventAtHour(date, "21:15");
             String nine30PM = this.formatEventAtHour(date, "21:30");
             String nine45PM = this.formatEventAtHour(date, "21:45");
             String tenPM = this.formatEventAtHour(date, "22:00");
             String ten15PM = this.formatEventAtHour(date, "22:15");
             String ten30PM = this.formatEventAtHour(date, "22:30");
             String ten45PM = this.formatEventAtHour(date, "22:45");
             String elevenPM = this.formatEventAtHour(date, "23:00");
             String eleven15PM = this.formatEventAtHour(date, "23:15");
             String eleven30PM = this.formatEventAtHour(date, "23:30");
             String eleven45PM = this.formatEventAtHour(date, "23:45");
             String twelveAM = this.formatEventAtHour(date, "24:00");
             String twelve15AM = this.formatEventAtHour(date, "24:15");
             String twelve30AM = this.formatEventAtHour(date, "24:30");
             String twelve45AM = this.formatEventAtHour(date, "24:45");
             
             model.addRow(new Object[] {sixAM, six15AM, six30AM, six45AM, sevenAM, seven15AM, seven30AM,
                 seven45AM,eightAM, eight15AM, eight30AM, eight45AM, nineAM, nine15AM, nine30AM, nine45AM
                     , tenAM, ten15AM, ten30AM, ten45AM, elevenAM, eleven15AM, eleven30AM, eleven45AM, twelvePM
                     , twelve15PM, twelve30PM, twelve45PM, onePM, one15PM, one30PM, one45PM, twoPM, two15PM, two30PM
                     , two45PM, threePM, three15PM, three30PM, three45PM, fourPM, four15PM, four30PM, four45PM,
                     fivePM, five15PM, five30PM, five45PM, sixPM, six15PM, six30PM, six45PM, sevenPM, seven15PM
                     , seven30PM, seven45PM, eightPM, eight15PM, eight30PM, eight45PM, ninePM, nine15PM, nine30PM
                     , nine45PM, tenPM, ten15PM, ten30PM, ten45PM, elevenPM, eleven15PM, eleven30PM, eleven45PM,
                     twelveAM, twelve15PM, twelve30PM, twelve45PM});
             
        return model;
    }

    public void passToDailyPlan(String date) {
        dailyPlanForm dp = new dailyPlanForm();
        dp.setDateLabel(date);
        dp.setVisible(true);
    }
    
}
