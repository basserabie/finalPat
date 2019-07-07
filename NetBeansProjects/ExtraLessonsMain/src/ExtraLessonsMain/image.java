/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtraLessonsMain;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import sun.tools.jar.CommandLine;

/**
 *
 * @author YishaiBasserabie
 */
public class image {//creates a class handling the facial recognition
    
    /*The HTTPEntityWrapper was needed to get he definite size of the HTTP entity fetched from the API NOTE: will not need this class if using maven libraries for some fucking reason*/
    public static class HttpEntityWrapper implements HttpEntity {//creates a class to wrapp the HTTPEntity to get the definite size
        private byte[] stuff;//creates a byte array to store the entity
        private final Header contentType;//creates a header to store the content type of the entity
        private final Header contentEncoding;//creates a hear to store the contentEncodings of the entity
        
        public HttpEntityWrapper(HttpEntity entity) {//creates the constructor for the HTTPEntityWrapper class
            contentType = entity.getContentType();//assignes the content type of the object to the content type of the entity passed in
            contentEncoding = entity.getContentEncoding();//assignes the content encodings of the object to the content encodings of the entity passed in
            try {//opens the trycatch statement
                ByteArrayOutputStream output = new ByteArrayOutputStream();//instantiates a byteArrayOutpuStream for the entity
                entity.writeTo(output);//writes the entity to the  ByteArrayOutputStream
                stuff = output.toByteArray();//assignes the byte array of the object to the output byte array
            } catch (IOException ex) {//opens the catch statement
                Logger.getLogger(image.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error writing the entity to the outputStream
            }//closes the catch statement
        }//closes the constructor
        
        /*Implements all abstract methods*/
        
        @Override
        public boolean isRepeatable() {//creates a method to check if the entity is repeatable
            return true;//returns true
        }//closes the isRepeatable method

        @Override
        public boolean isChunked() {//creates a method to check if the entity is chunked (each packet sent is recieved independantly of each other)
            return false;//returns false
        }//closes the isChunked method

        @Override
        public long getContentLength() {//creates a method to return the contentLength of the entity
            return stuff.length;//returns the content lenght i.e the lenght of the byte array
        }//closes the getContentLenght method

        @Override
        public Header getContentType() {//creates a method to return the contentType of the entity
            return contentType;//returns the content type
        }//closes the getContentType method

        @Override
        public Header getContentEncoding() {//creates a method to return the content encoding of the entity
            return contentEncoding;//returns the content encodings
        }//closes the getContentEncoding method

        @Override
        public InputStream getContent() throws IOException, UnsupportedOperationException {//creates a method to return the content as an inputStream
            return new ByteArrayInputStream(stuff);//returns the new inputStream object according to the stuff byteArray
        }//closes the getContentMethod

        @Override
        public void writeTo(OutputStream out) throws IOException {//creates a method to write the content byteArray to the passed in outputStream
            out.write(stuff);//writes stuff to the passed in outputStream
        }//closes the writeTo method

        @Override
        public boolean isStreaming() {//creates a method to return whether the entity isStreaming (causing unessacary buffering on the server side)
            return false;//returns false
        }//closes the isStreaming method

        @Override
        public void consumeContent() throws IOException {//creates a method to consume the entity i.e. close it
            throw new UnsupportedOperationException("Not supported yet."); //throws unsoported opration
        }//coses the consumeContent method
    }//closes the HTTPEntityWrapper class

    //public static String json = "";//creates a string to hold the
    public static String path = "";//creates a static string to hold the path to the image to be validated
    public static String IDPath = "";//creates a static string to hold the path to the text file holding the user ID for the API
    public static String addImageURL = "";//creates a static string to hold the url used to add an image to the user profile in the API
    public static String addUserURL = "https://api.chooch.ai/predict/face?person_name=Generic Name&model_id=119&apikey=4df35e9e-77d1-43b8-b5a0-b3588c017ee5&command=create_person";//creates a static holding the url used to create a user profile in  the API
    public static String validateURL = "";//creates a static string to hold the url used to validate a ogin attempt
    
    public void createIDFileAndSetPath(String id) {//creates a method to create the file for the ID of the user profile from the API
        AddStudentNote add = new AddStudentNote();//creates an object for the addStudentNote class
        add.createNote("faceID");//creates a text file to store the ID
        add.writing("faceID", id);//writes the id passed in to the text file
        File file = new File("faceID" + ".txt");//creates a new file object sccordning to the text file created
        IDPath = file.getAbsolutePath();//sets the IDPath string to the absolute path of the text file
    }//closes the createIDFileAndSetPath method
    
    public void deleteIDFile() {//creates a method to delete the id text file
        AddStudentNote add = new AddStudentNote();//creates an object for the addStudentNote class
        add.deleteNote("faceID");//deletes the text file
    }//closes the deleteIDFile method
    
    public static String readFaceID() {//creates a method to fetch the user profile id stored in the text file
        AddStudentNote add = new AddStudentNote();//creates an object for the AddStudentNote class
        return add.reading("faceID");//returns the text file contents
    }//closes the readFaceID method
            
    public void deleteImage() {//creates a method to delete an image taken
        //file name only
        File file = new File("face" + ".jpg");//creates a file object according for the image
        if(file.delete()){//deletes the file and checks if successful
            System.out.println("file.txt File deleted from Project root directory");//alerts the class user that the file was deleted
        }else {//if the file was not deleted (error)
            System.out.println("File file.txt doesn't exist in the project root directory");//alerts the class user that the file does not exist
        }
    }//closes the deleteImage method
    
    public void take() throws IOException {//creates a method to take and store an image
        Webcam webcam = Webcam.getDefault();//instatiates a webcam object
        webcam.setViewSize(WebcamResolution.VGA.getSize());//sets the webcam image size to the biggest size
        webcam.open();//opens the webcam
        File file = new File("face.jpg");//creates a new file object for the image
        path = file.getAbsolutePath();//sets the path to the absolute path of the image
        ImageIO.write(webcam.getImage(), "jpg", file);//writes the image to the face image file
        webcam.close();//closes the webcam
    }//closes the take method
    
    public String imagePost(String localFilePath) throws FileNotFoundException, IOException {//creates a method to post an image to user profile
       CloseableHttpClient httpClient = HttpClients.createDefault();//creaters a closableHTTPCleint object
       HttpPost uploadFile = new HttpPost(addImageURL);//creates a new HttpPost object and adds the the addImageURL to it
       MultipartEntityBuilder builder = MultipartEntityBuilder.create();//creates a new multipartentitybuilder object

       // This attaches the file to the POST:
       File f = new File(localFilePath);//creates a new file object for the image file
       builder.addBinaryBody(//adds a binary body of the image to the multipart builer
           "image",//sets the multipart part to an image
           new FileInputStream(f),//creates a new fileInputStream of the image
           ContentType.APPLICATION_OCTET_STREAM,//sets the content type of the part to octet stream (image)
           f.getName()//sets the name of the part to the name of the image file 
       );//closes the addBinaryBody function
       
       HttpEntity multipart = builder.build();//creates an HTTPEntity for the post
       HttpEntity sizedEntity = new HttpEntityWrapper(multipart);//creates a sized representation of the multipart HTTPEntity using the HTTPEntityWrapper class
       uploadFile.setEntity(sizedEntity);//uploads the sizedEntity to the upload file
       CloseableHttpResponse response = httpClient.execute(uploadFile);//excecutes the http request and stores the response in a closable httpresponse
       HttpEntity responseEntity = response.getEntity();//creates an httpEntity as the response entity
       String responseStr = EntityUtils.toString(responseEntity);//creates a string representation of the reponse
       return responseStr;//returns the response
    }//closes the imagePost method
    
    public boolean validateFace() throws FileNotFoundException, IOException {//creates a method to validate the face of an attempted login
       boolean faceRecognised = false;//creates a boolean to indicate whether the attempted face is valid
       validateURL = "https://api.chooch.ai/predict/face?person_id_filter=" + image.readFaceID() + "&model_id=119&apikey=4df35e9e-77d1-43b8-b5a0-b3588c017ee5";//formats the validateURL according to the user profile id stored in the created text file
       JOptionPane.showMessageDialog(null, "Please look pretty for the camera while we validate your face.\nPlease click ok and then wait a few secounds");//instructs the user to wait a few seconds and look into the webcam camera
       this.take();//takes a picture
       Sound.playcam();//plays the camera snapshot sound
       CloseableHttpClient httpClient = HttpClients.createDefault();//creates a default closeableHTTPCleint object
       HttpPost uploadFile = new HttpPost(validateURL);//creates an httpPost object according to the validateURL string
       MultipartEntityBuilder builder = MultipartEntityBuilder.create();//creates a MultipartEntityBuilder for the image to be validated

       // This attaches the file to the POST:
       File f = new File(path);//creates a file object according to the path string (to the image taken to be validated)
       builder.addBinaryBody(//adds a binary body of the image to the multipart builer
           "image",//sets the multipart part to an image
           new FileInputStream(f),//creates a new fileInputStream of the image
           ContentType.APPLICATION_OCTET_STREAM,//sets the content type of the part to octet stream (image)
           f.getName()//sets the name of the part to the name of the image file  
       );//closes the addBinaryBody function
       
       HttpEntity multipart = builder.build();//creates an HTTPEntity for the post
       HttpEntity sizedEntity = new HttpEntityWrapper(multipart);//creates a sized representation of the multipart HTTPEntity using the HTTPEntityWrapper class
       uploadFile.setEntity(sizedEntity);//uploads the sizedEntity to the upload file
       CloseableHttpResponse response = httpClient.execute(uploadFile);//excecutes the http request and stores the response in a closable httpresponse
       HttpEntity responseEntity = response.getEntity();//creates an httpEntity as the response entity
       String responseStr = EntityUtils.toString(responseEntity);//creates a string representation of the reponse
       if (this.formattValidation(responseStr).equals("true")) {//checks if the farmatted version of the respons is 'true' i.e the attempted login face is valid
           this.pushValidatedFace();//adds the face to the database of the user profile in the API
           faceRecognised = true;//sets the faceRegognised boolean to true
       }
       return faceRecognised;//returns faceRecognised
    }//closes the validateFace method
    
    public String formattAddUserToGetID(String raw) {//creates a method to format the httpresponse containing the new user profile id from the API
        String id = raw.substring(raw.lastIndexOf(":")+1, raw.lastIndexOf(":")+5);//formats the response to extract the id
        return id;//returns the id
    }//closes the formattAddUserToGetID method
    
    public void addUser() {//creates a method to add the user to the API database
        String id = "";//creates a string to hold the id of the user profile from the database
        String replacedURL = addUserURL.replace(" ", "%20");//replaces spaces in the addUserURL wil %20 to be handled by the browser
        try {//starts a trycatch stamement
            URL obj = new URL(replacedURL);//creates a URL object representation of the replacedURL
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();//creates an httpURLConnection object
            con.addRequestProperty("User-Agent", "Chrome"); //adds a request property to the connection
            int responseCode = con.getResponseCode();//creates an integer to store the response code of the connection response
            BufferedReader in = new BufferedReader(//creates a new buffer reader object reading the connection response
            new InputStreamReader(con.getInputStream()));//creates a new inputStream reader for the inputStream of the connection response
            String inputLine;//instantiates a string to hold the inputline of the response
            StringBuffer response = new StringBuffer();//creates a stringBuffer of the response
            while ((inputLine = in.readLine()) != null) {//starts a while loop iterating through the lines in the response
                response.append(inputLine);//adds the iterated line to the response
            } in .close();//closes the buffer reader
            JSONObject myresponse = new JSONObject(response.toString());//creates a JSON object representation of the response
            id = this.formattAddUserToGetID(myresponse.toString());//sets the id string to the formatted representation of the response(the id)
            } catch(Exception e) {//opens the catch statement
                System.out.println(e);//alerts the class user that there was an error getting the response and id
            }//closes the catch statement
        this.createIDFileAndSetPath(id);//creates a text file storing the gotten id
        }//closes the addUser method
    
    public String formattValidation(String raw) {//creates a method to formatt the validation response (accertain whether the face is valid or not)
        String hit = raw.substring(raw.indexOf("face_recog_hit\":") + 17, raw.indexOf("face_recog_hit\":") + 21);//creates a string to determine whether the face detected is valid
        return hit;//returns the hit string
    }//closes the formattValidation repsonse
    
    
    
    public void pushValidatedFace() {//creates a method to add the pvalidated picture to the database of the user profile in the API
        addImageURL = "https://api.chooch.ai/predict/face?person_id_filter=" + image.readFaceID() + "&apikey=4df35e9e-77d1-43b8-b5a0-b3588c017ee5&command=insert_person_image";//formats the addImageURL acording to the user profile id read from the text file
        try {//opens the trycatch statement
            this.imagePost(path);//posts the image according to the path of the validated image
        } catch (IOException ex) {//opens the catch statement
            Logger.getLogger(image.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user there was an error pushing the face to the API
        }//closes the catch
    }//closes the pushValidateedFace method
    
    public void pushFace() throws IOException {//creates a method to add the user protraits to the database profile at sign up
        this.addUser();//creates a profile in the APi for the current user
        addImageURL = "https://api.chooch.ai/predict/face?person_id_filter=" + image.readFaceID() + "&apikey=4df35e9e-77d1-43b8-b5a0-b3588c017ee5&command=insert_person_image";//formats the addImageURL according to the profile id in the API gotten from the text file created
        for (int i = 0; i < 5; i++) {//starts a for loop from 0 to 4
            JOptionPane.showMessageDialog(null, "PICTURE " + (i+1) + "\nPlease click the 'take' button and then look into the camera (you will do this " + (5-i) + " more times).\n**Please move your head around between snapshots");//instructs the user to look into the webcam camera and do it 5 times
            this.take();//takes a snapshot from the webcam
            Sound.playcam();//plays the snapshot camera sound
            this.imagePost(path);//posts the image taken to the user profile in the API
            this.deleteImage();//deletes the image taken
        }
        JOptionPane.showMessageDialog(null, "Shots Taken (looking great!)");//alerts the user that their profile has successfully been created (and of course that they are looking great)
    }//closes the pushface method
    
    public void addAnotherSnapshot() {//creates method to add an image to the user profile in the API
        fetchTeacher ft = new fetchTeacher();//creates an object for the fetchTeacher class
        loginSignUpHandler h = new loginSignUpHandler();//creates an object for the loginSignUpHandler class
        String pass = h.encryptPassword(JOptionPane.showInputDialog("Please enter your password"), ft.getAnswer());//creates a string holding the encrypted password entered
        if (pass.equals(ft.getPassword())) {//checks if the password enetered is correct
             try {//opens the trycatch statement
                addImageURL = "https://api.chooch.ai/predict/face?person_id_filter=" + image.readFaceID() + "&apikey=4df35e9e-77d1-43b8-b5a0-b3588c017ee5&command=insert_person_image";//formats the addImageURL according to the profile id in the API gotten from the text file created
                JOptionPane.showMessageDialog(null, "Please click the 'take' button and then look into the camera ");//instructs the user to look into the webcam camera and do it 5 times
                this.take();//takes a snapshot from the webcam
                Sound.playcam();//plays the snapshot camera sound
                this.imagePost(path);//posts the image taken to the user profile in the API
                this.deleteImage();//deletes the image taken
            } catch (IOException ex) {//opens the catch stamement
                Logger.getLogger(image.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error pushing the face
            }//closes the catch statement
        } else {//if the password enetered is incorrect
            JOptionPane.showMessageDialog(null, "Password incorrect");//alerts the user that the password enetered is incorrect
        }
    }//closes the addAnotherSnapshot method
    
}//closes the image class
