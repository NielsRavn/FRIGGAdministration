/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Frames;

import BE.Employee;
import BLL.Employee_AccessLink;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Niels
 */
public class NewEmployeeDialog extends javax.swing.JDialog {

    Employee employee;
    Employee_AccessLink eal;

    /**
     * Creates new form NewEmployeeDialog
     */
    public NewEmployeeDialog(java.awt.Frame parent, boolean modal) {
        super(parent, "Ny Medarbejder", modal);
        initComponents();
        tfId.addKeyListener(new myKeyListener());
        try {
            eal = new Employee_AccessLink();
            employee = null;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Der er sket en fejl. /r/n Forsætter fejlen kontakt da administartion med følgende fejl /r/n" + ex);

        }
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        btnMedarbejder = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        tfId = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnMedarbejder.setText("Opret medarbejder");
        btnMedarbejder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMedarbejderActionPerformed(evt);
            }
        });
        jPanel3.add(btnMedarbejder);

        btnCancel.setText("Annuller");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel3.add(btnCancel);

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setText("Skriv et login id til den nye medarbejder");
        jPanel1.add(jLabel1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        tfId.setPreferredSize(new java.awt.Dimension(50, 20));
        jPanel2.add(tfId);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMedarbejderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMedarbejderActionPerformed
        createNewEmployee();
    }//GEN-LAST:event_btnMedarbejderActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        employee = null;
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnMedarbejder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField tfId;
    // End of variables declaration//GEN-END:variables

    /**
     * checks if the new employee id exists in the database, if not it will set
     * the employee of this to a new employee with the inputted id and dispose.
     */
    public void createNewEmployee() {
        int id = Integer.parseInt(tfId.getText());
        employee = new Employee(id);
        try {
            if (eal.checkNewEmployeeId(id)) {
                dispose();
            } else {
                employee = null;
                JOptionPane.showMessageDialog(rootPane, "Id'et er allerede brugt");
            }
        } catch (SQLException ex) {
            employee = null;
            JOptionPane.showMessageDialog(null,"Der er sket en fejl. /r/n Forsætter fejlen kontakt da administartion med følgende fejl /r/n" + ex);

        }
    }

    /**
     * @return the employee set in this object, if null no new employee should
     * be created.
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * keylistener to listen for enter key, and consume inputs that are not
     * numbers.
     */
    private class myKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                createNewEmployee();
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
            if (!Character.isDigit(e.getKeyChar()) || tfId.getText().length() >= 6) {
                e.consume();
            }
        }
    }

}
