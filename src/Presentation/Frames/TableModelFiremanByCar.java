/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Frames;

import BE.TimeSheet;
import BLL.MyUtil;
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
/**
 * Creates a Table of timeSheets
 * @param timeSheet 
 */
    public TableModelFiremanByCar(ArrayList<TimeSheet> timeSheet) {
        this.timeSheet = timeSheet;
        fireTableDataChanged();
    }
  
    public TableModelFiremanByCar() {
        this(new ArrayList<TimeSheet>());
    }
    
/**
 * gets the number of rows to be created
     * @return the number of rows in the table 
 */
    
    @Override
    public int getRowCount() {
        return timeSheet.size();
    }
    
/**
 * 
 * @return the number of columns to be shown 
 */
    
    @Override
    public int getColumnCount() {
        return colNames.length;
    }
    
/**
 * Returns the value of a specific cell
 * @param rowIndex the row of the cell
 * @param columnIndex the column of the cell    
 * @return the value of the cell
 */
    
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
    
/**
 * gets the class of the object to be shown in a column
 * @param columnIndex the column to look in
 * @return the class that the values to be shown in the column goes under.
 */
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return classes[columnIndex];
    }

    /**
     * gets the column name to be shown over the columns
     * @param column the column to get the name for
     * @return the name of the column
     */
    
    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
    
/**
 * sets the array of timesheet to be shown and updates the table
 * @param TimeSheets an array
 */
    
    public void setTimeSheets(ArrayList<TimeSheet> TimeSheets) {
        
        this.timeSheet = TimeSheets;
        fireTableDataChanged();
    }
    
/**
 * Tells witch cells that should be editable 
 * @param rowIndex tells the row of the cell
 * @param columnIndex tells the column of the cell
 * @return true if the cell can be edited and false otherwise. here ist only column 7 and 8 (columns starts with 0)
 */
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 8 || columnIndex == 9) {
            return true;
        }
        return false;
    }

    /**
     * clears the Arraylist and the table
     */
    
    public void clearTimeSheet() {
        
        timeSheet = new ArrayList<>();
        fireTableDataChanged();
    }

    /**
     * sets a value of a specific cell
     * @param aValue the value set in
     * @param rowIndex the row of the cell
     * @param columnIndex the colum of the cell 
     */
    
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
    
    /**
     * 
     * @return the arraylist of the currently shown data of the table
     */
    
    public ArrayList<TimeSheet> getTimeSheets() {
        return timeSheet;
        
    }
}
