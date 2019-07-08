/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtraLessonsMain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author YishaiBasserabie
 */
public class keysArray {//creates a class to handle the lesson keys
    private ArrayList<fetchKeys> keyArray = new ArrayList<>();//creates a private array list for the lesson keys

    public keysArray() {//creates the constructor for the current class and populates the key array
        ConnectDB  db = new ConnectDB();//creates an object for the connectDB class
        ResultSet r = db.getResults("SELECT * FROM lessonKeys");//creates a resultSet for fetching the keys from the connected database
        try {//opens the trycatch statement
            while(r.next()) {//starts a while loop iterating through each result (key) in the resultSet
                fetchKeys fks = new fetchKeys(r.getInt("lessonID"), r.getString("lessonKey"));//creates a new fethKeys object according to the iterated result
                keyArray.add(fks);//adds the fks fetchKeys object to the keysArray
            }
        } catch (SQLException ex) {//opens the catch stament
            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of what went wrong while fetching the keys
            JOptionPane.showMessageDialog(null, "Database error, please contact administrator at 0836570642");//alerts the user that there was an error connecting to the database and to contact the administrator
        }//closes the catch
    }//closes the constructor

    public ArrayList<fetchKeys> getKeyArray() {//creates a method to return the keysArray array list
        return keyArray;//returns the keysArray
    }//closes the getKeyArray method

//    public void setKeyArray(ArrayList<fetchKeys> keyArray) {
//        this.keyArray = keyArray;
//    }
    
    public String getKeyFromLessonID(int id) {//creates a method to return the key for the inputted lesson id
        String key = "";//creates a string to hold the key
        for (int i = 0; i < this.keyArray.size(); i++) {//iterates through the keyArray
            if (this.keyArray.get(i).getLessonID() == id) {//checks if the itetrated id is the same as the id passed in
                key = this.keyArray.get(i).getLessonKey();//sets the key string to the iterated key
            }
        }
        return key;//returns the key string
    }//closes the getKeyFromLessonID method
    
    public int getLessonIDFromKey(String key) {//creates a method to get the lessonID from the key passed
        int id = 0;//creates an integer for the id
        for (int i = 0; i < this.keyArray.size(); i++) {//iterates through the keysArray
            if (this.keyArray.get(i).getLessonKey().equals(key)) {//checks if the iterated key is the same as the key passed in
                id = this.keyArray.get(i).getLessonID();//sets the id int to the iterated id
            }
        }
        return id;//returns id
    }//closes the getFirstLessonIDFromKey method
    
    public String getStartTimeFromKey(String key) {//creates a method to get the start time of a lesson according to the passed in key
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        String startTime = "";//creates a string to store the start time
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {//iterates through all lesson objects in the lessonDataArray array list
            if (this.getKeyArray().get(i).getLessonKey().equals(key)) {//checks if the the iterated key is the same as the key passed in
                startTime = la.getLessonDataArray().get(i).getLessonTime();//sets the start time to that of the iterated lesson object
            }
        }
        return startTime;//returns startTime
    }//closes the getStartTimeFromKey method
    
//    public int getLessonIDFromKey(String key) {//creates a method to het the lessonID from the key
//        int id = 0;
//        for (int i = 0; i < this.keyArray.size(); i++) {
//            if (this.keyArray.get(i).getLessonKey().equals(key)) {
//                id = this.keyArray.get(i).getLessonID();
//            }
//        }
//        return id;
//    }
    
    public String getDateFromKey(String key) {//creates a method to get the date of the lesson according to the passed key
        lessonDataArray la = new lessonDataArray();//creates an obejct for the lessonDataArray class
        String date = "";//creates a string for the date
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {//iterates thrugh the lesson objects in the lessonDataArray array list
            if (this.getKeyArray().get(i).getLessonKey().equals(key)) {//checks if the iterated key is equal to the passed in key
                date = la.getLessonDataArray().get(i).getLessonDate();//sets the date string to the date of the lesson corresponding to the iterated key
            }
        }
        return date;//returns the date
    }//closes the getDateFromKey method
    
    public String getEndTimeFromKey(String key) {//creates a method to get the end time of the lesson according to the key passed in
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        String startTime = "";//creates a string to store the start time
        String endTime = "";//creates a string to store the end time
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {//iterates through the lesson objects in the lessonDataArray array list
            if (this.getKeyArray().get(i).getLessonKey().equals(key)) {//checks if the iterated key is the same as the key passed in
                startTime = la.getStartTimeFromIndex(i);//sets the start time string to the start time of the lesson corresponsing to the iterated key
                endTime = la.getEndTime(startTime, la.getLessonDataArray().get(i).getLessonDuration());//sets the endtime string to the end time of the lesson corresponding to the iterated key
            }
        }
        return endTime;//retuns the end time
    }//closes the getEndTimeFromKey method
    
    public String getKeyFromDateAndTime(String date, String time) {//creates a method to get the key from a date and time of a lesson passed in
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        String key = "";//creates a string to store the key
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {//iterates through the lesson objects in the lessonDataArray array list
            if (la.getLessonDataArray().get(i).getLessonDate().equals(date) &&
                    la.getLessonDataArray().get(i).getLessonTime().equals(time)) {//checks if the date and time of the iterated lesson is the same as those of those passed in
                key = this.getKeyFromLessonID(la.getLessonDataArray().get(i).getLessonID());//sets the key to the key of the iterated lesson
            }
        }
        return key;//returns the key
    }//closes the getKeyFromDateAndTime method
    
    public void sortArray() {//creates a method to sort the keysArray according to the date and time of their corresponding lessons
        Calendar p1 = Calendar.getInstance();//instantiates a clandar object
        Calendar p2 = Calendar.getInstance();//instantiates a clandar object
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM HH:mm");//creates a simpleDateFormat
        for (int i = 0; i < this.keyArray.size(); i++) {//iterates through the keysArray
            try {//opens the trycatch statement
                if (i < this.keyArray.size()-1) {//checks if i is smaller than the index bounds of the keysArray
                    p1.setTime(sdf.parse(this.getDateFromKey(this.keyArray.get(i).getLessonKey()) + " " + this.getStartTimeFromKey(this.keyArray.get(i).getLessonKey())));//sets the date and time of the p1 calendarobject to thedate and time of the lesson correspoonding to the iterated key
                    p2.setTime(sdf.parse(this.getDateFromKey(this.keyArray.get(i+1).getLessonKey()) + " " + this.getStartTimeFromKey(this.keyArray.get(i+1).getLessonKey())));//sets the date and time of the p2 calendarobject to thedate and time of the lesson correspoonding to the next iterated key
                }
                fetchKeys temp;//instantiates a fetchKeys object to be used for the sorting
                if (p1.after(p2)) {//checks if the p1 calendar object is before the p2 calendar object
                    temp = this.keyArray.get(i);//sets the temp key object to the iterated key object
                    this.keyArray.set(i, this.keyArray.get(i+1));//sets the iterated key object to the next iterated key object
                    this.keyArray.set(i+1, temp);//sets the next iterated key object to the temp object
                }
            } catch (ParseException ex) {//opens the catch statement
                Logger.getLogger(keysArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error setting the times of the claendar object
            }//closes the catch object
        }
    }//closes the sortArray method
    
    public int [] getLessonIDSFromKey(String key) {//creates a method to get the lesson ids of the lesson corresponding to the lesson key passed in
        ArrayList<Integer> idList = new ArrayList<>();//creates a array list for the ids
        for (int i  = 0; i < this.keyArray.size(); i++) {//iterates through keysArray 
            if (this.keyArray.get(i).getLessonKey().equals(key)) {//checks if the key of the iterated key object is the same as the key passed in
                idList.add(this.keyArray.get(i).getLessonID());//adds the id of the iterated key object to the id array list
            }
        }
        int idArray [] = idList.stream().mapToInt(i -> i).toArray();//streams the array list to an int array to be returned
        return idArray;//returns the idArray
    }//closes the getLessonIDSFromKey method
    
    public void deleteKeyFromDateAndTime(String date, String time) {//creates a method to delete a key object from the database according to the date and time passed in
        ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
        String keyToDelete = this.getKeyFromDateAndTime(date, time);//creates a string for the key of the lesson according to the date and time passed in
        for (int i = 0; i < this.keyArray.size(); i++) {//iterates through the keysArray
            if (this.keyArray.get(i).getLessonKey().equals(keyToDelete)) {//checks if the keysArray iterated key is the same as the keyToDelete
                String deleteKey = "DELETE * FROM lessonKeys WHERE lessonKey = '" + keyToDelete + "'";//creates a string for the SQL query used to delete the key obejct
                try {//opens the trycatch statement
                    db.UpdateDatabase(deleteKey);//deletes the key object(s)
                } catch (SQLException ex) {//opens the catch statement
                    Logger.getLogger(keysArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error deleteing the keys
                }//closes the catch statement
            }
        }
    }//closes the deleteKeyFromDateAndTime method
    
}//closes the keysArray class
