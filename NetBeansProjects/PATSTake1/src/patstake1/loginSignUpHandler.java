/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patstake1;

import java.awt.Desktop;
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
import javax.swing.JOptionPane;
import org.apache.commons.codec.binary.Base64;

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
            
            if (this.encryptPassword(passwordPassed, ft.getAnswer()).equals(password)) {
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
                            + "', '" + cell + "', '" + this.encryptPassword(password1, answer) + "', " + true + ", '" + yearString + "', '" + question + "', '" + answer + "')";
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
                send.send(ft.getEmail(), "Forgotten Password:", "Password: " + this.decryptPassword(ft.getPassword()));
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
    
    public String prepareKeyFromAnswer(String ans) {
        String key = "";
        if (ans.length() > 16) {
            key = ans.toLowerCase().substring(0, 16);
        } else {
            if (ans.length() < 16) {
                key = ans;
                for (int i = 0; i < (16-ans.length()); i++) {
                    key += "y";
                }
            } else {
                key = ans;
            }
        }
        return key;
    }
    
     public String encryptPassword(String passwordin, String answer) {
        
         EncryptDecrypt encDec = new EncryptDecrypt();//creates a EncryptDecrypt object called encDec
            String Password = "";//creates a string variale called Password
        
            String passimp = passwordin;// sets the the variable that will be encryped 
            passimp = EncryptDecrypt.encrypt(passimp);//settting variable passimp with my encryption method returned String.

            String encryptk = this.prepareKeyFromAnswer(answer.toLowerCase());//sets the encryption key
            SecretKeySpec key = new SecretKeySpec(encryptk.getBytes(), "AES");// Creates a new SecretKeySpec called key inorder to encrypt the message
            Cipher encryptCipher;
            String encryptpas = "";
        try {
            encryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); //calls the methood getInstance from the Cipher class
             try {
                 encryptCipher.init(Cipher.ENCRYPT_MODE, key);//calls the init methood to initiate the decryptCipher
                try {
                    //encryption statement
                    encryptpas = Base64.encodeBase64String(encryptCipher.doFinal(passimp.getBytes()));//creates a string called decryptpass that decrypts the password
                } catch (IllegalBlockSizeException ex) {
                    Logger.getLogger(AddStudentNote.class.getName()).log(Level.SEVERE, null, ex);
                } catch (BadPaddingException ex) {
                    Logger.getLogger(AddStudentNote.class.getName()).log(Level.SEVERE, null, ex);
                }
             } catch (InvalidKeyException ex) {
                 Logger.getLogger(AddStudentNote.class.getName()).log(Level.SEVERE, null, ex);
             }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AddStudentNote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(AddStudentNote.class.getName()).log(Level.SEVERE, null, ex);
        }
        Password = encryptpas;//sets the password to the decrypted password
        
        String etext = Password;
        return Password;
     }
    
    public String decryptPassword(String password) {
        fetchTeacher ft = new fetchTeacher();
        String decrypin = password;//creates a string varable to store the cyper text
            String decryptk;//creates a string variable to store the key for the cyper 
            decryptk = this.prepareKeyFromAnswer(ft.getAnswer().toLowerCase());//sets the key for the cyper
            SecretKeySpec key = new SecretKeySpec(decryptk.getBytes(), "AES");// Creates a new SecretKeySpec called key inorder to encrypt the message 
            Cipher decryptCipher = null;//Cretaes a Cipher object to store the array
        try {
            decryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//calls the methood getInstance from the Cipher class
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AddStudentNote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(AddStudentNote.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try {
            decryptCipher.init(Cipher.DECRYPT_MODE, key);//calls the init methood to initiate the decryptCipher
        } catch (InvalidKeyException ex) {
            Logger.getLogger(AddStudentNote.class.getName()).log(Level.SEVERE, null, ex);
        }
            String decryptpass = "";
        try {
            decryptpass = new String(decryptCipher.doFinal(Base64.decodeBase64(decrypin))); //creates a string called decryptpass that decrypts the password
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(AddStudentNote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(AddStudentNote.class.getName()).log(Level.SEVERE, null, ex);
        }
            decryptpass = EncryptDecrypt.decrypt(decryptpass);//Parsing the String to the method decrypt
        return decryptpass;
    }
    
    public void getHelp() {
        File htmlFile = new File("help.html");
        try {
            Desktop.getDesktop().browse(htmlFile.toURI());
        } catch (IOException ex) {
            Logger.getLogger(PATSTake1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
