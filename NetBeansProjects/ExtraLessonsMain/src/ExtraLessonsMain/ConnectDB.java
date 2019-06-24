/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtraLessonsMain;

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
public class ConnectDB {//creates a class called ConnectDB to handle connections to the database
    private Connection conn; //connection type to connect to the database
    private Statement stmt;//statement to store different queries
    private ResultSet rs;//a set of results stored of the stmt

    public Connection getConn() {//creates a method that returns the database connection object
        return conn;//returns the database connection object
    }//closes the getConn method

    public ConnectDB() {//creates a constructor of the class that establishes a connection with the ms Access database
        try {//opens the trycatch statement
            String url = "jdbc:ucanaccess://TestPAT.accdb";//url at which the database is stored on the computer aswell as the driver to connect to the database
            conn = DriverManager.getConnection(url);//establishes the connection to the database
            stmt = conn.createStatement();//sets the private statement 'stmt' to the connection statement
        } catch (Exception e) {//starts the catch statement
            System.out.println("Problem Connecting to database " + e);//alert the class user that there was a problem connecting to the database
        }//closes the catch statement
    }//closes the constructor

    public ResultSet getResults(String Query) {//creates a method that returns a resultSet of the database
        try {//opens the trycatch statement
            rs = stmt.executeQuery(Query);//The query that has been sent in via the String query is executed              
        } catch (Exception e) {//opens the catch statement
            System.out.println("Error occured: " + e);//alerts the user that an error occured when attempting to get the result set
        }//closes the catch
        return rs;//returns the resultset to the class user to be used
    }//closes the getResults method
    
    public void UpdateDatabase(String G) throws SQLException{//creates a method that will update the dtatabse according to the passed in SQL query
        stmt.executeUpdate(G);//exexutes the updating of the database according to the passed in SQL query
    }//closes the UpdateDatabase method

}//closes the ConnectDB class

