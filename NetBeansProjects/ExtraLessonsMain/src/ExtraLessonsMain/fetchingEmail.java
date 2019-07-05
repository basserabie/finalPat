/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtraLessonsMain;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;
import javax.mail.search.FlagTerm;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.jsoup.Jsoup;
import static org.jsoup.nodes.Document.OutputSettings.Syntax.html;

public class fetchingEmail {//creates a class handling the fetching of emails
    
    private static ArrayList<String> emailsArray = new ArrayList<>();//creates an array list to contain the emails recieved

   public static void check(String host, String storeType, String user,
      String password) {//creates a method to check the emails recieved
      try {//opens the trycatch statement
      //create properties field
      Properties properties = new Properties();//instantiates a properties object

      properties.put("mail.pop3.host", host);//adds the host to the property
      properties.put("mail.pop3.port", "995");//adds the type of port to the property
      properties.put("mail.pop3.starttls.enable", "true");//enables the ttls protocall to be used
      Session emailSession = Session.getDefaultInstance(properties);//creates a new sessions object according to the property
  
      //create the POP3 store object and connect with the pop server
      Store store = emailSession.getStore("pop3s");//creates the POP3 store object
      store.connect(host, user, password);//connects with the pop server

      //create the folder object and open it
      Folder emailFolder = store.getFolder("INBOX");//creates a new folder object for the emails
      emailFolder.open(Folder.READ_WRITE);//opens the emailfolder folder and sets the permission to read and write

      //retrieve the messages from the folder in an array and print it
      Message[] messages = emailFolder.search(new FlagTerm(new Flags(Flag.SEEN), false));//creates an array of messages used to store the unread emails

      for (int i = 0, n = messages.length; i < n; i++) {//starts a for loop iterating through the messages array
         Message message = messages[i];//creates a new message object according to the iterated message
         
         String messageContent = "";//instantiates a string to store the message content of the iterated message
         if (message.getContent().toString().contains("Multipart")) {//checks if the message is a multipart message (i.e not plain text)
            Multipart multiPart = (Multipart) message.getContent();//creates a new multipart object to handl the multipart message
            int numberOfParts = multiPart.getCount();//creates an integer storing the number of parts in the multipart of the message
            for (int partCount = 0; partCount < numberOfParts; partCount++) {//starts a for loop iterating through the parts in the multipart of the iterated message
                MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);//creates a MimeBodyPart object casting the iterated part to a mimePart (Multipurpose Internet Mail Extensions) type
                messageContent = part.getContent().toString();//sets the message content to the string representation of the above gotten mimePart
            }
        } else /*if the message is not a multipart and just plain text*/if (message.getContent().toString().contains("text/plain")
            || message.getContent().toString().contains("text/html")) {//checks if the message is plain text or html formatted text
                Object content = message.getContent();//creates an object according to the content
                if (content != null) {//checks if the content is not null
                    messageContent = content.toString();//sets the messageContent to the string representation of the content
                }
            }
        if (!message.getFrom()[0].toString().contains("yourextralessons@gmail.com")) {//checks if the message is not sent from the program email address
            paymentsArray pa = new paymentsArray();//creates an object for the parentArray class
            String pName = message.getFrom()[0].toString().substring(0, message.getFrom()[0].toString().indexOf("<"));//creates a string holding the parent first name of the sender
            String pEmail = message.getFrom()[0].toString().substring(message.getFrom()[0].toString().indexOf("<")+1, message.getFrom()[0].toString().length()-1);//creates a string holding the oarent last name of the sender
            emailsArray.add(pName + "(" + pEmail + "):\n    " + messageContent);//adds the email to the emails array
        }
      }

      //close the store and folder objects
      emailFolder.close(false);//closes the emailfolder folder
      store.close();//closes the store object

      } catch (NoSuchProviderException e) {
         e.printStackTrace();//alerts the class user that the provider being used is invalid
      } catch (MessagingException e) {
         e.printStackTrace();//alerts the class user that the there was an error getting the message
      } catch (Exception e) {
         e.printStackTrace();//alerts the class user that there was a problem in the above process of getting the message and formatting it
      }
   }//closes the check method

   public static void doEmail() {//creates a method to get the emails
      String host = "smtp.gmail.com";//creates a string representing the host
      String mailStoreType = "pop3";//creates a string representing the server type being used (protocall)
      String username = "yourextralessons@gmail.com";//creates a string representing the email address
      String password = "Macbookpro1";//creates a string representing the password of the memail address

      check(host, mailStoreType, username, password);//gets the emails
    }//closes the doEmail method
    
//   public static void fetch(String pop3Host, String storeType, String user,
//      String password) {
//      try {
//         // create properties field
//         Properties properties = new Properties();
//         properties.put("mail.store.protocol", "pop3");
//         properties.put("mail.pop3.host", pop3Host);
//         properties.put("mail.pop3.port", "995");
//         properties.put("mail.pop3.starttls.enable", "true");
//         Session emailSession = Session.getDefaultInstance(properties);
//         // emailSession.setDebug(true);
//
//         // create the POP3 store object and connect with the pop server
//         Store store = emailSession.getStore("pop3s");
//
//         store.connect(pop3Host, user, password);
//
//         // create the folder object and open it
//         Folder emailFolder = store.getFolder("INBOX");
//         emailFolder.open(Folder.READ_WRITE);
//
//         BufferedReader reader = new BufferedReader(new InputStreamReader(
//	      System.in));
//
//         // retrieve the messages from the folder in an array and print it
//         Message[] messages = emailFolder.getMessages();
//         System.out.println("messages.length---" + messages.length);
//
//         for (int i = 0; i < messages.length; i++) {
//            Message message = messages[i];
//            System.out.println("---------------------------------");
//            writePart(message);
//            String line = reader.readLine();
//            if ("YES".equals(line)) {
//               message.writeTo(System.out);
//            } else if ("QUIT".equals(line)) {
//               break;
//            }
//         }
//
//         // close the store and folder objects
//         emailFolder.close(false);
//         store.close();
//
//      } catch (NoSuchProviderException e) {
//         e.printStackTrace();
//      } catch (MessagingException e) {
//         e.printStackTrace();
//      } catch (IOException e) {
//         e.printStackTrace();
//      } catch (Exception e) {
//         e.printStackTrace();
//      }
//   }

//   public static void writePart(Part p) throws Exception {//creates a method to format the write part of the passed in part of the message
//      if (p instanceof Message)//checks if the part passed in is a message
//         //Call methos writeEnvelope
//         writeEnvelope((Message) p);//casts p to a message and writes it to an envelope to be formatted here
//
//      System.out.println("----------------------------");
//      System.out.println("CONTENT-TYPE: " + p.getContentType());
//
//      //check if the content is plain text
//      if (p.isMimeType("text/plain")) {
//         System.out.println("This is plain text");
//         System.out.println("---------------------------");
//         System.out.println((String) p.getContent());
//      } 
//      //check if the content has attachment
//      else if (p.isMimeType("multipart/*")) {
//         System.out.println("This is a Multipart");
//         System.out.println("---------------------------");
//         Multipart mp = (Multipart) p.getContent();
//         int count = mp.getCount();
//         for (int i = 0; i < count; i++)
//            writePart(mp.getBodyPart(i));
//      } 
//      //check if the content is a nested message
//      else if (p.isMimeType("message/rfc822")) {
//         System.out.println("This is a Nested Message");
//         System.out.println("---------------------------");
//         writePart((Part) p.getContent());
//      } 
//      //check if the content is an inline image
//      else if (p.isMimeType("image/jpeg")) {
//         System.out.println("--------> image/jpeg");
//         Object o = p.getContent();
//
//         InputStream x = (InputStream) o;
//         // Construct the required byte array
//         System.out.println("x.length = " + x.available());
//         int i = 0;
//         byte[] bArray = new byte[x.available()];;
//         while ((i = (int) ((InputStream) x).available()) > 0) {
//            int result = (int) (((InputStream) x).read(bArray));
//            if (result == -1)
//         i = 0;
//            break;
//         }
//         FileOutputStream f2 = new FileOutputStream("/tmp/image.jpg");
//         f2.write(bArray);
//      } 
//      else if (p.getContentType().contains("image/")) {
//         System.out.println("content type" + p.getContentType());
//         File f = new File("image" + new Date().getTime() + ".jpg");
//         DataOutputStream output = new DataOutputStream(
//            new BufferedOutputStream(new FileOutputStream(f)));
//            com.sun.mail.util.BASE64DecoderStream test = 
//                 (com.sun.mail.util.BASE64DecoderStream) p
//                  .getContent();
//         byte[] buffer = new byte[1024];
//         int bytesRead;
//         while ((bytesRead = test.read(buffer)) != -1) {
//            output.write(buffer, 0, bytesRead);
//         }
//      } 
//      else {
//         Object o = p.getContent();
//         if (o instanceof String) {
//            System.out.println("This is a string");
//            System.out.println("---------------------------");
//            System.out.println((String) o);
//         } 
//         else if (o instanceof InputStream) {
//            System.out.println("This is just an input stream");
//            System.out.println("---------------------------");
//            InputStream is = (InputStream) o;
//            is = (InputStream) o;
//            int c;
//            while ((c = is.read()) != -1)
//               System.out.write(c);
//         } 
//         else {
//            System.out.println("This is an unknown type");
//            System.out.println("---------------------------");
//            System.out.println(o.toString());
//         }
//      }
//
//   }
//   /*
//   * This method would print FROM,TO and SUBJECT of the message
//   */
//   public static void writeEnvelope(Message m) throws Exception {
//      System.out.println("This is the message envelope");
//      System.out.println("---------------------------");
//      Address[] a;
//
//      // FROM
//      if ((a = m.getFrom()) != null) {
//         for (int j = 0; j < a.length; j++)
//         System.out.println("FROM: " + a[j].toString());
//      }
//
//      // TO
//      if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
//         for (int j = 0; j < a.length; j++)
//         System.out.println("TO: " + a[j].toString());
//      }
//
//      // SUBJECT
//      if (m.getSubject() != null)
//         System.out.println("SUBJECT: " + m.getSubject());
//
//   }
   
   public String formatRequest(String raw1) {//creates a method to format the incoming string representation of the message
       paymentsArray pa = new paymentsArray();//creates an object for the paymentsArray class
       String rawT = pa.formattOutHTMLTags(raw1);//removes the HTML headers of the incoming string
       String from = rawT.substring(0, rawT.indexOf(":"));//creates a string holding the sender data
       String raw = rawT.substring(rawT.indexOf(":"));//creates a string holding the message content
        String req = "";//creates a string to hold the formatted message content
        int count = 0;//creates a count variable to count the characters iterated from the begining and from a line break
        for (int i = 0; i < raw.length(); i++) {//starts a for loop interating through the characters of the raw string (unformatted message)
            String c = ""+raw.charAt(i);//creates a string representation of the iterated character
            if (count >= 50 && c.equals(" ")) {//checks if there has been more than 50 iterations (more than 50 characters) and if the iterated character is a space
                req += "\n    ";//adds a line break and a teb to the req string
                count = 0;//sets count to 0
            } else {//if there has not been 50 characters iterated or the iterated character is not a space
                req += c;//adds the iterated character to req
                count++;//ups the character count
            }
        }
        return from + "\n" + req;//returns the formatted message (sender information and then the message)
    }//closes the formatRequests method
   
//   public String toPrettyString(String raw) {
//       paymentsArray pa = new paymentsArray();
//       String temp1 = raw.replaceAll("<br>", "mustbreaklinehere");
//       String temp2 = pa.formattOutHTMLTags(temp1);
//       String finalReq = temp2.replaceAll("mustbreaklinehere", "\n");
////       String temp2 = temp1.replaceAll("<div>", "");
////       String temp3 = temp2.replace("<div dir=\"ltr\">", "");
////       String finalReq = temp3.replaceAll("<\\div>", "");
//       return finalReq;
//   }
  

    @Override
    public String toString() {//creates the toString method of this class displaying all of the messages
        chuckNoris cn = new chuckNoris();//creates an object for the chuckNoris class
        mothersArray pa = new mothersArray();//creates an object for the mothersArray class
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        String noemails = "You have no emails at this time\n(Please ensure you are connected to the internet and try again)";//creates a string to alert the user of the absence of emails
        String temp = "INCOMING Emails:\n\n";//creates a string to be returned containing a display of the messages
        if (emailsArray.size() == 0 || emailsArray.get(0).contains(noemails)) {//checks if there are no emails recieved
            emailsArray.add(noemails);//adds the noemails string to the emails array
            temp += emailsArray.get(0);//sets the temp string to the first item in the emailsArray
        } else {//if there are emails
            for (int i = 0; i < this.emailsArray.size(); i++) {//starts a for loop iterating through the emailsArray
                temp += "EMAIL " + (i+1) + "\n" + this.formatRequest(this.emailsArray.get(i)) + "\n\n";//adds the formatted version of the iterated email to temp
            }
        }
        return temp;//returns temp
    }//closes the toString method
   
}//closes the fetchingEmail class