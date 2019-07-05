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
public class fetchSchools {//creates a class representing a school object
    private int schoolID;//creates an integer holding the schoolID of the school object
    private String schoolName, PFName, PLName, PEmail;//creates string to hold the schoolName, principal first name, principal last name, princpal email address of the school object

    public fetchSchools(int schoolID, String schoolName, String PFName, String PLName, String PEmail) {//creates the cosntructor for the current class instantiating the school object
        this.schoolID = schoolID;//assignes the schoolID passed in to the schoolID of the object
        this.schoolName = schoolName;//assignes the schoolName passed in to the schoolName of the object
        this.PFName = PFName;//assignes the PFname passed in to the PFName of the object
        this.PLName = PLName;//assignes the PLName passed in to the PLName of the object
        this.PEmail = PEmail;//assignes the PEmail passed in to the PEmail of the object
    }//closes the constructor

    public int getSchoolID() {//creates a method to return the schoolID
        return schoolID;//returns the schoolID
    }//closes the getter method

    public void setSchoolID(int schoolID) {//creates a method to set the schoolID
        this.schoolID = schoolID;//sets the schoolID
    }//closes the setter method

    public String getSchoolName() {//creates a method to return the schoolName
        return schoolName;//returns the schoolName
    }//closes the getter method

    public void setSchoolName(String schoolName) {//creates a method to set the schoolName
        this.schoolName = schoolName;//sets the schoolName
    }//closes the setter method

    public String getPFName() {//creates a method to return the PFName
        return PFName;//returns the PFName
    }//closes the getter method

    public void setPFName(String PFName) {//creates a method to set the PFName
        this.PFName = PFName;//sets the PFName
    }//closes the setter method

    public String getPLName() {//creates a method to return the PLName
        return PLName;//returns the PLName
    }//closes the getter method

    public void setPLName(String PLName) {//creates a method to set the PLName
        this.PLName = PLName;//sets the PLName
    }//closes the setter method

    public String getPEmail() {//creates a method to return the PEmail
        return PEmail;//returns the PEmail
    }//closes the getter method

    public void setPEmail(String PEmail) {//creates a method to set the PEmail
        this.PEmail = PEmail;//sets the PEmail
    }//closes the setter method
    
}//closes the fetchSchools class
