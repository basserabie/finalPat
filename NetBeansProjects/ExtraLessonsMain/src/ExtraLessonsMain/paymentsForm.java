/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtraLessonsMain;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SpinnerListModel;

/**
 *
 * @author YishaiBasserabie
 */
public class paymentsForm extends javax.swing.JFrame {//creates a class to handle the payments form

    private static int TOGGLE_PAID = 1;//creates an integer for the toggle display paid button
    private static boolean TOGGLE_DISPLAY_ALL = true;//creats a boolean for the display all toggle button
    private static boolean HOUR_CHOSEN = false;//creates a boolean indicating whether an hour has been selected
    
    /**
     * Creates new form paymentsForm
     */
    public paymentsForm() {//creates the constructor
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//sets the close operation to dispose
        populateComboBoxes pop = new populateComboBoxes();//creates an object for the populateComboBoxes mehtod
        this.paymetsTable.setModel(pop.payments());//sets the model of the payments table to the payments model
    }//closes the constructor

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        paymetsTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        studentBeingAddedTextField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        dateChooser = new com.toedter.calendar.JDateChooser();
        GoByDateAndTime = new javax.swing.JButton();
        deletePastAndUnpaidButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        dispayAllButton = new javax.swing.JButton();
        togglePaidButton = new javax.swing.JToggleButton();
        statsButton = new javax.swing.JButton();
        paymentsByLessonButton = new javax.swing.JButton();
        backToDashBoard = new javax.swing.JButton();
        DeleteAllPast = new javax.swing.JButton();
        addPayementButton = new javax.swing.JButton();
        removePaymentButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jPanel4.setBackground(new java.awt.Color(153, 204, 255));

        jPanel5.setBackground(new java.awt.Color(255, 204, 255));

        jLabel1.setText("Payments");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        paymetsTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(paymetsTable);

        jPanel3.setBackground(new java.awt.Color(255, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setText("Search By Student Name:");

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(studentBeingAddedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(studentBeingAddedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Search By Lesson Date And time:");

        dateChooser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dateChooserKeyPressed(evt);
            }
        });

        GoByDateAndTime.setText("Go");
        GoByDateAndTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GoByDateAndTimeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(GoByDateAndTime, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GoByDateAndTime))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        deletePastAndUnpaidButton.setText("Delete Past and Unpaid");
        deletePastAndUnpaidButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePastAndUnpaidButtonActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        dispayAllButton.setText("Display All");
        dispayAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispayAllButtonActionPerformed(evt);
            }
        });

        togglePaidButton.setText("DisplayPaid");
        togglePaidButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                togglePaidButtonStateChanged(evt);
            }
        });
        togglePaidButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                togglePaidButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dispayAllButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(togglePaidButton)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dispayAllButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(togglePaidButton))
        );

        statsButton.setText("Stats");
        statsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statsButtonActionPerformed(evt);
            }
        });

        paymentsByLessonButton.setText("lesson");
        paymentsByLessonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentsByLessonButtonActionPerformed(evt);
            }
        });

        backToDashBoard.setText("Back");
        backToDashBoard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToDashBoardActionPerformed(evt);
            }
        });

        DeleteAllPast.setText("Delete All Past Lessons");
        DeleteAllPast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteAllPastActionPerformed(evt);
            }
        });

        addPayementButton.setText("Add Payment");
        addPayementButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPayementButtonActionPerformed(evt);
            }
        });

        removePaymentButton.setText("Remove Payment");
        removePaymentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removePaymentButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deletePastAndUnpaidButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DeleteAllPast, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(addPayementButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removePaymentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statsButton)
                    .addComponent(paymentsByLessonButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(backToDashBoard)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1002, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backToDashBoard))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(statsButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(paymentsByLessonButton)
                        .addGap(24, 24, 24))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(DeleteAllPast)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(deletePastAndUnpaidButton))
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addPayementButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(removePaymentButton))))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backToDashBoardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToDashBoardActionPerformed
        this.hide();//discontinues the current JFrame
    }//GEN-LAST:event_backToDashBoardActionPerformed

    private void togglePaidButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_togglePaidButtonStateChanged
        populateComboBoxes pop = new populateComboBoxes();//creates an object for the populateComboBoxes mehtod
        TOGGLE_PAID++;//ups the paid toggle
        if (TOGGLE_PAID%2 == 0) {//checks if the pay toggle is even
            this.paymetsTable.setModel(pop.paymentsPaid());//sets the model of the payments table to the paid model
            this.togglePaidButton.setText("Display Not Paid");//sets the text of the toggle button to diplay not paid
        } else {//the pay toggle is odd
            this.paymetsTable.setModel(pop.paymentsNotPaid());//sets the model of the payments table to the unpaid model
            this.togglePaidButton.setText("Display Paid");//sets the text of the toggle button to diplay not paid
        }
        TOGGLE_DISPLAY_ALL = false;//sets the display all to false
    }//GEN-LAST:event_togglePaidButtonStateChanged

    private void dispayAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dispayAllButtonActionPerformed
        populateComboBoxes pop = new populateComboBoxes();//creates an object for the populateComboBoxes mehtod
        this.paymetsTable.setModel(pop.payments());//resets the payments table
        TOGGLE_DISPLAY_ALL = true;//sets the display all to true
    }//GEN-LAST:event_dispayAllButtonActionPerformed

    private void addPayementButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPayementButtonActionPerformed
        if (!this.paymetsTable.getSelectionModel().isSelectionEmpty()) {//checks if there is a selected payment
            Sound.playcoin();//plays the coin sound
            paymentsArray pa = new paymentsArray();//creates an object for the paymentsArray class
        studentsArray sa = new studentsArray();//creates an object for the studentsArray class
        populateComboBoxes pop = new populateComboBoxes();//creates an object for the populateComboBoxes mehtod
        String studentName = this.paymetsTable.getModel().getValueAt(this.paymetsTable.getSelectedRow(), 0).toString();//creates a string for the selected student name
        int studentID = sa.studentIDFromName(pa.formattOutHTMLTags(studentName));//creates an int for the selected student id
        String date = pa.formattOutHTMLTags(this.paymetsTable.getModel().getValueAt(this.paymetsTable.getSelectedRow(), 1).toString());//creates a string for the selected date
        String time = pa.formattOutHTMLTags(this.paymetsTable.getModel().getValueAt(this.paymetsTable.getSelectedRow(), 2).toString()).substring(0, 5);//creates a string for the selected time
        pa.addPayment(pa.getLessonPayIDFromDateTimeStudentID(date, time, studentID));//adds the payment
        this.paymetsTable.setModel(pop.payments());
        } else {//if the user has not selected a payment
            JOptionPane.showMessageDialog(null, "Please select a row first.");//instructs the user to first select a row
        }
    }//GEN-LAST:event_addPayementButtonActionPerformed

    private void removePaymentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removePaymentButtonActionPerformed
        if (!this.paymetsTable.getSelectionModel().isSelectionEmpty()) {//checks if there is a selected payment
            Sound.playbumb();//plays the bump sound
            paymentsArray pa = new paymentsArray();//creates an object for the paymentsArray class
            studentsArray sa = new studentsArray();//creates an object for the studentsArray class
            populateComboBoxes pop = new populateComboBoxes();//creates an object for the populateComboBoxes mehtod
            String studentName = this.paymetsTable.getModel().getValueAt(this.paymetsTable.getSelectedRow(), 0).toString();//creates a string for the selected student name
            int studentID = sa.studentIDFromName(pa.formattOutHTMLTags(studentName));//creates an int for the selected student id
            String date = pa.formattOutHTMLTags(this.paymetsTable.getModel().getValueAt(this.paymetsTable.getSelectedRow(), 1).toString());//creates a string for the selected date
            String time = pa.formattOutHTMLTags(this.paymetsTable.getModel().getValueAt(this.paymetsTable.getSelectedRow(), 2).toString());//creates a string for the selected time
            pa.removePayment(pa.getLessonPayIDFromDateTimeStudentID(date, time, studentID));
            this.paymetsTable.setModel(pop.payments());//removes the payment
        } else {//if the user has not selected a payment
            JOptionPane.showMessageDialog(null, "Please select a row first.");//instructs the user to first select a row
        }
    }//GEN-LAST:event_removePaymentButtonActionPerformed

    private void studentBeingAddedTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentBeingAddedTextFieldActionPerformed
        
    }//GEN-LAST:event_studentBeingAddedTextFieldActionPerformed

    private void studentBeingAddedTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentBeingAddedTextFieldKeyTyped

    }//GEN-LAST:event_studentBeingAddedTextFieldKeyTyped

    private void studentBeingAddedTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentBeingAddedTextFieldKeyPressed
        populateComboBoxes pop = new populateComboBoxes();//creates an object for the populateComboBoxes mehtod
        if (TOGGLE_DISPLAY_ALL) {//checks if the display all is selected
            this.paymetsTable.setModel(pop.paymentsByStudentName(this.studentBeingAddedTextField.getText()));//sets the model filtered by name and display all
        } else {//if display all is false
            if (TOGGLE_PAID%2 == 0) {//if the paid is selected
                this.paymetsTable.setModel(pop.paymentsPaidByStudentName(this.studentBeingAddedTextField.getText()));//sets the model filtered by name and paid
            } else {//if the unpaid is selected
                this.paymetsTable.setModel(pop.paymentsNotPaidByStudentName(this.studentBeingAddedTextField.getText()));//sets the model filtered by name and unpaid
            }
        }
    }//GEN-LAST:event_studentBeingAddedTextFieldKeyPressed

    private void togglePaidButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_togglePaidButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_togglePaidButtonActionPerformed

    private void GoByDateAndTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GoByDateAndTimeActionPerformed
        populateComboBoxes pop = new populateComboBoxes();//creates an object for the populateComboBoxes mehtod
        lessonDataArray la = new lessonDataArray();//creates an object for the lessonDataArray class
        String date = la.formatDate(this.dateChooser.getDate().toString());//creates a string for the chosen date
        if (TOGGLE_DISPLAY_ALL) {//checks if the display all is selected
            this.paymetsTable.setModel(pop.paymentsByDateAndTime(date));//sets the model filtered by date and display all
        } else {//if display all is false
            if (TOGGLE_PAID%2 == 0) {
                this.paymetsTable.setModel(pop.paymentsPaidByDateAndTime(date));//sets the model filtered by date and paid
            } else {//if the unpaid is selected
                this.paymetsTable.setModel(pop.paymentsNotPaidByDateAndTime(date));//sets the model filtered by date and unpaid
            }
        }
    }//GEN-LAST:event_GoByDateAndTimeActionPerformed

    private void dateChooserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateChooserKeyPressed
        this.studentBeingAddedTextField.setText("");//sets the students text field to null
    }//GEN-LAST:event_dateChooserKeyPressed

    private void statsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statsButtonActionPerformed
        paymentStatsForm stats = new paymentStatsForm();//creates an object for the paymentStatsForm class
        stats.setVisible(true);//sets the payment stats JFrame visible
    }//GEN-LAST:event_statsButtonActionPerformed

    private void deletePastAndUnpaidButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePastAndUnpaidButtonActionPerformed
        paymentsArray pa = new paymentsArray();//creates an object for the payments array class
        populateComboBoxes pop = new populateComboBoxes();//creates an object for the populateComboBoxes mehtod
        pa.deletePastAndunpaidPayments();//deletes past and unpaid payments
        this.paymetsTable.setModel(pop.payments());//resets the table model
    }//GEN-LAST:event_deletePastAndUnpaidButtonActionPerformed

    private void paymentsByLessonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentsByLessonButtonActionPerformed
        paymentsByLessonForm pblf = new paymentsByLessonForm();//creates an object for the paymentsByLessonForm class
        pblf.setVisible(true);// sets the paymentsByLessonForm JFrame visible
    }//GEN-LAST:event_paymentsByLessonButtonActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
         populateComboBoxes pop = new populateComboBoxes();//creates an object for the populateComboBoxes mehtod
        this.paymetsTable.setModel(pop.payments());//resets the table model
    }//GEN-LAST:event_formWindowGainedFocus

    private void DeleteAllPastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteAllPastActionPerformed
        paymentsArray pa = new paymentsArray();//creates an object for the payments array class
        populateComboBoxes pop = new populateComboBoxes();//creates an object for the populateComboBoxes mehtod
        pa.deletePast();//deletes past payments
        this.paymetsTable.setModel(pop.payments());//resets the table model
    }//GEN-LAST:event_DeleteAllPastActionPerformed

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
            java.util.logging.Logger.getLogger(paymentsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(paymentsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(paymentsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(paymentsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new paymentsForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DeleteAllPast;
    private javax.swing.JButton GoByDateAndTime;
    private javax.swing.JButton addPayementButton;
    private javax.swing.JButton backToDashBoard;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JButton deletePastAndUnpaidButton;
    private javax.swing.JButton dispayAllButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton paymentsByLessonButton;
    private javax.swing.JTable paymetsTable;
    private javax.swing.JButton removePaymentButton;
    private javax.swing.JButton statsButton;
    private javax.swing.JTextField studentBeingAddedTextField;
    private javax.swing.JToggleButton togglePaidButton;
    // End of variables declaration//GEN-END:variables
}
