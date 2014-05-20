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

    ArrayList<TimeSheet> employees;
    String[] colNames = {"Medarb.Nr", "Fornavn", "Efternavn", "Starttid", "Sluttid", "TimerHL", "Godk.HL", "TimerIL", "Godk.IL", "Position"};
    Class[] classes = {Integer.class, String.class, String.class, String.class, String.class, Integer.class, Integer.class, Integer.class, Boolean.class, String.class};

    public TableModelFiremanByCar(ArrayList<TimeSheet> timeSheet) {
        this.employees = timeSheet;
        fireTableDataChanged();
    }

    public TableModelFiremanByCar() {
        this(new ArrayList<TimeSheet>());
    }

    @Override
    public int getRowCount() {
        return employees.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return employees.get(rowIndex).getEmployeeID();
            case 1:
                return employees.get(rowIndex).getFirstName();
            case 2:
                return employees.get(rowIndex).getLastName();
            case 3:
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(employees.get(rowIndex).getStartTime().getTime());
                return cal.get(Calendar.HOUR_OF_DAY) + ":" + MyUtil.p0(cal.get(Calendar.MINUTE));
            case 4:
                cal = Calendar.getInstance();
                cal.setTimeInMillis(employees.get(rowIndex).getEndTime().getTime());
                return cal.get(Calendar.HOUR_OF_DAY) + ":" + MyUtil.p0(cal.get(Calendar.MINUTE));
            case 5:
                return employees.get(rowIndex).getHours();
            case 6:
                return employees.get(rowIndex).getHoursApproved();
            case 7:
                return employees.get(rowIndex).getApprovedByTeamleader();
            case 8:
                return employees.get(rowIndex).getApprovedByCommander();
            case 9:
                return employees.get(rowIndex).getPosition();

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
        this.employees = TimeSheets;
        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return super.isCellEditable(rowIndex, columnIndex); //To change body of generated methods, choose Tools | Templates.
    }

}
