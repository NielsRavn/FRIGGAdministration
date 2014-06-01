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

    public TableModelAlarm(ArrayList<Alarm> alarmList) {
    try {
        aal = new Alarm_AccessLink();
    } catch (IOException ex) {
        Logger.getLogger(TableModelAlarm.class.getName()).log(Level.SEVERE, null, ex);
    }
        this.alarmList = alarmList;
        fireTableDataChanged();
    }

    public TableModelAlarm() {
        this(new ArrayList<Alarm>());
    }

    @Override
    public int getRowCount() {
        return alarmList.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

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

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 5) {
            return true;
        }
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Alarm a = alarmList.get(rowIndex);
        
        switch (columnIndex) {
            case 5:
                boolean accepted = (boolean) aValue;
                if (accepted) {
                    int pickedOption = JOptionPane.showConfirmDialog(null, "Dette vil fjerne muligheden for flere at tjekke sig på pågældende alarm", "Er du sikker?!", JOptionPane.OK_CANCEL_OPTION) ;
                    if( pickedOption == 0){
                        a.setAccepted(accepted);
                        a.setChange(true);
                    }
                }
                else {
                    a.setAccepted(accepted);
                    a.setChange(true);
                }
                saveChangedRows();
                break;
        }
    }

        private void saveChangedRows() {
        ArrayList<Alarm> changes = alarmList;
        for (Alarm a : changes) {
            if (a.isChange()) {
                try {
                    aal.updateAlarm(a);
                } catch (SQLException ex) {
                    Logger.getLogger(TableModelAlarm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return classes[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    public void setAlarms(ArrayList<Alarm> alarmList) {
        this.alarmList = alarmList;
        fireTableDataChanged();
    }

    public Alarm getAlarmAt(int selectedRow) {
        return alarmList.get(selectedRow);
    }

}
