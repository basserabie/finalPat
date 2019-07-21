/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtraLessonsMain;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showConfirmDialog;

/**
 *
 * @author YishaiBasserabie
 */
public class studentsArray {//creates a class to handle the students
    private ArrayList<fetchStudentDetails> studentArray = new ArrayList<>();//creates an array list for the students

    public studentsArray() {//creates the cosntructor for the current class
        ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
        ResultSet r = db.getResults("SELECT * FROM sDetTable");//creates a resultset for the students
        try {
            while(r.next()) {//iterates through the results
                fetchStudentDetails fsd = new fetchStudentDetails(r.getInt("StudID"), r.getInt("schoolID"), 
                        r.getInt("motherID"), r.getString("fName"), r.getString("lName"), r.getString("grade"));//creates a student object according the iterated result
                studentArray.add(fsd);//adds the object to the array list
            }
        } catch (SQLException ex) {
            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error in getting the results
            JOptionPane.showMessageDialog(null, "Database error, please contact administrator at 0836570642");//alerts the user to contact the admin
        }
    }//closes the cosntructor

    public ArrayList<fetchStudentDetails> getStudentArray() {//creates a method to get the array list
        return studentArray;//returns the array list
    }//closes the getStudentArray method
    
    public int schoolIDFromStudentName(String name) {//creates a method to get the school id from the student name
        int id = 0;//creates an int for the id
        for (int i = 0; i < this.studentArray.size(); i++) {//iterates through the students
            if (this.studentNameFromID(this.studentArray.get(i).getStudentID()).equals(name)) {//checks if the iterated name is the same as the one passed in
                id = this.studentArray.get(i).getSchoolID();//sets the id to the iterated id
            }
        }
        return id;//returns the id
    }//closes the schoolIDFromStudentName method
    
    public int studentIDFromName(String name) {//creates a method to get the id form the name
        int id = 0;//creates an int for the id
        String fname = name.substring(0, name.indexOf(" "));//creates a string for the first name
        String lname = name.substring(name.indexOf(" ")+1);//creates a string for the first name
        for (int i = 0; i < studentArray.size(); i++) {//iterates through the students
            if (fname.equals(studentArray.get(i).getfName()) && lname.equals(studentArray.get(i).getlName())) {//checks if the name iterated is the same as that passed in
                id = studentArray.get(i).getStudentID();//sets the id to the iterated id
            }
        }
        return id;//returns the id
    }//coses the studentIDFromName method
    
    public String studentNameFromID(int id) {//gets the student name from the id
        String name = "";//creates a string for the name
        for (int i = 0; i < this.studentArray.size(); i++) {//iterates through the students
            if (id == this.studentArray.get(i).getStudentID()) {//checks if the id iterated is the same as the one passed in
                name = this.studentArray.get(i).getfName() + " " + this.studentArray.get(i).getlName();//sets the name to the iterated name
            }
        }
        return name;//returns the name
    }//closes the studentNameFromID method
    
    public String addParentEmail(String name) {//creates a method to format the parent email according to the parent passed in
        fetchTeacher ft = new fetchTeacher();//creates an object for the fetchTeacher class
        String email = "Congratulations Mr/Mrs " + name + ", you have successfully been added to the extra lessons register\n"
                + "run by " + ft.getFname() + " " + ft.getLname() + ". If you have any questions please contact me at: " + ft.getEmail() + " or: yourextralessons@gmail.com"
                + "\nIf you would like to book a lesson for you child, please request one by specifying date, time, and student to: yourextralessons@gmail.com\n\n"
                + "I look forward to hearing from you.\nKind regards\n" + ft.getFname() + " " + ft.getLname() + ".";//creates a formatted email for the parent passed in
        return email;//returns the email string
    }//closes the addParentEmail method
    
    public void addStudent(String fname, String lname, String grade, String school, String mfname, String mlname, String memail, String mcell) {//creates a method to add a student
        try {
            ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
            mothersArray ma = new mothersArray();//creates an object for the mothersArray class
            schoolsArray sa = new schoolsArray();//creates an object for the schoolsArray class
            dashboard d = new dashboard();//creates an object for the dashboard class
            
            try {
                if (ma.getMotherIDFromMotherName(mfname + " " + mlname) == 0) {//checks if the parent is not already part of the database
                    int motherID = 1;//creates an int for the mother id
                    if (ma.getMothersArray().isEmpty()) {//checks if there are already parents in the database
                        motherID = ma.getMothersArray().get(ma.getMothersArray().size()-1).getMotherID()+1;//sets the new mother id accordingly
                        String pushMother = "INSERT INTO mothers (motherfName, motherLName, motherEmail, motherCell) VALUES('" + mfname + "', '"
                                + mlname + "', '" + memail + "', '" + mcell + "')";//creates a string for the SQL query used to add the parent
                        db.UpdateDatabase(pushMother);//adds the parent
                        sendEmail send = new sendEmail();//creates an object for the sednEmail class
                        send.send(memail, "You Have Been Added To The Extra Lesson Register!", this.addParentEmail(mlname));//sends the welcome email
                    } else {//if there are already parents
                        String pushMother = "INSERT INTO mothers (motherfName, motherLName, motherEmail, motherCell) VALUES('" + mfname + "', '"
                                + mlname + "', '" + memail + "', '" + mcell + "')";//creates a string for the SQL query used to add the parent
                        db.UpdateDatabase(pushMother);//adds the parent
                        sendEmail send = new sendEmail();//creates an object for the sednEmail class
                        send.send(memail, "You Have Been Added To The Extra Lesson Register!", this.addParentEmail(mlname));//sends the welcome email
                    }
                    String schoolID = ""+sa.getSchoolID(school);//creates a string for the school id
                    String pushStudent = "INSERT INTO sDetTable (fname, lName, grade, schoolID, motherID) VALUES ('" + fname
                            + "', '" + lname + "', '" + grade + "', '" + schoolID + "', '" + motherID+ "')";//creates a string for the SQL query used to add the student
                    db.UpdateDatabase(pushStudent);//adds the student
                }else {//if the parent is already part of the database
                    String schoolID = ""+sa.getSchoolID(school);//creates a string for the school id
                    String pushStudent = "INSERT INTO sDetTable (fname, lName, grade, schoolID, motherID) VALUES ('" + fname
                            + "', '" + lname + "', '" + grade + "', '" + schoolID + "', '" + ma.getMotherIDFromMotherName(mfname + " " + mlname) + "')";//creates a string for the SQL query used to add the student
                    db.UpdateDatabase(pushStudent);//adds the student
                }
            } catch (SQLException ex) {
                Logger.getLogger(addStudentForm.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error adding the parent and student
            }
        } catch (IOException ex) {
            Logger.getLogger(studentsArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error adding the parent and student
        }
    }//closes the addStudent method
    
    public int StudentIndexFromStudentID(int id) {//creates a method to get the index from the id
        int index = 0;//creates an int for the index
        for (int i = 0; i < this.studentArray.size(); i++) {//iterates through the students
            if (this.studentArray.get(i).getStudentID() == id) {//checks if the ietrated id is the same as the one passed in
                index = i;//sets the index to i
                break;//discontinues the loop
            }
        }
        return index;//returns the index
    }//closes the StudentIndexFromStudentID method
    
    public int StudentIndexFromMotherID(int id) {//creates a method to get the index from the mother id
        int index = 0;//creates an int for the index
        for (int i = 0; i < this.studentArray.size(); i++) {//iterates through the students
            if (this.studentArray.get(i).getMotherID() == id) {//checks if the ietrated id is the same as the one passed in
                index = i;//sets the index to i
                break;//discontinues the loop
            }
        }
        return index;//returns the index
    }//closes the StudentIndexFromMotherID method
    
    //DUPLICATE
    public int MotherIndexFromStudentID(int id) {//
        int index = 0;
        for (int i = 0; i < this.studentArray.size(); i++) {//iterates through the students
            if (this.studentArray.get(i).getStudentID() == id) {
                index = i;
            }
        }
        return index;
    }
    
//    public int getIDFromIndex(int index) {
//        int id = 0;
//        for (int i = 0; i < this.studentArray.size(); i++) {//iterates through the students
//            if (i == index) {
//                id = this.studentArray.get(i).getStudentID();
//            }
//        }
//        return id;
//    }
    
    public int getMotherIDFromStudentName(String name) {//creates a method to get the mother id from the student name
        int id = 0;//creates an int for the id
        for (int i = 0; i < this.studentArray.size(); i++) {//iterates through the students
            if (this.studentNameFromID(this.studentArray.get(i).getStudentID()).equals(name)) {//checks if the iterated name matches the name passed in
                id = this.studentArray.get(i).getMotherID();//sets the id to the iterated id
            }
        }
        return id;//returns the id
    }//closes the getMotherIDFromStudentName method
    
    public int [] findSiblingIDs(String name) {//creates a method to get an array of the passed in student's siblings
        ArrayList<Integer> siblings = new ArrayList<>();//creates an array list for the siblings
        for (int i = 0; i < this.studentArray.size(); i++) {//iterates through the students
            if (this.studentArray.get(i).getMotherID() == this.getMotherIDFromStudentName(name)) {//checks if the iteraed mother matches the passed in
                siblings.add(this.studentArray.get(i).getStudentID());//adds the student iterated id
            }
        }
        int[] siblingIDsArray = siblings.stream().mapToInt(i -> i).toArray();//streams the array list to an int array
        return siblingIDsArray;//returns the array
    }//closes the findSiblingIDs method
    
    public void deleteStudent(String name) throws SQLException {//creates a method to delete a student
        AddStudentNote add = new AddStudentNote();//creates an object for the AddStudentNote method
        
        int dialogType1 = JOptionPane.YES_NO_OPTION;//creates an int for the JOptionPane yes option
        int firstConfirmationResult = JOptionPane.showConfirmDialog(null, "Are you sure you would like to continue the deletetionProcess of the student: " + name, "delete student", dialogType1);//asks the user if they would like to continues
        if (firstConfirmationResult == dialogType1) {//checks if the user would like to continue
            ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
            lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
            String deleteStudents = "";//creates a string for the SQL query used to delete the students
            String deleteParent = "";//creates a string for the SQL query used to delete the parent
            String deleteLessons = "";//creates a string for the SQL query used to delete the lessons
            String deleteKeys = "";//creates a string for the SQL query used to delete the keys
            String deletePayments = "";//creates a string for the SQL query used to delete the payments
            int dialogResult = 0;//creates an int for the dialogue result entered by the user
            int motherID = this.getMotherIDFromStudentName(name);// create a int for the mother ID
            int countMothers = 0;// create an into just to count the students associated with the iterated parent
            for (int i = 0; i < this.studentArray.size(); i++) {//iterates through the students
                if (this.studentArray.get(i).getMotherID() == this.getMotherIDFromStudentName(name)) {// checks if the iterated parent is the same as the parent of the name of the student that is passed in
                    countMothers++;// ups the student count
                }
            }
            //if more than one student with same parent, than deals accordingly
            if (countMothers > 1) {//checks if more than one student associated with the parent
                int dialogType = JOptionPane.YES_NO_CANCEL_OPTION;//creates an int for the JOptionPane no option
                dialogResult = JOptionPane.showConfirmDialog(null, "there is more than one student registered with the same parent:\nWould you like "
                        + "to either\n\nYES: delete the other family member student(s) along with their lessons and parent?\nNO: Just delete this student "
                        + "and their lessons while keeping the other siblings and the parent", "delete student", dialogType);//asks the user if they wish to delete all info on all the siblings as well
            }
                //checks the dialog result and deletes accordingly
                if (dialogResult == JOptionPane.YES_OPTION) {//if the anwer is yes
                    add.deleteNote(name);//deletes the note for the name
                    deleteParent = "DELETE * FROM mothers WHERE MotherID = " + motherID;//configures the delete query
                    //deletes parent
                    try {
                        db.UpdateDatabase(deleteParent);//deletes the parent
                    } catch (SQLException ex) {
                        Logger.getLogger(studentsArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error deleting the parent
                    }
                    for (int i = 0; i < this.findSiblingIDs(name).length; i++) {//iterates through the parent's children
                        deleteLessons = "DELETE * FROM lessonData WHERE studentID = " + this.findSiblingIDs(name)[i];//configures the delete query
                        deleteStudents = "DELETE * FROM sDetTable WHERE StudID = " + this.findSiblingIDs(name)[i];//configures the delete query
                        deleteKeys = "DELETE * FROM lessonKeys WHERE lessonID NOT IN (SELECT LessonID FROM lessonData)";//configures the delete query
                        deletePayments= "DELETE * FROM sPaytable WHERE StudID = " + this.findSiblingIDs(name)[i];//configures the delete query
                        //deletes lessons
                        try {
                            db.UpdateDatabase(deleteLessons);//deletes the lessons
                            db.UpdateDatabase(deletePayments);//deletes the payments
                        } catch (SQLException ex) {
                            Logger.getLogger(studentsArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error deleting the lessons
                        }
                        //deletes keys
                        db.UpdateDatabase(deleteKeys);
                        //deletes students
                        try {
                            db.UpdateDatabase(deleteStudents);
                        } catch (SQLException ex) {
                            Logger.getLogger(studentsArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the user of the error deleting the students
                        }
                    }
                } else {//if the user only wishes to delete the selected student
                    int confirmDelete = JOptionPane.showConfirmDialog(null, "are you sure you want to delete student: " + name + " and all of their data?", "confirm delete", JOptionPane.YES_NO_OPTION);//asks the user to confirm
                    if (confirmDelete == JOptionPane.YES_OPTION) {//checks the user's confirmation
                        if (dialogResult == JOptionPane.NO_OPTION) {
                            add.deleteNote(name);//deletes the student's notes
                            deleteLessons = "DELETE * FROM lessonData WHERE studentID = " + this.studentIDFromName(name);//configures the delete query
                            deleteStudents = "DELETE * FROM sDetTable WHERE StudID = " + this.studentIDFromName(name);//configures the delete query
                            deleteKeys = "DELETE * FROM lessonKeys WHERE lessonID NOT IN (SELECT LessonID FROM lessonData)";//configures the delete query
                            deletePayments= "DELETE * FROM sPaytable WHERE StudID = " + this.studentIDFromName(name);//configures the delete query
                            //deletes lessons
                            db.UpdateDatabase(deleteLessons);
                            //deletes the payment information
                            db.UpdateDatabase(deletePayments);
                            //deletes keys
                            db.UpdateDatabase(deleteKeys);
                            //deletes student
                            db.UpdateDatabase(deleteStudents);
                        }
                    }
            }
        }
            
    }//closes the deleteStudent method

    public String [] lessonKeysFromStudentID(int id) {//creates a method to get the keys of the lessons of a student passed in
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        keysArray ka = new keysArray();//creates an object for the keysArray class
        boolean keyAlreadyIn = false;//creates a boolean to check if the iterated item is already in he array list
        ArrayList<String> keys = new ArrayList<>();//creates an array list for the keys
        
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {//iterates through the lessons
            if (la.getLessonDataArray().get(i).getStudentID() == id) {//checks if the students attending the iterated lesson is the same as the one passed in
                //streams array of keys to check if key already is in array
                for (int k = 0; k < keys.size(); k++) {//iterates through the keys
                    if (ka.getKeyFromLessonID(la.getLessonDataArray().get(i).getLessonID()).equals(keys.get(k))) {//checks if the iterated key is the same as the one of the iterated lesson
                       keyAlreadyIn = true;//flips keyAlreadyIn to true
                    }
                }
                if (keyAlreadyIn == false) {//checks if the keyAlreadyIn is false
                    keys.add(ka.getKeyFromLessonID(la.getLessonDataArray().get(i).getLessonID()));//adds the iterated key
                }
                keyAlreadyIn = false;//flips keyAlreadyIn to false
            }
        }
        String [] keysArray = keys.toArray(new String[keys.size()]);//streams the keys array list to a string array
        return keysArray;//returns the array
    }//closes the lessonKeysFromStudentID method
    
    public String [] lessonStringArrayFromKeyArray(int id) {//creates a method to get a string reoresentation of a lesson for the key passed in
        CalendarHandler ch = new CalendarHandler();//creates an object for the CalendarHandler class
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        la.sortArray();//sorts the lesson array
        String [] keys = this.lessonKeysFromStudentID(id);//creates a string array for the keys
        
        ArrayList<String> lessonsData = new ArrayList<>();//creates an array list for the strings of the lessons
        String lessonIntro = "";//creates a string for the lesson intro 
        String getDate = "";//creates a string for the date
        
        if (keys.length > 0) {//checks if there is more than 0 keys
            String time = "";//creates a string for the time
            String venue = "";//creates a string for the venue
            String students = "";//creates a string for the students
            String date = "";//creates a string for the date
            
            //populates lessonsData with the data for all of the lessons for the children of the slected parent
            for (int i = 0; i < keys.length; i++) {//iterates through the keys
                getDate = la.getDateFromKeyAndStudentID(keys[i], id);//sets the date to the iterated date
                switch(la.getFrequencyFromKey(keys[i])) {//opens the switch case according to the frequency
                case "once-off"://opens the once off case
                    lessonIntro = "This is a once-off lesson:\n";//sets the lessonIntro accordingly
                    break;//discontinues the current case
                case "weekly"://opens the weekly case
                    lessonIntro = "This is a weekly lesson:\n";//sets the lessonIntro accordingly
                    break;//discontinues the current case
                case "monthly"://opens the menthly case
                    lessonIntro = "This is a monthly lesson:\n";//sets the lessonIntro accordingly
                    break;//discontinues the current case
            }//closes the switchcase statement
//                if (la.getFrequencyFromKey(keys[i]).equals("once-off")) {
//                    lessonIntro = "This is a once-off lesson:\n";
//                    date = "Date: " + getDate + "\n";
//                    time = "Time: " + ch.timeFromLessonKey(keys[i]) + "\n";
//                    venue = "Venue: " + ch.venueFromLessonKey(keys[i]) + "\n";
//                    students = "Student(s): " + ch.studentsStringFromArrayForParent(ch.studentsFromLessonDateAndTime(getDate, ch.StartTimeFromLessonKey(keys[i])));
//                    lessonsData.add(lessonIntro + date + time + venue + students + "\n");
//                } else {
//                    if (la.getFrequencyFromKey(keys[i]).equals("weekly")) {
//                        lessonIntro = "This is a weekly lesson:\n";
//                        date = "Date: " + getDate + "\n";
//                        time = "Time: " + ch.timeFromLessonKey(keys[i]) + "\n";
//                        venue = "Venue: " + ch.venueFromLessonKey(keys[i]) + "\n";
//                        students = "Student(s): " + ch.studentsStringFromArrayForParent(ch.studentsFromLessonDateAndTime(getDate, ch.StartTimeFromLessonKey(keys[i])));
//                        lessonsData.add(lessonIntro + date + time + venue + students + "\n");
//                    } else {
//                        if (la.getFrequencyFromKey(keys[i]).equals("monthly")) {
//                            lessonIntro = "This is a monthly lesson:\n";
                            date = "Date: " + getDate + "\n";//formats the date
                            time = "Time: " + ch.timeFromLessonKey(keys[i]) + "\n";//formats the time
                            venue = "Venue: " + ch.venueFromLessonKey(keys[i]) + "\n";//formats the venue
                            students = "Student(s): " + ch.studentsStringFromArrayForParent(ch.studentsFromLessonDateAndTime(getDate, ch.StartTimeFromLessonKey(keys[i])));//formats the stduents
                            lessonsData.add(lessonIntro + date + time + venue + students + "\n");//formats the lesson string
                        //}
                    //}
                //}
            }
        } else {//if there are no lessons booked by 
            lessonsData.add("this parent's children have no lessons booked");//adds the no lesson message to the lessonDataArray
        }
        String lessonsStringArray [] = lessonsData.toArray(new String[lessonsData.size()]);//streams the lesson data array list to a string array
        return  lessonsStringArray;//returns the array
    }//closes the lessonStringArrayFromKeyArray method
    
    public void editStudent(String name, String school) {//creates a method to edit a student (grades update automatically)
        int yes = JOptionPane.YES_OPTION;//creates an int for the JOptionPane yes option
        int confirmResult = JOptionPane.showConfirmDialog(null, "are you sure you would like to update " + name + "'s school?", "Update School", JOptionPane.YES_NO_OPTION);//asks the user to confirm
        if (confirmResult == yes) {//if the user has confirmed
            ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
            schoolsArray sa = new schoolsArray();//creates an object for the schoolsArray class
            String updateSchool = "";//creates a string for the SQL query used to update the school
            if (!school.equals("")) {//checks if the school is not blank
                updateSchool = "UPDATE sDetTable SET schoolID = '" + sa.getSchoolIDFromName(school) + "' WHERE StudID = " + this.studentIDFromName(name);//formats the SQL query
                try {
                    db.UpdateDatabase(updateSchool);//updates the school
                } catch (SQLException ex) {
                    Logger.getLogger(mothersArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the user that there was an error updating the school
                }
            }
            JOptionPane.showMessageDialog(null, "student information updated");//alerts the user that the school was updated
        } 
    }
    
    public void updateStudentsAnnually() {//creates a method to update the grade automatically
        fetchTeacher ft = new fetchTeacher();//creates an object for the fetchTeacher class
        if (ft.signedUp) {//checks if the user has signed up
        ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
        DateFormat sdf = new SimpleDateFormat("yyy/dd/MM");//creates a date formatter
        Calendar current = Calendar.getInstance();//createsa a claendar object for the date in the database (last login)
        Calendar now = Calendar.getInstance();//creates calendar object for the current date
        try {
            current.setTime(sdf.parse(ft.getCurrentYear()));//sets the time of the current object to the date stored in the database
            now.setTime(new Date());//sets the now date object to the current date
        } catch (ParseException ex) {
            Logger.getLogger(studentsArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error setting thje dates of the objects
        }
        if (current.get(Calendar.YEAR) != now.get(Calendar.YEAR)) {//checks if the dates are in different years
            for (int i = 0; i < this.studentArray.size(); i++) {//iterates through the students
                int currentGrade = Integer.parseInt(this.studentArray.get(i).getGrade());//creates an int for the current grade
                if (currentGrade != 12) {//checks if the grade is not the last
                    String updateYear = "UPDATE sDetTable SET grade = " + (currentGrade+1);//creates a string for the SQL query used to update the grade
                    try {
                        db.UpdateDatabase(updateYear);//ups the grade
                    } catch (SQLException ex) {
                        Logger.getLogger(studentsArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user of the error updating the grades
                    }
                } else {//if the grade is the last
                    try {
                        this.deleteStudent(this.studentNameFromID(this.studentArray.get(i).getStudentID()));//asks the user if they would like to delete the student and deletes it
                    } catch (SQLException ex) {
                        Logger.getLogger(studentsArray.class.getName()).log(Level.SEVERE, null, ex);//alerts the class user that there was an error deleting the student
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "HAPPY NEW YEAR!");//instructs the user to have a happy new year!!!!!
        }
        }
    }//closes the updateStudentsAnnually method
    
    public String schoolExists() {//creates a method to check if there is a school in the database
        String temp = "";//creates a string for the message
        schoolsArray sa = new schoolsArray();//creates an object for the schoolsArray class
        if (sa.getSchoolsDataArray().isEmpty()) {//checks if there are no schools
            temp = "()Please add a school to the database before adding a student.";//adds the no school message to the temp string
        }
        return temp;//returns the temp string
    }//closes the schoolExists method
    
}//closes the studentsArray class