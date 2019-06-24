/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtraLessonsMain;

import java.awt.Desktop;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.swing.JLabel;

/**
 *
 * @author YishaiBasserabie
 */
public class ExtraLessonsMain {//creates a class called ExtraLessonsMain to start the program
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {//creates the main static void method
        ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class, calling the constructo and establishing connection to database
        fetchTeacher ft = new fetchTeacher();
        new loginSignup().setVisible(true);//creates an object for the loginSignup class and sets its JFrame visible
        
        Runnable backGroundRunnable = new Runnable() {//creates a new background runnable to add to a seperate thread
        public void run(){//creates a method to run in the seperate thread
            sendEmail send = new sendEmail();//creates an object for the sendEmail class
            send.configure("null", "null", "null");//calls the configure method from the send object to configure the email service
        }};//closes the background runnable
        Thread sampleThread = new Thread(backGroundRunnable);//creates a new thread object and adds the background runnable to it
        sampleThread.start();//starts the new thread
    }//closes the main static void method
    
}//closes the ExtraLessonMainClass
