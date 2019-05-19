/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patstake1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author YishaiBasserabie
 */
public class paymentsArray {
    ConnectDB  db = new ConnectDB();
    dataValidation vd = new dataValidation();
    private ArrayList<fetchPayments> paymentArray = new ArrayList<>();

    public paymentsArray() {
        ResultSet r = db.getResults("SELECT * FROM sPayTable");
        try {
            while(r.next()) {
                fetchPayments fp = new fetchPayments(r.getInt("StudID"), r.getInt("PayDuration"), r.getBoolean("Paid"), r.getString("PayDate"), r.getString("PayTime")
                , r.getInt("Cost"));
                paymentArray.add(fp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Database error, please contact administrator at 0836570642");
        }
    }

    public ArrayList<fetchPayments> getPaymentArray() {
        return paymentArray;
    }
    
    public String formattOutHTMLTags(String input) {
        String output = input.replaceAll("\\<.*?\\>", "");
        return output;
    }

    public void addPayment(int lessonID) {
        ConnectDB db = new ConnectDB();
        String addPaymentString = "UPDATE sPaytable SET Paid = true WHERE lessonID = " + lessonID;
        try {
            db.UpdateDatabase(addPaymentString);
        } catch (SQLException ex) {
            Logger.getLogger(paymentsArray.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void removePayment(int lessonID) {
        ConnectDB db = new ConnectDB();
        String addPaymentString = "UPDATE sPaytable SET Paid = false WHERE lessonID = " + lessonID;
        try {
            db.UpdateDatabase(addPaymentString);
        } catch (SQLException ex) {
            Logger.getLogger(paymentsArray.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
