/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtraLessonsMain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 *
 * @author YishaiBasserabie
 */
public class chuckNoris {//creates a class to handle the chuck norris facts
    
    public void getFact() {//creates a method to get the facts from the API
        try {//opens the trycatch statement
            String url = "https://api.chucknorris.io/jokes/random";//creates a string holding the url of the API
            URL obj = new URL(url);//creates a new URL object for the url String
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();//creates a new http connection for the url object
            con.addRequestProperty("User-Agent", "Chrome");//adds a request property to the connection
            BufferedReader in =new BufferedReader(
            new InputStreamReader(con.getInputStream()));//creates a buffer reader to the inputStream of the connection
            String inputLine;//instantiates a string to hold the read line gotten
            StringBuffer response = new StringBuffer();//creates a StringBuffer to handle the inputLine
            while ((inputLine = in.readLine()) != null) {//starts a while loop running while there is a next line of the fact
                response.append(inputLine);//appends the iterated line to the response stringBuffer
            } in .close();//closes the bufferReader
            JSONObject myresponse = new JSONObject(response.toString());//creates a new JSON object according to the response text
            String endfact = this.formatFact(myresponse.toString());//creates a string holding the formatted fact
            Sound.playcoin();//plays the coin sound
            JOptionPane.showMessageDialog(null, "CHUCK NORRIS FACT:\n" + endfact);//displays the chuck norris fact to the reader
            } catch(Exception e) {//opens the catch statement
                System.out.println(e + "   problem");//alerts the class user that there was a problem getting the fact
            }//closes the catch statement
        }//closes the getFact method
    
    public String formatFact(String raw) {//creates a method to format the String version of the JSON object representation of the fact in: unformatted raw String representation of the gotten JSON
        String factLong = raw.substring(raw.indexOf("value")+8, raw.indexOf(",\"url\":\"https://")-1);//creates a string holding on the fatc text
        String fact = "";//creates a string to hold the formatted fact
        boolean lineBroken = false;//creates a boolean to check if there has been a line break
        for (int i = 0; i < factLong.length(); i++) {//starts a for loop iterating through each character in the factLong String
            String c = ""+factLong.charAt(i);//creates a string according to the iterated character
            if (i >= 80 & c.equals(" ") && !lineBroken) {//checks if the character count is above or equal to 80 and the line has not already been broken
                fact += "\n";//adds a line break to the fact string
                lineBroken = true;//flips the lineBroken boolean to true indicating that there has been a line break
            } else {//if the character count is less than 80 or the line has already been broken
                fact += c;//adds the string c to the string fact
            }
        }
        return fact;//returns the fact string
    }//closes the formatFact method
    
 }//closes the chuckNoris class
