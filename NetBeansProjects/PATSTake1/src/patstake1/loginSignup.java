/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patstake1;

import com.mindfusion.scheduling.awt.AwtCalendar;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author YishaiBasserabie
 */
public class loginSignup extends javax.swing.JFrame {

    public static String question, answer;
    public static boolean enteredQuestion = false;
    
    /**
     * Creates new form loginSignup
     */
    public loginSignup() {
        initComponents();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        passwordLableLogin = new javax.swing.JLabel();
        TPasswordTLogin = new javax.swing.JTextField();
        loginButtonLogin = new javax.swing.JButton();
        fpass = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        fnameLabelSignUp = new javax.swing.JLabel();
        lnameLabelSignUp = new javax.swing.JLabel();
        emailLabelSignUp = new javax.swing.JLabel();
        cellLabelSignUp = new javax.swing.JLabel();
        passwordLabelSignUp = new javax.swing.JLabel();
        passwordConfirmLabelSignUp = new javax.swing.JLabel();
        TfnameT = new javax.swing.JTextField();
        TlnameT = new javax.swing.JTextField();
        TcellT = new javax.swing.JTextField();
        TemailT = new javax.swing.JTextField();
        TpasswordT = new javax.swing.JTextField();
        TpasswordConfirmT = new javax.swing.JTextField();
        signUpButton = new javax.swing.JButton();
        squestion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        passwordLableLogin.setText("password");

        TPasswordTLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPasswordTLoginKeyPressed(evt);
            }
        });

        loginButtonLogin.setText("login!");
        loginButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonLoginActionPerformed(evt);
            }
        });
        loginButtonLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                loginButtonLoginKeyPressed(evt);
            }
        });

        fpass.setText("Forgot Password");
        fpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fpassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addComponent(passwordLableLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TPasswordTLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(358, 358, 358)
                        .addComponent(loginButtonLogin))
                    .addComponent(fpass))
                .addContainerGap(267, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLableLogin)
                    .addComponent(TPasswordTLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(loginButtonLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(fpass))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("login!", jPanel2);

        fnameLabelSignUp.setText("first name");

        lnameLabelSignUp.setText("last name");

        emailLabelSignUp.setText("email");

        cellLabelSignUp.setText("cell");

        passwordLabelSignUp.setText("password");

        passwordConfirmLabelSignUp.setText("password");

        TlnameT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TlnameTActionPerformed(evt);
            }
        });

        TcellT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TcellTActionPerformed(evt);
            }
        });

        TpasswordConfirmT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TpasswordConfirmTActionPerformed(evt);
            }
        });

        signUpButton.setText("sign up!");
        signUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUpButtonActionPerformed(evt);
            }
        });

        squestion.setText("add security question");
        squestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                squestionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(passwordConfirmLabelSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordLabelSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cellLabelSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emailLabelSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lnameLabelSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fnameLabelSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TfnameT, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(TpasswordConfirmT, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(squestion))
                            .addComponent(TpasswordT, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TcellT, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TemailT, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TlnameT, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(358, 358, 358)
                        .addComponent(signUpButton)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fnameLabelSignUp)
                    .addComponent(TfnameT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lnameLabelSignUp)
                    .addComponent(TlnameT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(emailLabelSignUp)
                    .addComponent(TemailT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cellLabelSignUp)
                    .addComponent(TcellT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(passwordLabelSignUp)
                    .addComponent(TpasswordT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(passwordConfirmLabelSignUp)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TpasswordConfirmT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(squestion)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(signUpButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("sign up!", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TlnameTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TlnameTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TlnameTActionPerformed

    private void TcellTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TcellTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TcellTActionPerformed

    private void TpasswordConfirmTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TpasswordConfirmTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TpasswordConfirmTActionPerformed

    private void signUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpButtonActionPerformed
        //TODO: test this and fix validation  
        loginSignUpHandler lsh = new loginSignUpHandler();
        if (enteredQuestion) {
            lsh.signUp(this.TfnameT.getText().toString(), this.TlnameT.getText().toString(), 
                  this.TemailT.getText().toString(), this.TcellT.getText().toString(), 
                  this.TpasswordT.getText().toString(), this.TpasswordConfirmT.getText().toString(), question, answer);
          if (loginSignUpHandler.allGood) {
                this.setVisible(false);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a security\nquestion and answer.");
        }
    }//GEN-LAST:event_signUpButtonActionPerformed

    private void loginButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonLoginActionPerformed
          loginSignUpHandler lsh = new loginSignUpHandler();
          lsh.login(this.TPasswordTLogin.getText().toString());
          if (loginSignUpHandler.allGood) {
                this.setVisible(false);
            }
    }//GEN-LAST:event_loginButtonLoginActionPerformed

    private void loginButtonLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_loginButtonLoginKeyPressed

    }//GEN-LAST:event_loginButtonLoginKeyPressed

    private void TPasswordTLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasswordTLoginKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            loginSignUpHandler lsh = new loginSignUpHandler();
            lsh.login(this.TPasswordTLogin.getText().toString());
            if (loginSignUpHandler.allGood) {
                this.setVisible(false);
            }
        }
    }//GEN-LAST:event_TPasswordTLoginKeyPressed

    private void fpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fpassActionPerformed
        loginSignUpHandler lsh = new loginSignUpHandler();
        lsh.forgotPassword();
    }//GEN-LAST:event_fpassActionPerformed

    private void squestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_squestionActionPerformed
        loginSignUpHandler lsh = new loginSignUpHandler();
        lsh.getSecurityAnswer();
    }//GEN-LAST:event_squestionActionPerformed

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
            java.util.logging.Logger.getLogger(loginSignup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(loginSignup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(loginSignup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(loginSignup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loginSignup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TPasswordTLogin;
    private javax.swing.JTextField TcellT;
    private javax.swing.JTextField TemailT;
    private javax.swing.JTextField TfnameT;
    private javax.swing.JTextField TlnameT;
    private javax.swing.JTextField TpasswordConfirmT;
    private javax.swing.JTextField TpasswordT;
    private javax.swing.JLabel cellLabelSignUp;
    private javax.swing.JLabel emailLabelSignUp;
    private javax.swing.JLabel fnameLabelSignUp;
    private javax.swing.JButton fpass;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel lnameLabelSignUp;
    private javax.swing.JButton loginButtonLogin;
    private javax.swing.JLabel passwordConfirmLabelSignUp;
    private javax.swing.JLabel passwordLabelSignUp;
    private javax.swing.JLabel passwordLableLogin;
    private javax.swing.JButton signUpButton;
    private javax.swing.JButton squestion;
    // End of variables declaration//GEN-END:variables
}
