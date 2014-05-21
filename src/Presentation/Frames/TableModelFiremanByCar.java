/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Frames;

import BE.TimeSheet;
import BLL.MyUtil;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Susanne
 */
public class TableModelFiremanByCar extends AbstractTableModel {

    ArrayList<TimeSheet> timeSheet;
    String[] colNames = {"Medarb.Nr", "Fornavn", "Efternavn", "Starttid", "Sluttid", "Position", "Timer", "Godk.HL", "Timer.IL", "Godk.IL"};
    Class[] classes = {Integer.class, String.class, String.class, String.class, String.class, String.class, Integer.class, Integer.class, Integer.class, Boolean.class};

    public TableModelFiremanByCar(ArrayList<TimeSheet> timeSheet) {
        this.timeSheet = timeSheet;
        fireTableDataChanged();
    }

    public TableModelFiremanByCar() {
        this(new ArrayList<TimeSheet>());
    }

    @Override
    public int getRowCount() {
        return timeSheet.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return timeSheet.get(rowIndex).getEmployeeID();
            case 1:
                return timeSheet.get(rowIndex).getFirstName();
            case 2:
                return timeSheet.get(rowIndex).getLastName();
            case 3:
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(timeSheet.get(rowIndex).getStartTime().getTime());
                return MyUtil.p0(cal.get(Calendar.HOUR_OF_DAY)) + ":" + MyUtil.p0(cal.get(Calendar.MINUTE));
            case 4:
                cal = Calendar.getInstance();
                cal.setTimeInMillis(timeSheet.get(rowIndex).getEndTime().getTime());
                return MyUtil.p0(cal.get(Calendar.HOUR_OF_DAY)) + ":" + MyUtil.p0(cal.get(Calendar.MINUTE));
            case 5:
                return timeSheet.get(rowIndex).getPosition();
            case 6:
                return timeSheet.get(rowIndex).getHours();
            case 7:
                return timeSheet.get(rowIndex).getApprovedByTeamleader();
            case 8:
                return timeSheet.get(rowIndex).getHoursApproved();
            case 9:
                return timeSheet.get(rowIndex).getApprovedByCommander();

        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return classes[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    public void setTimeSheets(ArrayList<TimeSheet> TimeSheets) {
        
        this.timeSheet = TimeSheets;
        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 8 || columnIndex == 9) {
            return true;
        }
        return false;
    }

    public void clearTimeSheet() {
        
        timeSheet = new ArrayList<>();
        fireTableDataChanged();
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        TimeSheet t = timeSheet.get(rowIndex);
        switch (columnIndex) {
            case 8:
                t.setHoursApproved((int)aValue);
                t.setChange(true);
                break;
            case 9:
                t.setApprovedByCommander((boolean)aValue);
                t.setChange(true);
                break;
        }

    }
    public ArrayList<TimeSheet> getTimeSheets() {
        return timeSheet;
        
    }
}
