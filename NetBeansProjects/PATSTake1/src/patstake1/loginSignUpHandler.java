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
    
    public static boolean allGood = false;
    
    //TODO: make login screen invisible when works
    public void login(String passwordPassed) {
        dashboard d = new dashboard();
        loginSignup ls = new loginSignup();
        fetchTeacher ft = new fetchTeacher();
        boolean issignedUp = ft.getSignedUp();
        System.out.println(issignedUp);
        String password = ft.getPassword();
        if (issignedUp == true) {
            
            if (passwordPassed.equals(password)) {
                this.addLogInTime();
                allGood = true;
                d.setVisible(true);
                ls.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "oh no your password is incorrect");
            }
        } else {
            JOptionPane.showMessageDialog(null, "please sign up first!");
        }
    }
    
    public boolean validateSecurityQuestion(String ans) {
        fetchTeacher ft = new fetchTeacher();
        boolean ok = false;
        if (ans.toLowerCase().equals(ft.getAnswer().toLowerCase())) {
            ok = true;
        }
        return ok;
    }
    
    public void signUp(String fName, String lName, String email, String cell, String password1, String password2, String question, String answer) {
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
            if (ft.validateSignUp(fName, lName, 
                    email, cell, password1, password2)) {
                allGood = true;
                try {
                    String insertUserString = "INSERT INTO teacherTable(fname, lname, email, cell, password, signedUp, currentYear, question, answer)"
                            + " VALUES('" + fName + "', '" + lName + "', '" + email
                            + "', '" + cell + "', '" + password1 + "', " + true + ", '" + yearString + "', '" + question + "', '" + answer + "')";
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
    
    public void getSecurityAnswer() {
        int type = Integer.parseInt(JOptionPane.showInputDialog("Choose security question, enter your type of question/neither '1' or '2':\n\nOption 1: What is your favourite holiday location?\nOption 2: what is your favourite ice cream flaovour?"));
        String ans = "";
        if (type == 1) {
            loginSignup.question = "What is your favourite holiday location?";
            ans = JOptionPane.showInputDialog("Enter your location.");
        } else {
            if (type == 2) {
                loginSignup.question = "What is your favourite ice cream flavour?";
                ans = JOptionPane.showInputDialog("Enter your flavour.");
            } else {
                JOptionPane.showMessageDialog(null, "please select a valid question type!");
            }
        }
        loginSignup.answer = ans;
        loginSignup.enteredQuestion = true;
    }
    
    public void forgotPassword() {
       fetchTeacher ft = new fetchTeacher();
       loginSignUpHandler lsh = new loginSignUpHandler();
       if (ft.signedUp) {
           String ans = JOptionPane.showInputDialog(ft.getQuestion());
           if (lsh.validateSecurityQuestion(ans)) {
                sendEmail send = new sendEmail();
                send.send(ft.getEmail(), "Forgotten Password:", "Password: " + ft.getPassword());
           } else {
               JOptionPane.showMessageDialog(null, "answer wrong! please try again");
           }
       } else {
           JOptionPane.showMessageDialog(null, "Please sign up first");
       }
    }
    
    public void addLogInTime() {
        ConnectDB db = new ConnectDB();
        DateFormat sdf = new SimpleDateFormat("yyy/dd/MM");
        Calendar currentYear = Calendar.getInstance();
        currentYear.setTime(new Date());
        String yearString = sdf.format(currentYear.getTime());
        String updateLogInTime = "UPDATE teacherTable SET currentYear = '" + yearString + "'";
        try {
            db.UpdateDatabase(updateLogInTime);
        } catch (SQLException ex) {
            Logger.getLogger(loginSignUpHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
