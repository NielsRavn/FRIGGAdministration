/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Frames;

import BE.Employee;
import BLL.Employee_AccessLink;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.RowSorter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Niels
 */
public class EmployeeAdministrationPanel extends javax.swing.JPanel {
    
    Employee_AccessLink eal;
    EmployeeAdministrationTableModel eatm;
    RowSorter<EmployeeAdministrationTableModel> rowSorter;
    JFrame parent;
    
    /**
     * Creates new form EmployeeAdministrationPanel
     */
    public EmployeeAdministrationPanel(JFrame parent) {
        this.parent = parent;
        initComponents();
        try {
            eal = new Employee_AccessLink();
        } catch (IOException ex) {
        }
        tblEmployee.getTableHeader().setReorderingAllowed(false);
        eatm = new EmployeeAdministrationTableModel();
        rowSorter = new TableRowSorter<EmployeeAdministrationTableModel>(eatm);
        tblEmployee.setModel(eatm);
        tblEmployee.setRowSorter(rowSorter);
        search();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmployee = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfSearch = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnNewEmployee = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnBackwards = new javax.swing.JButton();
        btnForward = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        tblEmployee.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblEmployee);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jLabel1.setText("Søge text:");
        jPanel1.add(jLabel1);

        tfSearch.setPreferredSize(new java.awt.Dimension(150, 20));
        tfSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSearchKeyReleased(evt);
            }
        });
        jPanel1.add(tfSearch);

        add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnNewEmployee.setText("New Employee");
        btnNewEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewEmployeeActionPerformed(evt);
            }
        });
        jPanel3.add(btnNewEmployee);

        jPanel2.add(jPanel3, java.awt.BorderLayout.EAST);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        btnBackwards.setText("<--");
        btnBackwards.setToolTipText(" Fortryd sidste indtastning");
        btnBackwards.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackwardsActionPerformed(evt);
            }
        });
        jPanel4.add(btnBackwards);

        btnForward.setText("-->");
        btnForward.setToolTipText("Insæt fortrudt text igen");
        btnForward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnForwardActionPerformed(evt);
            }
        });
        jPanel4.add(btnForward);

        jPanel2.add(jPanel4, java.awt.BorderLayout.WEST);

        add(jPanel2, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void btnForwardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForwardActionPerformed
        forward();
    }//GEN-LAST:event_btnForwardActionPerformed

    private void btnBackwardsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackwardsActionPerformed
        backwards();
    }//GEN-LAST:event_btnBackwardsActionPerformed

    private void btnNewEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewEmployeeActionPerformed
        newEmployee();
    }//GEN-LAST:event_btnNewEmployeeActionPerformed

    private void tfSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSearchKeyReleased
        search();
    }//GEN-LAST:event_tfSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackwards;
    private javax.swing.JButton btnForward;
    private javax.swing.JButton btnNewEmployee;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblEmployee;
    private javax.swing.JTextField tfSearch;
    // End of variables declaration//GEN-END:variables

    private void search() {
        String query = tfSearch.getText();
        ArrayList<Employee> employees = new ArrayList<>();
        try {
             employees = eal.getEmployeesBySearchQuery(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        eatm.setEmployees(employees);
    }

    private void newEmployee() {
        NewEmployeeDialog ned = new NewEmployeeDialog(parent, true);
        ned.setVisible(true);
        Employee e = ned.getEmployee();
        if(e != null){
            eatm.getEmployees().add(e);
            eatm.fireTableDataChanged();
        }
    }

    private void backwards() {
        Stack<Employee> test = new Stack<>();
    }

    private void forward() {
        
    }
}
