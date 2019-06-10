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
public class fetchTeacher {
    ConnectDB db = new ConnectDB();
    loginSignup ls = new loginSignup();
    dataValidation vd = new dataValidation();
    private String fname, lname, cell, email, password, currentYear, question, answer;
    private String signUpProblems = "";
    boolean signedUp = false;

    //constructor fecthes teacherData from teacherTable
    public fetchTeacher() {
        //creates resultSet fetching all data from teacherTable
        String sql = "SELECT * FROM teacherTable";
        ResultSet rs = db.getResults(sql);
        try {
            while (rs.next()) {
                //fetches first name from teacerTable
                String fnametest = rs.getString("fName");
                this.fname = fnametest;
                //fetches last name from teacerTable
                String lnametest = rs.getString("lName");
                this.lname = lnametest;
                //fetches cell from teacerTable
                String celltest = rs.getString("cell");
                this.cell = celltest;
                //fetches email from teacerTable
                String emailtest = rs.getString("email");
                this.email = emailtest;
                //fetches password from teacerTable
                String passwordtest = rs.getString("password");
                this.password = passwordtest;
                //fetches first name from teacerTable
                if (fname != null) {
                    boolean signedUptest = rs.getBoolean("signedUp");
                    this.signedUp = signedUptest;
                } else {
                    this.signedUp = false;
                }
                System.out.println("signedUp: " + this.signedUp);
                //fetches this current date of signUp/LogIn
                String currentYear = rs.getString("currentYear");
                this.currentYear = currentYear;
                this.question = rs.getString("question");
                this.answer = rs.getString("answer");
            }
        } catch (SQLException ex) {
//            Logger.getLogger(fetchTeacher.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("problem at fetching teacherTable data");
        }
        
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getSignedUp() {
        return signedUp;
    }

    public void SetSignedUp() {
        this.signedUp = signedUp;
    }

    public String getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(String currentYear) {
        this.currentYear = currentYear;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    public void editSecurityAnswer() {
        ConnectDB db = new ConnectDB();
        int type = Integer.parseInt(JOptionPane.showInputDialog("Choose security question:\n\n1: What is your favourite holiday location?\n2: what is your favourite ice cream flaovour?"));
        String ans = "";
        String question = "";
        if (type == 1) {
            question = "What is your favourite holiday location?";
            ans = JOptionPane.showInputDialog("Enter your location.");
        } else {
            if (type == 2) {
                question = "What is your favourite ice cream flavour?";
                ans = JOptionPane.showInputDialog("Enter your flavour.");
            } else {
                JOptionPane.showMessageDialog(null, "please select a valid question type!");
            }
        }
        String update = "UPDATE teacherTable SET question = '" + question + "', answer = '" + ans + "'";
        try {
            db.UpdateDatabase(update);
        } catch (SQLException ex) {
            Logger.getLogger(fetchTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //todo: fix this
    public boolean validateSignUp(String fname, String lname, String email, 
            String cell, String password, String confirmPassword) {
        boolean ok = true;
        
        if (!vd.checkName(fname, "f")) { 
            ok = false;
            this.signUpProblems += vd.getProblems();
        } if (!vd.checkName(lname, "l")) {
            ok = false;
            this.signUpProblems += vd.getProblems();
        } if (!vd.checkEmail(email)) {
            ok = false;
            this.signUpProblems += vd.getProblems();
        } if (!vd.checkCell(cell)) {
            ok = false;
            this.signUpProblems += vd.getProblems();
        } if (!vd.checkPassword(password, confirmPassword)) {
            ok = false;
            this.signUpProblems += vd.getProblems();
        }
        if (!this.signUpProblems.equals("")) {
            JOptionPane.showMessageDialog(null, this.signUpProblems);
        }
        return ok;
    }
    
    public void changePassword(String oldP, String newP, String CnewP) {
        ConnectDB cb = new ConnectDB();
        dataValidation dv = new dataValidation();
        String updatePassword = "UPDATE teacherTable SET password = '" + newP + "'";
        try {
            db.UpdateDatabase(updatePassword);
            JOptionPane.showMessageDialog(null, "password changed!");
        } catch (SQLException ex) {
            Logger.getLogger(fetchTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void changeCell(String cell) {
        ConnectDB db = new ConnectDB();
        dataValidation dv = new dataValidation();
            String updateCell = "UPDATE teacherTable SET cell = '" + cell + "'";
            try {
                db.UpdateDatabase(updateCell);
            } catch (SQLException ex) {
                Logger.getLogger(fetchTeacher.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void changeEmail(String email) {
        ConnectDB db = new ConnectDB();
        dataValidation dv = new dataValidation();
            String updateEmail = "UPDATE teacherTable SET email = '" + email + "'";
            try {
                db.UpdateDatabase(updateEmail);
            } catch (SQLException ex) {
                Logger.getLogger(fetchTeacher.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
}
