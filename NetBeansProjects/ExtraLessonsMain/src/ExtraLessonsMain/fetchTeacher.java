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
public class fetchTeacher {//creates a class representing a teacher (user) object
    private String fname, lname, cell, email, password, currentYear, question, answer, path;//creates string holding the first name, last name, cell, email, password, current date logged in, security question and answer, and absolute path to icon of the teacher object
    private String signUpProblems = "";//creates a string to holf the laert messages for the user regarding invalid sign up data
    public boolean signedUp = false;//creates a boolean indiciating whether the current user has signed up before

    //constructor fecthes teacherData from teacherTable
    public fetchTeacher() {//creates the cosntructor for the current class and instantiates a teacher object (there will only ever be one)
        ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
        //creates resultSet fetching all data from teacherTable
        String sql = "SELECT * FROM teacherTable";//creates a string holding the SQL query used to get the teacher info from the database
        ResultSet rs = db.getResults(sql);//creates a result set of the teacher data
        try {//opens a trycatch statement
            while (rs.next()) {//iterates through the resultSet
                //fetches first name from teacerTable
                this.fname = rs.getString("fName");//assignes the fname of the object to the gotten first name
                //fetches last name from teacerTable
                this.lname = rs.getString("lName");//assignes the lname of the object to the gotten last name
                //fetches cell from teacerTable
                this.cell = rs.getString("cell");//assignes the cell of the object to the gotten cell
                //fetches email from teacerTable
                this.email = rs.getString("email");//assignes the email of the object to the gotten email
                //fetches password from teacerTable
                this.password = rs.getString("password");//assignes the password of the object to the gotten password
                //fetches first name from teacerTable
                if (fname != null) {
                    this.signedUp = rs.getBoolean("signedUp");//assignes the signedUp boolean of the object to the gotten signedUp boolean
                } else {
                    this.signedUp = false;
                }
                //fetches this current date of signUp/LogIn
                this.currentYear = rs.getString("currentYear");//assignes the currentDate of the object to the gotten current date
                this.question = rs.getString("question");//assignes the security question of the object to the gotten security question
                this.answer = rs.getString("answer");//assignes the security answer of the object to the gotten security answer
                this.path = rs.getString("imagepath");//assignes the image path of the object to the gotten image path
            }
        } catch (SQLException ex) {//opens the catch statement
            Logger.getLogger(fetchTeacher.class.getName()).log(Level.SEVERE, null, ex);//alerts the user that there was an error creting the resultSet
        }//closes the catch
    }//closes the constructor

    public String getFname() {//creates a method to return the first name
        return fname;//returns the first name
    }//closes the getter method

    public void setFname(String fname) {//creates a method to set the first name in: first name to set to
        this.fname = fname;//sets the first name
    }//closes the setter method

    public String getLname() {//creates a method to return the last name
        return lname;//returns the last name
    }//closes the getter method

    public void setLname(String lname) {//creates a method to set the last name in: last name to set to
        this.lname = lname;//sets the last name
    }//closes the setter method

    public String getCell() {//creates a method to return the cell
        return cell;//returns the cell
    }//closes the getter method

    public void setCell(String cell) {//creates a method to set the cell in: cell to set to
        this.cell = cell;//sets the cell
    }//closes the setter method

    public String getEmail() {//creates a method to return the email
        return email;//returns the email
    }//closes the getter method

    public void setEmail(String email) {//creates a method to set the email in: cell number to set to
        this.email = email;//sets the email
    }//closes the setter method

    public String getPassword() {//creates a method to return the password
        return password;//returns the password
    }//closes the getter method

    public void setPassword(String password) {//creates a method to set the password in: password to set to
        this.password = password;//sets the password
    }//closes the setter method

    public boolean getSignedUp() {//creates a method to return the state of the login
        return signedUp;//returns the state of login
    }//closes the getter method

    public void SetSignedUp(boolean s) {//creates a method to set the state of login in: signedUp state to set to
        this.signedUp = s;//sets the state of the login
    }//closes the setter method

    public String getCurrentYear() {//creates a method to return the current date of login
        return currentYear;//returns the current date
    }//closes the getter method

    public void setCurrentYear(String currentYear) {//creates a method to set the current date of login in: date to set to
        this.currentYear = currentYear;//sets the current date of login
    }//closes the setter method

    public String getQuestion() {//creates a method to return the security question
        return question;//returns the security question
    }//closes the getter method

    public void setQuestion(String question) {//creates a method to set the security question in: question to set to
        this.question = question;//sets the security question
    }//closes the setter method

    public String getAnswer() {//creates a method to return the security answer
        return answer;//returns the security answer
    }//closes the getter method

    public void setAnswer(String answer) {//creates a method to set the security answer in: answer to set to
        this.answer = answer;//sets the security answer
    }//closes the setter method

    public String getPath() {//creates a method to return the image path
        return path;//returns the image path
    }//closes the getter method

    public void setPath(String path) {//creates a method to set the icon path in: icon path to set to
        this.path = path;//sets the icon path
    }//closes the setter method
    
    public void changeSecurity(String question, String ans) {//creates a method to change the security and answer in: question and answer of the user
        ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
        loginSignUpHandler h = new loginSignUpHandler();//creates an object for the loginSignUpHandler class
        String decryptedPass = h.decryptPassword(password);//creates a string holding the decrypted password
            String update = "UPDATE teacherTable SET question = '" + question + "', answer = '" + ans + "'";//creates a string for the SQL query used to change the security question and answer
            try {//opens the trycatch statement
                db.UpdateDatabase(update);//updates the question and answer
            } catch (SQLException ex) {//opens the catch statement
                Logger.getLogger(fetchTeacher.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error chenging the question and answer
            }//closes the catch statement
            String upPass = "UPDATE teacherTable SET password = '" + h.encryptPassword(decryptedPass, ans.toLowerCase()) + "'";//creates a string for the SQL query that updates the newly encrypted password (the key being the new answer)
            try {//opens the trycatch statement
                db.UpdateDatabase(upPass);//updates the password
            } catch (SQLException ex) {//opens the catch statement
                Logger.getLogger(fetchTeacher.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error updating the password
            }//closes the catch statement
//            h.decryptPassword(h.encryptPassword(this.password, ans.toLowerCase()));
    }//closes the changeSecurity method
    
    public void getSecurityAnswerForm() {//creates a method validating the permissions of the user to change their security answer and question
        SecurityQuestionForm.changing = true;//sets the changing boolean of the securityQuestionForm to true indicating that the question and answer is being changed and not added for the first time
        ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
        loginSignUpHandler h = new loginSignUpHandler();//creates an object for the loginSignUpHandler class
        
        String password = JOptionPane.showInputDialog("Enter your password");//creates a string to hold the attempted password (the user must eneter ther password to change their security question and answer)
        if (this.password.equals(h.encryptPassword(password, this.answer.toLowerCase()))) {//checks if the attempted password is correct
            SecurityQuestionForm s = new SecurityQuestionForm();//creates an object for the SecurityQuestionForm class
            s.setVisible(true);//sets the SecurityQuestionForm class JFrame
        } else {//if the password enetered is incorrect
            JOptionPane.showMessageDialog(null, "Incorrect Password");//alerts the user that the password inputted was incorrect
        }
    }//closes the getSecurityAnswerForm class
    
    // in: first name, last name,,, email address, cell number, password, confirmation password to check
    public boolean validateSignUp(String fname, String lname, String email, String cell, String password, String confirmPassword) {//creates a method to validate the sign up data
        dataValidation vd = new dataValidation();//creates an object for the dataValidation class
        boolean ok = true;//creates a boolean indicating that the data is valid
        if (!vd.checkName(fname, "f")) {//checks if the first name inputted is not valid
            ok = false;//flips ok to false
            this.signUpProblems += vd.getProblems();//adds the first name error maessage to the problems string
        } if (!vd.checkName(lname, "l")) {//checks if the last name inputted is not valid
            ok = false;//flips ok to false
            this.signUpProblems += vd.getProblems();//adds the last name error maessage to the problems string
        } if (!vd.checkEmail(email)) {//checks if the emailinputted is not valid
            ok = false;//flips ok to false
            this.signUpProblems += vd.getProblems();//adds the email error maessage to the problems string
        } if (!vd.checkCell(cell)) {//checks if the cell inputted is not valid
            ok = false;//flips ok to false
            this.signUpProblems += vd.getProblems();//adds the cell error maessage to the problems string
        } if (!vd.checkPassword(password, confirmPassword)) {//checks if the password inputted is not valid or does not match the confirm password
            ok = false;//flips ok to false
            this.signUpProblems += vd.getProblems();//adds the password error maessage to the problems string
        }
        if (!this.signUpProblems.equals("")) {//checks if there are any errors with the data
            JOptionPane.showMessageDialog(null, this.signUpProblems);//alerts the user of the invalid data
        }
        return ok;//returns ok
    }//closes the validateSignUp method
    
    // in: old password for validation, new password to set to, new password verification
    public void changePassword(String oldP, String newP, String CnewP) {//creates a method allwoing the user to change their password
        ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
        dataValidation dv = new dataValidation();//creates an object for the dataValidation class
        loginSignUpHandler h = new loginSignUpHandler();//creates an object for the loginSignupHandler class
        String enPass = h.encryptPassword(newP, this.getAnswer().toLowerCase());//creates a string storing the encrypted new password
        String updatePassword = "UPDATE teacherTable SET password = '" + enPass + "'";//creates a string for the SQL query that will update the password
        try {//opens the trycatch staement
            db.UpdateDatabase(updatePassword);//updates the password
            JOptionPane.showMessageDialog(null, "password changed!");//alerts the user that the password was updated
        } catch (SQLException ex) {//opens the catch statement
            Logger.getLogger(fetchTeacher.class.getName()).log(Level.SEVERE, null, ex);//alerts the user that there was an error updating the password
        }//closes the catch
    }//closes the changePassword method
    
    public void changeCell(String cell) {//creates a method to chenge the cell number of the user in: cell to change to
        ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
        dataValidation dv = new dataValidation();//creates an object for the dataValidation class
            String updateCell = "UPDATE teacherTable SET cell = '" + cell + "'";//creates a string to hold the SQL query to update the cell
            try {//opens the trycatch statement
                db.UpdateDatabase(updateCell);//updates the cell number
            } catch (SQLException ex) {//opens the catch statement
                Logger.getLogger(fetchTeacher.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error updating the cell
            }//closes the catch statement
    }//closes the changeCell method
    
    public void changeEmail(String email) {//creates a method to change the user email in: email to change to
        ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
        dataValidation dv = new dataValidation();//creates an object for the dataValidation class
            String updateEmail = "UPDATE teacherTable SET email = '" + email + "'";//creates a string holding the SQL query used to update the email address
            try {//opens the trycatch statement
                db.UpdateDatabase(updateEmail);//updates the email addess
            } catch (SQLException ex) {//opens the catch statement
                Logger.getLogger(fetchTeacher.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error updating the email address
            }//closes the catch statement
    }//closes the changeEmail method
    
}//closes the fetchTeacher class
