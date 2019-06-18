/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patstake1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author YishaiBasserabie
 */
public class populateComboBoxes {
    
    //method populates the 'today's date' label
    public String populateTodayDateLabel() {
        DateFormat sdf = new SimpleDateFormat("yyy/dd/MM");
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        String todayString = sdf.format(today.getTime());
        return todayString;
    }
    
    //methid to populate grade combo boxes
    public String [] populateGrades() {
        String grades [] = {"10", "11", "12"};
        return grades;
    }
    
    public String [] populateQuestions() {
        String [] q = {"What is your favourite holiday location?",
            "What is your favourite ice-cream flavour?",
            "What was you favourite school teachers last name",
            "What is your mothers maiden name?"};
        return q;
    }
    
    //populate lesson primary lesson search filter
    public String [] populatePrimaryFilterTpeLessonsComboBox() {
        String types [] = {"Date", "Venue", "Student"};
        return types;
    }
    
    public String [] populateStudentFilterTypeComboBox() {
        String types [] = {"Student Name", "Mother Name", "School"};
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
    
    public String populateSelectedLessonLabel(int id) {
        lessonDataArray la = new lessonDataArray();
        venueArray va = new venueArray();
        keysArray ka = new keysArray();
        String lessonData = "";
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {
            if (la.getLessonDataArray().get(i).getLessonID() == id) {
                String date = la.getLessonDataArray().get(i).getLessonDate();
                String time = la.getTimeFromLessonID(la.getLessonDataArray().get(i).getLessonID());
                String venue = va.venueNameFromID(la.getLessonDataArray().get(i).getVenueID());
                String frequency = la.getFrequencyFromKey(ka.getKeyFromLessonID(la.getLessonDataArray().get(i).getLessonID()));
                lessonData = "Date: " + date + " / Time: " + time + " / venue: " + venue + " / Freq: " + frequency;
            }
        }
        return lessonData;
    }
    
    public String [] populateSelectedLessonStudentsList(int lessonID) {
        lessonDataArray la = new lessonDataArray();
        CalendarHandler ch = new CalendarHandler();
        String date = la.getLessonDataArray().get(la.getIndexFromID(lessonID)).getLessonDate();
        String time = la.getLessonDataArray().get(la.getIndexFromID(lessonID)).getLessonTime();
        String [] students = ch.studentsFromLessonDateAndTime(date, time);
        return students;
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
        ArrayList<String> studentList = new ArrayList<>();
        for (int i = 0; i < sa.getStudentArray().size(); i++) {
             String fname = "";
             String lname = "";
             if (sa.getStudentArray().get(i).getGrade().equals(gradeSelected)) {
                 fname = sa.getStudentArray().get(i).getfName();
                 lname = sa.getStudentArray().get(i).getlName();
                 //adds item of fname and lname
                 studentList.add(fname + " " + lname);
             }
         }
        String students [] = studentList.toArray(new String[studentList.size()]);
        return students;
    }
    
    public String [] correctStudentsAccordingToSearchTextField(String gradeSelected, String nameInputted) {
        studentsArray sa = new studentsArray();
        ArrayList<String> studentList = new ArrayList<>();
        
        for (int i = 0; i < sa.getStudentArray().size(); i++) {
             String fname = "";
             String lname = "";
             if (sa.getStudentArray().get(i).getGrade().equals(gradeSelected)) {
                 fname = sa.getStudentArray().get(i).getfName();
                 lname = sa.getStudentArray().get(i).getlName();
                 //adds item of fname and lname
                 studentList.add(fname + " " + lname);
                 for (int k = 0; k < studentList.size(); k++) {
                    if (!studentList.get(k).toLowerCase().startsWith(nameInputted.toLowerCase())) {
                        studentList.remove(k);
                    }
                 }
             }
         }
        String students [] = studentList.toArray(new String[studentList.size()]);
        return students;
    }
    
    public String [] populateHourSpinner() {
        String hours [] = {"06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"};
        return hours;
    }
    
    public String [] populateMinuteComboBoxAccordingToHour(String hourSelected) {
        String minutes [] = new String[4];
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
    
    public DefaultTableModel Lessons() {
        lessonDataArray la = new lessonDataArray();
        venueArray va = new venueArray();
        studentsArray sa = new studentsArray();
        DefaultTableModel model = null;
        Object columnNames[] = {"student name", "venue", "date", "Time", "Day"};
        model = new DefaultTableModel(columnNames, 0);
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {
            int StudentID = la.getLessonDataArray().get(i).getStudentID();
            String StudentName = sa.studentNameFromID(StudentID);
            String venue = va.venueNameFromID(la.getLessonDataArray().get(i).getVenueID());
            String date = la.getLessonDataArray().get(i).getLessonDate();
            String time = la.getTimeFromLessonID(la.getLessonDataArray().get(i).getLessonID());
            String day = la.getLessonDataArray().get(i).getDay();
            model.addRow(new Object[] {StudentName, venue, date, time, day});
        }
        return model;
     }
    
    public DefaultTableModel LessonsByDate(String dateInputted) {
        lessonDataArray la = new lessonDataArray();
        venueArray va = new venueArray();
        studentsArray sa = new studentsArray();
        
        DefaultTableModel model = null;
        Object columnNames[] = {"student name", "venue", "date", "Time", "Day"};
        model = new DefaultTableModel(columnNames, 0);
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {
            int StudentID = la.getLessonDataArray().get(i).getStudentID();
            String StudentName = sa.studentNameFromID(StudentID);
            String venue = va.venueNameFromID(la.getLessonDataArray().get(i).getVenueID());
            String date = la.getLessonDataArray().get(i).getLessonDate();
            String time = la.getTimeFromLessonID(la.getLessonDataArray().get(i).getLessonID());
            String day = la.getLessonDataArray().get(i).getDay();
            if (dateInputted.equals(date)) {
                model.addRow(new Object[] {StudentName, venue, date, time, day});
            }
        }
        return model;
     }
    
    public DefaultTableModel LessonsByVenue(String venueInputted) {
        lessonDataArray la = new lessonDataArray();
        venueArray va = new venueArray();
        studentsArray sa = new studentsArray();
        
        DefaultTableModel model = null;
        Object columnNames[] = {"student name", "venue", "date", "Time", "Day"};
        model = new DefaultTableModel(columnNames, 0);
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {
            int StudentID = la.getLessonDataArray().get(i).getStudentID();
            String StudentName = sa.studentNameFromID(StudentID);
            String venue = va.venueNameFromID(la.getLessonDataArray().get(i).getVenueID());
            String date = la.getLessonDataArray().get(i).getLessonDate();
            String time = la.getTimeFromLessonID(la.getLessonDataArray().get(i).getLessonID());
            String day = la.getLessonDataArray().get(i).getDay();
            
            if (venueInputted.equals(venue)) {
                model.addRow(new Object[] {StudentName, venue, date, time, day});
            }
        }
        return model;
     }
    
    public DefaultTableModel LessonsByStudentName(String nameInputted) {
        lessonDataArray la = new lessonDataArray();
        venueArray va = new venueArray();
        studentsArray sa = new studentsArray();
        
        DefaultTableModel model = null;
        Object columnNames[] = {"student name", "venue", "date", "Time", "Day"};
        model = new DefaultTableModel(columnNames, 0);
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {
            int StudentID = la.getLessonDataArray().get(i).getStudentID();
            String StudentName = sa.studentNameFromID(StudentID);
            String venue = va.venueNameFromID(la.getLessonDataArray().get(i).getVenueID());
            String date = la.getLessonDataArray().get(i).getLessonDate();
            String time = la.getTimeFromLessonID(la.getLessonDataArray().get(i).getLessonID());
            String day = la.getLessonDataArray().get(i).getDay();
            if (StudentName.toLowerCase().startsWith(nameInputted.toLowerCase())) {
                model.addRow(new Object[] {StudentName, venue, date, time, day});
            }
        }
        return model;
     }
    
    public DefaultTableModel LessonsByParentSName(String nameInputted) {
        lessonDataArray la = new lessonDataArray();
        mothersArray ma = new mothersArray();
        venueArray va = new venueArray();
        studentsArray sa = new studentsArray();
        
        DefaultTableModel model = null;
        Object columnNames[] = {"student name", "venue", "date", "Time", "Day"};
        model = new DefaultTableModel(columnNames, 0);
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {
            int StudentID = la.getLessonDataArray().get(i).getStudentID();
            String StudentName = sa.studentNameFromID(StudentID);
            String venue = va.venueNameFromID(la.getLessonDataArray().get(i).getVenueID());
            String date = la.getLessonDataArray().get(i).getLessonDate();
            String time = la.getTimeFromLessonID(la.getLessonDataArray().get(i).getLessonID());
            String day = la.getLessonDataArray().get(i).getDay();
            String parentName = ma.getMotherNameFromStudentID(StudentID);
            if (parentName.startsWith(nameInputted.toLowerCase())) {
                model.addRow(new Object[] {StudentName, venue, date, time, day});
            }
        }
        return model;
     }
    
    public DefaultTableModel LessonsBySchool(String schoolInputted) {
        lessonDataArray la = new lessonDataArray();
        mothersArray ma = new mothersArray();
        venueArray va = new venueArray();
        schoolsArray sca = new schoolsArray();
        studentsArray sa = new studentsArray();
        
        DefaultTableModel model = null;
        Object columnNames[] = {"student name", "venue", "date", "Time", "Day"};
        model = new DefaultTableModel(columnNames, 0);
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {
            int StudentID = la.getLessonDataArray().get(i).getStudentID();
            String StudentName = sa.studentNameFromID(StudentID);
            String venue = va.venueNameFromID(la.getLessonDataArray().get(i).getVenueID());
            String date = la.getLessonDataArray().get(i).getLessonDate();
            String time = la.getTimeFromLessonID(la.getLessonDataArray().get(i).getLessonID());
            String day = la.getLessonDataArray().get(i).getDay();
            String school = sca.getSchoolFtomStudentID(StudentID);
            if (school.toLowerCase().startsWith(schoolInputted.toLowerCase())) {
                model.addRow(new Object[] {StudentName, venue, date, time, day});
            }
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
             if (ma.getStudentsFromMotherName(name).length > 1) {
                 students = "click more info for students";
             } else {
                 students = ma.getStudentsFromMotherName(name)[0];
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
             if (ma.getStudentsFromMotherName(name).length > 1) {
                 students = "click more info for students";
             } else {
                 students = ma.getStudentsFromMotherName(name)[0];
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
             if (ma.getStudentsFromMotherName(name).length > 1) {
                 students = "click more info for students";
             } else {
                 students = ma.getStudentsFromMotherName(name)[0];
             }
             for (int s = 0; s < ma.getStudentsFromMotherName(name).length; s++) {
                 if (ma.getStudentsFromMotherName(name)[s].toLowerCase().startsWith(nameInputted.toLowerCase())) {
                    model.addRow(new Object[] {name, email, cell, students});
                }
             }
         }
        return model;
    }
    
    public DefaultTableModel payments() {
         mothersArray ma = new mothersArray();
         studentsArray sa = new studentsArray();
         paymentsArray pa = new paymentsArray();
         lessonDataArray la = new lessonDataArray();
         String colour = "";
         DefaultTableModel model = null;
         Object columnNames[] = {"Student Name", "Lesson Date", "LessonTime", "Cost", "paid"};
         model = new DefaultTableModel(columnNames, 0);
         for (int i = 0; i < pa.getPaymentArray().size(); i++) {
             boolean paid = pa.getPaymentArray().get(i).isPaid();
             if (paid) {
                 colour = "black";
             } else {
                 colour = "red";
             }
             int studentID = pa.getPaymentArray().get(i).getStudentID();
             String name = "<html><font size = 3 color=\"" + colour + "\">" + sa.studentNameFromID(studentID) + "</font></html>";
             String date = "<html><font size = 3 color=\"" + colour + "\">" + pa.getPaymentArray().get(i).getPayDate() + "</font></html>";
             String time = "<html><font size = 3 color=\"" + colour + "\">" + la.getLessonTimeFromStartTimeAndDuration(pa.getPaymentArray().get(i).getPayTime(), pa.getPaymentArray().get(i).getPayDuration()) + "</font></html>";
             String cost = "<html><font size = 3 color=\"" + colour + "\">" + "R" + pa.getPaymentArray().get(i).getCost() + "</font></html>";
             String paidString = "";
             if (paid) {
                 paidString = "<html><font size = 3 color=\"" + colour + "\">" + "paid" + "</font></html>";
             } else {
                 paidString = "<html><font size = 3 color=\"" + colour + "\">" + "un-paid" + "</font></html>";
             }
             
             model.addRow(new Object[] {name, date, time, cost, paidString});
         }
        return model;
    }
    
    public DefaultTableModel paymentsByDateAndTime(String dateInputted) {
         mothersArray ma = new mothersArray();
         studentsArray sa = new studentsArray();
         paymentsArray pa = new paymentsArray();
         lessonDataArray la = new lessonDataArray();
         String colour = "";
         DefaultTableModel model = null;
         Object columnNames[] = {"Student Name", "Lesson Date", "LessonTime", "Cost", "paid"};
         model = new DefaultTableModel(columnNames, 0);
         for (int i = 0; i < pa.getPaymentArray().size(); i++) {
             boolean paid = pa.getPaymentArray().get(i).isPaid();
             if (paid) {
                 colour = "black";
             } else {
                 colour = "red";
             }
             int studentID = pa.getPaymentArray().get(i).getStudentID();
             String name = "<html><font size = 3 color=\"" + colour + "\">" + sa.studentNameFromID(studentID) + "</font></html>";
             String date = "<html><font size = 3 color=\"" + colour + "\">" + pa.getPaymentArray().get(i).getPayDate() + "</font></html>";
             String time = "<html><font size = 3 color=\"" + colour + "\">" + la.getLessonTimeFromStartTimeAndDuration(pa.getPaymentArray().get(i).getPayTime(), pa.getPaymentArray().get(i).getPayDuration()) + "</font></html>";
             String cost = "<html><font size = 3 color=\"" + colour + "\">" + "R" + pa.getPaymentArray().get(i).getCost() + "</font></html>";
             String paidString = "";
             if (paid) {
                 paidString = "<html><font size = 3 color=\"" + colour + "\">" + "paid" + "</font></html>";
             } else {
                 paidString = "<html><font size = 3 color=\"" + colour + "\">" + "un-paid" + "</font></html>";
             }
             
             if (pa.formattOutHTMLTags(date).equals(dateInputted)) {
                 model.addRow(new Object[] {name, date, time, cost, paidString});
             }
             
         }
        return model;
    }
    
    public DefaultTableModel paymentsByStudentName(String nameInputted) {
         mothersArray ma = new mothersArray();
         studentsArray sa = new studentsArray();
         paymentsArray pa = new paymentsArray();
         lessonDataArray la = new lessonDataArray();
         String colour = "";
         DefaultTableModel model = null;
         Object columnNames[] = {"Student Name", "Lesson Date", "LessonTime", "Cost", "paid"};
         model = new DefaultTableModel(columnNames, 0);
         for (int i = 0; i < pa.getPaymentArray().size(); i++) {
             boolean paid = pa.getPaymentArray().get(i).isPaid();
             if (paid) {
                 colour = "black";
             } else {
                 colour = "red";
             }
             int studentID = pa.getPaymentArray().get(i).getStudentID();
             String name = "<html><font size = 3 color=\"" + colour + "\">" + sa.studentNameFromID(studentID) + "</font></html>";
             String date = "<html><font size = 3 color=\"" + colour + "\">" + pa.getPaymentArray().get(i).getPayDate() + "</font></html>";
             String time = "<html><font size = 3 color=\"" + colour + "\">" + la.getLessonTimeFromStartTimeAndDuration(pa.getPaymentArray().get(i).getPayTime(), pa.getPaymentArray().get(i).getPayDuration()) + "</font></html>";
             String cost = "<html><font size = 3 color=\"" + colour + "\">" + "R" + pa.getPaymentArray().get(i).getCost() + "</font></html>";
             String paidString = "";
             if (paid) {
                 paidString = "<html><font size = 3 color=\"" + colour + "\">" + "paid" + "</font></html>";
             } else {
                 paidString = "<html><font size = 3 color=\"" + colour + "\">" + "un-paid" + "</font></html>";
             }
             
             if (pa.formattOutHTMLTags(name).toLowerCase().startsWith(nameInputted.toLowerCase())) {
                 model.addRow(new Object[] {name, date, time, cost, paidString});
             }
         }
        return model;
    }
    
    public DefaultTableModel paymentsPaid() {
         mothersArray ma = new mothersArray();
         studentsArray sa = new studentsArray();
         paymentsArray pa = new paymentsArray();
         lessonDataArray la = new lessonDataArray();
         DefaultTableModel model = null;
         Object columnNames[] = {"Student Name", "Lesson Date", "LessonTime", "Cost", "paid"};
         model = new DefaultTableModel(columnNames, 0);
         for (int i = 0; i < pa.getPaymentArray().size(); i++) {
             int studentID = pa.getPaymentArray().get(i).getStudentID();
             String name = sa.studentNameFromID(studentID);
             String date = pa.getPaymentArray().get(i).getPayDate();
             String time = la.getLessonTimeFromStartTimeAndDuration(pa.getPaymentArray().get(i).getPayTime(), pa.getPaymentArray().get(i).getPayDuration());
             boolean paid = pa.getPaymentArray().get(i).isPaid();
             String cost = "R" + pa.getPaymentArray().get(i).getCost();
             String paidString = "Paid";
      
             if (paid) {
                 model.addRow(new Object[] {name, date, time, cost, paidString});
             }
         }
        return model;
    }
    
    public DefaultTableModel paymentsPaidByDateAndTime(String dateInputted) {
         mothersArray ma = new mothersArray();
         studentsArray sa = new studentsArray();
         paymentsArray pa = new paymentsArray();
         lessonDataArray la = new lessonDataArray();
         DefaultTableModel model = null;
         Object columnNames[] = {"Student Name", "Lesson Date", "LessonTime", "Cost", "paid"};
         model = new DefaultTableModel(columnNames, 0);
         for (int i = 0; i < pa.getPaymentArray().size(); i++) {
             int studentID = pa.getPaymentArray().get(i).getStudentID();
             String name = sa.studentNameFromID(studentID);
             String date = pa.getPaymentArray().get(i).getPayDate();
             String time = la.getLessonTimeFromStartTimeAndDuration(pa.getPaymentArray().get(i).getPayTime(), pa.getPaymentArray().get(i).getPayDuration());
             boolean paid = pa.getPaymentArray().get(i).isPaid();
             String cost = "R" + pa.getPaymentArray().get(i).getCost();
             String paidString = "Paid";
             
             if (paid && pa.formattOutHTMLTags(date).equals(dateInputted)) {
                 model.addRow(new Object[] {name, date, time, cost, paidString});
             }
         }
        return model;
    }
    
    public DefaultTableModel paymentsPaidByStudentName(String nameInputted) {
         mothersArray ma = new mothersArray();
         studentsArray sa = new studentsArray();
         paymentsArray pa = new paymentsArray();
         lessonDataArray la = new lessonDataArray();
         DefaultTableModel model = null;
         Object columnNames[] = {"Student Name", "Lesson Date", "LessonTime", "Cost", "paid"};
         model = new DefaultTableModel(columnNames, 0);
         for (int i = 0; i < pa.getPaymentArray().size(); i++) {
             int studentID = pa.getPaymentArray().get(i).getStudentID();
             String name = sa.studentNameFromID(studentID);
             String date = pa.getPaymentArray().get(i).getPayDate();
             String time = la.getLessonTimeFromStartTimeAndDuration(pa.getPaymentArray().get(i).getPayTime(), pa.getPaymentArray().get(i).getPayDuration());
             String cost = "R" + pa.getPaymentArray().get(i).getCost();
             boolean paid = pa.getPaymentArray().get(i).isPaid();
             String paidString = "Paid";
             
             if (paid && pa.formattOutHTMLTags(name).toLowerCase().startsWith(nameInputted.toLowerCase())) {
                 model.addRow(new Object[] {name, date, time, cost, paidString});
             }
         }
        return model;
    }
    
    public DefaultTableModel paymentsNotPaid() {
         mothersArray ma = new mothersArray();
         studentsArray sa = new studentsArray();
         paymentsArray pa = new paymentsArray();
         lessonDataArray la = new lessonDataArray();
         String colour  = "red";
         DefaultTableModel model = null;
         Object columnNames[] = {"Student Name", "Lesson Date", "LessonTime", "Cost", "paid"};
         model = new DefaultTableModel(columnNames, 0);
         for (int i = 0; i < pa.getPaymentArray().size(); i++) {
             int studentID = pa.getPaymentArray().get(i).getStudentID();
             String name = "<html><font size = 3 color=\"" + colour + "\">" + sa.studentNameFromID(studentID) + "</font></html>";
             String date = "<html><font size = 3 color=\"" + colour + "\">" + pa.getPaymentArray().get(i).getPayDate() + "</font></html>";
             String time = "<html><font size = 3 color=\"" + colour + "\">" + la.getLessonTimeFromStartTimeAndDuration(pa.getPaymentArray().get(i).getPayTime(), pa.getPaymentArray().get(i).getPayDuration()) + "</font></html>";
             String cost = "<html><font size = 3 color=\"" + colour + "\">" + "R" + pa.getPaymentArray().get(i).getCost() + "</font></html>";
             boolean paid = pa.getPaymentArray().get(i).isPaid();
             String paidString = "<html><font size = 3 color=\"" + colour + "\">" + "un-paid" + "</font></html>";
             
             if (!paid) {
                 model.addRow(new Object[] {name, date, time, cost, paidString});
             }
         }
        return model;
    }
    
    public DefaultTableModel paymentsNotPaidByDateAndTime(String dateInputted) {
         mothersArray ma = new mothersArray();
         studentsArray sa = new studentsArray();
         paymentsArray pa = new paymentsArray();
         lessonDataArray la = new lessonDataArray();
         String colour  = "red";
         DefaultTableModel model = null;
         Object columnNames[] = {"Student Name", "Lesson Date", "LessonTime", "Cost", "paid"};
         model = new DefaultTableModel(columnNames, 0);
         for (int i = 0; i < pa.getPaymentArray().size(); i++) {
             int studentID = pa.getPaymentArray().get(i).getStudentID();
             String name = "<html><font size = 3 color=\"" + colour + "\">" + sa.studentNameFromID(studentID) + "</font></html>";
             String date = "<html><font size = 3 color=\"" + colour + "\">" + pa.getPaymentArray().get(i).getPayDate() + "</font></html>";
             String time = "<html><font size = 3 color=\"" + colour + "\">" + la.getLessonTimeFromStartTimeAndDuration(pa.getPaymentArray().get(i).getPayTime(), pa.getPaymentArray().get(i).getPayDuration()) + "</font></html>";
            String cost = "<html><font size = 3 color=\"" + colour + "\">" + "R" + pa.getPaymentArray().get(i).getCost() + "</font></html>";
             boolean paid = pa.getPaymentArray().get(i).isPaid();
             String paidString = "<html><font size = 3 color=\"" + colour + "\">" + "un-paid" + "</font></html>";
             
             if (!paid && pa.formattOutHTMLTags(date).equals(dateInputted)) {
                 model.addRow(new Object[] {name, date, time, cost, paidString});
             }
         }
        return model;
    }
    
    public DefaultTableModel paymentsNotPaidByStudentName(String nameInputted) {
         mothersArray ma = new mothersArray();
         studentsArray sa = new studentsArray();
         paymentsArray pa = new paymentsArray();
         lessonDataArray la = new lessonDataArray();
         String colour  = "red";
         DefaultTableModel model = null;
         Object columnNames[] = {"Student Name", "Lesson Date", "LessonTime", "Cost", "paid"};
         model = new DefaultTableModel(columnNames, 0);
         for (int i = 0; i < pa.getPaymentArray().size(); i++) {
             int studentID = pa.getPaymentArray().get(i).getStudentID();
             String name = "<html><font size = 3 color=\"" + colour + "\">" + sa.studentNameFromID(studentID) + "</font></html>";
             String date = "<html><font size = 3 color=\"" + colour + "\">" + pa.getPaymentArray().get(i).getPayDate() + "</font></html>";
             String time = "<html><font size = 3 color=\"" + colour + "\">" + la.getLessonTimeFromStartTimeAndDuration(pa.getPaymentArray().get(i).getPayTime(), pa.getPaymentArray().get(i).getPayDuration()) + "</font></html>";
             String cost = "<html><font size = 3 color=\"" + colour + "\">" + "R" + pa.getPaymentArray().get(i).getCost() + "</font></html>";
             boolean paid = pa.getPaymentArray().get(i).isPaid();
             String paidString = "<html><font size = 3 color=\"" + colour + "\">" + "un-paid" + "</font></html>";
             
             if (!paid && pa.formattOutHTMLTags(name).toLowerCase().startsWith(nameInputted.toLowerCase())) {
                 model.addRow(new Object[] {name, date, time, cost, paidString});
             }
         }
        return model;
    }
    
    public DefaultTableModel PaymentsByLesson(String dateInputted, String timeInputted1) {
        lessonDataArray la = new lessonDataArray();
        paymentsArray pa = new paymentsArray();
        studentsArray sa = new studentsArray();
        keysArray ka = new keysArray();
        
        String timeInputted;
        if (timeInputted1.indexOf(":") < 2) {
            timeInputted = "0" + timeInputted1;
        } else {
            timeInputted = timeInputted1;
        }
        
        DefaultTableModel model = null;
        Object columnNames[] = {"Student Name", "Lesson Date", "LessonTime", "Cost", "paid"};
        model = new DefaultTableModel(columnNames, 0);

         String colour = "";
         model = new DefaultTableModel(columnNames, 0);
         for (int i = 0; i < pa.getPaymentArray().size(); i++) {
             boolean paid = pa.getPaymentArray().get(i).isPaid();
             if (paid) {
                 colour = "black";
             } else {
                 colour = "red";
             }
             int studentID = pa.getPaymentArray().get(i).getStudentID();
             String name = "<html><font size = 3 color=\"" + colour + "\">" + sa.studentNameFromID(studentID) + "</font></html>";
             String date = "<html><font size = 3 color=\"" + colour + "\">" + pa.getPaymentArray().get(i).getPayDate() + "</font></html>";
             String time = "<html><font size = 3 color=\"" + colour + "\">" + la.getLessonTimeFromStartTimeAndDuration(pa.getPaymentArray().get(i).getPayTime(), pa.getPaymentArray().get(i).getPayDuration()) + "</font></html>";
             String cost = "<html><font size = 3 color=\"" + colour + "\">" + "R" + pa.getPaymentArray().get(i).getCost() + "</font></html>";
             String paidString = "";
             if (paid) {
                 paidString = "<html><font size = 3 color=\"" + colour + "\">" + "paid" + "</font></html>";
             } else {
                 paidString = "<html><font size = 3 color=\"" + colour + "\">" + "un-paid" + "</font></html>";
             }
             
             if (pa.formattOutHTMLTags(date).equals(dateInputted) && pa.formattOutHTMLTags(time).substring(0, 5).equals(timeInputted)) {
                 model.addRow(new Object[] {name, date, time, cost, paidString});
             }
         }
        return model;
    }
    
    
}
