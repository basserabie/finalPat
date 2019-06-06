/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patstake1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
                fetchPayments fp = new fetchPayments(r.getInt("lessonID") ,r.getInt("StudID"), r.getInt("PayDuration"), r.getBoolean("Paid"), r.getString("PayDate"), r.getString("PayTime")
                , r.getInt("Cost"));
                paymentArray.add(fp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Database error, please contact administrator at 0836570642");
        }
        this.sortArray();
    }

    public ArrayList<fetchPayments> getPaymentArray() {
        return paymentArray;
    }
    
    //method sorts the array list
    public void sortArray() {
        //sorts by dates and times
         Collections.sort(this.paymentArray, new Comparator<fetchPayments>() {
             public int compare(fetchPayments m1, fetchPayments m2) {
                 int comp = 0;
                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM HH:mm");
                 try {
                     Date date1 = sdf.parse(m1.getPayDate() + " " + m1.getPayTime());
                     Date date2 = sdf.parse(m2.getPayDate() + " " + m2.getPayTime());
                     comp =  date1.compareTo(date2);
                 } catch (ParseException ex) {
                     Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
                 }
                  return comp;
             }

             public int comparePayments(fetchPayments o1, fetchPayments o2) {
                 throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }
         });
    }
    
    public String formattOutHTMLTags(String input) {
        String output = input.replaceAll("\\<.*?\\>", "");
        return output;
    }
    
    public int getCostFromLessonID(int id) {
        int cost = 0;
        for (int i = 0; i < this.paymentArray.size(); i++) {
            if (this.paymentArray.get(i).getLessonID() == id) {
                cost = this.paymentArray.get(i).getCost();
            }
        }
        return cost;
    }
    
    public boolean getIfPaidFromLessonID(int id) {
        boolean paid = false;
        for (int i = 0; i < this.paymentArray.size(); i++) {
            if (this.paymentArray.get(i).getLessonID() == id) {
                paid = this.paymentArray.get(i).isPaid();
            }
        }
        return paid;
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
    
    public String [] monthsForRealChart() {
        ArrayList<String> months = new ArrayList<>();
        int count = -1;
        for (int i = 0; i < this.paymentArray.size(); i++) {
            if (!months.contains(this.paymentArray.get(i).getPayDate().substring(0, 5) + " " + this.paymentArray.get(i).getPayDate().substring(8, 10)) && this.paymentArray.get(i).isPaid()) {
                months.add(this.paymentArray.get(i).getPayDate().substring(0, 5) + " " + this.paymentArray.get(i).getPayDate().substring(8, 10));
                count++;
            }
        }
        if (months.size() == 0) {
            months.add("no payments");
        }
        String monthString [] = months.toArray(new String[months.size()]);
        return monthString;
    }
    
    public int [] totalPaymentsForAllMonthsArrayForRealChart() {
        ArrayList<Integer> p = new ArrayList<>();
        int monthSum = 0;
        for (int i = 0;i < this.monthsForRealChart().length; i++) {
            for (int k = 0; k < this.paymentArray.size(); k++) {
                if ((this.paymentArray.get(k).getPayDate().substring(0, 5) + " " + this.paymentArray.get(k).getPayDate().substring(8, 10)).equals(this.monthsForRealChart()[i]) && this.paymentArray.get(k).isPaid()) {
                    monthSum += this.paymentArray.get(k).getCost();
                }
            }
            p.add(monthSum);
            monthSum = 0;
        }
        if (p.size() == 0) {
            p.add(0);
        }
        int[] Parr = p.stream().mapToInt(s -> s).toArray();
        return Parr;
    }
    
    public String [] monthsForProjectedChart() {
        ArrayList<String> months = new ArrayList<>();
        for (int i = 0; i < this.paymentArray.size(); i++) {
            if (!months.contains(this.paymentArray.get(i).getPayDate().substring(0, 5) + " " +  this.paymentArray.get(i).getPayDate().substring(8, 10))) {
                months.add(this.paymentArray.get(i).getPayDate().substring(0, 5) + " " + this.paymentArray.get(i).getPayDate().substring(8, 10));
            }
        }
        if (months.size() == 0) {
            months.add("no payments");
        }
        String monthString [] = months.toArray(new String[months.size()]);
        return monthString;
    }
    
    public int [] totalPaymentsForAllMonthsArrayForProjectedChart() {
        ArrayList<Integer> p = new ArrayList<>();
        int monthSum = 0;
        for (int i = 0;i < this.monthsForProjectedChart().length; i++) {
            for (int k = 0; k < this.paymentArray.size(); k++) {
                if ((this.paymentArray.get(k).getPayDate().substring(0, 5) + " " + this.paymentArray.get(k).getPayDate().substring(8, 10)).equals(this.monthsForProjectedChart()[i])) {
                    monthSum += this.paymentArray.get(k).getCost();
                }
            }
            p.add(monthSum);
            monthSum = 0;
        }
        if (p.size() == 0) {
            p.add(0);
        }
        int[] Parr = p.stream().mapToInt(s -> s).toArray();
        return Parr;
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
    
    public int totalForCurrentMonth() {
        lessonDataArray la = new lessonDataArray();
        int total = 0;
        DateFormat sdf = new SimpleDateFormat("yyy/dd/MM");
        Calendar today = Calendar.getInstance();
        try {
            today.setTime(sdf.parse(la.formatDate(""+Calendar.getInstance().getTime())));
        } catch (ParseException ex) {
            Logger.getLogger(paymentsArray.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < this.paymentArray.size(); i++) {
            Calendar ref = Calendar.getInstance();
            try {
                ref.setTime(sdf.parse(this.paymentArray.get(i).getPayDate()));
            } catch (ParseException ex) {
                Logger.getLogger(paymentsArray.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (ref.get(Calendar.MONTH) == today.get(Calendar.MONTH)) {
                total += this.paymentArray.get(i).getCost();
            }
        }
        return total;
    }
    
    public int totalPaidForCurrentMonth() {
        lessonDataArray la = new lessonDataArray();
        int total = 0;
        DateFormat sdf = new SimpleDateFormat("yyy/dd/MM");
        Calendar today = Calendar.getInstance();
        try {
            today.setTime(sdf.parse(la.formatDate(""+Calendar.getInstance().getTime())));
        } catch (ParseException ex) {
            Logger.getLogger(paymentsArray.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < this.paymentArray.size(); i++) {
            Calendar ref = Calendar.getInstance();
            try {
                ref.setTime(sdf.parse(this.paymentArray.get(i).getPayDate()));
            } catch (ParseException ex) {
                Logger.getLogger(paymentsArray.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (ref.get(Calendar.MONTH) == today.get(Calendar.MONTH) && this.paymentArray.get(i).isPaid()) {
                total += this.paymentArray.get(i).getCost();
            }
        }
        return total;
    }
    
    public int totalOutstandingForCurrentMonth() {
        lessonDataArray la = new lessonDataArray();
        int total = 0;
        DateFormat sdf = new SimpleDateFormat("yyy/dd/MM");
        Calendar today = Calendar.getInstance();
        try {
            today.setTime(sdf.parse(la.formatDate(""+Calendar.getInstance().getTime())));
        } catch (ParseException ex) {
            Logger.getLogger(paymentsArray.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < this.paymentArray.size(); i++) {
            Calendar ref = Calendar.getInstance();
            try {
                ref.setTime(sdf.parse(this.paymentArray.get(i).getPayDate()));
            } catch (ParseException ex) {
                Logger.getLogger(paymentsArray.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (ref.get(Calendar.MONTH) == today.get(Calendar.MONTH) && !this.paymentArray.get(i).isPaid()) {
                total += this.paymentArray.get(i).getCost();
            }
        }
        return total;
    }
    
    public int getStudentTotal(String name) {
        lessonDataArray la = new lessonDataArray();
        studentsArray sa = new studentsArray();
        int total = 0;
        
        for (int i = 0; i < this.paymentArray.size(); i++) {
            int studentID = la.getStudentIDFromIndex(la.getIndexFromID(this.paymentArray.get(i).getLessonID()));
            String studentName = sa.studentNameFromID(studentID);
            if (studentName.toLowerCase().equals(name.toLowerCase())) {
                total += this.paymentArray.get(i).getCost();
            }
        }
        return total;
    }
    
    public int getStudentPaid(String name) {
        lessonDataArray la = new lessonDataArray();
        studentsArray sa = new studentsArray();
        int total = 0;
        
        for (int i = 0; i < this.paymentArray.size(); i++) {
            int studentID = la.getStudentIDFromIndex(la.getIndexFromID(this.paymentArray.get(i).getLessonID()));
            String studentName = sa.studentNameFromID(studentID);
            if (studentName.toLowerCase().equals(name.toLowerCase()) && this.paymentArray.get(i).isPaid()) {
                total += this.paymentArray.get(i).getCost();
            }
        }
        return total;
    }
        
    public int getStudentOwed(String name) {
        lessonDataArray la = new lessonDataArray();
        studentsArray sa = new studentsArray();
        int total = 0;
        
        for (int i = 0; i < this.paymentArray.size(); i++) {
            int studentID = la.getStudentIDFromIndex(la.getIndexFromID(this.paymentArray.get(i).getLessonID()));
            String studentName = sa.studentNameFromID(studentID);
            if (studentName.toLowerCase().equals(name.toLowerCase()) && !this.paymentArray.get(i).isPaid()) {
                total += this.paymentArray.get(i).getCost();
            }
        }
        return total;
    }
    
    public String getPaymentsLessonDateFromLessonID(int id) {
        String date = "";
        for (int i = 0; i < this.paymentArray.size(); i++) {
            if (this.paymentArray.get(i).getLessonID() == id) {
                date = this.paymentArray.get(i).getPayDate();
            }
        }
        return date;
    }
    
    public String getPaymentsLessonTimeFromLessonID(int id) {
        String time = "";
        for (int i = 0; i < this.paymentArray.size(); i++) {
            if (this.paymentArray.get(i).getLessonID() == id) {
                time = this.paymentArray.get(i).getPayTime();
            }
        }
        return time;
    }
        
    public void deletePastAndunpaidPayments() {
        lessonDataArray la = new lessonDataArray();
        ConnectDB db = new ConnectDB();
        DateFormat sdf = new SimpleDateFormat("yyy/dd/MM HH:mm");
        Calendar today = Calendar.getInstance();
        Calendar ref = Calendar.getInstance();
        today.setTime(Calendar.getInstance().getTime());
        
        for (int i = 0; i < this.paymentArray.size(); i++) {
            int id = this.paymentArray.get(i).getLessonID();
            try {
                ref.setTime(sdf.parse(this.getPaymentsLessonDateFromLessonID(id) + " " + this.getPaymentsLessonTimeFromLessonID(id)));
            } catch (ParseException ex) {
                Logger.getLogger(paymentsArray.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (ref.before(today)) {
                String delete = "DELETE * FROM sPayTable WHERE lessonID = " + id;
                try {
                    db.UpdateDatabase(delete);
                } catch (SQLException ex) {
                    Logger.getLogger(paymentsArray.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void deletePaymentsFromDateAndTime(String date, String time) {
        ConnectDB db = new ConnectDB();
        for (int i = 0; i < this.paymentArray.size(); i++) {
            if (this.paymentArray.get(i).getPayDate().equals(date) && this.paymentArray.get(i).getPayTime().equals(time)) {
                String deletePayments = "DELETE * FROM sPayTable WHERE lessonID = " + this.paymentArray.get(i).getLessonID();
                try {
                    db.UpdateDatabase(deletePayments);
                } catch (SQLException ex) {
                    Logger.getLogger(keysArray.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
