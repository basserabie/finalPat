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
    
    public int getMotherID(String cell) {
        int temp = this.mothersArray.get(mothersArray.size()-1).getMotherID()+1;
        return temp;
    }
    
    public String getMotherName(int id) {
        studentsArray sa = new studentsArray();
        String name = "";
        
        for (int i = 0; i < this.mothersArray.size(); i++) {
            if (sa.getStudentArray().get(sa.StudentIndexFromID(id)).getMotherID() == this.mothersArray.get(i).getMotherID()) {
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
        studentsArray sa = new studentsArray();
        for (int i = 0; i < this.mothersArray.size(); i++) {
            if (this.getMotherNameFromIndex(i).equals(name)) {
                for (int k = 0; k < sa.getStudentArray().size(); k++) {
                    if (this.getMotherName(sa.getStudentArray().get(k).getMotherID()).equals(name)) {
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
            if (this.getMotherName(this.mothersArray.get(i).getMotherID()).equals(name)) {
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
    
    public void editMother(String name, String email, String cell) {
        ConnectDB db = new ConnectDB();
        int id = this.getMotherIDFromName(name);
        String updateEmail = "";
        String updateCell = "";
        System.out.println(email + "   " + cell);
        //checks if the user wants to update the email
        if (!email.equals("")) {
            updateEmail = "UPDATE mothers SET motherEmail = '" + email + "' WHERE MotherID = " + this.getMotherIDFromName(name);
            System.out.println("id: " + this.getMotherIDFromName(name));
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
