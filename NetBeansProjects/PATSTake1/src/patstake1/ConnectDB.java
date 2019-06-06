/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patstake1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author YishaiBasserabie
 */
public class ConnectDB {
    
    
    private Connection conn; //connection type to connect to the database
    private Statement stmt;//statement to store different queries
    private ResultSet rs;//a set of results stored of the stmt

    public Connection getConn() {
        return conn;
    }

    public ConnectDB() {
        try {
            
            String url = "jdbc:ucanaccess://TestPAT.accdb";//url at which the database is stored on the computer aswell as the driver to connect to the database
            conn = DriverManager.getConnection(url);//establishes the connection to the database
            stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println("Problem Connecting to database " + e);
        }
    }

    public ResultSet getResults(String Query) {
        try {
            rs = stmt.executeQuery(Query);//The query that has been sent in via the String query is executed              
        } catch (Exception e) {
            System.out.println("Error occured: " + e);
        }
        return rs;//returns the resultset to the user to be used
    }
    
    public void UpdateDatabase(String G) throws SQLException{
        stmt.executeUpdate(G);
    }

}

