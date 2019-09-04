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
public class invoiceHandler {//creates a class to handle the invoice formatting
    
    // in: date and time of unpaid lesson
    public String getInvoiceSubjectTemplate(String date, String time) {//creates a method to format the subject of the invoice email according to the date and time passed in
        String subjectTemplate = "Invoice Regarding The Lesson On " + date + " at " + time;//creates a string holding the formatted subject
        return subjectTemplate;//returns the subject string
    }//closes the getInvoiceSubjectTemplate method
    
    // in: date, time, parent name, cost of unpaid lesson
    public String getInvoiceTemplate(String date, String time, String parent, String cost) {//creates a method to format the invoivce email body text
        fetchTeacher ft = new fetchTeacher();//creates an object for the fetchTeacher class
        
        String parentLastName = parent.substring(parent.indexOf(" "));//creates a string to hold the parent's last name
        String invoiceTemplate = 
            "Dear Mr/Mrs " + parentLastName + ".\n\n"
          + "I would like to inform you that I have not recieved payment for the lesson you child attended\n"
          + "on this date: " + date + " and this time: " + time + ".\n"
          + "This lesson cost " + cost + ".00\n\n"
          + "Please inform me of any issues regarding the payment, or if there is any confusion regarding\nthe aforementioned lesson.\n\n"
          + "Kind Regards\n"
          + ft.getFname() + " " + ft.getLname() + ".";//creates a string holding the formatted invoice email body text according to the date time parent and cost passed in
        return invoiceTemplate;//returns the invoiceTemplate String
    }//closes the getInvoiceTemplate method
    
}//closes the invoiceHandler class
