/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Frames;

import BE.Alarm;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TableModelAlarm extends AbstractTableModel {

    ArrayList<Alarm> alarmList;
    String[] colNames = {"Destination", "Tidspunkt", "Melding", "Godkendt", "OdinNr.", "Øvelse"};
    Class[] classes = {String.class, Timestamp.class, String.class, Boolean.class, Integer.class, Boolean.class};

    public TableModelAlarm(ArrayList<Alarm> alarmList) {
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
                return alarmList.get(rowIndex).getTime();
            case 2:
                return alarmList.get(rowIndex).getType();
            case 3:
                return alarmList.get(rowIndex).isAccepted();
            case 4:
                return alarmList.get(rowIndex).getOdinNr();
            case 5:
                return alarmList.get(rowIndex).isExercise();
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return classes[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
    public void setAlarms (ArrayList <Alarm> alarmList) {
        this.alarmList = alarmList;
        fireTableDataChanged();
    }

}
