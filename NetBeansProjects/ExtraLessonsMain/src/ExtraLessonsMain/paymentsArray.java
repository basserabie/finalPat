/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtraLessonsMain;

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
public class paymentsArray {//creates a class to handle the payments
    private ArrayList<fetchPayments> paymentArray = new ArrayList<>();//creates an array list for the payment objects

    public paymentsArray() {//creates the cosntructor for the current class and instantiates a payment object
        ConnectDB  db = new ConnectDB();//creates an object for the Connect db class
        ResultSet r = db.getResults("SELECT * FROM sPayTable");//creates a result set for the payment objects
        try {
            while(r.next()) {//iterates through the results
                fetchPayments fp = new fetchPayments(r.getInt("lessonID") ,r.getInt("StudID"), r.getInt("PayDuration"), r.getBoolean("Paid"), r.getString("PayDate"), r.getString("PayTime")
                , r.getInt("Cost"));//creates a new payment object for the iterated result
                paymentArray.add(fp);//adds the object to the array
            }
        } catch (SQLException ex) {
            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error
            JOptionPane.showMessageDialog(null, "Database error, please contact administrator at 0836570642");//instructs the user to contact the admin
        }
        this.sortArray();//sorts the payments array
    }//closes the constructor

    public ArrayList<fetchPayments> getPaymentArray() {//creates a method to return the array list of payments
        return paymentArray;//returns the array
    }//closes the getPaymentArray method
    
    public void sortArray() {//method sorts the array list
        //sorts by dates and times
         Collections.sort(this.paymentArray, new Comparator<fetchPayments>() {//sorts with comparator
             public int compare(fetchPayments m1, fetchPayments m2) {//creates a method to compare the objects
                 int comp = 0;//creates an int to returns the comparison
                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM HH:mm");//cretes a simple date formatter
                 try {
                     Date date1 = sdf.parse(m1.getPayDate() + " " + m1.getPayTime());//createsa date object for the first iterated payment
                     Date date2 = sdf.parse(m2.getPayDate() + " " + m2.getPayTime());//createsa date object for the 2nd iterated payment
                     comp =  date1.compareTo(date2);//compares the two
                 } catch (ParseException ex) {
                     Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error setting the dates of the date objects
                 }
                  return comp;//returns the comp int
             }//closes the compare method

             public int comparePayments(fetchPayments o1, fetchPayments o2) {//creates an abstract method for the comparison
                 throw new UnsupportedOperationException("Not supported yet."); //throws not supported exception
             }//closes the comparePayments method
         });//closes the sort comparator
    }//closes the sort method
    
    // in: text to have tags removed
    public String formattOutHTMLTags(String input) {//creates a method to remove HTML tags from text
        String output = input.replaceAll("\\<.*?\\>", "");//creates a string without tags
        return output;//returns the output string
    }//closes the formattOutHTMLTags method
    
    // in: lesson id
    public int getCostFromLessonID(int id) {//creates a method to get cost from id
        int cost = 0;//creates an int for the cost
        for (int i = 0; i < this.paymentArray.size(); i++) {//iterates through the payments
            if (this.paymentArray.get(i).getLessonID() == id) {//chec is the iterated and reference ids match
                cost = this.paymentArray.get(i).getCost();//sets the cost to the iterated cost
            }
        }
        return cost;//returns the cost
    }
    
    // in: lesson id
    public boolean getIfPaidFromLessonID(int id) {//creates a method to get if paid from lesson id
        boolean paid = false;//creates a boolean i.e. if paid
        for (int i = 0; i < this.paymentArray.size(); i++) {//iterates through the payments
            if (this.paymentArray.get(i).getLessonID() == id) {//chec is the iterated and reference ids match
                paid = this.paymentArray.get(i).isPaid();//sets paid to the iterated paid boolean
            }
        }
        return paid;//returns paid
    }//closes the getIfPaidFromLessonID method
    
    // in: date and time and studentID
    public int getLessonPayIDFromDateTimeStudentID(String date, String time, int studentID) {//creates a method to get id from date time and student id
        int id = 0;//creates an in for the id
        for (int i = 0; i < this.paymentArray.size(); i++) {//iterates through the payments
            String startTime = time.substring(0, 5);//creates a string for the start time
            if (this.paymentArray.get(i).getPayDate().equals(date) &&
                    this.paymentArray.get(i).getPayTime().equals(startTime) &&
                    this.paymentArray.get(i).getStudentID() == studentID) {//checks if the date time and id mathc the iterated
                id = this.paymentArray.get(i).getLessonID();//sets the id to the iterated id
            }
        }
        return id;//returns id
    }//cloes the getLessonPayIDFromDateTimeStudentID method

    // in: lesson id
    public void addPayment(int lessonID) {//creates a method to add a payment
        ConnectDB db = new ConnectDB();//creates an object for the Connect DB class
        String addPaymentString = "UPDATE sPaytable SET Paid = true WHERE lessonID = " + lessonID;//creates a string for the SQL query used to add a payment
        try {
            db.UpdateDatabase(addPaymentString);//adds payment
        } catch (SQLException ex) {
            Logger.getLogger(paymentsArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error to add the payment
        }
    }//closes the addPayment method
    
    public String [] monthsForRealChart() {//creates a method to get the months for the paid bar chart
        ArrayList<String> months = new ArrayList<>();//creates an array list for the months
        int count = -1;//creates an int to count the months
        for (int i = 0; i < this.paymentArray.size(); i++) {//iterates through the payments
            if (!months.contains(this.paymentArray.get(i).getPayDate().substring(0, 5) + " " + this.paymentArray.get(i).getPayDate().substring(8, 10)) && this.paymentArray.get(i).isPaid()) {//checks if the terated month contains a payment
                months.add(this.paymentArray.get(i).getPayDate().substring(0, 5) + " " + this.paymentArray.get(i).getPayDate().substring(8, 10));//adds the iterated month the to the array
                count++;//ups the count
            }
        }
        if (months.size() == 0) {//checks if there are no payments
            months.add("no payments");//adds the no payments message to the array
        }
        String monthString [] = months.toArray(new String[months.size()]);//streams the array list to a string array
        return monthString;//returns the string array
    }//closes the monthsForRealChart method
    
    public int [] totalPaymentsForAllMonthsArrayForRealChart() {//creates a method to get the amount paid for each months
        ArrayList<Integer> p = new ArrayList<>();//creates an array list for the payments
        int monthSum = 0;//creates an int for the sum of payments for the iterated month
        for (int i = 0;i < this.monthsForRealChart().length; i++) {//iterates through the months
            for (int k = 0; k < this.paymentArray.size(); k++) {//iterates through the payments
                if ((this.paymentArray.get(k).getPayDate().substring(0, 5) + " " + this.paymentArray.get(k).getPayDate().substring(8, 10)).equals(this.monthsForRealChart()[i]) && this.paymentArray.get(k).isPaid()) {//checks if the iterated payments is paid and in the same mnth as the iterated month
                    monthSum += this.paymentArray.get(k).getCost();//adds the oayment to the sum
                }
            }
            p.add(monthSum);//adds the sum
            monthSum = 0;//resets sum to 0
        }
        if (p.size() == 0) {//checks if there are no payments
            p.add(0);//adds 0 to the array
        }
        int[] Parr = p.stream().mapToInt(s -> s).toArray();//streams the array lsit to a int array
        return Parr;//returns the int array
    }//closes the totalPaymentsForAllMonthsArrayForRealChart method
    
    public String [] monthsForProjectedChart() {//creates a method to get the months for the unpaid bar chart
        ArrayList<String> months = new ArrayList<>();//creates an array list for the months
        for (int i = 0; i < this.paymentArray.size(); i++) {//iterates through the payments
            if (!months.contains(this.paymentArray.get(i).getPayDate().substring(0, 5) + " " +  this.paymentArray.get(i).getPayDate().substring(8, 10))) {//checks if the iterated month does not ocntain a payment
                months.add(this.paymentArray.get(i).getPayDate().substring(0, 5) + " " + this.paymentArray.get(i).getPayDate().substring(8, 10));//adds the iterated month to the array
            }
        }
        if (months.size() == 0) {//checks if there are no months i.e., no payments
            months.add("no payments");//adds the no payments message to the array
        }
        String monthString [] = months.toArray(new String[months.size()]);//streams the array list to a string array
        return monthString;//returns the string array
    }//closes the monthsForProjectedChart method
    
    public int [] totalPaymentsForAllMonthsArrayForProjectedChart() {//creates a method to get the amount unpaid for each months
        ArrayList<Integer> p = new ArrayList<>();//creates an array list for the payments
        int monthSum = 0;//creates an int for the sum of payments for the iterated month
        for (int i = 0;i < this.monthsForProjectedChart().length; i++) {//iterates through the months
            for (int k = 0; k < this.paymentArray.size(); k++) {//iterates through the payments
                if ((this.paymentArray.get(k).getPayDate().substring(0, 5) + " " + this.paymentArray.get(k).getPayDate().substring(8, 10)).equals(this.monthsForProjectedChart()[i])) {//checks if the iterated payments is unpaid and in the same mnth as the iterated month
                    monthSum += this.paymentArray.get(k).getCost();//adds the nonpayment to the sum
                }
            }
            p.add(monthSum);//adds the some to the array
            monthSum = 0;//resets the sum to 0
        }
        if (p.size() == 0) {//checks if there are no payments
            p.add(0);//adds 0 to the array
        }
        int[] Parr = p.stream().mapToInt(s -> s).toArray();//streams the array list to an int array
        return Parr;//returns the int array
    }//closes the totalPaymentsForAllMonthsArrayForProjectedChart method
    
    // in: lesson id
    public void removePayment(int lessonID) {//creates a method to remove a payment
        ConnectDB db = new ConnectDB();//creates an object for the Connect DB class
        String addPaymentString = "UPDATE sPaytable SET Paid = false WHERE lessonID = " + lessonID;//creates a string for the SQL query used to remove the payment
        try {
            db.UpdateDatabase(addPaymentString);//removes the payment
        } catch (SQLException ex) {
            Logger.getLogger(paymentsArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error removing the payment
        }
    }//closes the removePayment method
    
    public int totalForCurrentMonth() {//creates a method to get the total for the current month
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        int total = 0;//creates an in to the total
        DateFormat sdf = new SimpleDateFormat("yyy/dd/MM");//creates a simple date formatter
        Calendar today = Calendar.getInstance();//creates a calendar instance
        try {
            today.setTime(sdf.parse(la.formatDate(""+Calendar.getInstance().getTime())));//sets the calendar object to today
        } catch (ParseException ex) {
            Logger.getLogger(paymentsArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error setting the date
        }
        for (int i = 0; i < this.paymentArray.size(); i++) {//iterates through the payments
            Calendar ref = Calendar.getInstance();//creates a clalendar instance
            try {
                ref.setTime(sdf.parse(this.paymentArray.get(i).getPayDate()));//sets the date to the iterated date
            } catch (ParseException ex) {
                Logger.getLogger(paymentsArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error setting the date
            }
            if (ref.get(Calendar.MONTH) == today.get(Calendar.MONTH)) {//checks if the months are the same
                total += this.paymentArray.get(i).getCost();//adds the iterated payment to the total
            }
        }
        return total;//returns the total
    }//closes the totalForCurrentMonth method
    
    public int totalPaidForCurrentMonth() {//creates a method to get the paid for the current month
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        int total = 0;//creates an in to the total
        DateFormat sdf = new SimpleDateFormat("yyy/dd/MM");//creates a simple date formatter
        Calendar today = Calendar.getInstance();//creates a calendar instance
        try {
            today.setTime(sdf.parse(la.formatDate(""+Calendar.getInstance().getTime())));//sets the calendar object to today
        } catch (ParseException ex) {
            Logger.getLogger(paymentsArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error setting the date
        }
        for (int i = 0; i < this.paymentArray.size(); i++) {//iterates through the payments
            Calendar ref = Calendar.getInstance();//creates a clalendar instance
            try {
                ref.setTime(sdf.parse(this.paymentArray.get(i).getPayDate()));//sets the date to the iterated date
            } catch (ParseException ex) {
                Logger.getLogger(paymentsArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error setting the date
            }
            if (ref.get(Calendar.MONTH) == today.get(Calendar.MONTH) && this.paymentArray.get(i).isPaid()) {//checks if the months are the same and the payment is paid
                total += this.paymentArray.get(i).getCost();//adds the iterated payment to the total
            }
        }
        return total;//returns the total
    }//closes the totalPaidForCurrentMonth method
    
    public int totalOutstandingForCurrentMonth() {//creates a method to get the owed for the current month
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        int total = 0;//creates an in to the total
        DateFormat sdf = new SimpleDateFormat("yyy/dd/MM");//creates a simple date formatter
        Calendar today = Calendar.getInstance();//creates a calendar instance
        try {
            today.setTime(sdf.parse(la.formatDate(""+Calendar.getInstance().getTime())));//sets the calendar object to today
        } catch (ParseException ex) {
            Logger.getLogger(paymentsArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error setting the date
        }
        for (int i = 0; i < this.paymentArray.size(); i++) {//iterates through the payments
            Calendar ref = Calendar.getInstance();//creates a clalendar instance
            try {
                ref.setTime(sdf.parse(this.paymentArray.get(i).getPayDate()));//sets the date to the iterated date
            } catch (ParseException ex) {
                Logger.getLogger(paymentsArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error setting the date
            }
            if (ref.get(Calendar.MONTH) == today.get(Calendar.MONTH) && !this.paymentArray.get(i).isPaid()) {//checks if the months are the same and the payment is not paid
                total += this.paymentArray.get(i).getCost();//adds the iterated payment to the total
            }
        }
        return total;//returns the total
    }//cloes the totalOutstandingForCurrentMonth method
    
    // in: student name
    public int getStudentTotal(String name) {//creates a method to get the sudent total
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        int total = 0;//creates an int for the total
        
        for (int i = 0; i < this.paymentArray.size(); i++) {//iterates through the payments
            int studentID = la.getStudentIDFromIndex(la.getIndexFromID(this.paymentArray.get(i).getLessonID()));//creates an int to the iterated student id
            String studentName = sa.studentNameFromID(studentID);//creates a string for the iterated student name
            if (studentName.toLowerCase().equals(name.toLowerCase())) {//checks if the iterated name is the same as the one passed in
                total += this.paymentArray.get(i).getCost();//adds the iterated payment to the total
            }
        }
        return total;//returns the total
    }//closes the getStudentTotal method
    
    // in: student name
    public int getStudentTotalForMonth(String name) {//creates a method to get the student total for the month
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        int total = 0;//creates an int for the total
        
        DateFormat sdf = new SimpleDateFormat("yyy/dd/MM");//creates a simple date formatter
        Calendar today = Calendar.getInstance();//creates a vcalendar instance for today
        Calendar ref = Calendar.getInstance();//creates a vcalendar instance for the ietrated payment object
        try {
            today.setTime(sdf.parse(la.formatDate(""+Calendar.getInstance().getTime())));//sets the time to today
        } catch (ParseException ex) {
            Logger.getLogger(paymentsArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error setting the date
        }
        
        for (int i = 0; i < this.paymentArray.size(); i++) {//iterates through the payments
            int studentID = la.getStudentIDFromIndex(la.getIndexFromID(this.paymentArray.get(i).getLessonID()));//creates an int to the iterated student id
            String studentName = sa.studentNameFromID(studentID);//creates a string for the iterated student name
            try {
                ref.setTime(sdf.parse(this.paymentArray.get(i).getPayDate()));//sets the date of the ref object to the iterated date
            } catch (ParseException ex) {
                Logger.getLogger(paymentsArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error setting the date
            }
            if (studentName.toLowerCase().equals(name.toLowerCase()) && ref.get(Calendar.MONTH) == today.get(Calendar.MONTH)) {//checks if the name and months match
                total += this.paymentArray.get(i).getCost();//adds the iterated payment to the total
            }
        }
        return total;//returns the total
    }//closes the getStudentTotalForMonth method
    
    // in: student name
    public int getStudentPaid(String name) {//creates a method to get the amount a student has paid
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        int total = 0;//creates an int for the total
        
        for (int i = 0; i < this.paymentArray.size(); i++) {//iterates through the payments
            int studentID = la.getStudentIDFromIndex(la.getIndexFromID(this.paymentArray.get(i).getLessonID()));//creates an int to the iterated student id
            String studentName = sa.studentNameFromID(studentID);//creates a string for the iterated student name
            if (studentName.toLowerCase().equals(name.toLowerCase()) && this.paymentArray.get(i).isPaid()) {//checks if the payment is paud and the names match
                total += this.paymentArray.get(i).getCost();//adds the iterated payment to the total
            }
        }
        return total;//returns the total
    }//closes the getStudentPaid method
    
    // in: student name
    public int getStudentPaidForMonth(String name) {//creates a method to get the payments aid for the month of a student
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        int total = 0;//creates an int for the total
        
        DateFormat sdf = new SimpleDateFormat("yyy/dd/MM");//creates a simple date formatter
        Calendar today = Calendar.getInstance();//creates a vcalendar instance for today
        Calendar ref = Calendar.getInstance();//creates a vcalendar instance for the ietrated payment object
        try {
            today.setTime(sdf.parse(la.formatDate(""+Calendar.getInstance().getTime())));//sets the time to today
        } catch (ParseException ex) {
            Logger.getLogger(paymentsArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error setting the date
        }
        
        for (int i = 0; i < this.paymentArray.size(); i++) {//iterates through the payments
            int studentID = la.getStudentIDFromIndex(la.getIndexFromID(this.paymentArray.get(i).getLessonID()));//creates an int to the iterated student id
            String studentName = sa.studentNameFromID(studentID);//creates a string for the iterated student name
            
             try {
                ref.setTime(sdf.parse(this.paymentArray.get(i).getPayDate()));//sets the date of the ref object to the iterated date
            } catch (ParseException ex) {
                Logger.getLogger(paymentsArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error setting the date
            }
            
            if (studentName.toLowerCase().equals(name.toLowerCase()) && this.paymentArray.get(i).isPaid() && ref.get(Calendar.MONTH) == today.get(Calendar.MONTH)) {//checks if the months match and the iterated oayment is paid
                total += this.paymentArray.get(i).getCost();//adds the iterated payment to the total
            }
        }
        return total;//returns the total
    }//returns the getStudentPaidForMonth method
        
    // in: student name
    public int getStudentOwed(String name) {//gets the amount a student owes
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        int total = 0;//creates an int for the total
        
        for (int i = 0; i < this.paymentArray.size(); i++) {//iterates through the payments
            int studentID = la.getStudentIDFromIndex(la.getIndexFromID(this.paymentArray.get(i).getLessonID()));//creates an int to the iterated student id
            String studentName = sa.studentNameFromID(studentID);//creates a string for the iterated student name
            if (studentName.toLowerCase().equals(name.toLowerCase()) && !this.paymentArray.get(i).isPaid()) {//checks if the names match and the payment is unpaid
                total += this.paymentArray.get(i).getCost();//adds the iterated payment to the total
            }
        }
        return total;//returns the total
    }//closes the getStudentOwed method
    
    // in: student name
    public int getStudentOwedForMonth(String name) {//creates a method that takes in a name and returns the amount the student owes for the current month
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        int total = 0;//creates an int for the total
        
        DateFormat sdf = new SimpleDateFormat("yyy/dd/MM");//creates a simple date formatter
        Calendar today = Calendar.getInstance();//creates a vcalendar instance for today
        Calendar ref = Calendar.getInstance();//creates a vcalendar instance for the ietrated payment object
        try {
            today.setTime(sdf.parse(la.formatDate(""+Calendar.getInstance().getTime())));//sets the time to today
        } catch (ParseException ex) {
            Logger.getLogger(paymentsArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error setting the date
        }
        
        for (int i = 0; i < this.paymentArray.size(); i++) {//iterates through the payments
            int studentID = la.getStudentIDFromIndex(la.getIndexFromID(this.paymentArray.get(i).getLessonID()));//creates an int to the iterated student id
            String studentName = sa.studentNameFromID(studentID);//creates a string for the iterated student name
            
            try {
                ref.setTime(sdf.parse(this.paymentArray.get(i).getPayDate()));//sets the date of the ref object to the iterated date
            } catch (ParseException ex) {
                Logger.getLogger(paymentsArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error setting the date
            }
            
            if (studentName.toLowerCase().equals(name.toLowerCase()) && !this.paymentArray.get(i).isPaid() && ref.get(Calendar.MONTH) == today.get(Calendar.MONTH)) {//checks if the months match and the iterated oayment is unpaid
                total += this.paymentArray.get(i).getCost();//adds the iterated payment to the total
            }
        }
        return total;//returns the total
    }//closes the getStudentOwedForMonth method
    
    // in: lesson id
    public String getPaymentsLessonDateFromLessonID(int id) {//creates a method to get the date form the id
        String date = "";//creates a string for the date
        for (int i = 0; i < this.paymentArray.size(); i++) {//iterates through the payments
            if (this.paymentArray.get(i).getLessonID() == id) {//chec is the iterated and reference ids match
                date = this.paymentArray.get(i).getPayDate();//sets the date to the iterated date
            }
        }
        return date;//returns the date
    }//closes the getPaymentsLessonDateFromLessonID method
    
    // in: lesson id
    public String getPaymentsLessonTimeFromLessonID(int id) {//creates a method to get the time form the id
        String time = "";//creates a string for the time
        for (int i = 0; i < this.paymentArray.size(); i++) {//iterates through the payments
            if (this.paymentArray.get(i).getLessonID() == id) {//chec is the iterated and reference ids match
                time = this.paymentArray.get(i).getPayTime();//sets the time to the iterated time
            }
        }
        return time;//returns the time
    }//closes the getPaymentsLessonTimeFromLessonID method
    
    // in: lesson id
    public boolean getPaidFromID(int id) {//creates a method that takes in a lessonID and returns whether that lesson has been paid for
        boolean paid = false;//creates a boolean indicating if the is paid or not
        for (int i = 0; i < this.paymentArray.size(); i++) {//iterates through the payments
            if (this.paymentArray.get(i).getLessonID() == id) {//chec is the iterated and reference ids match
                paid = this.paymentArray.get(i).isPaid();//sets the paid boolean to the iterated paid boolean
            }
        }
        return paid;//returns the paid boolean
    }//closes the getPaidFromID method
        
    public void deletePastAndunpaidPayments() {//creates a method to delete past payments and upaid payments
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        ConnectDB db = new ConnectDB();//creates an object for the Connect DB class
        DateFormat sdf = new SimpleDateFormat("yyy/dd/MM HH:mm");//creates a simple date formatter
        Calendar today = Calendar.getInstance();//creates a calendar object for today
        Calendar ref = Calendar.getInstance();//creates a calendar object for the iterated date
        today.setTime(new Date());//sets the time of the today object to the currebt date
        
        for (int i = 0; i < this.paymentArray.size(); i++) {//iterates through the payments
            int id = this.paymentArray.get(i).getLessonID();//creates an integer for the iterated id
            try {
                ref.setTime(sdf.parse(this.getPaymentsLessonDateFromLessonID(id) + " " + this.getPaymentsLessonTimeFromLessonID(id)));//sets the ref calendar object to the iterated date
            } catch (ParseException ex) {
                Logger.getLogger(paymentsArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error setting the date
            }
            if (ref.before(today) && !this.getIfPaidFromLessonID(id)) {//checks if the lesosn is in the past and that is is unpaid
                String delete = "DELETE * FROM sPayTable WHERE lessonID = " + id;//creates a string for the SQL query used to delete the payments
                try {
                    db.UpdateDatabase(delete);//deletes the payment
                } catch (SQLException ex) {
                    Logger.getLogger(paymentsArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error deleting the payment
                }
            }
        }
    }
    
    public void deletePast() {//creates a method to delete past payments
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        ConnectDB db = new ConnectDB();//creates an object for the Connect DB class
        DateFormat sdf = new SimpleDateFormat("yyy/dd/MM HH:mm");//creates a simple date formatter
        Calendar today = Calendar.getInstance();//creates a calendar object for today
        Calendar ref = Calendar.getInstance();//creates a calendar object for the iterated date
        today.setTime(new Date());//sets the time of the today object to the currebt date
        
        for (int i = 0; i < this.paymentArray.size(); i++) {//iterates through the payments
            int id = this.paymentArray.get(i).getLessonID();//creates an integer for the iterated id
            try {
                ref.setTime(sdf.parse(this.getPaymentsLessonDateFromLessonID(id) + " " + this.getPaymentsLessonTimeFromLessonID(id)));//sets the ref calendar object to the iterated date
            } catch (ParseException ex) {
                Logger.getLogger(paymentsArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error setting the date
            }
            if (ref.before(today)) {//checks if the lesosn is in the past
                String delete = "DELETE * FROM sPayTable WHERE lessonID = " + id;//creates a string for the SQL query used to delete the payments
                try {
                    db.UpdateDatabase(delete);//deletes the payment
                } catch (SQLException ex) {
                    Logger.getLogger(paymentsArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error deleteing the payment
                }
            }
        }
    }
    
    // in: date and time
    public void deletePaymentsFromDateAndTime(String date, String time) {//creates a method to delete payments from date and time
        ConnectDB db = new ConnectDB();//creates an object for the Connect DB class
        for (int i = 0; i < this.paymentArray.size(); i++) {//iterates through the payments
            if (this.paymentArray.get(i).getPayDate().equals(date) && this.paymentArray.get(i).getPayTime().equals(time)) {//checks if the date and time of the iterated match those passed in
                String deletePayments = "DELETE * FROM sPayTable WHERE lessonID = " + this.paymentArray.get(i).getLessonID();//creates a string for the SQL query used to delete the payments
                try {
                    db.UpdateDatabase(deletePayments);//deletes the payments
                } catch (SQLException ex) {
                    Logger.getLogger(keysArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error deleteing the payment
                }
            }
        }
    }//closes the deletePaymentsFromDateAndTime method

}//closes the paymentsArray class
