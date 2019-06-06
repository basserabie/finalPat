/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patstake1;

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
        
//        String insert = "INSERT INTO teacherTable (fName, lName, cell, email, password, signedUp, currentYear) VALUES ('alwin', 'murugen', '0843546354', 'yishibass@gmail.com', 'Macbookpro1', "
//                + "true, '2019/06/05')";
//        try {
//            db.UpdateDatabase(insert);
//            System.out.println("boo");
//        } catch (SQLException ex) {
//            Logger.getLogger(PATSTake1.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println("hi");
//        fetchTeacher ft = new fetchTeacher();
        }       
    
    }

