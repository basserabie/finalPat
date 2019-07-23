/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtraLessonsMain;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SpinnerListModel;
import static ExtraLessonsMain.addLessonForm.ADDED_ARRAY;

/**
 *
 * @author YishaiBasserabie
 */
public class editLessonForm extends javax.swing.JFrame {//creates a class to handle the editing of a lesson

    //checks if check-boxes are checked
    private static boolean STUDENTS_CHECKED = false;//creates a static boolean indicating whether the students are being edited
    private static boolean DATETIME_CHECKED = false;//creates a static boolean indicating whether the date and time is being edited
    private static boolean VENUE_CHECKED = false;//creates a static boolean indicating whether the venue is being edited
    
    private static boolean HOUR_CHOSEN = false;//creates a boolean indicating whether an hour has been selected
    //arrayList of students
    public static ArrayList<String> ADDED_ARRAY = new ArrayList<>();//creates an array list according to the students added to the lesson
    //list model
    DefaultListModel StudentsAddedListModel = new DefaultListModel();//creates a default list model for the new students added to the lesson
    public static DefaultListModel checkListModel = new DefaultListModel();//creates a default list model according to the students already added to the lesson
    //checks what has been edited
    private boolean STUDENTS_CHANGED = false;//creates a private boolean indicating whether the students were changed
    private boolean VENUE_CHANGED = false;//creates a private boolean indicating whether the venue was changed
    
    /**
     * Creates new form editLessonForm
     */
    public editLessonForm() {//creates the constructor for the current class
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//sets the default close operation of the current class to dispose
        
        this.editStudentPanel.setBackground(Color.red);//sets the background colour of the panel to red
        this.editDateTimePanel.setBackground(Color.red);//sets the background colour of the panel to red
        this.venuePanel.setBackground(Color.red);//sets the background colour of the panel to red
        
        this.studentsAddedList.removeAll(); //removes generic items from studentsAddedList
        this.addStudentNameComboBox.removeAllItems();//removes all generic tiems from name combo box
        populateComboBoxes pop = new populateComboBoxes();//creates an object for the populateComboBoxes class
        //populates grade combo box
        DefaultComboBoxModel grades = new DefaultComboBoxModel(pop.populateGrades());//creates a default combo box model for the grades
        this.addStudentGradeComboBox.setModel(grades);//sets the model of the addStudentGradeComboBox to the grades model
        //populates venues combo box
        DefaultComboBoxModel venuesModel = new DefaultComboBoxModel(pop.populateVenues());//creates a default combo box model for the venues
        this.venuesComboBox.setModel(venuesModel);//sets the venuesComboBox to the venuesModel model
        //populates hourSpinner
        SpinnerListModel hourModel = new SpinnerListModel(pop.populateHourSpinner());//creates a spinner model for the hours
        this.HourSpinner.setModel(hourModel);//sets the hourspinner to the hour model
        SpinnerListModel hours = new SpinnerListModel(pop.populateDurationSpinner());//creates a spinner model for the duration
        this.durationSpinner.setModel(hours);//sets the duration spinner to the hours model
        //creates and populates a reference listModel
        CalendarHandler ch = new CalendarHandler();//creates an object for the calendarHandler class
        for (int i = 0; i < ch.studentsFromLessonDateAndTime(lesson.SELECTED_LESSON_DATE, lesson.SELECTED_LESSON_TIME).length; i++) {//starts a for loop iterating through the students according to the selected lesson
            ADDED_ARRAY.add(ch.studentsFromLessonDateAndTime(lesson.SELECTED_LESSON_DATE, lesson.SELECTED_LESSON_TIME)[i]);//adds the iterated student to the added array
            this.StudentsAddedListModel.addElement(ADDED_ARRAY.get(i));//adds the iterated student to the studentsAddedListModel
            checkListModel.addElement(ADDED_ARRAY.get(i));//adds the iterated element to the checkListModel
        }
        this.studentsAddedList.setModel(checkListModel);//sets the model of the studentsAddedList to the checkListModel
    }//closes the constructor
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        addlessonLabel = new javax.swing.JLabel();
        backToDashboardButton = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        studentsAddedList1 = new javax.swing.JList<>();
        addLessonButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        addStudentGradeComboBox1 = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        studentBeingAddedTextField1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        addStudentNameComboBox1 = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        deleteStudentFromLessonButton1 = new javax.swing.JButton();
        addStudentToLessonButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        addDateComboBox1 = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        addLessonTimeHourComboBox1 = new javax.swing.JComboBox<>();
        addLessonTimeMinuteComboBox1 = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        durationSpinner1 = new javax.swing.JSpinner();
        jLabel24 = new javax.swing.JLabel();
        timeSetLabel1 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        frequencyComboBox1 = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        addVenueComboBox = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        studentsAddedLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        studentCheckBox = new javax.swing.JCheckBox();
        dateTimeCheckBox = new javax.swing.JCheckBox();
        venueCheckbox = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        studentsAddedList = new javax.swing.JList<>();
        editStudentPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        addStudentGradeComboBox = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        studentBeingAddedTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        addStudentNameComboBox = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        deleteStudentFromLessonButton = new javax.swing.JButton();
        addStudentToLessonButton = new javax.swing.JButton();
        editDateTimePanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        addDateComboBox = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        durationSpinner = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        timeSetLabel = new javax.swing.JLabel();
        HourSpinner = new javax.swing.JSpinner();
        minuteSpinner = new javax.swing.JSpinner();
        venuePanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        venuesComboBox = new javax.swing.JComboBox<>();
        editSelectedLessonButton = new javax.swing.JButton();
        editAllLessonsButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        jFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        addlessonLabel.setText("add lesson!");

        backToDashboardButton.setText("back");
        backToDashboardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToDashboardButtonActionPerformed(evt);
            }
        });

        jLabel15.setText("students added:");

        jScrollPane2.setViewportView(studentsAddedList1);

        addLessonButton.setText("add lesson!");
        addLessonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addLessonButtonActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel16.setText("add student:");

        addStudentGradeComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "11", "12" }));
        addStudentGradeComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStudentGradeComboBox1ActionPerformed(evt);
            }
        });

        jLabel17.setText("grade:");

        studentBeingAddedTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentBeingAddedTextField1ActionPerformed(evt);
            }
        });
        studentBeingAddedTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                studentBeingAddedTextField1KeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                studentBeingAddedTextField1KeyPressed(evt);
            }
        });

        jLabel18.setText("Choose From Combo Box:");

        addStudentNameComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel19.setText("Or Start Typing The Name:");

        deleteStudentFromLessonButton1.setText("delete student");
        deleteStudentFromLessonButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteStudentFromLessonButton1ActionPerformed(evt);
            }
        });

        addStudentToLessonButton1.setText("add student");
        addStudentToLessonButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStudentToLessonButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(addStudentToLessonButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deleteStudentFromLessonButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel17)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addStudentGradeComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(studentBeingAddedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addStudentNameComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addStudentGradeComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addStudentNameComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(studentBeingAddedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteStudentFromLessonButton1)
                    .addComponent(addStudentToLessonButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel20.setText("Set Lesson Date, Time, And Frequency:");

        jLabel21.setText("Date:");

        jLabel22.setText("Time:");

        addLessonTimeHourComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24" }));
        addLessonTimeHourComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addLessonTimeHourComboBox1ActionPerformed(evt);
            }
        });

        addLessonTimeMinuteComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addLessonTimeMinuteComboBox1ActionPerformed(evt);
            }
        });

        jLabel23.setText("Duration(hours):");

        durationSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                durationSpinner1StateChanged(evt);
            }
        });

        jLabel24.setText("Time Set:");

        timeSetLabel1.setText("------------------");

        jLabel25.setText("Frequency:");

        frequencyComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "once off", "weekly", "monthly" }));
        frequencyComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frequencyComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(addDateComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(addLessonTimeHourComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addLessonTimeMinuteComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(frequencyComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(durationSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timeSetLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addDateComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addLessonTimeHourComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addLessonTimeMinuteComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(durationSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timeSetLabel1)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(frequencyComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel26.setText("Set Venue:");

        addVenueComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        addVenueComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addVenueComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(addVenueComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addVenueComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addComponent(backToDashboardButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addlessonLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jFrame1Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addLessonButton, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(653, 653, 653))))
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addlessonLabel)
                    .addComponent(backToDashboardButton))
                .addGap(24, 24, 24)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGap(14, 14, 14))
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addLessonButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        jPanel3.setBackground(new java.awt.Color(255, 204, 255));

        jLabel1.setText("Editing Lesson:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(255, 204, 255));

        jLabel27.setText("NOTE: Please select field if edit is required");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel27)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(255, 204, 255));

        studentsAddedLabel.setText("Students Added:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(studentsAddedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(studentsAddedLabel)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel13.setText("Please select what you would like to edit:");

        studentCheckBox.setText("Students");
        studentCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                studentCheckBoxStateChanged(evt);
            }
        });

        dateTimeCheckBox.setText("Date/Time");
        dateTimeCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                dateTimeCheckBoxStateChanged(evt);
            }
        });
        dateTimeCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateTimeCheckBoxActionPerformed(evt);
            }
        });

        venueCheckbox.setText("Venue");
        venueCheckbox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                venueCheckboxStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(studentCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateTimeCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(venueCheckbox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(studentCheckBox)
                    .addComponent(dateTimeCheckBox)
                    .addComponent(venueCheckbox))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 204, 255));

        jLabel14.setText("* Please note: if you are updating a monthly or weekly lesson, the date and time cannot be changed for all of them");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(studentsAddedList);

        editStudentPanel.setBackground(new java.awt.Color(255, 204, 255));
        editStudentPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setText("Add Student:");

        addStudentGradeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "11", "12" }));
        addStudentGradeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStudentGradeComboBoxActionPerformed(evt);
            }
        });

        jLabel5.setText("Grade:");

        studentBeingAddedTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentBeingAddedTextFieldActionPerformed(evt);
            }
        });
        studentBeingAddedTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                studentBeingAddedTextFieldKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                studentBeingAddedTextFieldKeyPressed(evt);
            }
        });

        jLabel9.setText("Choose From Combo Box:");

        addStudentNameComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Or Start Typing The Name:");

        deleteStudentFromLessonButton.setText("Delete Student");
        deleteStudentFromLessonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteStudentFromLessonButtonActionPerformed(evt);
            }
        });

        addStudentToLessonButton.setText("Add Student");
        addStudentToLessonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStudentToLessonButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout editStudentPanelLayout = new javax.swing.GroupLayout(editStudentPanel);
        editStudentPanel.setLayout(editStudentPanelLayout);
        editStudentPanelLayout.setHorizontalGroup(
            editStudentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editStudentPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(editStudentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, editStudentPanelLayout.createSequentialGroup()
                        .addComponent(addStudentToLessonButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deleteStudentFromLessonButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editStudentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(editStudentPanelLayout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addStudentGradeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(studentBeingAddedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addStudentNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        editStudentPanelLayout.setVerticalGroup(
            editStudentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editStudentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editStudentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addStudentGradeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addStudentNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(studentBeingAddedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editStudentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteStudentFromLessonButton)
                    .addComponent(addStudentToLessonButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        editDateTimePanel.setBackground(new java.awt.Color(255, 204, 255));
        editDateTimePanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setText("Set Lesson Date, Time, And Frequency:");

        jLabel10.setText("Date:");

        jLabel11.setText("Time:");

        jLabel8.setText("Duration(hours):");

        durationSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                durationSpinnerStateChanged(evt);
            }
        });

        jLabel12.setText("Time Set:");

        timeSetLabel.setText("------------------");

        HourSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                HourSpinnerStateChanged(evt);
            }
        });

        minuteSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                minuteSpinnerStateChanged(evt);
            }
        });

        javax.swing.GroupLayout editDateTimePanelLayout = new javax.swing.GroupLayout(editDateTimePanel);
        editDateTimePanel.setLayout(editDateTimePanelLayout);
        editDateTimePanelLayout.setHorizontalGroup(
            editDateTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editDateTimePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editDateTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(editDateTimePanelLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timeSetLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editDateTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, editDateTimePanelLayout.createSequentialGroup()
                            .addComponent(HourSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(minuteSpinner))
                        .addGroup(editDateTimePanelLayout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(durationSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(editDateTimePanelLayout.createSequentialGroup()
                .addComponent(addDateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        editDateTimePanelLayout.setVerticalGroup(
            editDateTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editDateTimePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addDateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGap(7, 7, 7)
                .addGroup(editDateTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HourSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minuteSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editDateTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(durationSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(editDateTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timeSetLabel)
                    .addComponent(jLabel12))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        venuePanel.setBackground(new java.awt.Color(255, 204, 255));
        venuePanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setText("Venue:");

        venuesComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout venuePanelLayout = new javax.swing.GroupLayout(venuePanel);
        venuePanel.setLayout(venuePanelLayout);
        venuePanelLayout.setHorizontalGroup(
            venuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(venuePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(venuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(venuePanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(venuesComboBox, 0, 289, Short.MAX_VALUE))
                .addContainerGap())
        );
        venuePanelLayout.setVerticalGroup(
            venuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(venuePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(venuesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        editSelectedLessonButton.setText("Update Only Selected!");
        editSelectedLessonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSelectedLessonButtonActionPerformed(evt);
            }
        });

        editAllLessonsButton.setText("Update All!");
        editAllLessonsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editAllLessonsButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(editStudentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editDateTimePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(venuePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(editSelectedLessonButton, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editAllLessonsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(cancelButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(123, 123, 123)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(cancelButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(editStudentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editDateTimePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(venuePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editSelectedLessonButton)
                            .addComponent(editAllLessonsButton))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 731, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addStudentGradeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStudentGradeComboBoxActionPerformed
        ConnectDB db = new ConnectDB();//creates an object for the ConnectDB class
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        populateComboBoxes pop = new populateComboBoxes();//creates an object for the populateComboBoxes class
        //populates students combo box according to grade selected
        this.addStudentNameComboBox.removeAllItems();//removes the items in the addStudentNameComboBox
        DefaultComboBoxModel correctedStudents = new DefaultComboBoxModel(pop.correctStudentsAccordingToGrade(this.addStudentGradeComboBox.getSelectedItem().toString()));//corrects the students according to grade
        this.addStudentNameComboBox.setModel(correctedStudents);//sets the model of the addStudensNameComboBox to the correctedStudents model
    }//GEN-LAST:event_addStudentGradeComboBoxActionPerformed

    private void studentBeingAddedTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentBeingAddedTextFieldActionPerformed

    }//GEN-LAST:event_studentBeingAddedTextFieldActionPerformed

    private void studentBeingAddedTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentBeingAddedTextFieldKeyTyped

    }//GEN-LAST:event_studentBeingAddedTextFieldKeyTyped

    private void studentBeingAddedTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentBeingAddedTextFieldKeyPressed
        populateComboBoxes pop = new populateComboBoxes();//creates an object for the populateComboBoxes class
        if (!this.addStudentGradeComboBox.getSelectedItem().toString().equals("")) {//checks if the student grade selected is not null
            DefaultComboBoxModel nameModel = new DefaultComboBoxModel(pop.correctStudentsAccordingToSearchTextField(this.addStudentGradeComboBox.getSelectedItem().toString(), this.studentBeingAddedTextField.getText()));//correct the combo box model according to the typed name
            this.addStudentNameComboBox.setModel(nameModel);//sets the model of the addStudentNameComboBox to the nameModel model
        } else {//if the grade is null
            JOptionPane.showMessageDialog(null, "Before you start typing please select the grade :)");//alerts the user that a grade must first be selected
        }
    }//GEN-LAST:event_studentBeingAddedTextFieldKeyPressed

    private void deleteStudentFromLessonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteStudentFromLessonButtonActionPerformed
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        String name = this.studentsAddedList.getSelectedValue();//creates a string for the selected student name
        ADDED_ARRAY.remove(name);//removes the student from the added array
        la.setNamesList(ADDED_ARRAY);//sets the names list to the added array
        this.StudentsAddedListModel.removeAllElements();//removes all elements from the studentsAddedListModel
        for (int i = 0; i < la.getNames().size(); i++) {//starts a for loop iterating througb the students in the array of the lessonDataArray class
            StudentsAddedListModel.addElement(la.getArrayOfStudentsAdded(i));//adds the iterated item to the studentsAeedListModel
        }
        this.studentsAddedList.setModel(StudentsAddedListModel);//sets the model of the studentsAddedList to the studentsAddedListModel
    }//GEN-LAST:event_deleteStudentFromLessonButtonActionPerformed

    private void addStudentToLessonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStudentToLessonButtonActionPerformed
        ConnectDB db = new ConnectDB();//creates an obkect for the ConnectDB class
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        String name = this.addStudentNameComboBox.getSelectedItem().toString();//creates a string for the selected student name
        if (!ADDED_ARRAY.contains(name)) {//checks if the added array does not contain name
            ADDED_ARRAY.add(name);//adds the name to the added array
        }
        la.setNamesList(ADDED_ARRAY);//sets the names list of the lessonDataArray class
        this.StudentsAddedListModel.removeAllElements();//removes all items from studentsAddedArrayListModel
        for (int i = 0; i < la.getNames().size(); i++) {//starst a for loop iterating through the names in the lessonDataArray class
            StudentsAddedListModel.addElement(la.getArrayOfStudentsAdded(i));//adds the iterated name to the studentsAddedListModel
        }
        this.studentsAddedList.setModel(StudentsAddedListModel);//sets the model of the studentsAddedList to the studentsAddedListModel
    }//GEN-LAST:event_addStudentToLessonButtonActionPerformed

    private void backToDashboardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToDashboardButtonActionPerformed
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        la.sortArray();//sorts the lessonDataArray array
        this.hide();//discontinues the current JFrame
    }//GEN-LAST:event_backToDashboardButtonActionPerformed

    private void addLessonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addLessonButtonActionPerformed
        
    }//GEN-LAST:event_addLessonButtonActionPerformed

    private void addStudentGradeComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStudentGradeComboBox1ActionPerformed
//        ConnectDB db = new ConnectDB();
//        studentsArray sa = new studentsArray();
//        populateComboBoxes pop = new populateComboBoxes();
//        //populates students combo box according to grade selected
//        this.addStudentNameComboBox.removeAllItems();
//        DefaultComboBoxModel correctedStudents = new DefaultComboBoxModel(pop.correctStudentsAccordingToGrade(this.addStudentGradeComboBox.getSelectedItem().toString()));
//        this.addStudentNameComboBox.setModel(correctedStudents);
    }//GEN-LAST:event_addStudentGradeComboBox1ActionPerformed

    private void studentBeingAddedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentBeingAddedTextField1ActionPerformed

    }//GEN-LAST:event_studentBeingAddedTextField1ActionPerformed

    private void studentBeingAddedTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentBeingAddedTextField1KeyTyped

    }//GEN-LAST:event_studentBeingAddedTextField1KeyTyped

    private void studentBeingAddedTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentBeingAddedTextField1KeyPressed
//        populateComboBoxes pop = new populateComboBoxes();
//        if (!this.addStudentGradeComboBox.getSelectedItem().toString().equals("")) {
//            DefaultComboBoxModel nameModel = new DefaultComboBoxModel(pop.correctStudentsAccordingToSearchTextField(this.addStudentGradeComboBox.getSelectedItem().toString(), this.studentBeingAddedTextField.getText()));
//            this.addStudentNameComboBox.setModel(nameModel);
//        } else {
//            JOptionPane.showMessageDialog(null, "Before you start typing please select the grade :)");
//        }
    }//GEN-LAST:event_studentBeingAddedTextField1KeyPressed

    private void deleteStudentFromLessonButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteStudentFromLessonButton1ActionPerformed
//        lessonDataArray la = new lessonDataArray();
//        la.removeStudentAdded(this.studentsAddedList.getSelectedValue());
//        this.StudentsAddedListModel.removeAllElements();
//        for (int i = 0; i < la.getNames().size(); i++) {
//            StudentsAddedListModel.addElement(la.getArrayOfStudentsAdded(i));
//        }
//        this.studentsAddedList.setModel(StudentsAddedListModel);
    }//GEN-LAST:event_deleteStudentFromLessonButton1ActionPerformed

    private void addStudentToLessonButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStudentToLessonButton1ActionPerformed
//        ConnectDB db = new ConnectDB();
//        String name = this.addStudentNameComboBox.getSelectedItem().toString();
//        lessonDataArray la = new lessonDataArray();
//        la.addToNamesList(this.addStudentNameComboBox.getSelectedItem().toString());
//        for (int i = 0; i < la.getNames().size(); i++) {
//            StudentsAddedListModel.addElement(la.getArrayOfStudentsAdded(i));
//        }
//        this.studentsAddedList.setModel(StudentsAddedListModel);
    }//GEN-LAST:event_addStudentToLessonButton1ActionPerformed

    private void addLessonTimeHourComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addLessonTimeHourComboBox1ActionPerformed
//        
    }//GEN-LAST:event_addLessonTimeHourComboBox1ActionPerformed

    private void addLessonTimeMinuteComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addLessonTimeMinuteComboBox1ActionPerformed
      
    }//GEN-LAST:event_addLessonTimeMinuteComboBox1ActionPerformed

    private void durationSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_durationSpinner1StateChanged
      
    }//GEN-LAST:event_durationSpinner1StateChanged

    private void frequencyComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frequencyComboBox1ActionPerformed

    }//GEN-LAST:event_frequencyComboBox1ActionPerformed

    private void addVenueComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addVenueComboBoxActionPerformed

    }//GEN-LAST:event_addVenueComboBoxActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        STUDENTS_CHECKED = false;//sets the studentsChecked to false
        DATETIME_CHECKED = false;//sets the dateTime checked to false
        VENUE_CHECKED = false;//sets the venue checked to false
        ADDED_ARRAY.removeAll(ADDED_ARRAY);//removes all items from the added array
        checkListModel.removeAllElements();//removes all elements from the checkListModel
        la.sortArray();//sorts the lessonDataArray array
        this.setVisible(false);//discontinues the current JFrame
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void editSelectedLessonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editSelectedLessonButtonActionPerformed
       lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
       boolean lessonDoubleBooked = false;//creates a boolean indicating whteher the lesson is double booked
       if (DATETIME_CHECKED) {//checks if the date ad time is being edited
           la.checkIfDoubleBookingForEdit(la.formatDate(this.addDateComboBox.getDate().toString()), this.minuteSpinner.getModel().getValue().toString(), Integer.parseInt(this.durationSpinner.getModel().getValue().toString()), ADDED_ARRAY.size(), lesson.SELECTED_KEY);//checks if the new time is being double booked
           if (lessonDataArray.EDIT_DOUBLE_BOOKED) {//checks if the lesson is double booked
               lessonDoubleBooked = true;//sets the lessonDoubleBooked boolean to true
               JOptionPane.showMessageDialog(null, la.checkIfDoublebooking(la.formatDate(this.addDateComboBox.getDate().toString()), this.minuteSpinner.getModel().getValue().toString(), Integer.parseInt(this.durationSpinner.getModel().getValue().toString()), ADDED_ARRAY.size(), lesson.SELECTED_KEY) + "\nPlease try again!");//alerts the user that the lesson is being double booked
           }
       }
        if (!lessonDoubleBooked) {//checks if the lesson is not douuble booked
            if (STUDENTS_CHECKED) {//checks if the students are being edited
                la.editSelectedLessonStudents(ADDED_ARRAY, lesson.SELECTED_LESSON_ID, lesson.SELECTED_LESSON_DATE, lesson.SELECTED_LESSON_TIME);//edits the students
            }
            if (VENUE_CHECKED) {//checks if the venue is being edited
                la.editSelectedLessonVenue(this.venuesComboBox.getSelectedItem().toString(), lesson.SELECTED_LESSON_DATE, lesson.SELECTED_LESSON_TIME);//edits the venue
            }
            if (DATETIME_CHECKED) {//checks if the date and time is being edited
                la.updateLessonDateTime(lesson.SELECTED_LESSON_DATE, lesson.SELECTED_LESSON_TIME, this.addDateComboBox.getDate().toString(), this.minuteSpinner.getModel().getValue().toString(), this.durationSpinner.getModel().getValue().toString());//edits the date and time
            }
            STUDENTS_CHECKED = false;//sets the students checked to false
            DATETIME_CHECKED = false;//sets the datetime checked to false
            VENUE_CHECKED = false;//sets the venue checked to false
            ADDED_ARRAY.removeAll(ADDED_ARRAY);//removes all items from the added array
            checkListModel.removeAllElements();//removes all elements from the checkListModel
            JOptionPane.showMessageDialog(null, "Lesson Edited");//alerts the user that the lesson was edited
            this.setVisible(false);//discontinues the current JFrame
        }
        STUDENTS_CHECKED = false;//sets the students checked to false
        DATETIME_CHECKED = false;//sets the datetime checked to false
        VENUE_CHECKED = false;//sets the venue checked to false
        ADDED_ARRAY.removeAll(ADDED_ARRAY);//removes all items from the added array
        checkListModel.removeAllElements();//removes all elements from the checkListModel
        la.sortArray();//sorts the lesson array
    }//GEN-LAST:event_editSelectedLessonButtonActionPerformed

    private void durationSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_durationSpinnerStateChanged
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        if (HOUR_CHOSEN) {//checks if an hour has been selected by the user
            if (this.minuteSpinner.getModel().getValue().toString().equals("")) {//checks if the user has selected a minute
                String timeSet = la.getLessonTimeFromStartTimeAndDuration(this.HourSpinner.getModel().getValue().toString() + ":00", Integer.parseInt(this.durationSpinner.getModel().getValue().toString()));//creates a string according to the minute chosen
                this.timeSetLabel.setText(timeSet);//sets the text of the timeSet Label to the timseSet string
            } else {//if there is only an hour selected
                String timeSet = la.getLessonTimeFromStartTimeAndDuration(this.minuteSpinner.getModel().getValue().toString(), Integer.parseInt(this.durationSpinner.getModel().getValue().toString()));//creates a string according to the hour chosen
                this.timeSetLabel.setText(timeSet);//sets the text of the timeSetLabel to the timeSet string
            }
        } else {//if the user has not yet selected an hour
            JOptionPane.showMessageDialog(null, "before choosing a duration please select a time");//alerts the use that they must first select an hour
        }
    }//GEN-LAST:event_durationSpinnerStateChanged

    private void HourSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_HourSpinnerStateChanged
        HOUR_CHOSEN = true;//sets the hourChosen to true indicating that the user has selected an hour
        populateComboBoxes pop = new populateComboBoxes();//creates an object for the lessonDataArray class
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        SpinnerListModel minuteModel = new SpinnerListModel(pop.populateMinuteComboBoxAccordingToHour(this.HourSpinner.getModel().getValue().toString()));//instantiates a spinnerListModel
        this.minuteSpinner.setModel(minuteModel);//sets the model of the minuteSpinner to the minuteModel
        String timeSet = la.getLessonTimeFromStartTimeAndDuration(this.minuteSpinner.getModel().getValue().toString(), Integer.parseInt(this.durationSpinner.getModel().getValue().toString()));//creates a string displaying the time according to the hour selected
        this.timeSetLabel.setText(timeSet);//sets the text of the timeSetLabel to the timeSet string
    }//GEN-LAST:event_HourSpinnerStateChanged

    private void minuteSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_minuteSpinnerStateChanged
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        String timeSet = la.getLessonTimeFromStartTimeAndDuration(this.minuteSpinner.getModel().getValue().toString(), Integer.parseInt(this.durationSpinner.getModel().getValue().toString()));//creates a string displaying the time according to the minute selected
        this.timeSetLabel.setText(timeSet);//sets the text of the timeSetLabel to the timeSet string
    }//GEN-LAST:event_minuteSpinnerStateChanged

    private void studentCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_studentCheckBoxStateChanged
        STUDENTS_CHECKED = !STUDENTS_CHECKED;//flips the students checked boolean
        if (STUDENTS_CHECKED) {//checks if the studentsChecked boolean is true indicating that the students are being edited
            this.editStudentPanel.setBackground(Color.green);//sets the background colour of the studentsPanel to green
        } else {//if the students are not being edited
            this.editStudentPanel.setBackground(Color.red);//sets the background colour of the studentsPanel to red
        }
    }//GEN-LAST:event_studentCheckBoxStateChanged

    private void dateTimeCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_dateTimeCheckBoxStateChanged
        DATETIME_CHECKED = !DATETIME_CHECKED;//flips the datetime checked boolean
        if (DATETIME_CHECKED) {//checks if the datettimeChecked boolean is true indicating that the date and time are being edited
            this.editDateTimePanel.setBackground(Color.green);//sets the background colour of the datetimePanel to green
            this.editAllLessonsButton.setVisible(false);
        } else {//if the date and time are not being edited
            this.editDateTimePanel.setBackground(Color.red);//sets the background colour of the dateTimePanel to red
            this.editAllLessonsButton.setVisible(true);
        }
    }//GEN-LAST:event_dateTimeCheckBoxStateChanged

    private void venueCheckboxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_venueCheckboxStateChanged
        VENUE_CHECKED = !VENUE_CHECKED;//flips the venue checked boolean
        if (VENUE_CHECKED) {//checks if the venueChecked boolean is true indicating that the venue is being edited
            this.venuePanel.setBackground(Color.green);//sets the background colour of the venuePanel to green
        } else {//if the venue is not being edited
            this.venuePanel.setBackground(Color.red);//sets the background colour of the venuePanel to red
        }
    }//GEN-LAST:event_venueCheckboxStateChanged

    private void editAllLessonsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editAllLessonsButtonActionPerformed
       lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
            if (VENUE_CHECKED) {//checks if the venue is being edited
                la.editAllLessonVenue(this.venuesComboBox.getSelectedItem().toString(), lesson.SELECTED_LESSON_DATE, lesson.SELECTED_LESSON_TIME);//edits the venue
            }
            if (STUDENTS_CHECKED) {//checks if the students are being edited
                la.editAllLessonStudents(ADDED_ARRAY, lesson.SELECTED_LESSON_ID, lesson.SELECTED_LESSON_DATE, lesson.SELECTED_LESSON_TIME);//edits the students
            }
            STUDENTS_CHECKED = false;//sets the students checked boolean to false
            VENUE_CHECKED = false;//sets the venue checked boolean to false
            ADDED_ARRAY.removeAll(ADDED_ARRAY);//removes all elements from the added_array array
            checkListModel.removeAllElements();//removes all elements from the checkListMsodel
            la.sortArray();//sorts the lessonDataArray array list
            this.setVisible(false);//discontinues the current JFrame 
    }//GEN-LAST:event_editAllLessonsButtonActionPerformed

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus

    }//GEN-LAST:event_formWindowLostFocus

    private void dateTimeCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateTimeCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateTimeCheckBoxActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(editLessonForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editLessonForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editLessonForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editLessonForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new editLessonForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner HourSpinner;
    private com.toedter.calendar.JDateChooser addDateComboBox;
    private com.toedter.calendar.JDateChooser addDateComboBox1;
    private javax.swing.JButton addLessonButton;
    private javax.swing.JComboBox<String> addLessonTimeHourComboBox1;
    private javax.swing.JComboBox<String> addLessonTimeMinuteComboBox1;
    private javax.swing.JComboBox<String> addStudentGradeComboBox;
    private javax.swing.JComboBox<String> addStudentGradeComboBox1;
    private javax.swing.JComboBox<String> addStudentNameComboBox;
    private javax.swing.JComboBox<String> addStudentNameComboBox1;
    private javax.swing.JButton addStudentToLessonButton;
    private javax.swing.JButton addStudentToLessonButton1;
    private javax.swing.JComboBox<String> addVenueComboBox;
    private javax.swing.JLabel addlessonLabel;
    private javax.swing.JButton backToDashboardButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JCheckBox dateTimeCheckBox;
    private javax.swing.JButton deleteStudentFromLessonButton;
    private javax.swing.JButton deleteStudentFromLessonButton1;
    private javax.swing.JSpinner durationSpinner;
    private javax.swing.JSpinner durationSpinner1;
    private javax.swing.JButton editAllLessonsButton;
    private javax.swing.JPanel editDateTimePanel;
    private javax.swing.JButton editSelectedLessonButton;
    private javax.swing.JPanel editStudentPanel;
    private javax.swing.JComboBox<String> frequencyComboBox1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner minuteSpinner;
    private javax.swing.JTextField studentBeingAddedTextField;
    private javax.swing.JTextField studentBeingAddedTextField1;
    private javax.swing.JCheckBox studentCheckBox;
    private javax.swing.JLabel studentsAddedLabel;
    private javax.swing.JList<String> studentsAddedList;
    private javax.swing.JList<String> studentsAddedList1;
    private javax.swing.JLabel timeSetLabel;
    private javax.swing.JLabel timeSetLabel1;
    private javax.swing.JCheckBox venueCheckbox;
    private javax.swing.JPanel venuePanel;
    private javax.swing.JComboBox<String> venuesComboBox;
    // End of variables declaration//GEN-END:variables
}
