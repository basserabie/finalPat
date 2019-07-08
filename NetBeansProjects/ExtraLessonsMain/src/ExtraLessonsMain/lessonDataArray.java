/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtraLessonsMain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

/**
 *
 * @author YishaiBasserabie
 */
public class lessonDataArray {//creates a class to handle and process the lesson objects
    private ArrayList<fetchLessonData> lessonDataArray = new ArrayList<>();//creates an array list of lesson objects//
    private ArrayList<String> names = new ArrayList<>();//creates an array list for the names of students added to a lesson
    private String lessonKey;//instantiates a string for the lessonKey to be added to the database
    private static String studentLost = "";//creates a string to store the studentremoved from the lesson
    private static int STUDENTS_ADDED_TO_LESSON = 0;//creates an int to store the number of students added to a lesson
    public static boolean EDIT_DOUBLE_BOOKED = false;//creates a boolean to indicate whether the edited lesson is set to be double booked

    public lessonDataArray() {//creates a constructor for the current class
        ConnectDB  db = new ConnectDB();//creates an object for the ConnectDB class
        ResultSet r = db.getResults("SELECT * FROM lessonData");//creates a result set of lesson objects
        try {//opens the trycatch statement
            while(r.next()) {//iterates through the resultSet
                fetchLessonData fld = new fetchLessonData(r.getInt("LessonID"), r.getInt("StudentID"),
                        r.getInt("venueID"), r.getInt("lessonDuration"), r.getString("lessonDate"), r.getString("lessonTime"), r.getString("lessonDay"));//creates a new fetchLessons object according to the iterated result
                lessonDataArray.add(fld);//adds the fetchlesson object to the lessonDataArray
            }
        } catch (SQLException ex) {//closes the catch statement
            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user as to what went wrong getting the resultSet
            JOptionPane.showMessageDialog(null, "Database error, please contact administrator at 0836570642");//alerts the user to contact the administrator
        }//closes the catch statement
        this.sortArray();//calls method to sort array list
    }//closes the cosntructor

    public ArrayList<fetchLessonData> getLessonDataArray() {//creates a method to return the lessonDataArray array list
        return lessonDataArray;//returns the array list
    }//closes the getter method

    public ArrayList<String> getNames() {//creates a method to return the names array list
        return names;//returns the array list
    }//closes the getter method

    public void setNames(ArrayList<String> names) {//creates a method to set the names array list
        this.names = names;//sets the array list
    }//closes the setter method

    public String getLessonKey() {//creates a method to return the lesson key
        return lessonKey;//returns the lessonKey
    }//closes the getter method

    public void setLessonKey(String lessonKey) {//creates a method to set the lesson key
        this.lessonKey = lessonKey;//sets the lesson key
    }//closes the setter method

    
    public void setNamesList(ArrayList<String> list) {//creates a method to set the names array list
        boolean alreadyIn = false;//creates a boolean indicating whether the names has already been added
        for (int i = 0; i < list.size(); i++) {//iterates through the list passed in
            for (int k = 0; k < this.names.size(); k++) {//iterates through the name array list
                if (this.names.get(k).equals(list.get(i))) {//checks if the names array list already contains the iterated name
                    alreadyIn = true;//sets alreadyIn to true
                }
            }
            if (alreadyIn == false) {//checks if the iterated name is not already in the names list
                this.names.add(list.get(i));//adds the iterated name to the names list
            }
            alreadyIn = false;//flips alreadyIn to false
        }
    }//closes the setNamesList method
    
    public int getIndexFromID(int id) {//creates a method to get the index of a lesson object from the passed in ID
        int index = 0;//creates an int to store the index
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (this.lessonDataArray.get(i).getLessonID() == id) {//checks if the iterated id euqlas the passed in array
                index = i;//sets the index to i
            }
        }
        return index;//returns index
    }//closes the getIndexfromID method
    
    public int getStudentIDFromIndex(int index) {//creates a method to the ther student id from the lesson index
        int student  = 0;//creates 
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (i == index) {//checks if i is equal to the index passed in
                student = this.lessonDataArray.get(i).getStudentID();//sets the students id to the student id of the iterated lesson
            }
        }
        return student;//returns the student id
    }//closes the getStudentIDFromIndex method
    
    public String getStartTimeFromIndex(int index) {//creates a method to get the start time of a lesson from the index passed in
        String time  = "";//creates a string to store the time
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (i == index) {//checks if the index is equal to i
                time = this.lessonDataArray.get(i).getLessonTime();//sets the time string to the iterated time
            }
        }
        return time;//returns time
    }//closes the getStartTimeFromIndex method
    
    public boolean compareTimesFromKeys(String key1, String key2) {//creates a method to check if one time is before the other
        keysArray ka = new keysArray();//creates an object for the keysArray class
        boolean before = false;//creates a boolean indicating whether the one time is before the other
        
        int startTime1 = (Integer.parseInt(ka.getStartTimeFromKey(key1).substring(0, 2))*60)+Integer.parseInt(ka.getStartTimeFromKey(key1).substring(3, 5));//creates an integer representation of the time
        int startTime2 = (Integer.parseInt(ka.getStartTimeFromKey(key2).substring(0, 2))*60)+Integer.parseInt(ka.getStartTimeFromKey(key2).substring(3, 5));//creates an integer representation of the time
        if (startTime1 < startTime2) {//checks if the startTime1 int is smaller than the startTime2 int i.e. it is before
            before = true;//flips before to true
        }
        return before;//returns the before boolean
    }//closes the compareTimesFromKeys method
    
    public String getFrequencyFromKey(String lessonKey) {//creates a method to get the frequency of a lesson from the key passed in
        keysArray ka = new keysArray();//creates an object for the keysArray class
        String frequency = "once-off";//creates a string to indicate the frequency
        int countFreq = 0;//creates an int to count the lessons of that key in a period of time
        ArrayList<String> refDates = new ArrayList<>();//creates an array list of the dates of the lessons according to the passed in key
        int day1, month1, day2, month2;//instantiates ints to represent the iterated dates and times as integers
        
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (i > 0) {//checks if i is bigger than 0
                if (ka.getKeyFromLessonID(this.lessonDataArray.get(i).getLessonID()).equals(lessonKey) && !this.lessonDataArray.get(i).getLessonDate().equals(this.lessonDataArray.get(i-1).getLessonDate())) {//checks if the key of the iterated lesson is the same as the one passed in and the lesson is on a different date to the last one checked
                    refDates.add(this.lessonDataArray.get(i).getLessonDate());//adds the lessondate to the refDates array list
                    countFreq++;//ups the frequency of the lessons
                }
            }
            if (countFreq > 1) {//checks if the countFreq is more than 1 i.e. it is not once off
                day1 = Integer.parseInt(refDates.get(0).substring(5, 7));//sets the day1 int to the first item in the refDates array list
                day2 = Integer.parseInt(refDates.get(1).substring(5, 7));//sets the day2 int to the second item in the refDates array list
                month1 = Integer.parseInt(refDates.get(0).substring(8, 10));//sets the month1 int to the first item in the refDates array list
                month2 = Integer.parseInt(refDates.get(1).substring(8, 10));//sets the month2 int to the first item in the refDates array list
                if (this.isMonthArpart(refDates.get(0), refDates.get(1))) {//checks if the two dates are a month apart
                    frequency = "monthly";//sets the frequency to monthly
                }
                if (this.isWeekApart(refDates.get(0), refDates.get(1))) {//checks if the two dates are week apart
                    frequency = "weekly";//sets the frequency to weekly
                }
            }
        }
        return frequency;//returns the frequency
    }//closes the getFrequencyFromKey method
    
    public boolean isLeapYear(String date) {//creates a method to check if a date passed in is in a leap year
        boolean leap = false;//creates a boolean to indicate whether the date passed in is in a leap year
        int year = Integer.parseInt(date.substring(0, 4));//creates an integer representation of the year
        if (year%100 == 0 && year%400 ==0) {//checks if the year is a leap year (divisible by 100 and 400)
                leap = true;//sets leap to true
            } else {//if the year is not divisible by 100 or 400
                if (year%4 == 0 && year%100 != 0) {//check if the year is leap (divisible by 4 and not 100)
                    leap = true;//flips leap to true
                }
            }
        return leap;//returns leap
    }//closes the isLeapYear method
    
    public int checkDaysInMonth(String date) {//creates a method to format the days passed in
        int days = 0;//creates an int to store the day
        int month = Integer.parseInt(date.substring(8, 10));//creates an int for the month
        
        if (this.isLeapYear(date)) {//checks if the date is a leap year
            switch (month) {//creates a switchcase statement according to the month int
                case 1://case jan
                    days = 31;//sets days to 31
                    break;//dicontinues the current case
                case 2://case feb
                    days = 28;//sets days to 28
                    break;//dicontinues the current case
                case 3://case mar
                    days = 31;//sets days to 31
                    break;//dicontinues the current case
                case 4://case apr
                    days = 30;//sets days to 30
                    break;//dicontinues the current case
                case 5://case may
                    days = 31;//sets days to 31
                    break;//dicontinues the current case
                case 6://case jun
                    days = 30;//sets days to 30
                    break;//dicontinues the current case
                case 7://case jul
                    days = 31;//sets days to 31
                    break;//dicontinues the current case
                case 8://case aug
                    days = 31;//sets days to 31
                    break;//dicontinues the current case
                case 9://case sep
                    days = 30;//sets days to 30
                    break;//dicontinues the current case
                case 10://case oct
                    days = 31;//sets days to 31
                    break;//dicontinues the current case
                case 11://case nov
                    days = 30;//sets days to 30
                    break;//dicontinues the current case
                case 12://case dec
                    days = 31;//sets days to 31
                    break;//dicontinues the current case
            }//closes the switchcase statement
        } else {//if the year is not a leap
            switch (month) {//creates a switchcase statement according to the month int
                case 1://case jan
                    days = 31;//sets days to 31
                    break;//dicontinues the current case
                case 2://case feb
                    days = 29;//sets days to 29
                    break;//dicontinues the current case
                case 3://case mar
                    days = 31;//sets days to 31
                    break;//dicontinues the current case
                case 4://case apr
                    days = 30;//sets days to 30
                    break;//dicontinues the current case
                case 5://case may
                    days = 31;//sets days to 31
                    break;//dicontinues the current case
                case 6://case june
                    days = 30;//sets days to 30
                    break;//dicontinues the current case
                case 7://case july
                    days = 31;//sets days to 31
                    break;//dicontinues the current case
                case 8://case aug
                    days = 31;//sets days to 31
                    break;//dicontinues the current case
                case 9://case sep
                    days = 30;//sets days to 30
                    break;//dicontinues the current case
                case 10://case oct
                    days = 31;//sets days to 31
                    break;//dicontinues the current case
                case 11://case nov
                    days = 30;//sets days to 30
                    break;//dicontinues the current case
                case 12://case dec
                    days = 31;//sets days to 31
                    break;//dicontinues the current case
            }//closes the switchcase statement
        }
        return days;//return days
    }//closes the checkDaysInMonth method
    
    public boolean isWeekApart(String date1, String date2) {//creates a method to check if two dates are a week apart
        boolean weekly = false;//creates a boolean indicating whether the dates are a week apart
        
        int day1 = Integer.parseInt(date1.substring(5, 7));//
        int day2 = Integer.parseInt(date2.substring(5, 7));
        int month1 = Integer.parseInt(date1.substring(8, 10));
        int month2 = Integer.parseInt(date2.substring(8, 10));
        //checks if the days are in the same month
        if (month2 - month1 != 0) {
            //gest days in each of the months in question
            int daysInMonth1 = this.checkDaysInMonth(date1);
            if ((day2 + daysInMonth1 - day1) == 7) {
                weekly = true;
            }
        } else {
            if (day2 - day1 == 7) {
                weekly = true;
            }
        }
        return weekly;
    }
    
    public boolean isMonthArpart(String date1, String date2) {
        boolean monthly = false;
        
        int day1 = Integer.parseInt(date1.substring(5, 7));
        int day2 = Integer.parseInt(date2.substring(5, 7));
        int month1 = Integer.parseInt(date1.substring(8, 10));
        int month2 = Integer.parseInt(date2.substring(8, 10));
        
        if (month2 - month1 == 1 || month2 - month1 == -11) {
                    if (day2 == day1) {
                        monthly = true;
                    }
                }
        return monthly;
    }
    
    public void AddStudentsAdded(String name) {
        this.names.add(name);
            for (int i = 0; i < this.names.size(); i++) {
            }
    }
    public void removeStudentAdded(String name) {
        this.names.remove(name);
    }
    
    public String getArrayOfStudentsAdded(int index) {
        String namesArray [] = this.names.toArray(new String[this.names.size()]);
        return namesArray[index];
    }
    
    public String [] getArrayOfStudentsAddedNoIndex() {
        String namesArray [] = this.names.toArray(new String[this.names.size()]);
        return namesArray;
    }
    
    public void addToNamesList(String name) {
        if (!names.contains(name)) {
            this.AddStudentsAdded(name);
        }
    }
    
    public void sortArray() {
        //sorts by dates and times
         Collections.sort(this.lessonDataArray, new Comparator<fetchLessonData>() {
             public int compare(fetchLessonData l1, fetchLessonData l2) {
                 int comp = 0;
                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM HH:mm");
                 try {
                     Date date1 = sdf.parse(l1.getLessonDate() + " " + l1.getLessonTime());
                     Date date2 = sdf.parse(l2.getLessonDate() + " " + l2.getLessonTime());
                     comp =  date1.compareTo(date2);
                 } catch (ParseException ex) {
                     Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
                 }
                  return comp;
             }

             public int compareLessons(fetchLessonData p1, fetchLessonData p2) {
                 throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }
         });
    }
    
    public String formatDate(String date) {
        String finalDate = "";
        //create a date fromatter
        DateFormat sdf = new SimpleDateFormat("yyyy/dd/MM");
        Calendar cal = Calendar.getInstance(); // adds instance to cal
        //gets substrings of ivne full date
        String day = date.substring(8, 10);
        String month = date.substring(4, 7);
        String year = date.substring(25);
        
        //checks and formats month
        int finalMonth = 0;
        switch (month) {
            case "Jan": 
                finalMonth = 1;
                break;
            case "Feb":
                finalMonth = 2;
                break; 
            case "Mar":
                finalMonth = 3;
                break; 
            case "Apr":
                finalMonth = 4;
                break; 
            case "May":
                finalMonth = 5;
                break; 
            case "Jun":
                finalMonth = 6;
                break; 
            case "Jul":
                finalMonth = 7;
                break; 
            case "Aug":
                finalMonth = 8;
                break; 
            case "Sep":
                finalMonth = 9;
                break; 
            case "Oct":
                finalMonth = 10;
                break; 
            case "Nov":
                finalMonth = 11;
                break; 
            case "Dec":
                finalMonth = 12;
                break; 
        }
        
        if (finalMonth < 10) {
            finalDate = year + "/" + day + "/" + "0" + ""+finalMonth;
        } else {
            finalDate = year + "/" + day + "/" + "" + ""+finalMonth;
        }
        
        try {
            cal.setTime(sdf.parse(finalDate)); 
        } catch (ParseException ex) {
            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sdf.format(cal.getTime());
    }
    
    public String formatTime(String date) {
        String time = date.substring(11, 16);
        return time;
    }
    
    public String formatDay(String day) {
        String finalDay = "";
        
        //gets substring of day from given full date
        String subbedDay = day.substring(0, 3);
         switch (subbedDay) {
             case "Sun":
                 finalDay = "sunday";
                 break;
            case "Mon":
                 finalDay = "monday";
                 break;
            case "Tue":
                 finalDay = "tuesday";
                 break;
            case "Wed":
                 finalDay = "wednesday";
                 break;
            case "Thu":
                 finalDay = "thursday";
                 break;
            case "Fri":
                 finalDay = "friday";
                 break;
            case "Sat":
                 finalDay = "saturday";
                 break;
         }
         return finalDay;
    }
    
    public void addLessonForEdit(String venue, String date, String time, String day, int size, String name, int frequency, int duration, String lessonKeyToAdd, boolean paid, int cost) {
        ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
        lessonDataArray la = new lessonDataArray();
        studentsArray sa = new studentsArray();
        keysArray ka = new keysArray();//creates an object for the keysArray class
        paymentsArray pa = new paymentsArray();
        venueArray va = new venueArray();
        int countDisplayMessages = 0;
        int countLessonIDForKeys = 1;
        
        if (this.checkIfInPast(date, time) == false) {
                
        int id = sa.studentIDFromName(name);
        
        //create a date fromatter
        DateFormat sdf = new SimpleDateFormat("yyyy/dd/MM");
        Calendar cal = Calendar.getInstance(); // adds instance to cal
        try {
            cal.setTime(sdf.parse(date)); 
        } catch (ParseException ex) {
            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
        }
        String insert;
        String insertKey;
        String insertPaid;
        
        if (frequency == 0) {
            insert = "INSERT INTO lessonData (studentID, venueID, lessonDate, lessonTime, lessonDuration, lessonDay) VALUES (" + id + ", " 
                    + va.venueIDFromVenue(venue) + ", '" + sdf.format(cal.getTime()) + "', '" + time + "', " + duration + ", '" + day + "')";
            insertKey = "INSERT INTO lessonKeys (lessonKey) VALUES ('" + lessonKeyToAdd + "')";
            insertPaid = "INSERT INTO sPayTable (StudID, Paid, PayDate, PayTime, PayDuration, Cost) VALUES "
                    + "(" + id + ", " + paid + ", '" + sdf.format(cal.getTime()) + "', '" + time + "', " + duration + ", " + cost + ")";
            try {
                db.UpdateDatabase(insert);
                db.UpdateDatabase(insertKey);
                db.UpdateDatabase(insertPaid);
            } catch (SQLException ex) {
                Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (frequency == 1) {
                for (int i = 0; i < 52; i++) {
                    insert = "INSERT INTO lessonData (studentID, venueID, lessonDate, lessonTime, lessonDuration, lessonDay) VALUES (" + id + ", " 
                    + va.venueIDFromVenue(venue) + ", '" + sdf.format(cal.getTime()) + "', '" + time + "', " + duration + ", '" + day + "')";
                    insertKey = "INSERT INTO lessonKeys (lessonKey) VALUES ('" + lessonKeyToAdd + "')";
                    insertPaid = "INSERT INTO sPayTable (StudID, Paid, PayDate, PayTime, PayDuration, Cost) VALUES "
                    + "(" + id + ", " + paid + ", '" + sdf.format(cal.getTime()) + "', '" + time + "', " + duration + ", " + cost + ")";
                    try {
                        db.UpdateDatabase(insert);
                        db.UpdateDatabase(insertKey);
                        db.UpdateDatabase(insertPaid);
                    } catch (SQLException ex) {
                        Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //adds a week to the date
                    cal.add(cal.DATE, 7);
                }
            } else {
                if (frequency == 2) {
                    for (int i = 0; i < 12; i++) {
                        insert = "INSERT INTO lessonData (studentID, venueID, lessonDate, lessonTime, lessonDuration, lessonDay) VALUES (" + id + ", " 
                        + va.venueIDFromVenue(venue) + ", '" + sdf.format(cal.getTime()) + "', '" + time + "', " + duration + ", '" + day + "')";
                        insertKey = "INSERT INTO lessonKeys (lessonKey) VALUES ('" + lessonKeyToAdd + "')";
                        insertPaid = "INSERT INTO sPayTable (StudID, Paid, PayDate, PayTime, PayDuration, Cost) VALUES "
                        + "(" + id + ", " + paid + ", '" + sdf.format(cal.getTime()) + "', '" + time + "', " + duration + ", " + cost + ")";
                        try {
                            db.UpdateDatabase(insert);
                            db.UpdateDatabase(insertKey);
                            db.UpdateDatabase(insertPaid);
                        } catch (SQLException ex) {
                            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //adds a week to the date
                        cal.add(cal.MONTH, 1);
                    }
                }
            }
        }
        STUDENTS_ADDED_TO_LESSON++;
        if (STUDENTS_ADDED_TO_LESSON == size) {
            JOptionPane.showMessageDialog(null, "the lesson(s) have been added!");
        }
        } else {
            JOptionPane.showMessageDialog(null, "please do not try to book a lesson in the past ;)");
        }
        
    }
    
    public void addLesson(String venue, String date, String time, String day, int size, String name, int frequency, int duration, String lessonKeyToAdd, boolean paid, int cost) {
        ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
        lessonDataArray la = new lessonDataArray();
        studentsArray sa = new studentsArray();
        keysArray ka = new keysArray();//creates an object for the keysArray class
        paymentsArray pa = new paymentsArray();
        venueArray va = new venueArray();
        int countDisplayMessages = 0;
        int countLessonIDForKeys = 1;
        
        if (this.checkIfInPast(date, time) == false) {
            if(!this.checkIfDoublebooking(date, time, duration, size, lessonKeyToAdd).contains("Venue")) {
                
        int id = sa.studentIDFromName(name);
        
        //create a date fromatter
        DateFormat sdf = new SimpleDateFormat("yyyy/dd/MM");
        Calendar cal = Calendar.getInstance(); // adds instance to cal
        try {
            cal.setTime(sdf.parse(date)); 
        } catch (ParseException ex) {
            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
        }
        String insert;
        String insertKey;
        String insertPaid;
        
        if (frequency == 0) {
            insert = "INSERT INTO lessonData (studentID, venueID, lessonDate, lessonTime, lessonDuration, lessonDay) VALUES (" + id + ", " 
                    + va.venueIDFromVenue(venue) + ", '" + sdf.format(cal.getTime()) + "', '" + time + "', " + duration + ", '" + day + "')";
            insertKey = "INSERT INTO lessonKeys (lessonKey) VALUES ('" + lessonKeyToAdd + "')";
            insertPaid = "INSERT INTO sPayTable (StudID, Paid, PayDate, PayTime, PayDuration, Cost) VALUES "
                    + "(" + id + ", " + paid + ", '" + sdf.format(cal.getTime()) + "', '" + time + "', " + duration + ", " + cost + ")";
            try {
                db.UpdateDatabase(insert);
                db.UpdateDatabase(insertKey);
                db.UpdateDatabase(insertPaid);
            } catch (SQLException ex) {
                Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (frequency == 1) {
                for (int i = 0; i < 52; i++) {
                    insert = "INSERT INTO lessonData (studentID, venueID, lessonDate, lessonTime, lessonDuration, lessonDay) VALUES (" + id + ", " 
                    + va.venueIDFromVenue(venue) + ", '" + sdf.format(cal.getTime()) + "', '" + time + "', " + duration + ", '" + day + "')";
                    insertKey = "INSERT INTO lessonKeys (lessonKey) VALUES ('" + lessonKeyToAdd + "')";
                    insertPaid = "INSERT INTO sPayTable (StudID, Paid, PayDate, PayTime, PayDuration, Cost) VALUES "
                    + "(" + id + ", " + paid + ", '" + sdf.format(cal.getTime()) + "', '" + time + "', " + duration + ", " + cost + ")";
                      String insert2 = "INSERT INTO lessonData (studentID, venueID, lessonDate, lessonTime, lessonDuration, lessonDay) VALUES (24, 9, '2019/06/07', '13:00', 1, 'saturday')";
                    try {
                        db.UpdateDatabase(insert);
                        db.UpdateDatabase(insertKey);
                        db.UpdateDatabase(insertPaid);
                    } catch (SQLException ex) {
                        Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //adds a week to the date
                    cal.add(cal.DATE, 7);
                }
            } else {
                if (frequency == 2) {
                    for (int i = 0; i < 12; i++) {
                        insert = "INSERT INTO lessonData (studentID, venueID, lessonDate, lessonTime, lessonDuration, lessonDay) VALUES (" + id + ", " 
                        + va.venueIDFromVenue(venue) + ", '" + sdf.format(cal.getTime()) + "', '" + time + "', " + duration + ", '" + day + "')";
                        insertKey = "INSERT INTO lessonKeys (lessonKey) VALUES ('" + lessonKeyToAdd + "')";
                        insertPaid = "INSERT INTO sPayTable (StudID, Paid, PayDate, PayTime, PayDuration, Cost) VALUES "
                        + "(" + id + ", " + paid + ", '" + sdf.format(cal.getTime()) + "', '" + time + "', " + duration + ", " + cost + ")";
                        try {
                            db.UpdateDatabase(insert);
                            db.UpdateDatabase(insertKey);
                            db.UpdateDatabase(insertPaid);
                        } catch (SQLException ex) {
                            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //adds a week to the date
                        cal.add(cal.MONTH, 1);
                    }
                }
            }
        }
        STUDENTS_ADDED_TO_LESSON++;
        if (STUDENTS_ADDED_TO_LESSON == size) {
            JOptionPane.showMessageDialog(null, "the lesson(s) have been added!");
        }
        } else {
            if (countDisplayMessages == 0) {
                JOptionPane.showMessageDialog(null, this.checkIfDoublebooking(date, time, duration, size, lessonKeyToAdd));
            }
            countDisplayMessages++;
        }
        } else {
            JOptionPane.showMessageDialog(null, "please do not try to book a lesson in the past ;)");
        }
        
    }

    public String [] notInNewStudents(String [] list1, ArrayList<String> list2) {
        ArrayList<String> notIn = new ArrayList(Arrays.asList(list1));
        for (int i = 0; i < list2.size(); i++) {
            if (notIn.contains(list2.get(i))) {
                notIn.remove(list2.get(i));
            } else {
                notIn.add(list2.get(i));
                studentLost = list1[i];
            }
        }
         String StringArray [] = notIn.toArray(new String[notIn.size()]);
         return StringArray;
    }
    
    public void deleteLesson(int lessonID, String date, String time, String lesson) {
        ConnectDB  db = new ConnectDB();//creates an object for the ConnectDB class
        keysArray ka = new keysArray();//creates an object for the keysArray class
        paymentsArray pa = new paymentsArray();
        CalendarHandler ch = new CalendarHandler();
        int yes = JOptionPane.YES_OPTION;
        int no = JOptionPane.NO_OPTION;
        int deletePaymentsDialog = JOptionPane.showConfirmDialog(null, "Continuing will delete all information regarding this lesson\n " + lesson + "\nSelect 'Yes' if you wish to delete all payment info as well.", "delete lesson", JOptionPane.YES_NO_OPTION);
        int deleteAllDialog = JOptionPane.showConfirmDialog(null, "Continuing will delete all information regarding this lesson\n " + lesson + "\nSelect 'Yes' if you wish to delete all of the lessons in this group.\nSelect 'No' if you wish to delete only the selected lesson", "delete lesson", JOptionPane.YES_NO_CANCEL_OPTION);
        
        String key = ka.getKeyFromDateAndTime(date, time);
        
        if (deleteAllDialog == no) {
            if (deletePaymentsDialog == yes) {
                    for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
                        int lessonID2 = this.lessonDataArray.get(i).getLessonID();
                        if (ka.getKeyFromLessonID(lessonID2).equals(key) && this.lessonDataArray.get(i).getLessonDate().equals(date) && this.lessonDataArray.get(i).getLessonTime().equals(time)) {
                            String deletePayments = "DELETE * FROM sPayTable WHERE lessonID = " + lessonID2;
                            String deleteKeys = "DELETE * FROM lessonKeys WHERE lessonID = " + lessonID2;
                            String deleteLessons = "DELETE * FROM lessonData WHERE LessonID = " + lessonID2;
                            try {
                                db.UpdateDatabase(deletePayments);
                                db.UpdateDatabase(deleteKeys);
                                db.UpdateDatabase(deleteLessons);
                            } catch (SQLException ex) {
                                Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
                        int lessonID2 = this.lessonDataArray.get(i).getLessonID();
                        if (ka.getKeyFromLessonID(lessonID2).equals(key) && this.lessonDataArray.get(i).getLessonDate().equals(date) && this.lessonDataArray.get(i).getLessonTime().equals(time)) {
                            String deleteKeys = "DELETE * FROM lessonKeys WHERE lessonID = " + lessonID2;
                            String deleteLessons = "DELETE * FROM lessonData WHERE LessonID = " + lessonID2;
                            try {
                                db.UpdateDatabase(deleteKeys);
                                db.UpdateDatabase(deleteLessons);
                            } catch (SQLException ex) {
                                Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                } 
        } else {
            if (deleteAllDialog == yes) {
                if (deletePaymentsDialog == yes) {
                    for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
                        int lessonID2 = this.lessonDataArray.get(i).getLessonID();
                        if (ka.getKeyFromLessonID(lessonID2).equals(key)) {
                            String deletePayments = "DELETE * FROM sPayTable WHERE lessonID = " + lessonID2;
                            String deleteKeys = "DELETE * FROM lessonKeys WHERE lessonID = " + lessonID2;
                            String deleteLessons = "DELETE * FROM lessonData WHERE LessonID = " + lessonID2;
                            try {
                                db.UpdateDatabase(deletePayments);
                                db.UpdateDatabase(deleteKeys);
                                db.UpdateDatabase(deleteLessons);
                            } catch (SQLException ex) {
                                Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
                        int lessonID2 = this.lessonDataArray.get(i).getLessonID();
                        if (ka.getKeyFromLessonID(lessonID2).equals(key)) {
                            String deleteKeys = "DELETE * FROM lessonKeys WHERE lessonID = " + lessonID2;
                            String deleteLessons = "DELETE * FROM lessonData WHERE LessonID = " + lessonID2;
                            try {
                                db.UpdateDatabase(deleteKeys);
                                db.UpdateDatabase(deleteLessons);
                            } catch (SQLException ex) {
                                Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                } 
            } else {
               JOptionPane.showMessageDialog(null, "Lesson Saved");
            }
        } 
    }
    
    public String getLessonDayFromLessonID(int id) {
        String day = "";
        
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (this.lessonDataArray.get(i).getLessonID() == id) {
                day = this.lessonDataArray.get(i).getDay();
            }
        }
        return day;
    }
    
    public void deleteStudentsInSpecificLesson(String date, String time) {
       ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
       keysArray ka = new keysArray();//creates an object for the keysArray class
       CalendarHandler ch = new CalendarHandler();
       paymentsArray pa = new paymentsArray();
       studentsArray sa = new studentsArray();
       venueArray va = new venueArray();
       
       String students [] = ch.studentsFromLessonDateAndTime(date, time);
       
       for (int i = 0; i < students.length; i++) {
           int studentID = sa.studentIDFromName(students[i]);
           int lessonID = this.getLessoIDFromDateTimeAndStudentID(date, time, studentID);
           String deleteKey = "DELETE * FROM lessonKeys WHERE lessonID = " + lessonID;
           String deletePayments = "DELETE * FROM sPayTable WHERE lessonID = " + lessonID;
           String deleteLesson = "DELETE * FROM lessonData WHERE LessonID = " + lessonID;
           try {
               db.UpdateDatabase(deleteKey);
               db.UpdateDatabase(deletePayments);
               db.UpdateDatabase(deleteLesson);
           } catch (SQLException ex) {
               Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
    }
    
    public void deletStudentsInAllLesson(String date, String time, String key) {
       ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
       keysArray ka = new keysArray();//creates an object for the keysArray class
       CalendarHandler ch = new CalendarHandler();
       paymentsArray pa = new paymentsArray();
       studentsArray sa = new studentsArray();
       venueArray va = new venueArray();
       
       String students [] = ch.studentsFromLessonDateAndTime(date, time);
       
       for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
           int lessonID = this.lessonDataArray.get(i).getLessonID();
           if (ka.getKeyFromLessonID(lessonID).equals(key)) {
               int studentID = this.lessonDataArray.get(i).getStudentID();
               String deleteKey = "DELETE * FROM lessonKeys WHERE lessonID = " + lessonID;
                String deletePayments = "DELETE * FROM sPayTable WHERE lessonID = " + lessonID;
                String deleteLesson = "DELETE * FROM lessonData WHERE LessonID = " + lessonID;
                try {
                    db.UpdateDatabase(deleteKey);
                    db.UpdateDatabase(deletePayments);
                    db.UpdateDatabase(deleteLesson);
                } catch (SQLException ex) {
                    Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
       }
    }
    
    public int formatFrequency(String freq) {
        int freqInt = 0;
        if (freq.toLowerCase().startsWith("week")) {
            freqInt = 1;
        } else {
            if (freq.toLowerCase().startsWith("month")) {
                freqInt = 2;
            }
        }
        return freqInt;
    }
    
    public void editAllLessonStudents(ArrayList<String> list, int id, String date, String time) {
       ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
       keysArray ka = new keysArray();//creates an object for the keysArray class
       CalendarHandler ch = new CalendarHandler();
       paymentsArray pa = new paymentsArray();
       studentsArray sa = new studentsArray();
       venueArray va = new venueArray();
     
       String deleteKey = ka.getKeyFromLessonID(id);
       String newKey = this.generateLessonKey();
       
       int frequency = this.formatFrequency(this.getFrequencyFromKey(deleteKey));
       int cost = pa.getCostFromLessonID(id);
       boolean paid = pa.getIfPaidFromLessonID(id);
       int venueID = va.getVenueIDFromLessonID(id);
       String venue = va.venueNameFromID(venueID);
       String day = this.getLessonDayFromLessonID(id);
       int duration = this.getDurationFromTimeAndDate(time, date); 
        //pushes lesson
        for (int i = 0; i < list.size(); i++) {
            this.addLessonForEdit(venue, date, time, day, list.size(), list.get(i),frequency,duration,newKey,paid, cost);
        }
       editLessonForm.ADDED_ARRAY.removeAll(editLessonForm.ADDED_ARRAY);
       
       pa.deletePaymentsFromDateAndTime(date, time);
       this.deletStudentsInAllLesson(date, time, deleteKey);
       ka.deleteKeyFromDateAndTime(date, time);
   }
    
   public void editSelectedLessonStudents(ArrayList<String> list, int id, String date, String time) {
       ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
       keysArray ka = new keysArray();//creates an object for the keysArray class
       CalendarHandler ch = new CalendarHandler();
       paymentsArray pa = new paymentsArray();
       studentsArray sa = new studentsArray();
       venueArray va = new venueArray();
       
       String students [] = ch.studentsFromLessonDateAndTime(date, time);
       
       String key = ka.getKeyFromLessonID(id);
       int cost = pa.getCostFromLessonID(id);
       int venueID = va.getVenueIDFromLessonID(id);
       String day = this.getLessonDayFromLessonID(id);
       int duration = this.getDurationFromTimeAndDate(time, date);
       
           this.deleteStudentsInSpecificLesson(date, time);
           for (int i = 0; i < list.size(); i++) {
               int studentID = sa.studentIDFromName(list.get(i));
               int lessonID = this.getLessoIDFromDateTimeAndStudentID(date, time, studentID);
               boolean paid = pa.getIfPaidFromLessonID(lessonID);
               String insertLesson = "INSERT INTO lessonData (studentID, venueID, lessonDate, lessonTime, lessonDuration, lessonDay) "
                       + "VALUES ('" + studentID + "', '" + venueID + "', '" + date + "', '" + time + "', '" + duration + "', '" + day + "')";
               String insertKey = "INSERT INTO lessonKeys (lessonKey) VALUES ('" + key + "')";
               String insertPayment = "INSERT INTO sPayTable (StudID, Paid, PayDate, PayTime, PayDuration, Cost) "
                       + "VALUES ('" + studentID + "', '" + paid + "', '" + date + "', '" + time + "', '" + duration + "', '" + cost + "')";
               try {
                   db.UpdateDatabase(insertLesson);
                   db.UpdateDatabase(insertKey);
                   db.UpdateDatabase(insertPayment);
               } catch (SQLException ex) {
                   Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
   }
   
   public void updateLessonDateTime(String originalDate, String originalTime, String newDateIn, String newTime, String newDuration) {
       ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
       keysArray ka = new keysArray();//creates an object for the keysArray class
       
       String newDate = this.formatDate(newDateIn);
       String day = this.formatDay(newDateIn.substring(0, 3));
       
       String key = ka.getKeyFromDateAndTime(originalDate, originalTime);
       for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
           if (ka.getKeyFromLessonID(this.lessonDataArray.get(i).getLessonID()).equals(key) && this.lessonDataArray.get(i).getLessonDate().equals(originalDate) && this.lessonDataArray.get(i).getLessonTime().equals(originalTime)) {
               String updateDateTime = "UPDATE lessonData SET lessonDate = '" + newDate + "', lessonTime = '" + newTime + "', lessonDuration = " + newDuration + ", lessonDay = '" + day + "' "
                       + "WHERE LessonID = " + this.lessonDataArray.get(i).getLessonID();
               String updatePayTime = "UPDATE sPayTable SET PayDate = '" + newDate + "', PayTime = '" + newTime + "', PayDuration = " + newDuration
                       + " WHERE lessonID = " + this.lessonDataArray.get(i).getLessonID();
               try {
                   db.UpdateDatabase(updateDateTime);
                   db.UpdateDatabase(updatePayTime);
               } catch (SQLException ex) {
                   Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
   }
    
   public void editAllLessonVenue(String  venue, String date, String time) {
       ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
       venueArray va = new venueArray();
       keysArray ka = new keysArray();//creates an object for the keysArray class

       int venueID = va.venueIDFromVenue(venue);
       String key = ka.getKeyFromDateAndTime(date, time);
       for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
           if (ka.getKeyFromLessonID(this.lessonDataArray.get(i).getLessonID()).equals(key)) {
               String updateVenue = "UPDATE lessonData SET venueID = " + venueID + " "
                       + "WHERE LessonID = " + this.lessonDataArray.get(i).getLessonID();
               try {
                   db.UpdateDatabase(updateVenue);
               } catch (SQLException ex) {
                   Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
   }
   
   public void editSelectedLessonVenue(String  venue, String date, String time) {
       ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
       venueArray va = new venueArray();
       keysArray ka = new keysArray();//creates an object for the keysArray class
       
       int venueID = va.venueIDFromVenue(venue);
       String key = ka.getKeyFromDateAndTime(date, time);
       for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
           if (ka.getKeyFromLessonID(this.lessonDataArray.get(i).getLessonID()).equals(key) && this.lessonDataArray.get(i).getLessonDate().equals(date) && this.lessonDataArray.get(i).getLessonTime().equals(time)) {
               String updateVenue = "UPDATE lessonData SET venueID = " + venueID + " "
                       + "WHERE LessonID = " + this.lessonDataArray.get(i).getLessonID();
               try {
                   db.UpdateDatabase(updateVenue);
               } catch (SQLException ex) {
                   Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
   }
   
    public int getDurationFromTimeAndDate(String time, String date) {
        int duration = 0;
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (this.lessonDataArray.get(i).getLessonTime().equals(time) && this.lessonDataArray.get(i).getLessonDate().equals(date)) {
                duration = this.lessonDataArray.get(i).getLessonDuration();
            }
        }
        return duration;
    }
    
    public void checkIfDoubleBookingForEdit(String date, String time, int duration, int size, String lessonKeyToCheck) {
        if (!this.checkIfDoublebooking(date, time, duration, size, lessonKeyToCheck).contains("Venue")) {
            EDIT_DOUBLE_BOOKED = false;
        } else {
            EDIT_DOUBLE_BOOKED = true;
        }
    }
    
    //checks if double booked
    public String checkIfDoublebooking(String date, String time, int duration, int size, String lessonKeyToCheck) {
        ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
        lessonDataArray la = new lessonDataArray();
        studentsArray sa = new studentsArray();
        venueArray va = new venueArray();
        //sorts array of lessons
        this.sortArray();
        //gets end time of attemped time
        String endTimeAttempted = this.getEndTime(time, duration);
        
        String tempstudents = "\nStudent(s): ";
        String tempvenue = "";
        String tempReturn = "";
        String tempTime = "";
        boolean ok = true;
        boolean oneStudent = size == 1;
        
        int minsAttemptedTime = (Integer.parseInt(time.substring(0, 2))*60)+Integer.parseInt(time.substring(3, 5));
        int minsAttemptedEndTime = (Integer.parseInt(endTimeAttempted.substring(0, 2))*60)+Integer.parseInt(endTimeAttempted.substring(3, 5));
        int minsDuration = duration*60;
            
            for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            //gets reference end time TODO: problem is that the size of the lessonArray is not updating
            String endTimeReference = this.getEndTime(this.getLessonDataArray().get(i).getLessonTime(), this.lessonDataArray.get(i).getLessonDuration());
            
            int minsReferenceTime = (Integer.parseInt(this.lessonDataArray.get(i).getLessonTime().substring(0, 2))*60)+Integer.parseInt(this.lessonDataArray.get(i).getLessonTime().substring(3, 5));
            int minsReferenceEndTime = (Integer.parseInt(endTimeReference.substring(0, 2))*60)+Integer.parseInt(endTimeReference.substring(3, 5));
            
            //checks the name of reference against array of added students
            boolean isInArray = Arrays.stream(this.getArrayOfStudentsAddedNoIndex()).anyMatch(sa.studentNameFromID(this.lessonDataArray.get(i).getStudentID())::equals);
            //checks if an attempted lesson to be booked has the same lesson key as the reference lesson
            boolean keyIsEqual = this.getLessonKey(i).equals(lessonKeyToCheck);
            //checks if lesson attmepted to be booked will overlap with an already booked lesson
            boolean checkDate = this.lessonDataArray.get(i).getLessonDate().equals(date);
            boolean check1 = minsReferenceTime-minsAttemptedTime > 0 && minsReferenceEndTime-minsAttemptedEndTime > 0 && !(minsAttemptedEndTime <= minsReferenceTime);
             boolean check2 = minsReferenceTime-minsAttemptedTime > 0 && minsReferenceEndTime-minsAttemptedEndTime == 0;
              boolean check3 = minsReferenceTime-minsAttemptedTime == 0 && minsReferenceEndTime-minsAttemptedEndTime > 0;
               boolean check4 = minsReferenceTime-minsAttemptedTime == 0 && minsReferenceEndTime-minsAttemptedEndTime < 0;
                boolean check5 = minsReferenceTime-minsAttemptedTime < 0 && minsReferenceEndTime-minsAttemptedEndTime > 0;
                 boolean check6 = minsReferenceTime-minsAttemptedTime < 0 && minsReferenceEndTime-minsAttemptedEndTime < 0 && !(minsReferenceTime-minsAttemptedEndTime <= 0);
                  boolean check7 = minsReferenceTime-minsAttemptedTime == 0 && minsReferenceEndTime-minsAttemptedEndTime == 0;
                
            if (oneStudent) {
                if (keyIsEqual == false && checkDate == true && (check1 == true) ||
                    keyIsEqual == false && checkDate == true && (check2 == true) ||
                    keyIsEqual == false && checkDate == true && (check3 == true) ||
                    keyIsEqual == false && checkDate == true && (check4 == true) ||
                    keyIsEqual == false && checkDate == true && (check5 == true) ||
                    keyIsEqual == false && checkDate == true && (check6 == true) ||
                    keyIsEqual == false && checkDate == true && (check7 == true)) {
                    ok = false;
                } else {
                    ok = true;
                }
            } else {
                if (!isInArray && keyIsEqual == false && checkDate == true && (check1 == true) ||
                    !isInArray && keyIsEqual == false && checkDate == true && (check2 == true) ||
                    !isInArray && keyIsEqual == false && checkDate == true && (check3 == true) ||
                    !isInArray && keyIsEqual == false && checkDate == true && (check4 == true) ||
                    !isInArray && keyIsEqual == false && checkDate == true && (check5 == true) ||
                    !isInArray && keyIsEqual == false && checkDate == true && (check6 == true) ||
                    !isInArray && keyIsEqual == false && checkDate == true && (check7 == true)) {
                    ok = false;
                } else {
                    ok = true;
                }
            }
                //TODO: fix display message
            if (ok == false) {
                
                //gets name of double booked venue
                String prevStudentName = "tempStudName";
                for (int k = 0; k < va.getVenuesArray().size(); k++) {
                    if (va.getVenuesArray().get(k).getVenueID() == this.lessonDataArray.get(i).getVenueID()) {
                        tempvenue = "\nVenue: " + va.getVenuesArray().get(k).getVenue();
                    }
                    if (!tempTime.contains(this.lessonDataArray.get(i).getLessonTime())) {
                        tempTime += this.lessonDataArray.get(i).getLessonTime() + " - " + endTimeReference + ", ";
                    }
                } 
                for (int s = 0; s < sa.getStudentArray().size(); s++) {
                    if (sa.getStudentArray().get(s).getStudentID() == this.lessonDataArray.get(i).getStudentID() && !tempstudents.contains(prevStudentName)) {
                    tempstudents += sa.getStudentArray().get(s).getfName() + " " + sa.getStudentArray().get(s).getlName() + "\n                  ";
                    prevStudentName = sa.getStudentArray().get(s).getfName() + " " + sa.getStudentArray().get(s).getlName();
                }
            }
            }
        
        }      
    tempReturn = "You have already booked a lesson at this time and date:\n\nDate: " + date + "\nTime: " + tempTime + tempvenue + tempstudents;
    return tempReturn;
    } 
    
    public String getLessonKey(int lessonID) {
        lessonDataArray la = new lessonDataArray();
        keysArray ka = new keysArray();//creates an object for the keysArray class
        String key = "";
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            for (int k = 0; k < ka.getKeyArray().size(); k++) {
                if (this.lessonDataArray.get(i).getLessonID() == ka.getKeyArray().get(k).getLessonID()) {
                    key = ka.getKeyArray().get(k).getLessonKey();
                }
            }
        }
        return key;
    }
    
    public String generateLessonKey() {
        String generateString;
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
    
    public String getEndTime(String time, int duration) {
        String endTime = "";
        int endHour = 0;
        String mins = time.substring(3, 5);
        int startHour = Integer.parseInt(time.substring(0, 2));
        endHour = startHour + duration;
        if (endHour < 10) {
            endTime = "0"+endHour + ":" + mins;
        } else {
            endTime = endHour + ":" + mins;
        }
        return endTime;
    }

    public String GetEndTimeForSpecificStudent(int duration, String startTime, int i) { 
        String endTime = "";
        int endHour = 0;
        String mins = startTime.substring(3, 5);
        int startHour = Integer.parseInt(this.lessonDataArray.get(i).getLessonTime().substring(0, 2));
        endHour = startHour + duration;
        if (endHour < 10) {
            endTime = "0"+endHour + ":" + mins;
        } else {
            endTime = endHour + ":" + mins;
        }
        return endTime;
    }
    
    
    public boolean checkIfInPast(String refDate, String refTime) {
        String delete = "";
        boolean inPast = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM HH:mm");
        Date todayDate = new Date();
        try {
            Date formattedTodayDate = sdf.parse(this.formatDate(todayDate.toString()) + " " + this.formatTime(todayDate.toString()));
            Date date1 = sdf.parse(refDate + " " + refTime);
            if (date1.compareTo(formattedTodayDate) < 0) {
               inPast = true;          
            }
        } catch (ParseException ex) {
            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inPast;
    }
    
    public void DeletePastLessonsAndLessonKeys() {
        ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
        String deleteLessons = "";
        String deleteKeys = "";
        
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (this.checkIfInPast(this.lessonDataArray.get(i).getLessonDate(), this.lessonDataArray.get(i).getLessonTime()) == true) {
                deleteLessons = "DELETE * FROM lessonData WHERE LessonID = " + this.lessonDataArray.get(i).getLessonID();
                deleteKeys = "DELETE * FROM lessonKeys WHERE lessonID = " + this.lessonDataArray.get(i).getLessonID();
                try {
                    db.UpdateDatabase(deleteLessons);
                    db.UpdateDatabase(deleteKeys);
                } catch (SQLException ex) {
                    Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public String upcomingDate(int studentID) {
        String message = "";
        boolean booked = false;
        //checks if in fact booked a lesson
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (this.lessonDataArray.get(i).getStudentID() == studentID) {
                booked = true;
            }
        }
        //cheks if count id true i.e. a lesson has been booked by that student
        if (booked == true) {
            this.sortArray();
            String date = "";
        
            for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
                if (this.lessonDataArray.get(i).getStudentID() == studentID) {
                    date = this.lessonDataArray.get(i).getLessonDate();
                    break;
                }
            }
            return date;
        } else {
            return "N/A";
        }
        }
    
    public String upcomingTime(int studentID, String upcomingDate) {
        if (!this.upcomingDate(studentID).equals("N/A")) {
            String time = "";
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (upcomingDate.equals(this.lessonDataArray.get(i).getLessonDate()) && studentID == this.lessonDataArray.get(i).getStudentID()) {
                time = this.lessonDataArray.get(i).getLessonTime() + " - " +  this.getEndTime(this.lessonDataArray.get(i).getLessonTime(), this.lessonDataArray.get(i).getLessonDuration());
            }
        }
        return time;
        } else {
            return "N/A";
        }
        }
    
    public String upcomingVenue(int studentID, String upcomingDate, String upcomingTime) {
        if (!this.upcomingDate(studentID).equals("N/A")) {
            venueArray va = new venueArray();
        String venue = "";
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (upcomingDate.equals(this.lessonDataArray.get(i).getLessonDate()) && studentID == this.lessonDataArray.get(i).getStudentID() 
                    && upcomingTime.equals(this.lessonDataArray.get(i).getLessonTime())) {
                venue = va.venueNameFromID(this.lessonDataArray.get(i).getVenueID());
            }
        }
        return venue;
        } else {
            return "N/A";
        }
    }
    
    public String upcomingDay(int studentID, String upcomingDate, String upcomingTime) {
        if (!this.upcomingDate(studentID).equals("N/A")) {
            String day = "";
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (upcomingDate.equals(this.lessonDataArray.get(i).getLessonDate()) && studentID == this.lessonDataArray.get(i).getStudentID() 
                    && upcomingTime.equals(this.lessonDataArray.get(i).getLessonTime())) {
                day = this.lessonDataArray.get(i).getDay();
            }
        }
        return day;
        } else {
            return "N/A";
        }
    }
    
    public String getLessonStartTimeFromLessonID(int id) {
        String time = "";
        for (int i = 0;  i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (this.lessonDataArray.get(i).getLessonID() == id) {
                time = this.lessonDataArray.get(i).getLessonTime();
            }
        }
        return time;
    }
    
    public String getLessonDateFromLessonID(int id) {
        String date = "";
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (this.lessonDataArray.get(i).getLessonID() == id) {
                date = this.lessonDataArray.get(i).getLessonDate();
            }
        }
          return date;      
    }
    
    public String getLessonEndTimeFromLessonID(int id) {
        String time = "";
        for (int i = 0;  i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (this.lessonDataArray.get(i).getLessonID() == id) {
                time = this.getEndTime(this.lessonDataArray.get(i).getLessonTime(), this.lessonDataArray.get(i).getLessonDuration());
            }
        }
        return time;
    }
    
    public String getLessonTimeFromStartTimeAndDuration(String startTime, int duration) {
        String time = startTime + " - " + this.getEndTime(startTime, duration);
        return time;
    }
    
    public String getTimeFromLessonID(int id) {
        String time = "";
        for (int i = 0;  i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (this.lessonDataArray.get(i).getLessonID() == id) {
                time = this.lessonDataArray.get(i).getLessonTime() + "-" + this.getEndTime(this.lessonDataArray.get(i).getLessonTime(), this.lessonDataArray.get(i).getLessonDuration());
            }
        }
        return time;
    }
    
    public int countLessonsFromStudentID(int id) {
        int lessons = 0;
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (this.lessonDataArray.get(i).getStudentID() == id) {
                lessons++;
            }
        }
        return lessons;
    }
    
    public String getDateFromKeyAndStudentID(String key, int id) {
        keysArray ka = new keysArray();//creates an object for the keysArray class
        String date = "";
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (ka.getKeyFromLessonID(this.lessonDataArray.get(i).getLessonID()).equals(key) &&
                    this.lessonDataArray.get(i).getStudentID() == id) {
                date = this.lessonDataArray.get(i).getLessonDate();
                break;
            }
        }
        return date;
    }
    
    public int getLessoIDFromDateTimeAndStudentID(String date, String time, int studentID) {
        int id = 0;
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            String startTime = time.substring(0, 5);
            if (this.lessonDataArray.get(i).getLessonDate().equals(date) &&
                    this.lessonDataArray.get(i).getLessonTime().equals(startTime) &&
                    this.lessonDataArray.get(i).getStudentID() == studentID) {
                id = this.lessonDataArray.get(i).getLessonID();
            }
        }
        return id;
    }
    
    public int getLessonIDFromDateAndTime(String date, String time) {
        int id = 0;
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (this.lessonDataArray.get(i).getLessonDate().equals(date) && this.lessonDataArray.get(i).getLessonTime().equals(time)) {
                id = this.lessonDataArray.get(i).getLessonID();
            }
        }
        return id;
    }
    
    public int getLessonIDFromDuringTimeAndDate(String date, String duringTime) {
        CalendarHandler ch = new CalendarHandler();
        int id = 0;
        String startTime = ch.floorStartTime(date, duringTime);
        for (int i = 0; i < this.getLessonDataArray().size(); i++) {
            if (this.getLessonDataArray().get(i).getLessonDate().equals(date) && this.getLessonDataArray().get(i).getLessonTime().equals(startTime)) {
                id = this.getLessonDataArray().get(i).getLessonID();
            }
        }
        return id;
    }
    
    public String lessonComponentsExist() {
        String temp = "";
        venueArray va = new venueArray();
        studentsArray sa = new studentsArray();
        schoolsArray sca = new schoolsArray();
        int v = va.getVenuesArray().size();
        int s = sa.getStudentArray().size();
        int sc = sca.getSchoolsDataArray().size();
        if (v == 0) {
            temp += "() Please add a venue to the database before you add a lesson\n";
        }
        if (s == 0) {
            temp += "() Please add a student to the database before you add a lesson\n";
        }
        if (sc == 0) {
            temp += "() Please add a school to the database before you add a lesson\n";
        }
        return temp;
    }
    
}


