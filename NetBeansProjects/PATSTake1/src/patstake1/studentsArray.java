/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patstake1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
                fetchStudentDetails fsd = new fetchStudentDetails(r.getInt("StudID"), r.getInt("schoolD"), 
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
    
    public int studentIDFromName(String name) {
        String fname = "";
        String lname = "";
        int id = 0;
        for (int i = 0; i < name.length(); i++) {
            if (Character.isSpaceChar(name.charAt(i))) {
                fname = name.substring(0, i);
                lname = name.substring(i+1);
            }
        }
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
            //get motherID
            String pushMother = "INSERT INTO mothers (motherfName, motherLName, motherEmail, motherCell) VALUES('" + mfname + "', '" 
                + mlname + "', '" + memail + "', '" + mcell + "')";
            db.UpdateDatabase(pushMother);//pushes mother
            String motherID = ""+ma.getMotherID(mcell);
            //get SchoolID
            String schoolID = ""+sa.getSchoolID(school);
            
            //push student
            String pushStudent = "INSERT INTO sDetTable (fname, lName, grade, schoolD, motherID) VALUES ('" + fname 
                    + "', '" + lname + "', '" + grade + "', '" + schoolID + "', '" + motherID+ "')";
            System.out.println(pushStudent);
            db.UpdateDatabase(pushStudent);//pushes student
        } catch (SQLException ex) {
            Logger.getLogger(addStudentForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int StudentIndexFromID(int id) {
        int index = 0;
        for (int i = 0; i < this.studentArray.size(); i++) {
            if (this.studentArray.get(i).getMotherID() == id) {
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
        
        ConnectDB db = new ConnectDB();
        lessonDataArray la = new lessonDataArray();
        String deleteStudents = "";
        String deleteParent = "";
        String deleteLessons = "";
        int dialogResult = 0;
        int motherID = this.getMotherIDFromStudentName(name);
        int countMothers = 0;
        for (int i = 0; i < this.studentArray.size(); i++) {
            if (this.studentArray.get(i).getMotherID() == this.getMotherIDFromStudentName(name)) {
                System.out.println("into coutn mothers if statement");
                countMothers++;
            }
        }
        //if more than one student with same parent, than deals accordingly
        if (countMothers > 1) {
            int dialogType = JOptionPane.YES_NO_CANCEL_OPTION;
            dialogResult = JOptionPane.showConfirmDialog(null, "there is more than one student registered with the same parent:\nWould you like "
                    + "to either\n\nYES: delete the other family member student(s) along with their lessons and parent?\nNO: Just delete this student "
                    + "and their lessons while keeping the other siblings and the parent", "delete all students, mothers, and lessons?", dialogType);
            System.out.println("dialog result: " + dialogResult);
        }
            //checks the dialog result and deletes accordingly
            if (dialogResult == JOptionPane.YES_OPTION) {
                System.out.println("enetered yes option");
                deleteParent = "DELETE * FROM mothers WHERE MotherID = " + motherID;                
                //deltes parent
                try {
                    db.UpdateDatabase(deleteParent);
                } catch (SQLException ex) {
                    Logger.getLogger(studentsArray.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (int i = 0; i < this.findSiblingIDs(name).length; i++) {
                    deleteLessons = "DELETE * FROM lessonData WHERE studentID = " + this.findSiblingIDs(name)[i];
                    deleteStudents = "DELETE * FROM sDetTable WHERE StudID = " + this.findSiblingIDs(name)[i];
                    //deletes lessons
                    try {
                        db.UpdateDatabase(deleteLessons);
                    } catch (SQLException ex) {
                        Logger.getLogger(studentsArray.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
                        deleteLessons = "DELETE * FROM lessonData WHERE studentID = " + this.studentIDFromName(name);
                        deleteStudents = "DELETE * FROM sDetTable WHERE StudID = " + this.studentIDFromName(name);
                        //deletes lessons
                        db.UpdateDatabase(deleteLessons);
                        //deletes studnt
                        db.UpdateDatabase(deleteStudents);
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
                    if (ka.getKeyArray().get(i).getLessonKey().equals(keys.get(k))) {
                        keyAlreadyIn = true;
                    }
                }
                if (keyAlreadyIn == false) {
                    keys.add(ka.getKeyArray().get(i).getLessonKey());
                }
            }
        }
        String [] keysArray = keys.toArray(new String[keys.size()]);
        return keysArray;
    }
    
    public String [] lessonStringArrayFromKeyArray(int id) {
        CalendarHandler ch = new CalendarHandler();
        keysArray ka = new keysArray();
        
        String [] keys = this.lessonKeysFromStudentID(id);
        
        
        lessonDataArray la = new lessonDataArray();
        ArrayList<String> lessonsData = new ArrayList<>();
        String lessonIntro = "";
        String date = "";
        
        if (keys.length > 0) {
            String time = "";
            String venue = "";
            String students = "";
            //populates lessonsData with the data for all of the lessons on the selected date
            for (int i = 0; i < keys.length; i++) {
                
                for (int k = 0; k < la.getLessonDataArray().size(); k++) {
                    if (ka.getKeyArray().get(k).getLessonKey().equals(keys[i])) {
                        date = la.getLessonDataArray().get(i).getLessonDate();
                    }
                }
                
                if (la.getFrequencyFromKey(keys[i]).equals("once-off")) {
                    lessonIntro = "This is a once-off lesson:\n";
                    time = "Time: " + ch.timeFromLessonKey(keys[i]) + "\n";
                    venue = "Venue: " + ch.venueFromLessonKey(keys[i]) + "\n";
                    students = "Student(s): " + ch.studentsStringFromArray(ch.studentsFromLessonKey(keys[i], date, ch.StartTimeFromLessonKey(keys[i])));
                    lessonsData.add(lessonIntro + time + venue + students + "\n");
                } else {
                    if (la.getFrequencyFromKey(keys[i]).equals("weekly")) {
                        lessonIntro = "This is a weekly lesson:\n";
                        time = "Time: " + ch.timeFromLessonKey(keys[i]) + "\n";
                        venue = "Venue: " + ch.venueFromLessonKey(keys[i]) + "\n";
                        students = "Student(s): " + ch.studentsStringFromArray(ch.studentsFromLessonKey(keys[i], date, ch.StartTimeFromLessonKey(keys[i])));
                        lessonsData.add(lessonIntro + time + venue + students + "\n");
                    } else {
                        if (la.getFrequencyFromKey(keys[i]).equals("monthly")) {
                            lessonIntro = "This is a monthly lesson:\n";
                            time = "Time: " + ch.timeFromLessonKey(keys[i]) + "\n";
                            venue = "Venue: " + ch.venueFromLessonKey(keys[i]) + "\n";
                            students = "Student(s): " + ch.studentsStringFromArray(ch.studentsFromLessonKey(keys[i], date, ch.StartTimeFromLessonKey(keys[i])));
                            lessonsData.add(lessonIntro + time + venue + students + "\n");
                        }
                    }
                }
            }
        } else {
            lessonsData.add("this parent's children have no lessons booked");
        }
        String lessonsStringArray [] = lessonsData.toArray(new String[lessonsData.size()]);
        return  lessonsStringArray;   
    }
}
