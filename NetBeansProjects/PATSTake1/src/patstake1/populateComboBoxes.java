/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patstake1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author YishaiBasserabie
 */
public class populateComboBoxes {
    
    //methid to populate grade combo boxes
    public String [] populateGrades() {
        String grades [] = {"10", "11", "12"};
        return grades;
    }
    
    public String [] populateStudentFilterTypeComboBox() {
        String types [] = {"Student Name", "Mother Name", "Grade", "School"};
        return types;
    }
    
    public String [] populateSchoolFilterTypeComboBox() {
        String types [] = {"school name", "principle name", "student name"};
        return types;
    }
    
    //populates schools combo boxes
    public String [] populateSchools() {
        schoolsArray sa = new schoolsArray();
        String [] schools = new String [sa.getSchoolsDataArray().size()];
        for (int i = 0; i < sa.getSchoolsDataArray().size(); i++) {
            schools[i] = sa.getSchoolsDataArray().get(i).getSchoolName();
        }
        return schools;
    }
    
    public String [] populateParentFilterTypeComboBox() {
        String types [] = {"Parent Name", "Child Name"};
        return types;
    }
    
    public String [] populateVenues() {
        venueArray va = new venueArray();
        String venues [] = new String [va.getVenuesArray().size()];
        for (int i = 0; i < va.getVenuesArray().size(); i++) {
            venues[i] = va.getVenuesArray().get(i).getVenue();
        }
        return venues;
    }
    
    public String [] getStudentsFromMotherName(String name) {
        mothersArray ma = new mothersArray();
        ArrayList<String> students = new ArrayList<>();
        studentsArray sa = new studentsArray();
        for (int i = 0; i < ma.getMothersArray().size(); i++) {
            if (ma.getMotherNameFromIndex(i).equals(name)) {
                for (int k = 0; k < sa.getStudentArray().size(); k++) {
                    if (ma.getMotherNameForLessonArray(sa.getStudentArray().get(k).getMotherID()).equals(name)) {
                        students.add(sa.studentNameFromID(sa.getStudentArray().get(k).getStudentID()));
                    }
                }
            }
        }
        String studentsArray [] = students.toArray(new String[students.size()]);
        return studentsArray;
    }
    
    //populates the lessons for a given parentName
    public String [] getLessonsFromMotherName(String name) {
        mothersArray ma = new mothersArray();
        lessonDataArray la = new lessonDataArray();
        studentsArray sa = new studentsArray();
        ArrayList<String> lessons = new ArrayList<>();
        
        for (int i = 0; i < ma.getStudentsFromMotherName(name).length; i++) {
            for (int k = 0; k < sa.lessonKeysFromStudentID(sa.studentIDFromName(ma.getStudentsFromMotherName(name)[i])).length; k++) {
                lessons.add(sa.lessonStringArrayFromKeyArray(sa.studentIDFromName(ma.getStudentsFromMotherName(name)[i]))[k]);
            }     
        }
        String StringLessonDataArray [] = lessons.toArray(new String[lessons.size()]);
        return StringLessonDataArray;
    }
    
    //populates a list of lessons from a given student name
    public String [] getLessonsFromStudentName(String name) {
        studentsArray sa = new studentsArray();
        lessonDataArray la = new lessonDataArray();
        ArrayList<String> lessons = new ArrayList<>();
        
            for (int k = 0; k < sa.lessonKeysFromStudentID(sa.studentIDFromName(name)).length; k++) {
                lessons.add(sa.lessonStringArrayFromKeyArray(sa.studentIDFromName(name))[k]);
            }     
        String StringLessonDataArray [] = lessons.toArray(new String[lessons.size()]);
        return StringLessonDataArray;
    }
    
    
    //populates a corrected combo box of students according to grade
    public String [] correctStudentsAccordingToGrade(String gradeSelected) {
        studentsArray sa = new studentsArray();
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < sa.getStudentArray().size(); i++) {
            if (sa.getStudentArray().get(i).getGrade().equals(gradeSelected)) {
                count1++;
            }
        }
        String students [] = new String[count1];
        for (int i = 0; i < sa.getStudentArray().size(); i++) {
             String fname = "";
             String lname = "";
             if (sa.getStudentArray().get(i).getGrade().equals(gradeSelected)) {
                 fname = sa.getStudentArray().get(i).getfName();
                 lname = sa.getStudentArray().get(i).getlName();
                 //adds item of fname and lname
                 students[count2]  = fname + " " + lname;
                 count2++;
             }
         }
        return students;
    }
    
    public String [] populateMinuteComboBoxAccordingToHour(String hourSelected) {
        String minutes [] = new String [4];
        int min = 15;
        minutes[0] = hourSelected + ":" + "00";
        for (int i = 0; i < 3; i++) {
            minutes[i+1] = hourSelected + ":" + ""+min;
            min += 15;
        }
        return minutes;
    }
    
    public String [] populateBookingAmoountComboBox() {
        String amounts [] = {"three months", "six months", "year"};
        return amounts;
    }
    
    //populates duration spinner for lesson booking
    public String [] populateDurationSpinner() {
        String hours [] = {"1", "2", "3", "4", "5"};
        return hours;
    } 
    
    public DefaultTableModel schools() {
         schoolsArray sa = new schoolsArray();
         DefaultTableModel model = null;
         Object columnNames[] = {"school", "principal", "principal emial"};
         model = new DefaultTableModel(columnNames, 0);
         for (int i = 0; i < sa.getSchoolsDataArray().size(); i++) {
             String school = sa.getSchoolsDataArray().get(i).getSchoolName();
             String principal = sa.getSchoolsDataArray().get(i).getPFName() + " " + sa.getSchoolsDataArray().get(i).getPLName();
             String email = sa.getSchoolsDataArray().get(i).getPEmail();
             model.addRow(new Object[] {school, principal, email});
         }
        return model;
     }
    
    public DefaultTableModel schoolsByName(String name) {
         schoolsArray sa = new schoolsArray();
         DefaultTableModel model = null;
         Object columnNames[] = {"school", "principal", "principal emial"};
         model = new DefaultTableModel(columnNames, 0);
         for (int i = 0; i < sa.getSchoolsDataArray().size(); i++) {
             String school = sa.getSchoolsDataArray().get(i).getSchoolName();
             String principal = sa.getSchoolsDataArray().get(i).getPFName() + " " + sa.getSchoolsDataArray().get(i).getPLName();
             String email = sa.getSchoolsDataArray().get(i).getPEmail();
             
             if (school.toLowerCase().startsWith(name.toLowerCase())) {
                 model.addRow(new Object[] {school, principal, email});
             }
         }
        return model;
     }
    
    public DefaultTableModel schoolsByPrincipalName(String name) {
         schoolsArray sa = new schoolsArray();
         DefaultTableModel model = null;
         Object columnNames[] = {"school", "principal", "principal emial"};
         model = new DefaultTableModel(columnNames, 0);
         for (int i = 0; i < sa.getSchoolsDataArray().size(); i++) {
             String school = sa.getSchoolsDataArray().get(i).getSchoolName();
             String principal = sa.getSchoolsDataArray().get(i).getPFName() + " " + sa.getSchoolsDataArray().get(i).getPLName();
             String email = sa.getSchoolsDataArray().get(i).getPEmail();
             
             if (principal.toLowerCase().startsWith(name.toLowerCase())) {
                 model.addRow(new Object[] {school, principal, email});
             }
         }
        return model;
     }
    
    public DefaultTableModel schoolsByStudentName(String name) {
         studentsArray sta = new studentsArray();
         schoolsArray sa = new schoolsArray();
         DefaultTableModel model = null;
         Object columnNames[] = {"school", "principal", "principal emial"};
         model = new DefaultTableModel(columnNames, 0);
         for (int i = 0; i < sa.getSchoolsDataArray().size(); i++) {
             String school = sa.getSchoolsDataArray().get(i).getSchoolName();
             String principal = sa.getSchoolsDataArray().get(i).getPFName() + " " + sa.getSchoolsDataArray().get(i).getPLName();
             String email = sa.getSchoolsDataArray().get(i).getPEmail();
             
             if (sa.getSchoolNameFromID(sta.schoolIDFromStudentName(name)).toLowerCase().equals(name.toLowerCase())) {
                 model.addRow(new Object[] {school, principal, email});
             }
         }
        return model;
     }
    
    public DefaultTableModel Lessons() {
        lessonDataArray la = new lessonDataArray();
        venueArray va = new venueArray();
        studentsArray sa = new studentsArray();
        DefaultTableModel model = null;
        Object columnNames[] = {"student ID","student name", "venue", "date", "start-time", "end-time", "Day"};
        model = new DefaultTableModel(columnNames, 0);
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {
            int StudentID = la.getLessonDataArray().get(i).getStudentID();
            String StudentName = sa.studentNameFromID(StudentID);
            String venue = va.venueNameFromID(la.getLessonDataArray().get(i).getVenueID());
            String date = la.getLessonDataArray().get(i).getLessonDate();
            String startTime = la.getLessonDataArray().get(i).getLessonTime();
            String endTime = la.GetEndTimeForSpecificStudent(la.getLessonDataArray().get(i).getLessonDuration(), la.getLessonDataArray().get(i).getLessonTime(), i);
            String day = la.getLessonDataArray().get(i).getDay();
            model.addRow(new Object[] {StudentID, StudentName, venue, date, startTime, endTime, day});
        }
        return model;
     }
    
    public DefaultTableModel StudentsByMotherName(String name) {
        lessonDataArray la = new lessonDataArray();
        schoolsArray sca = new schoolsArray();
        venueArray va = new venueArray();
        mothersArray ma = new mothersArray();
        studentsArray sa = new studentsArray();
        DefaultTableModel model = null;
        Object columnNames[] = {"student name", "grade", "school", "parent name", "upcoming-lesson date", 
            "time", "venue", "Day"};
        model = new DefaultTableModel(columnNames, 0);
        
        String upcomingLessonVenue = "";
        String upcomingLessonDay = "";
        
        for (int i = 0; i < sa.getStudentArray().size(); i++) {
            
            int StudentID = sa.getStudentArray().get(i).getStudentID();
            String StudentName = sa.studentNameFromID(StudentID);
            String grade = sa.getStudentArray().get(i).getGrade();
            String school = sca.getSchoolNameFromID(sa.getStudentArray().get(i).getSchoolID());
            String motherName = ma.getMotherNameFromStudentID(StudentID);
            String upcomingLessonDate = la.upcomingDate(StudentID);
            String upcomingLessonTime = la.upcomingTime(StudentID, upcomingLessonDate);
            if (!upcomingLessonTime.equals("N/A")) {
                String testOtherFieldsAgainstTime = upcomingLessonTime.substring(0, 5);
                upcomingLessonVenue = la.upcomingVenue(StudentID, upcomingLessonDate, testOtherFieldsAgainstTime);
                upcomingLessonDay = la.upcomingDay(StudentID, upcomingLessonDate, testOtherFieldsAgainstTime);
            } else {
                upcomingLessonVenue = la.upcomingVenue(StudentID, upcomingLessonDate, upcomingLessonTime);
                upcomingLessonDay = la.upcomingDay(StudentID, upcomingLessonDate, upcomingLessonTime);
            }
            
            if (motherName.toLowerCase().startsWith(name.toLowerCase())) {
                model.addRow(new Object[] {StudentName, grade, school, motherName, upcomingLessonDate, 
                    upcomingLessonTime, upcomingLessonVenue, upcomingLessonDay});
            }
        }
            
        return model;
     }
    
    public DefaultTableModel StudentsByGrade(String inputtedGrade) {
        lessonDataArray la = new lessonDataArray();
        schoolsArray sca = new schoolsArray();
        venueArray va = new venueArray();
        mothersArray ma = new mothersArray();
        studentsArray sa = new studentsArray();
        DefaultTableModel model = null;
        Object columnNames[] = {"student name", "grade", "school", "parent name", "upcoming-lesson date", 
            "upcoming-lesson time", "upcoming-lesson venue", "upcoming-Lesson Day"};
        model = new DefaultTableModel(columnNames, 0);
        
        String upcomingLessonVenue = "";
        String upcomingLessonDay = "";
        
        for (int i = 0; i < sa.getStudentArray().size(); i++) {
            
            int StudentID = sa.getStudentArray().get(i).getStudentID();
            String StudentName = sa.studentNameFromID(StudentID);
            String grade = sa.getStudentArray().get(i).getGrade();
            String school = sca.getSchoolNameFromID(sa.getStudentArray().get(i).getSchoolID());
            String motherName = ma.getMotherNameFromStudentID(StudentID);
            String upcomingLessonDate = la.upcomingDate(StudentID);
            String upcomingLessonTime = la.upcomingTime(StudentID, upcomingLessonDate);
            if (!upcomingLessonTime.equals("N/A")) {
                String testOtherFieldsAgainstTime = upcomingLessonTime.substring(0, 5);
                upcomingLessonVenue = la.upcomingVenue(StudentID, upcomingLessonDate, testOtherFieldsAgainstTime);
                upcomingLessonDay = la.upcomingDay(StudentID, upcomingLessonDate, testOtherFieldsAgainstTime);
            } else {
                upcomingLessonVenue = la.upcomingVenue(StudentID, upcomingLessonDate, upcomingLessonTime);
                upcomingLessonDay = la.upcomingDay(StudentID, upcomingLessonDate, upcomingLessonTime);
            }
            
            if (grade.equals(inputtedGrade)) {
                model.addRow(new Object[] {StudentName, grade, school, motherName, upcomingLessonDate, 
                    upcomingLessonTime, upcomingLessonVenue, upcomingLessonDay});
            }
        }
            
        return model;
     }
    
    public DefaultTableModel StudentsBySchool(String inputtedSchool) {
        lessonDataArray la = new lessonDataArray();
        schoolsArray sca = new schoolsArray();
        venueArray va = new venueArray();
        mothersArray ma = new mothersArray();
        studentsArray sa = new studentsArray();
        DefaultTableModel model = null;
        Object columnNames[] = {"student name", "grade", "school", "parent name", "upcoming-lesson date", 
            "upcoming-lesson time", "upcoming-lesson venue", "upcoming-Lesson Day"};
        model = new DefaultTableModel(columnNames, 0);
        
        String upcomingLessonVenue = "";
        String upcomingLessonDay = "";
        
        for (int i = 0; i < sa.getStudentArray().size(); i++) {
            
            int StudentID = sa.getStudentArray().get(i).getStudentID();
            String StudentName = sa.studentNameFromID(StudentID);
            String grade = sa.getStudentArray().get(i).getGrade();
            String school = sca.getSchoolNameFromID(sa.getStudentArray().get(i).getSchoolID());
            String motherName = ma.getMotherNameFromStudentID(StudentID);
            String upcomingLessonDate = la.upcomingDate(StudentID);
            String upcomingLessonTime = la.upcomingTime(StudentID, upcomingLessonDate);
            if (!upcomingLessonTime.equals("N/A")) {
                String testOtherFieldsAgainstTime = upcomingLessonTime.substring(0, 5);
                upcomingLessonVenue = la.upcomingVenue(StudentID, upcomingLessonDate, testOtherFieldsAgainstTime);
                upcomingLessonDay = la.upcomingDay(StudentID, upcomingLessonDate, testOtherFieldsAgainstTime);
            } else {
                upcomingLessonVenue = la.upcomingVenue(StudentID, upcomingLessonDate, upcomingLessonTime);
                upcomingLessonDay = la.upcomingDay(StudentID, upcomingLessonDate, upcomingLessonTime);
            }
            
            if (school.toLowerCase().startsWith(inputtedSchool.toLowerCase())) {
                model.addRow(new Object[] {StudentName, grade, school, motherName, upcomingLessonDate, 
                    upcomingLessonTime, upcomingLessonVenue, upcomingLessonDay});
            }
        }
            
        return model;
     }
    
    public DefaultTableModel StudentsByName(String name) {
        lessonDataArray la = new lessonDataArray();
        schoolsArray sca = new schoolsArray();
        venueArray va = new venueArray();
        mothersArray ma = new mothersArray();
        studentsArray sa = new studentsArray();
        DefaultTableModel model = null;
        Object columnNames[] = {"student name", "grade", "school", "parent name", "upcoming-lesson date", 
            "upcoming-lesson time", "upcoming-lesson venue", "upcoming-Lesson Day"};
        model = new DefaultTableModel(columnNames, 0);
        
        String upcomingLessonVenue = "";
        String upcomingLessonDay = "";
        
        for (int i = 0; i < sa.getStudentArray().size(); i++) {
            
            int StudentID = sa.getStudentArray().get(i).getStudentID();
            String StudentName = sa.studentNameFromID(StudentID);
            String grade = sa.getStudentArray().get(i).getGrade();
            String school = sca.getSchoolNameFromID(sa.getStudentArray().get(i).getSchoolID());
            String motherName = ma.getMotherNameFromStudentID(StudentID);
            String upcomingLessonDate = la.upcomingDate(StudentID);
            String upcomingLessonTime = la.upcomingTime(StudentID, upcomingLessonDate);
            if (!upcomingLessonTime.equals("N/A")) {
                String testOtherFieldsAgainstTime = upcomingLessonTime.substring(0, 5);
                upcomingLessonVenue = la.upcomingVenue(StudentID, upcomingLessonDate, testOtherFieldsAgainstTime);
                upcomingLessonDay = la.upcomingDay(StudentID, upcomingLessonDate, testOtherFieldsAgainstTime);
            } else {
                upcomingLessonVenue = la.upcomingVenue(StudentID, upcomingLessonDate, upcomingLessonTime);
                upcomingLessonDay = la.upcomingDay(StudentID, upcomingLessonDate, upcomingLessonTime);
            }
            
            if (StudentName.toLowerCase().startsWith(name.toLowerCase())) {
                model.addRow(new Object[] {StudentName, grade, school, motherName, upcomingLessonDate, 
                    upcomingLessonTime, upcomingLessonVenue, upcomingLessonDay});
            }
        }
            
        return model;
     }
    
    public DefaultTableModel Students() {
        lessonDataArray la = new lessonDataArray();
        schoolsArray sca = new schoolsArray();
        venueArray va = new venueArray();
        mothersArray ma = new mothersArray();
        studentsArray sa = new studentsArray();
        DefaultTableModel model = null;
        Object columnNames[] = {"student name", "grade", "school", "parent name", "upcoming-lesson date", 
            "upcoming-lesson time", "upcoming-lesson venue", "upcoming-Lesson Day"};
        model = new DefaultTableModel(columnNames, 0);
        
        String upcomingLessonVenue = "";
        String upcomingLessonDay = "";
        
        for (int i = 0; i < sa.getStudentArray().size(); i++) {
            
            int StudentID = sa.getStudentArray().get(i).getStudentID();
            String StudentName = sa.studentNameFromID(StudentID);
            String grade = sa.getStudentArray().get(i).getGrade();
            String school = sca.getSchoolNameFromID(sa.getStudentArray().get(i).getSchoolID());
            String motherName = ma.getMotherNameFromStudentID(StudentID);
            String upcomingLessonDate = la.upcomingDate(StudentID);
            String upcomingLessonTime = la.upcomingTime(StudentID, upcomingLessonDate);
            if (!upcomingLessonTime.equals("N/A")) {
                String testOtherFieldsAgainstTime = upcomingLessonTime.substring(0, 5);
                upcomingLessonVenue = la.upcomingVenue(StudentID, upcomingLessonDate, testOtherFieldsAgainstTime);
                upcomingLessonDay = la.upcomingDay(StudentID, upcomingLessonDate, testOtherFieldsAgainstTime);
            } else {
                upcomingLessonVenue = la.upcomingVenue(StudentID, upcomingLessonDate, upcomingLessonTime);
                upcomingLessonDay = la.upcomingDay(StudentID, upcomingLessonDate, upcomingLessonTime);
            }
            
            model.addRow(new Object[] {StudentName, grade, school, motherName, upcomingLessonDate, 
                upcomingLessonTime, upcomingLessonVenue, upcomingLessonDay});
        }
        return model;
     }
    
    public DefaultTableModel StudentInfo(int id) {
        lessonDataArray la = new lessonDataArray();
        schoolsArray sca = new schoolsArray();
        venueArray va = new venueArray();
        mothersArray ma = new mothersArray();
        studentsArray sa = new studentsArray();
        DefaultTableModel model = null;
        Object columnNames[] = {"student name", "grade", "school", "parent name", "upcoming-lesson date", 
            "upcoming-lesson time", "upcoming-lesson venue", "upcoming-Lesson Day"};
        int index = sa.StudentIndexFromStudentID(id);
        model = new DefaultTableModel(columnNames, 0);
           
        String upcomingLessonVenue = "";
        String upcomingLessonDay = "";
            
            int StudentID = sa.getStudentArray().get(index).getStudentID();
            System.out.println("id: " + StudentID);
            System.out.println("index: " + index);
            String StudentName = sa.studentNameFromID(StudentID);
            String grade = sa.getStudentArray().get(index).getGrade();
            String school = sca.getSchoolNameFromID(sa.getStudentArray().get(index).getSchoolID());
            String motherName = ma.getMotherNameFromStudentID(StudentID);
            String upcomingLessonDate = la.upcomingDate(StudentID);
            String upcomingLessonTime = la.upcomingTime(StudentID, upcomingLessonDate);
            if (!upcomingLessonTime.equals("N/A")) {
                String testOtherFieldsAgainstTime = upcomingLessonTime.substring(0, 5);
                upcomingLessonVenue = la.upcomingVenue(StudentID, upcomingLessonDate, testOtherFieldsAgainstTime);
                upcomingLessonDay = la.upcomingDay(StudentID, upcomingLessonDate, testOtherFieldsAgainstTime);
            } else {
                upcomingLessonVenue = la.upcomingVenue(StudentID, upcomingLessonDate, upcomingLessonTime);
                upcomingLessonDay = la.upcomingDay(StudentID, upcomingLessonDate, upcomingLessonTime);
            }
            model.addRow(new Object[] {StudentName, grade, school, motherName, upcomingLessonDate, 
                upcomingLessonTime, upcomingLessonVenue, upcomingLessonDay});
        return model;
     }
    
    public DefaultTableModel parents() {
        mothersArray ma = new mothersArray();
         DefaultTableModel model = null;
         Object columnNames[] = {"Parent Name", "Parent Email", "Mother Cell", "Children"};
         model = new DefaultTableModel(columnNames, 0);
         for (int i = 0; i < ma.getMothersArray().size(); i++) {
             String name = ma.getMotherNameFromIndex(i);
             String email = ma.getMothersArray().get(i).getMotherEmail();
             String cell = ma.getMothersArray().get(i).getMotherCell();
             String students = "";
             for (int k = 0; k < ma.getStudentsFromMotherName(name).length; k++) {
                 if (k < ma.getStudentsFromMotherName(name).length-1) {
                     students += ma.getStudentsFromMotherName(name)[k] + ", ";
                 } else {
                     students += ma.getStudentsFromMotherName(name)[k];
                 }
             }
             
             model.addRow(new Object[] {name, email, cell, students});
         }
        return model;
    }
    
    public DefaultTableModel parentsByParentName(String nameInputted) {
        mothersArray ma = new mothersArray();
         DefaultTableModel model = null;
         Object columnNames[] = {"Parent Name", "Parent Email", "Mother Cell", "Children"};
         model = new DefaultTableModel(columnNames, 0);
         for (int i = 0; i < ma.getMothersArray().size(); i++) {
             String name = ma.getMotherNameFromIndex(i);
             String email = ma.getMothersArray().get(i).getMotherEmail();
             String cell = ma.getMothersArray().get(i).getMotherCell();
             String students = "";
             for (int k = 0; k < ma.getStudentsFromMotherName(name).length; k++) {
                 if (k < ma.getStudentsFromMotherName(name).length-1) {
                     students += ma.getStudentsFromMotherName(name)[k] + ", ";
                 } else {
                     students += ma.getStudentsFromMotherName(name)[k];
                 }
             }
             if (name.toLowerCase().startsWith(nameInputted.toLowerCase())) {
                 model.addRow(new Object[] {name, email, cell, students});
             }
         }
        return model;
    }
    
    public DefaultTableModel parentsByChildName(String nameInputted) {
        mothersArray ma = new mothersArray();
         DefaultTableModel model = null;
         Object columnNames[] = {"Parent Name", "Parent Email", "Mother Cell", "Children"};
         model = new DefaultTableModel(columnNames, 0);
         for (int i = 0; i < ma.getMothersArray().size(); i++) {
             String name = ma.getMotherNameFromIndex(i);
             String email = ma.getMothersArray().get(i).getMotherEmail();
             String cell = ma.getMothersArray().get(i).getMotherCell();
             String students = "";
             for (int k = 0; k < ma.getStudentsFromMotherName(name).length; k++) {
                 if (k < ma.getStudentsFromMotherName(name).length-1) {
                     students += ma.getStudentsFromMotherName(name)[k] + ", ";
                 } else {
                     students += ma.getStudentsFromMotherName(name)[k];
                 }
             }
             for (int s = 0; s < ma.getStudentsFromMotherName(name).length; s++) {
                 if (ma.getStudentsFromMotherName(name)[s].toLowerCase().startsWith(nameInputted.toLowerCase())) {
                    model.addRow(new Object[] {name, email, cell, students});
                }
             }
         }
        return model;
    }
    
    
}
