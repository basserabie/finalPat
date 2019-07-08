/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtraLessonsMain;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author YishaiBasserabie
 */
public class lesson extends javax.swing.JFrame {//creates a class to handle the displaying of the lessons

    public static int SELECTED_LESSON_ID;//creates a static integer to hold the selected lesson id
    public static String SELECTED_LESSON_DATE;//creates a static integer to hold the selected lesson date
    public static String SELECTED_LESSON_TIME;//creates a static integer to hold the selected lesson time
    public static String SELECTED_KEY;//creates a static integer to hold the selected lesson key
    
    /**
     * Creates new form lesson
     */
    public lesson() {//creates the constructor for the current class
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//sets the default close operation of the current JFRame to dispose
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        la.sortArray();//sorts the lessonDataArray lessonData Array list
        this.selectedLessonLabelReal.setText("----------------");//sets the default text of the selectedLessonsLabelReal button
        this.seeStudentsButton.setText("---------");//sets the default text of the seeStudentsButton
        populateComboBoxes pop = new populateComboBoxes();//creates an object for the populateComboBoxes class
        this.searchComboBox.removeAllItems();//removes all items from the searchComboBox
        this.filterTypeStudent.removeAllItems();//removes all items from the filerTypeStudent comboBox
        this.filterTypeStudent.addItem("Use Date Picker");//adds an item to the filterTypeStudent combo box instructing the user to use the date chooser
        this.searchComboBox.addItem("Use Date Picker");//adds an item to the searchComboBox combo box instructing the user to use the date chooser
        this.searchTextField.setText("Use Date Picker");//adds an item to the searchTextField instructing the user to use the date chooser
        this.lessonsTable.setModel(pop.Lessons());//sets the model of the lessonsTable to the lessons table model from the populateComboBoxes class
        DefaultComboBoxModel primarySearch = new DefaultComboBoxModel(pop.populatePrimaryFilterTpeLessonsComboBox());//creates a combo box model of the search/filter options
        this.filterTypeComboBox.setModel(primarySearch);//sets the filterTypeComboBox model to the primarySearch model
    }//closes the constructor
    
//    public void setTableModel(DefaultTableModel model) {
//        this.lessonsTable.setModel(model);
//    } 

//    public JButton getSeeStudentsButton() {
//        return seeStudentsButton;
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        selectedLessonLabelReal = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        backToDashboardButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        filterTypeComboBox = new javax.swing.JComboBox<>();
        dateChooser = new com.toedter.calendar.JDateChooser();
        searchByDate = new javax.swing.JButton();
        searchComboBox = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        filterTypeStudent = new javax.swing.JComboBox<>();
        searchTextField = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        editLesson = new javax.swing.JButton();
        deleteLesson = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        seeStudentsButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lessonsTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jPanel3.setBackground(new java.awt.Color(153, 204, 255));

        jPanel4.setBackground(new java.awt.Color(255, 204, 255));

        jLabel1.setText("lessons");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 204, 255));

        selectedLessonLabelReal.setText("jLabel5");

        jLabel4.setText("Selected Lesson:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectedLessonLabelReal, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(selectedLessonLabelReal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        backToDashboardButton.setText("back");
        backToDashboardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToDashboardButtonActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Search By:");

        filterTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        filterTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterTypeComboBoxActionPerformed(evt);
            }
        });

        searchByDate.setText("Go Date");
        searchByDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchByDateActionPerformed(evt);
            }
        });

        searchComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        searchComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(filterTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchByDate)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchByDate)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filterTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 204, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setText("Secondary search:");

        filterTypeStudent.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        searchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextFieldActionPerformed(evt);
            }
        });
        searchTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchTextFieldKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filterTypeStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterTypeStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        editLesson.setText("Edit Lesson");
        editLesson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editLessonActionPerformed(evt);
            }
        });

        deleteLesson.setText("Delete Lesson");
        deleteLesson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteLessonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(editLesson, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteLesson, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(editLesson)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteLesson)
                .addContainerGap())
        );

        reset.setText("Display All");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        seeStudentsButton.setText("See Students!");
        seeStudentsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seeStudentsButtonActionPerformed(evt);
            }
        });

        lessonsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        lessonsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lessonsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lessonsTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 977, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(backToDashboardButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(seeStudentsButton)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(backToDashboardButton))
                        .addGap(20, 20, 20)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(reset)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(seeStudentsButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backToDashboardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToDashboardButtonActionPerformed
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        la.sortArray();//sorts the lessonDataArray array list of lesson objects
        this.setVisible(false);//discontinues the current JFrame
    }//GEN-LAST:event_backToDashboardButtonActionPerformed

    private void searchTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyTyped
        populateComboBoxes pop = new populateComboBoxes();//creates an object for the populateComboBoxes class
        switch (this.filterTypeStudent.getSelectedItem().toString()) {//starts a switchcase statement according to the filter type chosen by the user
            case "Student Name"://opens the case of student name filter type
                this.lessonsTable.setModel(pop.LessonsByStudentName(this.searchTextField.getText()));//sets the model of the lessonsTable to the filtered version of the lessons tablle model
                break;//discontinues the current case
            case "Mother Name"://opens the case of parent name filter type
                this.lessonsTable.setModel(pop.LessonsByParentSName(this.searchTextField.getText()));//sets the model of the lessonsTable to the filtered version of the lessons tablle model
                break;//discontinues the current case
            case "School"://opens the case of school name filter type
                this.lessonsTable.setModel(pop.LessonsBySchool(this.searchTextField.getText()));//sets the model of the lessonsTable to the filtered version of the lessons tablle model
        }//closes the switchcase statement
    }//GEN-LAST:event_searchTextFieldKeyTyped

    private void filterTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterTypeComboBoxActionPerformed
        populateComboBoxes pop = new populateComboBoxes();//creates an object for the populateComboNBoxes class
        switch (this.filterTypeComboBox.getSelectedItem().toString()) {//opens a switchcase statement according to the filter chosen by the user
            case "Date"://opens the case of date filter type
                String dateChosenCorrection [] = {"Use Date Picker"};//creates a string array with an item instructing the user to use the date picker
                DefaultComboBoxModel dateChosenModel = new DefaultComboBoxModel(dateChosenCorrection);//creates a combobox model according to the dateChosenCorrection string array
                this.searchComboBox.setModel(dateChosenModel);//sets the model of the combobox to the above comboBoxModel
                this.filterTypeStudent.setModel(dateChosenModel);//sets the model of the combobox to the above comboBoxModel
                this.searchTextField.setText(dateChosenCorrection[0]);//sets the text of the searchTextField to the first item of the dateChosejCorrection
                this.lessonsTable.setModel(pop.Lessons());//sets the model of the lessons table to the lessons table model in the populateComboBoxes class
                break;//discontinues the current case
            case "Venue"://opens the case of venue filter type
                String venueChosenCorrection [] = {"Use Venue Box"};//creates a string array with an item instructing the user to use the venue combobox
                DefaultComboBoxModel venueChosenModel = new DefaultComboBoxModel(venueChosenCorrection);//creates a combobox model according to the VenueChosenCorrection string array
                this.filterTypeStudent.setModel(venueChosenModel);//sets the model of the combobox to the above comboBoxModel
                this.searchTextField.setText(venueChosenCorrection[0]);//sets the model of the combobox to the above comboBoxModel
                DefaultComboBoxModel venuesModel = new DefaultComboBoxModel(pop.populateVenues());//creates a combo box model according to the venues in the database
                this.searchComboBox.setModel(venuesModel);//sets the model searchComboBox to the venuesModel
                this.lessonsTable.setModel(pop.Lessons());//sets the model of the lessons table to the lessons table model in the populateComboBoxes class
                break;//discontinues the current case
            case "Student"://opens the case of student filter type
                String studentNameChosenCorrection [] = {"Use Student Block"};//creates a string array with an item instructing the user to use the student bordered panel
                DefaultComboBoxModel studentNameChosenModel = new DefaultComboBoxModel(studentNameChosenCorrection);//creates a combobox model according to the studentChosenCorrection string array
                this.searchComboBox.setModel(studentNameChosenModel);//sets the model of the combobox to the above comboBoxModel
                DefaultComboBoxModel studentPickerModel = new DefaultComboBoxModel(pop.populateStudentFilterTypeComboBox());
                this.filterTypeStudent.setModel(studentPickerModel);//sets the model of the combobox to the above comboBoxModel
                this.searchTextField.setText("");//sets the text of the searchTextField to blank
                this.lessonsTable.setModel(pop.Lessons());//sets the model of the lessons table to the lessons table model in the populateComboBoxes class
                break;//discontinues the current case
        }//closes the switchcase statement
    }//GEN-LAST:event_filterTypeComboBoxActionPerformed

    private void searchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextFieldActionPerformed
        
    }//GEN-LAST:event_searchTextFieldActionPerformed

    private void searchComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchComboBoxActionPerformed
       populateComboBoxes pop = new populateComboBoxes();//creates an object for the populateComboBoxes class
       if (this.filterTypeComboBox.getSelectedItem().toString().equals("Venue")) {//checks if user is filtering by venue
           this.lessonsTable.setModel(pop.LessonsByVenue(this.searchComboBox.getSelectedItem().toString()));//sets the model to the filtered version of the lesson table model
       }
    }//GEN-LAST:event_searchComboBoxActionPerformed

    private void searchByDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchByDateActionPerformed
        populateComboBoxes poop = new populateComboBoxes();//creates an object for the populateComboBoxes class
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        String date = la.formatDate(this.dateChooser.getDate().toString());//creates a string to hold a formatted version of the date selected
        if (!(this.dateChooser.getDate().toString()).equals("")) {//checks that the dateChooser is not null i.e. the user has not selected a date
            this.lessonsTable.setModel(poop.LessonsByDate(date));//filters the tabel according to the date selected
        } else {//if the user has not yet selected a date
            JOptionPane.showMessageDialog(null, "please select a date first\nor ensure that the filter type is set to date.");//alerts the user to selected a date first
        }
    }//GEN-LAST:event_searchByDateActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        populateComboBoxes pop = new populateComboBoxes();//creates an object for the populateComboBoxes class
        this.lessonsTable.setModel(pop.Lessons());//resets the lessonstable to the unfiltered lessons table model from the populateComboBoxes class
    }//GEN-LAST:event_resetActionPerformed

    private void lessonsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lessonsTableMouseClicked
        populateComboBoxes pop = new populateComboBoxes();//creates an object for the populateComboBoxes class
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        keysArray ka = new keysArray();//
        this.seeStudentsButton.setText("See Students!");
        String date = this.lessonsTable.getModel().getValueAt(this.lessonsTable.getSelectedRow(), 2).toString();
        int studentID = sa.studentIDFromName(this.lessonsTable.getModel().getValueAt(this.lessonsTable.getSelectedRow(), 0).toString());
        String time = this.lessonsTable.getModel().getValueAt(this.lessonsTable.getSelectedRow(), 3).toString().substring(0, 5);
        SELECTED_LESSON_ID = la.getLessoIDFromDateTimeAndStudentID(date, time, studentID);
        SELECTED_LESSON_DATE = date;
        SELECTED_LESSON_TIME = time;
        SELECTED_KEY = ka.getKeyFromLessonID(SELECTED_LESSON_ID);
        this.selectedLessonLabelReal.setText(pop.populateSelectedLessonLabel(SELECTED_LESSON_ID));
    }//GEN-LAST:event_lessonsTableMouseClicked

    private void seeStudentsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seeStudentsButtonActionPerformed
     seeStudentsForm ssf = new seeStudentsForm();
     if (!this.lessonsTable.getSelectionModel().isSelectionEmpty()) {
         ssf.setLocation((this.seeStudentsButton.getLocationOnScreen().x - 358), (this.seeStudentsButton.getLocationOnScreen().y));
         ssf.setVisible(true);
     } else {
         JOptionPane.showMessageDialog(null, "Please Select A Lesson (row) First");
     }
    }//GEN-LAST:event_seeStudentsButtonActionPerformed

    private void editLessonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editLessonActionPerformed
        if (!this.lessonsTable.getSelectionModel().isSelectionEmpty()) {
            editLessonForm elf = new editLessonForm();
            elf.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a lesson first");
        }
    }//GEN-LAST:event_editLessonActionPerformed

    private void deleteLessonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteLessonActionPerformed
        if (!this.lessonsTable.getSelectionModel().isSelectionEmpty()) {
            lessonDataArray la = new lessonDataArray();
            populateComboBoxes pop = new populateComboBoxes();//creates an object for the populateComboBoxes class
            la.deleteLesson(SELECTED_LESSON_ID, SELECTED_LESSON_DATE, SELECTED_LESSON_TIME, pop.populateSelectedLessonLabel(SELECTED_LESSON_ID));
            this.lessonsTable.setModel(pop.Lessons());
        } else {
            JOptionPane.showMessageDialog(null, "Please select a lesson first");
        }
    }//GEN-LAST:event_deleteLessonActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        populateComboBoxes pop = new populateComboBoxes();//creates an object for the populateComboBoxes class
        this.lessonsTable.setModel(pop.Lessons());
    }//GEN-LAST:event_formWindowGainedFocus

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
            java.util.logging.Logger.getLogger(lesson.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(lesson.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(lesson.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(lesson.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new lesson().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backToDashboardButton;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JButton deleteLesson;
    private javax.swing.JButton editLesson;
    private javax.swing.JComboBox<String> filterTypeComboBox;
    private javax.swing.JComboBox<String> filterTypeStudent;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable lessonsTable;
    private javax.swing.JButton reset;
    private javax.swing.JButton searchByDate;
    private javax.swing.JComboBox<String> searchComboBox;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JButton seeStudentsButton;
    private javax.swing.JLabel selectedLessonLabelReal;
    // End of variables declaration//GEN-END:variables
}
