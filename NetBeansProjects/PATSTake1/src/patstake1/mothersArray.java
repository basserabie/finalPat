/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patstake1;

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
public class mothersArray {
    ConnectDB  db = new ConnectDB();
    dataValidation vd = new dataValidation();
    public ArrayList<fetchMothers> mothersArray = new ArrayList<>();

    public mothersArray() {
        ConnectDB  db = new ConnectDB();
        ResultSet r = db.getResults("SELECT * FROM mothers");
        try {
            while(r.next()) {
                fetchMothers fm = new fetchMothers(r.getInt("MotherID"), r.getString("motherfName"), 
                        r.getString("motherLName"), r.getString("motherEmail"), r.getString("motherCell"));
                mothersArray.add(fm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(mothersArray.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Database error, please contact administrator at 0836570642");
        }
        
    }

    public ArrayList<fetchMothers> getMothersArray() {
        return mothersArray;
    }
    
    public String getMotherNameForLessonArray(int id) {
        studentsArray sa = new studentsArray();
        String name = "";
        for (int i = 0; i < this.mothersArray.size(); i++) {
            if (sa.getStudentArray().get(sa.StudentIndexFromMotherID(id)).getMotherID() == this.mothersArray.get(i).getMotherID()) {
                name = this.mothersArray.get(i).getMotherFName() + " " + this.mothersArray.get(i).getMotherLName();
            }
        }
        return name;
    } 
    
    public String getMotherNameFromStudentID(int id) {
        studentsArray sa = new studentsArray();
        String name = "";
        
        for (int i = 0; i < this.mothersArray.size(); i++) {
            if (sa.getStudentArray().get(sa.MotherIndexFromStudentID(id)).getMotherID() == this.mothersArray.get(i).getMotherID()) {
                name = this.mothersArray.get(i).getMotherFName() + " " + this.mothersArray.get(i).getMotherLName();
            }
        }
        return name;
    } 
    
    public String getMotherNameFromIndex(int index) {
        String name = "";
        for (int i = 0; i < this.mothersArray.size(); i++) {
            if (i == index) {
                name = this.mothersArray.get(i).getMotherFName() + " " + this.mothersArray.get(i).getMotherLName();
            }
        }
        return name;
    }
    
    public String [] getStudentsFromMotherName(String name) {
        ArrayList<String> students = new ArrayList<>();
        int count = 0;
        studentsArray sa = new studentsArray();
        for (int i = 0; i < this.mothersArray.size(); i++) {
            if (this.getMotherNameFromIndex(i).equals(name)) {
                for (int k = 0; k < sa.getStudentArray().size(); k++) {
                    if (this.getMotherNameForLessonArray(sa.getStudentArray().get(k).getMotherID()).equals(name)) {
                        count ++;
                        students.add(sa.studentNameFromID(sa.getStudentArray().get(k).getStudentID()));
                    }
                }
            }
        }
        String studentsArray [] = students.toArray(new String[students.size()]);
        return studentsArray;
    }
    
    public int getMotherIDFromName(String name) {
        int id = 0;
        for (int i = 0; i < this.mothersArray.size(); i++) {
            if (this.getMotherNameForLessonArray(this.mothersArray.get(i).getMotherID()).equals(name)) {
                id = this.mothersArray.get(i).getMotherID();
            }
        }
        return id;
    }
    
    public void passSelectedMotherNameToEdit(String name) {
        editMotherForm emf = new editMotherForm();
        emf.setParentNameText(name);
        emf.setVisible(true);
    }
    
    public void passSelectedMotherToInfo(String name) {
        moreInfoParentForm mi = new moreInfoParentForm();
        mi.setParentNameText(name);
        mi.setVisible(true);
    }
    
    public int getMotherIDFromMotherName(String name) {
        int id = 0;
        for (int i = 0; i < this.mothersArray.size(); i++) {
            String motherName = this.getMothersArray().get(i).getMotherFName() + " " + this.getMothersArray().get(i).getMotherLName();
            if (motherName.toLowerCase().equals(name.toLowerCase())) {
                id = this.getMothersArray().get(i).getMotherID();
            }
        }
        return id;
    }
    
    public String getMotherEmailFromID(int id) {
        String email = "";
        for (int i = 0; i < this.mothersArray.size(); i++) {
            if (this.mothersArray.get(i).getMotherID() == id) {
                email = this.mothersArray.get(i).getMotherEmail();
            }
        }
        return email;
    }
    
    public void editMother(String name, String email, String cell) {
        ConnectDB db = new ConnectDB();
        int id = this.getMotherIDFromName(name);
        String updateEmail = "";
        String updateCell = "";
        //checks if the user wants to update the email
        if (!email.equals("")) {
            updateEmail = "UPDATE mothers SET motherEmail = '" + email + "' WHERE MotherID = " + this.getMotherIDFromName(name);
            try {
                db.UpdateDatabase(updateEmail);
            } catch (SQLException ex) {
                System.out.println("error updating email");
                Logger.getLogger(mothersArray.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!cell.equals("")) {
            updateCell = "UPDATE mothers SET motherCell = '" + cell + "' WHERE MotherID = " + this.getMotherIDFromName(name);
            try {
                db.UpdateDatabase(updateCell);
            } catch (SQLException ex) {
                System.out.println("error updating cell");
                Logger.getLogger(mothersArray.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
