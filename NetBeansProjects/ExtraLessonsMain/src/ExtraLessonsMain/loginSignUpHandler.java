/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtraLessonsMain;

import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author YishaiBasserabie
 */
public class loginSignUpHandler {//creates a class to handle the login signup process
    
    public static boolean allGood = false;//creates a static boolean indicating whether the user details are correct
    
    public void loginWithFace(boolean recognised) {//creates a method to allow the user to log in with facial recognition
        loginSignup ls = new loginSignup();//creates an object for the loginSignUp class
        fetchTeacher ft = new fetchTeacher();//creates an object for the fetchTeacher class
        boolean issignedUp = ft.getSignedUp();//creates a boolean indicating whether the user has already signed up
        if (issignedUp == true) {//checks if the user has signed up
            if (recognised) {//checks if the users face is valid
                try {//opens the trycatch statement
                    this.addLogInTime();//adds the current date to the database
                    allGood = true;//sets the allGood boolean to true
                    dashboard d = new dashboard();//creates an object for the dashboard class
                    d.setVisible(true);//sets the dashboard JFrame visible
                    ls.setVisible(false);//discontinues the loginSignup JFrame
                } catch (IOException ex) {//opens the catch statement
                    Logger.getLogger(loginSignUpHandler.class.getName()).log(Level.SEVERE, null, ex);//alerts the user that there was an error updating the date in the database
                }
            } else {//if the face is invalid
                JOptionPane.showMessageDialog(null, "FACE NOT RECOGNISED\nDont worry, we still think you are quite pretty :)");//alerts the user that their face was not recognised
            }
        } else {//if the user has not signed up yet
            JOptionPane.showMessageDialog(null, "please sign up first!");//alerts the user to sign up first
        }
    }//closes the loginWithFace method
    
    public void login(String passwordPassed) {//creates a method allowing the user to login with a password
        loginSignup ls = new loginSignup();//creates an object for the loginSignUp class
        fetchTeacher ft = new fetchTeacher();//creates a method for the fetchTeacher class
        boolean issignedUp = ft.getSignedUp();//creates a boolean indicating whether the user has signed up
        String password = ft.getPassword();//creates a string of the encrypted password in the database
        if (issignedUp == true) {//checks if the user has signed up
            if (this.encryptPassword(passwordPassed, ft.getAnswer()).equals(password)) {//checks if the encrypted version of the attempted password matches the one in the database
                try {//opens the trycatch statement
                    this.addLogInTime();//adds the current date to the database
                    allGood = true;//sets the allGood boolean to true
                    dashboard d = new dashboard();//creates an object for the dashboard class
                    d.setVisible(true);//sets the dashboard JFrame visible
                    ls.setVisible(false);//discontinues the loginSignup JFrame
                } catch (IOException ex) {//opens the catch statement
                    Logger.getLogger(loginSignUpHandler.class.getName()).log(Level.SEVERE, null, ex);//alerts the user that there was an error updating the date in the database
                }
            } else {//if the passwords do not match
                JOptionPane.showMessageDialog(null, "oh no your password is incorrect");//alerts the user that the enetered password is incorrect
            }
        } else {//if the user has not signed up yet
            JOptionPane.showMessageDialog(null, "please sign up first!");//alerts the user to sign up first
        }
    }//closes the login method
    
    public boolean validateSecurityQuestion(String ans) {//creates a method to validate the attempted security question
        fetchTeacher ft = new fetchTeacher();//creates an object for the fetchTeacher class
        boolean ok = false;//creates a boolean indicating whether the security answer is correct
        if (ans.toLowerCase().equals(ft.getAnswer().toLowerCase())) {//checks if the answer passed in matches the answer in the database
            ok = true;//flips ok to true
        }
        return ok;//returns ok
    }//closes the validateSecurityQuestion method
    
    public void signUp(String fName, String lName, String email, String cell, String password1, String password2, String question, String answer) {//creates a method allowing the user to sign up
        try {//opens the trycatch statement
            loginSignup ls = new loginSignup();//creates an object for the loginSignup class
            ConnectDB db = new ConnectDB();//creates an object for the connectDB class
            dashboard d = new dashboard();//creates an object for the dashboard class
            fetchTeacher ft = new fetchTeacher();//creates an object for the fetchteacher class
            
            DateFormat sdf = new SimpleDateFormat("yyy/dd/MM");//creates adate formatter
            Calendar currentYear = Calendar.getInstance();//creates a clanedra instance
            currentYear.setTime(new Date());//sets the time of the date object to the current date
            String yearString = sdf.format(currentYear.getTime());//creates a string of the formatted current year date object
             
            if (ft.validateSignUp(fName, lName,
                    email, cell, password1, password2)) {//checks if the sign up data is valid
                allGood = true;//sets the allGood boolean to true
                takeIcon.changing = false;//sets the changing boolean for the icon to false i.e. an icon is being added for the first time
                try {//opens the trycatch statement
                    String insertUserString = "INSERT INTO teacherTable(fname, lname, email, cell, password, signedUp, currentYear, question, answer, imagepath)"
                            + " VALUES('" + fName + "', '" + lName + "', '" + email
                            + "', '" + cell + "', '" + this.encryptPassword(password1, answer) + "', " + true + ", '" + yearString + "', '" + question + "', '" + answer + "', '" + takeIcon.path + "')";//creates a string for the SQl query used to add the user to the database
                    db.UpdateDatabase(insertUserString);//adds the user the database
                    d.setVisible(true);//sets the dashboard JFrame visible
                    ls.setVisible(false);//sets the login/signup JFrame invisible
                } catch (SQLException ex) {//opens the catch statement
                    Logger.getLogger(loginSignup.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error inserting the user
                }//closes the catch statement
            } 
        } catch (IOException ex) {//opens the catch statement
            Logger.getLogger(loginSignUpHandler.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error setting the date object
        }//closes the catch
    }//closes the signUp method
    
    public void forgotPassword() {//creates a method to send an email of the password to the user
       fetchTeacher ft = new fetchTeacher();//creates an object for the fetchTeacher class
       loginSignUpHandler lsh = new loginSignUpHandler();//creates an object for the loginSignUpHandler class
       if (ft.signedUp) {//checks if the user has signed up
           String ans = JOptionPane.showInputDialog(ft.getQuestion());//creates a string for the answer to the security question
           if (lsh.validateSecurityQuestion(ans)) {//checks if the answer is correct
                sendEmail send = new sendEmail();//creates an object for the sendEmail class
                send.send(ft.getEmail(), "Forgotten Password:", "Password: " + this.decryptPassword(ft.getPassword()));//sends the emails of the password to the user
           } else {//if the answer was incorrect
               JOptionPane.showMessageDialog(null, "incorrect answer please try again");
           }
       } else {//if the user has not already signed up
           JOptionPane.showMessageDialog(null, "Please sign up first");//instructs the user to sign up first
       }
    }//closes the forgotPassword method
    
    public void addLogInTime() {//creates a method to add the current date to the database upon login
        ConnectDB db = new ConnectDB();//creates an object for the connectDB class
        DateFormat sdf = new SimpleDateFormat("yyy/dd/MM");//creates a date formatter
        Calendar currentYear = Calendar.getInstance();//creates a calendar instance
        currentYear.setTime(new Date());//sets the date and time of the current year to the current date and time
        String yearString = sdf.format(currentYear.getTime());//creates a formatted string reoresentation of the currentYear date object
        String updateLogInTime = "UPDATE teacherTable SET currentYear = '" + yearString + "'";//creates a string for the SQL query used to update the current date stored in the database
        try {//opens the trycatch statement
            db.UpdateDatabase(updateLogInTime);//updates the current date stored
        } catch (SQLException ex) {//opens the catch statement
            Logger.getLogger(loginSignUpHandler.class.getName()).log(Level.SEVERE, null, ex);//alerts the user that there was an error updating the date
        }//closes the catch statement 
    }//closes the addLogInTime method
    
    public String prepareKeyFromAnswer(String ans) {//creates a method to prepare an encryption key based on the answer to the user's security question
        String key = "";//creates a string for the key
        if (ans.length() > 16) {//checks if the lenght of the answer is bigger than 16
            key = ans.toLowerCase().substring(0, 16);//sets the to the frst 16 characters of the answer
        } else {//if the answer lenght is not bigger than 16
            if (ans.length() < 16) {//checks if the answer lenght is smaller than 16
                key = ans;//sets the key to the answer passed in
                for (int i = 0; i < (16-ans.length()); i++) {//runs a loop for the difference between 16 and the answer lenght
                    key += "y";//adds 'y' to the key to make it 16 characters
                }
            } else {//if the answer is 16 characters
                key = ans;//sets the key to the anser
            }
        }
        return key;//returns the key
    }//closes the prepareKeyFromAnswer method
    
     public String encryptPassword(String passwordin, String answer) {//creates a method to encrypt the password according to the answer key
        
         EncryptDecrypt encDec = new EncryptDecrypt();//creates a EncryptDecrypt object called encDec
            String Password = "";//creates a string variale called Password
        
            String passimp = passwordin;// sets the the variable that will be encryped 
            passimp = EncryptDecrypt.encrypt(passimp);//settting variable passimp with my encryption method returned String.

            String encryptk = this.prepareKeyFromAnswer(answer.toLowerCase());//sets the encryption key
            SecretKeySpec key = new SecretKeySpec(encryptk.getBytes(), "AES");// Creates a new SecretKeySpec called key inorder to encrypt the message
            Cipher encryptCipher;
            String encryptpas = "";
        try {//opens the trycatch statement
            encryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); //calls the methood getInstance from the Cipher class
             try {//opens the trycatch statement
                 encryptCipher.init(Cipher.ENCRYPT_MODE, key);//calls the init methood to initiate the decryptCipher
                try {//opens the trycatch statement
                    //encryption statement
                    encryptpas = Base64.encodeBase64String(encryptCipher.doFinal(passimp.getBytes()));//creates a string called decryptpass that decrypts the password
                } catch (IllegalBlockSizeException ex) {//opens the catch statement
                    Logger.getLogger(AddStudentNote.class.getName()).log(Level.SEVERE, null, ex);
                } catch (BadPaddingException ex) {//opens the catch statement
                    Logger.getLogger(AddStudentNote.class.getName()).log(Level.SEVERE, null, ex);
                }
             } catch (InvalidKeyException ex) {//opens the catch statement
                 Logger.getLogger(AddStudentNote.class.getName()).log(Level.SEVERE, null, ex);
             }
        } catch (NoSuchAlgorithmException ex) {//opens the catch statement
            Logger.getLogger(AddStudentNote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {//opens the catch statement
            Logger.getLogger(AddStudentNote.class.getName()).log(Level.SEVERE, null, ex);
        }
        Password = encryptpas;//sets the password to the decrypted password
        return Password;//returns the encrypted password
     }//closes the encryptPassword method
    
    public String decryptPassword(String password) {//creates a method to decrypt the password according to the answer key
        fetchTeacher ft = new fetchTeacher();
        String decrypin = password;//creates a string varable to store the cyper text
            String decryptk;//creates a string variable to store the key for the cyper 
            decryptk = this.prepareKeyFromAnswer(ft.getAnswer().toLowerCase());//sets the key for the cyper
            SecretKeySpec key = new SecretKeySpec(decryptk.getBytes(), "AES");// Creates a new SecretKeySpec called key inorder to encrypt the message 
            Cipher decryptCipher = null;//Cretaes a Cipher object to store the array
        try {//opens the trycatch statement
            decryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//calls the methood getInstance from the Cipher class
        } catch (NoSuchAlgorithmException ex) {//opens the catch statement
            Logger.getLogger(AddStudentNote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {//opens the catch statement
            Logger.getLogger(AddStudentNote.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {//opens the trycatch statement
            decryptCipher.init(Cipher.DECRYPT_MODE, key);//calls the init methood to initiate the decryptCipher
        } catch (InvalidKeyException ex) {//opens the catch statement
            Logger.getLogger(AddStudentNote.class.getName()).log(Level.SEVERE, null, ex);
        }
            String decryptpass = "";
        try {//opens the trycatch statement
            decryptpass = new String(decryptCipher.doFinal(Base64.decodeBase64(decrypin))); //creates a string called decryptpass that decrypts the password
        } catch (IllegalBlockSizeException ex) {//opens the catch statement
            Logger.getLogger(AddStudentNote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {//opens the catch statement
            Logger.getLogger(AddStudentNote.class.getName()).log(Level.SEVERE, null, ex);
        }
            decryptpass = EncryptDecrypt.decrypt(decryptpass);//Parsing the String to the method decrypt
        return decryptpass;//returns the decrpted password
    }//closes the decryptPassword method
    
    public void getHelp() {
        File htmlFile = new File("help.html");
        try {//opens the trycatch statement
            Desktop.getDesktop().browse(htmlFile.toURI());
        } catch (IOException ex) {//opens the catch statement
            Logger.getLogger(ExtraLessonsMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void changeIcon() {//creates a method to allow the user to change their icon
        takeIcon.changing = true;//sets the changing boolean to false i.e the icon is being changed not added for the first time
        boolean iconAdded = false;
        new takeIcon();//changes the icon
    }//closes the changeIcon method
    
    
}//closes the loginSignUpHandler class
