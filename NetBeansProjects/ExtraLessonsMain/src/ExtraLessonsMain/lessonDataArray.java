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
        
        int day1 = Integer.parseInt(date1.substring(5, 7));//creates an integer representation of the day1 passed in
        int day2 = Integer.parseInt(date2.substring(5, 7));//creates an integer representation of the day2 passed in
        int month1 = Integer.parseInt(date1.substring(8, 10));//creates an integer representation of the month1 passed in
        int month2 = Integer.parseInt(date2.substring(8, 10));//creates an integer representation of the month2 passed in
        //checks if the days are in the same month
        if (month2 - month1 != 0) {//checks if the month not the same
            //gest days in each of the months in question
            int daysInMonth1 = this.checkDaysInMonth(date1);//creates an int for the days in the month of date1
            if ((day2 + daysInMonth1 - day1) == 7) {//checks if the dates are a week apart
                weekly = true;//flips weekly to true
            }
        } else {//if the dates are in the same month
            if (day2 - day1 == 7) {//checks if a week apart
                weekly = true;//fips weekly to true
            }
        }
        return weekly;//returns weekly
    }//closes the isWeekApart method
    
    public boolean isMonthArpart(String date1, String date2) {//creates if two passed in are month
        boolean monthly = false;//creates a boolean indicating whether the dates are a month apart
        
        int day1 = Integer.parseInt(date1.substring(5, 7));//creates an integer representation of the day1 passed in
        int day2 = Integer.parseInt(date2.substring(5, 7));//creates an integer representation of the day2 passed in
        int month1 = Integer.parseInt(date1.substring(8, 10));//creates an integer representation of the month1 passed in
        int month2 = Integer.parseInt(date2.substring(8, 10));//creates an integer representation of the month2 passed in
        
        if (month2 - month1 == 1 || month2 - month1 == -11) {//checks of the dates are a month apart
                    if (day2 == day1) {//checks if the day is the same
                        monthly = true;//flips monthly to true
                    }
                }
        return monthly;//returns monthly
    }//closes the isMonthApart method
    
    public void AddStudentsAdded(String name) {//creates a method to add student to the names array
        this.names.add(name);//adds the passed in student
    }//closes the addStudentAdded method
    
    public void removeStudentAdded(String name) {//creates a method to remove student from the names array
        this.names.remove(name);//removes the passed in student
    }
    
    public String getArrayOfStudentsAdded(int index) {
        String namesArray [] = this.names.toArray(new String[this.names.size()]);
        return namesArray[index];
    }//closes the removeStudentAdded method
    
    public String [] getArrayOfStudentsAddedNoIndex() {//creates a method to return a string array representation of the names array list
        String namesArray [] = this.names.toArray(new String[this.names.size()]);//creates a string array of the names array list
        return namesArray;//returns namesArray
    }//closes the getArrayOfStudentsAddedNoIndex method
    
    public void addToNamesList(String name) {//creates a method to add a student to the names list
        if (!names.contains(name)) {//checks if the passed in name does not already exists in the names ist
            this.AddStudentsAdded(name);//adds the passed in name to the list
        }
    }//closes the addToNamesList method
    
    public void sortArray() {//creates a method to sort the lessonDataArray list
        //sorts by dates and times
         Collections.sort(this.lessonDataArray, new Comparator<fetchLessonData>() {//opens a comparator dependant sort method
             public int compare(fetchLessonData l1, fetchLessonData l2) {//creates a method to compare the dates and time of two passed in lessons
                 int comp = 0;//creates an int to compare the teo dates
                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM HH:mm");//creates a simple date format
                 try {//opens the trycatch statement
                     Date date1 = sdf.parse(l1.getLessonDate() + " " + l1.getLessonTime());//creates a date object for the first lesson
                     Date date2 = sdf.parse(l2.getLessonDate() + " " + l2.getLessonTime());//creates a date object for the second lesson
                     comp = date1.compareTo(date2);//sets the comp int according to the comparison of the two dates
                 } catch (ParseException ex) {//opens the catch
                     Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error setting the times of the date objects
                 }//closes the catch statement
                  return comp;//returns the comp int
             }
             public int compareLessons(fetchLessonData p1, fetchLessonData p2) {//creates a method to compare the two dates (abstract method)
                 throw new UnsupportedOperationException("Not supported yet.");//throws the not supported message to the class user
             }
         });//closes the comparator
    }//closes the sortArray method
    
    public String formatDate(String date) {//creates a method to format a parsed in date string from the JDateChooser
        String finalDate = "";//creates a string to the formatted date
        //create a date fromatter
        DateFormat sdf = new SimpleDateFormat("yyyy/dd/MM");//creates a simple date formatter
        Calendar cal = Calendar.getInstance();//instantiates a calendar object
        //gets substrings of the passed in full date
        String day = date.substring(8, 10);//gets the day
        String month = date.substring(4, 7);//gets the month
        String year = date.substring(25);//gets the year
        
        //checks and formats month
        int finalMonth = 0;//creates an int for the formatted month
        switch (month) {//opens the switchcase statement according to the month
            case "Jan"://starts the jan case
                finalMonth = 1;//sets the month int accordingly
                break;//discontinues the current case
            case "Feb"://starts the feb case
                finalMonth = 2;//sets the month int accordingly
                break;//discontinues the current case
            case "Mar"://starts the mar case
                finalMonth = 3;//sets the month int accordingly
                break;//discontinues the current case
            case "Apr"://starts the apr case
                finalMonth = 4;//sets the month int accordingly
                break;//discontinues the current case
            case "May"://starts the may case
                finalMonth = 5;//sets the month int accordingly
                break;//discontinues the current case
            case "Jun"://starts the jun case
                finalMonth = 6;//sets the month int accordingly
                break;//discontinues the current case
            case "Jul"://starts the jul case
                finalMonth = 7;//sets the month int accordingly
                break;//discontinues the current case
            case "Aug"://starts the aug case
                finalMonth = 8;//sets the month int accordingly
                break;//discontinues the current case
            case "Sep"://starts the sep case
                finalMonth = 9;//sets the month int accordingly
                break;//discontinues the current case
            case "Oct"://starts the oct case
                finalMonth = 10;//sets the month int accordingly
                break;//discontinues the current case
            case "Nov"://starts the nov case
                finalMonth = 11;//sets the month int accordingly
                break;//discontinues the current case
            case "Dec"://starts the dec case
                finalMonth = 12;//sets the month int accordingly
                break;//discontinues the current case
        }//closes the switchcase statement
        if (finalMonth < 10) {//checks if the month int is over 10
            finalDate = year + "/" + day + "/" + "0" + ""+finalMonth;//formats the final date
        } else {//if the month int is less than 10
            finalDate = year + "/" + day + "/" + "" + ""+finalMonth;//formats the final date
        }
        try {//opens the trycatch statement
            cal.setTime(sdf.parse(finalDate)); //sets the time of the cal object to the final date string
        } catch (ParseException ex) {//opens the catch statement
            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user there was a problem setting the time of the calendar objects
        }//closes the catch statement
        return sdf.format(cal.getTime());//returns the formatted date
    }//closes the formatDate method
    
    public String formatTime(String date) {//creates a method to format the time of a passed in date
        String time = date.substring(11, 16);//creates a string of the formatted time
        return time;//returns the formatted time
    }//closes the formatTime method
    
    public String formatDay(String day) {//creates a method to format the day of the date passed in
        String finalDay = "";//creates a string to hold the formatted day
        //gets substring of day from given full date
        String subbedDay = day.substring(0, 3);//creates a string to hold the substring of the not formatted day i.e. Thu or Sat
         switch (subbedDay) {//opens the switchcase statement
             case "Sun"://opens the case for sun
                 finalDay = "sunday";//sets the finalDay String to sunday
                 break;//discontinues the current case
            case "Mon"://opens the case for mon
                 finalDay = "monday";//sets the finalDay String to monday
                 break;//discontinues the current case
            case "Tue"://opens the case for tue
                 finalDay = "tuesday";//sets the finalDay String to tuesday
                 break;//discontinues the current case
            case "Wed"://opens the case for wed
                 finalDay = "wednesday";//sets the finalDay String to wednesday
                 break;//discontinues the current case
            case "Thu"://opens the case for thu
                 finalDay = "thursday";//sets the finalDay String to thrusday
                 break;//discontinues the current case
            case "Fri"://opens the case for fri
                 finalDay = "friday";//sets the finalDay String to friday
                 break;//discontinues the current case
            case "Sat"://opens the case for sat
                 finalDay = "saturday";//sets the finalDay String to saturday
                 break;//discontinues the current case
         }//closes the switchcase statement
         return finalDay;//returns the formatted day
    }//closes the formatedDay method
    
    public void addLesson(String venue, String date, String time, String day, int size, String name, int frequency, int duration, String lessonKeyToAdd, boolean paid, int cost) {//creates a method to add a lesson to the database
        ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
        lessonDataArray la = new lessonDataArray();
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        keysArray ka = new keysArray();//creates an object for the keysArray class
        paymentsArray pa = new paymentsArray();//creates an object for the paymentsArray class
        venueArray va = new venueArray();//creates an object for the venueArray class
        int countDisplayMessages = 0;//creates an int to count the number of displayed messages
        //int countLessonIDForKeys = 1;
        
        if (this.checkIfInPast(date, time) == false) {//checks if the lesson is not set to be in the past
            if(!this.checkIfDoublebooking(date, time, duration, size, lessonKeyToAdd).contains("Venue")) {//checks if the lesson is not set to be double booked
        int id = sa.studentIDFromName(name);//creates an integer for the student id
        DateFormat sdf = new SimpleDateFormat("yyyy/dd/MM");//create a date fromatter
        Calendar cal = Calendar.getInstance();//instantiates a calendar object
        try {//opens the trycatch statement
            cal.setTime(sdf.parse(date));//sets the date of the cal object to the date passed in
        } catch (ParseException ex) {//opens the cacth
            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error setting the date of cal
        }//closes the catch
        String insert;//instatntiates a string for the SQL query used to insert the lesson object
        String insertKey;//instatntiates a string for the SQL query used to insert the key object
        String insertPaid;//instatntiates a string for the SQL query used to insert the payment object
        if (frequency == 0) {//checks if the frequency passed in is 0 i.e. once off
            insert = "INSERT INTO lessonData (studentID, venueID, lessonDate, lessonTime, lessonDuration, lessonDay) VALUES (" + id + ", " 
                    + va.venueIDFromVenue(venue) + ", '" + sdf.format(cal.getTime()) + "', '" + time + "', " + duration + ", '" + day + "')";//sets the insertlesson string to the formatted query
            insertKey = "INSERT INTO lessonKeys (lessonKey) VALUES ('" + lessonKeyToAdd + "')";//sets the insertkey string to the formatted query
            insertPaid = "INSERT INTO sPayTable (StudID, Paid, PayDate, PayTime, PayDuration, Cost) VALUES "
                    + "(" + id + ", " + paid + ", '" + sdf.format(cal.getTime()) + "', '" + time + "', " + duration + ", " + cost + ")";//sets the insertpayments string to the formatted query
            try {//opens the trycacth statement
                db.UpdateDatabase(insert);//inserts the lesson
                db.UpdateDatabase(insertKey);//inserts the key
                db.UpdateDatabase(insertPaid);//inserts the payment
            } catch (SQLException ex) {//opens the catch statement
                Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error adding the lesson
            }//closes the catch statement
        } else {//if the frequency is not 0
            if (frequency == 1) {//checks if the frequency is 1 i.e. weekly
                for (int i = 0; i < 52; i++) {//starts a for loop from 0 to 51 (weeks in the year)
                    insert = "INSERT INTO lessonData (studentID, venueID, lessonDate, lessonTime, lessonDuration, lessonDay) VALUES (" + id + ", " 
                    + va.venueIDFromVenue(venue) + ", '" + sdf.format(cal.getTime()) + "', '" + time + "', " + duration + ", '" + day + "')";//sets the insertlesson string to the formatted query
                    insertKey = "INSERT INTO lessonKeys (lessonKey) VALUES ('" + lessonKeyToAdd + "')";//sets the insertkey string to the formatted query
                    insertPaid = "INSERT INTO sPayTable (StudID, Paid, PayDate, PayTime, PayDuration, Cost) VALUES "
                    + "(" + id + ", " + paid + ", '" + sdf.format(cal.getTime()) + "', '" + time + "', " + duration + ", " + cost + ")";//sets the insertpayments string to the formatted query
                    try {//opens the trycacth statement
                        db.UpdateDatabase(insert);//inserts the lesson
                        db.UpdateDatabase(insertKey);//inserts the key
                        db.UpdateDatabase(insertPaid);//inserts the payment
                    } catch (SQLException ex) {//opens the catch statement
                        Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error adding the lesson
                    }//closes the catch statement
                    cal.add(cal.DATE, 7);//adds a week to the date
                }
            } else {//the frequency is not 1
                if (frequency == 2) {//checks if the frequency is 2 i.e. monthly
                    for (int i = 0; i < 12; i++) {//starts a for loop from 0 to 11 (months in the year)
                        insert = "INSERT INTO lessonData (studentID, venueID, lessonDate, lessonTime, lessonDuration, lessonDay) VALUES (" + id + ", " 
                        + va.venueIDFromVenue(venue) + ", '" + sdf.format(cal.getTime()) + "', '" + time + "', " + duration + ", '" + day + "')";//sets the insertlesson string to the formatted query
                        insertKey = "INSERT INTO lessonKeys (lessonKey) VALUES ('" + lessonKeyToAdd + "')";//sets the insertkey string to the formatted query
                        insertPaid = "INSERT INTO sPayTable (StudID, Paid, PayDate, PayTime, PayDuration, Cost) VALUES "
                        + "(" + id + ", " + paid + ", '" + sdf.format(cal.getTime()) + "', '" + time + "', " + duration + ", " + cost + ")";//sets the insertpayments string to the formatted query
                        try {//opens the trycacth statement
                            db.UpdateDatabase(insert);//inserts the lesson
                            db.UpdateDatabase(insertKey);//inserts the key
                            db.UpdateDatabase(insertPaid);//inserts the payment
                        } catch (SQLException ex) {//opens the catch statement
                            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error adding the lesson
                        }//closes the catch statement
                        cal.add(cal.MONTH, 1);//adds a month to the date
                    }
                }
            }
        }
        STUDENTS_ADDED_TO_LESSON++;//ups the students_added_to_lesson static int
        if (STUDENTS_ADDED_TO_LESSON == size) {//checks if the students_added_to_lesson static int is equal to the number of students set to be attending the lesson
            JOptionPane.showMessageDialog(null, "the lesson(s) have been added!");//alerts the user that the lessons have been added
        }
        } else {//if the lesson is set to be double booked
            if (countDisplayMessages == 0) {//checks that there has not already been a message displayed
                JOptionPane.showMessageDialog(null, this.checkIfDoublebooking(date, time, duration, size, lessonKeyToAdd));//alerts the user of the lesson that is already in place at the attempted booking time
            }
            countDisplayMessages++;//ups countDisplayMessages indicating that the double booking message has been shown
        }
        } else {//if the lesson is set to be booked in the past
            JOptionPane.showMessageDialog(null, "please do not try to book a lesson in the past");//alerts the user to not book a lesson in the past
        }
    }//closes the addLesson method

//    public String [] notInNewStudents(String [] list1, ArrayList<String> list2) { 
//        ArrayList<String> notIn = new ArrayList(Arrays.asList(list1));
//        for (int i = 0; i < list2.size(); i++) {
//            if (notIn.contains(list2.get(i))) {
//                notIn.remove(list2.get(i));
//            } else {
//                notIn.add(list2.get(i));
//                studentLost = list1[i];
//            }
//        }
//         String StringArray [] = notIn.toArray(new String[notIn.size()]);
//         return StringArray;
//    }
    
    public void deleteLesson(int lessonID, String date, String time, String lesson) {//creates a method to delete a lesson
        ConnectDB  db = new ConnectDB();//creates an object for the ConnectDB class
        keysArray ka = new keysArray();//creates an object for the keysArray class
        paymentsArray pa = new paymentsArray();//creates an object for the paymentsArray class
        CalendarHandler ch = new CalendarHandler();//creates an object for the calendarHandler class
        int yes = JOptionPane.YES_OPTION;//creates an int for the yes option
        int no = JOptionPane.NO_OPTION;//creates an int for the no option
        int deletePaymentsDialog = JOptionPane.showConfirmDialog(null, "Continuing will delete all information regarding this lesson\n " + lesson + "\nSelect 'Yes' if you wish to delete all payment info as well.", "delete lesson", JOptionPane.YES_NO_OPTION);//asks the user if they would like to delete all of the associated payemnts
        int deleteAllDialog = JOptionPane.showConfirmDialog(null, "Continuing will delete all information regarding this lesson\n " + lesson + "\nSelect 'Yes' if you wish to delete all of the lessons in this group.\nSelect 'No' if you wish to delete only the selected lesson", "delete lesson", JOptionPane.YES_NO_CANCEL_OPTION);//asks the user if they would like to delete all of the lessons in the group or just the selected lesson
        String key = ka.getKeyFromDateAndTime(date, time);//creates a string for the key of the selected lesson
        
        if (deleteAllDialog == no) {//if the user would only like to delete the selected lesson
            if (deletePaymentsDialog == yes) {//if the user would like to delete the payment info
                    for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
                        int lessonID2 = this.lessonDataArray.get(i).getLessonID();//creates an int for the iterated lessonID
                        if (ka.getKeyFromLessonID(lessonID2).equals(key) && this.lessonDataArray.get(i).getLessonDate().equals(date) && this.lessonDataArray.get(i).getLessonTime().equals(time)) {//checks if the iterated lesson is the same as the one specified by the passed in data
                            String deletePayments = "DELETE * FROM sPayTable WHERE lessonID = " + lessonID2;//creates a string to hold the SQL query to delete the payments
                            String deleteKeys = "DELETE * FROM lessonKeys WHERE lessonID = " + lessonID2;//creates a string to hold the SQL query to delete the lessonkey
                            String deleteLessons = "DELETE * FROM lessonData WHERE LessonID = " + lessonID2;//creates a string to hold the SQL query to delete the lesson
                            try {//opens the trycatch statement
                                db.UpdateDatabase(deletePayments);//deletes the payments
                                db.UpdateDatabase(deleteKeys);//deletes the keys
                                db.UpdateDatabase(deleteLessons);//deletes the lesson
                            } catch (SQLException ex) {//opens the catch statement
                                Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error deleteing the lesson
                            }//closes the catch
                        }
                    }
                } else {//if the user does not want to delete the payments
                    for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
                        int lessonID2 = this.lessonDataArray.get(i).getLessonID();//creates an int for the iterated lessonID
                        if (ka.getKeyFromLessonID(lessonID2).equals(key) && this.lessonDataArray.get(i).getLessonDate().equals(date) && this.lessonDataArray.get(i).getLessonTime().equals(time)) {//checks if the iterated lesson is the same as the one specified by the passed in data
                            String deleteKeys = "DELETE * FROM lessonKeys WHERE lessonID = " + lessonID2;//creates a string to hold the SQL query to delete the keys
                            String deleteLessons = "DELETE * FROM lessonData WHERE LessonID = " + lessonID2;//creates a string to hold the SQL query to delete the lesson
                            try {//opens the trycatch statement
                                db.UpdateDatabase(deleteKeys);//deletes the keys
                                db.UpdateDatabase(deleteLessons);//deletes the lesson
                            } catch (SQLException ex) {//opens the catch statement
                                Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error deleteing the lesson
                            }//closes the catch statement
                        }
                    }
                } 
        } else {//if the user would not like to delete only the selected lesson
            if (deleteAllDialog == yes) {//checks if the user wants to delete all the lessons in the group
                if (deletePaymentsDialog == yes) {//checks if the user wants to delete the payment info
                    for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
                        int lessonID2 = this.lessonDataArray.get(i).getLessonID();//creates an int for the iterated lesson id
                        if (ka.getKeyFromLessonID(lessonID2).equals(key)) {//checks if the key of the iterated lesson is the same as the key passed in
                            String deletePayments = "DELETE * FROM sPayTable WHERE lessonID = " + lessonID2;//creates a string to hold the SQL query to delete the payments
                            String deleteKeys = "DELETE * FROM lessonKeys WHERE lessonID = " + lessonID2;//creates a string to hold the SQL query to delete the keys
                            String deleteLessons = "DELETE * FROM lessonData WHERE LessonID = " + lessonID2;//creates a string to hold the SQL query to delete the lesson
                            try {//opens the trycatch statement
                                db.UpdateDatabase(deletePayments);//deletes the payments
                                db.UpdateDatabase(deleteKeys);//deletes the keys
                                db.UpdateDatabase(deleteLessons);//deletes the lessons
                            } catch (SQLException ex) {//opens the catch statement
                                Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error deleteing the lesson
                            }//closes the catch
                        }
                    }
                } else {//if the user wants to retain the payments info
                    for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
                        int lessonID2 = this.lessonDataArray.get(i).getLessonID();//creates an int for the iterated lesson id
                        if (ka.getKeyFromLessonID(lessonID2).equals(key)) {//checks if the key of the iterated lesson is the same as the key passed in
                            String deleteKeys = "DELETE * FROM lessonKeys WHERE lessonID = " + lessonID2;//creates a string to hold the SQL query to delete the key
                            String deleteLessons = "DELETE * FROM lessonData WHERE LessonID = " + lessonID2;//creates a string to hold the SQL query to delete the lesson
                            try {//opens the trycatch statement
                                db.UpdateDatabase(deleteKeys);//deletes the keys
                                db.UpdateDatabase(deleteLessons);//deletes the lessons
                            } catch (SQLException ex) {//opens the catch statement
                                Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error deleteing the lesson
                            }//closes the catch statement
                        }
                    }
                } 
            } else {//if the user does not wish to delete the lesson
               JOptionPane.showMessageDialog(null, "Lesson Saved");//alerts the user that the deletion was cancelled
            }
        } 
    }//closes the deleteLesson method
    
    public String getLessonDayFromLessonID(int id) {//gets the day of the lesson from the passed in lesson id
        String day = "";//creates a string to hold the day
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (this.lessonDataArray.get(i).getLessonID() == id) {//checks if the iterated id is the same as the one passed in
                day = this.lessonDataArray.get(i).getDay();//sets the day string to the iterated day
            }
        }
        return day;//returns the day string
    }//closes the getLessonDayFromLessonID method
    
    public void deleteStudentsInSpecificLesson(String date, String time) {//creates a method to delete all students from a selected lesson (used in the editing method)
       ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
       keysArray ka = new keysArray();//creates an object for the keysArray class
       CalendarHandler ch = new CalendarHandler();//creates an object for the calendarHandler class
       paymentsArray pa = new paymentsArray();//creates an object for the paymentsArray class
       studentsArray sa = new studentsArray();//creates an object for the studentsArray class
       venueArray va = new venueArray();//creates an object for the venueArray class
       
       String students [] = ch.studentsFromLessonDateAndTime(date, time);//creates a string array fro the students attending the lesson
       for (int i = 0; i < students.length; i++) {//iterates through the students in the students array
           int studentID = sa.studentIDFromName(students[i]);//creates an integer for the iterated student id
           int lessonID = this.getLessoIDFromDateTimeAndStudentID(date, time, studentID);//creates an integer for the lesson id according to the iterated student id
           String deleteKey = "DELETE * FROM lessonKeys WHERE lessonID = " + lessonID;//creates a string holding the SQL quary used to delete the lesson keys
           String deletePayments = "DELETE * FROM sPayTable WHERE lessonID = " + lessonID;//creates a string holding the SQL quary used to delete the payment information
           String deleteLesson = "DELETE * FROM lessonData WHERE LessonID = " + lessonID;//creates a string holding the SQL quary used to delete the lesson lessons
           try {//opens the trycatch statement
               db.UpdateDatabase(deleteKey);//deletes the keys
               db.UpdateDatabase(deletePayments);//deletes the payments
               db.UpdateDatabase(deleteLesson);//deletes the lessons
           } catch (SQLException ex) {//opens the catch statement
               Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an arror deleteng the lessons
           }//closes the catch
       }
    }//closes the deleteStudentsInSpecificLesson method
    
    public void deletStudentsInAllLesson(String date, String time, String key) {//creates a method to delete all students from a lesson group (by key) (used in the editing method)
       ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
       keysArray ka = new keysArray();//creates an object for the keysArray class
       CalendarHandler ch = new CalendarHandler();//creates an object for the calendarHandler class
       paymentsArray pa = new paymentsArray();//creates an object for the paymentsArray class
       studentsArray sa = new studentsArray();//creates an object for the studentsArray class
       venueArray va = new venueArray();//creates an object for the venueArray class
       
       String students [] = ch.studentsFromLessonDateAndTime(date, time);//creates a string array fro the students attending the lesson
       for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
           int lessonID = this.lessonDataArray.get(i).getLessonID();//creates an integer for the lesson id according to the iterated student id
           if (ka.getKeyFromLessonID(lessonID).equals(key)) {//checks if the iterated key is the same as the passed in key
               int studentID = this.lessonDataArray.get(i).getStudentID();//creates an integer for the iterated student id
               String deleteKey = "DELETE * FROM lessonKeys WHERE lessonID = " + lessonID;//creates a string holding the SQL quary used to delete the lesson keys
                String deletePayments = "DELETE * FROM sPayTable WHERE lessonID = " + lessonID;//creates a string holding the SQL quary used to delete the lesson payments information
                String deleteLesson = "DELETE * FROM lessonData WHERE LessonID = " + lessonID;//creates a string holding the SQL quary used to delete the lesson lessons
                try {//opens the trycatch statement
                    db.UpdateDatabase(deleteKey);//deletes the keys
                    db.UpdateDatabase(deletePayments);//deletes the payments
                    db.UpdateDatabase(deleteLesson);//deletes the lessons
                } catch (SQLException ex) {//opens the catch statement
                    Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an arror deleteng the lessons
                }
            }
       }
    }//closes the deletStudentsInAllLesson method
    
    public int formatFrequency(String freq) {//creates a method to format the frequency string passed in to an integer
        int freqInt = 0;//creates an int to store the frequency
        if (freq.toLowerCase().startsWith("week")) {//checks if the frequency passed in is weekly
            freqInt = 1;//sets the frequency integer to 1
        } else {//if the passed in frequency is not weekly
            if (freq.toLowerCase().startsWith("month")) {//checks if the frequency passed in is monthly
                freqInt = 2;//sets the frequency integer to 2
            }
        }
        return freqInt;//returns the freqInt integer
    }//closes the formatFrequency method
    
    public void editAllLessonStudents(ArrayList<String> list, int id, String date, String time) {//creates a method to edit all lesson within a key group
       ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
       keysArray ka = new keysArray();//creates an object for the keysArray class
       CalendarHandler ch = new CalendarHandler();//creates an object for the calendarHandler class
       paymentsArray pa = new paymentsArray();//creates an object for the paymentsArray class
       venueArray va = new venueArray();//creates an object for the venueArray class
       studentsArray sa = new studentsArray();
       
       String [] studentsOriginal = ch.studentsFromLessonDateAndTime(date, time);//creates a string array for the students who were attending the lesson
       ArrayList<String> origList = this.ArrayListFromNamesArray(studentsOriginal);//creates an array list representation of the above array
       
       ArrayList<String> listRetains = new ArrayList<>();//creates an object for the students retained in a lesson
       for (int i = 0; i < list.size(); i++) {//iterates through the students in the passed in list
           listRetains.add(list.get(i));//adds the iterated student to the listRetains array list
       }
     
       String Key = ka.getKeyFromLessonID(id);//creates a string to hold the key of the lesson passed in
       int frequency = this.formatFrequency(this.getFrequencyFromKey(Key));//creates an integer for the frequency of the lesson
       int cost = pa.getCostFromLessonID(id);//creates an int for the cost
       boolean paid = pa.getIfPaidFromLessonID(id);//creates a boolean indicating whether the students have paid
       int venueID = va.getVenueIDFromLessonID(id);//creates an integer for the venue id of the lesson
       String venue = va.venueNameFromID(venueID);//creates a string for the venue name
       String day = this.getLessonDayFromLessonID(id);//creates a string for the day
       int duration = this.getDurationFromTimeAndDate(time, date);//creates an integer regarding the duration of the lesson

        for (int i = 0; i < list.size(); i++) {//iterates through the students list passed in
            if (!origList.contains(list.get(i))) {//checks if the the itetrated student does not already part of the lesson
                for (int k = 0; k < this.lessonDataArray.size(); k++) {//iterates through the lessons
                    String itDate = this.lessonDataArray.get(k).getLessonDate();//creates a string for the iterated lesson date
                    String itTime = this.lessonDataArray.get(k).getLessonTime();//creates a string for the iterated lesson time
                    ArrayList<String> attendingIteratedStudents = new ArrayList<>();//creates an array list for the students attending the iterated lesson
                    for (int s = 0; s < ch.studentsFromLessonDateAndTime(itDate, itTime).length; s++) {//iterates through the students attending
                        attendingIteratedStudents.add(ch.studentsFromLessonDateAndTime(itDate, itTime)[s]);//adds the iterated student to the attendingIteratedStudents array list
                    }
                    if (!attendingIteratedStudents.contains(list.get(i))) {//checks if the student is not already attending
                        if (this.getLessonKey(this.lessonDataArray.get(k).getLessonID()).equals(Key)) {//checks if the iterated key is the same as the lesson being edited
                            int studentID = sa.studentIDFromName(list.get(i));//creates an integer for the iterated student id
                            String insert = "INSERT INTO lessonData (studentID, venueID, lessonDate, lessonTime, lessonDuration, lessonDay) VALUES (" + studentID + ", " 
                                    + this.lessonDataArray.get(k).getVenueID() + ", '" + itDate + "', '" + itTime + "', " + this.lessonDataArray.get(k).getLessonDuration() + ", '" + this.lessonDataArray.get(k).getDay() + "')";//sets the insertlesson string to the formatted query
                            String insertKey = "INSERT INTO lessonKeys (lessonKey) VALUES ('" + Key + "')";//sets the insertkey string to the formatted query
                            String insertPaid = "INSERT INTO sPayTable (StudID, Paid, PayDate, PayTime, PayDuration, Cost) VALUES "
                                    + "(" + studentID + ", " + false + ", '" + itDate + "', '" + itTime + "', " + this.lessonDataArray.get(k).getLessonDuration() + ", " + pa.getCostFromLessonID(this.lessonDataArray.get(k).getLessonID()) + ")";//sets the insertpayments string to the formatted query
                            try {//opens the trycacth statement
                                db.UpdateDatabase(insert);//inserts the lesson
                                db.UpdateDatabase(insertKey);//inserts the key
                                db.UpdateDatabase(insertPaid);//inserts the payment
                            } catch (SQLException ex) {//opens the catch statement
                                Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error adding the lesson
                            }//closes the catch
                        }
                    }
                    
                }
                //this.addLessonForEdit(venue, date, time, day, list.size(), list.get(i),frequency,duration,Key, false, cost);//adds the lesson for the iterated student
            }
        }
       editLessonForm.ADDED_ARRAY.removeAll(editLessonForm.ADDED_ARRAY);//removes the items (student names) from the list displayed to the user as the JFrame dicontinues
       if (origList.size() >= listRetains.size()) {//checks if the original list is bigger than the retains list
           for (int i = 0; i < origList.size(); i++) {//iterates through the original list
               if (!listRetains.contains(origList.get(i))) {//checks if the retains list does not contain the iterated student
                   this.deleteStudentFromLesson(origList.get(i), Key);//deletes the iterated student from the lesson group
               }
           }
       }
   }//closes the editAllLessonStudents method
    
    public void deleteStudentFromLesson(String name, String key) {//creates a method to delete a student from a lesson group
        ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
       keysArray ka = new keysArray();//creates an object for the keysArray class
       CalendarHandler ch = new CalendarHandler();//creates an object for the calendarHandler class
       paymentsArray pa = new paymentsArray();//creates an object for the paymentsArray class
       studentsArray sa = new studentsArray();//creates an object for the studentsArray class
       venueArray va = new venueArray();//creates an object for the venueArray class
       
       for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
           int lessonID = this.lessonDataArray.get(i).getLessonID();//creates an integer for the lesson id according to the iterated student id
           int studentID = this.lessonDataArray.get(i).getStudentID();//creates an integer for the iterated student id
           if (ka.getKeyFromLessonID(lessonID).equals(key) && sa.studentNameFromID(studentID).equals(name)) {//checks if the iterated key is the same as the passed in key
               String deleteKey = "DELETE * FROM lessonKeys WHERE lessonID = " + lessonID;//creates a string holding the SQL quary used to delete the lesson keys
                String deletePayments = "DELETE * FROM sPayTable WHERE lessonID = " + lessonID;//creates a string holding the SQL quary used to delete the lesson payments information
                String deleteLesson = "DELETE * FROM lessonData WHERE LessonID = " + lessonID;//creates a string holding the SQL quary used to delete the lesson lessons
                try {//opens the trycatch statement
                    db.UpdateDatabase(deleteKey);//deletes the keys
                    db.UpdateDatabase(deletePayments);//deletes the payments
                    db.UpdateDatabase(deleteLesson);//deletes the lessons
                } catch (SQLException ex) {//opens the catch statement
                    Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an arror deleteng the lessons
                }
            }
       }
    }
    
   public void editSelectedLessonStudents(ArrayList<String> list, int id, String date, String time) {//creates a method to delete a selected lesson
       ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
       keysArray ka = new keysArray();//creates an object for the keysArray class
       CalendarHandler ch = new CalendarHandler();//creates an object for the calendarHandler class
       paymentsArray pa = new paymentsArray();//creates an object for the paymentsArray class
       studentsArray sa = new studentsArray();//creates an object for the studentsArray class
       venueArray va = new venueArray();//creates an object for the venueArray class

       String key = ka.getKeyFromLessonID(id);//creates a string for the key of the lesson
       int cost = pa.getCostFromLessonID(id);//creates an integer for the cost
       int venueID = va.getVenueIDFromLessonID(id);//creates an integer for the venue id
       String day = this.getLessonDayFromLessonID(id);//creates a string for the day
       int duration = this.getDurationFromTimeAndDate(time, date);//creates an int for the duration
       
       String [] studentsOriginal = ch.studentsFromLessonDateAndTime(date, time);;//creates an array representation of the students originally attending the lesson
       ArrayList<String> origList = this.ArrayListFromNamesArray(studentsOriginal);//creates an array list representation of the above array
       
           for (int i = 0; i < list.size(); i++) {//iterates through the list of students passed in
               if (!origList.contains(list.get(i))) {//checks if the the itetrated student does not already part of the lesson
                    int studentID = sa.studentIDFromName(list.get(i));//creates an integer for the iterated student id
                    int lessonID = this.getLessoIDFromDateTimeAndStudentID(date, time, studentID);//creates an integer fo the iterated students lesson id
                    boolean paid = pa.getIfPaidFromLessonID(lessonID);//creates a boolean indicating whther the students have paid
                    String insertLesson = "INSERT INTO lessonData (studentID, venueID, lessonDate, lessonTime, lessonDuration, lessonDay) "
                            + "VALUES ('" + studentID + "', '" + venueID + "', '" + date + "', '" + time + "', '" + duration + "', '" + day + "')";//creates a string for the SQL query used to insert the updated lesson
                    String insertKey = "INSERT INTO lessonKeys (lessonKey) VALUES ('" + key + "')";//creates a string for the SQL query used to insert the updated key
                    String insertPayment = "INSERT INTO sPayTable (StudID, Paid, PayDate, PayTime, PayDuration, Cost) "
                            + "VALUES ('" + studentID + "', '" + false + "', '" + date + "', '" + time + "', '" + duration + "', '" + cost + "')";//creates a string for the SQL query used to insert the updated payment info
                    try {//opens the trycatch statement
                        db.UpdateDatabase(insertLesson);//inserts the lessons
                        db.UpdateDatabase(insertKey);//inserts the key
                        db.UpdateDatabase(insertPayment);//inserts the payment info
                    } catch (SQLException ex) {//opens the catch statement
                        Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the user that there was an error editing the lessons
                    }//closes the catch statement
               }
           }
   }//closes the editSelectedLessonStudents method
   
   public ArrayList<String> ArrayListFromNamesArray(String [] arr) {//creates a method to create an array list from an array of students
       ArrayList<String> names = new ArrayList<>();//creates an array list for the students
       for (int i = 0; i < arr.length; i++) {//iterates through the students in the passed in array
           names.add(arr[i]);//adds the iterated student to the array list
       }
       return names;//returns the array list
   }//closes the ArrayListFromNamesArray method
   
   public ArrayList<Integer> idsFromKey(String key) {//creates a method to get the lesson ids from a key passed in
       keysArray ka = new keysArray();//creates an object for the keysArray class
       ArrayList<Integer> ids = new ArrayList<>();//creates an array list for the ids
       for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lessons
           if (ka.getKeyFromLessonID(this.lessonDataArray.get(i).getLessonID()).equals(key)) {//checks if the key of the iterated lesson ios the same as the key passed in
               ids.add(this.lessonDataArray.get(i).getLessonID());//adds the iterated id to the array list
           }
       }
       return ids;//returns the list
   }
   
   public ArrayList<Boolean> paidForEditing(String date, String time, String key) {//creates a method to return a list of the payment info regarding a key
       paymentsArray pa = new paymentsArray();//creates an object for the paymentsArray class
       ArrayList<Boolean> pays  = new ArrayList<>();//creates an array list for the booleans
       for (int i = 0; i < this.idsFromKey(key).size(); i++) {//iterates through the lessons for the key passed in
           pays.add(pa.getPaidFromID(this.idsFromKey(key).get(i)));//adds the payment state of the iterated lesson to the pays list
       }
      return pays;//returns the pays list
   }
   
   public void updateLessonDateTime(String originalDate, String originalTime, String newDateIn, String newTime, String newDuration) {//creates a method to update the date and time of a lesson
       ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
       keysArray ka = new keysArray();//creates an object for the keysArray class
       
       String newDate = this.formatDate(newDateIn);//creates a string for the newDate formatted version
       String day = this.formatDay(newDateIn.substring(0, 3));//creates a string for the new day formatted
       String key = ka.getKeyFromDateAndTime(originalDate, originalTime);//creates a string for the lesson key
       for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
           if (ka.getKeyFromLessonID(this.lessonDataArray.get(i).getLessonID()).equals(key) && this.lessonDataArray.get(i).getLessonDate().equals(originalDate) && this.lessonDataArray.get(i).getLessonTime().equals(originalTime)) {//checks if the iterated key date and time is the same as those passed in
               String updateDateTime = "UPDATE lessonData SET lessonDate = '" + newDate + "', lessonTime = '" + newTime + "', lessonDuration = " + newDuration + ", lessonDay = '" + day + "' "
                       + "WHERE LessonID = " + this.lessonDataArray.get(i).getLessonID();//creates a string for the SQL query used to update the lesson data
               String updatePayTime = "UPDATE sPayTable SET PayDate = '" + newDate + "', PayTime = '" + newTime + "', PayDuration = " + newDuration
                       + " WHERE lessonID = " + this.lessonDataArray.get(i).getLessonID();//creates a string for the SQL query used to update the peyment data
               try {//opens the trycatch statement
                   db.UpdateDatabase(updateDateTime);//updates the lesson data
                   db.UpdateDatabase(updatePayTime);//updates the payment data
               } catch (SQLException ex) {//opens the catch statement
                   Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error updating the date and time of the lesson
               }//closes the catch statement
           }
       }
   }//closes the updateLessonDateTime method
    
   public void editAllLessonVenue(String  venue, String date, String time) {//creates a method to update all the venues of the lessons in a key group
       ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
       venueArray va = new venueArray();//creates an object for the venueArray class
       keysArray ka = new keysArray();//creates an object for the keysArray class

       int venueID = va.venueIDFromVenue(venue);//creates an integer for the id of the venue passed in
       String key = ka.getKeyFromDateAndTime(date, time);//creates a string for the key of the lesson
       for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
           if (ka.getKeyFromLessonID(this.lessonDataArray.get(i).getLessonID()).equals(key)) {//checks if the key of the iterated lesson is the same as the one passed in
               String updateVenue = "UPDATE lessonData SET venueID = " + venueID + " "
                       + "WHERE LessonID = " + this.lessonDataArray.get(i).getLessonID();//creates a string holding the SQL query used to update the venue id of a lesson
               try {//opens the trycatch statement
                   db.UpdateDatabase(updateVenue);//updates the venue id
               } catch (SQLException ex) {//opens the catch statement
                   Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error updating the venue id
               }//closes the catch
           }
       }
   }//closes the editAllLessonVenue method
   
   public void editSelectedLessonVenue(String  venue, String date, String time) {//
       ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
       venueArray va = new venueArray();//creates an object for the venueArray class
       keysArray ka = new keysArray();//creates an object for the keysArray class
       
       int venueID = va.venueIDFromVenue(venue);//creates a string for the venue id of the venue passed in
       String key = ka.getKeyFromDateAndTime(date, time);//creates a string for the key of the lesson
       for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
           if (ka.getKeyFromLessonID(this.lessonDataArray.get(i).getLessonID()).equals(key) && this.lessonDataArray.get(i).getLessonDate().equals(date) && this.lessonDataArray.get(i).getLessonTime().equals(time)) {//checks if the key and date and time of the iterated lesson is the same as those passed in
               String updateVenue = "UPDATE lessonData SET venueID = " + venueID + " "
                       + "WHERE LessonID = " + this.lessonDataArray.get(i).getLessonID();//creates a string for the SQL query used to update the venue id
               try {//opens the trycatch statement
                   db.UpdateDatabase(updateVenue);//uodates the venue id
               } catch (SQLException ex) {//opens the catch statement
                   Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the user that there was an error updating the venue id
               }//closes the catch statement
           }
       }
   }//closes the editSelectedLessonVenue method
   
    public int getDurationFromTimeAndDate(String time, String date) {//creates a method to get the duration from the date an time of a lesson
        int duration = 0;//creates an integer to hold the duration
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (this.lessonDataArray.get(i).getLessonTime().equals(time) && this.lessonDataArray.get(i).getLessonDate().equals(date)) {//checks if the iterated date and time is the same as those passed in
                duration = this.lessonDataArray.get(i).getLessonDuration();//sets the duration integer to the iterated duration
            }
        }
        return duration;//returns the duration integer
    }//closes the getDurationFromTimeAndDate method
    
    public void checkIfDoubleBookingForEdit(String date, String time, int duration, int size, String lessonKeyToCheck) {//creates a method to indicate whether the attempted date and time in a lesson edit will result in a double book
        if (!this.checkIfDoublebooking(date, time, duration, size, lessonKeyToCheck).contains("Venue")) {//checks if no double booking occurs
            EDIT_DOUBLE_BOOKED = false;//sets the double booked boolean to false
        } else {//if there is a double booking instance
            EDIT_DOUBLE_BOOKED = true;//sets the double booking boolean to true
        }
    }//closes the checkIfDoubleBookingForEdit method
    
    public String checkIfDoublebooking(String date, String time, int duration, int size, String lessonKeyToCheck) {//creates a method to check if an attempted lesson booking will result in a double booking
        ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
        lessonDataArray la = new lessonDataArray();
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        venueArray va = new venueArray();//creates an object for the venueArray class
        this.sortArray();//sorts array of lessons
        String endTimeAttempted = this.getEndTime(time, duration);//gets end time of attemped time
        
        String tempstudents = "\nStudent(s): ";//creates a string to hold the students of the potentially double booked lesson
        String tempvenue = "";//creates a string to hold the venu of the potentially double booked lesson
        String tempReturn = "";//creates a string to hold and display the data of the potentially double booked lesson
        String tempTime = "";//creates a string to hold the time string of the potentially double booked lesson
        boolean ok = true;//creates a boolean indicating whether there is a double booking instance
        boolean oneStudent = size == 1;//creates a boolean to check if there is only 1 student attending the double booked lesson
        
        int minsAttemptedTime = (Integer.parseInt(time.substring(0, 2))*60)+Integer.parseInt(time.substring(3, 5));//creates an int representation of the attempted booking time
        int minsAttemptedEndTime = (Integer.parseInt(endTimeAttempted.substring(0, 2))*60)+Integer.parseInt(endTimeAttempted.substring(3, 5));//creates an int representation of the attempted booking end time
        int minsDuration = duration*60;//creates an int representation of the attempted booking duration
            
            for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            String endTimeReference = this.getEndTime(this.getLessonDataArray().get(i).getLessonTime(), this.lessonDataArray.get(i).getLessonDuration());//gets reference end time
            int minsReferenceTime = (Integer.parseInt(this.lessonDataArray.get(i).getLessonTime().substring(0, 2))*60)+Integer.parseInt(this.lessonDataArray.get(i).getLessonTime().substring(3, 5));//creates an int representation of the reference time
            int minsReferenceEndTime = (Integer.parseInt(endTimeReference.substring(0, 2))*60)+Integer.parseInt(endTimeReference.substring(3, 5));//creates an int representation of the reference end time
            boolean isInArray = Arrays.stream(this.getArrayOfStudentsAddedNoIndex()).anyMatch(sa.studentNameFromID(this.lessonDataArray.get(i).getStudentID())::equals);//checks the name of reference against array of added students
            boolean keyIsEqual = this.getLessonKey(i).equals(lessonKeyToCheck);//checks if an attempted lesson to be booked has the same lesson key as the reference lesson
            //creates the checks to determine if lesson attmepted to be booked will overlap with an already booked lesson
            boolean checkDate = this.lessonDataArray.get(i).getLessonDate().equals(date);
            boolean check1 = minsReferenceTime-minsAttemptedTime > 0 && minsReferenceEndTime-minsAttemptedEndTime > 0 && !(minsAttemptedEndTime <= minsReferenceTime);
             boolean check2 = minsReferenceTime-minsAttemptedTime > 0 && minsReferenceEndTime-minsAttemptedEndTime == 0;
              boolean check3 = minsReferenceTime-minsAttemptedTime == 0 && minsReferenceEndTime-minsAttemptedEndTime > 0;
               boolean check4 = minsReferenceTime-minsAttemptedTime == 0 && minsReferenceEndTime-minsAttemptedEndTime < 0;
                boolean check5 = minsReferenceTime-minsAttemptedTime < 0 && minsReferenceEndTime-minsAttemptedEndTime > 0;
                 boolean check6 = minsReferenceTime-minsAttemptedTime < 0 && minsReferenceEndTime-minsAttemptedEndTime < 0 && !(minsReferenceTime-minsAttemptedEndTime <= 0);
                  boolean check7 = minsReferenceTime-minsAttemptedTime == 0 && minsReferenceEndTime-minsAttemptedEndTime == 0;
                
            if (oneStudent) {//checks if there is only student to be attendting the lesson
                if (keyIsEqual == false && checkDate == true && (check1 == true) ||
                    keyIsEqual == false && checkDate == true && (check2 == true) ||
                    keyIsEqual == false && checkDate == true && (check3 == true) ||
                    keyIsEqual == false && checkDate == true && (check4 == true) ||
                    keyIsEqual == false && checkDate == true && (check5 == true) ||
                    keyIsEqual == false && checkDate == true && (check6 == true) ||
                    keyIsEqual == false && checkDate == true && (check7 == true)) {//checks if lesson attmepted to be booked will overlap with an already booked lesson
                    ok = false;//flips ok to false i.e. there is a double booking instance
                } else {//if there is no double booking instance
                    ok = true;//flips ok to true
                }
            } else {//if there is more than 1 student
                if (!isInArray && keyIsEqual == false && checkDate == true && (check1 == true) ||
                    !isInArray && keyIsEqual == false && checkDate == true && (check2 == true) ||
                    !isInArray && keyIsEqual == false && checkDate == true && (check3 == true) ||
                    !isInArray && keyIsEqual == false && checkDate == true && (check4 == true) ||
                    !isInArray && keyIsEqual == false && checkDate == true && (check5 == true) ||
                    !isInArray && keyIsEqual == false && checkDate == true && (check6 == true) ||
                    !isInArray && keyIsEqual == false && checkDate == true && (check7 == true)) {//checks if lesson attmepted to be booked will overlap with an already booked lesson
                    ok = false;//flips ok to false i.e. there is a double booking instance
                } else {//if there is no double booking instance
                    ok = true;//flips ok to true
                }
            }
            if (ok == false) {//checks if ok is false i.e. there is a double booking
                String prevStudentName = "tempStudName";//gets name of double booked venue
                for (int k = 0; k < va.getVenuesArray().size(); k++) {//iterates through the stored venues
                    if (va.getVenuesArray().get(k).getVenueID() == this.lessonDataArray.get(i).getVenueID()) {//checks if the venue id of the iterated lesson is the same as the iterated venue
                        tempvenue = "\nVenue: " + va.getVenuesArray().get(k).getVenue();//sets the venue string to display the venue
                    }
                    if (!tempTime.contains(this.lessonDataArray.get(i).getLessonTime())) {//checks if tempTime does not already contain the iterated time
                        tempTime += this.lessonDataArray.get(i).getLessonTime() + " - " + endTimeReference + ", ";//adds the iterated time to tempTime
                    }
                } 
                for (int s = 0; s < sa.getStudentArray().size(); s++) {//iterates through the stored students
                    if (sa.getStudentArray().get(s).getStudentID() == this.lessonDataArray.get(i).getStudentID() && !tempstudents.contains(prevStudentName)) {//checks if the iterated student is the same as the iterated lesson's student and that it has not laready been added to the display string
                    tempstudents += sa.getStudentArray().get(s).getfName() + " " + sa.getStudentArray().get(s).getlName() + "\n                  ";//adds the iterated student to the display string
                    prevStudentName = sa.getStudentArray().get(s).getfName() + " " + sa.getStudentArray().get(s).getlName();//sets the previous student string to the added student
                }
            }
            }
        
        }      
    tempReturn = "You have already booked a lesson at this time and date:\n\nDate: " + date + "\nTime: " + tempTime + tempvenue + tempstudents;//formats the display message
    return tempReturn;//returns the display message
    }//closes the checkIfDoublebooking method
    
    public String getLessonKey(int lessonID) {
        keysArray ka = new keysArray();//creates an object for the keysArray class
        String key = "";
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            for (int k = 0; k < ka.getKeyArray().size(); k++) {//iterates through the keys
                if (this.lessonDataArray.get(i).getLessonID() == ka.getKeyArray().get(k).getLessonID()) {//checks if the iterated id is the same as the one passed in
                    key = ka.getKeyArray().get(k).getLessonKey();//sets the key string to the iterated key
                }
            }
        }
        return key;//returns the key
    }//closes the getLessonKey method
    
    public String generateLessonKey() {//creates a method to generate a lesson key
        String uuid = UUID.randomUUID().toString();//creates a string of a unique identifier
        return uuid;//returns the uuid
    }//closes the generateLessonKey method
    
    public String getEndTime(String time, int duration) {//creates a method to get the endtime
        String endTime = "";//creates a string for the endTime
        int endHour = 0;//creates an int to represent the end time hour
        String mins = time.substring(3, 5);//creates a string for the minutes of the end time
        int startHour = Integer.parseInt(time.substring(0, 2));//creates an integer representation of the hour o the time passed in
        endHour = startHour + duration;//sets the endhour to the start hour plus the duration
        if (endHour < 10) {//checks if the end hour is smaller than 10
            endTime = "0"+endHour + ":" + mins;//formats the endtime
        } else {//if the end hour is bigger than 10
            endTime = endHour + ":" + mins;//formats the endtime
        }
        return endTime;//returns the end time
    }//closes the getEndTime method

    public String GetEndTimeForSpecificStudent(int duration, String startTime, int i) {//creates a method to get the endTime of a lesson for an iterated student
        String endTime = "";//creates a string for the end time
        int endHour = 0;//creates an integer for the hour of the end time
        String mins = startTime.substring(3, 5);//creates a string for the minutes of the start time
        int startHour = Integer.parseInt(this.lessonDataArray.get(i).getLessonTime().substring(0, 2));//creates an integer for the start hour passed in
        endHour = startHour + duration;//sets the end hour to the start hour plus the duration
        if (endHour < 10) {//checks if the end hour is smaller than 10
            endTime = "0"+endHour + ":" + mins;//formats the endtime
        } else {//if the end hour is bigger than 10
            endTime = endHour + ":" + mins;//formats the endtime
        }
        return endTime;//returns the end time
    }//closes the GetEndTimeForSpecificStudent method
    
    
    public boolean checkIfInPast(String refDate, String refTime) {//creates a method to check if a date and time is in the past
        boolean inPast = false;//creates a boolean indicating whether the date is in the past
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM HH:mm");//creates a date formatter
        Date todayDate = new Date();//creates a new date object of the current date
        try {//opens the trycatch statement
            Date formattedTodayDate = sdf.parse(this.formatDate(todayDate.toString()) + " " + this.formatTime(todayDate.toString()));//creates a formatted date object of the current date
            Date date1 = sdf.parse(refDate + " " + refTime);//creates a date object for the date passed in
            if (date1.compareTo(formattedTodayDate) < 0) {//checks if the date passed in is before the current date
               inPast = true;//flips inPast to true
            }
        } catch (ParseException ex) {//opens the catch statement
            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error setting the dates of the date objects
        }//closes the catch statement
        return inPast;//returns inPast
    }//closes the checkIfInPast method
    
    public void DeletePastLessonsAndLessonKeys() {//creates a method to delete lessons in the past
        ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (this.checkIfInPast(this.lessonDataArray.get(i).getLessonDate(), this.lessonDataArray.get(i).getLessonTime()) == true) {//checks if the iterated lesson is in the past
                String deleteLessons = "DELETE * FROM lessonData WHERE LessonID = " + this.lessonDataArray.get(i).getLessonID();//sets the deleteLessons string to the SQL query used to delete the lesson
                String deleteKeys = "DELETE * FROM lessonKeys WHERE lessonID = " + this.lessonDataArray.get(i).getLessonID();//sets the deleteKeys string to the SQL query used to delete the key
                try {//opens the trycatch statement
                    db.UpdateDatabase(deleteLessons);//deletes the lesson
                    db.UpdateDatabase(deleteKeys);//deletes the key
                } catch (SQLException ex) {//opens tyhe catch statement
                    Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the user that there was an error deleteing the lessons
                }//closes the catch statement
            }
        }
    }//closes the DeletePastLessonsAndLessonKeys method
    
    public String upcomingDate(int studentID) {//creates a method to get the date of the upcoming lesson of a student
        boolean booked = false;//creates a boolean indicating whether the student has any lessos booked
        //checks if in fact booked a lesson
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (this.lessonDataArray.get(i).getStudentID() == studentID) {//checks if the iterated student id is the same as the one passed in
                booked = true;//flips booked to true
            }
        }
        if (booked == true) {//cheks if booked is true i.e. a lesson has been booked by that student
            this.sortArray();//sorts the lesson array
            String date = "";//creates a string to hold the upcoming date
            for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
                if (this.lessonDataArray.get(i).getStudentID() == studentID) {//checks if theiterated student is the same as the one passed in
                    date = this.lessonDataArray.get(i).getLessonDate();//sets the date to the iterated date
                    break;//discontinues the loop
                }
            }
            return date;//returns the date
        } else {//if the student has not booked a lesson
            return "N/A";//returns not applicable
        }
    }//closes the upcomingDate method
    
    public String upcomingTime(int studentID, String upcomingDate) {//creates a method to get the time of a students upcoming lesson
        if (!this.upcomingDate(studentID).equals("N/A")) {//checks if the students has booked a lesson
            String time = "";//creates a string to store the time
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (upcomingDate.equals(this.lessonDataArray.get(i).getLessonDate()) && studentID == this.lessonDataArray.get(i).getStudentID()) {//checks if the iterated lesson is on the same date and the iterated student id is the same as the one passed in
                time = this.lessonDataArray.get(i).getLessonTime() + " - " +  this.getEndTime(this.lessonDataArray.get(i).getLessonTime(), this.lessonDataArray.get(i).getLessonDuration());//sets the time to the iterated time
            }
        }
        return time;//returns the time
        } else {//if the student has not booked a lesson
            return "N/A";//returns not applicable
        }
    }//closes the upcomingTime method
    
    public String upcomingVenue(int studentID, String upcomingDate, String upcomingTime) {//creates a method to get the venue of an upcoming lesson of a student
        if (!this.upcomingDate(studentID).equals("N/A")) {//checks if the student has booked a lesson
            venueArray va = new venueArray();//creates an object for the venueArray class
        String venue = "";//creates a string for the venue
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (upcomingDate.equals(this.lessonDataArray.get(i).getLessonDate()) && studentID == this.lessonDataArray.get(i).getStudentID()
                    && upcomingTime.equals(this.lessonDataArray.get(i).getLessonTime())) {//checks if the iterated lesson is on the same date and the iterated student id is the same as the one passed in
                venue = va.venueNameFromID(this.lessonDataArray.get(i).getVenueID());//sets the venue to the iterated venue
            }
        }
        return venue;//returns the venue
        } else {//if the student has not booked a lesson
            return "N/A";//returns not applicable
        }
    }//closes the upcomingVenue method
    
    public String upcomingDay(int studentID, String upcomingDate, String upcomingTime) {//creates a method to get the venue of a students upcoming lesson
        if (!this.upcomingDate(studentID).equals("N/A")) {//checks if the student has booked a lesson
            String day = "";//creates a string for the day
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (upcomingDate.equals(this.lessonDataArray.get(i).getLessonDate()) && studentID == this.lessonDataArray.get(i).getStudentID() 
                    && upcomingTime.equals(this.lessonDataArray.get(i).getLessonTime())) {//checks if the iterated lesson is on the same date and the iterated student id is the same as the one passed in
                day = this.lessonDataArray.get(i).getDay();//sets the day to the iterated day
            }
        }
        return day;//returns the day
        } else {//if the student has not booked a lesson
            return "N/A";//returns not applicable
        }
    }//closes the upcomingDay method
    
    public String getLessonStartTimeFromLessonID(int id) {//creates a method to get the start time from the lesosn id
        String time = "";//creates a string for the time
        for (int i = 0;  i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (this.lessonDataArray.get(i).getLessonID() == id) {//checks if the iterated id is the same as the one passed in
                time = this.lessonDataArray.get(i).getLessonTime();//sets the time to the iterated time
            }
        }
        return time;//returns the time
    }//closes rhe getLessonStartTimeFromLessonID method
    
    public String getLessonDateFromLessonID(int id) {//creates a method to get the date from the lesosn id
        String date = "";//creates a string for the date
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (this.lessonDataArray.get(i).getLessonID() == id) {//checks if the iterated id is the same as the one passed in
                date = this.lessonDataArray.get(i).getLessonDate();//sets the date to the iterated date
            }
        }
          return date;   //returns the date   
    }//closes the getLessonDateFromLessonID method
    
    public String getLessonEndTimeFromLessonID(int id) {//creates a method to get the end time from the lesosn id
        String time = "";//creates a string for the time
        for (int i = 0;  i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (this.lessonDataArray.get(i).getLessonID() == id) {//checks if the iterated id is the same as the one passed in
                time = this.getEndTime(this.lessonDataArray.get(i).getLessonTime(), this.lessonDataArray.get(i).getLessonDuration());//sets the end time to the iterated end time
            }
        }
        return time;//returns the end time
    }//closes the getLessonEndTimeFromLessonID method
    
    public String getLessonTimeFromStartTimeAndDuration(String startTime, int duration) {//creates a method to get the formatted string of the time of a lesson
        String time = startTime + " - " + this.getEndTime(startTime, duration);//creates a formatted string of the time
        return time;//returns the time
    }//closes the getLessonTimeFromStartTimeAndDuration method
    
    public String getTimeFromLessonID(int id) {//creates a method to get the formatted time from the lesson id passed in 
        String time = "";//creates a string for the time
        for (int i = 0;  i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (this.lessonDataArray.get(i).getLessonID() == id) {//checks if the iterated lesson id is the same as the one passed in
                time = this.lessonDataArray.get(i).getLessonTime() + "-" + this.getEndTime(this.lessonDataArray.get(i).getLessonTime(), this.lessonDataArray.get(i).getLessonDuration());//sets the time to the formatted time
            }
        }
        return time;//returns the time
    }//closes the getTimeFromLessonID method
    
    public int countLessonsFromStudentID(int id) {//creates a method to count the lessons a student a student has booked
        int lessons = 0;//creates an integer for the count
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (this.lessonDataArray.get(i).getStudentID() == id) {//checks if the iterated lesson id is the same as the one passed in
                lessons++;//ups the lessons count
            }
        }
        return lessons;//returns the lessons count
    }//closes the countLessonsFromStudentID method
    
    public String getDateFromKeyAndStudentID(String key, int id) {//gets the date of a lesson from the key and student id
        keysArray ka = new keysArray();//creates an object for the keysArray class
        String date = "";//creates a string for the date
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (ka.getKeyFromLessonID(this.lessonDataArray.get(i).getLessonID()).equals(key) &&
                    this.lessonDataArray.get(i).getStudentID() == id) {//checks if the key and student id is the same as those passed in
                date = this.lessonDataArray.get(i).getLessonDate();//sets the date to the iterated date
                break;//discontinues the loop
            }
        }
        return date;//returns the date
    }//closes the getDateFromKeyAndStudentID method
    
    public int getLessoIDFromDateTimeAndStudentID(String date, String time, int studentID) {//creates a method to get the lesson id from date time and student id
        int id = 0;//creates an integer for the id
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            String startTime = time.substring(0, 5);//creates a string for the start time passed in
            if (this.lessonDataArray.get(i).getLessonDate().equals(date) &&
                    this.lessonDataArray.get(i).getLessonTime().equals(startTime) &&
                    this.lessonDataArray.get(i).getStudentID() == studentID) {//checks if the iterated parametres match those passed in
                id = this.lessonDataArray.get(i).getLessonID();//sets the id to the iterated id
            }
        }
        return id;//returns the id
    }//closes the getLessoIDFromDateTimeAndStudentID method
    
    public int getLessonIDFromDateAndTime(String date, String time) {//creates a method to get the lesson id from date and time
        int id = 0;//creates an integer for the id
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (this.lessonDataArray.get(i).getLessonDate().equals(date) && this.lessonDataArray.get(i).getLessonTime().equals(time)) {//checks if the date and time iterated is the same as those passed in
                id = this.lessonDataArray.get(i).getLessonID();//sets the id to the iterated id
            }
        }
        return id;//returns the id
    }//closes the getLessonIDFromDateAndTime method
    
    public int getLessonIDFromDuringTimeAndDate(String date, String duringTime) {//creates a method to get the lesson id from the date and time during a lesson
        CalendarHandler ch = new CalendarHandler();//creates an object for the calendarHandler class
        int id = 0;//creates an integer for the id
        String startTime = ch.floorStartTime(date, duringTime);//creates a string for th dtart time of the lesson
        for (int i = 0; i < this.lessonDataArray.size(); i++) {//iterates through the lesson objects in the lessonDataArray list
            if (this.getLessonDataArray().get(i).getLessonDate().equals(date) && this.getLessonDataArray().get(i).getLessonTime().equals(startTime)) {//checks if the iterated date and time is the same as those passed in
                id = this.getLessonDataArray().get(i).getLessonID();//sets the id to the iterated id
            }
        }
        return id;//returns the id
    }//closes the getLessonIDFromDuringTimeAndDate method
    
    public String lessonComponentsExist() {//creates a method to instruct the user to add the needed data to the database before adding a lesson
        String temp = "";//creates a string for the display message
        venueArray va = new venueArray();//creates an object for the venueArray class
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        schoolsArray sca = new schoolsArray();//creates an object for the schoolsArray class
        int v = va.getVenuesArray().size();//creates an integer for the size of the venue array
        int s = sa.getStudentArray().size();//creates an integer for the size of the students array
        int sc = sca.getSchoolsDataArray().size();//creates an integer for the size of the school array
        if (v == 0) {//checks if there are venues in the databse
            temp += "() Please add a venue to the database before you add a lesson\n";//adds the venue message to the temp string
        }
        if (s == 0) {//checks if there are students in the databse
            temp += "() Please add a student to the database before you add a lesson\n";//adds the students message to the temp string
        }
        if (sc == 0) {//checks if there are schools in the databse
            temp += "() Please add a school to the database before you add a lesson\n";//adds the schools message to the temp string
        }
        return temp;//returns the temp string
    }//closes the lessonComponentsExist method
    
}//closes the lessonDataArray class