/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Frames;

import BE.Alarm;
import BLL.Alarm_AccessLink;
import Presentation.Components.MyDateChooserCombo;
import datechooser.beans.DateChooserDialog;
import datechooser.events.SelectionChangedEvent;
import datechooser.events.SelectionChangedListener;
import datechooser.model.multiple.MultyModelBehavior;
import datechooser.model.multiple.Period;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Susanne
 */
public class Overview extends javax.swing.JPanel {

    Alarm_AccessLink aal;
    ShowUpList sul;
    TableModelAlarm alarmTableModel;
    MyDateChooserCombo dateChooser;

    /**
     * Creates new form Overview
     */
    public Overview() {
        initComponents();
        this.setVisible(true);
        alarmTableModel = new TableModelAlarm();
        tblAlarm.setModel(alarmTableModel);
        sul = new ShowUpList();
        dateChooser = new MyDateChooserCombo();
        dateChooser.setCalendarPreferredSize(new Dimension(500, 500));
        dateChooser.setBehavior(MultyModelBehavior.SELECT_PERIOD);
        dateChooser.addSelectionChangedListener(new SelectionChangedListener() {
            @Override
            public void onSelectionChange(SelectionChangedEvent sce) {
                search();
            }
        });
        jPanel3.add(dateChooser);
        try {
            aal = new Alarm_AccessLink();
        } catch (IOException ex) {
            Logger.getLogger(Overview.class.getName()).log(Level.SEVERE, null, ex);
        }
        page2.add(sul, BorderLayout.CENTER);
        ListSelectionModel lsm = tblAlarm.getSelectionModel();
        lsm.addListSelectionListener(new myTableSelectionListener());
        search();
        validate();

        repaint();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        page2 = new javax.swing.JPanel();
        overview = new javax.swing.JPanel();
        alarmInfo = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        chbxApproved = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAlarm = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        page2.setLayout(new java.awt.BorderLayout());
        add(page2, java.awt.BorderLayout.CENTER);

        overview.setLayout(new java.awt.BorderLayout());

        alarmInfo.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Dato:");
        jPanel3.add(jLabel1);

        jPanel1.add(jPanel3);

        chbxApproved.setText("Vis godkendte");
        chbxApproved.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbxApprovedActionPerformed(evt);
            }
        });
        jPanel4.add(chbxApproved);

        jPanel1.add(jPanel4);

        alarmInfo.add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel2.setText("Fremmøde ved:");
        jPanel2.add(jLabel2);

        alarmInfo.add(jPanel2, java.awt.BorderLayout.CENTER);

        overview.add(alarmInfo, java.awt.BorderLayout.NORTH);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 100));

        tblAlarm.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblAlarm);

        overview.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(overview, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents

    private void chbxApprovedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbxApprovedActionPerformed
        search();
    }//GEN-LAST:event_chbxApprovedActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel alarmInfo;
    private javax.swing.JCheckBox chbxApproved;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel overview;
    private javax.swing.JPanel page2;
    private javax.swing.JTable tblAlarm;
    // End of variables declaration//GEN-END:variables

    private void search() {
        Boolean approved = chbxApproved.isSelected();
        Iterable<Period> dates = dateChooser.getSelection();
        ArrayList<Alarm> alarms = new ArrayList<>();
        for (Period p : dates) {
            try {

                alarms.addAll(aal.getAlarmsByPeriodAndAccepted(p, approved));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        alarmTableModel.setAlarms(alarms);
        tblAlarm.getSelectionModel().setSelectionInterval(0, 0);
    }

    private class myTableSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            int selectedRow = tblAlarm.getSelectedRow();
            System.out.println("Her");
            if (selectedRow != -1) {
                try {
                    sul.SelectionChanged(alarmTableModel.getAlarmAt(selectedRow));
                } catch (SQLException ex) {
                    Logger.getLogger(Overview.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
