/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 *https://www.google.com/settings/security/lesssecureapps
 * and open the template in the editor.
 */
package patstake1;

//import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import java.net.PasswordAuthentication;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author YishaiBasserabie
 */
public class sendEmail {
    public static boolean sent = false;
    
    public void send(String to, String subject, String text) {
        
        try {
            fetchTeacher t  = new fetchTeacher();
            String host = "smtp.gmail.com";
            String user = t.getEmail();
            String pass = t.getPassword();
            String from = user;
            boolean sessionDebug = false;
            
            Properties prop = System.getProperties(); //to set different types of properties
            
            prop.put("mail.smtp.starttls.enable", "true");
            prop.put("mail.smtp.host", host);
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.required", "true");
            
            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            
            Session mailSession = Session.getDefaultInstance(prop, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress address = new InternetAddress(to);
            msg.setRecipient(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setText(text);
            
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            JOptionPane.showMessageDialog(null, "Email successfully sent!");
            System.out.println("email sent!");
        } catch (Exception e) {
            System.out.println("email not send ERROR!!!");
            JOptionPane.showMessageDialog(null, "Email not sent: please insure you are connect to the internet");
        }
        
    }
    
}
