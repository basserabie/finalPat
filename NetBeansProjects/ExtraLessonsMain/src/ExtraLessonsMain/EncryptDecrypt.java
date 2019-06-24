/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtraLessonsMain;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author YishaiBasserabie
 */
public class EncryptDecrypt {//creates a class called EncryptDecrypt to handle encryption and decryption of files and text
    
      public static String encrypt(String e) {// creates a methood that returns a incrypted password
        int arr[][] = new int[256][256];//creates an interger 2D array [row][column] 
        for (int loop = 0; loop <= 255; loop++) {//row loop
            for (int k = 0; k <= 255; k++) {//column loop
                if (k + loop < 256) {//if it shifts "outside" the ascii table ie more than 256...

                    arr[loop][k] = k + loop;//setting the 2D array at position ([loop]; [k])
                } else {
                    arr[loop][k] = k + loop - 256;   //-256
                }

            }
        }
        String key = key(e.length());//sets key to method returned string
        String cipher = "";//creates a String varable caled ciper
        for (int loop = 0; loop < e.length(); loop++) {//loops though the parsed in string one char at a time
            int indchar = (int) e.charAt(loop);//converts the char at the possition in the loop into its ascii value
            int indkey = (int) key.charAt(loop);//converts the char at the possition in the loop into its ascii value to make it a key
            for (int r = 0; r <= 255; r++) {//loops through all the ascii values
                if (arr[r][indchar] == indkey) {//if the intger at the posistion got by the loop equals the key
                    cipher = cipher + (char) r;// then the ciper is set to the value of the chiper in a char format

                }

            }
        }
        return cipher;//returns the char
    }//closes the encrypt method

    public static String key(int l) {//the parameter takes in the length of the password
        String s = "";//creates a String varable called s
        for (int loop = 0; loop <= 256-l; loop++) {//loop through ASCII table 
            s = s + (char) (256 - l - loop);//shifting the letter of the ASCII by the number of letters in the string to make my key. This is done by starting at the end of the ASCII table
        }
        return s; //returns the string
    }//closes the key method


    static String decrypt(String e) {//(in brackets shows what you are sending into the method and its datatype
        int arr[][] = new int[256][256];//creates an interger 2D array [row][column] 
        for (int loop = 0; loop <= 255; loop++) {//row loop
            for (int k = 0; k <= 255; k++) {//column loop
                if (k + loop < 256) {

                    arr[loop][k] = k + loop;//if it shifts "outside" the ascii table ie more than 256...
                } else {
                    arr[loop][k] = k + loop - 256;//setting the 2D array at position ([loop]; [k])
                }

            }
        }
        String key = key(e.length());//creates a varable called key that stores the lenght of the pases in String
        String pass = "";//creates a String varable called pass
        for (int loop = 0; loop < e.length(); loop++) {//loops though the parsed in string one char at a time
            int indchar = (int) e.charAt(loop);//converts the char at the possition in the loop into its ascii value
            int indkey = (int) key.charAt(loop);//converts the char at the possition in the loop into its ascii value
            for (int r = 0; r <= 255; r++) {//loops through all the ascii values
                if (arr[indchar][r] == indkey) {//if the intger at the posistion got by the loop equals the key
                    pass = pass + (char) r;//if the intger at the posistion got by the loop equals the key
                }
            }
        }
        return pass;//returns the decryped password
    }//closes the decrypt method
  
}//closes the EncryptDecrypt class