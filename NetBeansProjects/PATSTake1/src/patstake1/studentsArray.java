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
public class studentsArray {
//    ConnectDB  db = new ConnectDB();
    dataValidation vd = new dataValidation();
    private ArrayList<fetchStudentDetails> studentArray = new ArrayList<>();

    public studentsArray() {
        ConnectDB db = new ConnectDB();
        ResultSet r = db.getResults("SELECT * FROM sDetTable");
        try {
            while(r.next()) {
                fetchStudentDetails fsd = new fetchStudentDetails(r.getInt("StudID"), r.getInt("schoolID"), 
                        r.getInt("motherID"), r.getString("fName"), r.getString("lName"), r.getString("grade"));
                studentArray.add(fsd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(lessonDataArray.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "here");
            JOptionPane.showMessageDialog(null, "Database error, please contact administrator at 0836570642");
        }
    }

    public ArrayList<fetchStudentDetails> getStudentArray() {
        return studentArray;
    }

    public void setStudentArray(ArrayList<fetchStudentDetails> studentArray) {
        this.studentArray = studentArray;
    }
    
    public int schoolIDFromStudentName(String name) {
        int id = 0;
        for (int i = 0; i < this.studentArray.size(); i++) {
            if (this.studentNameFromID(this.studentArray.get(i).getStudentID()).equals(name)) {
                id = this.studentArray.get(i).getSchoolID();
            }
        }
        return id;
    }
    
    public int studentIDFromName(String name) {
        String fname = "";
        String lname = "";
        int id = 0;
        fname = name.substring(0, name.indexOf(" "));
        lname = name.substring(name.indexOf(" ")+1);
        for (int i = 0; i < studentArray.size(); i++) {
            if (fname.equals(studentArray.get(i).getfName()) && lname.equals(studentArray.get(i).getlName())) {
                id = studentArray.get(i).getStudentID();
            }
        }
        return id;
    }
    
    public String studentNameFromID(int id) {
        String name = "";
        for (int i = 0; i < this.studentArray.size(); i++) {
            if (id == this.studentArray.get(i).getStudentID()) {
                name = this.studentArray.get(i).getfName() + " " + this.studentArray.get(i).getlName();
            }
        }
        return name;
    }
    
    public void addStudent(String fname, String lname, String grade, String school, String mfname, String mlname, String memail, String mcell) {
        ConnectDB db = new ConnectDB();
        mothersArray ma = new mothersArray();
        schoolsArray sa = new schoolsArray();
        dashboard d = new dashboard();
        
        //push
        try {
            if (ma.getMotherIDFromMotherName(mfname + " " + mlname) == 0) {
                int motherID = 1;
                if (ma.getMothersArray().size() != 0) {
                     motherID = ma.getMothersArray().get(ma.getMothersArray().size()-1).getMotherID()+1;
                    //get motherID
                    String pushMother = "INSERT INTO mothers (motherfName, motherLName, motherEmail, motherCell) VALUES('" + mfname + "', '" 
                        + mlname + "', '" + memail + "', '" + mcell + "')";
                    //pushes mother
                    db.UpdateDatabase(pushMother);
                    System.out.println(pushMother);
                } else {
                    //get motherID
                    String pushMother = "INSERT INTO mothers (motherfName, motherLName, motherEmail, motherCell) VALUES('" + mfname + "', '" 
                        + mlname + "', '" + memail + "', '" + mcell + "')";
                    //pushes mother
                    db.UpdateDatabase(pushMother);
                    System.out.println(pushMother);
                }
                //get SchoolID
                String schoolID = ""+sa.getSchoolID(school);
                //push student
                String pushStudent = "INSERT INTO sDetTable (fname, lName, grade, schoolID, motherID) VALUES ('" + fname 
                     + "', '" + lname + "', '" + grade + "', '" + schoolID + "', '" + motherID+ "')";
                System.out.println(pushStudent);
                db.UpdateDatabase(pushStudent);//pushes student
            }else {
                String schoolID = ""+sa.getSchoolID(school);
                //gets mother ID
                //push student
                String pushStudent = "INSERT INTO sDetTable (fname, lName, grade, schoolID, motherID) VALUES ('" + fname 
                     + "', '" + lname + "', '" + grade + "', '" + schoolID + "', '" + ma.getMotherIDFromMotherName(mfname + " " + mlname) + "')";
                System.out.println(pushStudent);
                db.UpdateDatabase(pushStudent);//pushes student
            } 
        } catch (SQLException ex) {
            Logger.getLogger(addStudentForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int StudentIndexFromStudentID(int id) {
        int index = 0;
        for (int i = 0; i < this.studentArray.size(); i++) {
            if (this.studentArray.get(i).getStudentID() == id) {
                index = i;
                break;
            }
        }
        return index;
    }
    
    public int StudentIndexFromMotherID(int id) {
        int index = 0;
        for (int i = 0; i < this.studentArray.size(); i++) {
            if (this.studentArray.get(i).getMotherID() == id) {
                index = i;
                break;
            }
        }
        return index;
    }
    
    public int MotherIndexFromStudentID(int id) {
        int index = 0;
        for (int i = 0; i < this.studentArray.size(); i++) {
            if (this.studentArray.get(i).getStudentID() == id) {
                index = i;
            }
        }
        return index;
    }
    
    public int getIDFromIndex(int index) {
        int id = 0;
        
        for (int i = 0; i < this.studentArray.size(); i++) {
            if (i == index) {
                id = this.studentArray.get(i).getStudentID();
            }
        }
        return id;
    }
    
    public int getMotherIDFromStudentName(String name) {
        int id = 0;
        
        for (int i = 0; i < this.studentArray.size(); i++) {
            if (this.studentNameFromID(this.getIDFromIndex(i)).equals(name)) {
                id = this.studentArray.get(i).getMotherID();
            }
        }
        return id;
    }
    
    public int [] findSiblingIDs(String name) {
        ArrayList<Integer> siblings = new ArrayList<>();
        for (int i = 0; i < this.studentArray.size(); i++) {
            if (this.studentArray.get(i).getMotherID() == this.getMotherIDFromStudentName(name)) {
                siblings.add(this.studentArray.get(i).getStudentID());
            }
        }
        int[] siblingIDsArray = siblings.stream().mapToInt(i -> i).toArray();
        return siblingIDsArray;
    }
    
    public void deleteStudent(String name) throws SQLException {
        System.out.println(name);
        AddStudentNote add = new AddStudentNote();
        
        int dialogType1 = JOptionPane.YES_NO_OPTION;
        int firstConfirmationResult = JOptionPane.showConfirmDialog(null, "Are you sure you would like to continue the deletetionProcess of the student: " + name, "delete student", dialogType1);
        if (firstConfirmationResult == dialogType1) {
            ConnectDB db = new ConnectDB();
            lessonDataArray la = new lessonDataArray();
            String deleteStudents = "";
            String deleteParent = "";
            String deleteLessons = "";
            String deleteKeys = "";
            String deletePayments = "";
            int dialogResult = 0;
            int motherID = this.getMotherIDFromStudentName(name);
            int countMothers = 0;
            for (int i = 0; i < this.studentArray.size(); i++) {
                if (this.studentArray.get(i).getMotherID() == this.getMotherIDFromStudentName(name)) {
                    System.out.println("into count mothers if statement");
                    countMothers++;
                }
            }
            //if more than one student with same parent, than deals accordingly
            if (countMothers > 1) {
                int dialogType = JOptionPane.YES_NO_CANCEL_OPTION;
                dialogResult = JOptionPane.showConfirmDialog(null, "there is more than one student registered with the same parent:\nWould you like "
                        + "to either\n\nYES: delete the other family member student(s) along with their lessons and parent?\nNO: Just delete this student "
                        + "and their lessons while keeping the other siblings and the parent", "delete student", dialogType);
                System.out.println("dialog result: " + dialogResult);
            }
                //checks the dialog result and deletes accordingly
                if (dialogResult == JOptionPane.YES_OPTION) {
                    add.deleteNote(name);
                    System.out.println("enetered yes option");
                    deleteParent = "DELETE * FROM mothers WHERE MotherID = " + motherID;                
                    //deletes parent
                    try {
                        db.UpdateDatabase(deleteParent);
                    } catch (SQLException ex) {
                        Logger.getLogger(studentsArray.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    for (int i = 0; i < this.findSiblingIDs(name).length; i++) {
                        deleteLessons = "DELETE * FROM lessonData WHERE studentID = " + this.findSiblingIDs(name)[i];
                        deleteStudents = "DELETE * FROM sDetTable WHERE StudID = " + this.findSiblingIDs(name)[i];
                        deleteKeys = "DELETE * FROM lessonKeys WHERE lessonID NOT IN (SELECT LessonID FROM lessonData)";
                        deletePayments= "DELETE * FROM sPaytable WHERE StudID = " + this.findSiblingIDs(name)[i];
                        //deletes lessons
                        try {
                            db.UpdateDatabase(deleteLessons);
                            db.UpdateDatabase(deletePayments);
                        } catch (SQLException ex) {
                            Logger.getLogger(studentsArray.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //deletes keys
                        db.UpdateDatabase(deleteKeys);
                        //deletes students
                        try {
                            db.UpdateDatabase(deleteStudents);
                        } catch (SQLException ex) {
                            Logger.getLogger(studentsArray.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    int confirmDelete = JOptionPane.showConfirmDialog(null, "are you sure you want to delete student: " + name + " and all of their data?", "confirm delete", JOptionPane.YES_NO_OPTION);
                    if (confirmDelete == JOptionPane.YES_OPTION) {
                        if (dialogResult == JOptionPane.NO_OPTION) {
                            add.deleteNote(name);
                            deleteLessons = "DELETE * FROM lessonData WHERE studentID = " + this.studentIDFromName(name);
                            deleteStudents = "DELETE * FROM sDetTable WHERE StudID = " + this.studentIDFromName(name);
                            deleteKeys = "DELETE * FROM lessonKeys WHERE lessonID NOT IN (SELECT LessonID FROM lessonData)";
                            deletePayments= "DELETE * FROM sPaytable WHERE StudID = " + this.studentIDFromName(name);
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
            
    }

    public String [] lessonKeysFromStudentID(int id) {
        lessonDataArray la = new lessonDataArray();
        keysArray ka = new keysArray();
        boolean keyAlreadyIn = false;
        ArrayList<String> keys = new ArrayList<>();
        int count1 = 0;
        
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {
            if (la.getLessonDataArray().get(i).getStudentID() == id) {
                //streams array of keys to check if key already is in array
                for (int k = 0; k < keys.size(); k++) {
                    if (ka.getKeyFromLessonID(la.getLessonDataArray().get(i).getLessonID()).equals(keys.get(k))) {
                       keyAlreadyIn = true;
                    }
                }
                if (keyAlreadyIn == false) {
                    keys.add(ka.getKeyFromLessonID(la.getLessonDataArray().get(i).getLessonID()));
                }
                keyAlreadyIn = false;
            }
        }
        String [] keysArray = keys.toArray(new String[keys.size()]);
        return keysArray;
    }
    
    public String [] lessonStringArrayFromKeyArray(int id) {
        CalendarHandler ch = new CalendarHandler();
        keysArray ka = new keysArray();
        lessonDataArray la = new lessonDataArray();
        la.sortArray();
        String [] keys = this.lessonKeysFromStudentID(id);
        
        ArrayList<String> lessonsData = new ArrayList<>();
        String lessonIntro = "";
        String getDate = "";
        
        if (keys.length > 0) {
            String time = "";
            String venue = "";
            String students = "";
            String date = "";
            //populates lessonsData with the data for all of the lessons for the children of the slected parent
            for (int i = 0; i < keys.length; i++) {
                getDate = la.getDateFromKeyAndStudentID(keys[i], id);
                if (la.getFrequencyFromKey(keys[i]).equals("once-off")) {
                    lessonIntro = "This is a once-off lesson:\n";
                    date = "Date: " + getDate + "\n";
                    time = "Time: " + ch.timeFromLessonKey(keys[i]) + "\n";
                    venue = "Venue: " + ch.venueFromLessonKey(keys[i]) + "\n";
                    students = "Student(s): " + ch.studentsStringFromArrayForParent(ch.studentsFromLessonDateAndTime(getDate, ch.StartTimeFromLessonKey(keys[i])));
                    lessonsData.add(lessonIntro + date + time + venue + students + "\n");
                } else {
                    if (la.getFrequencyFromKey(keys[i]).equals("weekly")) {
                        lessonIntro = "This is a weekly lesson:\n";
                        date = "Date: " + getDate + "\n";
                        time = "Time: " + ch.timeFromLessonKey(keys[i]) + "\n";
                        venue = "Venue: " + ch.venueFromLessonKey(keys[i]) + "\n";
                        students = "Student(s): " + ch.studentsStringFromArrayForParent(ch.studentsFromLessonDateAndTime(getDate, ch.StartTimeFromLessonKey(keys[i])));
                        lessonsData.add(lessonIntro + date + time + venue + students + "\n");
                    } else {
                        if (la.getFrequencyFromKey(keys[i]).equals("monthly")) {
                            lessonIntro = "This is a monthly lesson:\n";
                            date = "Date: " + getDate + "\n";
                            time = "Time: " + ch.timeFromLessonKey(keys[i]) + "\n";
                            venue = "Venue: " + ch.venueFromLessonKey(keys[i]) + "\n";
                            students = "Student(s): " + ch.studentsStringFromArrayForParent(ch.studentsFromLessonDateAndTime(getDate, ch.StartTimeFromLessonKey(keys[i])));
                            lessonsData.add(lessonIntro + date + time + venue + students + "\n");
                        }
                    }
                }
            }
        } else {
            lessonsData.add("this parent's children have no lessons booked");
        }
        String lessonsStringArray [] = lessonsData.toArray(new String[lessonsData.size()]);
        for (int i = 0; i < lessonsData.size(); i++) {
        }
        return  lessonsStringArray;   
    }
    
    public void editStudent(String name, String school) {
        int yes = JOptionPane.YES_OPTION;
        int confirmResult = JOptionPane.showConfirmDialog(null, "are you sure you would like to update " + name + "'s school?", "Update School", JOptionPane.YES_NO_OPTION);
        if (confirmResult == yes) {
            ConnectDB db = new ConnectDB();
            schoolsArray sa = new schoolsArray();
            int id = this.studentIDFromName(name);
            String updateSchool = "";
            //checks if the user wants to update the email
            if (!school.equals("")) {
                updateSchool = "UPDATE sDetTable SET schoolID = '" + sa.getSchoolIDFromName(school) + "' WHERE StudID = " + this.studentIDFromName(name);
                try {
                    db.UpdateDatabase(updateSchool);
                } catch (SQLException ex) {
                    System.out.println("error updating email");
                    Logger.getLogger(mothersArray.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            JOptionPane.showMessageDialog(null, "student information updated");
        } 
    }
    
    public void updateStudentsAnnually() {
        fetchTeacher ft = new fetchTeacher();
        ConnectDB db = new ConnectDB();
        DateFormat sdf = new SimpleDateFormat("yyy/dd/MM");
        Calendar current = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        try {
            current.setTime(sdf.parse(ft.getCurrentYear()));
            now.setTime(new Date());
        } catch (ParseException ex) {
            Logger.getLogger(studentsArray.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (current.get(Calendar.YEAR) != now.get(Calendar.YEAR)) {
            for (int i = 0; i < this.studentArray.size(); i++) {
                int currentGrade = Integer.parseInt(this.studentArray.get(i).getGrade());
                if (currentGrade != 12) {
                    String updateYear = "UPDATE sDetTable SET grade = " + (currentGrade+1);
                    try {
                        db.UpdateDatabase(updateYear);
                    } catch (SQLException ex) {
                        Logger.getLogger(studentsArray.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        this.deleteStudent(this.studentNameFromID(this.studentArray.get(i).getStudentID()));
                    } catch (SQLException ex) {
                        Logger.getLogger(studentsArray.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "HAPPY NEW YEAR!");
        }
    }
    
    
}
