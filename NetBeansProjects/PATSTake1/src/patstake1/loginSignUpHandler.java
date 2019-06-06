/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patstake1;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author YishaiBasserabie
 */
public class loginSignUpHandler {
    //TODO: make login screen invisible when works
    public void login(String passwordPassed) {
        dashboard d = new dashboard();
        loginSignup ls = new loginSignup();
        fetchTeacher ft = new fetchTeacher();
        boolean issignedUp = ft.getSignedUp();
        String password = ft.getPassword();
        if (issignedUp == true) {
            if (passwordPassed.equals(password)) {
                d.setVisible(true);
                ls.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "oh no your password is incorrect");
            }
        } else {
            JOptionPane.showMessageDialog(null, "please sign up first!");
        }
    }
    
    public void signUp(String fName, String lName, String email, String cell, String password1, String password2) {
        //objects
        loginSignup ls = new loginSignup();
        ConnectDB db = new ConnectDB();
        dashboard d = new dashboard();
        fetchTeacher ft = new fetchTeacher();
        boolean issignedUp = ft.getSignedUp();
        
        DateFormat sdf = new SimpleDateFormat("yyy/dd/MM");
        Calendar currentYear = Calendar.getInstance();
        currentYear.setTime(new Date());
        String yearString = sdf.format(currentYear.getTime());
        System.out.println("cy: " + yearString);
        
        if (issignedUp == false) {
//            TODO: fix validation
            if (ft.validateSignUp(fName, lName, 
                    email, cell, password1, password2)) {
                try {
                    String insertUserString = "INSERT INTO teacherTable(fname, lname, email, cell, password, signedUp, currentYear)"
                            + " VALUES('" + fName + "', '" + lName + "', '" + email
                            + "', '" + cell + "', '" + password1 + "', " + true + ", '" + yearString + "')";
                    db.UpdateDatabase(insertUserString);
                    System.out.println("\n" + insertUserString);
                    d.setVisible(true);
                    ls.setVisible(false);
                } catch (SQLException ex) {
                    Logger.getLogger(loginSignup.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("problem in sign up");
                }
            } 
        } else {
            JOptionPane.showMessageDialog(null, "you are already signed up\nplease login instead");
        }
    }
    
}
