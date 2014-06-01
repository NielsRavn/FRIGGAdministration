package Presentation.Frames;

import BE.Alarm;
import BE.TimeSheet;
import BLL.Alarm_AccessLink;
import BLL.TimeSheet_AccessLink;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Susanne
 */
public class ShowUpList extends javax.swing.JPanel {

    Alarm_AccessLink aal;
    TimeSheet_AccessLink tsal;
    TableModelFiremanByCar tableFiremanByCar;
    int alarmID;
    final String stationsVagt = "Stationsvagt";

    /**
     * Creates new form ShowUpList
     */
    public ShowUpList() {
        try {
            aal = new Alarm_AccessLink();
            tsal = new TimeSheet_AccessLink();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Der er sket en fejl. /r/n Forsætter fejlen kontakt da administartion med følgende fejl /r/n" + ex);
        }
        initComponents();
        this.setVisible(true);
        cbxCar.addActionListener(new myComboBoxSelectionListener());
        tableFiremanByCar = new TableModelFiremanByCar();
        tblShowUpOnCar.setModel(tableFiremanByCar);
        tblShowUpOnCar.getTableHeader().setReorderingAllowed(false);
        ListSelectionModel lsm = tblShowUpOnCar.getSelectionModel();
        lsm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
        tblShowUpOnCar = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblBil = new javax.swing.JLabel();
        cbxCar = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        btnApprove = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        tblShowUpOnCar.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblShowUpOnCar);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.BorderLayout());

        lblBil.setText("Bil :");
        jPanel1.add(lblBil);

        cbxCar.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel1.add(cbxCar);

        jPanel3.add(jPanel1, java.awt.BorderLayout.WEST);

        add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new java.awt.BorderLayout());

        btnApprove.setText("Gem");
        btnApprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApproveActionPerformed(evt);
            }
        });
        jPanel2.add(btnApprove, java.awt.BorderLayout.EAST);

        add(jPanel2, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void btnApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApproveActionPerformed
        saveChangedRows();
    }//GEN-LAST:event_btnApproveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApprove;
    private javax.swing.JComboBox cbxCar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBil;
    private javax.swing.JTable tblShowUpOnCar;
    // End of variables declaration//GEN-END:variables

    /**
     * when the Alarm in Overview is changed the 
     * combobox is filled with the new date
     * 
     * @param alarm changes the content of the combobox 
     * @throws SQLException 
     */
    void SelectionChanged(Alarm alarm) throws SQLException {
        clearTimeSheet();

        ArrayList<Integer> cars = new ArrayList<>();
        alarmID = alarm.getID();
        cars.addAll(aal.getCarNrByAlarmID(alarmID));
        for (Integer i : cars) {
            if (i == 0) {
                cbxCar.addItem(stationsVagt);
            } else {
                cbxCar.addItem(i);
            }
        }
    }
    
    /*
     * Saves the rows which has changes
     */
    
    private void saveChangedRows() {
        ArrayList<TimeSheet> changes = tableFiremanByCar.getTimeSheets();
        for (TimeSheet t : changes) {
            if (t.isChange()) {
                try {
                    tsal.updateTimesheet(t);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"Der er sket en fejl, ingen ændringer er gemt. /r/n Forsætter fejlen kontakt da administartion med følgende fejl /r/n" + ex);

                }
            }
        }
    }

    /*
     * Listen to when the selection in the combobox is changed
     * and gets the selected content and displays it in the Table
     */
    private class myComboBoxSelectionListener implements ActionListener {

        ArrayList<TimeSheet> employees;

        @Override
        public void actionPerformed(ActionEvent e) {
            employees = new ArrayList<>();
            if (cbxCar.getSelectedItem() != null && !cbxCar.getSelectedItem().equals(stationsVagt)) {
                try {
                    employees = tsal.getTimeSheetByCarNrAndAlarmID(alarmID, ((int) cbxCar.getSelectedItem()));
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"Der er sket en fejl. /r/n Forsætter fejlen kontakt da administartion med følgende fejl /r/n" + ex);
                }
            } else if (cbxCar.getSelectedItem() != null) {
                try {
                    employees = tsal.getTimeSheetByCarNrAndAlarmID(alarmID, 0);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"Der er sket en fejl. /r/n Forsætter fejlen kontakt da administartion med følgende fejl /r/n" + ex);
                }
            }
            tableFiremanByCar.setTimeSheets(employees);
        }
    }

    /*
     * clears all items from the Table and Checkbox
     */
    public void clearTimeSheet() {
        tableFiremanByCar.clearTimeSheet();
        cbxCar.removeAllItems();
    }
}
