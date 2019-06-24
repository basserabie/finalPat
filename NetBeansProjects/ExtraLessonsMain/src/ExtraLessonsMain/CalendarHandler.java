/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtraLessonsMain;

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
public class CalendarHandler {//creates a class called CalendarHandler to handler and procces the JCalendar object and the dailyPlanForm Table
    
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
    
    public String studentsStringFromArrayForParent(String [] studentsArray) {//creates a method to get a formatted string of students according to an inputted array of students
        String students = "";//creates a string to hold the formatted string of students and be returned
        for (int i = 0; i < studentsArray.length; i++) {//starts a for loop iterating through the students array
            students += studentsArray[i] + ", ";//adds a formatted event of a student to the students string
        }
        return students;//returns the students string
    }//closes the studentsStringFromArrayForParent method
    
//    public String getKeyFromPositionInDayAndDate(int index, String date) {//creates a method to get the key from the position of the associated lesson on the selected date
//        String key = "";//creates a string that will store the gotten key and will be returned
//        for (int i = 0; i < KEYS_ON_DAY.length; i++) {//starts a for loop iterating through the key array
//            if (i == index) {//checks if i is equal to the passed in index
//                key = KEYS_ON_DAY[i];//sets the key string to the key at the index if i;
//            }
//        }
//        return key;
//    }
    
    public String [] keysOnDayTest() {//creates a method that gets the keys of the lessons on the selected date
        ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        keysArray ka = new keysArray();//creates an object for the keysArray class
        la.sortArray();//sorts the lessonDataArray lesson data
        ArrayList<String> keys = new ArrayList<>();//creates an array list to store the keys on the selected date
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM HH:mm");//creates a date formatter
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/dd/MM");//creates a date formatter
        Calendar time = Calendar.getInstance();//creates a calendar instance
        Calendar refTime = Calendar.getInstance();//creates a calendar instance
        String getDates = "SELECT DISTINCT lessonKey " +"FROM lessonData, lessonKeys " +"WHERE lessonDate = '" + DATE + "' AND lessonData.LessonID = lessonKeys.lessonID";//creates a string to store the SQL query used to assertain the distinct keys of the lessons on the selected day
        ResultSet rKeys = db.getResults(getDates);//creates a resultSet to store the results of the above query
        try {//opens a trycatch statement
            while(rKeys.next()) {//starts a while loop iterating through the results of the resultSet
               keys.add(rKeys.getString("lessonKey"));//adds the lesson key in the resultSet to the keys array list
               countOnDay++;//ups the count of countOnDay i.e for every lesson key added to the list, there is another lesson on the day
            }
        } catch (SQLException ex) {//opens the catch statement
            Logger.getLogger(CalendarHandler.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error fetching the resultset' next record
        }//coloses the catch statement
        
        Calendar p1 = Calendar.getInstance();//creates a calendar object
        Calendar p2 = Calendar.getInstance();//creates a calendar object
        //sorting the key array list according to the date of the associated lesson
        for (int i = 0; i < keys.size()-1; i++) {//starts a for loop iterating through the keys array list-1
            for (int k =i+1; k < keys.size(); k++) {//starts a for loop iterating through the keys on the selected day corresponding to the above for loop
                try {//opens a trycatch statement
                    p1.setTime(sdf.parse(ka.getDateFromKey(keys.get(i)) + " " + ka.getStartTimeFromKey(keys.get(i))));//sets the time of the p1 object to the date for the key at 1
                    p2.setTime(sdf.parse(ka.getDateFromKey(keys.get(k)) + " " + ka.getStartTimeFromKey(keys.get(k))));//sets the time of the p2 object to the datefor the key at 2
                } catch (ParseException ex) {//opens the catch statement
                    Logger.getLogger(CalendarHandler.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error setting the times of the calendar object
                }//closes the catch statement
                String temp;//instantiates a string to store a temporary date to be used in the sorting of the key array
                if (p2.after(p1)) {//checks if p1 is after p2
                    temp = keys.get(k);//sets the temp string to the key at index k
                    keys.set(k, keys.get(i));//sets the key at index k to the key at index i
                    keys.set(i, temp);//sets the key at index i to the key stored in the temp string
                }
            }
        }
        String keysArray [] = keys.toArray(new String[keys.size()]);//creates a string array representation of the keys array list
        KEYS_ON_DAY = keysArray;//sets the class static of the keys array to the newly created key string array above
        return keysArray;//returns the keysArray string array
    }//closes the keysOnDayTest method
    
    public String StartTimeFromLessonKey(String key) {//creates a method to get the start time of the lesson associated with the lesson key passed in
        lessonDataArray la = new lessonDataArray();//creates an object of the lessonDataArray object
        keysArray ka = new keysArray();//creates an object of the keysArray object
        String time = "";//creates a string to store the time gotten from the lesson key
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {//starts a for loop iterating through each of the items in the lessonDataArray array list
            if (ka.getKeyFromLessonID(la.getLessonDataArray().get(i).getLessonID()).equals(key)) {//checks if the iterated key is equal to the key passed in
                time = la.getLessonStartTimeFromLessonID(la.getLessonDataArray().get(i).getLessonID());//sets the time string to the time gotton from the iterated in key
                break;//discontinues the loop
            }
        }
        return time;//returns the time string
    }
    
    public String timeFromLessonKey(String key) {//creates a method to get the full formatted time from the passed in key
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        keysArray ka = new keysArray();//creates an object for the keysArray class
        String time = "";//creates a string to store the formatted time
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {//starts a for loop iterting through the items in the lessonDataArray array list
            if (ka.getKeyFromLessonID(la.getLessonDataArray().get(i).getLessonID()).equals(key)) {//checks if the iterated key is equal to the key passed in
                time = la.getLessonDataArray().get(i).getLessonTime() + " - " +
                        la.getEndTime(la.getLessonDataArray().get(i).getLessonTime(), la.getLessonDataArray().get(i).getLessonDuration());//sets the time string to the formatted time gotten from the iterated key
                break;//discontinues the loop
            }
        }
        return time;//returns the time string
    }//closes the timeFromLessonKey method
    
    public String venueFromLessonKey(String key) {//create method to get the venue of a lesson from the associated key
        keysArray ka = new keysArray();//creates an object for the keysArray object
        venueArray va = new venueArray();//creates an object for the venueArray class
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        String venue = "";//creates a string to store the venue gotten to be returned
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {//starts a for loop iterating through the items in the lessonDataArray array list
            if (ka.getKeyFromLessonID(la.getLessonDataArray().get(i).getLessonID()).equals(key)) {//checks if the iterated key is equal to the passed in key
                int lessonID = la.getLessonDataArray().get(i).getLessonID();//creates an intiger to store the iterated lesson id
                int index = la.getIndexFromID(lessonID);//creates an integer representing the index of the lesson
                venue = va.venueNameFromID(la.getLessonDataArray().get(index).getVenueID());//sets the venue string to the gotton venue
                break;//discontinues the loop
            }
        }
        return venue;//returns the gotton venue
    }//closes the venueFromLessonKey method
    
    public String [] studentsFromLessonDateAndTime(String date, String time) {//creates a method that fetches an array of students attending a specific lesson
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        keysArray ka = new keysArray();//creates an object for the keysArray class
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        ArrayList<String> students = new ArrayList<>();//creates an array list storing the students
        
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {//starts a for loop iterating through the items in the lessondata array list
            if (la.getLessonDataArray().get(i).getLessonDate().equals(date) && la.getLessonDataArray().get(i).getLessonTime().equals(time)) {//checks if the date and time of the iterated item is equal to the date and time passed in
                students.add(sa.studentNameFromID(la.getLessonDataArray().get(i).getStudentID()));//add the student of the iterated item to the students array list
            }
        }
        String studentsArray [] = students.toArray(new String[students.size()]);//creates a string array representation of the students array list
        return studentsArray;//returns the studentsArray
    }//closes the studentsFromLessonDateAndTime method
    
    public String getEndSegTime(String segStartTime, String date) {//creates a method to get the end time of a 15 minute time segment
        DateFormat sdf = new SimpleDateFormat("yyy/dd/MM HH:mm");//creates a date formatter
        Calendar time = Calendar.getInstance(); //creates a calendar object
        try {//opens a trycatch statement
            time.setTime(sdf.parse(date + " " + segStartTime));//sets the time of the time calendar object
        } catch (ParseException ex) {//opens the catch statement
            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error setting the time of the date object
        }//closes the catch statement
        time.add(Calendar.MINUTE, 15);//adds 15 minutes to the time calendar object
        String StringTime = sdf.format(time.getTime());//creates a string representation of the time calendar object time using the date formatter
        return StringTime;//returns the StringTime string
    }//closes the getEndSegTime method
    
    public boolean TimeHasLesson(String date, String time) {//creates a method that checks if a time segment is during a lesson
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        keysArray ka = new keysArray();//creates an object for the keysArray class
        boolean segHasLessonBooked = false;//creates a boolean to indicate whether the segment has a lesson or not
        
        if (COUNT_SET <= KEYS_ON_DAY.length) {//checks if all the lessons on the selected date have been already accounted for
            DateFormat sdf = new SimpleDateFormat("yyyy/dd/MM HH:mm");//creates a date formatter
            Calendar attemptedStartTime = Calendar.getInstance(); //creates a calendar object
            Calendar attemptedEndTime = Calendar.getInstance();//creates a calendar object
            try {//opens a trycatch statement
                attemptedStartTime.setTime(sdf.parse(date + " " + time));//sets the time of the attemptedStartTime calendar object to the time passed in
                attemptedEndTime.setTime(sdf.parse(this.getEndSegTime(time, date)));//sets the time of the attemptedEndTime calendar object to the end time of the time passed in
            } catch (ParseException ex) {//opens the catch statement
                Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error setting the times of the calendar objects
            }//closes the catch statement
            for (int i = 0; i < KEYS_ON_DAY.length; i++) {//starts a for loop iterating through the keys of the lessons on the selected date
                Calendar refStartTime = Calendar.getInstance();//creates a calendar object to store the startTime of the lesson represented by the iterated key
                Calendar refEndTime = Calendar.getInstance();//creates a calendar object to store the end Time of the lesson represented by the iterated key
                try {//opens a trycatch statement
                    refStartTime.setTime(sdf.parse(date + " " + START_TIMES[i]));//sets the time of the start time calendar object to the start time associated with the iterated key
                    refEndTime.setTime(sdf.parse(date + " " + END_TIMES[i]));//sets the time of the end time calendar object to the end time associated with the iterated key
                } catch (ParseException ex) {//closes the catch
                    Logger.getLogger(CalendarHandler.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error setting the times of the calendar objects
                }//closes the catch statement
                if (attemptedStartTime.equals(refStartTime) ||//checks if the timeseg start time is equal to the reference lesson start time
                        attemptedEndTime.equals(refEndTime) ||//checks if the timeseg end time is equal to the reference lesson end time
                        attemptedStartTime.after(refStartTime) && attemptedEndTime.before(refEndTime)) {//checks if the timeseg start time is after the reference lesson start time and checks if the timeseg end time is before the reference lesson end time
                    segHasLessonBooked = true;//flips the segHasLessonBooked boolean to true indicating that there is a lesson during the time seg passed in
                } else {
                    if (attemptedStartTime.equals(refEndTime)) {//checks if the end time of the time segment is equal to the reference lesson end time i.e it is the end of the lesson
                        COUNT_SET++;//upps the lesson counter
                    }
                }
            }
        }
        return segHasLessonBooked;//returns the segHasLessonBooked boolean
    }//closes the timeHasLesson method
    
    public String formatEventAtHour(String date, String time, String startTime) {//creates a method to format the lesson event at each time seg (15 minutes) to be displayed in the corresponding column
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        
        String lessonDataEventFiller = "";//creates a string to hold the string that will be displayed for the corresponding tiem seg
        if (this.TimeHasLesson(date, time)) {//checks if the passed in timeseg is during a lesson
            DateFormat sdf = new SimpleDateFormat("yyy/dd/MM HH:mm");//creates a date formatter 
            DateFormat sdf2 = new SimpleDateFormat("HH:mm");//creates a date formatter
            String colours [] = {"red", "blue", "green", "pink", "purple", "yellow", "orange"};//creates a string array of colour names to be iterated through according to the lesson the event formatter is displaying
            String colour = colours[COLOUR];//creates a string representing the name of a color at index 'COLOUR' of the colours array
            
            String endTime = "";//creates a string to hold the end time of the reference lesson
            for (int i = 0; i < la.getLessonDataArray().size(); i++) {//starts a for loop iterating through the items in the lessonData Array list
                if (la.getLessonDataArray().get(i).getLessonDate().equals(date) && la.getLessonDataArray().get(i).getLessonTime().equals(startTime)) {//checks if the date and time of the iterated item is euqla to the date and startTime passed in
                    endTime = la.getEndTime(la.getLessonDataArray().get(i).getLessonTime(), la.getLessonDataArray().get(i).getLessonDuration());//sets the endTime string to the end time of the iterated item
                    break;//discontinues the loop
                }
            }
            Calendar timeDate = Calendar.getInstance();//creates a calendar object
            Calendar endTimeDate = Calendar.getInstance();//creates a calendar object
            Calendar endTimeDateRef = Calendar.getInstance();//creates a calendar object
            
            try {//opens a trycatch statement
                timeDate.setTime(sdf.parse(date + " " + time));//sets the time of the timeDate calendar object to the time passed in of the seg
                endTimeDate.setTime(sdf.parse(this.getEndSegTime(sdf2.format(timeDate.getTime()), date)));//sets the time of the endtimeDate calendar object to the end time of the time passed in of the seg
                endTimeDateRef.setTime(sdf.parse(date + " " + endTime));//sets the time of the endTiemDateRef calendar object tot eh end time of the reference lesson
            } catch (ParseException ex) {//opens the catch statement
                Logger.getLogger(CalendarHandler.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error setting the times of the calendar objects
            }//closes the catch statement
            
            lessonDataEventFiller = "<html><font size = 200 color=\"" + colour + "\">■■■■</font></html>";//sets the lessonDataAevenFiller string to a string of squares indicating that the corresponding time seg has a lesson, formated with HTML code
            
            if (endTimeDate.equals(endTimeDateRef)) {//checks if the end time of the segment is equal to the end time of the reference lesson ie.e the lesson is over
                if (COLOUR < colours.length-1) {//checks if the integer COLOUR is smaller that one less than the lenght of the colours string array
                    COLOUR++;//upps the count of COLOUR i.e changes the colour of the event starting the next lesson
                } else {//if the COLOUR integer is smaller than one less than the lenght of the colours string array
                    COLOUR = 0;//sets the COLOUR counter to 0 i.e the next lesosn event will be red
                }
            }
        } else {//if the time segment passed in is not during a lesson
            return "";//return an empty string to be displayed
        }
        return lessonDataEventFiller;//returns the HTML formatted event string for the passed in time segment
    }//closes the formatEventAtHour method
    
     public void LessonsOnDay() {//creates a method to check if there are any lessons booked on the selected date
         if (countOnDay == 0) {//checks if the countOnDay integer is 0 i.e there are no lessons on the date
             DAY_HAS_LESSON = false;//sets the class static boolean DAY_HAS_LESSON to false
         } else {//if there are lessons on the day
             DAY_HAS_LESSON = true;//sets the class static DAY_HAS_LESSON to true
         }
     }//closes the lessonOnDay method
     
     public String formatTime(int iterate) {//creates a method that formates a string representation of the next timesegment as the column name of the dailyPlanner table
         String ts = "05:45";//sets string to the string representation of a 05:45 calendar object
         DateFormat sdf = new SimpleDateFormat("HH:mm");//creates a date formatter
         Calendar time = Calendar.getInstance();//creates a calendar object
        try {//opens a trycatch statement
            time.setTime(sdf.parse(ts));//sets the time of the time calendar object to the ts string  i.e 05:45
            for (int i = 0; i <= iterate; i++) {//starts a for loop running from 0 to the integer 'iterate' passed in
                time.add(Calendar.MINUTE, 15);//adds 15 minutes to the time claendar object
            }
        } catch (ParseException ex) {//opens the catch statement
            Logger.getLogger(CalendarHandler.class.getName()).log(Level.SEVERE, null, ex);//alerts the user that there was an error setting the time of the calendar object
        }//closes the catch statement
        String StringTime = sdf.format(time.getTime());//creates a string represenation of the time calendar object formatted using the date formatter
        return StringTime;//return the string StringTime
     }//closes the formatTime method
     
     public DefaultTableModel schedModel(String date) {//creates a method returning a tabelModel that is the daily planner
        DefaultTableModel model = null;//instantiates a defaultTableModel temporarily setting it to null
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        keysArray ka = new keysArray();//creates an object for the keysArray class
        
            Object columnNames[] = {"06:00", "06:15", "06:30", "06:45", "07:00", "07:15", "07:30", "07:45", "08:00",
                "08:15", "08:30", "08:45", "09:00", "09:15", "09:30", "09:45", "10:00", "10:15", "10:30", "10:45",
                    "11:00", "11:15", "11:30", "11:45", "12:00", "12:15", "12:30", "12:45", "13:00", "13:15", "13:30",
                    "13:45", "14:00", "14:15", "14:30", "14:45", "15:00", "15:15", "15:30", "15:45", "16:00", "16:15",
                    "16:30", "16:45", "17:00", "17:15", "17:30", "17:45", "18:00", "18:15", "18:30", "18:45", "19:00",
                    "19:15", "19:30", "19:45", "20:00", "20:15", "20:30", "20:45", "21:00", "21:15", "21:30", "21:45",
                    "22:00", "22:15", "22:30", "22:45", "23:00", "23:15", "23:30", "23:45"};//creates an object array of the time segemnts in any day to become the column names of the table model
            model = new DefaultTableModel(columnNames, 0);//adds a default table model to the model
            
            DateFormat sdf = new SimpleDateFormat("HH:mm");//creates a date formatter
            this.getLastEndTime();//calls the method that returns the last end time of the last lesson on the selected passed in date
            String segEvent [] = new String[71];//instantiates a string array to hold the formatted time event of each time segemnt
            Calendar LET = Calendar.getInstance();//creates a calendar object
            Calendar segTime = Calendar.getInstance();//creates a calendar object
        try {//opens a trycatch statement
            LET.setTime(sdf.parse(LAST_END_TIME));//sets the time of the LET calendar object to the last end time string
        } catch (ParseException ex) {//opens the catch statement
            Logger.getLogger(CalendarHandler.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error setting the time of the LET calendar object
        }//closes the catch
        for (int i = 0; i < 71; i++) {//starts a for loop running from 0 to 71(the number of time segments in a day)
            String seg = this.formatTime(i);//creates a string holding the string represenation of the iterated time segment
             try {//opens a trycatch staement
                 segTime.setTime(sdf.parse(seg));//sets the time of the segTime calendar object to the seg string
             } catch (ParseException ex) {//opens the catch statement
                 Logger.getLogger(CalendarHandler.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error setting the time of the segTime calendar object
             }//closes the catch statement
             if (segTime.before(LET)) {//checks if the segTime is before the last end time of the passed in date
                 segEvent[i] = this.formatEventAtHour(date, seg, this.floorStartTime(seg, date));//sets the segEvent item at index i to the formatted string of the segment event
             } else {//is the segTime is not before the last end time
                 segEvent[i] = "";//sets the segTime item at i to an empty string
             }
        }
        
        model.addRow(segEvent);//adds the row (segEvent) to the table model
        return model;//returns tje table model
     }//closes the schedModel method
    
    public DefaultTableModel noLessonModel() {//creates a method returning a table model representing no lessons on the date
        DefaultTableModel model = null;//instantiates a tabel model to be returned and temporarily sets it to null
        String n = "<html><font size = 20 color=\" black\">N/A</font></html>";//creates an HTML formatted string representing N/A
        Object columnNames[] = {n, n, n, n, n, n, n, n, n, n, n, n, n};//creates an object array of the 'n' string to be used as the column names of the table model
        model = new DefaultTableModel(columnNames, 0);//adds a defuatl table model to the model
        model.addRow(new Object[] {n, n, n, n, n, n, n, n, n, n, n, n, n});//adds a row (n's) to the model
        return model;//returns the model
    }//closes the noLessonModel method
    
    public DefaultTableModel DefModel() {//creates a method returning a table model representing the loading of the schedule for the date
        DefaultTableModel model = null;//instantiates a tabel model to be returned and temporarily sets it to null
        String l = "<html><font size = 200 color=\" black\">↻</font></html>";//creates an HTML formatted string representing th eloading sign
        Object columnNames[] = {l, l, l, l, l, l, l, l, l, l, l, l, l, l};//creates an object array of the 'l' string to be used as the column names of the table model
        model = new DefaultTableModel(columnNames, 0);//adds a defuatl table model to the model
        model.addRow(new Object[] {l, l, l, l, l, l, l, l, l, l, l, l, l, l});//adds a row (l's) to the model
        return model;//returns the model
    }//closes the DefModel method

    public void passToDailyPlan(String date) {//creates a method to pass the selected date on the JCalendar object to the dailyPlan form
        dailyPlanForm dp = new dailyPlanForm();//creates an object for the dailyPlanForm class
        dp.setDateLabel(date);//sets the dateLable on the JFrame to the date passed in
        dp.setVisible(true);//sets the JFrame visible
    }//closes the passToDailyPlan method
   
}//closes the CalendarHandler class