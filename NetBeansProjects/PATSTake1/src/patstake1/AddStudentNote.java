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

    public void createNote(String name) {
        try{
            // Create new file
            String path = name + ".txt";
            File file = new File(path);
            file.createNewFile();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void deleteNote(String name) {
        //file name only
        File file = new File(name + ".txt");
        if(file.delete()){
            System.out.println("file.txt File deleted from Project root directory");
        }else System.out.println("File file.txt doesn't exist in the project root directory");
    }
    
    public void writing(String name, String text) {
        
         EncryptDecrypt encDec = new EncryptDecrypt();//creates a EncryptDecrypt object called encDec
            String Password = "";//creates a string variale called Password
        
            String passimp = text;// sets the the variable that will be encryped 
            passimp = EncryptDecrypt.encrypt(passimp);//settting variable passimp with my encryption method returned String.

            String encryptk = "thisisasecurekey";//sets the encryption key
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
        
        try{
                    PrintWriter pw = new PrintWriter(name + ".txt");
                    pw.close();
                    try(FileWriter fw = new FileWriter(name + ".txt", true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter out = new PrintWriter(bw))
                    {
                        out.println(etext);
                        //more code
                    } catch (IOException e) {
                        //exception handling left as an exercise for the reader
                    }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public String reading(String name) {
        String entireFileText = "";
        try {
            entireFileText = new Scanner(new File(name + ".txt")).useDelimiter("\\A").next();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddStudentNote.class.getName()).log(Level.SEVERE, null, ex);
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
        System.out.println(dtext);
        return dtext;
    }
    
    public boolean fileExists(String name) {
        boolean exists = true;
        // Create new file
        String path = name + ".txt";
        File file = new File(path);
        // If file doesn't exists, then create it
        if (!file.exists()) {
            exists = false;
        }
        return exists;
    }

}

                                           
   
