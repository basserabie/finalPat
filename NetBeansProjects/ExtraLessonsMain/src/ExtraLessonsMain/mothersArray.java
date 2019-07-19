/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtraLessonsMain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author YishaiBasserabie
 */
public class mothersArray {//creates a class to handle the parents
    public ArrayList<fetchMothers> mothersArray = new ArrayList<>();//creates an array list for the parent objects

    public mothersArray() {//opens the constructor for the current class
        ConnectDB  db = new ConnectDB();//creates an object for the ConnectDB class
        ResultSet r = db.getResults("SELECT * FROM mothers");//creates a resulSet for the parent objects
        try {//opens the trycatch statement
            while(r.next()) {//starts a loop iterateing through the results
                fetchMothers fm = new fetchMothers(r.getInt("MotherID"), r.getString("motherfName"), 
                        r.getString("motherLName"), r.getString("motherEmail"), r.getString("motherCell"));//creates a new mother object according to the iterated set
                mothersArray.add(fm);//adds the object to the array list
            }
        } catch (SQLException ex) {//opens the catch
            Logger.getLogger(mothersArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the c;ass user that there was an error getting the results
            JOptionPane.showMessageDialog(null, "Database error, please contact administrator at 0836570642");//instructs the user to contact the admin
        }//closes the catch
    }//closes the cosntructor

    public ArrayList<fetchMothers> getMothersArray() {//creates an accessor method for the mothersArray
        return mothersArray;//returns the array list
    }//closes the getMothersArray method
    
    public String getMotherNameForLessonArray(int id) {//creates a method to get the name from the student id
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        String name = "";//creates a string for the name
        for (int i = 0; i < this.mothersArray.size(); i++) {//iterates through the parents
            if (sa.getStudentArray().get(sa.StudentIndexFromMotherID(id)).getMotherID() == this.mothersArray.get(i).getMotherID()) {//checks if the iterated student mother id is the same as the one passed in
                name = this.mothersArray.get(i).getMotherFName() + " " + this.mothersArray.get(i).getMotherLName();//sets the name string to the iterated name
            }
        }
        return name;//returns the name
    }//closes the getMotherNameForLessonArray method
    //DUPLICATE
    public String getMotherNameFromStudentID(int id) {//gets the mother name from student id
        studentsArray sa = new studentsArray();
        String name = "";
        for (int i = 0; i < this.mothersArray.size(); i++) {
            if (sa.getStudentArray().get(sa.MotherIndexFromStudentID(id)).getMotherID() == this.mothersArray.get(i).getMotherID()) {
                name = this.mothersArray.get(i).getMotherFName() + " " + this.mothersArray.get(i).getMotherLName();
            }
        }
        return name;
    } 
    
    public String getMotherNameFromIndex(int index) {//gets the mother name from index
        String name = "";//creates a string for the name
        for (int i = 0; i < this.mothersArray.size(); i++) {//iterates through the mothers
            if (i == index) {//checks if the i is the same as the index passed in
                name = this.mothersArray.get(i).getMotherFName() + " " + this.mothersArray.get(i).getMotherLName();//sets the name to the iterated name
            }
        }
        return name;//returns the name
    }//closes the getMotherNameFromIndex method
    
    public String getMotherNameFromEmail(String email) {//creates a method to to get the name from the email
        String name = "";//creates a string for the name
        for (int i = 0; i < this.mothersArray.size(); i++) {//iterates through the mothers
            if (this.mothersArray.get(i).getMotherEmail().equals(email)) {//checks if the iterated email is the same as the one passed in
                name = this.mothersArray.get(i).getMotherFName() + " " + this.mothersArray.get(i).getMotherLName();//sets the name to the iterated name
            }
        }
        return name;//returns the name
    }//closes the getMotherNameFromEmail methos
    
    public String [] getStudentsFromMotherName(String name) {//creates a method to get the students from a parent name
        ArrayList<String> students = new ArrayList<>();//creates an array list for the students
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        for (int i = 0; i < this.mothersArray.size(); i++) {//iterates through the mothers
            if (this.getMotherNameFromIndex(i).equals(name)) {//checks if the iterated mothers name is the same as the one passed in
                for (int k = 0; k < sa.getStudentArray().size(); k++) {//iterates through the students
                    if (this.getMotherNameForLessonArray(sa.getStudentArray().get(k).getMotherID()).equals(name)) {//checks if the iterates student parent is the name as the one passed in
                        students.add(sa.studentNameFromID(sa.getStudentArray().get(k).getStudentID()));//adds the iterated student to the students array
                    }
                }
            }
        }
        String studentsArray [] = students.toArray(new String[students.size()]);//streams the array list to the string array
        return studentsArray;//returns the studentsArray
    }//closes the getStudentsFromMotherName method
    
    public int getMotherIDFromName(String name) {//creates a method to get the mother id from name
        int id = 0;//creates an int for the id
        for (int i = 0; i < this.mothersArray.size(); i++) {//iterates through the mothers
            if (this.getMotherNameForLessonArray(this.mothersArray.get(i).getMotherID()).equals(name)) {//checks if the iterted name is the same as the one passed in
                id = this.mothersArray.get(i).getMotherID();//sets the id to the iterated id
            }
        }
        return id;//returns the id
    }//closes the getMotherIDFromName method
    
    public String getMotherEmailFromMotherName(String name) {//creates a metgod to the get the email from name
        int mid = this.getMotherIDFromMotherName(name);//creates an int for the mother id
        String email = "";//creates a string for the email
        for (int i = 0; i < this.mothersArray.size(); i++) {//iterates through the mothers
            if (this.mothersArray.get(i).getMotherID() == mid) {//checks if the iterated id is the saqme as the one passed in
                email = this.mothersArray.get(i).getMotherEmail();//sets the email to the iterated email
            }
        }
        return email;//returns the email
    }//closes the getMotherEmailFromMotherName method
    
    public void passSelectedMotherNameToEdit(String name) {//creates a method to pass the mother name to the edit mother screen
        editMotherForm emf = new editMotherForm();//creates an object for the motehr edoit form class
        emf.setParentNameText(name);//stes the parent name label to the name passed in
        emf.setVisible(true);//sets the edit JFrame visible
    }//closes the passSelectedMotherNameToEdit method
    
    public void passSelectedMotherToInfo(String name) {//creates a method to pass the mother name to the info mother screen
        moreInfoParentForm mi = new moreInfoParentForm();//creates an object for the motehr info form class
        mi.setParentNameText(name);//stes the parent name label to the name passed in
        mi.setVisible(true);//sets the info JFrame visible
    }//closes the passSelectedMotherToInfo method
    
    public int getMotherIDFromMotherName(String name) {//creates a method to get the id from name
        int id = 0;//creates an int for the id
        for (int i = 0; i < this.mothersArray.size(); i++) {//iterates through the mothers
            String motherName = this.getMothersArray().get(i).getMotherFName() + " " + this.getMothersArray().get(i).getMotherLName();//creates a string of the iterated mother's name
            if (motherName.toLowerCase().equals(name.toLowerCase())) {//checks if the names match
                id = this.getMothersArray().get(i).getMotherID();//sets the id to the iterated id
            }
        }
        return id;//returns the id
    }//closes the getMotherIDFromMotherName method
    
    public String getMotherEmailFromID(int id) {//creates a method to get the email from id
        String email = "";//creates a string for the email
        for (int i = 0; i < this.mothersArray.size(); i++) {//iterates through the mothers
            if (this.mothersArray.get(i).getMotherID() == id) {//checks if the ids match
                email = this.mothersArray.get(i).getMotherEmail();//sets the meial to the iterated email
            }
        }
        return email;//returns the email
    }//closes the getMotherEmailFromID method
    
    public void editMother(String name, String email, String cell) {//creates a method to edit a parent
        ConnectDB db = new ConnectDB();//creates an object for the connectDB class
        int id = this.getMotherIDFromName(name);//creates an int for the id
        String updateEmail = "";//creates s string for the email SQL query to update
        String updateCell = "";//creates a string for the cell SQL query to update
        //checks if the user wants to update the email
        if (!email.equals("")) {//checks that the email is not blank
            updateEmail = "UPDATE mothers SET motherEmail = '" + email + "' WHERE MotherID = " + this.getMotherIDFromName(name);//sets the updateEmail string to the SQL query to update
            try {
                db.UpdateDatabase(updateEmail);//updates the email
            } catch (SQLException ex) {
                Logger.getLogger(mothersArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error updating the email
            }
        }
        if (!cell.equals("")) {//if the cell is not blank
            updateCell = "UPDATE mothers SET motherCell = '" + cell + "' WHERE MotherID = " + this.getMotherIDFromName(name);//sets the updateCell string to the SQL query to update
            try {
                db.UpdateDatabase(updateCell);//updates the cell
            } catch (SQLException ex) {
                Logger.getLogger(mothersArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error updating the cell
            }
        }
    }//closes the editMother method
    
}//closes the mothersArray class
