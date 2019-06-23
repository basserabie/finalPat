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
    
    private static String DATE = "";//creates a private static string that will hold the string value of the date selected by the user
    public static boolean DAY_HAS_LESSON = true;//creates a private static boolean that will indicate whether the date selected by the user has any lessons on it
    private static int COLOUR = 0;//creates a private static integer that will hold the current index of the color array that will be used to determine the color of the text in the daily planner
    private static String FIRST_START_TIME = "";//creates a private static string that will hold the string value of the first start time on the day selected
    private static String [] START_TIMES;//creates a private static string sarray that will hold the string value of the start times on the selected date
    private static String [] END_TIMES;//creates a private static string array that will hold the string value of the end times on the selected date
    private static String [] KEYS_ON_DAY;//creates a private static string array that will hold the string value of the keys of the lessons on the selected date
    private static int COUNT_SET = 0;//creates a private static integer that will hold the number of lessons added to the daily planner on the selected date
    private static String LAST_END_TIME = "";//creates a private static string that will hold the string value of the last end time on the selected date
    public static int countOnDay = 0;//creates a private static integer that will hold the number of lessons on the selected date
    
    

    public void JCalendarActionPerformed(JCalendar cal) {//creates a listener attached to the JCalendar object on the dashboard JFrame
        cal.getDayChooser().addPropertyChangeListener("day", new PropertyChangeListener() {//instantiates the listener
        @Override
        public void propertyChange(PropertyChangeEvent e) {//detects a property change to the JCalendar
            CalendarHandler ch = new CalendarHandler();//creates an object for the CalendarHandler class
            String date = ch.getFormattedDateFromJCalendar(cal.getDate().toString());//creates a string that holds the formatted date according to the date selected date from the JCalendar by the user
            ch.passToDailyPlan(date);//calls the method that passes the date to the dailyPlannerForm JFrame (escapes it from the listener)
        }//closes the property change listener
        });//closes the listener
    }//closes the method containing the listener
    
    public void setDate(String date) {//creates a method that sets the static date String to the selected date
        DATE = date;//sets the static date String to the selected date
    }//closes the setDate method
    
    public void setArrays() {//creates method that sets the static arrays of this class to the relevant data accirding to the selected date
        this.keysOnDayTest();//calls the method that sets the static array 'KEYS_ON_DAY' to an array of the keys
        if (countOnDay > 0) {//checks if the static integer countOnDay is bigger than 0 i.e. there are lessons on the selected date
            this.getStartTimesOnDate();//calls the method witch sets the static array of this class 'START_TIMES_ON_DAY' to an array of the start times on the selected date
            this.getEndTimesOnDate();////calls the method witch sets the static array of this class 'END_TIMES_ON_DAY' to an array of the end times on the selected date
            this.getFirstStartTime();////calls the method witch sets the static String of this class 'FIRST_START_TIME' to the first start time on the selected date
        }
    }//closes the setArray method
    
    public String getFormattedDateFromJCalendar(String UDate) {//creates a method that returns the formatted date selected
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        String formattedDate = "";//instantiates a string that will hold the final formatted date to be returned
        
        String UYear = UDate.substring(25);//instantiates a string holding the selected year
        String UMonth = UDate.substring(4, 7);//instantiates a string holding the selected month
        String UDay = UDate.substring(8, 10);//instantiates a string holding the selected day
        
        //checks and formats month
        String finalMonth = "";//instantiates a string that will hold the formatted month
        switch (UMonth) {//opens a switchcase statement according to the selected unformatted month
            case "Jan": //creates the case of january
                finalMonth = "01";//sets the finalMonth string to the correct format of january
                break;//breaks the january case
            case "Feb"://creates the case of february
                finalMonth = "02";//sets the finalMonth string to the correct format of february
                break;//breaks the february case
                case "Mar"://creates the case of march
                finalMonth = "03";//sets the finalMonth string to the correct format of march
                break; //breaks the march case
            case "Apr"://creates the case of april
                finalMonth = "04";//sets the finalMonth string to the correct format of april
                break; //breaks the april case
            case "May"://creates the case of may
                finalMonth = "05";//sets the finalMonth string to the correct format of may
                break; //breaks the may case
            case "Jun"://creates the case of june
                finalMonth = "06";//sets the finalMonth string to the correct format of june
                break; //breaks the june case
            case "Jul"://creates the case of july
                finalMonth = "07";//sets the finalMonth string to the correct format of july
                break; //breaks the july case
            case "Aug"://creates the case of august
                finalMonth = "08";//sets the finalMonth string to the correct format of august
                break; //breaks the august case
            case "Sep"://creates the case of september
                finalMonth = "09";//sets the finalMonth string to the correct format of september
                break; //breaks the september case
            case "Oct"://creates the case of october
                finalMonth = "10";//sets the finalMonth string to the correct format of october
                break; //breaks the october case
            case "Nov"://creates the case of november
                finalMonth = "11";//sets the finalMonth string to the correct format of november
                break; //breaks the november case
            case "Dec"://creates the case of december
                finalMonth = "12";//sets the finalMonth string to the correct format of december
                break; //breaks the december case
        }//closes the switchcase statement
        formattedDate = UYear + "/" + UDay + "/" + finalMonth;//sets the formattedDate string to the fomatted date
        return formattedDate;//return the formatted date
    }//closes the getFormattedDateFromJCalendar method
    
     public void getFirstStartTime() {//creates a method that gets the first start time on the selected day
        String startTime = this.getStartTimesOnDate()[0];//gets the first start time on the selected day
        FIRST_START_TIME = startTime;//sets the static string to the gotten first start time
    }//closes the getFirstStartTime method
     
     public void getLastEndTime() {//creates a method that gets the last end time on the selected day
         String endTime = this.getEndTimesOnDate()[this.getEndTimesOnDate().length-1];//gets the last end time on the selected day
         LAST_END_TIME = endTime;////sets the static string to the gotten last end time
     }//closes the getLastEndTime method
    
    public int getStartTimeIndexfromStartTime(String date, String startTime) {//creates a method that gets the index of an inputted start time in the static String array 'START_TIMES'
        int index = 0;//creates an integer that will store the index of the inpuuted start time and be returned
        for (int i = 0; i < START_TIMES.length; i++) {//starts a for loop iteration through the start times of the selected date
            if (START_TIMES[i].equals(startTime)) {//checks if the iterated start time is equal to the one inputted
                index = i;//sets the index integer to the index of the start time in the static start times array
            }
        }
        return index;//returns the index integer
    }//closes the getStartTimeIndexfromStartTime method

    public String floorStartTime(String attemptedTime, String date) {//creates a method that return the start time of any 15 minute time segment inputted
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        TreeSet<Date> times = new TreeSet<>();//creates a tree set to store the start times of the date selected
        String time = "";//creates a string to store the start time of the inputted segment
        
            DateFormat sdf = new SimpleDateFormat("yyy/dd/MM HH:mm");//creates a date formatter
            DateFormat sdf2 = new SimpleDateFormat("HH:mm");//creates a date formatter
            
            Calendar timeSeg = Calendar.getInstance(); //instantiates a calendar object 
            try {//opens a tryctach statement
                timeSeg.setTime(sdf.parse(date + " " + attemptedTime));//sets the time of the calendar object to the time segment inputted
            } catch (ParseException ex) {//opens the catch statemnt
                Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error setting the time of the calendar object
            }//closes the catch statement
        for (int i = 0; i < START_TIMES.length; i++) {//starts a for loop iterating through the start times of the selected date
            Calendar startTime = Calendar.getInstance(); //instantiates a Calendar object
            try {//opens a tryctach statement
                startTime.setTime(sdf.parse(date + " " + START_TIMES[i]));//sets the time of the startTime calnder object to the startTime of index i
                times.add(startTime.getTime());//adds the calendar object to the treeset of start times
            } catch (ParseException ex) {//opens the catch statemnt
                Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the user that there was an error setting the time of the calendar object
            }//closes the catch statement
        }
        //gets first starttime of day
        Calendar firstStartTime = Calendar.getInstance();//instantiates a calendar object to store the first start time of the date selected
        try {//opens the trycatch
            firstStartTime.setTime(sdf.parse(date + " " + FIRST_START_TIME));//sets the time of the calendar object to the first start time of the selected date
        } catch (ParseException ex) {//opens the catch statement
            Logger.getLogger(CalendarHandler.class.getName()).log(Level.SEVERE, null, ex);//alerts the user that there was an error setting the time of the calendar object
        }//closes the catch statement
        
        Date floor = null;//instantiates a date object to store the start time of the inputted time segment
        boolean overTime = true;//creates a boolean that will check if the time segment is after the closest end time
        if (timeSeg.getTime().toString() != null && !(timeSeg.getTime().before(firstStartTime.getTime()))) {//checks if the timeSeg is not null and if it is not before the first start time of the selected date
                if (times.floor(timeSeg.getTime()) != null) {//checks if the floor function of the treeset of start times when set against the time sgements is not null
                    floor = times.floor(timeSeg.getTime());//sets the floor date object to the result of the floor function of the start times tree set when set against the inpuuted time seg
                }
        Calendar endTime = Calendar.getInstance();//creates a calendar object to store the end time corresponding to the floor start time
        try {//opens the trycatch statement
            if (floor != null) {//checks if the floor date object is null
                endTime.setTime(sdf.parse(date + " " + END_TIMES[this.getStartTimeIndexfromStartTime(date, sdf2.format(floor.getTime()))]));//sets the time of the calendar object to the corresponding end time
                if (timeSeg.before(endTime)) {//checks if the time segment is before the gotton corresponding end time
                    overTime = false;//flips the overTime boolean to false
                }
            }
        } catch (ParseException ex) {//opens catch statement
            Logger.getLogger(CalendarHandler.class.getName()).log(Level.SEVERE, null, ex);//alerts the user that there was an error setting the time of the date object
        }//closes the catch statement
          if (floor != null && overTime == false)  {//checks if the floor date object is not null and if the over time boolean is false
            time = sdf2.format(times.floor(floor));//sets the time string to the formatted string representation of the floor date object
          } 
        }
        return time;//return the time string
    }//closes the getFloorTime method
    
    public String [] getStartTimesOnDate() {//creates a method to get the start times of the lessons of the selected date
        lessonDataArray la = new lessonDataArray();//creates an object of the lessonDataArray class
        keysArray ka = new keysArray();//creates an object of the keysArray class
        ArrayList<String> startTimes = new ArrayList<>();//creates an array list to store the start times of the selected date
        
        for (int i = 0; i < KEYS_ON_DAY.length; i++) {//starts a for loop iterating through the keys of the lessons on the selected date
            String startAttemptedTime = la.getLessonStartTimeFromLessonID(ka.getLessonIDFromKey(KEYS_ON_DAY[i]));//creates a string holding the start time of a lesson corresponding to the lesson key
            startTimes.add(startAttemptedTime);//adds the attempted start time to the start times array list
            //** please note: distinctness of the start times array is ensured by the nature of the keys array
        }
        String times [] = startTimes.toArray(new String[startTimes.size()]);//creates a string arrays representation of the sartTimes array list
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM HH:mm");//creates a simple dat formatter
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");//creates a simple date formatter
        Calendar t1 = Calendar.getInstance();//creates a calendar instance to use in sorting the times array
        Calendar t2 = Calendar.getInstance();//creates a calendar instance to use in sorting the times array
        //sorting the array:
        for (int i = 0; i < times.length-1; i++) {//starts a for loop iterating through each start time in the times array-1
            for (int k = i+1; k < times.length; k++) {//starts a for loop corresponding the the loop above for sorting
                try {//opens a trycatch statement
                    t1.setTime(sdf.parse(DATE + " " + times[i]));//sets the t1 calendar object to the start time iat index i of the times array
                    t2.setTime(sdf.parse(DATE + " " + times[k]));//sets the t2 calendar object to the start time iat index k of the times array
                } catch (ParseException ex) {//opens the catch statement
                    Logger.getLogger(CalendarHandler.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error setting the time of the calendar objects
                }//closes the catch statement
                if (t2.before(t1)) {//checks if the t2 calendar object time is beofore the t1 object
                    String temp = sdf2.format(t1.getTime());//crerates a string object temp to store the start time held in the t1 object
                    times[i] = times[k];//sets the start time at index i of the array to the start time at index k of the array
                    times[k] = temp;//sets the start time at index k of the array to the start time string representation stored in the temp string variable
                }
            }
        }
        START_TIMES = times;//sets the class static string array of start times to the times array of start times
        return times;//returns the times array
    }//closes the getStartTimesOnDate method
    
    public String [] getEndTimesOnDate() {//creates a method to get the end times of the lessons of the selected date
        lessonDataArray la = new lessonDataArray();//creates an object of the lessonDataArray class
        keysArray ka = new keysArray();//creates an object of the keysArray class
        ArrayList<String> endTimes = new ArrayList<>();//creates an array list to store the end times of the selected date
        
        for (int i = 0; i < KEYS_ON_DAY.length; i++) {//starts a for loop iterating through the keys of the lessons on the selected date
            String endAttemptedTime = la.getLessonEndTimeFromLessonID(ka.getLessonIDFromKey(KEYS_ON_DAY[i]));//creates a string holding the end time of a lesson corresponding to the lesson key
            endTimes.add(endAttemptedTime);//adds the attempted end time to the start times array list
            //** please note: distinctness of the end times array is ensured by the nature of the keys array
        }
        String times [] = endTimes.toArray(new String[endTimes.size()]);//creates a string arrays representation of the endTimes array list
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM HH:mm");//creates a simple dat formatter
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");//creates a simple dat formatter
        Calendar t1 = Calendar.getInstance();//creates a calendar instance to use in sorting the times array
        Calendar t2 = Calendar.getInstance();//creates a calendar instance to use in sorting the times array
        for (int i = 0; i < times.length-1; i++) {//starts a for loop iterating through each end time in the times array-1
            for (int k = i+1; k < times.length; k++) {//starts a for loop corresponding the the loop above for sorting
                try {//opens a trycatch statement
                    t1.setTime(sdf.parse(DATE + " " + times[i]));//sets the t1 calendar object to the end time iat index i of the times array
                    t2.setTime(sdf.parse(DATE + " " + times[k]));//sets the t2 calendar object to the end time iat index k of the times array
                } catch (ParseException ex) {//opens the catch statement
                    Logger.getLogger(CalendarHandler.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error setting the time of the calendar objects
                }//closes the catch statement
                if (t2.before(t1)) {//checks if the t2 calendar object time is beofore the t1 object
                    String temp = sdf2.format(t1.getTime());//crerates a string object temp to store the end time held in the t1 object
                    times[i] = times[k];//sets the start time at index i of the array to the end time at index k of the array
                    times[k] = temp;//sets the end time at index k of the array to the end time string representation stored in the temp string variable
                }
            }
        }
        END_TIMES = times;//sets the class static string array of end times to the times array of end times
        return times;//returns the times array
    }//closes the getEndTimesOnDate method
    
    public String getLessonDataOnThatDateAndTime(String date, String timeInputted) {//creates a method that formatts the output of the data regarding a selected lesson on a selected date from the daily planner form
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        keysArray ka = new keysArray();//creates an object for the keysArray class
        String lessonsOnDayData = "Youre lesson at this time is:\n\n";//creates a string that will sore the final structure of the lesson data display (and adds the opeing message)
        String lessonIntro = "";//creates a string that will store the display of the frequency of the selected lesson
        String timeRef = "";//creates a string that will store the time of the selected lesson
        
        if (this.TimeHasLesson(date, timeInputted)) {//checks if there is a startTime to the selected time and assigns that startTime to timeRef
            timeRef = this.floorStartTime(timeInputted, date);//assigns start time to timeRef string
        }
        if (!timeRef.equals("")) {//checks if the timeRef string is not null i.e no lesson at the time selected
            String time = "";//creates a string that will sotre the time of the selected lesson
            String venue = "";//creates a string that will store the venue of the selected lesson
            String students = "";//creates a string that will store a list of the students attending the selected lesson
            //populates lessonsOnDayData with the data for all of the lessons on the selected date
                String key = ka.getKeyFromDateAndTime(date, timeRef);//creates a string holding the key of the selected lesson
                switch (la.getFrequencyFromKey(key)) {//creates a switchcase statement according to the frequency of the selected lesson
                    case "once-off"://starts the once-off case
                        lessonIntro = "This is a once-off lesson:\n";//sets the lessonIntro string to display the once-off nature of the selected lesson
                        break;//breaks from the once-off case
                    case "weekly"://starts the weekly case
                        lessonIntro = "This is a weekly lesson:\n";//sets the lessonIntro string to display the weekly nature of the selected lesson
                        break;//breaks from the weekly case
                    case "monthly"://starts the monthly case
                        lessonIntro = "This is a monthly lesson:\n";//sets the lessonIntro string to display the monthly nature of the selected lesson
                        break;//breaks from the monthly case
                }//closes the switchcase statement
            time = "Time: " + this.timeFromLessonKey(key) + "\n";//sets the time string tot eh time of the selected lesson
            venue = "Venue: " + this.venueFromLessonKey(key) + "\n";//sets the venue string to the venue of the selected lesson
            students = "Student(s): " + this.studentsStringFromArray(this.studentsFromLessonDateAndTime(date, this.StartTimeFromLessonKey(key)));//sets the students string to the list of students attending the selected lesson
            lessonsOnDayData += lessonIntro + time + venue + students + "\n";//formats the final string to be displayed to display the above gotten data
        } else {//if there is no lesson at the selected time
            lessonsOnDayData = "You ave no lesson at this time! :) :)";//formats the final display to alert the user that there are no lessons booked at the selected time
        }
        
       return  lessonsOnDayData;//returns the formatted lessonsOnDayData string
    }//closes the getLessonDataOnThatDateAndTime method
    
    public String studentsStringFromArray(String [] studentsArray) {//creates a method to format the display of the students attending the selected lesson
        String students = "";//creates a string that will store the formatted list of the attending students
        for (int i = 0; i < studentsArray.length; i++) {//starts a for loop iterating through each student in the students array
            students += studentsArray[i] + "\n                  ";//adds the formatted event of each student to the final student's string
        }
        return students;//returns the formatted students string
    }//closes the studentsStringFromArray method
    
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