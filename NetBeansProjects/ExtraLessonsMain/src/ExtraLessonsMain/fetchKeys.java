/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtraLessonsMain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YishaiBasserabie
 */
public class fetchKeys {//creates a class representing a lessonKey object
    private int lessonID;//creates a private integer holding the lesson id of the key object
    private String lessonKey;//creates a private string holding the key string of the key object

    public fetchKeys(int lessonID, String lessonKey) {//creates the constructor for the current class instantiating a key object
        this.lessonID = lessonID;//assigns the passed in lessonID to the lessonID belonging to the object
        this.lessonKey = lessonKey;//assigns the passed in lessonKey to the lessonKey belonging to the object
    }//closes the constructor

    public int getLessonID() {//creates a method that returns the lessonID
        return lessonID;//returns the lessonID of the key object
    }//closes the getLessonID class

    public void setLessonID(int lessonID) {//creates a method that sets the lessonID
        this.lessonID = lessonID;//sets the lessonID of the key object
    }//closes the setLessonID class

    public String getLessonKey() {//creates a method that returns the lessonKey
        return lessonKey;//returns the lessonKey of the key object
    }//closes the getLessonKey class

    public void setLessonKey(String lessonKey) {//creates a method that sets the lessonKey
        this.lessonKey = lessonKey;//sets the lessonKey of the key object
    }//closes the setLessonKey class
    
}//closes the fetchKeys class