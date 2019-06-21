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
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    private static String DATE = "";
    public static boolean DAY_HAS_LESSON = true;
    private static int COLOUR = 0;
    private static String FIRST_START_TIME = "";
    private static String [] START_TIMES;
    private static String [] END_TIMES;
    private static String [] KEYS_ON_DAY;
    private static int COUNT_SET = 0;
    private static String LAST_END_TIME = "";
    public static int countOnDay = 0;
    
    

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
    
    public void setDate(String date) {
        DATE = date;
    }
    
    public void setArrays() {
        this.keysOnDayTest();
        System.out.println("count on day: " + countOnDay);
        for (int i = 0; i < KEYS_ON_DAY.length; i++) {
            System.out.println(KEYS_ON_DAY[i]);
        }
        if (countOnDay > 0) {
            this.getStartTimesOnDate();
            this.getEndTimesOnDate();
            this.getFirstStartTime();
        }
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
    
     public void getFirstStartTime() {
        String startTime = this.getStartTimesOnDate()[0];
        FIRST_START_TIME = startTime;
    }
     
     public void getLastEndTime() {
         String endTime = this.getEndTimesOnDate()[this.getEndTimesOnDate().length-1];
         LAST_END_TIME = endTime;
     }
    
    public int getStartTimeIndexfromStartTime(String date, String startTime) {
        int index = 0;
        for (int i = 0; i < START_TIMES.length; i++) {
            if (START_TIMES[i].equals(startTime)) {
                index = i;
            }
        }
        return index;
    }
    //TODO: fix to include first seg of lesson!
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
        for (int i = 0; i < START_TIMES.length; i++) {
            //create a date fromatter
            Calendar startTime = Calendar.getInstance(); // adds instance to timeIn
            try {
                startTime.setTime(sdf.parse(date + " " + START_TIMES[i])); 
                times.add(startTime.getTime());
            } catch (ParseException ex) {
                Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //gets first starttime of day
        Calendar firstStartTime = Calendar.getInstance();
        try {
            firstStartTime.setTime(sdf.parse(date + " " + FIRST_START_TIME));
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
        try {
            if (floor != null) {
                endTime.setTime(sdf.parse(date + " " + END_TIMES[this.getStartTimeIndexfromStartTime(date, sdf2.format(floor.getTime()))]));
                if (timeSeg.before(endTime)) {
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
    
    public String [] getStartTimesOnDate() {
        lessonDataArray la = new lessonDataArray();
        keysArray ka = new keysArray();
        ArrayList<String> startTimes = new ArrayList<>();
        for (int i = 0; i < KEYS_ON_DAY.length; i++) {
            String startAttemptedTime = la.getLessonStartTimeFromLessonID(ka.getLessonIDFromKey(KEYS_ON_DAY[i]));
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
                    t1.setTime(sdf.parse(DATE + " " + times[i]));
                    t2.setTime(sdf.parse(DATE + " " + times[k]));
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
        START_TIMES = times;
        return times;
    }
    
    public String [] getEndTimesOnDate() {
        lessonDataArray la = new lessonDataArray();
        keysArray ka = new keysArray();
        ArrayList<String> endTimes = new ArrayList<>();
        
        for (int i = 0; i < KEYS_ON_DAY.length; i++) {
            String endAttemptedTime = la.getLessonEndTimeFromLessonID(ka.getLessonIDFromKey(KEYS_ON_DAY[i]));
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
                    t1.setTime(sdf.parse(DATE + " " + times[i]));
                    t2.setTime(sdf.parse(DATE + " " + times[k]));
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
        END_TIMES = times;
        return times;
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
        for (int i = 0; i < KEYS_ON_DAY.length; i++) {
            if (i == index) {
                key = KEYS_ON_DAY[i];
            }
        }
        return key;
    }
    
    public String [] keysOnDayTest() {
        ConnectDB db = new ConnectDB();
        lessonDataArray la = new lessonDataArray();
        keysArray ka = new keysArray();
        la.sortArray();
        ArrayList<String> keys = new ArrayList<>();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/dd/MM");
        Calendar time = Calendar.getInstance();
        Calendar refTime = Calendar.getInstance();
        String getDates = "SELECT DISTINCT lessonKey " +"FROM lessonData, lessonKeys " +"WHERE lessonDate = '" + DATE + "' AND lessonData.LessonID = lessonKeys.lessonID";
        ResultSet rKeys = db.getResults(getDates);
        try {
            while(rKeys.next()) { 
               keys.add(rKeys.getString("lessonKey"));
               countOnDay++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CalendarHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Calendar p1 = Calendar.getInstance();
        Calendar p2 = Calendar.getInstance();
        
        for (int i = 0; i < keys.size()-1; i++) {
            for (int k =i+1; k < keys.size(); k++) {
                try {
                    p1.setTime(sdf.parse(ka.getDateFromKey(keys.get(i)) + " " + ka.getStartTimeFromKey(keys.get(i))));
                    p2.setTime(sdf.parse(ka.getDateFromKey(keys.get(k)) + " " + ka.getStartTimeFromKey(keys.get(k))));
                } catch (ParseException ex) {
                    Logger.getLogger(CalendarHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                String temp;
                if (p2.after(p1)) {
                    temp = keys.get(k);
                    keys.set(k, keys.get(i));
                    keys.set(i, temp);
                }
            }
        }
        String keysArray [] = keys.toArray(new String[keys.size()]);
        KEYS_ON_DAY = keysArray;
        return keysArray;
        
    }
    
//    public String [] keysOnDay() {
//        System.out.println("getting keys");
//        lessonDataArray la = new lessonDataArray();
//        keysArray ka = new keysArray();
//        ka.sortArray();
//        la.sortArray();
//        ArrayList<String> keys = new ArrayList<>();
//        
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM HH:mm");
//        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/dd/MM");
//        Calendar time = Calendar.getInstance();
//        Calendar refTime = Calendar.getInstance();
//        int s = 0;
//        try {
//            time.setTime(sdf2.parse(la.getLessonDataArray().get(s).getLessonDate()));
//            refTime.setTime(sdf2.parse(DATE));
//            System.out.println("firstTime: " + sdf2.format(time.getTime()) + "      date: " + sdf2.format(refTime.getTime()));
//        } catch (ParseException ex) {
//            Logger.getLogger(CalendarHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        while(!(time.after(refTime))) {
//            try {
//                time.setTime(sdf2.parse(la.getLessonDataArray().get(s).getLessonDate()));
//            } catch (ParseException ex) {
//                Logger.getLogger(CalendarHandler.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            if (la.getLessonDataArray().get(s).getLessonDate().equals(DATE)) {
//                if (!keys.contains(ka.getKeyFromLessonID(la.getLessonDataArray().get(s).getLessonID()))) {
//                    keys.add(ka.getKeyFromLessonID(la.getLessonDataArray().get(s).getLessonID()));
//                }
//            }
//            s++;
//        }
//        
//        Calendar p1 = Calendar.getInstance();
//        Calendar p2 = Calendar.getInstance();
//        
//        for (int i = 0; i < keys.size()-1; i++) {
//            for (int k =i+1; k < keys.size(); k++) {
//                try {
//                    p1.setTime(sdf.parse(ka.getDateFromKey(keys.get(i)) + " " + ka.getStartTimeFromKey(keys.get(i))));
//                    p2.setTime(sdf.parse(ka.getDateFromKey(keys.get(k)) + " " + ka.getStartTimeFromKey(keys.get(k))));
//                } catch (ParseException ex) {
//                    Logger.getLogger(CalendarHandler.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                String temp;
//                if (p2.after(p1)) {
//                    temp = keys.get(k);
//                    keys.set(k, keys.get(i));
//                    keys.set(i, temp);
//                }
//            }
//        }
//        String keysArray [] = keys.toArray(new String[keys.size()]);
//        KEYS_ON_DAY = keysArray;
//        return keysArray;
//    }
    
    public String StartTimeFromLessonKey(String key) {
        lessonDataArray la = new lessonDataArray();
        keysArray ka = new keysArray();
        String time = "";
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {
            if (ka.getKeyFromLessonID(la.getLessonDataArray().get(i).getLessonID()).equals(key)) {
                time = la.getLessonStartTimeFromLessonID(la.getLessonDataArray().get(i).getLessonID()); 
                break;
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
                break;
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
                break;
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
        
        if (COUNT_SET <= KEYS_ON_DAY.length) {
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
            for (int i = 0; i < KEYS_ON_DAY.length; i++) {
                Calendar refStartTime = Calendar.getInstance();
                Calendar refEndTime = Calendar.getInstance();
                try {
                    refStartTime.setTime(sdf.parse(date + " " + START_TIMES[i]));
                        refEndTime.setTime(sdf.parse(date + " " + END_TIMES[i]));
                } catch (ParseException ex) {
                    Logger.getLogger(CalendarHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (attemptedStartTime.equals(refStartTime) ||
                        attemptedEndTime.equals(refEndTime) ||
                        attemptedStartTime.after(refStartTime) && attemptedEndTime.before(refEndTime)) {
                    segHasLessonBooked = true;
                } else {
                    if (attemptedStartTime.equals(refEndTime)) {
                        COUNT_SET++;
                    }
                }
            }
        }
        return segHasLessonBooked;
    }
    
    public String formatEventAtHour(String date, String time, String startTime) {
        lessonDataArray la = new lessonDataArray();
        
        String lessonDataEventFiller = "";
        boolean after = false;
        if (this.TimeHasLesson(date, time)) {
            DateFormat sdf = new SimpleDateFormat("yyy/dd/MM HH:mm");
            DateFormat sdf2 = new SimpleDateFormat("HH:mm");
            String colours [] = {"red", "blue", "green", "pink", "purple", "yellow", "orange"};
            String colour = colours[COLOUR];
            
            String endTime = "";
            for (int i = 0; i < la.getLessonDataArray().size(); i++) {
                if (la.getLessonDataArray().get(i).getLessonDate().equals(date) && la.getLessonDataArray().get(i).getLessonTime().equals(startTime)) {
                    endTime = la.getEndTime(la.getLessonDataArray().get(i).getLessonTime(), la.getLessonDataArray().get(i).getLessonDuration());
                    break;
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
        } else {
            return "";
        }
        
        return lessonDataEventFiller;
    }
     public void LessonsOnDay() {
         if (countOnDay == 0) {
             System.out.println("entered lessonHas no day: " + KEYS_ON_DAY.length);
             DAY_HAS_LESSON = false;
         } else {
             DAY_HAS_LESSON = true;
         }
     }
     
     public String formatTime(int iterate) {
         String ts = "05:45";
         DateFormat sdf = new SimpleDateFormat("HH:mm");
         
         Calendar time = Calendar.getInstance();
        try {
            time.setTime(sdf.parse(ts));
            for (int i = 0; i <= iterate; i++) {
                time.add(Calendar.MINUTE, 15);
            }
        } catch (ParseException ex) {
            Logger.getLogger(CalendarHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        String StringTime = sdf.format(time.getTime());
        return StringTime;
     }
     
     public DefaultTableModel schedModel(String date) {
         DefaultTableModel model = null;
        lessonDataArray la = new lessonDataArray();
        keysArray ka = new keysArray();
//        ka.sortArray();
        
            Object columnNames[] = {"06:00", "06:15", "06:30", "06:45", "07:00", "07:15", "07:30", "07:45", "08:00",
                "08:15", "08:30", "08:45", "09:00", "09:15", "09:30", "09:45", "10:00", "10:15", "10:30", "10:45",
                    "11:00", "11:15", "11:30", "11:45", "12:00", "12:15", "12:30", "12:45", "13:00", "13:15", "13:30",
                    "13:45", "14:00", "14:15", "14:30", "14:45", "15:00", "15:15", "15:30", "15:45", "16:00", "16:15",
                    "16:30", "16:45", "17:00", "17:15", "17:30", "17:45", "18:00", "18:15", "18:30", "18:45", "19:00",
                    "19:15", "19:30", "19:45", "20:00", "20:15", "20:30", "20:45", "21:00", "21:15", "21:30", "21:45",
                    "22:00", "22:15", "22:30", "22:45", "23:00", "23:15", "23:30", "23:45"};
            
            
            model = new DefaultTableModel(columnNames, 0);
            
            DateFormat sdf = new SimpleDateFormat("HH:mm");
            this.getLastEndTime();
            String segEvent [] = new String[71];
            Calendar LET = Calendar.getInstance();
            Calendar segTime = Calendar.getInstance();
        try {
            LET.setTime(sdf.parse(LAST_END_TIME));
        } catch (ParseException ex) {
            Logger.getLogger(CalendarHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < 71; i++) {
            String seg = this.formatTime(i);
             try {
                 segTime.setTime(sdf.parse(seg));
             } catch (ParseException ex) {
                 Logger.getLogger(CalendarHandler.class.getName()).log(Level.SEVERE, null, ex);
             }
             if (segTime.before(LET)) {
                 segEvent[i] = this.formatEventAtHour(date, seg, this.floorStartTime(seg, date));
             } else {
                 segEvent[i] = "";
             }
        }
        
        model.addRow(segEvent);
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