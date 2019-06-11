/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patstake1;

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
public class chuckNoris {
    
    public void getFact() {
        try {
            String url = "https://api.chucknorris.io/jokes/random";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.addRequestProperty("User-Agent", "Chrome"); 
            int responseCode = con.getResponseCode();
            BufferedReader in =new BufferedReader(
            new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            } in .close();
            JSONObject myresponse = new JSONObject(response.toString());
            System.out.println(myresponse);
            String endfact = this.formatFact(myresponse.toString());
            JOptionPane.showMessageDialog(null, "CHUCK NORIS FACT:\n" + endfact);
            } catch(Exception e) {
                System.out.println(e + "   problem");
            }
        }
    
    public String formatFact(String raw) {
        String fact = raw.substring(raw.indexOf("value")+8, raw.indexOf(",\"url\":\"https://")-1);
        return fact;
    }
    }
