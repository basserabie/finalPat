/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patstake1;

import com.toedter.calendar.JCalendar;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
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
            JOptionPane.showMessageDialog(null, ch.getLessonDataOnThatDate(ch.getFormattedDateFromJCalendar(cal.getDate().toString())));
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
    
    
    public String getLessonDataOnThatDate(String date) {
        lessonDataArray la = new lessonDataArray();
        String lessonsOnDayData = "Youre lessons on this day are:\n\n";
        String lessonIntro = "";
        
        if (this.NumberOfLessonsOnDay(date) > 0) {
            String time = "";
            String venue = "";
            String students = "";
            //populates lessonsOnDayData with the data for all of the lessons on the selected date
            for (int i = 0; i < this.NumberOfLessonsOnDay(date); i++) {
                String key = this.getKeyFromPositionInDayAndDate(i, date);
                if (la.getFrequencyFromKey(key).equals("once-off")) {
                    lessonIntro = "This is a once-off lesson:\n";
                    time = "Time: " + this.timeFromLessonKey(key) + "\n";
                    venue = "Venue: " + this.venueFromLessonKey(key) + "\n";
                    students = "Student(s): " + this.studentsStringFromArray(this.studentsFromLessonKey(date, this.StartTimeFromLessonKey(key)));
                    lessonsOnDayData += lessonIntro + time + venue + students + "\n";
                } else {
                    if (la.getFrequencyFromKey(key).equals("weekly")) {
                        lessonIntro = "This is a weekly lesson:\n";
                        time = "Time: " + this.timeFromLessonKey(key) + "\n";
                        venue = "Venue: " + this.venueFromLessonKey(key) + "\n";
                        students = "Student(s): " + this.studentsStringFromArray(this.studentsFromLessonKey(date, this.StartTimeFromLessonKey(key)));
                        lessonsOnDayData += lessonIntro + time + venue + students + "\n";
                    } else {
                        if (la.getFrequencyFromKey(key).equals("monthly")) {
                            lessonIntro = "This is a monthly lesson:\n";
                            time = "Time: " + this.timeFromLessonKey(key) + "\n";
                            venue = "Venue: " + this.venueFromLessonKey(key) + "\n";
                            students = "Student(s): " + this.studentsStringFromArray(this.studentsFromLessonKey(date, this.StartTimeFromLessonKey(key)));
                            lessonsOnDayData += lessonIntro + time + venue + students + "\n";
                        }
                    }
                }
            }
        } else {
            lessonsOnDayData = "you have no lessons on this day!";
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
    
    public String [] studentsFromLessonKey(String date, String time) {
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
    
//    public boolean HourHasLesson(String hour) {
//        boolean HourHasLessonBooked = false;
//        ArrayList<Integer> hoursBooked = new ArrayList<>();
//        
//        //loop runs through all the hours of the day
//        for (int i = 0; i < 19; i++) {
//            if ()
//        }
//    }
//    
//    public String formatEventAtHour(String date, String hour) {
//        String lessonDataEventFiller = "";
//        
//        if ()
//    }
    
//    public DefaultTableModel selectedDateModel(String date) {
//         DefaultTableModel model = null;
//         
//         Object columnNames[] = {"06:00", "07:00", "08:00" + "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00"};
//         model = new DefaultTableModel(columnNames, 0);
//         
//             String sixAM = this.formatEventAtHour(date, "06:00");
//             String sevenAM = this.formatEventAtHour(date, "07:00");
//             String eightAM = this.formatEventAtHour(date, "08:00");
//             String nineAM = this.formatEventAtHour(date, "09:00");
//             String tenAM = this.formatEventAtHour(date, "10:00");
//             String elevenAM = this.formatEventAtHour(date, "11:00");
//             String twelvePM = this.formatEventAtHour(date, "12:00");
//             String onePM = this.formatEventAtHour(date, "13:00");
//             String twoPM = this.formatEventAtHour(date, "14:00");
//             String threePM = this.formatEventAtHour(date, "15:00");
//             String fourPM = this.formatEventAtHour(date, "16:00");
//             String fivePM = this.formatEventAtHour(date, "17:00");
//             String sixPM = this.formatEventAtHour(date, "18:00");
//             String sevenPM = this.formatEventAtHour(date, "19:00");
//             String eightPM = this.formatEventAtHour(date, "20:00");
//             String ninePM = this.formatEventAtHour(date, "21:00");
//             String tenPM = this.formatEventAtHour(date, "22:00");
//             String elevenPM = this.formatEventAtHour(date, "23:00");
//             String twelveAM = this.formatEventAtHour(date, "24:00");
//             
//             model.addRow(new Object[] {sixAM, sevenAM, eightAM, nineAM, tenAM, elevenAM, twelvePM, onePM, twoPM, threePM, fourPM, fivePM, sixPM, sevenPM, eightPM, ninePM, tenPM, elevenPM, twelveAM});
//             
//             
//        return model;
//    }
    
}
