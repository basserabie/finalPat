/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtraLessonsMain;

/**
 *
 * @author YishaiBasserabie
 */
public class fetchLessonData {//creates a class repserenting a lesson object
    private int lessonID, studentID, venueID, lessonDuration;//creates private integers to store the lessonID, studentID, venueID, and lessonDuration of the lesson object
    private String lessonDate, lessonTime, day;//creates private strings to store the lessonDate, lessonTime, and lessonDay of the lesson object

    public fetchLessonData(int lessonID, int studentID, int venueID, int lessonDuration, String lessonDate, String lessonTime, String day) {//creates a constructor for the current class to instantiate a lesson object
        this.lessonID = lessonID;//assignes the lessonID passed in to the lessonID of the object
        this.studentID = studentID;//assignes the studentID passed in to the studentID of the object
        this.venueID = venueID;//assignes the venueID passed in to the venueID of the object
        this.lessonDuration = lessonDuration;//assignes the lessonDuration passed in to the lessonDuration of the object
        this.lessonDate = lessonDate;//assignes the lessonDate passed in to the lessonDate of the object
        this.lessonTime = lessonTime;//assignes the lessonTime passed in to the lessonTime of the object
        this.day = day;//assignes the day passed in to the day of the object
    }//closes the constructor

    public int getLessonID() {//creates a method to return the lessonID
        return lessonID;//returns the lessonID
    }//closes the getter method

    public void setLessonID(int lessonID) {//creates a method to set the lessonID
        this.lessonID = lessonID;//sets the lessonID
    }//closes the setter method

    public int getStudentID() {//creates a method to return the studentID
        return studentID;//returns the studentID
    }//closes the getter method

    public void setStudentID(int studentID) {//creates a method to set the studentID
        this.studentID = studentID;//sets the studentID
    }//closes the setter method

    public int getVenueID() {//creates a method to return the venueID
        return venueID;//returns the venueID
    }//closes the getter method

    public void setVenueID(int venueID) {//creates a method to set the venueID
        this.venueID = venueID;//sets the venueID
    }//closes the setter method

    public int getLessonDuration() {//creates a method to return the lessonDuration
        return lessonDuration;//returns the lessonDuration
    }//closes the getter method

    public void setLessonDuration(int lessonDuration) {//creates a method to set the lessonDuration
        this.lessonDuration = lessonDuration;//sets the lessonDuration
    }//closes the setter method

    public String getLessonDate() {//creates a method to return the lessonDate
        return lessonDate;//returns the lessonDate
    }//closes the getter method

    public void setLessonDate(String lessonDate) {//creates a method to set the lessonDate
        this.lessonDate = lessonDate;//sets the lessonDate
    }//closes the setter method

    public String getLessonTime() {//creates a method to return the lessontime
        return lessonTime;//returns the lessonTime
    }//closes the getter method

    public void setLessonTime(String lessonTime) {//creates a method to set the lessonTime
        this.lessonTime = lessonTime;//sets the lessonTime
    }//closes the setter method

    public String getDay() {//creates a method to return the lesson day
        return day;//returns the lesson Day
    }//closes the getter method

    public void setDay(String day) {//creates a method to set the lesson Day
        this.day = day;//sets the lesson Day
    }//closes the setter method
    
}//closes the fetchLessonData class