/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtraLessonsMain;

/**
 *
 * @author YishaiBasserabie
 */
public class fetchVenue {//creates a class representing a venue object
    private int venueID;//creates an integer holding the venueID of the venue object
    private String venue;//creates a string holding the venue name of the venue object

    public fetchVenue(int venueID, String venue) {//creates the constructor for the current class instantiating a venue object
        this.venueID = venueID;//assignes the vneuID passed in to the venueID of the object
        this.venue = venue;//assignes the venue name passed in to the venue name of the object
    }//closes the constructor

    public int getVenueID() {//creates a method to return the venueID
        return venueID;//returns the venueID
    }//closes the getter method

    public void setVenueID(int venueID) {//creates a method to set the venueID
        this.venueID = venueID;//sets the venueID
    }//closes the setter method

    public String getVenue() {//creates a method to return the venue name
        return venue;//returns the venue name
    }//closes the getter method

    public void setVenue(String venue) {//creates a method to set the venue name
        this.venue = venue;//sets the venue name
    }//closes the setter method
       
}//closes the fetchVenue class
