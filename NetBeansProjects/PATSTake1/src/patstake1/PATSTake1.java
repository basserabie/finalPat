/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patstake1;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;

/**
 *
 * @author YishaiBasserabie
 */
public class PATSTake1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConnectDB db = new ConnectDB();
        new loginSignup().setVisible(true);
        
        Runnable backGroundRunnable = new Runnable() {
        public void run(){
            sendEmail send = new sendEmail();
            send.configure("null", "null", "null");
        }};
        Thread sampleThread = new Thread(backGroundRunnable);
        sampleThread.start();
        
    }
}
