/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patstake1;

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
public class image {
    
    public static class HttpEntityWrapper implements HttpEntity {
        private byte[] stuff;
        private final Header contentType;
        private final Header contentEncoding;
        
        public HttpEntityWrapper(HttpEntity entity) {
            contentType = entity.getContentType();
            contentEncoding = entity.getContentEncoding();
            try {
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                entity.writeTo(output);
                stuff = output.toByteArray();
            } catch (IOException ex) {
                Logger.getLogger(image.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        @Override
        public boolean isRepeatable() {
            return true;
        }

        @Override
        public boolean isChunked() {
            return false;
        }

        @Override
        public long getContentLength() {
            return stuff.length;
        }

        @Override
        public Header getContentType() {
            return contentType;
        }

        @Override
        public Header getContentEncoding() {
            return contentEncoding;
        }

        @Override
        public InputStream getContent() throws IOException, UnsupportedOperationException {
            return new ByteArrayInputStream(stuff);
        }

        @Override
        public void writeTo(OutputStream out) throws IOException {
            out.write(stuff);
        }

        @Override
        public boolean isStreaming() {
            return false;
        }

        @Override
        public void consumeContent() throws IOException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    
    
    public static String json = "";
    public static String path = "";
    public static String IDPath = "";
    public static String addImageURL = "";
    public static String addUserURL = "https://api.chooch.ai/predict/face?person_name=Generic Name&model_id=119&apikey=4df35e9e-77d1-43b8-b5a0-b3588c017ee5&command=create_person";
    public static String validateURL = "";
    
    public void createIDFileAndSetPath(String id) {
        AddStudentNote add = new AddStudentNote();
        add.createNote("faceID");
        add.writing("faceID", id);
        File file = new File("faceID" + ".txt");
        IDPath = file.getAbsolutePath();
    }
    
    public void deleteIDFile() {
        AddStudentNote add = new AddStudentNote();
        add.deleteNote("faceID");
    }
    
    public static String readFaceID() {
        AddStudentNote add = new AddStudentNote();
        return add.reading("faceID");
    }
            
    public void deleteImage() {
        //file name only
        File file = new File("face" + ".jpg");
        if(file.delete()){
            System.out.println("file.txt File deleted from Project root directory");
        }else {
            System.out.println("File file.txt doesn't exist in the project root directory");
        }
    }
    
    public void take() throws IOException {
        Webcam webcam = Webcam.getDefault();
        for (Dimension supportedSize: webcam.getViewSizes()) {
            System.out.println(supportedSize.toString());
        }
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        webcam.open();
        File file = new File("face.jpg");
        path = file.getAbsolutePath();
        System.out.println(path);
        ImageIO.write(webcam.getImage(), "jpg", file);
        webcam.close();
    } 
    public String imagePost(String localFilePath) throws FileNotFoundException, IOException {
       CloseableHttpClient httpClient = HttpClients.createDefault();
       HttpPost uploadFile = new HttpPost(addImageURL);
       MultipartEntityBuilder builder = MultipartEntityBuilder.create();

       // This attaches the file to the POST:
       File f = new File(localFilePath);
       builder.addBinaryBody(
           "image",
           new FileInputStream(f),
           ContentType.APPLICATION_OCTET_STREAM,
           f.getName()   
       );
       
       HttpEntity multipart = builder.build();
       HttpEntity sizedEntity = new HttpEntityWrapper(multipart);
       uploadFile.setEntity(sizedEntity);
       CloseableHttpResponse response = httpClient.execute(uploadFile);
       HttpEntity responseEntity = response.getEntity();
       String responseStr = EntityUtils.toString(responseEntity);
       System.out.println(responseStr);
       return responseStr;
    }
    
    public String formattAddUserToGetID(String raw) {
        //{"status_description":"success","post_type":"create_person","person_id":5053,"status":5053}
        String id = raw.substring(raw.lastIndexOf(":")+1, raw.lastIndexOf(":")+5);
        return id;
    }
    
    public void addUser() {
        String id = "";
        String replacedURL = addUserURL.replace(" ", "%20");
        try {
            URL obj = new URL(replacedURL);
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
            id = this.formattAddUserToGetID(myresponse.toString());
            System.out.println("ID: " +id);
            } catch(Exception e) {
                System.out.println(e + "   problem");
            }
        this.createIDFileAndSetPath(id);
        }
    
    public String formattValidation(String raw) {
        String hit = raw.substring(raw.indexOf("face_recog_hit\":") + 17, raw.indexOf("face_recog_hit\":") + 21);
        return hit;
    }
    
    public boolean validateFace() throws FileNotFoundException, IOException {
        boolean faceRecognised = false;
       validateURL = "https://api.chooch.ai/predict/face?person_id_filter=" + image.readFaceID() + "&model_id=119&apikey=4df35e9e-77d1-43b8-b5a0-b3588c017ee5";
       JOptionPane.showMessageDialog(null, "Please look pretty for the camera while we validate your face :)\nPlease click ok and then wait a few secounds");
       this.take();
       Sound.playcam();
       CloseableHttpClient httpClient = HttpClients.createDefault();
       HttpPost uploadFile = new HttpPost(validateURL);
       MultipartEntityBuilder builder = MultipartEntityBuilder.create();

       // This attaches the file to the POST:
       File f = new File(path);
       builder.addBinaryBody(
           "image",
           new FileInputStream(f),
           ContentType.APPLICATION_OCTET_STREAM,
           f.getName()   
       );
       
       HttpEntity multipart = builder.build();
       HttpEntity sizedEntity = new HttpEntityWrapper(multipart);
       uploadFile.setEntity(sizedEntity);
       CloseableHttpResponse response = httpClient.execute(uploadFile);
       HttpEntity responseEntity = response.getEntity();
       String responseStr = EntityUtils.toString(responseEntity);
       System.out.println(responseStr);
       if (this.formattValidation(responseStr).equals("true")) {
           this.pushValidatedFace();
           faceRecognised = true;
       }
       
       return faceRecognised;
    }
    
    public void pushValidatedFace() {
        addImageURL = "https://api.chooch.ai/predict/face?person_id_filter=" + image.readFaceID() + "&apikey=4df35e9e-77d1-43b8-b5a0-b3588c017ee5&command=insert_person_image";
        try {
            this.imagePost(path);
        } catch (IOException ex) {
            Logger.getLogger(image.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void pushFace() throws IOException {
        this.addUser();
        addImageURL = "https://api.chooch.ai/predict/face?person_id_filter=" + image.readFaceID() + "&apikey=4df35e9e-77d1-43b8-b5a0-b3588c017ee5&command=insert_person_image";
        JOptionPane.showMessageDialog(null, "Please click the 'take' button and then look into the camera (you will do this five times).");
        this.take();
        Sound.playcam();
        this.imagePost(path);
        this.deleteImage();
        JOptionPane.showMessageDialog(null, "Please click the 'take' button and then look into the camera again (you will do this five times).");
        this.take();
        Sound.playcam();
        this.imagePost(path);
        this.deleteImage();
        JOptionPane.showMessageDialog(null, "Please click the 'take' button and then look into the camera again (you will do this five times).");
        this.take();
        Sound.playcam();
        this.imagePost(path);
        this.deleteImage();
        JOptionPane.showMessageDialog(null, "Please click the 'take' button and then look into the camera again (you will do this five times).");
        this.take();
        Sound.playcam();
        this.imagePost(path);
        this.deleteImage();
        JOptionPane.showMessageDialog(null, "Please click the 'take' button and then look into the camera again (you will do this five times).");
        this.take();
        Sound.playcam();
        this.imagePost(path);
        this.deleteImage();
        JOptionPane.showMessageDialog(null, "Shots Taken (looking great!)");
    }
    
}
