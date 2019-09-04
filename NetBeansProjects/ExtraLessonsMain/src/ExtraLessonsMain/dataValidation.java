/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtraLessonsMain;

import java.util.ArrayList;

/**
 *
 * @author YishaiBasserabie
 */
public class dataValidation {//creates a class to handle data validation
    private String problems = "";//creates a private string to hold the list of potential invalidities of data

    public String getProblems() {//creates a method to get the problems string
        return problems;//returns the problems string
    }//closes the getProblems method
    
    public String fixEntries(String entry) {//creates a method to add capital letters the the beggining of any strings inserted into the program in: string to be fixed
        String fix = "";//creates a string to hold the formatted version of inputted data
        String temp = entry;//creates a string to remove spaces from the end of strings inputte 
        if (entry.endsWith(" ")) {//checks if the string ends in a space
            temp = entry.substring(0, temp.length()-1);//removes the space
        }
        String c = ""+temp.charAt(0);//creates a string representation of the first character of the temp string
        String temp2 = c.toUpperCase() + temp.substring(1);//creates a string having made the first letter capital
        for (int i = 1; i < temp.length(); i++) {//starts a for loop iterating through each character in the temp String
            String charTemp = ""+temp2.charAt(i-1);//creates a string representation of the character before th iterated character
            String nextChar = ""+temp2.charAt(i);//creates a string representation of the iterated character
            if (!charTemp.equals(" ")) {//checks if the charTemp string is not a space
                fix += nextChar;//adds the nextChar string to the string fix
            } else {//if the charTemp string is a space
                fix += nextChar.toUpperCase();//makes the nextChar string uppercase and adds it to the fix string
            }
        }
        return temp2.charAt(0) + fix;//returns the final formatted string
    }//closes the fixEntries method
    
    public boolean checkName(String input, String ForL) {//checks if a name string is valid in: name to be checked
        boolean ok = true;//creates variable that holds value of valid/non-valid
        if (!input.equals("")) {//checks if something is entered
            for (int i = 0; i < input.length(); i++) {//loops through inputted string
            if (!Character.isAlphabetic(input.charAt(i))) {//checks if character at i is alphabetic
                if (!problems.contains("it seems you have an invalid character in your last name")) {//checks whether the below message has already been added to the problems string
                    if (ForL.equals("f")) {//checks if the inputted name is a first name
                        problems += "()it seems you have an invalid character in your first Name\n";//adds the invalid character message to the problems string
                    } else {//if it is not a first name
                        if (ForL.equals("l")) {//checks if the it is a last name
                            problems += "()it seems you have an invalid character in your last name\n";//adds the invalid character message to the problems string
                        } else {//if it is not a last name
                            if (ForL.equals("mf")) {//checks if it is a parent's first name
                               problems += "()it seems you have an invalid character in the parent's first name\n";//adds the invalid character message to the problems string
                            } else {//if it is a parent's last name
                                problems += "()it seems you have an invalid character in the parent's last name\n";//adds the invalid character message to the problems string
                            }
                        } 
                    }
                    ok = !ok;//flips value of ok to false
                    }  
            }
        }
      } else {//if the inputted string is blank
            if (ForL.equals("f")) {//checks if the inputted name is a first name
                problems += "()oh no you have left first name blank\n";//adds the bank message to the problems string
            } else {//if it is not a first name
                if (ForL.equals("l")) {//checks if the it is a last name
                    problems += "()oh no you have last name blank\n";//adds the bank message to the problems string
                } else {//if it is not a last name
                    if (ForL.equals("mf")) {//checks if it is a parent's first name
                        problems += "()oh no you have the parent's first name blank\n";//adds the bank message to the problems string
                    } else {//if it is a parent's last name
                        problems += "()oh no you have the parent's last name blank\n";//adds the bank message to the problems string
                    }
                }
            }
            ok = !ok;//flips ok to false
        }
        return ok;//returns ok
    }//closes the checkName method
    
    public boolean checkNum(String inputString, int start, int end) {//checks if a number is valid in: num to be checked
        boolean ok = true;//creates variable that holds value of valid/non-valid
        if (!inputString.equals("")) {//checks if somethng is entered
            int input = Integer.parseInt(inputString);//converts string imputString to int input
            if (input >= start && input <= end) {// checks if input is between parametres
                ok = !ok;//flips value of ok to false
            }
        } else {//if the inputted string is null
            ok = !ok;//flips ok to false
        }
        return ok;//returns ok
    }//closes the checkNum message
    
    public boolean checkCell(String cell) {//creates a method to validate a cell phone number in: cell number to be checked
        boolean ok = true;//creates a boolean indicating whether the cell inputted is valid or not
        if (cell.equals("")) {//checks if the cell inputted is blank
            problems += "()you have left cell blank!\n";//adds the blank message to the problems string
            ok = !ok;//flips ok to false indicating that the cell is invalid
        } else {//if the cell is not blank
            if (cell.length() != 10) {//checks if the cell is not of valid length
                problems += "()the cell number you have entered is of invalid length!\n";//adds the invalid lenght message to the problems string
                ok = false;//flips ok to false indicating that the cell is invalid
            }
        }
        return ok;//returns ok
    }//closes the checkCell method
    
    public boolean checkEmail(String input) {//validates inputted email address in: email address to be checked
        boolean ok = true;//creates variable that holds value of valid/non-valid
        if (!input.equals("")) {//checks if something was entered
            if (!input.contains("@") || !input.contains(".")) {//checks if the email does not contain an '@' sign or a '.'
                ok = false;//flips ok to false
                problems += "()email invalid syntax please try again dude!\n";//adds the invalid syntax to the problems string
            }
        } else {//if the email entered is blank
            ok = false;//flips ok to false
            problems += "()you have left email blank\n";//adds the blank message to the problems string
        }
        return ok;//returns ok
    }//closes the checkCell method
    
    public boolean checkPassword(String password, String confirmPassword) {//creates a method to validate password data in: password and confirmation password to be checked
        boolean ok = true;//creates a boolean indicating whether the password data is valid or not
        if (!password.equals("")) {//checks if the password enetered is not null
            if (password.length() < 8) {//checks if the password lenght is less than 8
                ok = false;//flips ok to false
                problems += "()password must be at least 8 charachters!\n";//adds the invalid lenght message to the problems string
            }
        } else {//if the =password is blank
            ok = false;//flips ok to false
            problems += "()oy vey! you have left the password field blank\n";//adds the blank message to the problems string
        }
        if (!password.equals(confirmPassword)) {//checks if the password does not match the confirmation password
            ok = false;//flips ok to false
            problems += "()passwords do not match\n";//adds the 'do not match' message to the problems string
        }
        return ok;//returns ok
    }//closes the checkPassword method
    
    public boolean checkSchool(String school) {//creates a method to validate a school entry in: school name to be checked
        boolean ok = true;//creates a boolean indicating whether the school inputted is valid or not
        if (!school.equals("")) {//checks if the school entered is not null
            for (int i = 0; i < school.length(); i++) {//starts a for loop iterating through the characters in the school
                String c = ""+school.charAt(i);//creates a string representation of the iterated character
                if (!Character.isAlphabetic(school.charAt(i)) && !c.equals(" ")) {//checks if c is not an alphabetic character
                    ok = false;//flips ok to false
                }
            }
            if (!ok) {//checks if ok is false
                problems += "()oy vey! you have an invalid character in the school name\n";//adds the invalid character message to the problems string
            }
        } else {//if the school entered is null
            ok = false;//flips ok to false
            problems += "()oy vey! you have left the password field blank\n";//adds the blank message to the problems string
        }
        return ok;//returns ok
    }//closes the checkSchool method
    
    public boolean checkOldPassword(String password) {//creates a method to check if a password enetered is correct in: password to be checked
        fetchTeacher ft = new fetchTeacher();//creates an object for the fetchTeacher  class
        loginSignUpHandler h = new loginSignUpHandler();//creates an object for the loginSignUpHandler class
        boolean ok = true;//creates a boolean to indicate whether the password is correct or not
        if (!h.encryptPassword(password, ft.getAnswer()).equals(ft.getPassword())) {//checks if the passwords to do match
            ok = false;//flips ok to false
            problems += "()The password you entered is incorrect\n";//adds the incorrect password message to the problems string
        }
        return ok;//returns ok
    }//closes the checkOldPassword method
    
    public boolean checkBlank(String input) {//creates a method to check if an inputted string is blank in: text to be checked
        boolean ok = true;//creates a boolean indicating if the inputted string is blank
        if (input.equals("")) {//check if the string is blank
            ok = !ok;//flips ok to false
        }
        return ok;//returns ok
    }//closes the checkBlank method 
    
    public boolean checkCost(String cost) {//creates a method to validate an inputted cost in: cost attempt to be checked
        boolean ok = true;//creates a boolean indicating whether the inputted cost is valid or not
        boolean alphabetical = false;//creates a boolean showing whether there is an alpabetical character in the cost
        boolean negative = false;//creates a boolean to show if the cost is negative
        if (!this.checkBlank(cost)) {//checks if the cost is blank
            ok = false;//flips ok to false
            problems += "()please enter a cost.\n";//adds the blank message to problems
        }
        if (cost.contains(".") || cost.contains(",")) {//if the cost ontains a dot or a comma
            ok = false;//flips ok to false
            problems += "()please enter the cost as an integer.\n";//adds the integer message to problems
        } else {//if the cost contans niether a dot or a comma
            for (int i = 0; i < cost.length(); i++) {//starts a for loop iterating through the cost string
                if (!Character.isDigit(cost.charAt(i))) {//if the ierated character is not a digit
                    ok = false;//flips ok to false
                    alphabetical = true;//flips alphabetical to true
                } else {//if cost is only digits
                    if (Integer.parseInt(cost) < 0) {//if the cost is negative
                        ok = false;//flips ok to false
                        negative = true;//flips negative to true
                    }
                }
            }
            if (alphabetical) {//if there is an invalid character
                problems += "()please do not enter any alphabetical symbols in the cost.\n";//adds the syntax message to problems
            } else {//if not alphabetical
                if (negative) {//if it is a negative number
                    problems += "()please cost must be positive.\n";//adds the negative message to problems
                }
            }
        }
        return ok;//returns ok
    }//closes the checkCost method
    
    public boolean checkAddLesson(String date, String time, int students, String cost) {//creates a method to validate adding a lesson in: date, time, students, cost to be checked
        boolean ok = true;//creates a boolean to indicate validity of data
        if (time.equals("0")) {//if time equals 0 i.e not entered
            ok = false;//flips ok to false
            problems += "()please select a time.\n";//adds the time message to problems
        }
        if (students == 0) {//if no students entered
            ok = false;//flips ok to false
            problems += "()please add at least one student to the lesson.\n";//adds the add student message to problems
        }
        if (!this.checkCost(cost)) {//if cost invalid
            ok = false;//flips ok to false
        }
        return ok;//returns ok
    }//closes the checkAddLesson method
    
    public boolean checkAddStudent(String sfname, String slname, String mfname, String mlname, String email, String cell) {//creates a method to validate entering a student in: student name, mothername, email and cell number to be checked
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        boolean ok = true;//creates a boolean indicating the validity of the data
        
        for (int i = 0; i < sa.getStudentArray().size(); i++) {//iterates through the students in the studentsArray list
            String fname = sa.getStudentArray().get(i).getfName();//creates a string of the iterated student first name
            String lname = sa.getStudentArray().get(i).getlName();//creates a string of the iterated student last name
            if (fname.toLowerCase().equals(sfname.toLowerCase()) && lname.toLowerCase().equals(slname.toLowerCase())) {//checks if the student being added equals the the iterated student
                ok = false;//flips ok to false
            }
        }
        if (!ok) {//checks if ok is false
            problems += "**You have already added this student to the database!\n";//adds the student already added message to the problems
        } else {//if ok to true
            if (!this.checkName(sfname, "f")) {//checks if the first name is not valid
                ok = false;//flips ok to false
            }
            if (!this.checkName(slname, "l")) {//checks if the last name is not valid
                ok = false;//flips ok to false
            }
            if (!this.checkName(mfname, "mf")) {//checks if the parent first name is not valid
                ok = false;//flips ok to false
            }
            if (!this.checkName(mlname, "ml")) {//checks if the parent last name is not valid
                ok = false;//flips ok to false
            }
            if (!this.checkEmail(email)) {//checks if the email is not valid
                ok = false;//flips ok to false
            }
            if (!this.checkCell(cell)) {//checks if the cell is not valid
                ok = false;//flips ok to false
            }
            if (!ok) {//checks if ok is false
                problems += "**Please ensure you have entered the correct grade and school";//adds the cautionary message to the problems
            }
        }
        return ok;//returns ok
    }//closes the checkAddStudent method
    
    public boolean checkAddVenue(String venue) {//creates a method to check a venue being added in: venue name to be checked
        venueArray va = new venueArray();//creates an object for the venuesArray class
        boolean ok = true;//creates a boolean to indicate the validity of the data
        
        if (this.checkBlank(venue)) {//checks if the venue is not blank
            for (int i = 0; i < va.getVenuesArray().size(); i++) {//iterates through the venues in the venuesArray list
                String venueVA = va.getVenuesArray().get(i).getVenue();//creates a string holding the iterated venue
                if (venueVA.toLowerCase().equals(venue.toLowerCase())) {//checks if the venue passed in is equal to the iterated venue
                    ok = false;//flips ok to false
                }
            }
            if (!ok) {//checks if ok is false
                problems += "**You have already enetered this vanue to the database\n";//adds the venue added message to the problems string
            } else {//if ok is true
                String newVT = venue.replaceAll(" ", "");//creates a string venue without spaces
                String newV = "";//creates a new string to hold the string without digits
                for (int i = 0; i < newVT.length(); i++) {//starts a for loop iterating through the newVT string
                    String c = ""+newVT.charAt(i);//creates a string representation of the iterated character
                    if (!Character.isDigit(newVT.charAt(i))) {//checks if c is not a digit
                        newV += c;//adds c to the newV string
                    }
                }
                for (int i = 0; i < newV.length(); i++) {//starts a for loop iterating through the newV string
                    if (!Character.isAlphabetic(newV.charAt(i))) {//checks if the iterated character is not a digit
                        ok = false;//flips ok to false
                    }
                }
                if (!ok) {//checks if ok is false
                    problems += "()oy vey, There is an invalid character in the venue\n";//adds the invalid character message to the problems string
                }
            }
        } else {//if the venues is null
            ok = false;//flips ok to false
            problems += "()please do not leave the venue blank\n";//adds the blank message to problems
        }
        return ok;//returns ok
    }//closes the checkAddVenue method
    
    public boolean checkIfSchoolAlreadyAdded(String school) {//creates a method to check if a school has already been added in: school name to be checked
        schoolsArray sa = new schoolsArray();//creates an object for the schoolsArray class
        boolean ok = true;//creates a boolean indicating the presence of the school
        for (int i = 0; i < sa.getSchoolsDataArray().size(); i++) {//starts a for loop iterating though the schools added already
            if (sa.getSchoolsDataArray().get(i).getSchoolName().toLowerCase().equals(school.toLowerCase())) {//checks if the passed in school equals the iterated school
                ok = false;//flips ok to false
            }
        }
        return ok;//returns ok
    }//closes the checkIfSchoolAlreadyAdded method
    
    public boolean checkAddSchool(String school, String pfname, String plname, String email) {//creates a method to validate the adding of a school in: school name, principal details to be checked
        boolean ok = true;//creates a boolean indicating the validity of the data
        if (this.checkIfSchoolAlreadyAdded(school)) {//checks if the school has already been added
            if (!this.checkSchool(school)) {//checks if the school is not valid
                ok = false;//flips ok to false
            }
            if (!this.checkName(pfname, "f")) {//checks if the principal first name is not valid
                ok = false;//flips ok to false
            }
            if (!this.checkName(plname, "l")) {//checks if the principal last name is not valid
                ok = false;//flips ok to false
            }
            if (!this.checkEmail(email)) {//checks if the principal first email is not valid
                ok = false;//flips ok to false
            }
        } else {//if the school already exists
            ok = false;//flips ok to false
            problems += "()This school already exists in the database.\n";//adds the school already exists message to the problems string
        }
        return ok;//returns
    }//closes the checkaddSchool method
    
}//closes the dataValidation class
