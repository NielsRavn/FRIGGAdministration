/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Frames;

import BE.Alarm;
import BLL.Alarm_AccessLink;
import BLL.MyUtil;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelAlarm extends AbstractTableModel {

    Alarm_AccessLink aal;
    ArrayList<Alarm> alarmList;
    String[] colNames = {"Destination", "Tidspunkt", "Melding", "Øvelse", "OdinNr.", "Godkendt"};
    Class[] classes = {String.class, String.class, String.class, Boolean.class, Integer.class, Boolean.class};

    /**
     * creates a table with a list of alarms
     *
     * @param alarmList
     */
    public TableModelAlarm(ArrayList<Alarm> alarmList) {
        try {
            aal = new Alarm_AccessLink();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"Der er sket en fejl. /r/n Forsætter fejlen kontakt da administartion med følgende fejl /r/n" + ex);
        }
        this.alarmList = alarmList;
        fireTableDataChanged();
    }

    /**
     * sets the Array of Alarms to be shown
     */
    public TableModelAlarm() {
        this(new ArrayList<Alarm>());
    }

    /**
     * gets the number of rows to be created
     *
     * @return the number of rows in the table
     */
    @Override
    public int getRowCount() {
        return alarmList.size();
    }

    /**
     * gets the number of columns to be shown
     *
     * @return the number of columns to be shown
     */
    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    /**
     * returns a value of a specific cell
     *
     * @param rowIndex the row of the cell
     * @param columnIndex the column of the cell
     * @return the value of the cell
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return alarmList.get(rowIndex).getDistination();
            case 1:
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(alarmList.get(rowIndex).getTime().getTime());
                return cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1) + " " + MyUtil.p0(cal.get(Calendar.HOUR_OF_DAY)) + ":" + MyUtil.p0(cal.get(Calendar.MINUTE));

            case 2:
                return alarmList.get(rowIndex).getType();
            case 3:
                return alarmList.get(rowIndex).isExercise();
            case 4:
                return alarmList.get(rowIndex).getOdinNr();
            case 5:
                return alarmList.get(rowIndex).isAccepted();
        }
        return null;
    }

    /**
     * returns if cell is editable
     *
     * @param rowIndex the row of the cell
     * @param columnIndex the column of the cell
     * @return true if the cell is editabel, and false if not
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 5) {
            return true;
        }
        return false;
    }

    /**
     * changes the value of a specific cell 
     *
     * @param aValue the value set in
     * @param rowIndex the row of the cell
     * @param columnIndex the column of the cell
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Alarm a = alarmList.get(rowIndex);

        switch (columnIndex) {
            case 5:
                boolean accepted = (boolean) aValue;
                if (accepted) {
                    int pickedOption = JOptionPane.showConfirmDialog(null, "Dette vil fjerne muligheden for flere at tjekke sig på pågældende alarm", "Er du sikker?!", JOptionPane.OK_CANCEL_OPTION);
                    if (pickedOption == 0) {
                        a.setAccepted(accepted);
                        a.setChange(true);
                    }
                } else {
                    a.setAccepted(accepted);
                    a.setChange(true);
                }
                saveChangedRows();
                break;
        }
    }

    /**
     * this will save the changes in the selected rows
     */
    private void saveChangedRows() {
        ArrayList<Alarm> changes = alarmList;
        for (Alarm a : changes) {
            if (a.isChange()) {
                try {
                    aal.updateAlarm(a);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "der er sket en fejl, ingen ændringer er gemt /r/n forsætter feljen kontakt ademin med følgende feljbeskrivelse /r/n" + ex);
                }
            }
        }
    }

    /**
     * gets the class of the object to be shown in a column
     *
     * @param columnIndex the column to look in
     * @return the class that the values to be shown in the column goes under.
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return classes[columnIndex];
    }

    /**
     * gets the colum name to be shown over the column
     *
     * @param column the column to get the name from
     * @return the name of the column
     */
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    /**
     * sets the arraylist of alarms to be shown by updating the table
     *
     * @param alarmList an array of alarm
     */
    public void setAlarms(ArrayList<Alarm> alarmList) {
        this.alarmList = alarmList;
        fireTableDataChanged();
    }

    /**
     * gets the alarm on the selcted row
     *
     * @param selectedRow the selcted row
     * @return the alarm
     */
    public Alarm getAlarmAt(int selectedRow) {
        return alarmList.get(selectedRow);
    }
}
