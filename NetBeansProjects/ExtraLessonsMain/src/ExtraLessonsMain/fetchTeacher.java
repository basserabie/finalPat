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
public class fetchTeacher {
    ConnectDB db = new ConnectDB();
    loginSignup ls = new loginSignup();
    dataValidation vd = new dataValidation();
    private String fname, lname, cell, email, password, currentYear, question, answer, path;
    private String signUpProblems = "";
    boolean signedUp = false;

    //constructor fecthes teacherData from teacherTable
    public fetchTeacher() {
        ConnectDB db = new ConnectDB();
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
                //fetches this current date of signUp/LogIn
                String currentYear = rs.getString("currentYear");
                this.currentYear = currentYear;
                this.question = rs.getString("question");
                this.answer = rs.getString("answer");
                this.path = rs.getString("imagepath");
            }
        } catch (SQLException ex) {
//            Logger.getLogger(fetchTeacher.class.getName()).log(Level.SEVERE, null, ex);
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    public void changeSecurity(String question, String ans) {
        loginSignUpHandler h = new loginSignUpHandler();
        String decryptedPass = h.decryptPassword(password);
            String update = "UPDATE teacherTable SET question = '" + question + "', answer = '" + ans + "'";
            try {
                db.UpdateDatabase(update);
            } catch (SQLException ex) {
                Logger.getLogger(fetchTeacher.class.getName()).log(Level.SEVERE, null, ex);
            }
            String upPass = "UPDATE teacherTable SET password = '" + h.encryptPassword(decryptedPass, ans.toLowerCase()) + "'";
            try {
                db.UpdateDatabase(upPass);
            } catch (SQLException ex) {
                Logger.getLogger(fetchTeacher.class.getName()).log(Level.SEVERE, null, ex);
            }
            h.decryptPassword(h.encryptPassword(this.password, ans.toLowerCase()));
    }
    
    public void getSecurityAnswerForm() {
        SecurityQuestionForm.changing = true;
        ConnectDB db = new ConnectDB();
        loginSignUpHandler h = new loginSignUpHandler();
        
        String password = JOptionPane.showInputDialog("Enter your password");
        if (this.password.equals(h.encryptPassword(password, this.answer.toLowerCase()))) {
            SecurityQuestionForm s = new SecurityQuestionForm();
            s.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect Password");
        }
        
    }
    
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
        loginSignUpHandler h = new loginSignUpHandler();
        String enPass = h.encryptPassword(newP, this.getAnswer().toLowerCase());
        String updatePassword = "UPDATE teacherTable SET password = '" + enPass + "'";
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
