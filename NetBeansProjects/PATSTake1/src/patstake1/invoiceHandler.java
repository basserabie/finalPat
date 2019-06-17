/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patstake1;

/**
 *
 * @author YishaiBasserabie
 */
public class invoiceHandler {
    
    public String getInvoiceSubjectTemplate(String date, String time) {
        String subjectTemplate = "Incoice Regarding The Lesson On " + date + " at " + time;
        return subjectTemplate;
    }
    
    public String getInvoiceTemplate(String date, String time, String parent, String cost) {
        fetchTeacher ft = new fetchTeacher();
        
        String parentLastName = parent.substring(parent.indexOf(" "));
        
        String invoiceTemplate = 
            "Dear Mr/Mrs " + parentLastName + ".\n\n"
          + "I would like to inform you that I have not recieved payment for the lesson you child attended\n"
          + "on this date: " + date + " and this time: " + time + ".\n"
          + "This lesson cost " + cost + ".00\n\n"
          + "Please inform me of any issues regarding the payment, or if there is any confusion regarding\n the aforementioned lesson.\n\n"
          + "Kind Regards\n"
          + ft.getFname() + " " + ft.getLname() + ".";
        return invoiceTemplate;
    }
    
}
