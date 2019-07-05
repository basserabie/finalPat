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
public class fetchPayments {//creates a class representing a payment object
    private int lessonID, StudentID, payDuration, cost;//creates integers to hold the lessonID, studentID, payDuration (duration of lesson), and the lesson cost
    private boolean paid;//creates a boolean indicating whether the oayment has been made
    private String PayDate, PayTime;//creates strings to hold the date of the lesson, and the time of the lesson

    public fetchPayments(int lessonID, int StudentID, int payDuration, boolean paid, String PayDate, String PayTime, int cost) {//creates the constructor for the current class instatntiating a paymnet object
        this.lessonID = lessonID;//assignes the lessonID passed in to the lessonID of the object
        this.StudentID = StudentID;//assignes the studentID passed in to the studentID of the object
        this.payDuration = payDuration;//assignes the payDuration passed in to the payDuration of the object
        this.paid = paid;//assignes the paid boolean passed in to the paid boolean of the object
        this.PayDate = PayDate;//assignes the payDate passed in to the payDate of the object
        this.PayTime = PayTime;//assignes the payTime passed in to the payTime of the object
        this.cost = cost;//assignes the cost passed in to the cost of the object
    }//closes the constructor

    public int getStudentID() {//creates an method to return the studentID
        return StudentID;//returns the studentID
    }//closes the getter method

    public void setStudentID(int StudentID) {//creates an method to set the studentID
        this.StudentID = StudentID;//sets the studentID
    }//closes the setter method

    public int getPayDuration() {//creates an method to return the payDuration
        return payDuration;//returns the payDuration
    }//closes the getter method

    public void setPayDuration(int payDuration) {//creates an method to set the payDuration
        this.payDuration = payDuration;//sets the payDuration
    }//closes the setter method

    public boolean isPaid() {//creates an method to return the paid value (if the lesson has been paid for)
        return paid;//returns the state of payment
    }//closes the getter method

    public void setPaid(boolean paid) {//creates an method to set the state of the payment
        this.paid = paid;//sets the state of payment
    }//closes the setter method

    public String getPayDate() {//creates an method to return the payDate
        return PayDate;//returns the payDate
    }//closes the getter method

    public void setPayDate(String PayDate) {//creates an method to set the payDate
        this.PayDate = PayDate;//sets the payDate
    }//closes the setter method

    public String getPayTime() {//creates an method to return the payTime
        return PayTime;//returns the payTime
    }//closes the getter method

    public void setPayTime(String PayTime) {//creates an method to set the payTime
        this.PayTime = PayTime;//sets the payTime
    }//closes the setter method

    public int getCost() {//creates an method to return the cost
        return cost;//returns the cost
    }//closes the getter method

    public void setCost(int cost) {//creates an method to set the cost
        this.cost = cost;//sets the cost
    }//closes the setter method

    public int getLessonID() {//creates an method to return the lessonID
        return lessonID;//returns the lessonID
    }//closes the getter method

    public void setLessonID(int lessonID) {//creates an method to set the lessonID
        this.lessonID = lessonID;//sets the lessonID
    }//closes the setter method
 
}//closes the fetechPayments class
