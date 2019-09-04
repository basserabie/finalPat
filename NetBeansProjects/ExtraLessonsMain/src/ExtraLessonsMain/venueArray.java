/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtraLessonsMain;

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
public class venueArray {//creates a class to handle the venues
    private ArrayList<fetchVenue> venuesArray = new ArrayList<>();//creates an array list for the venues

    public venueArray() {//creates the class constrcutor instantiating a venueArray object
        ConnectDB db = new ConnectDB();//creates an object for the ConnectBD class
        ResultSet r = db.getResults("SELECT * FROM venues");//creates a resultSet for the venues
        try {
            while(r.next()) {//iterates through the results
                fetchVenue fv = new fetchVenue(r.getInt("venueID"), r.getString("venue"));//creates a venue object for the iterated result
                venuesArray.add(fv);//adds the object to the array list
            }
        } catch (SQLException ex) {
            Logger.getLogger(venueArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error getting the results
            JOptionPane.showMessageDialog(null, "Database error, please contact administrator at 0836570642");//instructs the user to contact the admin
        }
    }//closes the cosntructor

    public ArrayList<fetchVenue> getVenuesArray() {//creates a method to get the array list of venues
        return venuesArray;//returns the array list
    }//closes the getVenuesArray method
    
    // in: venue name
    public int venueIDFromVenue(String venue) {//creates a method to get the venueID from the name
        int id = 0;//creates an int for the id
        for (int i = 0; i < venuesArray.size(); i++) {//iterates through the venues
            if (this.venuesArray.get(i).getVenue().equals(venue)) {//checks if the iterated name is the same as the one passed in
                id = this.venuesArray.get(i).getVenueID();//sets the id to the iterated id
            }
        }
        return id;//returns the id
    }//closes the venueIDFromVenue method
    
    // in: venue id
    public String venueNameFromID(int id) {//creates a method to get the name from id
        String name = "";//creates a string for the name
        for (int i = 0; i < this.venuesArray.size(); i++) {//iterates through the venues
            if (id == this.venuesArray.get(i).getVenueID()) {//checks if the id is the same as the one passed in
                name = this.venuesArray.get(i).getVenue();//sets the name to the iterated name
            }
        }
        return name;//returns the name
    }//closes the venueNameFromID method
    
    // in: lesson id
    public int getVenueIDFromLessonID(int lessonID) {//creates a method to get the venue id of a lesson
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        int venueID = 0;//creates an int for the venue id
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {//itertates through the lessons
            if (la.getLessonDataArray().get(i).getLessonID() == lessonID) {//checks if the lesson id iterated is the same as the passed in id
                venueID = la.getLessonDataArray().get(i).getVenueID();//sets the venue id to the iterated venue id
            }
        }
        return venueID;//returns the venue id
    }//closes the getVenueIDFromLessonID method
    
    // in: venue to delete and venue to add
    public void deleteVenue(String Delvenue, String repVenue) {//creates a method to delete a venue
        ConnectDB db = new ConnectDB();//creates an object for the ConnectBD class
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        
        int delVid = this.venueIDFromVenue(Delvenue);//creates an int of the venue id to be deleted
        int refVid = this.venueIDFromVenue(repVenue);//creates an int of the venue id of the replacement
        
        if (delVid != refVid) {//checks if the replacement venue is not the same as the one being deleted
            for (int i = 0; i < la.getLessonDataArray().size(); i++) {//iterates through the lessons
                int Vid = la.getLessonDataArray().get(i).getVenueID();//creates an int for the iterated venue id
                if (delVid == Vid) {//checks if the iterated venue is is the same as the one being deleted
                    String editVenue = "UPDATE lessonData SET VenueID = " + refVid + " WHERE VenueID = " + delVid;//creates a string for the SQL query to change the venue id
                    try {
                        db.UpdateDatabase(editVenue);//changes the venue id to the replacement
                    } catch (SQLException ex) {
                        Logger.getLogger(venueArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error when changing the ids
                    }
                }
            }
            String delete = "DELETE * FROM venues WHERE venue = '" + Delvenue + "'";//creates a string fro the sql query used to delete the venue
            try {
                db.UpdateDatabase(delete);//deletes the venue
            } catch (SQLException ex) {
                Logger.getLogger(venueArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error when deleting the venue
            }
        } else {//if the replacement = the venue being deleted
            JOptionPane.showMessageDialog(null, "you cannot select the replacement venue as the same as the venue to delete!\n");//instructs the user to select a different replacement
        } 
    }//closes the deleteVenue method
    
    // in: venue name
    public void addVenue(String venue) {//creates a method to add a venue
        ConnectDB db = new ConnectDB();//creates an object for the ConnectBD class
        String insert = "INSERT INTO venues (venue) VALUES ('" + venue + "')";//creates a string for the SQL query used to insert the venue
        try {
            db.UpdateDatabase(insert);//adds the venue
        } catch (SQLException ex) {
            Logger.getLogger(addVenueForm.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error when adding the venue
        }
    }//closes the addVenue method
   
}//closes the venuesArray class
