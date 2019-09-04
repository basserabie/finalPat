/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 *https://www.google.com/settings/security/lesssecureapps
 * and open the template in the editor.
 */
package ExtraLessonsMain;

//import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author YishaiBasserabie
 */
public class sendEmail {//creates a class for handling the emails
    public static boolean sent = false;//creates a boolean indicating whether the email was sent

    // in: reciever address, subject string, email body
    public void send(String to, String subject, String text) {//creates a method to send an email to the reciever address from the sender address according to the text passed in
                try {
            String host = "smtp.gmail.com";//creates a string for the host
            String user = "yourextralessons@gmail.com";//creates a string for the email address
            String pass = "Macbookpro1";//creates a string for the password
            String from = user;//creates a string for the from email address
            boolean sessionDebug = false;//creates a boolean for the session debug boolean indicating that the session is not being debugged
            
            Properties prop = System.getProperties(); //to set different types of properties
            
            prop.put("mail.smtp.starttls.enable", "true");//enable the ttls protocol
            prop.put("mail.smtp.host", host);//adds the host to the property
            prop.put("mail.smtp.port", "587");//adds the port to the property
            prop.put("mail.smtp.auth", "true");//adds the authorisation enabled setting to the property
            prop.put("mail.smtp.starttls.required", "true");//adds the ttls required setting to the property
            
            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());//adds a security provider
            
         // This mail has 2 part, the BODY and the embedded image
         MimeMultipart multipart = new MimeMultipart("related");//creates the mimeMultiPart Object
         // first part (the html)
         BodyPart messageBodyPart = new MimeBodyPart();//creates the body part of the mimeMultiPart
         String htmlText = text + "<img src=\"cid:image\">";//formatts the text to html text to add image
         messageBodyPart.setContent(htmlText, "text/html");//sets the type of the multipart body to html
         // add it
         multipart.addBodyPart(messageBodyPart);//adds the message part to the multipart

         // second part (the image)
         messageBodyPart = new MimeBodyPart();//creates a new mimebodyPart for the message part
         DataSource fds = new FileDataSource(//finds the image (signiture) file
            "signiture.png");

         messageBodyPart.setDataHandler(new DataHandler(fds));//sets the data handler to the image datasource
         messageBodyPart.setHeader("Content-ID", "<image>");//sets the header of the part to image header

         // add image to the multipart
         multipart.addBodyPart(messageBodyPart);//adds the message body part to the multipart
            
            Session mailSession = Session.getDefaultInstance(prop, null);//instantiates a mail session according to the properties specified above
            mailSession.setDebug(sessionDebug);//sets the debug setting to false
            Message msg = new MimeMessage(mailSession);//creates a new message according to the multipart
            msg.setFrom(new InternetAddress(from));//adds a sent from field to the message
            InternetAddress address = new InternetAddress(to);//creates an internet address object for the reciever email address
            msg.setRecipient(Message.RecipientType.TO, address);//sets the reciever of the message to the address internet address
            msg.setSubject(subject);//sets the subject of the message
            msg.setSentDate(new Date());//sets the date of the message
            msg.setContent(multipart);//sets the content of the message to the multipart
            
            Transport transport = mailSession.getTransport("smtp");//creates a transport object for the mail session
            transport.connect(host, user, pass);//connects the mailsession cia the transport object
            transport.sendMessage(msg, msg.getAllRecipients());//sends the message
            transport.close();//closes the transport object
            JOptionPane.showMessageDialog(null, "Successfull");//alerts the user that the email was sent successfully
           
        } catch (Exception e) {
            System.out.println("email not send ERROR!!!");//alerts the class user of the error in sending the email
            JOptionPane.showMessageDialog(null, "Email not sent: please insure you are connect to the internet");//alerts the user that there was an error sending the email
        }
    }//closes the send method
    
    public void configure() {//creates a method to condigure the networking aspects of the program and to check if there is internet connection
        try {
            String host = "smtp.gmail.com";//creates a string for the host
            String user = "extralessoninternet@gmail.com";//creates a string for the email address
            String pass = "Macbookpro1";//creates a string for the password
            String from = user;//creates a string for the from email address
            boolean sessionDebug = false;//creates a boolean for the session debug boolean indicating that the session is not being debugged
            
            Properties prop = System.getProperties(); //to set different types of properties
            
            prop.put("mail.smtp.starttls.enable", "true");//enable the ttls protocol
            prop.put("mail.smtp.host", host);//adds the host to the property
            prop.put("mail.smtp.port", "587");//adds the port to the property
            prop.put("mail.smtp.auth", "true");//adds the authorisation enabled setting to the property
            prop.put("mail.smtp.starttls.required", "true");//adds the ttls required setting to the property
            
            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());//adds a security provider
            
            Session mailSession = Session.getDefaultInstance(prop, null);//instantiates a mail session according to the properties specified above
            mailSession.setDebug(sessionDebug);//sets the debug setting to false
            Message msg = new MimeMessage(mailSession);//creates a new message according to the multipart
            msg.setFrom(new InternetAddress(from));//adds a sent from field to the message
            InternetAddress address = new InternetAddress("extralessoninternet@gmail.com");//creates an internet address object for the reciever email address
            msg.setRecipient(Message.RecipientType.TO, address);//sets the reciever of the message to the address internet address
            msg.setSubject("internet establishment");//sets the subject of the message
            msg.setSentDate(new Date());//sets the date of the message
            msg.setText("established");//sets the content of the message to the multipart
            
            Transport transport = mailSession.getTransport("smtp");//creates a transport object for the mail session
            transport.connect(host, user, pass);//connects the mailsession cia the transport object
            transport.sendMessage(msg, msg.getAllRecipients());//sends the message
            transport.close();//closes the transport object
            System.out.println("configuration complete");//alerts the class user that the configuration is complete
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please Connect to the internet first");//alerts the user to first connect to the internet
            System.exit(0);//closes the program
        }
    }//closes the configure method
    
}//closes the sendEmail class
