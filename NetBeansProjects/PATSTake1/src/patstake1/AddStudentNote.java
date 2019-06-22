/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patstake1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/*import static pat.Login.nUser;*/

/**
 *
 * @author Jacob Sacks
 */
public class AddStudentNote { // main method 

    public void createNote(String name) {//creates method that creates a new txt file according to the student name
        try{ //opens try-catch
            String path = name + ".txt";//instantiates a string holding the new path for the txt file
            File file = new File(path);//instantiates the file of path path
            file.createNewFile();//creates the file
        }//closes the try bracket
        catch(Exception e){//opens the catch bracket
            System.out.println(e);//outputs the error
        }//coses the catch bracket
    }//ends the createNote method
    
    public void deleteNote(String name) {//creates the method to delete a txt file according to the inputted student name
        File file = new File(name + ".txt");//insttiates a file object according to path determied by the name
        if(file.delete()){//deletes the text file and checks if it was deleted
            System.out.println("file.txt File deleted from Project root directory");//outputs the message saying that the file was deleted
        }else System.out.println("File file.txt doesn't exist in the project root directory");//outputs the message saying that no such file exists
    }//closes the deleteNote method
    
    public void writing(String name, String text) {//creates a method that writes to a text file according to the student name inputted
         EncryptDecrypt encDec = new EncryptDecrypt();//creates a EncryptDecrypt object called encDec
            String Password = "";//creates a string variale called Password
        
            String passimp = text;// sets the the variable that will be encryped 
            passimp = EncryptDecrypt.encrypt(passimp);//settting variable passimp with my encryption method returned String.

            String encryptk = "thisisasecurekey";//sets the encryption key
            SecretKeySpec key = new SecretKeySpec(encryptk.getBytes(), "AES");// Creates a new SecretKeySpec called key inorder to encrypt the message
            Cipher encryptCipher; //creates a Cipher object
            String encryptpas = "";//instantiates a string
        try {//opens the try-catch
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
        try{//opens the trycatch
                    PrintWriter pw = new PrintWriter(name + ".txt");//creates a printWriter object to erite to the text file according to the student name
                    pw.close();//closes the printWtier object
                    try(FileWriter fw = new FileWriter(name + ".txt", true);//attempts to open a fileWriter to the text file according to the name
                        BufferedWriter bw = new BufferedWriter(fw);//creates a bufferedWriter oobject to write to the text file
                        PrintWriter out = new PrintWriter(bw))//creates another printWriter object to erite to the text file according to the student name
                    {//opens the trycatch
                        out.println(Password);//writes the added 'note' to the text file
 
                    } catch (IOException e) {
                        System.out.println("problem writting");//alerts the class user that there was an error writting to the text file
                    }
        }
        catch(Exception e){
            System.out.println(e);//alerts the class user that there was an error writting to the text file
        }
    }
    
    public String reading(String name) {//creates a method to read the contents of the text file according to the inputted name
        String entireFileText = "";//instantiates a string that will store the text file contents
        try {//opens the trycatch
            entireFileText = new Scanner(new File(name + ".txt")).useDelimiter("\\A").next();//creates a new scanner object to the scan the text file with the delimetre of '\A'
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddStudentNote.class.getName()).log(Level.SEVERE, null, ex);//a;erts the class user that there was an error scanning the contents of the text file
        }
         String decrypin = entireFileText;//creates a string varable to store the cyper text
            String decryptk;//creates a string variable to store the key for the cyper 
            decryptk = "thisisasecurekey";//sets the key for the cyper
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
            String dtext = decryptpass;//sets the password to the decrypted password
        return dtext;//returns the decrypted contents of the text file
    }//closes the reading method
    
    public boolean fileExists(String name) {//creates a method to check if a file exists according to the student name inputted
        boolean exists = true;//instantiates a boolean that will indicate whether the file exists or not
        String path = name + ".txt";//instantiates a string that holds the path of the text file according to the inputted name
        File file = new File(path);//instantiates a file object according to the path String
        if (!file.exists()) {//checks if the file exists
            exists = false;//flips exists to false
        }
        return exists;//return the boolean 'exists' indicating whether the file exists
    }//closes the method fileExists
    
    public boolean fileExists(String name, boolean image) {//creates a method to check if a file exists according to the student name inputted
        boolean exists = true;//instantiates a boolean that will indicate whether the file exists or not
        String path = name + ".png";//instantiates a string that holds the path of the text file according to the inputted name
        File file = new File(path);//instantiates a file object according to the path String
        if (!file.exists()) {//checks if the file exists
            exists = false;//flips exists to false
        }
        return exists;//return the boolean 'exists' indicating whether the file exists
    }//closes the method fileExists

}

                                           
   
