/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtraLessonsMain;

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
public class populateComboBoxes {//creates a class to handle to populating of components
    
    //method populates the 'today's date' label
    public String populateTodayDateLabel() {
        DateFormat sdf = new SimpleDateFormat("yyy/dd/MM");//creates a simple date formatter
        Calendar today = Calendar.getInstance();//creates a calendar instance
        today.setTime(new Date());//sets the date of the calendar object to the current date
        return sdf.format(today.getTime());//returns the current date
    }//closes the populateTodayDateLabel method
    
    //method to populate grade combo boxes
    public String [] populateGrades() {//creates a method to populate the grade combo boxes
        String grades [] = {"10", "11", "12"};//creates a string array of the grade
        return grades;///returns the grades string array
    }//closes the populateGrades method
    
    public String [] populateQuestions() {//creates a method to populate the security questions
        String [] q = {"What is your favourite holiday location?",
            "What is your favourite ice-cream flavour?",
            "What was you favourite school teachers last name",
            "What is your mothers maiden name?"};//creates an array of the questions
        return q;//returns the questions
    }//closes the populateQuestions method
    
    //populate lesson primary lesson search filter
    public String [] populatePrimaryFilterTpeLessonsComboBox() {//creates a method to populate the filter type combo box
        String types [] = {"Date", "Venue", "Student"};//creates an array of types
        return types;//returns the array
    }//closes the populatePrimaryFilterTpeLessonsComboBox method
    
    public String [] populateStudentFilterTypeComboBox() {//creates a method to populate the student filter type
        String types [] = {"Student Name", "Parent Name", "School"};//creates an array of the filter types
        return types;//returns the types
    }//closes the populateStudentFilterTypeComboBox method
    
    //populates schools combo boxes
    public String [] populateSchools() {//creates a method to populate the schools
        schoolsArray sa = new schoolsArray();//creates an object for the schoolsArray class
        String [] schools = new String [sa.getSchoolsDataArray().size()];//creates a string array for the schools
        for (int i = 0; i < sa.getSchoolsDataArray().size(); i++) {//iterates through the schools
            schools[i] = sa.getSchoolsDataArray().get(i).getSchoolName();//adds the iterated school to the schools array
        }
        return schools;//returns the schools
    }//closes the populateSchools method
    
    public String [] populateParentFilterTypeComboBox() {//creates a method to populate the filter type
        String types [] = {"Parent Name", "Child Name"};//creates string array for the filter types
        return types;//returns the types
    }//closes the populateParentFilterTypeComboBox method
    
    // in: lesson id
    public String populateSelectedLessonLabel(int id) {//creates a method to populate the lesson label
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        venueArray va = new venueArray();//creates an object for the venuesArray class
        keysArray ka = new keysArray();//creates an object for the keysArray class
        String lessonData = "";//creates a string for the lesson data
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {//iterates through the lessons
            if (la.getLessonDataArray().get(i).getLessonID() == id) {//checks if the iterated id is the same as the one passed in
                String date = la.getLessonDataArray().get(i).getLessonDate();//creates a string for the date iterated
                String time = la.getTimeFromLessonID(la.getLessonDataArray().get(i).getLessonID());//creates a string for the time iterated
                String venue = va.venueNameFromID(la.getLessonDataArray().get(i).getVenueID());//creates a string for the venue iterated
                String frequency = la.getFrequencyFromKey(ka.getKeyFromLessonID(la.getLessonDataArray().get(i).getLessonID()));//creates a string for the frequency iterated
                lessonData = "Date: " + date + " / Time: " + time + " / venue: " + venue + " / Freq: " + frequency;//formats the lesson data string
            }
        }
        return lessonData;//returns the lesson data string
    }//closes the populateSelectedLessonLabel method
    
    // in: lesson id
    public String [] populateSelectedLessonStudentsList(int lessonID) {//creates a method to populate the students list
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        CalendarHandler ch = new CalendarHandler();//creates an object for the CalendarHandler class
        String date = la.getLessonDataArray().get(la.getIndexFromID(lessonID)).getLessonDate();//creates a string for the date
        String time = la.getLessonDataArray().get(la.getIndexFromID(lessonID)).getLessonTime();//creates a string for the time
        String [] students = ch.studentsFromLessonDateAndTime(date, time);//creates a string array for the students
        return students;//returns the students
    }//closes the populateSelectedLessonStudentsList method
    
    public String [] populateVenues() {//creates a method to populate the venues
        venueArray va = new venueArray();//creates an object for the venuesArray class
        String venues [] = new String [va.getVenuesArray().size()];//creates a string array for the venues
        for (int i = 0; i < va.getVenuesArray().size(); i++) {//iterates through the venues
            venues[i] = va.getVenuesArray().get(i).getVenue();//adds the iterated venue to the array
        }
        return venues;//returns the array
    }//closes the populateVenues method
    
    // in: parent name
    public String [] getStudentsFromMotherName(String name) {//gets the students from themother name (children)
        mothersArray ma = new mothersArray();//creates an object for the mothersArray class
        ArrayList<String> students = new ArrayList<>();//creates an array list for the students
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        for (int i = 0; i < ma.getMothersArray().size(); i++) {//iterates through the parents
            if (ma.getMotherNameFromIndex(i).equals(name)) {//checks if the iterated name is the same as the one passed in
                for (int k = 0; k < sa.getStudentArray().size(); k++) {//iterates through the syudents
                    if (ma.getMotherNameForLessonArray(sa.getStudentArray().get(k).getMotherID()).equals(name)) {//checks if the iterated name is the same ast the name passed in
                        students.add(sa.studentNameFromID(sa.getStudentArray().get(k).getStudentID()));//adds the iterated student to the students array
                    }
                }
            }
        }
        String studentsArray [] = students.toArray(new String[students.size()]);//streams the array list to a string array
        return studentsArray;//returns the studentsArray
    }
    
    //populates the lessons for a given parentName in: parent name
    public String [] getLessonsFromMotherName(String name) {
        mothersArray ma = new mothersArray();//creates an object for the mothersArray class
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        ArrayList<String> lessons = new ArrayList<>();//creates an array list for the lessons
        
        for (int i = 0; i < ma.getStudentsFromMotherName(name).length; i++) {//iterates through the students associated with the parent name passed in
            for (int k = 0; k < sa.lessonKeysFromStudentID(sa.studentIDFromName(ma.getStudentsFromMotherName(name)[i])).length; k++) {//iterates through the lessons of the iterated student
                lessons.add(sa.lessonStringArrayFromKeyArray(sa.studentIDFromName(ma.getStudentsFromMotherName(name)[i]))[k]);//adds the iterated lesson to the lessons array list
            }     
        }
        String StringLessonDataArray [] = lessons.toArray(new String[lessons.size()]);//streams the array list to a string array
        return StringLessonDataArray;//returns the string array
    }//closes the getLessonsFromMotherName method
    
    //populates a list of lessons from a given student name in: student name
    public String [] getLessonsFromStudentName(String name) {
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        ArrayList<String> lessons = new ArrayList<>();//creates an array list for the lessons
        
            for (int k = 0; k < sa.lessonKeysFromStudentID(sa.studentIDFromName(name)).length; k++) {//iterates through the lessons of the iterated student
                lessons.add(sa.lessonStringArrayFromKeyArray(sa.studentIDFromName(name))[k]);//adds the iterated lesson to the lessons array list
            }     
        String StringLessonDataArray [] = lessons.toArray(new String[lessons.size()]);//streams the array list to a string array
        return StringLessonDataArray;//returns the string array
    }//closes the getLessonsFromStudentName method
    
    
    //populates a corrected combo box of students according to grade in: grade
    public String [] correctStudentsAccordingToGrade(String gradeSelected) {
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        ArrayList<String> studentList = new ArrayList<>();//creates an array list for the students
        for (int i = 0; i < sa.getStudentArray().size(); i++) {//iterates through the students
             if (sa.getStudentArray().get(i).getGrade().equals(gradeSelected)) {//checks if the iterated student is in the grade passed in
                 String fname = sa.getStudentArray().get(i).getfName();//creates a string for the first name
                 String lname = sa.getStudentArray().get(i).getlName();//creates a string for the last name
                 //adds item of fname and lname
                 studentList.add(fname + " " + lname);
             }
         }
        String students [] = studentList.toArray(new String[studentList.size()]);//streams the array list to a string array
        return students;//returns the students
    }//closes the correctStudentsAccordingToGrade method
    
    // in: grade and student name
    public String [] correctStudentsAccordingToSearchTextField(String gradeSelected, String nameInputted) {//creates a method to correct the students array according to name and grade being typed
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        ArrayList<String> studentList = new ArrayList<>();//creates an array list for the students
        
        for (int i = 0; i < sa.getStudentArray().size(); i++) {//iterates through the students
             String fname = "";//creates a string for the first name
             String lname = "";//creates a string for the last name
             if (sa.getStudentArray().get(i).getGrade().equals(gradeSelected)) {//checks if the iterated student is in the grade passed in
                 fname = sa.getStudentArray().get(i).getfName();//sets the string to the first name
                 lname = sa.getStudentArray().get(i).getlName();//sets the string to the last name
                 //adds item of fname and lname
                 studentList.add(fname + " " + lname);//adds the student to the array list
                 for (int k = 0; k < studentList.size(); k++) {//iterates through the students in the list
                    if (!studentList.get(k).toLowerCase().startsWith(nameInputted.toLowerCase())) {//checks if the iterated student does not start with the same characters as the name passed on
                        studentList.remove(k);//removes the student from the array list
                    }
                 }
             }
         }
        String students [] = studentList.toArray(new String[studentList.size()]);//streams the array list to a string array
        return students;//returns the students
    }//closes the correctStudentsAccordingToSearchTextField method
    
    public String [] populateHourSpinner() {//creates a method to populate the hours
        String hours [] = {"06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"};//creates a sn array of hours
        return hours;//returns the hours
    }//closes the populateHourSpinner method
    
    // in: hour chosen
    public String [] populateMinuteComboBoxAccordingToHour(String hourSelected) {//creates a method to populate the minutes according to the hour passed in
        String minutes [] = new String[4];//creates an array for the minutes
        int min = 15;//creates an integer for the minutes
        minutes[0] = hourSelected + ":" + "00";//sets the first minute item to 00 minutes
        for (int i = 0; i < 3; i++) {//iterates through 3
            minutes[i+1] = hourSelected + ":" + ""+min;//adds the iterated formatted minute to the array
            min += 15;//adds 15 to the minute integer
        }
        return minutes;//returns the minutes
    }//closes the populateMinuteComboBoxAccordingToHour method
    
    //populates duration spinner for lesson booking
    public String [] populateDurationSpinner() {
        String hours [] = {"1", "2", "3", "4", "5"};//creates an array of durations
        return hours;//returns the durations
    }//closes the populateDurationSpinner method
    
    public DefaultTableModel schools() {//creates a method for the schools table model
         schoolsArray sa = new schoolsArray();//creates an object for the schoolsArray class
         DefaultTableModel model = null;//instantiates a default table model
         Object columnNames[] = {"school", "principal", "principal emial"};//creates an array of the colunm names
         model = new DefaultTableModel(columnNames, 0);//sets the table model to a table model object according to the comunm names
         for (int i = 0; i < sa.getSchoolsDataArray().size(); i++) {//iterates through the schools
             String school = sa.getSchoolsDataArray().get(i).getSchoolName();//creates a string for the iterated school
             String principal = sa.getSchoolsDataArray().get(i).getPFName() + " " + sa.getSchoolsDataArray().get(i).getPLName();//creates a string for the iterated principal name
             String email = sa.getSchoolsDataArray().get(i).getPEmail();//creates a string for the iterated principal email
             model.addRow(new Object[] {school, principal, email});//adds a row to the model according to the iterated data
         }
        return model;//returns the model
     }//closes the schools method
    
    // in: school name
    public DefaultTableModel schoolsByName(String name) {//creates a method for the table model filtered by name passed in
         schoolsArray sa = new schoolsArray();//creates an object for the schoolsArray class
         DefaultTableModel model = null;//instantiates a default table model
         Object columnNames[] = {"school", "principal", "principal emial"};//creates an array of the colunm names
         model = new DefaultTableModel(columnNames, 0);//sets the table model to a table model object according to the comunm names
         for (int i = 0; i < sa.getSchoolsDataArray().size(); i++) {//iterates through the schools
             String school = sa.getSchoolsDataArray().get(i).getSchoolName();//creates a string for the iterated school
             String principal = sa.getSchoolsDataArray().get(i).getPFName() + " " + sa.getSchoolsDataArray().get(i).getPLName();//creates a string for the iterated principal name
             String email = sa.getSchoolsDataArray().get(i).getPEmail();//creates a string for the iterated principal email
             
             if (school.toLowerCase().startsWith(name.toLowerCase())) {//checks if the iterated school starts with the name passed in
                 model.addRow(new Object[] {school, principal, email});//adds a row to the model according to the iterated data
             }
         }
        return model;//returns the model
     }//closes the schoolsByName method
    
    public DefaultTableModel Lessons() {//creates a method for a table model of lessons
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        venueArray va = new venueArray();//creates an object for the venuesArray class
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        DefaultTableModel model = null;//instantiates a default table model
        Object columnNames[] = {"student name", "venue", "date", "Time", "Day"};//creates an array of the colunm names
        model = new DefaultTableModel(columnNames, 0);//sets the table model to a table model object according to the comunm names
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {//iterates through the lessons
            int StudentID = la.getLessonDataArray().get(i).getStudentID();//creates an int for the student id
            String StudentName = sa.studentNameFromID(StudentID);//creates a string for the student name
            String venue = va.venueNameFromID(la.getLessonDataArray().get(i).getVenueID());//creates a string for the venue
            String date = la.getLessonDataArray().get(i).getLessonDate();//creates a string for the date
            String time = la.getTimeFromLessonID(la.getLessonDataArray().get(i).getLessonID());//creates a string for the time
            String day = la.getLessonDataArray().get(i).getDay();//creates a string for the day
            model.addRow(new Object[] {StudentName, venue, date, time, day});//adds a row to the model according to the iterated data
        }
        return model;//returns the model
     }//closes the lessons method
    
    // in: date
    public DefaultTableModel LessonsByDate(String dateInputted) {//creates a method for a table model of lessons by date
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        venueArray va = new venueArray();//creates an object for the venuesArray class
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        
        DefaultTableModel model = null;//instantiates a default table model
        Object columnNames[] = {"student name", "venue", "date", "Time", "Day"};//creates an array of the colunm names
        model = new DefaultTableModel(columnNames, 0);//sets the table model to a table model object according to the comunm names
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {//iterates through the lessons
            int StudentID = la.getLessonDataArray().get(i).getStudentID();//creates an int for the student id
            String StudentName = sa.studentNameFromID(StudentID);//creates a string for the student name
            String venue = va.venueNameFromID(la.getLessonDataArray().get(i).getVenueID());//creates a string for the venue
            String date = la.getLessonDataArray().get(i).getLessonDate();//creates a string for the date
            String time = la.getTimeFromLessonID(la.getLessonDataArray().get(i).getLessonID());//creates a string for the date
            String day = la.getLessonDataArray().get(i).getDay();//creates a string for the day
            if (dateInputted.equals(date)) {//checks if the iterated date is the same as the one inputted
                model.addRow(new Object[] {StudentName, venue, date, time, day});//adds a row to the model according to the iterated data
            }
        }
        return model;//returns the model
     }//closes the LessonsByDate method
    
    // in: venue
    public DefaultTableModel LessonsByVenue(String venueInputted) {//creates a method for a table model of lessons by venue
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        venueArray va = new venueArray();//creates an object for the venuesArray class
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        
        DefaultTableModel model = null;//instantiates a default table model
        Object columnNames[] = {"student name", "venue", "date", "Time", "Day"};//creates an array of the colunm names
        model = new DefaultTableModel(columnNames, 0);//sets the table model to a table model object according to the comunm names
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {//iterates through the lessons
            int StudentID = la.getLessonDataArray().get(i).getStudentID();//creates an int for the student id
            String StudentName = sa.studentNameFromID(StudentID);//creates a string for the student name
            String venue = va.venueNameFromID(la.getLessonDataArray().get(i).getVenueID());//creates a string for the venue
            String date = la.getLessonDataArray().get(i).getLessonDate();//creates a string for the date
            String time = la.getTimeFromLessonID(la.getLessonDataArray().get(i).getLessonID());//creates a string for the date
            String day = la.getLessonDataArray().get(i).getDay();//creates a string for the day
            
            if (venueInputted.equals(venue)) {//checks if the iterated venue is the same as the one passed in
                model.addRow(new Object[] {StudentName, venue, date, time, day});//adds a row to the model according to the iterated data
            }
        }
        return model;//returns the model
     }//closes the LessonsByVenue method
    
    // in: student name
    public DefaultTableModel LessonsByStudentName(String nameInputted) {//creates a method for a table model of lessons by student name
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        venueArray va = new venueArray();//creates an object for the venuesArray class
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        
        DefaultTableModel model = null;//instantiates a default table model
        Object columnNames[] = {"student name", "venue", "date", "Time", "Day"};//creates an array of the colunm names
        model = new DefaultTableModel(columnNames, 0);//sets the table model to a table model object according to the comunm names
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {//iterates through the lessons
            int StudentID = la.getLessonDataArray().get(i).getStudentID();//creates an int for the student id
            String StudentName = sa.studentNameFromID(StudentID);//creates a string for the student name
            String venue = va.venueNameFromID(la.getLessonDataArray().get(i).getVenueID());//creates a string for the venue
            String date = la.getLessonDataArray().get(i).getLessonDate();//creates a string for the date
            String time = la.getTimeFromLessonID(la.getLessonDataArray().get(i).getLessonID());//creates a string for the date
            String day = la.getLessonDataArray().get(i).getDay();//creates a string for the day
            if (StudentName.toLowerCase().startsWith(nameInputted.toLowerCase())) {//checks if the iterated name is starts with the one passed in
                model.addRow(new Object[] {StudentName, venue, date, time, day});//adds a row to the model according to the iterated data
            }
        }
        return model;//returns the model
     }//closes the LessonsByStudentName method
    
    // in: parent name
    public DefaultTableModel LessonsByParentSName(String nameInputted) {//creates a method for a table model of lessons parent name
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        mothersArray ma = new mothersArray();//creates an object for the mothersArray class
        venueArray va = new venueArray();//creates an object for the venuesArray class
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        
        DefaultTableModel model = null;//instantiates a default table model
        Object columnNames[] = {"student name", "venue", "date", "Time", "Day"};//creates an array of the colunm names
        model = new DefaultTableModel(columnNames, 0);//sets the table model to a table model object according to the comunm names
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {//iterates through the lessons
            int StudentID = la.getLessonDataArray().get(i).getStudentID();//creates an int for the student id
            String StudentName = sa.studentNameFromID(StudentID);//creates a string for the student name
            String venue = va.venueNameFromID(la.getLessonDataArray().get(i).getVenueID());//creates a string for the venue
            String date = la.getLessonDataArray().get(i).getLessonDate();//creates a string for the date
            String time = la.getTimeFromLessonID(la.getLessonDataArray().get(i).getLessonID());//creates a string for the date
            String day = la.getLessonDataArray().get(i).getDay();//creates a string for the day
            String parentName = ma.getMotherNameFromStudentID(StudentID);
            if (parentName.startsWith(nameInputted.toLowerCase())) {//checks if the iterated name is starts with the one passed in
                model.addRow(new Object[] {StudentName, venue, date, time, day});//adds a row to the model according to the iterated data
            }
        }
        return model;//returns the model
     }//closes the LessonsByParentSName method
    
    // in: school name
    public DefaultTableModel LessonsBySchool(String schoolInputted) {//creates a method for a table model of lessons by school
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        mothersArray ma = new mothersArray();//creates an object for the mothersArray class
        venueArray va = new venueArray();//creates an object for the venuesArray class
        schoolsArray sca = new schoolsArray();//creates an object for the schoolsArray class
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        
        DefaultTableModel model = null;//instantiates a default table model
        Object columnNames[] = {"student name", "venue", "date", "Time", "Day"};//creates an array of the colunm names
        model = new DefaultTableModel(columnNames, 0);//sets the table model to a table model object according to the comunm names
        for (int i = 0; i < la.getLessonDataArray().size(); i++) {//iterates through the lessons
            int StudentID = la.getLessonDataArray().get(i).getStudentID();//creates an int for the student id
            String StudentName = sa.studentNameFromID(StudentID);//creates a string for the student name
            String venue = va.venueNameFromID(la.getLessonDataArray().get(i).getVenueID());//creates a string for the venue
            String date = la.getLessonDataArray().get(i).getLessonDate();//creates a string for the date
            String time = la.getTimeFromLessonID(la.getLessonDataArray().get(i).getLessonID());//creates a string for the date
            String day = la.getLessonDataArray().get(i).getDay();//creates a string for the day
            String school = sca.getSchoolFtomStudentID(StudentID);
            if (school.toLowerCase().startsWith(schoolInputted.toLowerCase())) {//checks if the iterated name is starts with the one passed in
                model.addRow(new Object[] {StudentName, venue, date, time, day});//adds a row to the model according to the iterated data
            }
        }
        return model;//returns the model
     }//closes the LessonsBySchool method
    
    // in: parent name
    public DefaultTableModel StudentsByMotherName(String name) {//creates a method to filter the table model of students by mother name
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        schoolsArray sca = new schoolsArray();//creates an object for the schoolsArray class
        venueArray va = new venueArray();//creates an object for the venuesArray class
        mothersArray ma = new mothersArray();//creates an object for the mothersArray class
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        DefaultTableModel model = null;//instantiates a default table model
        Object columnNames[] = {"student name", "grade", "school", "parent name", "upcoming-lesson date", 
            "time", "venue", "Day"};//creates an array of the colunm names
        model = new DefaultTableModel(columnNames, 0);//sets the table model to a table model object according to the comunm names
        
        String upcomingLessonVenue = "";//creates a string for the upcoming lesson venue
        String upcomingLessonDay = "";//creates a string for the upcoming lesson day
        
        for (int i = 0; i < sa.getStudentArray().size(); i++) {//iterates through the students
            
            int StudentID = sa.getStudentArray().get(i).getStudentID();//creates an int for the iterated student id
            String StudentName = sa.studentNameFromID(StudentID);//creates a string for the student name
            String grade = sa.getStudentArray().get(i).getGrade();//creates an int for the iterated student grade
            String school = sca.getSchoolNameFromID(sa.getStudentArray().get(i).getSchoolID());//creates an int for the iterated student school
            String motherName = ma.getMotherNameFromStudentID(StudentID);//creates an int for the iterated student parent name
            String upcomingLessonDate = la.upcomingDate(StudentID);//creates an int for the iterated student upcoming lesson date
            String upcomingLessonTime = la.upcomingTime(StudentID, upcomingLessonDate);//creates an int for the iterated student upcoming lesson time
            if (!upcomingLessonTime.equals("N/A")) {//checks if the student has an upcoming lesson
                String testOtherFieldsAgainstTime = upcomingLessonTime.substring(0, 5);//creates a string for getting the other lesson data against the time
                upcomingLessonVenue = la.upcomingVenue(StudentID, upcomingLessonDate, testOtherFieldsAgainstTime);//sets the upcoming lesson venue to the gotten venue
                upcomingLessonDay = la.upcomingDay(StudentID, upcomingLessonDate, testOtherFieldsAgainstTime);//sets the upcoming lesson day to the gotten day
            } else {//if the student has no lessons
                upcomingLessonVenue = la.upcomingVenue(StudentID, upcomingLessonDate, upcomingLessonTime);//sets the venue accordingly
                upcomingLessonDay = la.upcomingDay(StudentID, upcomingLessonDate, upcomingLessonTime);//sets the day accordingly
            }
            
            if (motherName.toLowerCase().startsWith(name.toLowerCase())) {//checks if the iterated name starts woth the one passed in
                model.addRow(new Object[] {StudentName, grade, school, motherName, upcomingLessonDate, 
                    upcomingLessonTime, upcomingLessonVenue, upcomingLessonDay});//adds a row to the model according to the iterated data
            }
        }
            
        return model;//returns the model
     }//closes the StudentsByMotherName model
    
    // in: school name
    public DefaultTableModel StudentsBySchool(String inputtedSchool) {//creates a method to filter the table model of students by school
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        schoolsArray sca = new schoolsArray();//creates an object for the schoolsArray class
        venueArray va = new venueArray();//creates an object for the venuesArray class
        mothersArray ma = new mothersArray();//creates an object for the mothersArray class
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        DefaultTableModel model = null;//instantiates a default table model
        Object columnNames[] = {"student name", "grade", "school", "parent name", "upcoming-lesson date", 
            "upcoming-lesson time", "upcoming-lesson venue", "upcoming-Lesson Day"};//creates an array of the colunm names
        model = new DefaultTableModel(columnNames, 0);//sets the table model to a table model object according to the comunm names
        
        String upcomingLessonVenue = "";//creates a string for the upcoming lesson venue
        String upcomingLessonDay = "";//creates a string for the upcoming lesson day
        
        for (int i = 0; i < sa.getStudentArray().size(); i++) {//iterates through the students
            
            int StudentID = sa.getStudentArray().get(i).getStudentID();//creates an int for the iterated student id
            String StudentName = sa.studentNameFromID(StudentID);//creates a string for the student name
            String grade = sa.getStudentArray().get(i).getGrade();//creates an int for the iterated student grade
            String school = sca.getSchoolNameFromID(sa.getStudentArray().get(i).getSchoolID());//creates an int for the iterated student school
            String motherName = ma.getMotherNameFromStudentID(StudentID);//creates an int for the iterated student parent name
            String upcomingLessonDate = la.upcomingDate(StudentID);//creates an int for the iterated student upcoming lesson date
            String upcomingLessonTime = la.upcomingTime(StudentID, upcomingLessonDate);//creates an int for the iterated student upcoming lesson time
            if (!upcomingLessonTime.equals("N/A")) {//checks if the student has an upcoming lesson
                String testOtherFieldsAgainstTime = upcomingLessonTime.substring(0, 5);//creates a string for getting the other lesson data against the time
                upcomingLessonVenue = la.upcomingVenue(StudentID, upcomingLessonDate, testOtherFieldsAgainstTime);//sets the upcoming lesson venue to the gotten venue
                upcomingLessonDay = la.upcomingDay(StudentID, upcomingLessonDate, testOtherFieldsAgainstTime);//sets the upcoming lesson day to the gotten day
            } else {//if the student has no lessons
                upcomingLessonVenue = la.upcomingVenue(StudentID, upcomingLessonDate, upcomingLessonTime);//sets the venue accordingly
                upcomingLessonDay = la.upcomingDay(StudentID, upcomingLessonDate, upcomingLessonTime);//sets the day accordingly
            }
            
            if (school.toLowerCase().startsWith(inputtedSchool.toLowerCase())) {//checks if the iterated name starts woth the one passed in
                model.addRow(new Object[] {StudentName, grade, school, motherName, upcomingLessonDate, 
                    upcomingLessonTime, upcomingLessonVenue, upcomingLessonDay});//adds a row to the model according to the iterated data
            }
        }
            
        return model;//returns the model
     }//closes the StudentsBySchool model
    
    // in: student name
    public DefaultTableModel StudentsByName(String name) {//creates a method to filter the table model of students by student name
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        schoolsArray sca = new schoolsArray();//creates an object for the schoolsArray class
        venueArray va = new venueArray();//creates an object for the venuesArray class
        mothersArray ma = new mothersArray();//creates an object for the mothersArray class
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        DefaultTableModel model = null;//instantiates a default table model
        Object columnNames[] = {"student name", "grade", "school", "parent name", "upcoming-lesson date", 
            "upcoming-lesson time", "upcoming-lesson venue", "upcoming-Lesson Day"};//creates an array of the colunm names
        model = new DefaultTableModel(columnNames, 0);//sets the table model to a table model object according to the comunm names
        
        String upcomingLessonVenue = "";//creates a string for the upcoming lesson venue
        String upcomingLessonDay = "";//creates a string for the upcoming lesson day
        
        for (int i = 0; i < sa.getStudentArray().size(); i++) {//iterates through the students
            
            int StudentID = sa.getStudentArray().get(i).getStudentID();//creates an int for the iterated student id
            String StudentName = sa.studentNameFromID(StudentID);//creates a string for the student name
            String grade = sa.getStudentArray().get(i).getGrade();//creates an int for the iterated student grade
            String school = sca.getSchoolNameFromID(sa.getStudentArray().get(i).getSchoolID());//creates an int for the iterated student school
            String motherName = ma.getMotherNameFromStudentID(StudentID);//creates an int for the iterated student parent name
            String upcomingLessonDate = la.upcomingDate(StudentID);//creates an int for the iterated student upcoming lesson date
            String upcomingLessonTime = la.upcomingTime(StudentID, upcomingLessonDate);//creates an int for the iterated student upcoming lesson time
            if (!upcomingLessonTime.equals("N/A")) {//checks if the student has an upcoming lesson
                String testOtherFieldsAgainstTime = upcomingLessonTime.substring(0, 5);//creates a string for getting the other lesson data against the time
                upcomingLessonVenue = la.upcomingVenue(StudentID, upcomingLessonDate, testOtherFieldsAgainstTime);//sets the upcoming lesson venue to the gotten venue
                upcomingLessonDay = la.upcomingDay(StudentID, upcomingLessonDate, testOtherFieldsAgainstTime);
            } else {//if the student has no lessons
                upcomingLessonVenue = la.upcomingVenue(StudentID, upcomingLessonDate, upcomingLessonTime);//sets the venue accordingly
                upcomingLessonDay = la.upcomingDay(StudentID, upcomingLessonDate, upcomingLessonTime);//sets the day accordingly
            }
            
            if (StudentName.toLowerCase().startsWith(name.toLowerCase())) {//checks if the iterated name starts woth the one passed in
                model.addRow(new Object[] {StudentName, grade, school, motherName, upcomingLessonDate, 
                    upcomingLessonTime, upcomingLessonVenue, upcomingLessonDay});//adds a row to the model according to the iterated data
            }
        }
            
        return model;//returns the model
     }//closes the StudentsByName model
    
    public DefaultTableModel Students() {//creates a method of the table model of students
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        schoolsArray sca = new schoolsArray();//creates an object for the schoolsArray class
        venueArray va = new venueArray();//creates an object for the venuesArray class
        mothersArray ma = new mothersArray();//creates an object for the mothersArray class
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        DefaultTableModel model = null;//instantiates a default table model
        Object columnNames[] = {"student name", "grade", "school", "parent name", "upcoming-lesson date", 
            "upcoming-lesson time", "upcoming-lesson venue", "upcoming-Lesson Day"};//creates an array of the colunm names
        model = new DefaultTableModel(columnNames, 0);//sets the table model to a table model object according to the comunm names
        
        String upcomingLessonVenue = "";//creates a string for the upcoming lesson venue
        String upcomingLessonDay = "";//creates a string for the upcoming lesson day
        
        for (int i = 0; i < sa.getStudentArray().size(); i++) {//iterates through the students
            
            int StudentID = sa.getStudentArray().get(i).getStudentID();//creates an int for the iterated student id
            String StudentName = sa.studentNameFromID(StudentID);//creates a string for the student name
            String grade = sa.getStudentArray().get(i).getGrade();//creates an int for the iterated student grade
            String school = sca.getSchoolNameFromID(sa.getStudentArray().get(i).getSchoolID());//creates an int for the iterated student school
            String motherName = ma.getMotherNameFromStudentID(StudentID);//creates an int for the iterated student parent name
            String upcomingLessonDate = la.upcomingDate(StudentID);//creates an int for the iterated student upcoming lesson date
            String upcomingLessonTime = la.upcomingTime(StudentID, upcomingLessonDate);//creates an int for the iterated student upcoming lesson time
            if (!upcomingLessonTime.equals("N/A")) {//checks if the student has an upcoming lesson
                String testOtherFieldsAgainstTime = upcomingLessonTime.substring(0, 5);//creates a string for getting the other lesson data against the time
                upcomingLessonVenue = la.upcomingVenue(StudentID, upcomingLessonDate, testOtherFieldsAgainstTime);//sets the upcoming lesson venue to the gotten venue
                upcomingLessonDay = la.upcomingDay(StudentID, upcomingLessonDate, testOtherFieldsAgainstTime);//sets the upcoming lesson day to the gotten day
            } else {//if the student has no lessons
                upcomingLessonVenue = la.upcomingVenue(StudentID, upcomingLessonDate, upcomingLessonTime);//sets the venue accordingly
                upcomingLessonDay = la.upcomingDay(StudentID, upcomingLessonDate, upcomingLessonTime);//sets the day accordingly
            }
            
            model.addRow(new Object[] {StudentName, grade, school, motherName, upcomingLessonDate, 
                upcomingLessonTime, upcomingLessonVenue, upcomingLessonDay});//adds a row to the model according to the iterated data
        }
        return model;//returns the model
     }//closes the students method
    
    // in: student id
    public DefaultTableModel StudentInfo(int id) {//creates a method for the student info table model by id
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        schoolsArray sca = new schoolsArray();//creates an object for the schoolsArray class
        venueArray va = new venueArray();//creates an object for the venuesArray class
        mothersArray ma = new mothersArray();//creates an object for the mothersArray class
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        DefaultTableModel model = null;//instantiates a default table model
        Object columnNames[] = {"student name", "grade", "school", "parent name", "upcoming-lesson date", 
            "upcoming-lesson time", "upcoming-lesson venue", "upcoming-Lesson Day"};//creates an array of the colunm names
        int index = sa.StudentIndexFromStudentID(id);//creates an int for the index of the student
        model = new DefaultTableModel(columnNames, 0);//sets the table model to a table model object according to the comunm names
           
        String upcomingLessonVenue = "";//creates a string for the upcoming lesson venue
        String upcomingLessonDay = "";//creates a string for the upcoming lesson day
            
            int StudentID = sa.getStudentArray().get(index).getStudentID();
            String StudentName = sa.studentNameFromID(StudentID);//creates a string for the student name
            String grade = sa.getStudentArray().get(index).getGrade();
            String school = sca.getSchoolNameFromID(sa.getStudentArray().get(index).getSchoolID());
            String motherName = ma.getMotherNameFromStudentID(StudentID);//creates an int for the iterated student parent name
            String upcomingLessonDate = la.upcomingDate(StudentID);//creates an int for the iterated student upcoming lesson date
            String upcomingLessonTime = la.upcomingTime(StudentID, upcomingLessonDate);//creates an int for the iterated student upcoming lesson time
            if (!upcomingLessonTime.equals("N/A")) {//checks if the student has an upcoming lesson
                String testOtherFieldsAgainstTime = upcomingLessonTime.substring(0, 5);//creates a string for getting the other lesson data against the time
                upcomingLessonVenue = la.upcomingVenue(StudentID, upcomingLessonDate, testOtherFieldsAgainstTime);//sets the upcoming lesson venue to the gotten venue
                upcomingLessonDay = la.upcomingDay(StudentID, upcomingLessonDate, testOtherFieldsAgainstTime);//sets the upcoming lesson day to the gotten day
            } else {//if the student has no lessons
                upcomingLessonVenue = la.upcomingVenue(StudentID, upcomingLessonDate, upcomingLessonTime);//sets the venue accordingly
                upcomingLessonDay = la.upcomingDay(StudentID, upcomingLessonDate, upcomingLessonTime);//sets the day accordingly
            }
            model.addRow(new Object[] {StudentName, grade, school, motherName, upcomingLessonDate, 
                upcomingLessonTime, upcomingLessonVenue, upcomingLessonDay});//adds a row to the model according to the iterated data
        return model;//returns the model
     }//closes the StudentInfo method
    
    public DefaultTableModel parents() {//creates a method for the parents table model
        mothersArray ma = new mothersArray();//creates an object for the mothersArray class
         DefaultTableModel model = null;//instantiates a default table model
         Object columnNames[] = {"Parent Name", "Parent Email", "Mother Cell", "Children"};//creates an array of the colunm names
         model = new DefaultTableModel(columnNames, 0);//sets the table model to a table model object according to the comunm names
         for (int i = 0; i < ma.getMothersArray().size(); i++) {//iterates through the parents
             String name = ma.getMotherNameFromIndex(i);//creates a string for the iterated name
             String email = ma.getMothersArray().get(i).getMotherEmail();//creates a string for the iterated email
             String cell = ma.getMothersArray().get(i).getMotherCell();//creates a string for the iterated cell
             String students = "";//creates a string for the iterated students (child(ren))
             if (ma.getStudentsFromMotherName(name).length > 1) {//checks if there is more than 1 student
                 students = "click more info for students";//adds the more info message to the students string
             } else {//if there is only 1 student
                 students = ma.getStudentsFromMotherName(name)[0];//adds the student to the students string
             }
             model.addRow(new Object[] {name, email, cell, students});//adds a row to the model according to the iterated data
         }
        return model;//returns the model
    }//closes the parents method
    
    // in: parent name
    public DefaultTableModel parentsByParentName(String nameInputted) {//creates a method for the parents table model filtered by parent name
        mothersArray ma = new mothersArray();//creates an object for the mothersArray class
         DefaultTableModel model = null;//instantiates a default table model
         Object columnNames[] = {"Parent Name", "Parent Email", "Mother Cell", "Children"};//creates an array of the colunm names
         model = new DefaultTableModel(columnNames, 0);//sets the table model to a table model object according to the comunm names
         for (int i = 0; i < ma.getMothersArray().size(); i++) {//iterates through the parents
             String name = ma.getMotherNameFromIndex(i);//creates a string for the iterated name
             String email = ma.getMothersArray().get(i).getMotherEmail();//creates a string for the iterated email
             String cell = ma.getMothersArray().get(i).getMotherCell();//creates a string for the iterated cell
             String students = "";//creates a string for the iterated students (child(ren))
             if (ma.getStudentsFromMotherName(name).length > 1) {//checks if there is more than 1 student
                 students = "click more info for students";//adds the more info message to the students string
             } else {//if there is only 1 student
                 students = ma.getStudentsFromMotherName(name)[0];//adds the student to the students string
             }
             if (name.toLowerCase().startsWith(nameInputted.toLowerCase())) {//checks if the iterated name starts with the name passed in
                 model.addRow(new Object[] {name, email, cell, students});//adds a row to the model according to the iterated data
             }
         }
        return model;//returns the model
    }//closes the parentsByParentName method
    
    // in: student name (child name)
    public DefaultTableModel parentsByChildName(String nameInputted) {//creates a method for the parents table model filtered by child name
        mothersArray ma = new mothersArray();//creates an object for the mothersArray class
         DefaultTableModel model = null;//instantiates a default table model
         Object columnNames[] = {"Parent Name", "Parent Email", "Mother Cell", "Children"};//creates an array of the colunm names
         model = new DefaultTableModel(columnNames, 0);//sets the table model to a table model object according to the comunm names
         for (int i = 0; i < ma.getMothersArray().size(); i++) {//iterates through the parents
             String name = ma.getMotherNameFromIndex(i);//creates a string for the iterated name
             String email = ma.getMothersArray().get(i).getMotherEmail();//creates a string for the iterated email
             String cell = ma.getMothersArray().get(i).getMotherCell();//creates a string for the iterated cell
             String students = "";//creates a string for the iterated students (child(ren))
             if (ma.getStudentsFromMotherName(name).length > 1) {//checks if there is more than 1 student
                 students = "click more info for students";//adds the more info message to the students string
             } else {//if there is only 1 student
                 students = ma.getStudentsFromMotherName(name)[0];//adds the student to the students string
             }
             for (int s = 0; s < ma.getStudentsFromMotherName(name).length; s++) {//iterates through the students of the parent
                 if (ma.getStudentsFromMotherName(name)[s].toLowerCase().startsWith(nameInputted.toLowerCase())) {//checks if the iterated name starts with the name passed in
                    model.addRow(new Object[] {name, email, cell, students});//adds a row to the model according to the iterated data
                }
             }
         }
        return model;//returns the model
    }//closes the parentsByChildName method
    
    public DefaultTableModel payments() {//creates a method for the payments table model
         mothersArray ma = new mothersArray();//creates an object for the mothersArray class
         studentsArray sa = new studentsArray();//creates an object for the mothersArray class);
         paymentsArray pa = new paymentsArray();//creates an object for the paymentsArray class
         lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
         String colour = "";//creates a string for the colour of the text displayed
         DefaultTableModel model = null;//instantiates a default table model
         Object columnNames[] = {"Student Name", "Lesson Date", "LessonTime", "Cost", "paid"};//creates an array of the colunm names
         model = new DefaultTableModel(columnNames, 0);//sets the table model to a table model object according to the comunm names
         for (int i = 0; i < pa.getPaymentArray().size(); i++) {//iterates through the payments
             boolean paid = pa.getPaymentArray().get(i).isPaid();//creates a boolean indicating whether the iteated payment is paid
             if (paid) {//checks if the payment is paid
                 colour = "black";//changes the colour string to black
             } else {//if the payment is not paid
                 colour = "red";//changes the colour string to red
             }
             int studentID = pa.getPaymentArray().get(i).getStudentID();//creates an int for the iterated student id
             String name = "<html><font size = 3 color=\"" + colour + "\">" + sa.studentNameFromID(studentID) + "</font></html>";//creates an int for the iterated student name
             String date = "<html><font size = 3 color=\"" + colour + "\">" + pa.getPaymentArray().get(i).getPayDate() + "</font></html>";//creates an int for the iterated date
             String time = "<html><font size = 3 color=\"" + colour + "\">" + la.getLessonTimeFromStartTimeAndDuration(pa.getPaymentArray().get(i).getPayTime(), pa.getPaymentArray().get(i).getPayDuration()) + "</font></html>";//creates an int for the iterated time
             String cost = "<html><font size = 3 color=\"" + colour + "\">" + "R" + pa.getPaymentArray().get(i).getCost() + "</font></html>";//creates an int for the iterated cost
             String paidString = "";//creates a string to display the payment state
             if (paid) {//checks if the payment is paid
                 paidString = "<html><font size = 3 color=\"" + colour + "\">" + "paid" + "</font></html>";//sets the paid string to black
             } else {//if the payment has not been made
                 paidString = "<html><font size = 3 color=\"" + colour + "\">" + "un-paid" + "</font></html>";//sets the paid string to red
             }
             
             model.addRow(new Object[] {name, date, time, cost, paidString});//adds a row to the model according to the iterated data
         }
        return model;//returns the model
    }//closes the payments method
    
    // in: date
    public DefaultTableModel paymentsByDateAndTime(String dateInputted) {//creates a method for the payments table model filtered by date and time
         mothersArray ma = new mothersArray();//creates an object for the mothersArray class
         studentsArray sa = new studentsArray();//creates an object for the studentsArray class
         paymentsArray pa = new paymentsArray();//creates an object for the paymentsArray class
         lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
         String colour = "";//creates a string for the colour of the text displayed
         DefaultTableModel model = null;//instantiates a default table model
         Object columnNames[] = {"Student Name", "Lesson Date", "LessonTime", "Cost", "paid"};//creates an array of the colunm names
         model = new DefaultTableModel(columnNames, 0);//sets the table model to a table model object according to the comunm names
         for (int i = 0; i < pa.getPaymentArray().size(); i++) {//iterates through the payments
             boolean paid = pa.getPaymentArray().get(i).isPaid();//creates a boolean indicating whether the iteated payment is paid
             if (paid) {//checks if the payment is paid
                 colour = "black";//changes the colour string to black
             } else {//if the payment is not paid
                 colour = "red";//changes the colour string to red
             }
             int studentID = pa.getPaymentArray().get(i).getStudentID();//creates an int for the iterated student id
             String name = "<html><font size = 3 color=\"" + colour + "\">" + sa.studentNameFromID(studentID) + "</font></html>";//creates an int for the iterated student name
             String date = "<html><font size = 3 color=\"" + colour + "\">" + pa.getPaymentArray().get(i).getPayDate() + "</font></html>";//creates an int for the iterated date
             String time = "<html><font size = 3 color=\"" + colour + "\">" + la.getLessonTimeFromStartTimeAndDuration(pa.getPaymentArray().get(i).getPayTime(), pa.getPaymentArray().get(i).getPayDuration()) + "</font></html>";//creates an int for the iterated time
             String cost = "<html><font size = 3 color=\"" + colour + "\">" + "R" + pa.getPaymentArray().get(i).getCost() + "</font></html>";//creates an int for the iterated cost
             String paidString = "";//creates a string to display the payment state
             if (paid) {//checks if the payment is paid
                 paidString = "<html><font size = 3 color=\"" + colour + "\">" + "paid" + "</font></html>";//sets the paid string to black
             } else {//if the payment has not been made
                 paidString = "<html><font size = 3 color=\"" + colour + "\">" + "un-paid" + "</font></html>";//sets the paid string to red
             }
             
             if (pa.formattOutHTMLTags(date).equals(dateInputted)) {//checks if the dates match
                 model.addRow(new Object[] {name, date, time, cost, paidString});//adds a row to the model according to the iterated data
             }
             
         }
        return model;//returns the model
    }//closes the paymentsByDateAndTime method
    
    // in: student name
    public DefaultTableModel paymentsByStudentName(String nameInputted) {//creates a method for the payments table model filtered by student name
         mothersArray ma = new mothersArray();//creates an object for the mothersArray class
         studentsArray sa = new studentsArray();//creates an object for the studentsArray class
         paymentsArray pa = new paymentsArray();//creates an object for the paymentsArray class
         lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
         String colour = "";//creates a string for the colour of the text displayed
         DefaultTableModel model = null;//instantiates a default table model
         Object columnNames[] = {"Student Name", "Lesson Date", "LessonTime", "Cost", "paid"};//creates an array of the colunm names
         model = new DefaultTableModel(columnNames, 0);//sets the table model to a table model object according to the comunm names
         for (int i = 0; i < pa.getPaymentArray().size(); i++) {//iterates through the payments
             boolean paid = pa.getPaymentArray().get(i).isPaid();//creates a boolean indicating whether the iteated payment is paid
             if (paid) {//checks if the payment is paid
                 colour = "black";//changes the colour string to black
             } else {//if the payment is not paid
                 colour = "red";//changes the colour string to red
             }
             int studentID = pa.getPaymentArray().get(i).getStudentID();//creates an int for the iterated student id
             String name = "<html><font size = 3 color=\"" + colour + "\">" + sa.studentNameFromID(studentID) + "</font></html>";//creates an int for the iterated student name
             String date = "<html><font size = 3 color=\"" + colour + "\">" + pa.getPaymentArray().get(i).getPayDate() + "</font></html>";//creates an int for the iterated date
             String time = "<html><font size = 3 color=\"" + colour + "\">" + la.getLessonTimeFromStartTimeAndDuration(pa.getPaymentArray().get(i).getPayTime(), pa.getPaymentArray().get(i).getPayDuration()) + "</font></html>";//creates an int for the iterated time
             String cost = "<html><font size = 3 color=\"" + colour + "\">" + "R" + pa.getPaymentArray().get(i).getCost() + "</font></html>";//creates an int for the iterated cost
             String paidString = "";//creates a string to display the payment state
             if (paid) {//checks if the payment is paid
                 paidString = "<html><font size = 3 color=\"" + colour + "\">" + "paid" + "</font></html>";//sets the paid string to black
             } else {//if the payment has not been made
                 paidString = "<html><font size = 3 color=\"" + colour + "\">" + "un-paid" + "</font></html>";//sets the paid string to red
             }
             
             if (pa.formattOutHTMLTags(name).toLowerCase().startsWith(nameInputted.toLowerCase())) {//checks if the names match
                 model.addRow(new Object[] {name, date, time, cost, paidString});//adds a row to the model according to the iterated data
             }
         }
        return model;//returns the model
    }//closes the paymentsByStudentName method
    
    public DefaultTableModel paymentsPaid() {//creates a method for the payments table model filtered by paid
         mothersArray ma = new mothersArray();//creates an object for the mothersArray class
         studentsArray sa = new studentsArray();//creates an object for the studentsArray class
         paymentsArray pa = new paymentsArray();//creates an object for the paymentsArray class
         lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
         DefaultTableModel model = null;//instantiates a default table model
         Object columnNames[] = {"Student Name", "Lesson Date", "LessonTime", "Cost", "paid"};//creates an array of the colunm names
         model = new DefaultTableModel(columnNames, 0);//sets the table model to a table model object according to the comunm names
         for (int i = 0; i < pa.getPaymentArray().size(); i++) {//iterates through the payments
             int studentID = pa.getPaymentArray().get(i).getStudentID();//creates an int for the iterated student id
             String name = sa.studentNameFromID(studentID);//creates a string for the ietrated name
             String date = pa.getPaymentArray().get(i).getPayDate();//creates a string for the ietrated date
             String time = la.getLessonTimeFromStartTimeAndDuration(pa.getPaymentArray().get(i).getPayTime(), pa.getPaymentArray().get(i).getPayDuration());
             boolean paid = pa.getPaymentArray().get(i).isPaid();//creates a boolean indicating whether the iteated payment is paid
             String cost = "R" + pa.getPaymentArray().get(i).getCost();//creates a string for the ietrated cost
             String paidString = "Paid";//sets the paid string to paid
      
             if (paid) {//checks if the payment is paid
                 model.addRow(new Object[] {name, date, time, cost, paidString});//adds a row to the model according to the iterated data
             }
         }
        return model;//returns the model
    }//closes the paymentsPaid method
    
    // in: date
    public DefaultTableModel paymentsPaidByDateAndTime(String dateInputted) {//creates a method for the payments table model filtered by date and time and paid
         mothersArray ma = new mothersArray();//creates an object for the mothersArray class
         studentsArray sa = new studentsArray();//creates an object for the studentsArray class
         paymentsArray pa = new paymentsArray();//creates an object for the paymentsArray class
         lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
         DefaultTableModel model = null;//instantiates a default table model
         Object columnNames[] = {"Student Name", "Lesson Date", "LessonTime", "Cost", "paid"};//creates an array of the colunm names
         model = new DefaultTableModel(columnNames, 0);//sets the table model to a table model object according to the comunm names
         for (int i = 0; i < pa.getPaymentArray().size(); i++) {//iterates through the payments
             int studentID = pa.getPaymentArray().get(i).getStudentID();//creates an int for the iterated student id
             String name = sa.studentNameFromID(studentID);//creates a string for the ietrated name
             String date = pa.getPaymentArray().get(i).getPayDate();//creates a string for the ietrated date
             String time = la.getLessonTimeFromStartTimeAndDuration(pa.getPaymentArray().get(i).getPayTime(), pa.getPaymentArray().get(i).getPayDuration());
             boolean paid = pa.getPaymentArray().get(i).isPaid();//creates a boolean indicating whether the iteated payment is paid
             String cost = "R" + pa.getPaymentArray().get(i).getCost();//creates a string for the ietrated cost
             String paidString = "Paid";//sets the paid string to paid
             
             if (paid && pa.formattOutHTMLTags(date).equals(dateInputted)) {//checks if the dates match and paid
                 model.addRow(new Object[] {name, date, time, cost, paidString});//adds a row to the model according to the iterated data
             }
         }
        return model;//returns the model
    }//closes the paymentsPaidByDateAndTime method
    
    // in: student name
    public DefaultTableModel paymentsPaidByStudentName(String nameInputted) {//creates a method for the payments table model filtered by student name and paid
         mothersArray ma = new mothersArray();//creates an object for the mothersArray class
         studentsArray sa = new studentsArray();//creates an object for the studentsArray class
         paymentsArray pa = new paymentsArray();//creates an object for the paymentsArray class
         lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
         DefaultTableModel model = null;//instantiates a default table model
         Object columnNames[] = {"Student Name", "Lesson Date", "LessonTime", "Cost", "paid"};//creates an array of the colunm names
         model = new DefaultTableModel(columnNames, 0);//sets the table model to a table model object according to the comunm names
         for (int i = 0; i < pa.getPaymentArray().size(); i++) {//iterates through the payments
             int studentID = pa.getPaymentArray().get(i).getStudentID();//creates an int for the iterated student id
             String name = sa.studentNameFromID(studentID);//creates a string for the ietrated name
             String date = pa.getPaymentArray().get(i).getPayDate();//creates a string for the ietrated date
             String time = la.getLessonTimeFromStartTimeAndDuration(pa.getPaymentArray().get(i).getPayTime(), pa.getPaymentArray().get(i).getPayDuration());
             String cost = "R" + pa.getPaymentArray().get(i).getCost();//creates a string for the ietrated cost
             boolean paid = pa.getPaymentArray().get(i).isPaid();//creates a boolean indicating whether the iteated payment is paid
             String paidString = "Paid";//sets the paid string to paid
             
             if (paid && pa.formattOutHTMLTags(name).toLowerCase().startsWith(nameInputted.toLowerCase())) {//checks if the names match and paid
                 model.addRow(new Object[] {name, date, time, cost, paidString});//adds a row to the model according to the iterated data
             }
         }
        return model;//returns the model
    }//closes the paymentsPaidByStudentName method
    
    public DefaultTableModel paymentsNotPaid() {//creates a method for the payments table model filtered by not paid
         mothersArray ma = new mothersArray();//creates an object for the mothersArray class
         studentsArray sa = new studentsArray();//creates an object for the studentsArray class
         paymentsArray pa = new paymentsArray();//creates an object for the paymentsArray class
         lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
         String colour  = "red";//sets the colour string to red
         DefaultTableModel model = null;//instantiates a default table model
         Object columnNames[] = {"Student Name", "Lesson Date", "LessonTime", "Cost", "paid"};//creates an array of the colunm names
         model = new DefaultTableModel(columnNames, 0);//sets the table model to a table model object according to the comunm names
         for (int i = 0; i < pa.getPaymentArray().size(); i++) {//iterates through the payments
             int studentID = pa.getPaymentArray().get(i).getStudentID();//creates an int for the iterated student id
             String name = "<html><font size = 3 color=\"" + colour + "\">" + sa.studentNameFromID(studentID) + "</font></html>";//creates an int for the iterated student name
             String date = "<html><font size = 3 color=\"" + colour + "\">" + pa.getPaymentArray().get(i).getPayDate() + "</font></html>";//creates an int for the iterated date
             String time = "<html><font size = 3 color=\"" + colour + "\">" + la.getLessonTimeFromStartTimeAndDuration(pa.getPaymentArray().get(i).getPayTime(), pa.getPaymentArray().get(i).getPayDuration()) + "</font></html>";//creates an int for the iterated time
             String cost = "<html><font size = 3 color=\"" + colour + "\">" + "R" + pa.getPaymentArray().get(i).getCost() + "</font></html>";//creates an int for the iterated cost
             boolean paid = pa.getPaymentArray().get(i).isPaid();//creates a boolean indicating whether the iteated payment is paid
             String paidString = "<html><font size = 3 color=\"" + colour + "\">" + "un-paid" + "</font></html>";//formats the paidString according to payment state
             
             if (!paid) {//checks if not paid
                 model.addRow(new Object[] {name, date, time, cost, paidString});//adds a row to the model according to the iterated data
             }
         }
        return model;//returns the model
    }//closes the paymentsNotPaid method
    
    // in: date
    public DefaultTableModel paymentsNotPaidByDateAndTime(String dateInputted) {//creates a method for the payments table model filtered by date and time and unpaid
         mothersArray ma = new mothersArray();//creates an object for the mothersArray class
         studentsArray sa = new studentsArray();//creates an object for the studentsArray class
         paymentsArray pa = new paymentsArray();//creates an object for the paymentsArray class
         lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
         String colour  = "red";//sets the colour string to red
         DefaultTableModel model = null;//instantiates a default table model
         Object columnNames[] = {"Student Name", "Lesson Date", "LessonTime", "Cost", "paid"};//creates an array of the colunm names
         model = new DefaultTableModel(columnNames, 0);//sets the table model to a table model object according to the comunm names
         for (int i = 0; i < pa.getPaymentArray().size(); i++) {//iterates through the payments
             int studentID = pa.getPaymentArray().get(i).getStudentID();//creates an int for the iterated student id
             String name = "<html><font size = 3 color=\"" + colour + "\">" + sa.studentNameFromID(studentID) + "</font></html>";//creates an int for the iterated student name
             String date = "<html><font size = 3 color=\"" + colour + "\">" + pa.getPaymentArray().get(i).getPayDate() + "</font></html>";//creates an int for the iterated date
             String time = "<html><font size = 3 color=\"" + colour + "\">" + la.getLessonTimeFromStartTimeAndDuration(pa.getPaymentArray().get(i).getPayTime(), pa.getPaymentArray().get(i).getPayDuration()) + "</font></html>";//creates an int for the iterated time
            String cost = "<html><font size = 3 color=\"" + colour + "\">" + "R" + pa.getPaymentArray().get(i).getCost() + "</font></html>";//creates an int for the iterated cost
             boolean paid = pa.getPaymentArray().get(i).isPaid();//creates a boolean indicating whether the iteated payment is paid
             String paidString = "<html><font size = 3 color=\"" + colour + "\">" + "un-paid" + "</font></html>";//formats the paidString according to payment state
             
             if (!paid && pa.formattOutHTMLTags(date).equals(dateInputted)) {//checks if not paid and dates match
                 model.addRow(new Object[] {name, date, time, cost, paidString});//adds a row to the model according to the iterated data
             }
         }
        return model;//returns the model
    }//closes the paymentsNotPaidByDateAndTime method
    
    // in: student name
    public DefaultTableModel paymentsNotPaidByStudentName(String nameInputted) {//creates a method for the payments table model filtered by student name and unpaid
         mothersArray ma = new mothersArray();//creates an object for the mothersArray class
         studentsArray sa = new studentsArray();//creates an object for the studentsArray class
         paymentsArray pa = new paymentsArray();//creates an object for the paymentsArray class
         lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
         String colour  = "red";//sets the colour string to red
         DefaultTableModel model = null;//instantiates a default table model
         Object columnNames[] = {"Student Name", "Lesson Date", "LessonTime", "Cost", "paid"};//creates an array of the colunm names
         model = new DefaultTableModel(columnNames, 0);//sets the table model to a table model object according to the comunm names
         for (int i = 0; i < pa.getPaymentArray().size(); i++) {//iterates through the payments
             int studentID = pa.getPaymentArray().get(i).getStudentID();//creates an int for the iterated student id
             String name = "<html><font size = 3 color=\"" + colour + "\">" + sa.studentNameFromID(studentID) + "</font></html>";//creates an int for the iterated student name
             String date = "<html><font size = 3 color=\"" + colour + "\">" + pa.getPaymentArray().get(i).getPayDate() + "</font></html>";//creates an int for the iterated date
             String time = "<html><font size = 3 color=\"" + colour + "\">" + la.getLessonTimeFromStartTimeAndDuration(pa.getPaymentArray().get(i).getPayTime(), pa.getPaymentArray().get(i).getPayDuration()) + "</font></html>";//creates an int for the iterated time
             String cost = "<html><font size = 3 color=\"" + colour + "\">" + "R" + pa.getPaymentArray().get(i).getCost() + "</font></html>";//creates an int for the iterated cost
             boolean paid = pa.getPaymentArray().get(i).isPaid();//creates a boolean indicating whether the iteated payment is paid
             String paidString = "<html><font size = 3 color=\"" + colour + "\">" + "un-paid" + "</font></html>";//formats the paidString according to payment state
             
             if (!paid && pa.formattOutHTMLTags(name).toLowerCase().startsWith(nameInputted.toLowerCase())) {//checks if not paid and names match
                 model.addRow(new Object[] {name, date, time, cost, paidString});//adds a row to the model according to the iterated data
             }
         }
        return model;//returns the model
    }//closes the paymentsNotPaidByStudentName method
    
    // in: date and time
    public DefaultTableModel PaymentsByLesson(String dateInputted, String timeInputted1) {//creates a method for the payments table model filtered by lesson
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        paymentsArray pa = new paymentsArray();//creates an object for the paymentsArray class
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        keysArray ka = new keysArray();//creates an object for the keysArray class
        
        String timeInputted;//creates a string for the time inputted
        if (timeInputted1.indexOf(":") < 2) {//checks if the index of the ':' is less than 2 
            timeInputted = "0" + timeInputted1;//adds a zero to the time
        } else {//if the index is bigger than 2
            timeInputted = timeInputted1;//sets the time inputted to the timeInputted1 string
        }
        
        DefaultTableModel model = null;//instantiates a default table model
        Object columnNames[] = {"Student Name", "Lesson Date", "LessonTime", "Cost", "paid"};//creates an array of the colunm names

         String colour = "";//creates a string for the colour of the text displayed
         model = new DefaultTableModel(columnNames, 0);//sets the table model to a table model object according to the comunm names
         for (int i = 0; i < pa.getPaymentArray().size(); i++) {//iterates through the payments
             boolean paid = pa.getPaymentArray().get(i).isPaid();//creates a boolean indicating whether the iteated payment is paid
             if (paid) {//checks if the payment is paid
                 colour = "black";//changes the colour string to black
             } else {//if the payment is not paid
                 colour = "red";//changes the colour string to red
             }
             int studentID = pa.getPaymentArray().get(i).getStudentID();//creates an int for the iterated student id
             String name = "<html><font size = 3 color=\"" + colour + "\">" + sa.studentNameFromID(studentID) + "</font></html>";//creates an int for the iterated student name
             String date = "<html><font size = 3 color=\"" + colour + "\">" + pa.getPaymentArray().get(i).getPayDate() + "</font></html>";//creates an int for the iterated date
             String time = "<html><font size = 3 color=\"" + colour + "\">" + la.getLessonTimeFromStartTimeAndDuration(pa.getPaymentArray().get(i).getPayTime(), pa.getPaymentArray().get(i).getPayDuration()) + "</font></html>";//creates an int for the iterated time
             String cost = "<html><font size = 3 color=\"" + colour + "\">" + "R" + pa.getPaymentArray().get(i).getCost() + "</font></html>";//creates an int for the iterated cost
             String paidString = "";//creates a string to display the payment state
             if (paid) {//checks if the payment is paid
                 paidString = "<html><font size = 3 color=\"" + colour + "\">" + "paid" + "</font></html>";//sets the paid string to black
             } else {//if the payment has not been made
                 paidString = "<html><font size = 3 color=\"" + colour + "\">" + "un-paid" + "</font></html>";//sets the paid string to red
             }
             
             if (pa.formattOutHTMLTags(date).equals(dateInputted) && pa.formattOutHTMLTags(time).substring(0, 5).equals(timeInputted)) {//checks if not paid and lessons match
                 model.addRow(new Object[] {name, date, time, cost, paidString});//adds a row to the model according to the iterated data
             }
         }
        return model;//returns the model
    }//closes the PaymentsByLesson method
    
    
}//closes the populateComboBoxes class
