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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author YishaiBasserabie
 */
public class schoolsArray {//creates a class to handles the schools
    private ArrayList<fetchSchools> schoolsDataArray = new ArrayList<>();//creates an array list for the schools

    public schoolsArray() {//creates the constructor for the current class and instantiates a school array object
        ConnectDB  db = new ConnectDB();//creates an object for the connectDB class
        ResultSet r = db.getResults("SELECT * FROM schools");//creates a result set for the schools
        try {
            while(r.next()) {//iterates through the results
                fetchSchools fs = new fetchSchools(r.getInt("schoolID"), r.getString("schoolName")
                        , r.getString("PFName"), r.getString("PLName"), r.getString("PEmail"));//creates an object for the iterated school
                schoolsDataArray.add(fs);//adds the object to the array list
            }
        } catch (SQLException ex) {
            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error getting the results
            JOptionPane.showMessageDialog(null, "Database error, please contact administrator at 0836570642");//instructs the user to contact the admin
        }
    }

    public ArrayList<fetchSchools> getSchoolsDataArray() {//creates a method to get the array list
        return schoolsDataArray;//returns the list
    }//closes the getSchoolsDataArray method
    
    // in: school name
    public int getSchoolID(String name) {//creates a method to get the id from the name
        int temp = 0;//creates an int for the id
        for (int i = 0; i < schoolsDataArray.size(); i++) {//iterates through the schools
            if (schoolsDataArray.get(i).getSchoolName().equals(name)) {//checks if the iterated name is the same as the one passed in
                temp = schoolsDataArray.get(i).getSchoolID();//sets temp to the iterated id
            }
        }
        return temp;//returns temp
    }//closes the getSchoolID method
    
    // in: school id
    public String getSchoolNameFromID(int id) {//creates a method to get the name from id
        String school = "";//creates a string for the name
        for (int i = 0; i < this.schoolsDataArray.size(); i++) {//iterates through the schools
            if (this.schoolsDataArray.get(i).getSchoolID() == id) {//checks if the id passed in is the same as the ietrated
                school = this.schoolsDataArray.get(i).getSchoolName();//sets the school to the iertaed name
            }
        }
        return school;//returns the school
    }//closes the getSchoolNameFromID method
    
    // in: school name, principal details
    public void addSchool(String school, String pfname, String plname, String email) {//creates a method to add a school
        ConnectDB db = new ConnectDB();//creates an object for the connectDB
        String insert = "INSERT INTO schools (schoolName, PFName, PLName, PEmail) VALUES ('" + school + "', '" + pfname + "', '" + plname + "', '" + email + "')";//creates a string for the SQL query to add a school
        try {
            db.UpdateDatabase(insert);//adds the school
        } catch (SQLException ex) {
            Logger.getLogger(schoolsArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error adding the schcool
        }
    }//closes the addSchool method
    
    // in: school name
    public void deleteSchool(String school) {//creates a method to delete a school
        int yes = JOptionPane.YES_OPTION;//creates an int for the yes JOptionPane oprion
        int dialogResult = JOptionPane.showConfirmDialog(null, "are you sure you want to delete this school?", "delete school confirmation", JOptionPane.YES_NO_OPTION);//asks if the user would like to delete
        if (dialogResult == yes) {//checks if the user would like to delete
            ConnectDB db = new ConnectDB();//creates an object for the connectDB class
            String delete = "DELETE * FROM schools WHERE schoolName = '" + school + "'";//creates a string for the SQL query to delete the school
            try {
                db.UpdateDatabase(delete);//deletes the school
            } catch (SQLException ex) {
                Logger.getLogger(schoolsArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error deleting the school
            }
        } else {//if the user would not like to delete the school
            JOptionPane.showMessageDialog(null, "School information not deleted");//alerts the user that no school was deleted
        }
    }//closes the deleteSchool method
    
    // in: student id
    public String getSchoolFtomStudentID(int id) {//creates a method to get the school from the student id
        String school = "";//creates a string for the school
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        for (int i = 0; i < sa.getStudentArray().size(); i++) {//iterates through the students
            if (sa.getStudentArray().get(i).getStudentID() == id) {//checks if the school id iterated is the same as the one passed on
                school = this.getSchoolNameFromID(sa.getStudentArray().get(i).getSchoolID());//sets the school to the iterated school
            }
        }
        return school;//returns the school
    }//closes the getSchoolFtomStudentID method
    
}//closes the schoolsArray class
