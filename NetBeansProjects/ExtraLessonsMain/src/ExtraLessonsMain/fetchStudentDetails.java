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
public class fetchStudentDetails {//creates a class representing a student object
    private int StudentID, schoolID, motherID;//creates integers holding the studentID, schoolID, and motherID of the student object
    private String fName, lName, grade;//creates string for the first name, last name, and grade of the student object

    public fetchStudentDetails(int StudentID, int schoolID, int motherID, String fName, String lName, String grade) {//creates the constructor for the current object and instantiates a student object
        this.StudentID = StudentID;//assignes the studentID passed in to the studentID of the object
        this.schoolID = schoolID;//assignes the schoolID passed in to the schoolID of the object
        this.motherID = motherID;//assignes the motherID passed in to the motherID of the object
        this.fName = fName;//assignes the fName passed in to the fName of the object
        this.lName = lName;//assignes the lName passed in to the lName of the object
        this.grade = grade;//assignes the grade passed in to the grade of the object
    }//closes the constructor

    public int getStudentID() {//creates a method to return the studentID
        return StudentID;//returns the studentID
    }//closes the getter method

    public void setStudentID(int StudentID) {//creates a method to set the studentID
        this.StudentID = StudentID;//sets the studentID
    }//closes the setter method

    public int getSchoolID() {//creates a method to return the schoolID
        return schoolID;//returns the sschoolID
    }//closes the getter method

    public void setSchoolID(int schoolID) {//creates a method to set the schoolID
        this.schoolID = schoolID;//sets the schoolID
    }//closes the setter method

    public int getMotherID() {//creates a method to return the motherID
        return motherID;//returns the motherID
    }//closes the getter method

    public void setMotherID(int motherID) {//creates a method to set the motherID
        this.motherID = motherID;//sets the motherID
    }//closes the setter method

    public String getfName() {//creates a method to return the gName
        return fName;//returns the fName
    }//closes the getter method

    public void setfName(String fName) {//creates a method to set the fName
        this.fName = fName;//sets the fName
    }//closes the setter method

    public String getlName() {//creates a method to return the lName
        return lName;//returns the lName
    }//closes the getter method

    public void setlName(String lName) {//creates a method to set the lName
        this.lName = lName;//sets the lName
    }//closes the setter method

    public String getGrade() {//creates a method to return the grade
        return grade;//returns the grade
    }//closes the getter method

    public void setGrade(String grade) {//creates a method to set the grade
        this.grade = grade;//sets the grade
    }//closes the setter method
      
}//closes the fetchStudentDetails class
