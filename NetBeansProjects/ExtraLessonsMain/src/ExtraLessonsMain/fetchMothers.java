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
public class fetchMothers {//creates a class representing a parent object
    private int motherID;//creates a int to hold the motherID of the parent object
    private String motherFName, motherLName, motherEmail, motherCell;//creates strings to hold the parent first name, last name, email, and cell of the parent object
        // in: id of mother, firstname, lastname, email, cell
    public fetchMothers(int motherID, String motherFName, String motherLName, String motherEmail, String motherCell) {//creates the constructor for the current class instantiating a parent object
        this.motherID = motherID;//assignes the motherID passed in to the motherID of the object
        this.motherFName = motherFName;//assignes the motherFName passed in to the motherFName of the object (first name)
        this.motherLName = motherLName;//assignes the motherLName passed in to the motherLName of the object (last name)
        this.motherEmail = motherEmail;//assignes the motherEmail passed in to the motherEmail of the object
        this.motherCell = motherCell;//assignes the motherCell passed in to the motherCell of the object
    }//closes the constructor

    public int getMotherID() {//creates a method to return the motherID
        return motherID;//returns the motherID
    }//closes the getter method

    public void setMotherID(int motherID) {//creates a method to set the motherID in: mother id to set to
        this.motherID = motherID;//sets the motherID
    }//closes the setter method

    public String getMotherFName() {//creates a method to return the motherFName
        return motherFName;//returns the motherFName
    }//closes the getter method

    public void setMotherFName(String motherFName) {//creates a method to set the motherFName in: mother first name to set to
        this.motherFName = motherFName;//sets the motherFName
    }//closes the setter method

    public String getMotherLName() {//creates a method to return the motherLName
        return motherLName;//returns the motherLName
    }//closes the getter method

    public void setMotherLName(String motherLName) {//creates a method to set the motherLName in: mother last name to set to
        this.motherLName = motherLName;//sets the motherLName
    }//closes the setter method

    public String getMotherEmail() {//creates a method to return the motherEmail
        return motherEmail;//returns the motherEmail
    }//closes the getter method

    public void setMotherEmail(String motherEmail) {//creates a method to set the motherEmail in: mother email to set to
        this.motherEmail = motherEmail;//sets the motherEmail
    }//closes the setter method

    public String getMotherCell() {//creates a method to return the motherCell
        return motherCell;//returns the motherEmail
    }//closes the getter method

    public void setMotherCell(String motherCell) {//creates a method to set the motherCell in: mother cell to set to
        this.motherCell = motherCell;//sets the motherCell
    }//closes the setter method
     
}//closes the fetchMothers class
