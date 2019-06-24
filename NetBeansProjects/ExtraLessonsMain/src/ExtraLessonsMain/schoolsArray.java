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
public class schoolsArray {
    ConnectDB  db = new ConnectDB();
    dataValidation vd = new dataValidation();
    private ArrayList<fetchSchools> schoolsDataArray = new ArrayList<>();

    public schoolsArray() {
        ResultSet r = db.getResults("SELECT * FROM schools");
        try {
            while(r.next()) {
                fetchSchools fs = new fetchSchools(r.getInt("schoolID"), r.getString("schoolName")
                        , r.getString("PFName"), r.getString("PLName"), r.getString("PEmail"));
                schoolsDataArray.add(fs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Database error, please contact administrator at 0836570642");
        }
    }

    public ArrayList<fetchSchools> getSchoolsDataArray() {
        return schoolsDataArray;
    }

    public void setSchoolsDataArray(ArrayList<fetchSchools> schoolsDataArray) {
        this.schoolsDataArray = schoolsDataArray;
    }
    
    public int getSchoolID(String name) {
        int temp = 0;
        for (int i = 0; i < schoolsDataArray.size(); i++) {
            if (schoolsDataArray.get(i).getSchoolName().equals(name)) {
                temp = schoolsDataArray.get(i).getSchoolID();
            }
        }
        return temp;
    }
    
    public String getSchoolNameFromID(int id) {
        String school = "";
        for (int i = 0; i < this.schoolsDataArray.size(); i++) {
            if (this.schoolsDataArray.get(i).getSchoolID() == id) {
                school = this.schoolsDataArray.get(i).getSchoolName();
            }
        }
        return school;
    }
    
    public void addSchool(String school, String pfname, String plname, String email) {
        ConnectDB db = new ConnectDB();
        String insert = "INSERT INTO schools (schoolName, PFName, PLName, PEmail) VALUES ('" + school + "', '" + pfname + "', '" + plname + "', '" + email + "')";
        try {
            db.UpdateDatabase(insert);
        } catch (SQLException ex) {
            Logger.getLogger(schoolsArray.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteSchool(String school) {
        int yes = JOptionPane.YES_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "are you sure you want to delete this school?", "delete school confirmation", JOptionPane.YES_NO_OPTION);
        if (dialogResult == yes) {
            ConnectDB db = new ConnectDB();
            String delete = "DELETE * FROM schools WHERE schoolName = '" + school + "'";
            try {
                db.UpdateDatabase(delete);
            } catch (SQLException ex) {
                Logger.getLogger(schoolsArray.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "School information not deleted");
        }
    }
    
    public int getSchoolIDFromName(String name) {
        int id = 0;
        for (int i = 0; i < this.schoolsDataArray.size(); i++) {
            if (this.schoolsDataArray.get(i).getSchoolName().equals(name)) {
                id = this.schoolsDataArray.get(i).getSchoolID();
            }
        }
        return id;
    }
    
    public String getSchoolFtomStudentID(int id) {
        String school = "";
        studentsArray sa = new studentsArray();
        for (int i = 0; i < sa.getStudentArray().size(); i++) {
            if (sa.getStudentArray().get(i).getStudentID() == id) {
                school = this.getSchoolNameFromID(sa.getStudentArray().get(i).getSchoolID());
            }
        }
        return school;
    }
    
}
