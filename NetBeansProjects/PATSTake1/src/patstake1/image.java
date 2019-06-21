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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import sun.tools.jar.CommandLine;

/**
 *
 * @author YishaiBasserabie
 */
public class image {
    public static String json = "";
    public static String path = "";
    
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
    public void run() {
		
        
    }
//    
//    public void pushFace() throws IOException {
//        JOptionPane.showMessageDialog(null, "Please click the 'take' button and then look into the camera (you will do this five times).");
//        this.take();
//        this.run();
//        this.deleteImage();
//        JOptionPane.showMessageDialog(null, "Please click the 'take' button and then look into the camera again (you will do this five times).");
//        this.take();
//        this.run();
//        this.deleteImage();
//        JOptionPane.showMessageDialog(null, "Please click the 'take' button and then look into the camera again (you will do this five times).");
//        this.take();
//        this.run();
//        this.deleteImage();
//        JOptionPane.showMessageDialog(null, "Please click the 'take' button and then look into the camera again (you will do this five times).");
//        this.take();
//        this.run();
//        this.deleteImage();
//        JOptionPane.showMessageDialog(null, "Please click the 'take' button and then look into the camera again (you will do this five times).");
//        this.take();
//        this.run();
//        this.deleteImage();
//        JOptionPane.showMessageDialog(null, "Shots Taken (looking great!)");
//    }
    
}
